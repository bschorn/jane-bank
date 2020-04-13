/*
 * Copyright Brickfeet LLC
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by Bryan Schorn <bschorn@gmail.com>, December 2019
 */
package com.schorn.jane.bank.app;

import java.util.Optional;
import org.schorn.ella.context.AppContext;
import org.schorn.ella.impl.services.ServicesProviderImpl;
import org.schorn.ella.node.ActiveNode;

/**
 *
 * @author bschorn
 */
public class JaneServicesProvider extends ServicesProviderImpl {

    @Override
    public String getHTMLAppInPage(String context_str, String object_type) {
        Optional<AppContext> optAppContext = AppContext.valueOf(context_str);
        if (optAppContext.isPresent()) {
            AppContext context = optAppContext.get();
            context.getObjectType(object_type);
            ActiveNode.ObjectType.get(context, object_type);
            if (context.name().equals("jane_bank")) {
                if (object_type.equalsIgnoreCase("account")) {

                }
            }
        }
        return "";
    }

}
