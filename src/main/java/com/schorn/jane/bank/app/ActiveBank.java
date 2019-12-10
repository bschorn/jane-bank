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

import org.schorn.ella.context.AppContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author bschorn
 */
public interface ActiveBank {
    static final Logger LGR = LoggerFactory.getLogger(ActiveBank.class);
    /*
     * CONSTANTS
     */
    public enum Constants {
        CONTEXT_STR(ActiveBankConfig.CONTEXT_STR.value()),
        TRANSFER_EVENT(ActiveBankConfig.TRANSFER_EVENT_TYPE.value());

        String constant;

        Constants(String constant) {
            this.constant = constant;
        }

        public String constant() {
            return this.constant;
        }
    }

    /*
     * CONTEXTS
     */
    public enum Contexts {
        ACTIVE_BANK(Constants.CONTEXT_STR.constant()),;

        AppContext context;

        Contexts(String context_str) {
            try {
                this.context = AppContext.create(context_str);
            } catch (Exception ex) {
                LGR.error("{}.{}.{} -> creation of AppContext has thrown an Exception: {}",
                        ActiveBank.class.getSimpleName(),
                        this.getClass().getSimpleName(),
                        this.name(), ex.getMessage());
            }
        }

        public AppContext context() {
            return this.context;
        }
    }

}
