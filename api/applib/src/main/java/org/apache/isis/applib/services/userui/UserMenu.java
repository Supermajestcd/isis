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
package org.apache.isis.applib.services.userui;

import javax.inject.Inject;
import javax.inject.Named;

import org.apache.isis.applib.IsisModuleApplib;
import org.apache.isis.applib.annotation.Action;
import org.apache.isis.applib.annotation.ActionLayout;
import org.apache.isis.applib.annotation.DomainService;
import org.apache.isis.applib.annotation.DomainServiceLayout;
import org.apache.isis.applib.annotation.MemberSupport;
import org.apache.isis.applib.annotation.NatureOfService;
import org.apache.isis.applib.annotation.PriorityPrecedence;
import org.apache.isis.applib.annotation.SemanticsOf;
import org.apache.isis.applib.services.user.UserMemento;
import org.apache.isis.applib.services.user.UserService;

import lombok.RequiredArgsConstructor;

/**
 * Menu to return a representation (a {@link UserMemento}) of the currently logged-in user.
 *
 * @since 2.0 {@index}
 */
@DomainService(
        nature = NatureOfService.VIEW
)
@Named(UserMenu.LOGICAL_TYPE_NAME)
@DomainServiceLayout(
        menuBar = DomainServiceLayout.MenuBar.TERTIARY
)
@javax.annotation.Priority(PriorityPrecedence.EARLY)
@RequiredArgsConstructor(onConstructor_ = { @Inject })
public class UserMenu {

    static final String LOGICAL_TYPE_NAME = IsisModuleApplib.NAMESPACE + ".UserMenu";

    public static abstract class ActionDomainEvent<T> extends IsisModuleApplib.ActionDomainEvent<T> {}

    final UserService userService;


    @Action(
            domainEvent = me.ActionDomainEvent.class,
            semantics = SemanticsOf.SAFE
            )
    @ActionLayout(
            cssClassFa = "fa-user",
            describedAs = "Returns your user account details",
            sequence = "100"
        )
    public class me {

        public class ActionDomainEvent extends UserMenu.ActionDomainEvent<me> {}

        @MemberSupport public UserMemento act() { return userService.currentUser().orElse(null); }
        @MemberSupport public String disableAct() {
            return userService.currentUser().isPresent() ? null : "Current user not available";
        }

    }

}
