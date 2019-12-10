/*
 * Copyright Brickfeet LLC
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by Bryan Schorn <bschorn@gmail.com>, December 2019
 */
package com.schorn.jane.bank;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.schorn.ella.app.ActiveMain;
import org.schorn.ella.node.MetaReader;
import org.schorn.ella.schema.ActiveSchemaParser;
import org.schorn.ella.util.CommandLineArgs;
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
            CommandLineArgs cmdLineArgs = new CommandLineArgs(args);
            String specFile = cmdLineArgs.getParameterValue("Spec.File");
            String context = "jane_bank";
            Path specFilePath = Paths.get(specFile);
            if (Files.exists(specFilePath)) {
                ActiveSchemaParser schemaParser = ActiveSchemaParser.compile(context, specFilePath.toString());
                MetaReader.MetaSupplier metaSupplier = new MetaReader.StringMetaSupplier(schemaParser.getMeta());
                starter.get().addContext(context, metaSupplier);
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
