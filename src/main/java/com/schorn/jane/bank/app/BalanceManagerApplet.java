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

import org.schorn.ella.event.ActiveEvent;
import org.schorn.ella.server.AbstractApplet;
import org.schorn.ella.server.ActiveServer;
import org.schorn.ella.server.ActiveServer.ContextServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author bschorn
 */
public class BalanceManagerApplet extends AbstractApplet implements ActiveServer.ContextApplet {

    private static final Logger LGR = LoggerFactory.getLogger(BalanceManagerApplet.class);

    public BalanceManagerApplet(ContextServer activeServer, Object... params) {
        super(activeServer, params);
    }

    /**
     * Member which will have the hook into the data server
     */
    private ActiveEvent.ObjectDataInjector injector = null;

    @Override
    public void init() throws Exception {

        //this.injector = new TradeDataEvent(this.context(), new TradeSummary(this.context(), new PositionSnapshot(this.context())));
    }

    /**
     * How do we get you going?
     */
    @Override
    public void start() {
        /*
	 * Start by registering the injector with event manager
         */
        ActiveEvent.DataEventManager.get(ActiveBank.Contexts.ACTIVE_BANK.context())
                .register(this.injector);

    }

    /**
     * How do I stop?
     */
    @Override
    public void stop() {
        /*
	 * Stop by unregistering the injector with event manager
         */
        ActiveEvent.DataEventManager.get(ActiveBank.Contexts.ACTIVE_BANK.context())
                .unregister(this.injector);
    }

    /**
     * How can I forcefully bring you down?
     */
    @Override
    public void kill() {
        this.stop();
    }

}
