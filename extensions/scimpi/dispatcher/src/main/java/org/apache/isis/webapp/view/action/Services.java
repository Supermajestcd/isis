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


package org.apache.isis.webapp.view.action;

import java.util.List;

import org.apache.isis.metamodel.adapter.ObjectAdapter;
import org.apache.isis.runtime.context.IsisContext;
import org.apache.isis.runtime.persistence.PersistenceSession;
import org.apache.isis.webapp.AbstractElementProcessor;
import org.apache.isis.webapp.context.RequestContext.Scope;
import org.apache.isis.webapp.processor.Request;
import org.apache.isis.webapp.view.field.InclusionList;


public class Services extends AbstractElementProcessor {

    public void process(Request request) {
        boolean showForms = request.isRequested(FORMS, false);

        InclusionList inclusionList = new InclusionList();
        request.setBlockContent(inclusionList);
        request.processUtilCloseTag();
        
        List<ObjectAdapter> serviceAdapters = getPersistenceSession().getServices();
        for (ObjectAdapter adapter: serviceAdapters) {
            String serviceId = request.getContext().mapObject(adapter, Scope.REQUEST);
            request.appendHtml("<div class=\"actions\">");
            request.appendHtml("<h3>" + adapter.titleString() + "</h3>");
            Methods.writeMethods(request, serviceId, adapter, showForms, inclusionList);
            request.appendHtml("</div>");
        }
        request.popBlockContent();
    }



    public String getName() {
        return "services";
    }

    private static PersistenceSession getPersistenceSession() {
    	return IsisContext.getPersistenceSession();
    }
    
}

