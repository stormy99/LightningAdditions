/*
 * ********************************************************************************
 * Copyright (c) 2017 StormyMode, MiningMark48. All Rights Reserved!
 * This file is part of Lightning Additions (MC-Mod).
 *
 * This project cannot be copied and/or distributed without the express
 * permission of StormyMode, MiningMark48 (Developers)!
 * ********************************************************************************
 */

package com.stormy.lightningadditions.utility.logger;

import java.io.File;
import java.util.Arrays;
import java.util.HashSet;

import com.stormy.lightningadditions.reference.ModInformation;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class ConfigurationManagerLA
{
    public static File optionsLoc;
    public static File allowedModsLoc;
    public static boolean straight2Ingots;
    public static boolean zombiePigsAttack;
    public static boolean supportNewDims;
    public static boolean changeVanilla;
    public static boolean supportForestry;
    public static boolean supportIC;
    public static String genCategory = "general";
    public static String modCategory = "compatibility";
    public static String CATEGORY_ID = "IDs";
    public static String CATEGORY_TWEAK = "Tweaks";

    public static int dimID = 69;
    public static boolean zenithPhase = true;
    public static boolean nightPhase = true;

    public ConfigurationManagerLA(FMLPreInitializationEvent event)
    {
        optionsLoc = new File(ModInformation.LOCATION + "/options.cfg");
        Configuration optionsConfig = new Configuration(optionsLoc);
        options(optionsConfig);
    }

    private void options(Configuration config)
    {   config.load();
        this.dimID = config.get(CATEGORY_ID, "Dim ID", 69, "Default Dimension ID for Void-World_LA").getInt();
        this.nightPhase = config.get(CATEGORY_TWEAK, "Night-Night Mode", false, "The void-age essentially becomes an infinite dark abyss, forever stuck at Night.").getBoolean();
        this.zenithPhase = config.get(CATEGORY_TWEAK, "Zenith-Phase Mode", true, "The void-age is forever stuck at Midday (Zenith-Phase) - @DireWolf20").getBoolean();
        this.straight2Ingots = config.get(genCategory, "smeltToIngots", true, "Makes new ores be smelted straight to their ingot form, instead of turning into vanilla ores first.").getBoolean(true);
        this.zombiePigsAttack = config.get(genCategory, "zombiePigmenAggro", true, "Zombie Pigmen will attack players who mine nether ores. Set to false to disable").getBoolean(true);
        this.supportNewDims = config.get(modCategory, "customDimensions", true, "Allows custom generation in modded dimensions.").getBoolean(true);
        this.changeVanilla = config.get(modCategory, "changeVanillaOreSpawn", true, "Should LA override Vanilla ore-generation?").getBoolean(true);
        this.supportForestry = config.get(modCategory, "supportForestry", true, "Support for Forestry ores").getBoolean(true);
        this.supportIC = config.get(modCategory, "supportIndustrialCraft", true, "Support for IC2 ores").getBoolean(true);
        config.save();
    }
}
