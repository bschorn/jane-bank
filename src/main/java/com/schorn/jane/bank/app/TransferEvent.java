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

import java.util.function.Consumer;
import org.schorn.ella.FunctionalException;
import org.schorn.ella.context.AbstractContextual;
import org.schorn.ella.context.AppContext;
import org.schorn.ella.event.ActiveEvent;
import org.schorn.ella.event.ActiveEvent.ObjectDataInjector;
import org.schorn.ella.node.ActiveNode.ObjectData;
import org.schorn.ella.node.ActiveNode.ObjectType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author bschorn
 */
public class TransferEvent extends AbstractContextual implements ObjectDataInjector {

    private static final Logger LGR = LoggerFactory.getLogger(TransferEvent.class);
    private final ObjectType aggregateType;
    private final Consumer<ObjectData> processor;

    /**
     *
     * @param context
     * @param processor
     */
    protected TransferEvent(AppContext context, Consumer<ObjectData> processor) {
        super(context);
        this.aggregateType = ObjectType.get(this.context(), ActiveBank.Constants.TRANSFER_EVENT.constant());
        this.processor = processor;
    }

    @Override
    public boolean test(ObjectDataEvent event) {
        if (event.flag().equals(ActiveEvent.EventFlag.REPO_CHANGE_EVENT)) {
            if (event.data().context().equals(this.context())) {
                if (event.data().activeType().equals(this.aggregateType)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * hand-off to a processor
     * @param objectData
     */
    @Override
    public void accept(ObjectData objectData) {
        this.processor.accept(objectData);

        if (this.processor instanceof FunctionalException) {
            try {
                ((FunctionalException) this.processor).throwException();
            } catch (Exception ex) {
                LGR.error("{}.accept() - {}", this.getClass().getSimpleName(),
                        ex.getMessage(),
                        objectData.toString());

            }
        }
    }

}
