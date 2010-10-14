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


package org.apache.isis.extensions.dnd.view.composite;

import org.apache.isis.extensions.dnd.drawing.Bounds;
import org.apache.isis.extensions.dnd.drawing.Canvas;
import org.apache.isis.extensions.dnd.form.FormSpecification;
import org.apache.isis.extensions.dnd.view.Axes;
import org.apache.isis.extensions.dnd.view.Click;
import org.apache.isis.extensions.dnd.view.Toolkit;
import org.apache.isis.extensions.dnd.view.View;
import org.apache.isis.extensions.dnd.view.base.AbstractBorder;

public class ReplaceViewBorder extends AbstractBorder {

    protected ReplaceViewBorder(View view) {
        super(view);
    }
    
    public void draw(Canvas canvas) {
        super.draw(canvas);
        
        Bounds b = getButtonBounds();
        canvas.drawRoundedRectangle(b.getX(), b.getY(), b.getWidth(), b.getHeight(), 6, 6, Toolkit.getColor(0xfff));
    }

    public void firstClick(Click click) {
        if (getButtonBounds().contains(click.getLocation())) {
            View view = new FormSpecification().createView(getContent(), new Axes(), 0);
            getWorkspace().replaceView(getParent(), view);
        }
    }

    private Bounds getButtonBounds() {
        int x = getSize().getWidth() - 28;
        return new Bounds(x, 8, 20, 16);
    }
}


