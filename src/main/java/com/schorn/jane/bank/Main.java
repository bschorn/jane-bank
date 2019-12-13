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

    private final String[] args;
    private final CommandLineArgs cmdLineArgs;
    private final String context = "jane_bank";
    private final String specFile;
    private final String metaFile;
    private final boolean createMeta;
    private final boolean loadMeta;
    private final boolean useSpring;
    private ActiveMain.Starter starter;

    public Main(String[] args) {
        this.args = args;
        this.cmdLineArgs = new CommandLineArgs(args);
        this.specFile = this.cmdLineArgs.getParameterValue("Spec.File");
        this.metaFile = this.cmdLineArgs.getParameterValue("Meta.File");
        this.createMeta = this.cmdLineArgs.hasParameterFlag("Create.Meta");
        this.loadMeta = this.cmdLineArgs.hasParameterFlag("Load.Meta");
        this.useSpring = this.cmdLineArgs.hasParameterFlag("Use.Spring");
    }

    public void createMeta() throws Exception {
        Path specFilePath = Paths.get(specFile);
        if (Files.exists(specFilePath)) {
            ActiveSchemaParser schemaParser = ActiveSchemaParser.compile(this.context, specFilePath.toString());
            Path metaFilePath = Paths.get(metaFile);
            Files.write(metaFilePath, schemaParser.getMeta().getBytes());
        }
    }

    public void loadMeta() throws Exception {
        if (this.loadMeta) {
            Path specFilePath = Paths.get(specFile);
            if (Files.exists(specFilePath)) {
                ActiveSchemaParser schemaParser = ActiveSchemaParser.compile(this.context, specFilePath.toString());
                MetaReader.MetaSupplier metaSupplier = new MetaReader.StringMetaSupplier(schemaParser.getMeta());
                this.starter.get().addContext(this.context, metaSupplier);
            }
        }
    }

    public void start() throws Exception {
        if (this.createMeta) {
            this.createMeta();
        } else {
            this.starter = new ActiveMain.Starter(args).create();
            if (this.loadMeta) {
                this.loadMeta();
            }
            this.starter.start();

            if (this.useSpring) {
                new EllaWsApplication.Starter(args).create().start();
            }
        }
    }

    public static void main(String[] args) {
        try {
            Main main = new Main(args);
            main.start();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}
