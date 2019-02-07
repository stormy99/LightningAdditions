package com.stormy.lightningadditions.config;

public class ConfigValues {

    //Client
    public static boolean getShowPreviewRender() {
        return ConfigurationHandler.showPreviewRender;
    }

    public static int getPreviewRenderTime() {
        return ConfigurationHandler.previewRenderTime;
    }

    //Compressed Base
    public static boolean getDoBlockCheck() {
        return ConfigurationHandler.doBlockCheck;
    }

    public static boolean getDoLoot() {
        return ConfigurationHandler.doLoot;
    }

}