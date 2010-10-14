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


package org.apache.isis.extensions.html.component.html;

import java.io.PrintWriter;

import org.apache.isis.metamodel.spec.feature.ObjectActionType;
import org.apache.isis.extensions.html.component.Component;


public class MenuItem implements Component {
    private final String actionId;
    private final String actionName;
    private final String objectId;
    private final String actionDescription;
    private final String reasonDisabled;
    private final boolean takesParameters;
    private final ObjectActionType type;

    public MenuItem(
            final String actionId,
            final String actionName,
            final String actionDescription,
            final String reasonDisabled,
            final ObjectActionType type,
            final boolean takesParameters,
            final String objectId) {
        this.actionId = actionId;
        this.actionName = actionName;
        this.actionDescription = actionDescription;
        this.reasonDisabled = reasonDisabled;
        this.type = type;
        this.takesParameters = takesParameters;
        this.objectId = objectId;
    }

    public void write(final PrintWriter writer) {
        writer.print("<div class=\"menu-item\">");
        if (isEmpty(reasonDisabled)) {
            writeEnabledLink(writer);
        } else {
            writeDisabledLink(writer);
        }
        writer.println("</div>");
    }

    private boolean isEmpty(final String str) {
        return str == null || str.length() == 0;
    }

    private void writeDisabledLink(final PrintWriter writer) {
        writer.print("<div class=\"disabled\" title=\"");
        writer.print(reasonDisabled);
        writer.print("\">");
        writer.print(actionName);
        if (takesParameters) {
            writer.print(". . .");
        }
        writer.print("</div>");
    }

    private void writeEnabledLink(final PrintWriter writer) {
        writer.print("<a title=\"");
        writer.print(actionDescription);
        writer.print("\" href=\"");
        writer.print("method.app?id=");
        writer.print(objectId);
        writer.print("&amp;action=");
        writer.print(actionId);
        writer.print("\">");
        if (type == ObjectActionType.EXPLORATION) {
            writer.print("[");
            writer.print(actionName);
            writer.print("]");
        } else {
            writer.print(actionName);
        }
        if (takesParameters) {
            writer.print(". . .");
        }
        writer.print("</a>");
    }

}

