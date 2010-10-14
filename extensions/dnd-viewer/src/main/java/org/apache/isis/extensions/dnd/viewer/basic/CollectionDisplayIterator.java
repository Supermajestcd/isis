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


package org.apache.isis.extensions.dnd.viewer.basic;

import java.util.Enumeration;


public interface CollectionDisplayIterator {

    /**
     * Return cache to be viewed on current page
     */
    public Enumeration displayElements();

    /**
     * Position cursor at first element
     */
    public void first();

    public int getDisplaySize();

    /**
     * If true there is a next page to display, and 'next' and 'last' options are valid
     */
    public boolean hasNext();

    public boolean hasPrevious();

    /**
     * Position cursor at last
     */
    public void last();

    /**
     * Position cursor at beginning of next page
     */
    public void next();

    public int position();

    /**
     * Position cursor at beginning of previous page
     */
    public void previous();
}
