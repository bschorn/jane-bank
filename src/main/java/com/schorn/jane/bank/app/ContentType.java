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

/**
 *
 * @author bschorn
 */
public enum ContentType {
    JSON("application/json"),
    HTML("text/html; charset=utf-8"),
    CSV("text/csv; charset=utf-8"),
    TXT("text/txt; charset=utf-8"),;
    final String value;

    ContentType(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return this.value;
    }

    static public ContentType parse(String contentTypeStr) {
        int maxMatchCt = 0;
        ContentType closestMatch = null;
        for (ContentType contentType0 : values()) {
            if (contentType0.value.equalsIgnoreCase(contentTypeStr)) {
                return contentType0;
            }
        }
        for (ContentType contentType1 : values()) {
            int matchCount = 0;
            String myParts[] = contentType1.value.split(";");
            String theirParts[] = contentTypeStr.split(";");
            for (int i = 0; i < Math.min(myParts.length, theirParts.length); ++i) {
                if (myParts[i].equalsIgnoreCase(theirParts[i])) {
                    ++matchCount;
                }
            }
            if (matchCount > maxMatchCt) {
                maxMatchCt = matchCount;
                closestMatch = contentType1;
            }
        }
        return closestMatch;
    }
}
