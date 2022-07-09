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
package org.apache.isis.core.security.authentication.standard;

import java.security.SecureRandom;

import javax.annotation.Priority;
import javax.inject.Named;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import org.apache.isis.applib.annotation.PriorityPrecedence;
import org.apache.isis.core.security.IsisModuleCoreSecurity;

@Component
@Named(RandomCodeGeneratorDefault.LOGICAL_TYPE_NAME)
@Priority(PriorityPrecedence.LATE)
@Qualifier("Default")
public class RandomCodeGeneratorDefault implements RandomCodeGenerator {

    static final String LOGICAL_TYPE_NAME = IsisModuleCoreSecurity.NAMESPACE + ".RandomCodeGenerator10Chars";

    private static final int NUMBER_CHARACTERS = 10;
    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";

    private final SecureRandom random = new SecureRandom(); // Compliant for security-sensitive use cases

    @Override
    public String generateRandomCode() {
        final StringBuilder buf = new StringBuilder(NUMBER_CHARACTERS);
        for (int i = 0; i < NUMBER_CHARACTERS; i++) {
            final int pos = random.nextInt(CHARACTERS.length());
            buf.append(CHARACTERS.charAt(pos));
        }
        return buf.toString();
    }

}
