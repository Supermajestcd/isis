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
package org.apache.isis.core.runtimeservices.memento;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;
import java.util.function.Function;

import org.springframework.lang.Nullable;

import org.apache.isis.applib.id.HasLogicalType;
import org.apache.isis.applib.id.LogicalType;
import org.apache.isis.applib.services.bookmark.Bookmark;
import org.apache.isis.applib.services.bookmark.Oid;
import org.apache.isis.applib.services.hint.HintIdProvider;
import org.apache.isis.commons.internal.base._Casts;
import org.apache.isis.commons.internal.base._NullSafe;
import org.apache.isis.commons.internal.exceptions._Exceptions;
import org.apache.isis.core.metamodel.context.MetaModelContext;
import org.apache.isis.core.metamodel.facets.object.value.ValueFacet;
import org.apache.isis.core.metamodel.facets.object.value.ValueSerializer.Format;
import org.apache.isis.core.metamodel.object.ManagedObject;
import org.apache.isis.core.metamodel.object.ManagedObjects;
import org.apache.isis.core.metamodel.object.MmTitleUtil;
import org.apache.isis.core.metamodel.objectmanager.memento.ObjectMemento;
import org.apache.isis.core.metamodel.spec.ObjectSpecification;
import org.apache.isis.core.metamodel.specloader.SpecificationLoader;
import org.apache.isis.core.metamodel.util.Facets;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.val;
import lombok.extern.log4j.Log4j2;

