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
package demoapp.dom.types.javalang.shorts;

import java.util.List;

import javax.inject.Inject;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;

import org.apache.isis.applib.annotations.Action;
import org.apache.isis.applib.annotations.ActionLayout;
import org.apache.isis.applib.annotations.Collection;
import org.apache.isis.applib.annotations.DomainObject;
import org.apache.isis.applib.annotations.Editing;
import org.apache.isis.applib.annotations.MemberSupport;
import org.apache.isis.applib.annotations.Nature;
import org.apache.isis.applib.annotations.ObjectSupport;
import org.apache.isis.applib.annotations.PromptStyle;
import org.apache.isis.applib.annotations.SemanticsOf;

import demoapp.dom._infra.asciidocdesc.HasAsciiDocDescription;
import demoapp.dom._infra.values.ValueHolderRepository;
import demoapp.dom.types.javalang.shorts.persistence.WrapperShortEntity;
import demoapp.dom.types.javalang.shorts.vm.WrapperShortVm;

@XmlRootElement(name = "Demo")
@XmlType
@XmlAccessorType(XmlAccessType.FIELD)
@DomainObject(nature=Nature.VIEW_MODEL, logicalTypeName = "demo.WrapperShorts", editing=Editing.ENABLED)
//@Log4j2
public class WrapperShorts implements HasAsciiDocDescription {

    @ObjectSupport public String title() {
        return "Short (wrapper) data type";
    }

    @Action(semantics = SemanticsOf.SAFE)
    @ActionLayout(promptStyle = PromptStyle.DIALOG_MODAL)
    public WrapperShortVm openViewModel(final Short initialValue) {
        return new WrapperShortVm(initialValue);
    }
    @MemberSupport public Short default0OpenViewModel() {
        return (short)12345;
    }

    @Collection
    public List<? extends WrapperShortEntity> getEntities() {
        return entities.all();
    }

    @Inject
    @XmlTransient
    ValueHolderRepository<Short, ? extends WrapperShortEntity> entities;


}
