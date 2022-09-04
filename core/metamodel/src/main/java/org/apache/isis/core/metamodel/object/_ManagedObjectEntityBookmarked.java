/*
 *  Licensed to the Apache Software Foundation (ASF) under one
 *  or more contributor license agreements.  See the NOTICE file
 *  distributed with this work for additional information
 *  regarding copyright ownership.  The ASF licenses this file
 *  to you under the Apache License, Version 2.0 (the
 *  "License"); you may not use this file except in compliance
 *  with the License.  You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing,
 *  software distributed under the License is distributed on an
 *  "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 *  KIND, either express or implied.  See the License for the
 *  specific language governing permissions and limitations
 *  under the License.
 */
package org.apache.isis.core.metamodel.object;

import java.util.Optional;

import org.springframework.lang.Nullable;

import org.apache.isis.applib.exceptions.unrecoverable.ObjectNotFoundException;
import org.apache.isis.applib.services.bookmark.Bookmark;
import org.apache.isis.applib.services.repository.EntityState;
import org.apache.isis.commons.internal.debug._Debug;
import org.apache.isis.commons.internal.debug.xray.XrayUi;
import org.apache.isis.commons.internal.exceptions._Exceptions;
import org.apache.isis.core.metamodel.facets.object.entity.EntityFacet;
import org.apache.isis.core.metamodel.spec.ObjectSpecification;

import lombok.NonNull;
import lombok.val;
import lombok.extern.log4j.Log4j2;

/**
 * (package private) specialization corresponding to a attached {@link Specialization#ENTITY}
 * @see ManagedObject.Specialization#ENTITY
 */
@Log4j2
final class _ManagedObjectEntityBookmarked
extends _ManagedObjectSpecified
implements _Refetchable {

    private /*final*/ @Nullable Object pojo;
    private final @NonNull Bookmark bookmark;

    _ManagedObjectEntityBookmarked(
            final ObjectSpecification spec,
            final Object pojo,
            final @NonNull Optional<Bookmark> bookmarkIfKnown) {
        super(ManagedObject.Specialization.ENTITY, spec);
        this.pojo = assertCompliance(pojo);
        //sanity check bookmark
        this.bookmark = entityFacet().validateBookmark(bookmarkIfKnown
                .orElseGet(this::createBookmark));
    }

    @Override
    public Optional<Bookmark> getBookmark() {
        return Optional.of(bookmark);
    }

    @Override
    public boolean isBookmarkMemoized() {
        return true;
    }

    @Override
    public Object peekAtPojo() {
        return pojo;
    }

    @Override
    public Object getPojo() {

        // refetch if required ...

        val entityFacet = entityFacet();

        val entityState = entityFacet.getEntityState(pojo);
        if(!entityState.isPersistable()) {
            throw _Exceptions.illegalState("not persistable %s", getSpecification());
        }
        if(entityState.isAttached()) {
            return pojo; // is attached
        }

        if(entityState.isSpecicalJpaDetachedWithOid()) {
            return entityFacet.fetchByBookmark(bookmark)
                    .orElse(pojo);
        }

        // throws on deleted entity
        val reattachedPojo = entityFacet.fetchByBookmark(bookmark)
                .orElseThrow(()->{
                    return new ObjectNotFoundException(""+bookmark);});

        if(!entityFacet.getEntityState(reattachedPojo).hasOid()) {
            throw _Exceptions.illegalState("entity not attached after refetch attempt %s", bookmark);
        }

        return this.pojo = assertCompliance(reattachedPojo);
    }

    @Override
    public @NonNull EntityState getEntityState() {
        val entityFacet = entityFacet();
        return entityFacet.getEntityState(pojo);
    }

    // -- HELPER

    private EntityFacet entityFacet() {
        return getSpecification().entityFacetElseFail();
    }

    @SuppressWarnings("deprecation")
    private Bookmark createBookmark() {
        val entityFacet = entityFacet();

        // fail early when detached entities are detected
        // should have been re-fetched at start of this request-cycle
        if(
//                && EntityUtil.getPersistenceStandard(managedObject)
//                    .map(PersistenceStandard::isJdo)
//                    .orElse(false)
                !entityFacet.getEntityState(pojo).hasOid()) {

            _Debug.onCondition(XrayUi.isXrayEnabled(), ()->{
                _Debug.log("detached entity detected %s", pojo);
            });

            val msg = String.format(
                    "The persistence layer does not recognize given object %s, "
                    + "meaning the object has no identifier that associates it with the persistence layer. "
                    + "(most likely, because the object is detached, eg. was not persisted after being new-ed up)",
                    getSpecification());

            // in case of the exception getting swallowed, also write a log
            log.error(msg);

            throw _Exceptions.illegalArgument(msg);
        }

        return entityFacet.bookmarkForElseFail(pojo);
    }

}