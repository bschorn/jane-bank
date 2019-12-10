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

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.schorn.ella.context.AppContext;
import org.schorn.ella.server.ActiveServer;
import org.schorn.ella.server.ActiveServer.ContextApplet;
import org.schorn.ella.server.ActiveServer.ContextServer;
import org.schorn.ella.services.ContentAPI;
import org.schorn.ella.services.ContentFormatter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author bschorn
 */
public class ActiveBankServer implements ContextServer {

    private static final Logger LGR = LoggerFactory.getLogger(ActiveBankServer.class);

    private final List<ContextApplet> applets;
    private final ContentAPI contentAPI;

    public ActiveBankServer() {
        this.applets = new ArrayList<>();
        this.contentAPI = new ActiveBankContentAPI(this);

    }

    @Override
    public void init() throws Exception {
        /*
         * Position Management
         */
        applets.add(new BalanceManagerApplet(this));
    }

    @Override
    public void postInit() throws Exception {

    }

    @Override
    public AppContext context() {
        return ActiveBank.Contexts.ACTIVE_BANK.context();
    }

    @Override
    public String name() {
        return this.getClass().getSimpleName();
    }

    @Override
    public List<ActiveServer.ContextApplet> applets() {
        return Collections.unmodifiableList(this.applets);
    }

    @Override
    public Object getProperty(Object propertyKey) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ContentAPI contentAPI() {
        return this.contentAPI;
    }

    @Override
    public ContentFormatter contentFormatter() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
