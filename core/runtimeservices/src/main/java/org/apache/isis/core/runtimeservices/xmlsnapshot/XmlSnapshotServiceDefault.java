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
package org.apache.isis.core.runtimeservices.xmlsnapshot;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import javax.annotation.Priority;
import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.w3c.dom.Element;

import org.apache.isis.applib.annotation.PriorityPrecedence;
import org.apache.isis.applib.services.xml.XmlService;
import org.apache.isis.applib.services.xmlsnapshot.XmlSnapshotService;
import org.apache.isis.core.metamodel.object.ManagedObject;
import org.apache.isis.core.metamodel.specloader.SpecificationLoader;
import org.apache.isis.core.metamodel.util.snapshot.XmlSnapshot;
import org.apache.isis.core.runtimeservices.IsisModuleCoreRuntimeServices;

@Service
@Named(IsisModuleCoreRuntimeServices.NAMESPACE + ".XmlSnapshotServiceDefault")
@Priority(PriorityPrecedence.MIDPOINT)
@Qualifier("Default")
public class XmlSnapshotServiceDefault implements XmlSnapshotService {

    private final XmlService xmlService;
    private final SpecificationLoader specificationLoader;

    @Inject
    public XmlSnapshotServiceDefault(
            final XmlService xmlService,
            final SpecificationLoader specificationLoader) {
        this.xmlService = xmlService;
        this.specificationLoader = specificationLoader;
    }

    static class XmlSnapshotServiceDefaultBuilder implements XmlSnapshotService.Snapshot.Builder{

        private final XmlSnapshotBuilder builder;
        public XmlSnapshotServiceDefaultBuilder(final SpecificationLoader specificationLoader, final Object domainObject) {
            builder = new XmlSnapshotBuilder(specificationLoader, domainObject);
        }

        @Override
        public void includePath(final String path) {
            builder.includePath(path);
        }

        @Override
        public void includePathAndAnnotation(final String path, final String annotation) {
            builder.includePathAndAnnotation(path, annotation);
        }

        @Override
        public XmlSnapshotService.Snapshot build() {
            XmlSnapshot xmlSnapshot = builder.build();
            return xmlSnapshot;
        }
    }

    /**
     * Creates a simple snapshot of the domain object.
     */
    @Override
    public XmlSnapshotService.Snapshot snapshotFor(final Object domainObject) {
        final ManagedObject adapter = ManagedObject.wrapScalar(specificationLoader, domainObject);
        return new XmlSnapshot(adapter);
    }

    /**
     * Creates a builder that allows a custom snapshot - traversing additional associated
     * properties or collections
     * (using {@link org.apache.isis.applib.services.xmlsnapshot.XmlSnapshotService.Snapshot.Builder#includePath(String)}
     * and
     * {@link org.apache.isis.applib.services.xmlsnapshot.XmlSnapshotService.Snapshot.Builder#includePathAndAnnotation(String, String)})
     * - to be created.
     */
    @Override
    public Snapshot.Builder builderFor(final Object domainObject) {
        return new XmlSnapshotServiceDefaultBuilder(specificationLoader, domainObject);
    }


    @Override
    @SuppressWarnings("unchecked")
    public <T> T getChildElementValue(final Element el, final String tagname, final Class<T> expectedCls) {
        final Element chldEl = xmlService.getChildElement(el, tagname);
        final String dataType = chldEl.getAttribute("isis:datatype");
        if(dataType == null) {
            throw new IllegalArgumentException(String.format("unable to locate %s/@datatype attribute", tagname));
        }
        if("isis:String".equals(dataType)) {
            return (T)xmlService.getChildTextValue(chldEl);
        }
        if("isis:LocalDate".equals(dataType)) {
            final String str = xmlService.getChildTextValue(chldEl);
            final DateTimeFormatter parser = DateTimeFormatter
                    .ofPattern("dd-MMM-yyyy", Locale.ENGLISH);
            return (T)parser.parse(str, LocalDate::from);
        }
        if("isis:Byte".equals(dataType)) {
            final String str = xmlService.getChildTextValue(chldEl);
            return (T)Byte.valueOf(str);
        }
        if("isis:Short".equals(dataType)) {
            final String str = xmlService.getChildTextValue(chldEl);
            return (T)Short.valueOf(str);
        }
        if("isis:Integer".equals(dataType)) {
            final String str = xmlService.getChildTextValue(chldEl);
            return (T)Integer.valueOf(str);
        }
        if("isis:Long".equals(dataType)) {
            final String str = xmlService.getChildTextValue(chldEl);
            return (T)Long.valueOf(str);
        }
        if("isis:Float".equals(dataType)) {
            final String str = xmlService.getChildTextValue(chldEl);
            return (T)Float.valueOf(str);
        }
        if("isis:Double".equals(dataType)) {
            final String str = xmlService.getChildTextValue(chldEl);
            return (T)Double.valueOf(str);
        }
        if("isis:BigDecimal".equals(dataType)) {
            final String str = xmlService.getChildTextValue(chldEl);
            return (T) new BigDecimal(str);
        }
        if("isis:BigInteger".equals(dataType)) {
            final String str = xmlService.getChildTextValue(chldEl);
            return (T) new BigInteger(str);
        }
        if("isis:Boolean".equals(dataType)) {
            final String str = xmlService.getChildTextValue(chldEl);
            return (T) Boolean.valueOf(str);
        }
        throw new IllegalArgumentException(
                String.format("Datatype of '%s' for element '%s' not recognized", dataType, tagname));
    }


}
