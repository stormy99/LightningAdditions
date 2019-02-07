package com.stormy.lightningadditions.config;


import com.stormy.lightningadditions.reference.ModInformation;
import com.stormy.lightninglib.lib.utils.TranslateUtils;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.io.File;

public class ConfigurationHandler {

    public static Configuration configuration;

    //Client
    static boolean showPreviewRender;
    static int previewRenderTime;

    //Compressed Base
    static boolean doBlockCheck;
    static boolean doLoot;

    public static void init(File configFile){

        //Create the configuration object from the given configuration file
        if (configuration == null){
            configuration = new Configuration(configFile);
            loadConfiguration();
        }

    }

    @SubscribeEvent
    public void onConfigurationChangedEvent(ConfigChangedEvent.OnConfigChangedEvent event){

        if (event.getModID().equalsIgnoreCase(ModInformation.MODID)){
            loadConfiguration();
        }

    }

    private static void loadConfiguration(){

        //Categories
        configuration.addCustomCategoryComment(TranslateUtils.toLocal("config.category.compressedBase.title"), TranslateUtils.toLocal("config.category.compressedBase.desc"));

        //Client
        showPreviewRender = configuration.getBoolean(TranslateUtils.toLocal("config.showPreviewRender.title"), Configuration.CATEGORY_CLIENT, true, TranslateUtils.toLocal("config.showPreviewRender.desc"));
        previewRenderTime = configuration.getInt(TranslateUtils.toLocal("config.previewRenderTime.title"), Configuration.CATEGORY_CLIENT, 10, 1, 60, TranslateUtils.toLocal("config.previewRenderTime.desc"));

        //Compressed Base
        doBlockCheck = configuration.getBoolean(TranslateUtils.toLocal("config.doBlockCheck.title"), TranslateUtils.toLocal("config.category.compressedBase.title"), true, TranslateUtils.toLocal("config.doBlockCheck.desc"));
        doLoot = configuration.getBoolean(TranslateUtils.toLocal("config.doLoot.title"), TranslateUtils.toLocal("config.category.compressedBase.title"), true, TranslateUtils.toLocal("config.doLoot.desc"));

        if (configuration.hasChanged()){
            configuration.save();
        }

    }

}
