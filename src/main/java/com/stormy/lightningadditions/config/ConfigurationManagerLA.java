/*
 *
 *  * ********************************************************************************
 *  * Copyright (c) 2017 StormyMode, MiningMark48. All Rights Reserved!
 *  * This file is part of Lightning Additions (MC-Mod).
 *  *
 *  * This project cannot be copied and/or distributed without the express
 *  * permission of StormyMode, MiningMark48 (Developers)!
 *  * ********************************************************************************
 *
 */

package com.stormy.lightningadditions.config;

import com.stormy.lightningadditions.reference.ModInformation;
import com.stormy.lightningadditions.utility.logger.LALogger;
import com.stormy.lightninglib.lib.utils.TranslateUtils;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

import java.io.File;

import static com.stormy.lightningadditions.config.ConfigCategories.*;


public class ConfigurationManagerLA
{
    public static File options_location;
    public static File world_options_location;
    public static File allowedModsLoc;

    public static boolean straight2Ingots;
    public static boolean zombiePigsAttack;
    public static boolean supportNewDims;
    public static boolean changeVanilla;
    public static boolean supportForestry;
    public static boolean supportIC;
    public static boolean zenithPhase = true;
    public static boolean nightPhase = true;

    public static int dimID = 69;
    public static int dimMiningID = 96;
    public static int biomeMiningID;
    public static int dimMiningheight;
    public static boolean dimMiningonlyDay;
    public static boolean dimMiningSpawnMobs;
    public static boolean dimMininggrassTop;

    public static boolean canTeleportDangerously;
    public static boolean canTeleportToAir;
    public static boolean canTeleportResetFallDamage;
    public static double teleportDistance;

    public static boolean addOreDictTooltip;

    public static int tickAmount;

    public static boolean creativeTabSearchBar;

    public static boolean atomicMagnetParticles;
    public static int atomicMagnetRange;
    public static float atomicMagnetPullSpeed;

    public ConfigurationManagerLA(FMLPreInitializationEvent event)
    {
        options_location = new File(ModInformation.LOCATION + "/options.cfg");
        Configuration options_config = new Configuration(options_location);
        options(options_config);

        world_options_location = new File(ModInformation.LOCATION + "/world_options.cfg");
        Configuration world_options_config = new Configuration(world_options_location);
        worldOptions(world_options_config);
    }

    private void options(Configuration config)
    {
        config.load();
        LALogger.info("Options config loaded.");

        //Category Comments
        config.addCustomCategoryComment(TranslateUtils.toLocal("config.category.atomicMagnet.title"), TranslateUtils.toLocal("config.category.atomicMagnet.desc"));

        //Creative Tab
        creativeTabSearchBar = config.getBoolean(TranslateUtils.toLocal("config.creativeTabSearchBar.title"), Configuration.CATEGORY_GENERAL, false, TranslateUtils.toLocal("config.creativeTabSearchBar.desc"));

        //Tweaks & Featuers
        addOreDictTooltip = config.get(TWEAKS.toString(), "Ore Dictionary Tooltip", false, "Should ore dictionary tooltips be added to itemstacks?").getBoolean();

        //Items & Blocks
            //TP Wand
        canTeleportDangerously = config.getBoolean("Dangerous Teleporting", "Teleport Wand", false, "If true, you may teleport inside blocks and suffocate.");
        canTeleportResetFallDamage = config.getBoolean("Negate Fall Damage", "Teleport Wand", false, "If true, teleporting will negate fall damage.");
        canTeleportToAir = config.getBoolean("Aerial Teleport", "Teleport Wand", true, "If true, the wand will teleport to an air-block (similarly to EnderIO teleportation).");
        teleportDistance = config.getFloat("Teleport Distance", "Teleport Wand", 45, 0, 250, "Extreme values may cause performance issues.");
            //Atomic Inhibitor
        tickAmount = config.getInt("Atomic Inhib. Tick Amount", Configuration.CATEGORY_GENERAL, 25, 1, Integer.MAX_VALUE, "Atomic Inhibitor: Amount of times the block is ticked.");
            //Atomic Magnet
        atomicMagnetRange = config.getInt(TranslateUtils.toLocal("config.atomicMagnetRange.title"), TranslateUtils.toLocal("config.category.atomicMagnet.title"), 9, 1, Integer.MAX_VALUE, TranslateUtils.toLocal("config.atomicMagnetRange.desc"));
        atomicMagnetPullSpeed = config.getFloat(TranslateUtils.toLocal("config.atomicMagnetPullSpeed.title"), TranslateUtils.toLocal("config.category.atomicMagnet.title"), 0.035F, 0, Float.MAX_VALUE, TranslateUtils.toLocal("config.atomicMagnetPullSpeed.desc"));
        atomicMagnetParticles = config.getBoolean(TranslateUtils.toLocal("config.atomicMagnetParticles.title"), TranslateUtils.toLocal("config.category.atomicMagnet.title"), true, TranslateUtils.toLocal("config.atomicMagnetParticles.desc"));

        //Compat
        supportForestry = config.get(COMPATIBILITY.toString(), "supportForestry", true, "Support for Forestry ores").getBoolean(true);
        supportIC = config.get(COMPATIBILITY.toString(), "supportIndustrialCraft", true, "Support for IC2 ores").getBoolean(true);

        config.save();
        LALogger.info("Options config saved.");
    }

