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
package org.apache.isis.client.kroviz.snapshots.demo2_0_0

import org.apache.isis.client.kroviz.snapshots.Response

object STRINGS_LAYOUT_XML: Response() {
    override val url = "http://localhost:8080/restful/objects/demo.Tab/ADw_eG1sIHZlcnNpb249IjEuMCIgZW5jb2Rpbmc9IlVURi04IiBzdGFuZGFsb25lPSJ5ZXMiPz4KPERlbW8-CiAgICA8ZmllbGQxPmZpZWxkIDE8L2ZpZWxkMT4KICAgIDxmaWVsZDI-ZmllbGQgMjwvZmllbGQyPgogICAgPGhpZGRlbj5mYWxzZTwvaGlkZGVuPgo8L0RlbW8-Cg==/object-layout"
    override val str = """<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<bs:grid xmlns:cpt="http://isis.apache.org/applib/layout/component"
         xmlns:lnk="http://isis.apache.org/applib/layout/links"
         xmlns:bs="http://isis.apache.org/applib/layout/grid/bootstrap3">
    <bs:row>
        <bs:col span="12" unreferencedActions="true">
            <cpt:domainObject bookmarking="AS_ROOT">
                <cpt:link>
                    <lnk:rel>urn:org.restfulobjects:rels/element</lnk:rel>
                    <lnk:method>GET</lnk:method>
                    <lnk:href>
                        http://localhost:9090/restful/objects/isis.applib.DomainObjectList/PAR-LCAAAAAAAAACFkMFOwzAMhu99iih3knFDU5uJy6QhEIeNBwip1WVKnFJ7g709jspgggM5Ob9_f7_ldvWRkzrBRLFgp2_NQivAUPqIQ6dfduubO62IPfY-FYROn4H0yjVtisRKZpGWoeRO75nHpbWRIhk_-rAHU6bBkhTZW7Hkgto1Sl7LkRO4hXrwJ68ePQ5qy5MEElBr5-Zs9IFlred3lOb6LaDrIRc_jqYv2fB5BDIHYSRBmAqrrF2VnwCPrf0zfk3d9I7m1ItRlGvD_TQcMyCTuxh-lNkHCeq3Jv633VfU95bbS_RvxkwurwcITFYObeulXfMJyMCkw6kBAAA=
                    </lnk:href>
                    <lnk:type>application/json;profile="urn:org.restfulobjects:repr-types/object"</lnk:type>
                </cpt:link>
            </cpt:domainObject>
        </bs:col>
    </bs:row>
    <bs:row>
        <bs:col span="4">
            <bs:row>
                <bs:col span="12">
                    <bs:tabGroup>
                        <bs:tab name="Identity">
                            <bs:row>
                                <bs:col span="12">
                                    <cpt:fieldSet name="Identity" id="identity"/>
                                </bs:col>
                            </bs:row>
                        </bs:tab>
                        <bs:tab name="Other">
                            <bs:row>
                                <bs:col span="12">
                                    <cpt:fieldSet name="Other" id="other" unreferencedProperties="true">
                                        <cpt:property dateRenderAdjustDays="0" id="actionArguments" labelPosition="LEFT"
                                                      typicalLength="25">
                                            <cpt:link>
                                                <lnk:rel>urn:org.restfulobjects:rels/property</lnk:rel>
                                                <lnk:method>GET</lnk:method>
                                                <lnk:href>
                                                    http://localhost:9090/restful/objects/isis.applib.DomainObjectList/PAR-LCAAAAAAAAACFkMFOwzAMhu99iih3knFDU5uJy6QhEIeNBwip1WVKnFJ7g709jspgggM5Ob9_f7_ldvWRkzrBRLFgp2_NQivAUPqIQ6dfduubO62IPfY-FYROn4H0yjVtisRKZpGWoeRO75nHpbWRIhk_-rAHU6bBkhTZW7Hkgto1Sl7LkRO4hXrwJ68ePQ5qy5MEElBr5-Zs9IFlred3lOb6LaDrIRc_jqYv2fB5BDIHYSRBmAqrrF2VnwCPrf0zfk3d9I7m1ItRlGvD_TQcMyCTuxh-lNkHCeq3Jv633VfU95bbS_RvxkwurwcITFYObeulXfMJyMCkw6kBAAA=/properties/actionArguments
                                                </lnk:href>
                                                <lnk:type>
                                                    application/json;profile="urn:org.restfulobjects:repr-types/object-property"
                                                </lnk:type>
                                            </cpt:link>
                                        </cpt:property>
                                        <cpt:property dateRenderAdjustDays="0" id="actionId" labelPosition="LEFT"
                                                      typicalLength="25">
                                            <cpt:link>
                                                <lnk:rel>urn:org.restfulobjects:rels/property</lnk:rel>
                                                <lnk:method>GET</lnk:method>
                                                <lnk:href>
                                                    http://localhost:9090/restful/objects/isis.applib.DomainObjectList/PAR-LCAAAAAAAAACFkMFOwzAMhu99iih3knFDU5uJy6QhEIeNBwip1WVKnFJ7g709jspgggM5Ob9_f7_ldvWRkzrBRLFgp2_NQivAUPqIQ6dfduubO62IPfY-FYROn4H0yjVtisRKZpGWoeRO75nHpbWRIhk_-rAHU6bBkhTZW7Hkgto1Sl7LkRO4hXrwJ68ePQ5qy5MEElBr5-Zs9IFlred3lOb6LaDrIRc_jqYv2fB5BDIHYSRBmAqrrF2VnwCPrf0zfk3d9I7m1ItRlGvD_TQcMyCTuxh-lNkHCeq3Jv633VfU95bbS_RvxkwurwcITFYObeulXfMJyMCkw6kBAAA=/properties/actionId
                                                </lnk:href>
                                                <lnk:type>
                                                    application/json;profile="urn:org.restfulobjects:repr-types/object-property"
                                                </lnk:type>
                                            </cpt:link>
                                        </cpt:property>
                                        <cpt:property dateRenderAdjustDays="0" id="actionOwningFqcn"
                                                      labelPosition="LEFT" typicalLength="25">
                                            <cpt:link>
                                                <lnk:rel>urn:org.restfulobjects:rels/property</lnk:rel>
                                                <lnk:method>GET</lnk:method>
                                                <lnk:href>
                                                    http://localhost:9090/restful/objects/isis.applib.DomainObjectList/PAR-LCAAAAAAAAACFkMFOwzAMhu99iih3knFDU5uJy6QhEIeNBwip1WVKnFJ7g709jspgggM5Ob9_f7_ldvWRkzrBRLFgp2_NQivAUPqIQ6dfduubO62IPfY-FYROn4H0yjVtisRKZpGWoeRO75nHpbWRIhk_-rAHU6bBkhTZW7Hkgto1Sl7LkRO4hXrwJ68ePQ5qy5MEElBr5-Zs9IFlred3lOb6LaDrIRc_jqYv2fB5BDIHYSRBmAqrrF2VnwCPrf0zfk3d9I7m1ItRlGvD_TQcMyCTuxh-lNkHCeq3Jv633VfU95bbS_RvxkwurwcITFYObeulXfMJyMCkw6kBAAA=/properties/actionOwningFqcn
                                                </lnk:href>
                                                <lnk:type>
                                                    application/json;profile="urn:org.restfulobjects:repr-types/object-property"
                                                </lnk:type>
                                            </cpt:link>
                                        </cpt:property>
                                        <cpt:property dateRenderAdjustDays="0" id="elementTypeFqcn" labelPosition="LEFT"
                                                      typicalLength="25">
                                            <cpt:link>
                                                <lnk:rel>urn:org.restfulobjects:rels/property</lnk:rel>
                                                <lnk:method>GET</lnk:method>
                                                <lnk:href>
                                                    http://localhost:9090/restful/objects/isis.applib.DomainObjectList/PAR-LCAAAAAAAAACFkMFOwzAMhu99iih3knFDU5uJy6QhEIeNBwip1WVKnFJ7g709jspgggM5Ob9_f7_ldvWRkzrBRLFgp2_NQivAUPqIQ6dfduubO62IPfY-FYROn4H0yjVtisRKZpGWoeRO75nHpbWRIhk_-rAHU6bBkhTZW7Hkgto1Sl7LkRO4hXrwJ68ePQ5qy5MEElBr5-Zs9IFlred3lOb6LaDrIRc_jqYv2fB5BDIHYSRBmAqrrF2VnwCPrf0zfk3d9I7m1ItRlGvD_TQcMyCTuxh-lNkHCeq3Jv633VfU95bbS_RvxkwurwcITFYObeulXfMJyMCkw6kBAAA=/properties/elementTypeFqcn
                                                </lnk:href>
                                                <lnk:type>
                                                    application/json;profile="urn:org.restfulobjects:repr-types/object-property"
                                                </lnk:type>
                                            </cpt:link>
                                        </cpt:property>
                                        <cpt:property dateRenderAdjustDays="0" id="title" labelPosition="LEFT"
                                                      typicalLength="25">
                                            <cpt:link>
                                                <lnk:rel>urn:org.restfulobjects:rels/property</lnk:rel>
                                                <lnk:method>GET</lnk:method>
                                                <lnk:href>
                                                    http://localhost:9090/restful/objects/isis.applib.DomainObjectList/PAR-LCAAAAAAAAACFkMFOwzAMhu99iih3knFDU5uJy6QhEIeNBwip1WVKnFJ7g709jspgggM5Ob9_f7_ldvWRkzrBRLFgp2_NQivAUPqIQ6dfduubO62IPfY-FYROn4H0yjVtisRKZpGWoeRO75nHpbWRIhk_-rAHU6bBkhTZW7Hkgto1Sl7LkRO4hXrwJ68ePQ5qy5MEElBr5-Zs9IFlred3lOb6LaDrIRc_jqYv2fB5BDIHYSRBmAqrrF2VnwCPrf0zfk3d9I7m1ItRlGvD_TQcMyCTuxh-lNkHCeq3Jv633VfU95bbS_RvxkwurwcITFYObeulXfMJyMCkw6kBAAA=/properties/title
                                                </lnk:href>
                                                <lnk:type>
                                                    application/json;profile="urn:org.restfulobjects:repr-types/object-property"
                                                </lnk:type>
                                            </cpt:link>
                                        </cpt:property>
                                    </cpt:fieldSet>
                                </bs:col>
                            </bs:row>
                        </bs:tab>
                        <bs:tab name="Metadata">
                            <bs:row>
                                <bs:col span="12">
                                    <cpt:fieldSet name="Metadata" id="metadata">
                                        <cpt:action id="downloadLayoutXml" position="PANEL_DROPDOWN">
                                            <cpt:link>
                                                <lnk:rel>urn:org.restfulobjects:rels/action</lnk:rel>
                                                <lnk:method>GET</lnk:method>
                                                <lnk:href>
                                                    http://localhost:9090/restful/objects/isis.applib.DomainObjectList/PAR-LCAAAAAAAAACFkMFOwzAMhu99iih3knFDU5uJy6QhEIeNBwip1WVKnFJ7g709jspgggM5Ob9_f7_ldvWRkzrBRLFgp2_NQivAUPqIQ6dfduubO62IPfY-FYROn4H0yjVtisRKZpGWoeRO75nHpbWRIhk_-rAHU6bBkhTZW7Hkgto1Sl7LkRO4hXrwJ68ePQ5qy5MEElBr5-Zs9IFlred3lOb6LaDrIRc_jqYv2fB5BDIHYSRBmAqrrF2VnwCPrf0zfk3d9I7m1ItRlGvD_TQcMyCTuxh-lNkHCeq3Jv633VfU95bbS_RvxkwurwcITFYObeulXfMJyMCkw6kBAAA=/actions/downloadLayoutXml
                                                </lnk:href>
                                                <lnk:type>
                                                    application/json;profile="urn:org.restfulobjects:repr-types/object-action"
                                                </lnk:type>
                                            </cpt:link>
                                        </cpt:action>
                                        <cpt:action id="downloadMetamodelXml" position="PANEL_DROPDOWN">
                                            <cpt:link>
                                                <lnk:rel>urn:org.restfulobjects:rels/action</lnk:rel>
                                                <lnk:method>GET</lnk:method>
                                                <lnk:href>
                                                    http://localhost:9090/restful/objects/isis.applib.DomainObjectList/PAR-LCAAAAAAAAACFkMFOwzAMhu99iih3knFDU5uJy6QhEIeNBwip1WVKnFJ7g709jspgggM5Ob9_f7_ldvWRkzrBRLFgp2_NQivAUPqIQ6dfduubO62IPfY-FYROn4H0yjVtisRKZpGWoeRO75nHpbWRIhk_-rAHU6bBkhTZW7Hkgto1Sl7LkRO4hXrwJ68ePQ5qy5MEElBr5-Zs9IFlred3lOb6LaDrIRc_jqYv2fB5BDIHYSRBmAqrrF2VnwCPrf0zfk3d9I7m1ItRlGvD_TQcMyCTuxh-lNkHCeq3Jv633VfU95bbS_RvxkwurwcITFYObeulXfMJyMCkw6kBAAA=/actions/downloadMetamodelXml
                                                </lnk:href>
                                                <lnk:type>
                                                    application/json;profile="urn:org.restfulobjects:repr-types/object-action"
                                                </lnk:type>
                                            </cpt:link>
                                        </cpt:action>
                                        <cpt:action id="inspectMetamodel" position="PANEL_DROPDOWN">
                                            <cpt:link>
                                                <lnk:rel>urn:org.restfulobjects:rels/action</lnk:rel>
                                                <lnk:method>GET</lnk:method>
                                                <lnk:href>
                                                    http://localhost:9090/restful/objects/isis.applib.DomainObjectList/PAR-LCAAAAAAAAACFkMFOwzAMhu99iih3knFDU5uJy6QhEIeNBwip1WVKnFJ7g709jspgggM5Ob9_f7_ldvWRkzrBRLFgp2_NQivAUPqIQ6dfduubO62IPfY-FYROn4H0yjVtisRKZpGWoeRO75nHpbWRIhk_-rAHU6bBkhTZW7Hkgto1Sl7LkRO4hXrwJ68ePQ5qy5MEElBr5-Zs9IFlred3lOb6LaDrIRc_jqYv2fB5BDIHYSRBmAqrrF2VnwCPrf0zfk3d9I7m1ItRlGvD_TQcMyCTuxh-lNkHCeq3Jv633VfU95bbS_RvxkwurwcITFYObeulXfMJyMCkw6kBAAA=/actions/inspectMetamodel
                                                </lnk:href>
                                                <lnk:type>
                                                    application/json;profile="urn:org.restfulobjects:repr-types/object-action"
                                                </lnk:type>
                                            </cpt:link>
                                        </cpt:action>
                                        <cpt:action id="openRestApi" position="PANEL_DROPDOWN">
                                            <cpt:link>
                                                <lnk:rel>urn:org.restfulobjects:rels/action</lnk:rel>
                                                <lnk:method>GET</lnk:method>
                                                <lnk:href>
                                                    http://localhost:9090/restful/objects/isis.applib.DomainObjectList/PAR-LCAAAAAAAAACFkMFOwzAMhu99iih3knFDU5uJy6QhEIeNBwip1WVKnFJ7g709jspgggM5Ob9_f7_ldvWRkzrBRLFgp2_NQivAUPqIQ6dfduubO62IPfY-FYROn4H0yjVtisRKZpGWoeRO75nHpbWRIhk_-rAHU6bBkhTZW7Hkgto1Sl7LkRO4hXrwJ68ePQ5qy5MEElBr5-Zs9IFlred3lOb6LaDrIRc_jqYv2fB5BDIHYSRBmAqrrF2VnwCPrf0zfk3d9I7m1ItRlGvD_TQcMyCTuxh-lNkHCeq3Jv633VfU95bbS_RvxkwurwcITFYObeulXfMJyMCkw6kBAAA=/actions/openRestApi
                                                </lnk:href>
                                                <lnk:type>
                                                    application/json;profile="urn:org.restfulobjects:repr-types/object-action"
                                                </lnk:type>
                                            </cpt:link>
                                        </cpt:action>
                                        <cpt:action id="rebuildMetamodel" position="PANEL">
                                            <cpt:link>
                                                <lnk:rel>urn:org.restfulobjects:rels/action</lnk:rel>
                                                <lnk:method>GET</lnk:method>
                                                <lnk:href>
                                                    http://localhost:9090/restful/objects/isis.applib.DomainObjectList/PAR-LCAAAAAAAAACFkMFOwzAMhu99iih3knFDU5uJy6QhEIeNBwip1WVKnFJ7g709jspgggM5Ob9_f7_ldvWRkzrBRLFgp2_NQivAUPqIQ6dfduubO62IPfY-FYROn4H0yjVtisRKZpGWoeRO75nHpbWRIhk_-rAHU6bBkhTZW7Hkgto1Sl7LkRO4hXrwJ68ePQ5qy5MEElBr5-Zs9IFlred3lOb6LaDrIRc_jqYv2fB5BDIHYSRBmAqrrF2VnwCPrf0zfk3d9I7m1ItRlGvD_TQcMyCTuxh-lNkHCeq3Jv633VfU95bbS_RvxkwurwcITFYObeulXfMJyMCkw6kBAAA=/actions/rebuildMetamodel
                                                </lnk:href>
                                                <lnk:type>
                                                    application/json;profile="urn:org.restfulobjects:repr-types/object-action"
                                                </lnk:type>
                                            </cpt:link>
                                        </cpt:action>
                                        <cpt:action id="impersonate" position="PANEL_DROPDOWN">
                                            <cpt:link>
                                                <lnk:rel>urn:org.restfulobjects:rels/action</lnk:rel>
                                                <lnk:method>GET</lnk:method>
                                                <lnk:href>
                                                    http://localhost:9090/restful/objects/isis.applib.DomainObjectList/PAR-LCAAAAAAAAACFkMFOwzAMhu99iih3knFDU5uJy6QhEIeNBwip1WVKnFJ7g709jspgggM5Ob9_f7_ldvWRkzrBRLFgp2_NQivAUPqIQ6dfduubO62IPfY-FYROn4H0yjVtisRKZpGWoeRO75nHpbWRIhk_-rAHU6bBkhTZW7Hkgto1Sl7LkRO4hXrwJ68ePQ5qy5MEElBr5-Zs9IFlred3lOb6LaDrIRc_jqYv2fB5BDIHYSRBmAqrrF2VnwCPrf0zfk3d9I7m1ItRlGvD_TQcMyCTuxh-lNkHCeq3Jv633VfU95bbS_RvxkwurwcITFYObeulXfMJyMCkw6kBAAA=/actions/impersonate
                                                </lnk:href>
                                                <lnk:type>
                                                    application/json;profile="urn:org.restfulobjects:repr-types/object-action"
                                                </lnk:type>
                                            </cpt:link>
                                        </cpt:action>
                                        <cpt:action id="impersonateWithRoles" position="PANEL_DROPDOWN">
                                            <cpt:link>
                                                <lnk:rel>urn:org.restfulobjects:rels/action</lnk:rel>
                                                <lnk:method>GET</lnk:method>
                                                <lnk:href>
                                                    http://localhost:9090/restful/objects/isis.applib.DomainObjectList/PAR-LCAAAAAAAAACFkMFOwzAMhu99iih3knFDU5uJy6QhEIeNBwip1WVKnFJ7g709jspgggM5Ob9_f7_ldvWRkzrBRLFgp2_NQivAUPqIQ6dfduubO62IPfY-FYROn4H0yjVtisRKZpGWoeRO75nHpbWRIhk_-rAHU6bBkhTZW7Hkgto1Sl7LkRO4hXrwJ68ePQ5qy5MEElBr5-Zs9IFlred3lOb6LaDrIRc_jqYv2fB5BDIHYSRBmAqrrF2VnwCPrf0zfk3d9I7m1ItRlGvD_TQcMyCTuxh-lNkHCeq3Jv633VfU95bbS_RvxkwurwcITFYObeulXfMJyMCkw6kBAAA=/actions/impersonateWithRoles
                                                </lnk:href>
                                                <lnk:type>
                                                    application/json;profile="urn:org.restfulobjects:repr-types/object-action"
                                                </lnk:type>
                                            </cpt:link>
                                        </cpt:action>
                                        <cpt:action id="recentCommands" position="PANEL_DROPDOWN">
                                            <cpt:link>
                                                <lnk:rel>urn:org.restfulobjects:rels/action</lnk:rel>
                                                <lnk:method>GET</lnk:method>
                                                <lnk:href>
                                                    http://localhost:9090/restful/objects/isis.applib.DomainObjectList/PAR-LCAAAAAAAAACFkMFOwzAMhu99iih3knFDU5uJy6QhEIeNBwip1WVKnFJ7g709jspgggM5Ob9_f7_ldvWRkzrBRLFgp2_NQivAUPqIQ6dfduubO62IPfY-FYROn4H0yjVtisRKZpGWoeRO75nHpbWRIhk_-rAHU6bBkhTZW7Hkgto1Sl7LkRO4hXrwJ68ePQ5qy5MEElBr5-Zs9IFlred3lOb6LaDrIRc_jqYv2fB5BDIHYSRBmAqrrF2VnwCPrf0zfk3d9I7m1ItRlGvD_TQcMyCTuxh-lNkHCeq3Jv633VfU95bbS_RvxkwurwcITFYObeulXfMJyMCkw6kBAAA=/actions/recentCommands
                                                </lnk:href>
                                                <lnk:type>
                                                    application/json;profile="urn:org.restfulobjects:repr-types/object-action"
                                                </lnk:type>
                                            </cpt:link>
                                        </cpt:action>
                                        <cpt:action id="recentExecutions" position="PANEL_DROPDOWN">
                                            <cpt:link>
                                                <lnk:rel>urn:org.restfulobjects:rels/action</lnk:rel>
                                                <lnk:method>GET</lnk:method>
                                                <lnk:href>
                                                    http://localhost:9090/restful/objects/isis.applib.DomainObjectList/PAR-LCAAAAAAAAACFkMFOwzAMhu99iih3knFDU5uJy6QhEIeNBwip1WVKnFJ7g709jspgggM5Ob9_f7_ldvWRkzrBRLFgp2_NQivAUPqIQ6dfduubO62IPfY-FYROn4H0yjVtisRKZpGWoeRO75nHpbWRIhk_-rAHU6bBkhTZW7Hkgto1Sl7LkRO4hXrwJ68ePQ5qy5MEElBr5-Zs9IFlred3lOb6LaDrIRc_jqYv2fB5BDIHYSRBmAqrrF2VnwCPrf0zfk3d9I7m1ItRlGvD_TQcMyCTuxh-lNkHCeq3Jv633VfU95bbS_RvxkwurwcITFYObeulXfMJyMCkw6kBAAA=/actions/recentExecutions
                                                </lnk:href>
                                                <lnk:type>
                                                    application/json;profile="urn:org.restfulobjects:repr-types/object-action"
                                                </lnk:type>
                                            </cpt:link>
                                        </cpt:action>
                                        <cpt:action id="recentAuditTrailEntries" position="PANEL_DROPDOWN">
                                            <cpt:link>
                                                <lnk:rel>urn:org.restfulobjects:rels/action</lnk:rel>
                                                <lnk:method>GET</lnk:method>
                                                <lnk:href>
                                                    http://localhost:9090/restful/objects/isis.applib.DomainObjectList/PAR-LCAAAAAAAAACFkMFOwzAMhu99iih3knFDU5uJy6QhEIeNBwip1WVKnFJ7g709jspgggM5Ob9_f7_ldvWRkzrBRLFgp2_NQivAUPqIQ6dfduubO62IPfY-FYROn4H0yjVtisRKZpGWoeRO75nHpbWRIhk_-rAHU6bBkhTZW7Hkgto1Sl7LkRO4hXrwJ68ePQ5qy5MEElBr5-Zs9IFlred3lOb6LaDrIRc_jqYv2fB5BDIHYSRBmAqrrF2VnwCPrf0zfk3d9I7m1ItRlGvD_TQcMyCTuxh-lNkHCeq3Jv633VfU95bbS_RvxkwurwcITFYObeulXfMJyMCkw6kBAAA=/actions/recentAuditTrailEntries
                                                </lnk:href>
                                                <lnk:type>
                                                    application/json;profile="urn:org.restfulobjects:repr-types/object-action"
                                                </lnk:type>
                                            </cpt:link>
                                        </cpt:action>
                                        <cpt:property dateRenderAdjustDays="0" hidden="ALL_TABLES" id="logicalTypeName"
                                                      typicalLength="25">
                                            <cpt:named>Logical Type Name</cpt:named>
                                            <cpt:describedAs>The logical name of this domain class (as used in
                                                persistence, URLs etc). Intended to be stable/unchanging across time
                                            </cpt:describedAs>
                                            <cpt:link>
                                                <lnk:rel>urn:org.restfulobjects:rels/property</lnk:rel>
                                                <lnk:method>GET</lnk:method>
                                                <lnk:href>
                                                    http://localhost:9090/restful/objects/isis.applib.DomainObjectList/PAR-LCAAAAAAAAACFkMFOwzAMhu99iih3knFDU5uJy6QhEIeNBwip1WVKnFJ7g709jspgggM5Ob9_f7_ldvWRkzrBRLFgp2_NQivAUPqIQ6dfduubO62IPfY-FYROn4H0yjVtisRKZpGWoeRO75nHpbWRIhk_-rAHU6bBkhTZW7Hkgto1Sl7LkRO4hXrwJ68ePQ5qy5MEElBr5-Zs9IFlred3lOb6LaDrIRc_jqYv2fB5BDIHYSRBmAqrrF2VnwCPrf0zfk3d9I7m1ItRlGvD_TQcMyCTuxh-lNkHCeq3Jv633VfU95bbS_RvxkwurwcITFYObeulXfMJyMCkw6kBAAA=/properties/logicalTypeName
                                                </lnk:href>
                                                <lnk:type>
                                                    application/json;profile="urn:org.restfulobjects:repr-types/object-property"
                                                </lnk:type>
                                            </cpt:link>
                                        </cpt:property>
                                        <cpt:property dateRenderAdjustDays="0" hidden="ALL_TABLES" id="objectIdentifier"
                                                      typicalLength="25">
                                            <cpt:named>Object Identifier</cpt:named>
                                            <cpt:describedAs>The identifier of this object instance, unique within its
                                                domain class. Combined with the 'logical type name', is a unique
                                                identifier across all domain classes.
                                            </cpt:describedAs>
                                            <cpt:link>
                                                <lnk:rel>urn:org.restfulobjects:rels/property</lnk:rel>
                                                <lnk:method>GET</lnk:method>
                                                <lnk:href>
                                                    http://localhost:9090/restful/objects/isis.applib.DomainObjectList/PAR-LCAAAAAAAAACFkMFOwzAMhu99iih3knFDU5uJy6QhEIeNBwip1WVKnFJ7g709jspgggM5Ob9_f7_ldvWRkzrBRLFgp2_NQivAUPqIQ6dfduubO62IPfY-FYROn4H0yjVtisRKZpGWoeRO75nHpbWRIhk_-rAHU6bBkhTZW7Hkgto1Sl7LkRO4hXrwJ68ePQ5qy5MEElBr5-Zs9IFlred3lOb6LaDrIRc_jqYv2fB5BDIHYSRBmAqrrF2VnwCPrf0zfk3d9I7m1ItRlGvD_TQcMyCTuxh-lNkHCeq3Jv633VfU95bbS_RvxkwurwcITFYObeulXfMJyMCkw6kBAAA=/properties/objectIdentifier
                                                </lnk:href>
                                                <lnk:type>
                                                    application/json;profile="urn:org.restfulobjects:repr-types/object-property"
                                                </lnk:type>
                                            </cpt:link>
                                        </cpt:property>
                                    </cpt:fieldSet>
                                </bs:col>
                            </bs:row>
                        </bs:tab>
                    </bs:tabGroup>
                </bs:col>
            </bs:row>
            <bs:row>
                <bs:col span="12">
                    <cpt:fieldSet name="Details" id="details"/>
                </bs:col>
            </bs:row>
        </bs:col>
        <bs:col span="8">
            <bs:tabGroup unreferencedCollections="true">
                <bs:tab name="Objects">
                    <bs:row>
                        <bs:col span="12" size="MD">
                            <cpt:collection defaultView="table" id="objects" paged="12">
                                <cpt:link>
                                    <lnk:rel>urn:org.restfulobjects:rels/collection</lnk:rel>
                                    <lnk:method>GET</lnk:method>
                                    <lnk:href>
                                        http://localhost:9090/restful/objects/isis.applib.DomainObjectList/PAR-LCAAAAAAAAACFkMFOwzAMhu99iih3knFDU5uJy6QhEIeNBwip1WVKnFJ7g709jspgggM5Ob9_f7_ldvWRkzrBRLFgp2_NQivAUPqIQ6dfduubO62IPfY-FYROn4H0yjVtisRKZpGWoeRO75nHpbWRIhk_-rAHU6bBkhTZW7Hkgto1Sl7LkRO4hXrwJ68ePQ5qy5MEElBr5-Zs9IFlred3lOb6LaDrIRc_jqYv2fB5BDIHYSRBmAqrrF2VnwCPrf0zfk3d9I7m1ItRlGvD_TQcMyCTuxh-lNkHCeq3Jv633VfU95bbS_RvxkwurwcITFYObeulXfMJyMCkw6kBAAA=/collections/objects
                                    </lnk:href>
                                    <lnk:type>
                                        application/json;profile="urn:org.restfulobjects:repr-types/object-collection"
                                    </lnk:type>
                                </cpt:link>
                            </cpt:collection>
                        </bs:col>
                    </bs:row>
                </bs:tab>
            </bs:tabGroup>
        </bs:col>
    </bs:row>
</bs:grid>
"""
}
