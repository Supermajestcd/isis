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


package org.apache.isis.extensions.dnd.tree;

import org.apache.isis.extensions.dnd.list.SimpleListSpecification;
import org.apache.isis.extensions.dnd.view.Axes;
import org.apache.isis.extensions.dnd.view.Content;
import org.apache.isis.extensions.dnd.view.View;
import org.apache.isis.extensions.dnd.view.ViewRequirement;
import org.apache.isis.extensions.dnd.view.ViewSpecification;
import org.apache.isis.extensions.dnd.view.border.SelectObjectBorder;
import org.apache.isis.extensions.dnd.view.composite.MasterDetailPanel;

public class ListWithDetailSpecification implements ViewSpecification {
    private SimpleListSpecification leftHandSideSpecification;

    public ListWithDetailSpecification() {
        leftHandSideSpecification = new SimpleListSpecification();
        leftHandSideSpecification.addSubviewDecorator(new SelectObjectBorder.Factory());
    }

    public boolean canDisplay(ViewRequirement requirement) {
        return requirement.isCollection() && requirement.isOpen();
    }
 
    public View createView(Content content, Axes axes, int sequence) {
        return new MasterDetailPanel(content, this, leftHandSideSpecification);
    }
    
    public String getName() {
        return "List and details";
    }

    public boolean isAligned() {
        return false;
    }

    public boolean isOpen() {
        return true;
    }

    public boolean isReplaceable() {
        return true;
    }
    
    public boolean isResizeable() {
        return false;
    }

    public boolean isSubView() {
        return false;
    }

}


