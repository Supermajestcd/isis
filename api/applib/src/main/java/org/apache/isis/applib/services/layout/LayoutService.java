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
package org.apache.isis.applib.services.layout;

import org.apache.isis.applib.services.menu.MenuBarsService;

/**
 * Provides the ability to obtain the XML layout for a single domain object or
 * for all domain objects.
 *
 * @since 1.x {@index}
 */
public interface LayoutService {

    /**
     * Obtains the serialized XML form of the layout (grid) for the specified domain class.
     */
    String toXml(Class<?> domainClass, LayoutExportStyle style);

    /**
     * Obtains a zip file of the serialized XML of the layouts (grids) of all domain entities and view models.
     */
    byte[] toZip(final LayoutExportStyle style);

    /**
     * Obtains the serialized XML form of the menu bars layout ({@link MenuBarsService}).
     * @param type - either the current menubars (could be loaded from a file) or the fallback (obtained from metamodel facets)
     */
    String toMenuBarsXml(final MenuBarsService.Type type);

}
