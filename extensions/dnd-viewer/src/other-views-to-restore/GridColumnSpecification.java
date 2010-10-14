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


package org.apache.isis.viewer.dnd.special;

import org.apache.isis.object.ObjectAdapterRuntimeException;
import org.apache.isis.viewer.dnd.Content;
import org.apache.isis.viewer.dnd.ObjectContent;
import org.apache.isis.viewer.dnd.OneToManyField;
import org.apache.isis.viewer.dnd.ValueContent;
import org.apache.isis.viewer.dnd.View;
import org.apache.isis.viewer.dnd.ViewAxis;
import org.apache.isis.viewer.dnd.ViewSpecification;
import org.apache.isis.viewer.dnd.core.AbstractCompositeViewSpecification;
import org.apache.isis.viewer.dnd.util.ViewFactory;

class GridColumnSpecification extends AbstractCompositeViewSpecification{

    GridColumnSpecification() {
        builder = new ColumnLayout(155, new ObjectFieldBuilder(new ColumnSubviews()));
    }
	
	public String getName() {
		return "Grid Column";
	}
	
    private static class ColumnSubviews implements SubviewSpec {
        public View createSubview(Content content, ViewAxis axis) {
            ViewFactory factory = Skylark.getViewFactory();

            ViewSpecification specification;

            if (content instanceof OneToManyField) {
                specification = new ScheduleSpecification();
            } else if (content instanceof ValueContent) {
                specification = factory.getValueFieldSpecification((ValueContent) content);
            } else if (content instanceof ObjectContent) {
                specification = factory.getIconizedSubViewSpecification((ObjectContent) content);
            } else {
                throw new ObjectAdapterRuntimeException();
            }

            return specification.createView(content, axis);
        }

        public View decorateSubview(View view) {
            return view;
        }
    }

}


/*
[[NAME]] - a framework that exposes behaviourally complete
business objects directly to the user.
Copyright (C) 2000 - 2005  [[NAME]] Group Ltd

This program is free software; you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by
the Free Software Foundation; either version 2 of the License, or
(at your option) any later version.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.

You should have received a copy of the GNU General Public License
along with this program; if not, write to the Free Software
Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA

The authors can be contacted via isis.apache.org (the
registered address of [[NAME]] Group is Kingsway House, 123 Goldworth
Road, Woking GU21 1NR, UK).
*/