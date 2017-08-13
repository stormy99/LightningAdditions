/*
 * ********************************************************************************
 * Copyright (c) 2017 StormyMode, MiningMark48. All Rights Reserved!
 * This file is part of Lightning Additions (MC-Mod).
 *
 * This project cannot be copied and/or distributed without the express
 * permission of StormyMode, MiningMark48 (Developers)!
 * ********************************************************************************
 */

package com.stormy.lightningadditions.config;

import com.stormy.lightningadditions.reference.ModInformation;
import com.stormy.lightninglib.lib.utils.TranslateUtils;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.io.File;

public class ConfigurationHandler
{
    public static Configuration configuration;

    public static int tickAmount;
    public static int atomicMagnetRange;

    public static boolean creativeTabSearchBar;
    public static boolean atomicMagnetParticles;

    public static float atomicMagnetPullSpeed;



    public static void init(File configFile)
    {
        if (configuration == null)
        {
            configuration = new Configuration(configFile);
            loadConfiguration();
        }
    }

    @SubscribeEvent
    public void onConfigurationChangedEvent(ConfigChangedEvent.OnConfigChangedEvent event){

        if (event.getModID().equalsIgnoreCase(ModInformation.MODID))
        {
            loadConfiguration();
        }
    }


    private static void loadConfiguration()
    {
        creativeTabSearchBar = configuration.getBoolean(TranslateUtils.toLocal("config.creativeTabSearchBar.title"), Configuration.CATEGORY_GENERAL, false, TranslateUtils.toLocal("config.creativeTabSearchBar.desc"));
        tickAmount = configuration.getInt("Tick Amount", Configuration.CATEGORY_GENERAL, 25, 1, Integer.MAX_VALUE, "Amount of times the block is ticked.");

        configuration.addCustomCategoryComment(TranslateUtils.toLocal("config.category.atomicMagnet.title"), TranslateUtils.toLocal("config.category.atomicMagnet.desc"));

        //Atomic Magnet
        atomicMagnetRange = configuration.getInt(TranslateUtils.toLocal("config.pearcelMagnetRange.title"), TranslateUtils.toLocal("config.category.pearcelMagnet.title"), 9, 1, Integer.MAX_VALUE, TranslateUtils.toLocal("config.pearcelMagnetRange.desc"));
        atomicMagnetPullSpeed = configuration.getFloat(TranslateUtils.toLocal("config.pearcelMagnetPullSpeed.title"), TranslateUtils.toLocal("config.category.pearcelMagnet.title"), 0.035F, 0, Float.MAX_VALUE, TranslateUtils.toLocal("config.pearcelMagnetPullSpeed.desc"));
        atomicMagnetParticles = configuration.getBoolean(TranslateUtils.toLocal("config.pearcelMagnetParticles.title"), TranslateUtils.toLocal("config.category.pearcelMagnet.title"), true, TranslateUtils.toLocal("config.pearcelMagnetParticles.desc"));



    }

}
