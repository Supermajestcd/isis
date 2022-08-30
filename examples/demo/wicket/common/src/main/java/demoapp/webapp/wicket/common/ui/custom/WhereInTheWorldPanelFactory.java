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
package demoapp.webapp.wicket.common.ui.custom;

import javax.inject.Inject;

import org.apache.wicket.Component;
import org.apache.wicket.model.IModel;

import org.apache.isis.applib.annotation.PriorityPrecedence;
import org.apache.isis.core.metamodel.object.ManagedObject;
import org.apache.isis.viewer.commons.model.components.ComponentType;
import org.apache.isis.viewer.wicket.model.models.EntityModel;
import org.apache.isis.viewer.wicket.ui.components.entity.EntityComponentFactoryAbstract;

import demoapp.dom.featured.customui.geocoding.GeoapifyClient;
import demoapp.dom.featured.customui.vm.WhereInTheWorldVm;

//tag::class[]
@org.springframework.stereotype.Component
@javax.annotation.Priority(PriorityPrecedence.EARLY)                                             // <.>
public class WhereInTheWorldPanelFactory extends EntityComponentFactoryAbstract {

    private static final long serialVersionUID = 1L;

    public WhereInTheWorldPanelFactory() {
        super(
            ComponentType.ENTITY                                          // <.>
            , WhereInTheWorldPanel.class
        );
    }

    @Override
    protected ApplicationAdvice doAppliesTo(EntityModel entityModel) {    // <.>
        final ManagedObject managedObject = entityModel.getObject();      // <.>
        final Object domainObject = managedObject.getPojo();              // <.>
        return ApplicationAdvice.appliesIf(
                domainObject instanceof WhereInTheWorldVm);               // <.>
    }

    @Override
    public Component createComponent(final String id, final IModel<?> model) {
        EntityModel entityModel = (EntityModel) model;                    // <.>
        return new WhereInTheWorldPanel(id, entityModel, geoapifyClient); // <.>
    }

    @Inject
    private GeoapifyClient geoapifyClient;                                // <.>

}
//end::class[]
