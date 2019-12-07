/*
 * Copyright Brickfeet LLC
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by Bryan Schorn <bschorn@gmail.com>, December 2019
 */
package com.schorn.jane.bank;

import org.schorn.ella.ComponentProperties;
import org.schorn.ella.app.ActiveMain;
import org.schorn.ella.app.NodeConfig;
import org.schorn.ella.schema.ActiveSchemaParser;
import org.schorn.ella.ws.EllaWsApplication;

/**
 *
 * @author bschorn
 */
public class Main {

    public static void main(String[] args) {
        try {
            /*
                Running InterNodal
             */
            ActiveMain.Starter starter = new ActiveMain.Starter(args).create();

            String context = "jane_bank";
            String specFile = ComponentProperties.NODE.getProperty("ActiveSpec", null);
            String[] metaFiles = NodeConfig.ACTIVE_METAS.values(",");
            if (specFile != null) {
                ActiveSchemaParser.compile(context, specFile, metaFiles[0]);
            }
            starter.start();

            /*
                Connectivity
             */
            new EllaWsApplication.Starter(args).create().start();
            /* ---- alternatively -----
            Starter starter = new Starter(args).create();
            // Do something else first
            starter.start();
             */
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}
