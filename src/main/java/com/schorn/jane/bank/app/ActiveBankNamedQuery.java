/*
 * The MIT License
 *
 * Copyright 2019 bschorn.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package com.schorn.jane.bank.app;

import java.util.List;
import org.schorn.ella.FunctionalException;
import org.schorn.ella.context.AppContext;
import org.schorn.ella.node.ActiveNode.ObjectType;
import org.schorn.ella.node.ActiveNode.ValueType;
import org.schorn.ella.node.ActiveNode.ValueTypeMember;
import org.schorn.ella.repo.RepoSupport.ActiveQuery;
import org.schorn.ella.repo.RepoSupport.ActiveQuery.QueryFlag;
import org.schorn.ella.services.NamedQuery;

/**
 *
 * @author bschorn
 */
enum ActiveBankNamedQuery implements NamedQuery, FunctionalException {

    ;

    private NamedQuery namedQuery;

    ActiveBankNamedQuery(AppContext context,
            String description,
            ValueTypeMember[] selectValueTypeMembers,
            ObjectType[] aggregateFromType,
            QueryFlag[] queryFlags,
            ValueType[] orderBy,
            String[] options,
            String[] filters) {
        try {
            this.namedQuery = NamedQuery.create(context,
                    this.name(),
                    description,
                    selectValueTypeMembers,
                    aggregateFromType,
                    null,
                    queryFlags,
                    orderBy,
                    options,
                    filters);
        } catch (Exception ex) {
            ex.printStackTrace();
            System.exit(501);
        }
    }

    ActiveBankNamedQuery(String context_str,
            String description,
            String[] select_types,
            String[] from_types,
            String[] to_type,
            String[] query_flags,
            String[] order_by_value_types,
            String[] options,
            String[] filters) {

        try {
            this.namedQuery = NamedQuery.create(
                    context_str,
                    this.name(),
                    description,
                    select_types,
                    from_types,
                    to_type,
                    query_flags,
                    order_by_value_types,
                    options,
                    filters);
        } catch (Exception ex) {
            ex.printStackTrace();
            System.exit(501);
        }
    }

    @Override
    public void throwException() throws Exception {
        this.namedQuery.throwException();
    }
    /**
     * This actually returns a ditto() copy of the original builder. The ditto()
     * copy will not copy the where()
     */
    @Override
    public ActiveQuery.Builder builder() {
        return this.namedQuery.builder().ditto();
    }

    @Override
    public String toString() {
        return this.namedQuery.toString();
    }

    @Override
    public ObjectType createIntoType(AppContext context, ValueTypeMember[] selectValueTypeMembers,
            ObjectType fromType) throws Exception {
        return this.namedQuery.createIntoType(context, selectValueTypeMembers, fromType);
    }

    @Override
    public String queryContext() {
        return this.namedQuery.queryContext();
    }

    @Override
    public String queryName() {
        return this.namedQuery.queryName();
    }

    @Override
    public String queryDescription() {
        return this.namedQuery.queryDescription();
    }

    @Override
    public List<String> selectFields() {
        return this.namedQuery.selectFields();
    }

    @Override
    public List<String> queryOptions() {
        return this.namedQuery.queryOptions();
    }

    @Override
    public List<String> queryFilters() {
        return this.namedQuery.queryFilters();
    }

}
