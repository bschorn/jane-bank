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

import java.util.HashMap;
import java.util.Map;
import org.schorn.ella.extension.ActiveOut.OutFormat;
import org.schorn.ella.html.ActiveHtml.HtmlElement;
import org.schorn.ella.node.ActiveNode.ArrayData;
import org.schorn.ella.node.ActiveNode.StructData;
import org.schorn.ella.repo.RepoSupport;
import org.schorn.ella.services.ContentFormatter;

/**
 *
 * @author bschorn
 */
public class CustomFormatter implements ContentFormatter {

    private final Map<Long, Exception> exception = new HashMap<>();

    @Override
    public Object format(Object content_type, Object content_obj, Object content_query) {
        if (content_type != null && content_type instanceof String) {
            ContentType contentType = ContentType.valueOf((String) content_type);
            switch (contentType) {
                case JSON:
                    return formatJSON(content_obj);
                case HTML:
                    return formatHTML(content_obj);
                case CSV:
                    return formatCSV(content_obj);
                case TXT:
                    return formatCSV(content_obj); // same as CSV (but ContentType will be 'text/txt')
            }
        }
        return null;
    }

    /**
     *
     * @param content_obj
     * @return
     */
    Object formatJSON(Object content_obj) {
        if (content_obj instanceof RepoSupport.QueryData) {
            RepoSupport.QueryData queryData = (RepoSupport.QueryData) content_obj;
            ArrayData arrayData = queryData.arrayData();
            return arrayData.asJsonString();
        }
        if (content_obj instanceof StructData) {
            StructData structData = (StructData) content_obj;
            return structData.asJsonString();
        }
        return "{}";

    }

    Object formatHTML(Object content_obj) {
        if (content_obj instanceof ArrayData) {
            ArrayData arrayData = (ArrayData) content_obj;
            HtmlElement htmlElement = arrayData.htmlTable();
            try {
                return htmlElement.render();
            } catch (Exception e) {
                this.setException(e);
            }
        }
        return "";
    }

    Object formatCSV(Object content_obj) {
        if (content_obj instanceof ArrayData) {
            ArrayData arrayData = (ArrayData) content_obj;
            return arrayData.activeOut(OutFormat.CSV);
        }
        return "";
    }

    @Override
    public void throwException() throws Exception {
        Exception exception = this.exception.get(Thread.currentThread().getId());
        if (exception != null) {
            throw exception;
        }
    }

    protected void setException(Exception exception) {
        this.exception.put(Thread.currentThread().getId(), exception);
    }

    protected Exception getException() {
        Exception exception = this.exception.get(Thread.currentThread().getId());
        if (exception != null) {
            return exception;
        }
        return null;
    }
}
