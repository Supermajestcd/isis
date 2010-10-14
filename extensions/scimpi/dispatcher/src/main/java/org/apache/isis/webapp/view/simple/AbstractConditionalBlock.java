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


package org.apache.isis.webapp.view.simple;

import java.util.List;

import org.apache.isis.metamodel.adapter.ObjectAdapter;
import org.apache.isis.metamodel.consent.Consent;
import org.apache.isis.metamodel.facets.collections.modify.CollectionFacet;
import org.apache.isis.metamodel.spec.ObjectSpecification;
import org.apache.isis.metamodel.spec.feature.ObjectAction;
import org.apache.isis.metamodel.spec.feature.ObjectActionType;
import org.apache.isis.metamodel.spec.feature.ObjectAssociation;
import org.apache.isis.runtime.context.IsisContext;
import org.apache.isis.webapp.AbstractElementProcessor;
import org.apache.isis.webapp.ScimpiException;
import org.apache.isis.webapp.processor.Request;


public abstract class AbstractConditionalBlock extends AbstractElementProcessor {

    public void process(Request request) {
        String id = request.getOptionalProperty(OBJECT);
        
        String method = request.getOptionalProperty(METHOD + "-visible");
        if (method != null) {
            ObjectAdapter object = (ObjectAdapter) request.getContext().getMappedObjectOrResult(id);
            // TODO needs to work irrespective of parameters 
            ObjectAction objectAction = object.getSpecification().getObjectAction(ObjectActionType.USER, method, new ObjectSpecification[0]);
            Consent visible = objectAction.isVisible(IsisContext.getAuthenticationSession(), object);
            processTags(visible.isAllowed(), request);
            return;
        }

        method = request.getOptionalProperty(METHOD + "-usable");
        if (method != null) {
            ObjectAdapter object = (ObjectAdapter) request.getContext().getMappedObjectOrResult(id);
            // TODO needs to work irrespective of parameters 
            ObjectAction objectAction = object.getSpecification().getObjectAction(ObjectActionType.USER, method, new ObjectSpecification[0]);
            Consent usable = objectAction.isUsable(IsisContext.getAuthenticationSession(), object);
            processTags(usable.isAllowed(), request);
            return;
        }

        method = request.getOptionalProperty(METHOD + "-exists");
        if (method != null) {
            ObjectAdapter object = (ObjectAdapter) request.getContext().getMappedObjectOrResult(id);
            List<? extends ObjectAction> objectActions = object.getSpecification().getObjectActionList(ObjectActionType.USER);
            boolean methodExists = false;
            for (ObjectAction objectAssociation : objectActions) {
                if (objectAssociation.getId().equals(method)) {
                    methodExists = true;
                    break;  
                }
            }
            processTags(methodExists, request);
            return;
        }

        String field = request.getOptionalProperty(FIELD + "-visible");
        if (field != null) {
            ObjectAdapter object = (ObjectAdapter) request.getContext().getMappedObjectOrResult(id);
            ObjectAssociation objectField = object.getSpecification().getAssociation(field);
            Consent visible = objectField.isVisible(IsisContext.getAuthenticationSession(), object);
            processTags(visible.isAllowed(), request);
            return;
        }

        field = request.getOptionalProperty(FIELD + "-exists");
        if (field != null) {
            ObjectAdapter object = (ObjectAdapter) request.getContext().getMappedObjectOrResult(id);
            List<? extends ObjectAssociation> objectFields = object.getSpecification().getAssociationList();
            boolean fieldExists = false;
            for (ObjectAssociation objectAssociation : objectFields) {
                if (objectAssociation.getId().equals(field)) {
                    fieldExists = true;
                    break;  
                }
            }
            processTags(fieldExists, request);
            return;
        }

        field = request.getOptionalProperty(FIELD + "-editable");
        if (field != null) {
            ObjectAdapter object = (ObjectAdapter) request.getContext().getMappedObjectOrResult(id);
            ObjectAssociation objectField = object.getSpecification().getAssociation(field);
            Consent usable = objectField.isUsable(IsisContext.getAuthenticationSession(), object);
            processTags(usable.isAllowed(), request);
            return;
        }
        
        field = request.getOptionalProperty(FIELD + "-empty");
        if (field != null) {
            ObjectAdapter object = (ObjectAdapter) request.getContext().getMappedObjectOrResult(id);
            ObjectAssociation objectField = object.getSpecification().getAssociation(field);
            IsisContext.getPersistenceSession().resolveField(object, objectField);
            ObjectAdapter fld = objectField.get(object);
            if (fld == null) {
                processTags(true, request);
            } else {
                    CollectionFacet facet = (CollectionFacet) fld.getSpecification().getFacet(CollectionFacet.class);
                    boolean isEmpty = facet != null &&   facet.size(fld) == 0;
                    // boolean isEmpty = fld == null || (fld instanceof CollectionAdapter && ((CollectionAdapter) fld).size() == 0);
                    processTags(isEmpty, request);
            }
            return;
        }
        
        field = request.getOptionalProperty(FIELD + "-set");
        if (field != null) {
            ObjectAdapter object = (ObjectAdapter) request.getContext().getMappedObjectOrResult(id);
            ObjectAssociation objectField = object.getSpecification().getAssociation(field);
            IsisContext.getPersistenceSession().resolveField(object, objectField);
            ObjectAdapter fld = objectField.get(object);
            Object fieldValue = fld.getObject();
            if (fieldValue instanceof Boolean) {
                processTags(((Boolean) fieldValue).booleanValue(), request);
            } else {
                processTags(true, request);
            }
            return;
        }

        String persistent = request.getOptionalProperty("persistent");
        if (persistent != null) {
            ObjectAdapter object = (ObjectAdapter) request.getContext().getMappedObjectOrResult(persistent);
            processTags(object.isPersistent(), request);
            return;
        }
        
        
        if (request.isPropertySpecified("empty")) {
            if (request.isPropertySet("empty")) {
                String collection = request.getOptionalProperty("empty");
                if (collection != null) {
                    ObjectAdapter object = request.getContext().getMappedObjectOrResult(collection);
                    CollectionFacet facet = (CollectionFacet) object.getSpecification().getFacet(CollectionFacet.class);
                    processTags(facet.size(object) == 0, request);
                }
            } else {
                processTags(true, request);
            }
            return;
        }
        
        if (request.isPropertySpecified("set")) {
            boolean valuePresent = request.isPropertySet("set");
            processTags(valuePresent, request);
            return;
        }
        
        throw new ScimpiException("No condition in " + getName());
    }

    protected abstract void processTags(boolean isSet, Request request);

}

