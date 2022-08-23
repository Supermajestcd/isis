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
package org.apache.isis.core.metamodel.valuesemantics;

import javax.annotation.Priority;
import javax.inject.Named;

import org.springframework.stereotype.Component;

import org.apache.isis.applib.annotation.PriorityPrecedence;
import org.apache.isis.applib.util.schema.CommandDtoUtils;
import org.apache.isis.commons.collections.Can;
import org.apache.isis.schema.cmd.v2.CommandDto;

@Component
@Named("isis.val.CommandDtoValueSemantics")
@Priority(PriorityPrecedence.LATE)
public class CommandDtoValueSemantics
extends XmlValueSemanticsAbstract<CommandDto> {

    @Override
    public final Class<CommandDto> getCorrespondingClass() {
        return CommandDto.class;
    }

    // -- ENCODER DECODER

    @Override
    public final String toXml(final CommandDto commandDto) {
        return CommandDtoUtils.toXml(commandDto);
    }

    @Override
    public final CommandDto fromXml(final String xml) {
        return CommandDtoUtils.fromXml(xml);
    }

    // -- EXAMPLES

    @Override
    public Can<CommandDto> getExamples() {
        return Can.of(new CommandDto(), new CommandDto());
    }

}
