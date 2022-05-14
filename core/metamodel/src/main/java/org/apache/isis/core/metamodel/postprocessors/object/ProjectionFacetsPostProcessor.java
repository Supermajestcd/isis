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
package org.apache.isis.core.metamodel.postprocessors.object;

import javax.inject.Inject;

import org.springframework.lang.Nullable;

import org.apache.isis.core.metamodel.context.MetaModelContext;
import org.apache.isis.core.metamodel.facetapi.Facet;
import org.apache.isis.core.metamodel.facetapi.FacetUtil;
import org.apache.isis.core.metamodel.facets.members.cssclass.CssClassFacet;
import org.apache.isis.core.metamodel.facets.object.icon.IconFacet;
import org.apache.isis.core.metamodel.facets.object.projection.ProjectionFacetFromProjectingProperty;
import org.apache.isis.core.metamodel.facets.object.projection.ident.IconFacetFromProjectionFacet;
import org.apache.isis.core.metamodel.facets.object.projection.ident.TitleFacetFromProjectionFacet;
import org.apache.isis.core.metamodel.facets.object.title.TitleFacet;
import org.apache.isis.core.metamodel.postprocessors.ObjectSpecificationPostProcessorAbstract;
import org.apache.isis.core.metamodel.spec.ObjectSpecification;

import lombok.val;

public class ProjectionFacetsPostProcessor
extends ObjectSpecificationPostProcessorAbstract {

    @Inject
    public ProjectionFacetsPostProcessor(final MetaModelContext metaModelContext) {
        super(metaModelContext);
    }

    @Override
    protected void doPostProcess(final ObjectSpecification objectSpecification) {
        val projectionFacet = ProjectionFacetFromProjectingProperty.create(objectSpecification);
        if (projectionFacet == null) {
            return;
        }
        FacetUtil.addFacet(projectionFacet);
        val titleFacet = objectSpecification.getFacet(TitleFacet.class);
        if(canOverwrite(titleFacet)) {
            FacetUtil.addFacet(new TitleFacetFromProjectionFacet(projectionFacet, objectSpecification));
        }
        val iconFacet = objectSpecification.getFacet(IconFacet.class);
        if(canOverwrite(iconFacet)) {
            FacetUtil.addFacet(new IconFacetFromProjectionFacet(projectionFacet, objectSpecification));
        }
        val cssClassFacet = objectSpecification.getFacet(CssClassFacet.class);
        if(canOverwrite(cssClassFacet)) {
            FacetUtil.addFacet(new IconFacetFromProjectionFacet(projectionFacet, objectSpecification));
        }
    }

    // -- HELPER

    private static boolean canOverwrite(final @Nullable Facet existingFacet) {
        return existingFacet == null
                || existingFacet.getPrecedence().isFallback()
                || existingFacet.getPrecedence().isInferred();
    }

}
