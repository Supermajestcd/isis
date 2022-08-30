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
package org.apache.isis.core.metamodel.facets.object.ident.title;

import org.jmock.Expectations;
import org.jmock.Sequence;
import org.jmock.auto.Mock;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import org.apache.isis.applib.annotation.Title;
import org.apache.isis.core.internaltestsupport.jmocking.JUnitRuleMockery2;
import org.apache.isis.core.internaltestsupport.jmocking.JUnitRuleMockery2.Mode;
import org.apache.isis.core.metamodel._testing.MetaModelContext_forTesting;
import org.apache.isis.core.metamodel.context.MetaModelContext;
import org.apache.isis.core.metamodel.facetapi.FacetHolder;
import org.apache.isis.core.metamodel.facets.object.title.annotation.TitleFacetViaTitleAnnotation;
import org.apache.isis.core.metamodel.object.ManagedObject;
import org.apache.isis.core.metamodel.objectmanager.ObjectManager;

public class TitleFacetViaTitleAnnotationTest {

    @Rule
    public JUnitRuleMockery2 context = JUnitRuleMockery2.createFor(Mode.INTERFACES_ONLY);

    @Mock private FacetHolder mockFacetHolder;
    @Mock private ManagedObject mockManagedObject;
    @Mock private ObjectManager mockObjectManager;

    protected MetaModelContext metaModelContext;

    protected static class DomainObjectWithProblemInItsAnnotatedTitleMethod {

        @Title
        public String brokenTitle() {
            throw new NullPointerException();
        }

    }

    protected static class NormalDomainObject {

        @Title(sequence = "1.0")
        public String titleElement1() {
            return "Normal";
        }

        @Title(sequence = "2.0")
        public String titleElement2() {
            return "Domain";
        }

        @Title(sequence = "3.0")
        public String titleElement3() {
            return "Object";
        }

    }

    @Before
    public void setUp() {
        metaModelContext = MetaModelContext_forTesting.builder()
//                .objectAdapterProvider(mockAdapterManager)
                .objectManager(mockObjectManager)
                .build();
    }

    @Test
    public void testTitle() throws Exception {

        final TitleFacetViaTitleAnnotation facet =
                (TitleFacetViaTitleAnnotation) TitleFacetViaTitleAnnotation
                .create(NormalDomainObject.class, mockFacetHolder)
                .orElse(null);

        final NormalDomainObject normalPojo = new NormalDomainObject();
        final Sequence sequence = context.sequence("in-title-element-order");
        context.checking(new Expectations() {
            {

                allowing(mockFacetHolder).getMetaModelContext();
                will(returnValue(metaModelContext));

                allowing(mockManagedObject).getPojo();
                will(returnValue(normalPojo));

                allowing(mockObjectManager).adapt("Normal");
                inSequence(sequence);

                allowing(mockObjectManager).adapt("Domain");
                inSequence(sequence);

                allowing(mockObjectManager).adapt("Object");
                inSequence(sequence);
            }
        });

        final String title = facet.title(mockManagedObject);
        assertThat(title, is("Normal Domain Object"));
    }

    @Test
    public void titleThrowsException() {

        final TitleFacetViaTitleAnnotation facet =
                (TitleFacetViaTitleAnnotation) TitleFacetViaTitleAnnotation
                .create(DomainObjectWithProblemInItsAnnotatedTitleMethod.class, mockFacetHolder)
                .orElse(null);

        final DomainObjectWithProblemInItsAnnotatedTitleMethod screwedPojo =
                new DomainObjectWithProblemInItsAnnotatedTitleMethod();
        context.checking(new Expectations() {
            {

                allowing(mockFacetHolder).getMetaModelContext();
                will(returnValue(metaModelContext));

                allowing(mockManagedObject).getPojo();
                will(returnValue(screwedPojo));
            }
        });

        final String title = facet.title(mockManagedObject);
        assertThat(title, is("Failed Title"));
    }

}
