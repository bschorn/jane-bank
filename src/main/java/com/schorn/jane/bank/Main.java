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
            CommandLineArgs cmdLineArgs = new CommandLineArgs(args);
            ActiveMain.Starter starter = new ActiveMain.Starter(args).create();
            String specFile = cmdLineArgs.getParameterValue("Spec.File");
            String context = "jane_bank";
            Path specFilePath = Paths.get(specFile);
            if (Files.exists(specFilePath)) {
                ActiveSchemaParser schemaParser = ActiveSchemaParser.compile(context, specFilePath.toString());
                MetaReader.MetaSupplier metaSupplier = new MetaReader.StringMetaSupplier(schemaParser.getMeta());
                starter.get().addContext(context, metaSupplier);
            }
            starter.start();

            //System.setProperty("spring.profiles.active", cmdLineArgs.getParameterValue("Active.Environment"));
            System.setProperty("server.port", "3158");

            /*
                Connectivity
            */
            new EllaWsApplication.Starter(args).create().start();
            /* ---- alternatively ----- 
            EllaWsApplication.Starter wsStarter = new EllaWsApplication.Starter(args);
            // Do something else first
            wsStarter.create();
            wsStarter.start();
            */
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}