@Log4j2
final class _ObjectMemento implements HasLogicalType, Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * Factory method
     */
    public static _ObjectMemento createOrNull(final ManagedObject adapter) {
        if(ManagedObjects.isNullOrUnspecifiedOrEmpty(adapter)) {
            return null;
        }
        return new _ObjectMemento(adapter);
    }

    /**
     * Factory method
     */
    static _ObjectMemento createPersistent(
            final Bookmark bookmark,
            final SpecificationLoader specificationLoader) {

        return new _ObjectMemento(bookmark, specificationLoader);
    }

    private enum Cardinality {
        /**
         * represents a single object
         */
        SCALAR {

            @Override
            public ManagedObject asAdapter(
                    final _ObjectMemento memento,
                    final MetaModelContext mmc) {
                return memento.recreateStrategy.recreateObject(memento, mmc);
            }

            @Override
            public Bookmark asPseudoBookmark(final _ObjectMemento memento) {
                return memento.recreateStrategy.asPseudoBookmark(memento);
            }

            @Override
            public int hashCode(final _ObjectMemento memento) {
                return memento.recreateStrategy.hashCode(memento);
            }

            @Override
            public boolean equals(final _ObjectMemento memento, final Object other) {
                if (!(other instanceof _ObjectMemento)) {
                    return false;
                }
                final _ObjectMemento otherMemento = (_ObjectMemento) other;
                if(otherMemento.cardinality != SCALAR) {
                    return false;
                }
                return memento.recreateStrategy.equals(memento, otherMemento);
            }
        },
        /**
         * represents a list of objects
         */
        VECTOR {

            @Override
            public ManagedObject asAdapter(
                    final _ObjectMemento memento,
                    final MetaModelContext mmc) {

                // I believe this code path is no longer reachable
                throw _Exceptions.unexpectedCodeReach();

//                final Can<ManagedObject> managedObjects =
//                        _NullSafe.stream(memento.list)
//                        .map(Functions.toManagedObject(mmc))
//                        .collect(Can.toCan());
//
//                val commonSpec = ManagedObjects.commonSpecification(managedObjects)
//                        .orElseGet(()->mmc.getSpecificationLoader().loadSpecification(Object.class));
//
//                return ManagedObject.packed(commonSpec, managedObjects);
            }

            @Override
            public Bookmark asPseudoBookmark(final _ObjectMemento memento) {
                return Bookmark.forLogicalTypeNameAndIdentifier(
                        memento.getLogicalTypeName(),
                        memento.list.toString());
            }

            @Override
            public int hashCode(final _ObjectMemento memento) {
                return memento.list.hashCode();
            }

            @Override
            public boolean equals(final _ObjectMemento memento, final Object other) {
                if (!(other instanceof _ObjectMemento)) {
                    return false;
                }
                final _ObjectMemento otherMemento = (_ObjectMemento) other;
                if(otherMemento.cardinality != VECTOR) {
                    return false;
                }
                return memento.list.equals(otherMemento.list);
            }

        };

        void ensure(final Cardinality sort) {
            if(this == sort) {
                return;
            }
            throw new IllegalStateException("Memento is not for " + sort);
        }

        public abstract ManagedObject asAdapter(
                _ObjectMemento memento,
                MetaModelContext mmc);

        public abstract int hashCode(_ObjectMemento memento);

        public abstract boolean equals(_ObjectMemento memento, Object other);

        public abstract Bookmark asPseudoBookmark(_ObjectMemento memento);
    }

    private enum RecreateStrategy {
        /**
         * The {@link ManagedObject} that this is the memento for, directly has
         * an {@link ValueFacet} (it is almost certainly a value), and so is
         * stored directly.
         */
        VALUE {
            @Override
            public ManagedObject recreateObject(
                    final _ObjectMemento memento,
                    final MetaModelContext mmc) {

                val valueSerializer = mmc.getSpecificationLoader()
                        .specForLogicalType(memento.logicalType)
                        .flatMap(spec->Facets.valueSerializer(spec, spec.getCorrespondingClass()))
                        .orElseThrow(()->_Exceptions.unrecoverable(
                                "logical type %s is expected to have a ValueFacet", memento.logicalType));

                return mmc.getObjectManager().adapt(
                        valueSerializer.fromEncodedString(Format.JSON, memento.encodableValue));
            }

            @Override
            public Bookmark asPseudoBookmark(final _ObjectMemento memento) {
                return Bookmark.forLogicalTypeNameAndIdentifier(
                        memento.getLogicalTypeName(),
                        memento.encodableValue);
            }

            @Override
            public boolean equals(
                    final _ObjectMemento memento,
                    final _ObjectMemento otherMemento) {

                return otherMemento.recreateStrategy == VALUE &&
                        memento.encodableValue.equals(otherMemento.encodableValue);
            }

            @Override
            public int hashCode(final _ObjectMemento memento) {
                return memento.encodableValue.hashCode();
            }

            @Override
            public void resetVersion(
                    final _ObjectMemento memento,
                    final MetaModelContext mmc) {
            }
        },
        /**
         * The {@link ManagedObject} that this is for, is already known by its
         * (persistent) {@link Oid}.
         */
        LOOKUP {
            @Override
            public @Nullable ManagedObject recreateObject(
                    final _ObjectMemento memento,
                    final MetaModelContext mmc) {

                if(_NullSafe.isEmpty(memento.persistentOidStr)) {
                    throw _Exceptions.illegalArgument(
                            "need an id to lookup an object, got logical-type %s", memento.logicalType);
                }

                final Bookmark bookmark = Bookmark.parseElseFail(memento.persistentOidStr);

                try {

                    log.debug("lookup by oid [{}]", bookmark);
                    return mmc.loadObject(bookmark).orElse(null);

                } finally {
                    // possibly out-dated insight ...
                    // a side-effect of AdapterManager#adapterFor(...) is that it will update the oid
                    // with the correct version, even when there is a concurrency exception
                    // we copy this updated oid string into our memento so that, if we retry,
                    // we will succeed second time around

                    memento.persistentOidStr = bookmark.stringify();
                }
            }

            @Override
            public void resetVersion(
                    final _ObjectMemento memento,
                    final MetaModelContext mmc) {

                //XXX REVIEW: this may be redundant because recreateAdapter also guarantees the version will be reset.
                ManagedObject adapter = recreateObject(memento, mmc);

                memento.persistentOidStr = ManagedObjects.stringifyElseFail(adapter);
            }

            @Override
            public Bookmark asPseudoBookmark(final _ObjectMemento memento) {
                return memento.asBookmark();
            }

            @Override
            public boolean equals(final _ObjectMemento oam, final _ObjectMemento other) {
                return other.recreateStrategy == LOOKUP
                        && oam.persistentOidStr.equals(other.persistentOidStr);
            }

            @Override
            public int hashCode(final _ObjectMemento oam) {
                return oam.persistentOidStr.hashCode();
            }

        },
        /**
         * If all other strategies fail, as last resort we use plain java serialization, provided
         * that the type in question is serializable
         */
        SERIALIZABLE {
            @Override
            public ManagedObject recreateObject(
                    final _ObjectMemento memento,
                    final MetaModelContext mmc) {
                ObjectSpecification spec = mmc.getSpecificationLoader()
                        .specForLogicalTypeElseFail(memento.logicalType);
                return mmc.getObjectManager().getObjectSerializer()
                        .deserialize(spec, memento.serializedObject);
            }

            @Override
            public boolean equals(
                    final _ObjectMemento memento,
                    final _ObjectMemento otherMemento) {
                return otherMemento.recreateStrategy == SERIALIZABLE
                        && Objects.equals(memento.logicalType, otherMemento.logicalType)
                        && Objects.equals(memento.serializedObject, otherMemento.serializedObject);
            }

            @Override
            public int hashCode(final _ObjectMemento memento) {
                return Arrays.hashCode(memento.serializedObject); // potentially expensive, unfortunately cannot be cached in enum
            }

            @Override
            public Bookmark asPseudoBookmark(final _ObjectMemento memento) {
                return Bookmark.forLogicalTypeNameAndIdentifier(
                        memento.getLogicalTypeName(),
                        "SERIALIZABLE");
            }

            @Override
            public void resetVersion(
                    final _ObjectMemento memento,
                    final MetaModelContext mmc) {
                // nope
            }
        };

        public abstract @Nullable ManagedObject recreateObject(
                _ObjectMemento memento,
                MetaModelContext mmc);

        public abstract boolean equals(
                _ObjectMemento memento,
                _ObjectMemento otherMemento);

        public abstract int hashCode(_ObjectMemento memento);

        public abstract Bookmark asPseudoBookmark(_ObjectMemento memento);

        public abstract void resetVersion(
                _ObjectMemento memento,
                MetaModelContext mmc);
    }



    private final Cardinality cardinality;
    @Getter(onMethod_ = {@Override}) private final LogicalType logicalType;

    /**
     * Populated only if {@link #getCardinality() sort} is {@link Cardinality#SCALAR scalar}
     */
    private RecreateStrategy recreateStrategy;

    /**
     * Populated only if {@link #getCardinality() sort} is {@link Cardinality#SCALAR scalar}
     */
    @SuppressWarnings("unused")
    private String titleHint;

    /**
     * The current value, if {@link RecreateStrategy#VALUE}; will be <tt>null</tt> otherwise.
     *
     * <p>
     * Also, populated only if {@link #getCardinality() sort} is {@link Cardinality#SCALAR scalar}
     */
    private String encodableValue;

    /**
     * The current value, if {@link RecreateStrategy#SERIALIZABLE}; will be <tt>null</tt> otherwise.
     *
     * <p>
     * Also, populated only if {@link #getCardinality() sort} is {@link Cardinality#SCALAR scalar}
     */
    private byte[] serializedObject;

    /**
     * The current value, if {@link RecreateStrategy#LOOKUP}, will be <tt>null</tt> otherwise.
     *
     * <p>
     * Also, populated only if {@link #getCardinality() sort} is {@link Cardinality#SCALAR scalar}
     */
    private String persistentOidStr;

    /**
     * The current value, if {@link RecreateStrategy#LOOKUP}, will be <tt>null</tt> otherwise.
     *
     * <p>
     * Also, populated only if {@link #getCardinality() sort} is {@link Cardinality#SCALAR scalar}
     */
    private Bookmark bookmark;

    /**
     * Untranslated String for rendering.
     */
    private String titleString;

    /**
     * populated only if {@link #getCardinality() sort} is {@link Cardinality#VECTOR vector}
     */
    private ArrayList<_ObjectMemento> list;

    private _ObjectMemento(
            final ArrayList<_ObjectMemento> list,
            final LogicalType logicalType) {

        this.cardinality = Cardinality.VECTOR;
        this.list = list;
        this.logicalType = logicalType;
    }

    private _ObjectMemento(final Bookmark bookmark, final SpecificationLoader specificationLoader) {

        // -- // TODO[2112] do we ever need to create ENCODEABLE here?
        val logicalTypeName = bookmark.getLogicalTypeName();
        val spec = specificationLoader.specForLogicalTypeName(logicalTypeName)
                .orElseThrow(()->_Exceptions.unrecoverable(
                        "cannot recreate spec from logicalTypeName %s", logicalTypeName));

        this.cardinality = Cardinality.SCALAR;
        this.logicalType = spec.getLogicalType();

        if(spec.isValue()) {
            this.encodableValue = bookmark.getIdentifier();
            this.recreateStrategy = RecreateStrategy.VALUE;
            return;
        }

        this.persistentOidStr = bookmark.stringify();
        Objects.requireNonNull(persistentOidStr, "persistentOidStr");

        this.bookmark = bookmark;
        this.recreateStrategy = RecreateStrategy.LOOKUP;
    }

    private _ObjectMemento(final @NonNull ManagedObject adapter) {
        this.cardinality = Cardinality.SCALAR;
        val spec = adapter.getSpecification();
        this.logicalType = spec.getLogicalType();
        init(adapter);
    }

    private _ObjectMemento(final LogicalType logicalType, final String encodableValue) {
        this.cardinality = Cardinality.SCALAR;
        this.logicalType = logicalType;
        this.encodableValue = encodableValue;
        this.recreateStrategy = RecreateStrategy.VALUE;
    }

    private void init(final ManagedObject adapter) {

        titleString = MmTitleUtil.titleOf(adapter);

        val spec = adapter.getSpecification();

        if(spec.isIdentifiable() || spec.isParented() ) {
            val hintId = adapter.getPojo() instanceof HintIdProvider
                 ? ((HintIdProvider) adapter.getPojo()).hintId()
                 : null;

            bookmark = ManagedObjects.bookmarkElseFail(adapter);
            bookmark = hintId != null
                    && bookmark != null
                        ? bookmark.withHintId(hintId)
                        : bookmark;

            persistentOidStr = bookmark.stringify();
            recreateStrategy = RecreateStrategy.LOOKUP;
            return;
        }

        val valueSerializer = Facets.valueSerializer(spec, spec.getCorrespondingClass())
                .orElse(null);
        val isEncodable = valueSerializer != null;
        if (isEncodable) {
            encodableValue = valueSerializer.toEncodedString(Format.JSON, _Casts.uncheckedCast(adapter.getPojo()));
            recreateStrategy = RecreateStrategy.VALUE;
            return;
        }

        if(spec.isSerializable()) {
            val serializer = spec.getMetaModelContext().getObjectManager().getObjectSerializer();
            serializedObject = serializer.serialize(adapter);
            recreateStrategy = RecreateStrategy.SERIALIZABLE;
            return;
        }

        throw _Exceptions.illegalArgument("Don't know how to create an ObjectMemento for a type "
                + "with ObjectSpecification %s. "
                + "All other strategies failed. Type is neither "
                + "identifiable (isManagedBean() || isViewModel() || isEntity()), "
                + "nor is a 'parented' Collection, "
                + "nor has 'encodable' semantics, nor is (Serializable || Externalizable)", spec);

    }

    private Cardinality getCardinality() {
        return cardinality;
    }

    public String getTitleString() {
        return titleString;
    }

    public Bookmark asBookmark() {
        val bookmark = asStrictBookmark();
        return bookmark!=null
                ? bookmark
                : asPseudoBookmark();
    }

    /**
     * Returns a bookmark only if
     * {@link org.apache.isis.viewer.wicket.viewer.services.mementos.ObjectMementoWkt.RecreateStrategy#LOOKUP} and
     * {@link #getCardinality() sort} is {@link Cardinality#SCALAR scalar}.
     * Returns {@code null} otherwise.
     */
    private Bookmark asStrictBookmark() {
        ensureScalar();
        return bookmark;
    }

    /**
     * In a strict sense, bookmarks are only available for viewmodels, entities and managed beans,
     * not for values or enums. However, the {@link Bookmark} as an immutable value,
     * is also perfectly suitable to represent an enum value or any value type.
     * @apiNote this is an intermediate refactoring step,
     * possibly providing a way of getting rid of {@link ObjectMemento} entirely,
     * with {@link Bookmark} being the replacement
     */
    private Bookmark asPseudoBookmark() {
        return cardinality.asPseudoBookmark(this);
    }

    /**
     * Lazily looks up {@link ManagedObject} if required.
     *
     * <p>
     * For transient objects, be aware that calling this method more than once
     * will cause the underlying {@link ManagedObject} to be recreated,
     * overwriting any changes that may have been made. In general then it's
     * best to call once and then hold onto the value thereafter. Alternatively,
     * can call {@link #setAdapter(ManagedObject)} to keep this memento in sync.
     */
    ManagedObject reconstructObject(final MetaModelContext mmc) {

        val specificationLoader = mmc.getSpecificationLoader();
        val spec = specificationLoader.specForLogicalType(logicalType).orElse(null);
        if(spec==null) {
            // eg. ill-formed request
            return null;
        }

        // intercept when managed by IoCC
        if(spec.getBeanSort().isManagedBean()) {
            return spec.getMetaModelContext().lookupServiceAdapterById(getLogicalTypeName());
        }

        return cardinality.asAdapter(this, mmc);
    }

    @Override
    public int hashCode() {
        return cardinality.hashCode(this);
    }

    @Override
    public boolean equals(final Object obj) {
        return cardinality.equals(this, obj);
    }

    // -- FUNCTIONS

    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    private static final class Functions {

        private static Function<_ObjectMemento, ManagedObject> toManagedObject(
                final MetaModelContext mmc) {

            return memento->{
                if(memento == null) {
                    return ManagedObject.unspecified();
                }
                val objectAdapter = memento
                        .reconstructObject(mmc);
                if(objectAdapter == null) {
                    return ManagedObject.unspecified();
                }
                return objectAdapter;
            };
        }

    }

    private void ensureScalar() {
        getCardinality().ensure(Cardinality.SCALAR);
    }

}
