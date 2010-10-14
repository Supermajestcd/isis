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

import org.apache.isis.commons.exceptions.IsisException;
import org.apache.isis.extensions.html.component.DebugPane;


public class HtmlDebug implements DebugPane {
    private static final String SPACES = "                                    ";
    private final StringBuffer debug = new StringBuffer();
    private int indent;

    public void addSection(final String title) {
        if (debug.length() > 0) {
            appendln("</pre>");
        }
        appendln("<h2>");
        appendln(title);
        appendln("</h2><pre>");
    }

    public void appendln(final String text) {
        debug.append(SPACES.substring(0, indent * 3));
        debug.append(text);
        debug.append("\n");
    }

    public void write(final PrintWriter writer) {
        if (debug.length() > 0) {
            writer.print(debug.toString());
            writer.println("</pre>");
        }
    }

    public void indent() {
        indent++;
    }

    public void unindent() {
        if (indent == 0) {
            throw new IsisException();
        }
        indent--;
    }
}

