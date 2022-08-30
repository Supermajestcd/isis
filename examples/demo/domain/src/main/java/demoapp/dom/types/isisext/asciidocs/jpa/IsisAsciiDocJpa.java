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
package demoapp.dom.types.isisext.asciidocs.jpa;

import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.context.annotation.Profile;

import org.apache.isis.applib.annotation.DomainObject;
import org.apache.isis.applib.annotation.Editing;
import org.apache.isis.applib.annotation.ObjectSupport;
import org.apache.isis.applib.annotation.Optionality;
import org.apache.isis.applib.annotation.Property;
import org.apache.isis.applib.annotation.PropertyLayout;
import org.apache.isis.applib.annotation.Where;
import org.apache.isis.applib.services.bookmark.BookmarkService;
import org.apache.isis.persistence.jpa.applib.integration.IsisEntityListener;
import org.apache.isis.valuetypes.asciidoc.applib.value.AsciiDoc;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import demoapp.dom.types.isisext.asciidocs.persistence.IsisAsciiDocEntity;

@Profile("demo-jpa")
//tag::class[]
@Entity
@Table(
      schema = "demo",
      name = "IsisAsciiDocJpa"
)
@EntityListeners(IsisEntityListener.class)
@Named("demo.IsisAsciiDocEntity")
@DomainObject
@NoArgsConstructor                                                  // <.>
public class IsisAsciiDocJpa
        extends IsisAsciiDocEntity {

//end::class[]
    public IsisAsciiDocJpa(final AsciiDoc initialValue) {
        this.readOnlyProperty = initialValue;
        this.readWriteProperty = initialValue;
    }

//tag::class[]
    @Id
    @GeneratedValue
    private Long id;

    @ObjectSupport public String title() {
        return "AsciiDoc JPA entity: " +
            bookmarkService.bookmarkForElseFail(this).getIdentifier();
    }

    @PropertyLayout(fieldSetId = "read-only-properties", sequence = "1")
    @Column(nullable = false) @Lob @Basic(fetch=FetchType.LAZY)     // <.>
    @Getter @Setter
    private AsciiDoc readOnlyProperty;

    @Property(editing = Editing.ENABLED)                            // <.>
    @PropertyLayout(hidden = Where.ALL_TABLES, fieldSetId = "editable-properties", sequence = "1", multiLine = 5)
    @Column(nullable = false) @Lob @Basic(fetch=FetchType.LAZY)
    @Getter @Setter
    private AsciiDoc readWriteProperty;

    @Property(optionality = Optionality.OPTIONAL)                   // <.>
    @PropertyLayout(hidden = Where.ALL_TABLES, fieldSetId = "optional-properties", sequence = "1")
    @Column(nullable = true) @Lob @Basic(fetch=FetchType.LAZY)      // <.>
    @Getter @Setter
    private AsciiDoc readOnlyOptionalProperty;

    @Property(editing = Editing.ENABLED, optionality = Optionality.OPTIONAL)
    @PropertyLayout(hidden = Where.ALL_TABLES, fieldSetId = "optional-properties", sequence = "2", multiLine = 5)
    @Column(nullable = true) @Lob @Basic(fetch=FetchType.LAZY)
    @Getter @Setter
    private AsciiDoc readWriteOptionalProperty;

    @Inject @Transient
    private transient BookmarkService bookmarkService;
}
//end::class[]
