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
package org.apache.isis.core.metamodel.facets.object.ident.cssclass;

import java.lang.reflect.Method;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;

import org.apache.isis.core.metamodel.facetapi.FacetHolder;
import org.apache.isis.core.metamodel.facets.object.cssclass.method.CssClassFacetViaCssClassMethod;
import org.apache.isis.core.metamodel.object.ManagedObject;

public class CssClassFacetMethodWithProblemTest {

    private final Mockery mockery = new JUnit4Mockery();

    private CssClassFacetViaCssClassMethod facet;
    private FacetHolder mockFacetHolder;

    private ManagedObject mockOwningAdapter;

    private DomainObjectWithProblemInCssClassMethod pojo;

    public static class DomainObjectWithProblemInCssClassMethod {
        public String cssClass() {
            throw new NullPointerException("for testing purposes");
        }
    }

    @Before
    public void setUp() throws Exception {

        pojo = new DomainObjectWithProblemInCssClassMethod();
        mockFacetHolder = mockery.mock(FacetHolder.class);
        mockOwningAdapter = mockery.mock(ManagedObject.class);
        final Method iconNameMethod = DomainObjectWithProblemInCssClassMethod.class.getMethod("cssClass");
        facet = (CssClassFacetViaCssClassMethod) CssClassFacetViaCssClassMethod
                .create(iconNameMethod, mockFacetHolder)
                .orElse(null);

        mockery.checking(new Expectations() {
            {
                allowing(mockOwningAdapter).getPojo();
                will(returnValue(pojo));
            }
        });
    }

    @After
    public void tearDown() {
        facet = null;
    }

    @Test
    public void testCssClassThrowsException() {
        //assertThrows(Exception.class, ()->facet.cssClass(mockOwningAdapter));
        final String iconName = facet.cssClass(mockOwningAdapter);
        assertThat(iconName, is(nullValue()));
    }

}
