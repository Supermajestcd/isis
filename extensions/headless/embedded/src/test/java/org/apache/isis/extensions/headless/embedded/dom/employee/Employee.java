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


package org.apache.isis.extensions.headless.embedded.dom.employee;

import org.apache.isis.applib.AbstractDomainObject;
import org.apache.isis.applib.annotation.Disabled;
import org.apache.isis.applib.annotation.MemberOrder;
import org.apache.isis.applib.annotation.When;
import org.apache.isis.extensions.headless.embedded.dom.claim.Approver;
import org.apache.isis.extensions.headless.embedded.dom.claim.Claimant;


public class Employee extends AbstractDomainObject implements Claimant, Approver {

	// {{ Title
    public String title() {
        return getName();
    }
    // }}

    
    // {{ Name
    private String name;
    @MemberOrder(sequence="1")
    public String getName() {
        return name;
    }
    public void setName(final String name) {
        this.name = name;
    }
    public void modifyName(final String name) {
        setName(name);
    }
    public void clearName() {
        setName(null);
    }
    
    public boolean whetherHideName;
    public boolean hideName() {
        return whetherHideName;
    }
    public String reasonDisableName;
    public String disableName() {
    	return reasonDisableName;
    }
    public String reasonValidateName;
    public String validateName(String name) {
    	return reasonValidateName;
    }
    // }}
    
    // {{ Password
    private String password;
    @MemberOrder(sequence="2")
    @Disabled(When.ONCE_PERSISTED)
    public String getPassword() {
        return password;
    }
    public void setPassword(final String password) {
        this.password = password;
    }
    // }}
    

    // {{ Approver
    private Approver approver;
    @MemberOrder(sequence="2")
    public Approver getApprover() {
        return approver;
    }
    public void setApprover(final Approver approver) {
        this.approver = approver;
    }
    // }}
 
    
    
}


