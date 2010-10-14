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


package org.apache.isis.extensions.sql.objectstore.jdbc;

import org.apache.isis.commons.debug.DebugString;
import org.apache.isis.metamodel.adapter.ObjectAdapter;
import org.apache.isis.metamodel.spec.feature.ObjectAssociation;
import org.apache.isis.metamodel.spec.feature.OneToOneAssociation;
import org.apache.isis.extensions.sql.objectstore.Results;
import org.apache.isis.extensions.sql.objectstore.Sql;
import org.apache.isis.extensions.sql.objectstore.mapping.FieldMapping;
import org.apache.isis.extensions.sql.objectstore.mapping.FieldMappingFactory;


public class JdbcObjectReferenceFieldMapping extends JdbcObjectReferenceMapping implements FieldMapping {

    public static class Factory implements FieldMappingFactory {
        public FieldMapping createFieldMapping(final ObjectAssociation field) {
            return new JdbcObjectReferenceFieldMapping(field);
        }
    }

    private final ObjectAssociation field;

    public void appendWhereClause(StringBuffer sql, ObjectAdapter object) {
        ObjectAdapter fieldValue = field.get(object);
        appendWhereClause(sql, fieldValue.getOid());
    }

    public JdbcObjectReferenceFieldMapping(ObjectAssociation field) {
        super(columnName(field), field.getSpecification());
        this.field = field;
    }

    private static String columnName(ObjectAssociation field) {
        return Sql.sqlFieldName(field.getId());
    }

    public void appendInsertValues(StringBuffer sb, ObjectAdapter object) {
        ObjectAdapter fieldValue = field.get(object);
        super.appendInsertValues(sb, fieldValue);
    }

    public void appendUpdateValues(StringBuffer sql, ObjectAdapter object) {
        ObjectAdapter fieldValue = field.get(object);
        super.appendUpdateValues(sql, fieldValue);
    }

    public void initializeField(ObjectAdapter object, Results rs) {
        ObjectAdapter reference = initializeField(rs);
        ((OneToOneAssociation) field).initAssociation(object, reference);
    }
    
    public void debugData(DebugString debug) {
        debug.appendln(field.getId(), getColumn());
    }

}

