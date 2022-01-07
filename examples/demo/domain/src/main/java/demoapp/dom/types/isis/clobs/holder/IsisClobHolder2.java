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
package demoapp.dom.types.isis.clobs.holder;

import org.apache.isis.applib.annotations.LabelPosition;
import org.apache.isis.applib.annotations.Property;
import org.apache.isis.applib.annotations.PropertyLayout;
import org.apache.isis.applib.annotations.Where;
import org.apache.isis.applib.value.Clob;

//tag::class[]
public interface IsisClobHolder2 extends IsisClobHolder {

    @Property                                               // <.>
    @PropertyLayout(
        labelPosition = LabelPosition.LEFT,                 // <.>
        describedAs =
            "@PropertyLayout(labelPosition=LEFT)",
        hidden = Where.ALL_TABLES,
        fieldSetId = "label-positions", sequence = "1")  // <.>
    default Clob getReadOnlyPropertyDerivedLabelPositionLeft() {
        return getReadOnlyProperty();
    }

    @Property
    @PropertyLayout(
        labelPosition = LabelPosition.TOP,                  // <.>
        describedAs =
            "@PropertyLayout(labelPosition=TOP)",
        hidden = Where.ALL_TABLES,
        fieldSetId = "label-positions", sequence = "2")
    default Clob getReadOnlyPropertyDerivedLabelPositionTop() {
        return getReadOnlyProperty();
    }

    @Property
    @PropertyLayout(
        labelPosition = LabelPosition.RIGHT,                // <.>
        describedAs =
            "@PropertyLayout(labelPosition=RIGHT)",
        hidden = Where.ALL_TABLES,
        fieldSetId = "label-positions", sequence = "3")
    default Clob getReadOnlyPropertyDerivedLabelPositionRight() {
        return getReadOnlyProperty();
    }

    @Property
    @PropertyLayout(
        labelPosition = LabelPosition.NONE,                 // <.>
        describedAs =
            "@PropertyLayout(labelPosition=NONE)",
        hidden = Where.ALL_TABLES,
        fieldSetId = "label-positions", sequence = "4")
    default Clob getReadOnlyPropertyDerivedLabelPositionNone() {
        return getReadOnlyProperty();
    }

}
//end::class[]
