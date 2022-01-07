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
package demoapp.dom.types.primitive.doubles.jpa;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.context.annotation.Profile;

import org.apache.isis.applib.annotations.DomainObject;
import org.apache.isis.applib.annotations.Editing;
import org.apache.isis.applib.annotations.Property;
import org.apache.isis.applib.annotations.PropertyLayout;
import org.apache.isis.applib.annotations.Title;
import org.apache.isis.persistence.jpa.applib.integration.IsisEntityListener;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import demoapp.dom.types.primitive.doubles.persistence.PrimitiveDoubleEntity;

@Profile("demo-jpa")
//tag::class[]
@Entity
@Table(
      schema = "demo",
      name = "PrimitiveDoubleJpa"
)
@EntityListeners(IsisEntityListener.class)
@DomainObject(
      logicalTypeName = "demo.PrimitiveDoubleEntity"
)
@NoArgsConstructor
public class PrimitiveDoubleJpa
        extends PrimitiveDoubleEntity {

//end::class[]
    public PrimitiveDoubleJpa(final double initialValue) {
        this.readOnlyProperty = initialValue;
        this.readWriteProperty = initialValue;
    }

//tag::class[]
    @Id
    @GeneratedValue
    private Long id;

    @Title(prepend = "double (primitive) JPA entity: ")
    @PropertyLayout(fieldSetId = "read-only-properties", sequence = "1")
    @Getter @Setter
    private double readOnlyProperty;                                // <.>

    @Property(editing = Editing.ENABLED)
    @PropertyLayout(fieldSetId = "editable-properties", sequence = "1")
    @Getter @Setter
    private double readWriteProperty;

}
//end::class[]
