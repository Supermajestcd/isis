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


/**
 * 
 */
package org.apache.isis.extensions.sql.objectstore;

import java.util.Properties;

import org.apache.isis.extensions.sql.objectstore.common.SqlIntegrationTestCommon;




public class PostgreSqlTest extends SqlIntegrationTestCommon {
	
	@Override
	public Properties getProperties(){
		Properties properties = new Properties();
		properties.put("isis.persistence.sql.jdbc.driver", "org.postgresql.Driver");
		properties.put("isis.persistence.sql.jdbc.connection", "jdbc:postgresql://abacus/noftest");
		properties.put("isis.persistence.sql.jdbc.user", "nof");
		properties.put("isis.persistence.sql.jdbc.password", "");
		return properties;
	}
	
	public  String getPropertiesFilename(){
		return "postgresql.properties";
	}	
}

