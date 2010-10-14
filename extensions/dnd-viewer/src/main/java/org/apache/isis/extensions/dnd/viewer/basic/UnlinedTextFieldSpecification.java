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


package org.apache.isis.extensions.dnd.viewer.basic;

import org.apache.isis.extensions.dnd.field.SingleLineTextField;
import org.apache.isis.extensions.dnd.view.Axes;
import org.apache.isis.extensions.dnd.view.Content;
import org.apache.isis.extensions.dnd.view.View;
import org.apache.isis.extensions.dnd.view.ViewRequirement;
import org.apache.isis.extensions.dnd.view.base.AbstractFieldSpecification;
import org.apache.isis.extensions.dnd.view.content.TextParseableContent;


/**
 * Creates a single line text field without the baseline being drawn.
 */
public class UnlinedTextFieldSpecification extends AbstractFieldSpecification {
    @Override
    public boolean canDisplay(ViewRequirement requirement) {
        return requirement.isTextParseable()   && ((TextParseableContent)  requirement.getContent()).getNoLines() == 1;
    }

    public View createView(final Content content, Axes axes, int sequence) {
        return new SingleLineTextField((TextParseableContent) content, this, false);
    }

    public String getName() {
        return "Single Line Text Field";
    }

    @Override
    public boolean isAligned() {
        return true;
    }

}
