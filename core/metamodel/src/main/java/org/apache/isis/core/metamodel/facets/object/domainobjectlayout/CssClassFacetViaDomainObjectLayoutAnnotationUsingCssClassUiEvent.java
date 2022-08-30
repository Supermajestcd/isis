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
package org.apache.isis.core.metamodel.facets.object.domainobjectlayout;

import java.lang.reflect.InvocationTargetException;
import java.util.Optional;
import java.util.function.BiConsumer;

import org.apache.isis.applib.annotation.DomainObjectLayout;
import org.apache.isis.applib.events.ui.CssClassUiEvent;
import org.apache.isis.applib.exceptions.UnrecoverableException;
import org.apache.isis.commons.internal.base._Casts;
import org.apache.isis.core.config.IsisConfiguration;
import org.apache.isis.core.metamodel.facetapi.FacetHolder;
import org.apache.isis.core.metamodel.facets.members.cssclass.CssClassFacet;
import org.apache.isis.core.metamodel.facets.members.cssclass.CssClassFacetAbstract;
import org.apache.isis.core.metamodel.object.ManagedObject;
import org.apache.isis.core.metamodel.object.ManagedObjects;
import org.apache.isis.core.metamodel.services.events.MetamodelEventService;
import org.apache.isis.core.metamodel.util.EventUtil;

public class CssClassFacetViaDomainObjectLayoutAnnotationUsingCssClassUiEvent
extends CssClassFacetAbstract {

    public static Optional<CssClassFacetViaDomainObjectLayoutAnnotationUsingCssClassUiEvent> create(
            final Optional<DomainObjectLayout> domainObjectLayoutIfAny,
            final MetamodelEventService metamodelEventService,
            final IsisConfiguration configuration,
            final FacetHolder facetHolder) {

        return domainObjectLayoutIfAny
        .map(DomainObjectLayout::cssClassUiEvent)
        .filter(cssClassUiEventClass -> EventUtil.eventTypeIsPostable(
                cssClassUiEventClass,
                CssClassUiEvent.Noop.class,
                CssClassUiEvent.Default.class,
                configuration.getApplib().getAnnotation().getDomainObjectLayout().getCssClassUiEvent().isPostForDefault()))
        .map(cssClassUiEventClass -> {
            return new CssClassFacetViaDomainObjectLayoutAnnotationUsingCssClassUiEvent(
                    cssClassUiEventClass, metamodelEventService, facetHolder);
        });

    }

    private final Class<? extends CssClassUiEvent<?>> cssClassUiEventClass;
    private final MetamodelEventService metamodelEventService;

    private CssClassFacetViaDomainObjectLayoutAnnotationUsingCssClassUiEvent(
            final Class<? extends CssClassUiEvent<?>> cssClassUiEventClass,
                    final MetamodelEventService metamodelEventService,
                    final FacetHolder holder) {

        super(holder, Precedence.EVENT);
        this.cssClassUiEventClass = cssClassUiEventClass;
        this.metamodelEventService = metamodelEventService;
    }

    @Override
    public String cssClass(final ManagedObject owningAdapter) {

        if(ManagedObjects.isNullOrUnspecifiedOrEmpty(owningAdapter)) {
            return null;
        }

        final CssClassUiEvent<Object> cssClassUiEvent = newCssClassUiEvent(owningAdapter);

        metamodelEventService.fireCssClassUiEvent(cssClassUiEvent);

        final String cssClass = cssClassUiEvent.getCssClass();

        if(cssClass == null) {
            // ie no subscribers out there...

            final CssClassFacet underlyingCssClassFacet = getSharedFacetRanking()
            .flatMap(facetRanking->facetRanking.getWinnerNonEvent(CssClassFacet.class))
            .orElse(null);

            if(underlyingCssClassFacet!=null) {
                return underlyingCssClassFacet.cssClass(owningAdapter);
            }
        }

        return cssClass;
    }

    private CssClassUiEvent<Object> newCssClassUiEvent(final ManagedObject owningAdapter) {
        final Object domainObject = owningAdapter.getPojo();
        return newCssClassUiEventForPojo(domainObject);
    }

    private CssClassUiEvent<Object> newCssClassUiEventForPojo(final Object domainObject) {
        try {
            final CssClassUiEvent<Object> cssClassUiEvent = _Casts.uncheckedCast(
                    cssClassUiEventClass.getConstructor().newInstance());
            cssClassUiEvent.initSource(domainObject);
            return cssClassUiEvent;
        } catch (InstantiationException | IllegalAccessException
                | IllegalArgumentException | InvocationTargetException
                | NoSuchMethodException | SecurityException ex) {
            throw new UnrecoverableException(ex);
        }
    }

    @Override
    public void visitAttributes(final BiConsumer<String, Object> visitor) {
        super.visitAttributes(visitor);
        visitor.accept("cssClassUiEventClass", cssClassUiEventClass);
    }
}
