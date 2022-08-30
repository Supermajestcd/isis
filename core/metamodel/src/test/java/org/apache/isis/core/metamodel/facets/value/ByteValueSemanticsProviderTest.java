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
package org.apache.isis.core.metamodel.facets.value;

import org.junit.Before;
import org.junit.Test;

import org.apache.isis.applib.exceptions.recoverable.TextEntryParseException;
import org.apache.isis.core.metamodel.valuesemantics.ByteValueSemantics;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class ByteValueSemanticsProviderTest
extends ValueSemanticsProviderAbstractTestCase<Byte> {

    private ByteValueSemantics value;

    private Byte byteObj;

    @Before
    public void setUpObjects() throws Exception {
        byteObj = Byte.valueOf((byte) 102);
        allowMockAdapterToReturn(byteObj);
        setSemantics(value = new ByteValueSemantics());
    }

    @Test
    public void testParseValidString() throws Exception {
        final Object parsed = value.parseTextRepresentation(null, "21");
        assertEquals(Byte.valueOf((byte) 21), parsed);
    }

    @Test
    public void testParseInvalidString() throws Exception {
        try {
            value.parseTextRepresentation(null, "xs21z4xxx23");
            fail();
        } catch (final TextEntryParseException expected) {
        }
    }

    @Test
    public void testTitleOf() throws Exception {
        assertEquals("102", value.titlePresentation(null, byteObj));
    }

    @Override
    protected Byte getSample() {
        return byteObj;
    }

    @Override
    protected void assertValueEncodesToJsonAs(final Byte a, final String json) {
        assertEquals("102", json);
    }
}
