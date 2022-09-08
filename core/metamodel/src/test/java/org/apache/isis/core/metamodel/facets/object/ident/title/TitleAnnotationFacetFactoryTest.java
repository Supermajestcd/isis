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

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.apache.isis.applib.annotation.DomainObject;
import org.apache.isis.applib.annotation.Nature;
import org.apache.isis.applib.annotation.Title;
import org.apache.isis.core.metamodel.facetapi.Facet;
import org.apache.isis.core.metamodel.facets.AbstractFacetFactoryJUnit4TestCase;
import org.apache.isis.core.metamodel.facets.Evaluators;
import org.apache.isis.core.metamodel.facets.FacetFactory.ProcessClassContext;
import org.apache.isis.core.metamodel.facets.object.title.TitleFacet;
import org.apache.isis.core.metamodel.facets.object.title.annotation.TitleAnnotationFacetFactory;
import org.apache.isis.core.metamodel.facets.object.title.annotation.TitleFacetViaTitleAnnotation;
import org.apache.isis.core.metamodel.object.ManagedObject;

import lombok.val;

public class TitleAnnotationFacetFactoryTest
extends AbstractFacetFactoryJUnit4TestCase {

    private TitleAnnotationFacetFactory facetFactory;

    @Before
    public void setUp() throws Exception {
        facetFactory = new TitleAnnotationFacetFactory(metaModelContext);
    }

    @After
    @Override
    public void tearDown() throws Exception {
        facetFactory = null;
        super.tearDown();
    }

    public static class Customer {

        @Title
        public String someTitle() {
            return "Some Title";
        }
    }

    @Test
    public void testTitleAnnotatedMethodPickedUpOnClassRemoved() throws Exception {
        facetFactory.process(ProcessClassContext
                .forTesting(Customer.class, mockMethodRemover, facetedMethod));

        final Facet facet = facetedMethod.getFacet(TitleFacet.class);
        Assert.assertNotNull(facet);
        Assert.assertTrue(facet instanceof TitleFacetViaTitleAnnotation);
        final TitleFacetViaTitleAnnotation titleFacetViaTitleAnnotation = (TitleFacetViaTitleAnnotation) facet;

        final List<Method> titleMethods = Arrays.asList(Customer.class.getMethod("someTitle"));
        for (int i = 0; i < titleMethods.size(); i++) {
            final Evaluators.MethodEvaluator titleEvaluator =
                    (Evaluators.MethodEvaluator) titleFacetViaTitleAnnotation.getComponents().getElseFail(i)
                    .getTitleEvaluator();

            Assert.assertEquals(titleMethods.get(i),
                    titleEvaluator.getMethod());
        }
    }

    public static class Customer2 {

        @Title(sequence = "1", append = ".")
        public String titleElement1() {
            return "titleElement1";
        }

        @Title(sequence = "2", prepend = ",")
        public String titleElement2() {
            return "titleElement2";
        }

        @Title(sequence = "1.5")
        public String titleElement3() {
            return "titleElement3";
        }

    }

    @Test
    public void testTitleAnnotatedMethodsPickedUpOnClass() throws Exception {

        facetFactory.process(ProcessClassContext
                .forTesting(Customer2.class, mockMethodRemover, facetedMethod));

        final Facet facet = facetedMethod.getFacet(TitleFacet.class);
        Assert.assertNotNull(facet);
        Assert.assertTrue(facet instanceof TitleFacetViaTitleAnnotation);
        final TitleFacetViaTitleAnnotation titleFacetViaTitleAnnotation = (TitleFacetViaTitleAnnotation) facet;

        final List<Method> titleMethods = Arrays.asList(Customer2.class.getMethod("titleElement1"), Customer2.class.getMethod("titleElement3"), Customer2.class.getMethod("titleElement2"));

        //final List<TitleComponent> components = titleFacetViaTitleAnnotation.getComponents();
        for (int i = 0; i < titleMethods.size(); i++) {
            final Evaluators.MethodEvaluator titleEvaluator =
                    (Evaluators.MethodEvaluator) titleFacetViaTitleAnnotation.getComponents().getElseFail(i)
                    .getTitleEvaluator();

            Assert.assertEquals(titleMethods.get(i),
                    titleEvaluator.getMethod());
        }

        final Customer2 customer = new Customer2();
        val objectAdapter = ManagedObject.adaptScalar(specificationLoader, customer);

        final String title = titleFacetViaTitleAnnotation.title(objectAdapter);
        assertThat(title, is("titleElement1. titleElement3,titleElement2"));
    }

    public static class Customer3 {
    }

    @Test
    public void testNoExplicitTitleAnnotations() {

        facetFactory.process(ProcessClassContext
                .forTesting(Customer3.class, mockMethodRemover, facetedMethod));

        Assert.assertNull(facetedMethod.getFacet(TitleFacet.class));
    }

    @DomainObject(nature = Nature.VIEW_MODEL)
    public static class Customer4 {

        @Title(sequence = "1")
        public String titleElement1() {
            return "titleElement1";
        }

        @Title(sequence = "2")
        public String titleElement2() {
            return null;
        }

        @Title(sequence = "3")
        public String titleElement3() {
            return "titleElement3";
        }

        @Title(sequence = "4", prepend = "ignored-since-null", append = "ignored-since-null")
        public Object titleElement4() {
            return null;
        }

        @Title(sequence = "4.4", prepend = "ignored-since-empty-string", append = "ignored-since-empty-string")
        public Object titleElement4a() {
            return "";
        }

        @Title(sequence = "5")
        public String titleElement5() {
            return "titleElement5";
        }

        @Title(sequence = "6")
        public Integer titleElement6() {
            return 3;
        }

        @Title(sequence = "7")
        public String titleElement7() {
            return "  this needs to be trimmed      ";
        }

    }

    @Test
    public void titleAnnotatedMethodsSomeOfWhichReturnNulls() throws Exception {

        { // check prerequisites
            val wThree = ManagedObject.adaptScalar(specificationLoader, Integer.valueOf(3));
            assertEquals("3", wThree.getTitle());
            val pThree = ManagedObject.adaptScalar(specificationLoader, 3);
            assertEquals("3", pThree.getTitle());
        }

        facetFactory.process(ProcessClassContext
                .forTesting(Customer4.class, mockMethodRemover, facetedMethod));

        final Customer4 customer = new Customer4();
        val objectAdapter = ManagedObject.adaptScalar(specificationLoader, customer);

        assertThat(objectAdapter.getTitle(),
                is("titleElement1 titleElement3 titleElement5 3 this needs to be trimmed"));
    }


    public static class Customer5 {

        @Title(sequence = "1")
        public String titleProperty() {
            return "titleElement1";
        }

        public String otherProperty() {
            return null;
        }
    }


}
