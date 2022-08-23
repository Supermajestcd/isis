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
package org.apache.isis.viewer.commons.model.header;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import org.apache.isis.applib.annotation.DomainServiceLayout.MenuBar;
import org.apache.isis.viewer.commons.applib.services.userprof.UserProfileUiModelProvider;
import org.apache.isis.viewer.commons.model.branding.BrandingUiModelProvider;
import org.apache.isis.viewer.commons.model.menu.MenuUiModelProvider;

@Service
public class HeaderUiModelProvider {

    @Inject private BrandingUiModelProvider brandingUiModelProvider;
    @Inject private UserProfileUiModelProvider userProfileUiModelProvider;
    @Inject private MenuUiModelProvider menuUiModelProvider;

    public HeaderUiModel getHeader() {
        return HeaderUiModel.of(
                brandingUiModelProvider.getHeaderBranding(),
                userProfileUiModelProvider.userProfile(),
                menuUiModelProvider.getMenu(MenuBar.PRIMARY),
                menuUiModelProvider.getMenu(MenuBar.SECONDARY),
                menuUiModelProvider.getMenu(MenuBar.TERTIARY));
    }

}
