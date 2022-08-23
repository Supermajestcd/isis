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
package org.apache.isis.security.authentication.standard;

import java.util.Collections;
import java.util.Optional;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.sameInstance;
import static org.hamcrest.MatcherAssert.assertThat;

import org.apache.isis.applib.exceptions.unrecoverable.NoAuthenticatorException;
import org.apache.isis.core.security._testing.InteractionService_forTesting;
import org.apache.isis.core.security.authentication.AuthenticationRequestPassword;
import org.apache.isis.core.security.authentication.manager.AuthenticationManager;
import org.apache.isis.core.security.authentication.standard.RandomCodeGeneratorDefault;
import org.apache.isis.security.AuthenticatorsForTesting;

import lombok.val;

public class AuthenticationManager_authenticators_Test {

    private AuthenticationManager authenticationManager;

    @Test(expected = NoAuthenticatorException.class)
    public void shouldNotBeAbleToAuthenticateWithNoAuthenticators() throws Exception {

        authenticationManager = new AuthenticationManager(
                Collections.emptyList(),
                new InteractionService_forTesting(),
                new RandomCodeGeneratorDefault(),
                Optional.empty(),
                Collections.emptyList());
        authenticationManager.authenticate(new AuthenticationRequestPassword("foo", "bar"));
    }

    @Test
    public void shouldBeAbleToUseAuthenticators() throws Exception {

        val auth = AuthenticatorsForTesting.authenticatorAllwaysValid();

        authenticationManager = new AuthenticationManager(
                Collections.singletonList(auth),
                new InteractionService_forTesting(),
                new RandomCodeGeneratorDefault(),
                Optional.empty(),
                Collections.emptyList());
        assertThat(authenticationManager.getAuthenticators().size(), is(1));
        assertThat(authenticationManager.getAuthenticators().getElseFail(0), is(sameInstance(auth)));
    }

}
