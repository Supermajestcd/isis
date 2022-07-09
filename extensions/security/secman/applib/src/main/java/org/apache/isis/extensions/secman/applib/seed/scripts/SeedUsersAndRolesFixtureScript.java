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
package org.apache.isis.extensions.secman.applib.seed.scripts;

import javax.inject.Inject;

import org.apache.isis.core.config.IsisConfiguration;
import org.apache.isis.extensions.secman.applib.role.seed.IsisApplibFeatureRoleAndPermissions;
import org.apache.isis.extensions.secman.applib.role.seed.IsisApplibConfigurationRoleAndPermissions;
import org.apache.isis.extensions.secman.applib.role.seed.IsisExtCommandReplayPrimaryRoleAndPermissions;
import org.apache.isis.extensions.secman.applib.role.seed.IsisExtCommandReplaySecondaryRoleAndPermissions;
import org.apache.isis.extensions.secman.applib.role.seed.IsisExtH2ConsoleRoleAndPermissions;
import org.apache.isis.extensions.secman.applib.role.seed.IsisExtSecmanAdminRoleAndPermissions;
import org.apache.isis.extensions.secman.applib.role.seed.IsisExtSecmanRegularUserRoleAndPermissions;
import org.apache.isis.extensions.secman.applib.role.seed.IsisPersistenceJdoMetaModelRoleAndPermissions;
import org.apache.isis.extensions.secman.applib.role.seed.IsisApplibSudoImpersonateRoleAndPermissions;
import org.apache.isis.extensions.secman.applib.role.seed.IsisViewerRestfulObjectsSwaggerRoleAndPermissions;
import org.apache.isis.extensions.secman.applib.seed.SeedSecurityModuleService;
import org.apache.isis.extensions.secman.applib.tenancy.seed.GlobalTenancy;
import org.apache.isis.extensions.secman.applib.user.seed.IsisExtSecmanAdminUser;
import org.apache.isis.testing.fixtures.applib.fixturescripts.FixtureScript;

import lombok.val;

/**
 * Sets up roles and permissions for both Secman itself and also for all other modules that expose UI features
 * for use by end-users.
 *
 * <p>
 * This fixture script is run automatically on start-up by the {@link SeedSecurityModuleService}.
 * </p>
 *
 * @see SeedSecurityModuleService
 *
 * @since 2.0 {@index}
 */
public class SeedUsersAndRolesFixtureScript extends FixtureScript {

    @Inject private IsisConfiguration config;

    @Override
    protected void execute(final ExecutionContext executionContext) {

        val secmanConfig = config.getExtensions().getSecman();

        // global tenancy
        executionContext.executeChild(this, new GlobalTenancy());

        // secman (admin and regular users)
        executionContext.executeChildren(this,
                new IsisExtSecmanAdminRoleAndPermissions(secmanConfig),
                new IsisExtSecmanAdminUser(secmanConfig),
                new IsisExtSecmanRegularUserRoleAndPermissions(secmanConfig));

        // other modules
        executionContext.executeChildren(this,
                new IsisApplibFeatureRoleAndPermissions(),
                new IsisPersistenceJdoMetaModelRoleAndPermissions(),
                new IsisExtCommandReplayPrimaryRoleAndPermissions(),
                new IsisExtCommandReplaySecondaryRoleAndPermissions(),
                new IsisExtH2ConsoleRoleAndPermissions(),
                new IsisViewerRestfulObjectsSwaggerRoleAndPermissions(),
                new IsisApplibSudoImpersonateRoleAndPermissions(),
                new IsisApplibConfigurationRoleAndPermissions()
                );
    }

}
