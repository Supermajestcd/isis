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

import org.apache.isis.extensions.html.component.Component;


public class Submenu implements Component {

    private final String menuName;
    private final Component[] items;

    public Submenu(final String menuName, final Component[] items) {
        this.menuName = menuName;
        this.items = items;
    }

    public void write(final PrintWriter writer) {
        writer.println("<div class=\"submenu-item\">");
        writer.println(menuName);
        for (int j = 0; j < items.length; j++) {
            items[j].write(writer);
        }
        writer.println("</div>");
    }

}

