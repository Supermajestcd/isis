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
package org.apache.isis.core.runtime.context;

import java.util.function.Supplier;

import org.apache.isis.applib.services.iactn.InteractionProvider;
import org.apache.isis.applib.services.iactnlayer.InteractionLayerTracker;
import org.apache.isis.applib.services.iactnlayer.InteractionService;
import org.apache.isis.applib.services.xactn.TransactionService;
import org.apache.isis.core.metamodel.context.MetaModelContext;
import org.apache.isis.core.metamodel.object.ManagedObject;
import org.apache.isis.core.metamodel.objectmanager.ObjectManager;
import org.apache.isis.core.security.authentication.manager.AuthenticationManager;

import lombok.Getter;
import lombok.val;

/**
 *
 * @since 2.0
 *
 */
public abstract class RuntimeContextBase {

    // -- FINAL FIELDS

    @Getter protected final MetaModelContext metaModelContext;

    @Getter protected final InteractionLayerTracker interactionLayerTracker;
    @Getter protected final InteractionService interactionService;
    @Getter protected final AuthenticationManager authenticationManager;
    @Getter protected final TransactionService transactionService;
    @Getter protected final Supplier<ManagedObject> homePageSupplier;
    @Getter protected final ObjectManager objectManager;

    // -- SINGLE ARG CONSTRUCTOR

    protected RuntimeContextBase(MetaModelContext mmc) {
        this.metaModelContext= mmc;
        val serviceRegistry = mmc.getServiceRegistry();
        this.objectManager = mmc.getObjectManager();
        this.transactionService = mmc.getTransactionService();
        this.homePageSupplier = mmc::getHomePageAdapter;
        this.interactionService = serviceRegistry.lookupServiceElseFail(InteractionService.class);
        this.authenticationManager = serviceRegistry.lookupServiceElseFail(AuthenticationManager.class);
        this.interactionLayerTracker = serviceRegistry.lookupServiceElseFail(InteractionLayerTracker.class);
    }

    // -- AUTH

    public InteractionProvider getInteractionProvider() {
        return interactionLayerTracker;
    }

}
