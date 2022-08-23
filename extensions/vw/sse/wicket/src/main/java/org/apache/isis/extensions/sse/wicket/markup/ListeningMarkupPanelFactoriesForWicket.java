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
package org.apache.isis.extensions.sse.wicket.markup;

import org.springframework.stereotype.Component;

import org.apache.isis.applib.value.LocalResourcePath;
import org.apache.isis.applib.value.Markup;
import org.apache.isis.extensions.sse.metamodel.facets.SseObserveFacet;
import org.apache.isis.viewer.wicket.model.models.ScalarModel;
import org.apache.isis.viewer.wicket.model.models.ValueModel;
import org.apache.isis.viewer.wicket.ui.components.scalars.markup.MarkupComponent;
import org.apache.isis.viewer.wicket.ui.components.scalars.markup.MarkupPanelFactories;

import lombok.val;

/**
 * @implNote Almost a copy of {@code Parented} and {@code Standalone} in
 * {@link MarkupPanelFactories}, but specific to
 * the {@link Markup} value-type which requires client-side java-script to be
 * executed to enable syntax highlighting
 */
public class ListeningMarkupPanelFactoriesForWicket {

    // -- PARENTED

    @Component
    public static class Parented extends MarkupPanelFactories.ParentedAbstract<Markup> {
        private static final long serialVersionUID = 1L;

        public Parented() {
            super(Markup.class);
        }


        @Override
        protected MarkupComponent newMarkupComponent(final String id, final ScalarModel model) {
            val markupComponent = new ListeningMarkupComponent(
                    id, model, getEventStreamResource(model));
            markupComponent.setEnabled(false);
            return getCommonContext().getServiceInjector().injectServicesInto(markupComponent);
        }

        // -- HELPER

        private LocalResourcePath getEventStreamResource(final ScalarModel scalarModel) {
            val observeFacet  = scalarModel.getFacet(SseObserveFacet.class);
            return observeFacet!=null
                    ? observeFacet.getEventStreamResource()
                    : null;
        }

    }

    // -- STANDALONE

    @Component
    public static class Standalone extends MarkupPanelFactories.StandaloneAbstract<Markup> {
        private static final long serialVersionUID = 1L;

        public Standalone() {
            super(Markup.class);
        }

        @Override
        protected MarkupComponent newMarkupComponent(final String id, final ValueModel model) {
            return getCommonContext()
                    .getServiceInjector()
                    .injectServicesInto(new ListeningMarkupComponent(id, model));
        }

    }


}