    //Config options for world modifications such as dimensions or biomes.
    private void worldOptions(Configuration config){
        config.load();
        LALogger.info("World Options config loaded.");

        //Dimensions
            //IDs
        dimID = config.get(IDS.toString(), "Dim ID", 69, "Default Dimension ID for Void-World_LA").getInt();
        dimMiningID = config.get(IDS.toString(), "Mining Dim ID", 96, "Default Dimension ID for the Mining-World_LA").getInt();
        biomeMiningID = config.get(IDS.toString(), "Mining Biome ID", 196, "This is the ID for the Mining Biome").getInt();
            //Tweaks
        nightPhase = config.get(TWEAKS.toString(), "Night-Night Mode", false, "The void-age essentially becomes an infinite dark abyss, forever stuck at Night.").getBoolean();
        zenithPhase = config.get(TWEAKS.toString(), "Zenith-Phase Mode", true, "The void-age is forever stuck at Midday (Zenith-Phase) - @DireWolf20").getBoolean();
            //General
        dimMiningheight = config.get(GENERAL.toString(), "worldHeight", 69, "The height of the Mining World").getInt();
            //World Modifiers
        dimMiningonlyDay = config.get(WORLD_MODIFIER.toString(), "onlyDay", true, "Set to 'true' for a Mining World zenith-phase").getBoolean();
        dimMiningSpawnMobs = config.get(WORLD_MODIFIER.toString(), "spawnMobs", true, "Set to 'false' if you want mobs to only spawn from artificially from Spawners etc.").getBoolean();
            //Misc
        dimMininggrassTop = config.getBoolean("grassTop", "World Modifiers", false, "Set to 'true' if you'd like Grass blocks instead of Stone");

        //Ores & Mining
        straight2Ingots = config.get(GENERAL.toString(), "smeltToIngots", true, "Makes new ores be smelted straight to their ingot form, instead of turning into vanilla ores first.").getBoolean(true);
        zombiePigsAttack = config.get(GENERAL.toString(), "zombiePigmenAggro", true, "Zombie Pigmen will attack players who mine nether ores. Set to false to disable").getBoolean(true);
        supportNewDims = config.get(GENERAL.toString(), "customDimensions", true, "Allows custom generation in modded dimensions.").getBoolean(true);
        changeVanilla = config.get(GENERAL.toString(), "changeVanillaOreSpawn", true, "Should LA override Vanilla ore-generation?").getBoolean(true);

        config.save();
        LALogger.info("World Options config saved.");
    }

}
