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
package org.apache.isis.viewer.wicket.viewer.integration;

import java.util.Locale;
import java.util.Optional;

import static java.util.Collections.emptyList;
import static java.util.Collections.singletonList;

import org.apache.wicket.request.Request;
import org.jmock.Expectations;
import org.jmock.auto.Mock;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import org.apache.isis.applib.services.iactnlayer.InteractionService;
import org.apache.isis.applib.services.registry.ServiceRegistry;
import org.apache.isis.commons.functional.ThrowingRunnable;
import org.apache.isis.core.internaltestsupport.jmocking.JUnitRuleMockery2;
import org.apache.isis.core.metamodel._testing.MetaModelContext_forTesting;
import org.apache.isis.core.metamodel.context.MetaModelContext;
import org.apache.isis.core.security._testing.InteractionService_forTesting;
import org.apache.isis.core.security.authentication.AuthenticationRequest;
import org.apache.isis.core.security.authentication.AuthenticationRequestPassword;
import org.apache.isis.core.security.authentication.Authenticator;
import org.apache.isis.core.security.authentication.InteractionContextFactory;
import org.apache.isis.core.security.authentication.manager.AuthenticationManager;
import org.apache.isis.core.security.authentication.standard.RandomCodeGeneratorDefault;

public class AuthenticatedWebSessionForIsis_SignIn {

    @Rule
    public final JUnitRuleMockery2 context =
            JUnitRuleMockery2.createFor(JUnitRuleMockery2.Mode.INTERFACES_AND_CLASSES);

    private AuthenticationManager authMgr;

    @Mock protected Request mockRequest;
    @Mock protected Authenticator mockAuthenticator;
    @Mock protected InteractionService mockInteractionService;
    @Mock protected ServiceRegistry mockServiceRegistry;

    protected AuthenticatedWebSessionForIsis webSession;
    private MetaModelContext mmc;

    @Before
    public void setUp() throws Exception {
        mmc = MetaModelContext_forTesting.builder()
                .singleton(mockInteractionService)
                .build();

        authMgr = new AuthenticationManager(
                singletonList(mockAuthenticator),
                new InteractionService_forTesting(),
                new RandomCodeGeneratorDefault(),
                Optional.empty(),
                emptyList());
    }

    @Test
    public void signInJustDelegatesToAuthenticateAndSavesState() {
        context.checking(new Expectations() {
            {

                allowing(mockInteractionService)
                .run(with(InteractionContextFactory.testing()), with(any(ThrowingRunnable.class)));

                allowing(mockInteractionService)
                .runAnonymous(with(any(ThrowingRunnable.class)));

                // ignore

                // must provide explicit expectation, since Locale is final.
                allowing(mockRequest).getLocale();
                will(returnValue(Locale.getDefault()));

                // stub everything else out
                ignoring(mockRequest);

                oneOf(mockAuthenticator).canAuthenticate(AuthenticationRequestPassword.class);
                will(returnValue(true));

                oneOf(mockAuthenticator).authenticate(with(any(AuthenticationRequest.class)), with(any(String.class)));
                will(returnValue(InteractionContextFactory.testing()));
            }
        });

        webSession = new AuthenticatedWebSessionForIsis(mockRequest) {
            private static final long serialVersionUID = 1L;

            {
                metaModelContext = mmc;
            }

            @Override
            public AuthenticationManager getAuthenticationManager() {
                return authMgr;
            }
        };


        // when
        webSession.signIn("john", "secret");

        // then
        assertThat(webSession.isSignedIn(), is(true));
    }
}
