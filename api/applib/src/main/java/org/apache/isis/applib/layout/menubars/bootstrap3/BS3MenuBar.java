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
package org.apache.isis.applib.layout.menubars.bootstrap3;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import org.apache.isis.applib.annotations.DomainServiceLayout;
import org.apache.isis.applib.layout.menubars.MenuBar;


/**
 * Describes the collection of domain services into menubars, broadly corresponding to the aggregation of information of {@link org.apache.isis.applib.annotations.DomainServiceLayout} that have the same value of {@link DomainServiceLayout#named()}.
 *
 * @since 1.x {@index}
 */
@XmlType(
        name = "menuBar"
        , propOrder = {
                "menus"
        }
        )
public class BS3MenuBar implements MenuBar, Serializable {

    private static final long serialVersionUID = 1L;

    public BS3MenuBar() {
    }


    private List<BS3Menu> menus = new ArrayList<>();

    // no wrapper
    @XmlElement(name = "menu", required = true)
    public List<BS3Menu> getMenus() {
        return menus;
    }

    public void setMenus(List<BS3Menu> menus) {
        this.menus = menus;
    }


}
