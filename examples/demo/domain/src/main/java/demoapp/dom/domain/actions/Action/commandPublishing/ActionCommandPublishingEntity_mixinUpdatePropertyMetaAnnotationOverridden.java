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
package demoapp.dom.domain.actions.Action.commandPublishing;

import org.apache.isis.applib.annotations.Action;
import org.apache.isis.applib.annotations.ActionLayout;
import org.apache.isis.applib.annotations.MemberSupport;
import org.apache.isis.applib.annotations.Publishing;
import org.apache.isis.applib.annotations.SemanticsOf;

import lombok.RequiredArgsConstructor;

//tag::class[]
@ActionCommandPublishingDisabledMetaAnnotation        // <.>
@Action(
    commandPublishing = Publishing.ENABLED            // <.>
    , semantics = SemanticsOf.IDEMPOTENT
)
@ActionLayout(
    named = "Mixin Update Property"
    , describedAs =
        "@ActionCommandDisabledMetaAnnotation " +
        "@Action(command = ENABLED)"
    , associateWith = "propertyMetaAnnotatedOverridden"
    , sequence = "2"
)
@RequiredArgsConstructor
public class ActionCommandPublishingEntity_mixinUpdatePropertyMetaAnnotationOverridden {
    // ...
//end::class[]

    private final ActionCommandPublishingEntity actionCommandEntity;

    @MemberSupport public ActionCommandPublishingEntity act(final String value) {
        actionCommandEntity.setPropertyMetaAnnotatedOverridden(value);
        return actionCommandEntity;
    }
    @MemberSupport public String default0Act() {
        return actionCommandEntity.getPropertyMetaAnnotatedOverridden();
    }
//tag::class[]
}
//end::class[]
