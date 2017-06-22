/*
 * ********************************************************************************
 * Copyright (c) 2017 StormyMode, MiningMark48. All Rights Reserved!
 * This file is part of Lightning Additions (MC-Mod).
 *
 * This project cannot be copied and/or distributed without the express
 * permission of StormyMode, MiningMark48 (Developers)!
 * ********************************************************************************
 */

package com.stormy.lightningadditions.client.lightchunkutil;

import com.stormy.lightningadditions.reference.ModInformation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class ConfigHandler {

    public static final String CONFIG_FILENAME = "LA-Overlay.cfg";
    public static Configuration config;
    public static List<String> categories = new ArrayList<>();

    public static void init(FMLPreInitializationEvent event){
        File configFile = new File(event.getModConfigurationDirectory(), CONFIG_FILENAME);
        config = new Configuration(configFile);

        MinecraftForge.EVENT_BUS.register(new ConfigHandler());
        config.load();
        Config.getCategories(categories);
        Config.loadValues();
    }

    @SubscribeEvent
    public void onConfigChanged(ConfigChangedEvent.OnConfigChangedEvent event){
        if(!event.getModID().equals(ModInformation.MODID))
            return;
        Config.loadValues();
    }

}
