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
package org.apache.isis.core.metamodel.facets.value.semantics;

import java.time.format.FormatStyle;
import java.util.Optional;

import org.apache.isis.applib.annotations.ValueSemantics;
import org.apache.isis.core.metamodel.facetapi.FacetHolder;
import org.apache.isis.core.metamodel.facets.objectvalue.temporalformat.DateFormatStyleFacet;
import org.apache.isis.core.metamodel.facets.objectvalue.temporalformat.DateFormatStyleFacetAbstract;

public class DateFormatStyleFacetFromValueSemanticsAnnotation
extends DateFormatStyleFacetAbstract {

    public static Optional<DateFormatStyleFacet> create(
            final Optional<ValueSemantics> valueSemanticsIfAny,
            final FacetHolder holder) {

        return valueSemanticsIfAny
        .map(ValueSemantics::dateFormatStyle)
        .map(dateFormatStyle->
            new DateFormatStyleFacetFromValueSemanticsAnnotation(dateFormatStyle, holder));
   }

   private DateFormatStyleFacetFromValueSemanticsAnnotation(
           final FormatStyle dateFormatStyle, final FacetHolder holder) {
       super(dateFormatStyle, holder);
   }

}
