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
package /*${java-package}*/;

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

import org.springframework.context.annotation.Profile;

import org.apache.isis.applib.annotation.DomainObject;
import org.apache.isis.applib.annotation.Editing;
import org.apache.isis.applib.annotation.Optionality;
import org.apache.isis.applib.annotation.Property;
import org.apache.isis.applib.annotation.PropertyLayout;
import org.apache.isis.applib.annotation.Title;
import org.apache.isis.applib.annotation.Where;
import org.apache.isis.persistence.jpa.applib.integration.IsisEntityListener;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import /*${showcase-java-package}*/.persistence./*${showcase-name}*/Entity;

/*${generated-file-notice}*/
@Profile("demo-jpa")
//tag::class[]
@Entity
@Table(
      schema = "demo",
      name = "/*${showcase-name}*/Jpa"
)
@EntityListeners(IsisEntityListener.class)
@Named("demo./*${showcase-name}*/Entity")
@DomainObject
@NoArgsConstructor                                                             // <.>
public class /*${showcase-name}*/Jpa
        extends /*${showcase-name}*/Entity {

//end::class[]
    public /*${showcase-name}*/Jpa(final /*${showcase-type}*/ initialValue) {
        this.readOnlyProperty = initialValue;
        this.readWriteProperty = initialValue;
    }

//tag::class[]
    @Id
    @GeneratedValue
    private Long id;

    @Title(prepend = "/*${showcase-type}*/ JPA entity: ")
    @PropertyLayout(fieldSetId = "read-only-properties", sequence = "1")
    @Column(nullable = false) @Lob @Basic(fetch=FetchType.LAZY)                 // <.>
    @Getter @Setter
    private /*${showcase-type}*/ readOnlyProperty;

    @Property(editing = Editing.ENABLED)                                        // <.>
    @PropertyLayout(fieldSetId = "editable-properties", sequence = "1", hidden = Where.ALL_TABLES, multiLine = 5)
    @Column(nullable = false) @Lob @Basic(fetch=FetchType.LAZY)
    @Getter @Setter
    private /*${showcase-type}*/ readWriteProperty;

    @Property(optionality = Optionality.OPTIONAL)                               // <.>
    @PropertyLayout(fieldSetId = "optional-properties", sequence = "1")
    @Column(nullable = true) @Lob @Basic(fetch=FetchType.LAZY)                  // <.>
    @Getter @Setter
    private /*${showcase-type}*/ readOnlyOptionalProperty;

    @Property(editing = Editing.ENABLED, optionality = Optionality.OPTIONAL)
    @PropertyLayout(fieldSetId = "optional-properties", sequence = "2", hidden = Where.ALL_TABLES, multiLine = 5)
    @Column(nullable = true) @Lob @Basic(fetch=FetchType.LAZY)
    @Getter @Setter
    private /*${showcase-type}*/ readWriteOptionalProperty;

}
//end::class[]
