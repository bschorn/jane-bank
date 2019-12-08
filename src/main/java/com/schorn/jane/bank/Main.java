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
import org.schorn.ella.ComponentProperties;
import org.schorn.ella.app.ActiveMain;
import org.schorn.ella.schema.ActiveSchemaParser;
import org.schorn.ella.util.CommandLineArgs;

/**
 *
 * @author bschorn
 */
public class Main {

    static void recompile(String[] args) throws Exception {
        CommandLineArgs cmdLineArgs = new CommandLineArgs(args);
        ComponentProperties.NODE.checkForException();
        String[] contexts = cmdLineArgs.getParameterValue("Active.Contexts").split(",");
        if (contexts != null && contexts.length > 0) {
            String context = contexts[0];
            String specFile = ComponentProperties.NODE.getProperty("Active.Spec");
            Path specFilePath = Paths.get(specFile);
            if (!Files.exists(specFilePath)) {
                specFilePath = Paths.get(ComponentProperties.NODE.getRootPath().toString(), specFile);
            }
            if (Files.exists(specFilePath)) {
                String[] metaFiles = ComponentProperties.NODE.getProperty("Active.Metas").split(",");
                if (specFile != null) {
                    for (String metaFile : metaFiles) {
                        if (metaFile.toLowerCase().contains(context.toLowerCase())) {
                            Path metaFilePath = Paths.get(metaFile);
                            if (!Files.exists(metaFilePath)) {
                                metaFilePath = Paths.get(ComponentProperties.NODE.getRootPath().toString(), metaFile);
                            }
                            if (Files.exists(metaFilePath)) {
                                ActiveSchemaParser.compile(context, specFilePath.toString(), metaFilePath.toString());
                            } else {
                                System.err.println(String.format("there is no metaFilePath"));
                            }
                        }
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        try {
            /*
                Running InterNodal
             */
            ActiveMain.Starter starter = new ActiveMain.Starter(args).create();
            recompile(args);
            starter.start();

            /*
                Connectivity
             */
            //new EllaWsApplication.Starter(args).create().start();
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
