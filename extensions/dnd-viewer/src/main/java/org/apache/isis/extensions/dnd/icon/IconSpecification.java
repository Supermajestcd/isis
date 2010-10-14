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


package org.apache.isis.extensions.dnd.icon;

import org.apache.isis.extensions.dnd.drawing.ColorsAndFonts;
import org.apache.isis.extensions.dnd.drawing.Text;
import org.apache.isis.extensions.dnd.view.Axes;
import org.apache.isis.extensions.dnd.view.Content;
import org.apache.isis.extensions.dnd.view.Toolkit;
import org.apache.isis.extensions.dnd.view.View;
import org.apache.isis.extensions.dnd.view.ViewRequirement;
import org.apache.isis.extensions.dnd.view.ViewSpecification;
import org.apache.isis.extensions.dnd.view.base.IconGraphic;
import org.apache.isis.extensions.dnd.view.text.ObjectTitleText;


public class IconSpecification implements ViewSpecification {
    private final boolean isSubView;
    private final boolean isReplaceable;

    public IconSpecification() {
        this(true, true);
    }

    IconSpecification(final boolean isSubView, final boolean isReplaceable) {
        this.isSubView = isSubView;
        this.isReplaceable = isReplaceable;
    }

    public boolean canDisplay(ViewRequirement requirement) {
        return requirement.isClosed() && requirement.isObject() && requirement.hasReference();
    }
    
    public View createView(final Content content, Axes axes, int sequence) {
        final Text style = Toolkit.getText(ColorsAndFonts.TEXT_NORMAL);
        final Icon icon = new Icon(content, this);
        icon.setTitle(new ObjectTitleText(icon, style));
        icon.setSelectedGraphic(new IconGraphic(icon, style));
        return icon;
    }

    public String getName() {
        return "Icon";
    }

    public boolean isSubView() {
        return isSubView;
    }

    public boolean isReplaceable() {
        return isReplaceable;
    }
    
    public boolean isResizeable() {
        return false;
    }

    public View decorateSubview(final View subview) {
        return subview;
    }

    public boolean isOpen() {
        return false;
    }

    public boolean isAligned() {
        return false;
    }
}
