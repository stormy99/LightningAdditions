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

import com.stormy.lightningadditions.reference.ModInformation;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

import java.io.File;


public class ConfigurationManagerLA
{
    public static File optionsLoc;
    public static File allowedModsLoc;

    public static String genCategory = "general";
    public static String modCategory = "compatibility";
    public static String CATEGORY_ID = "IDs";
    public static String CATEGORY_TWEAK = "Tweaks";
    public static final String CATEGORY_WORLD_MODIFIER = "World Modifiers";


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

    public ConfigurationManagerLA(FMLPreInitializationEvent event)
    {
        optionsLoc = new File(ModInformation.LOCATION + "/options.cfg");
        Configuration optionsConfig = new Configuration(optionsLoc);
        options(optionsConfig);
    }

    private void options(Configuration config)
    {   config.load();
        dimID = config.get(CATEGORY_ID, "Dim ID", 69, "Default Dimension ID for Void-World_LA").getInt();
        nightPhase = config.get(CATEGORY_TWEAK, "Night-Night Mode", false, "The void-age essentially becomes an infinite dark abyss, forever stuck at Night.").getBoolean();
        zenithPhase = config.get(CATEGORY_TWEAK, "Zenith-Phase Mode", true, "The void-age is forever stuck at Midday (Zenith-Phase) - @DireWolf20").getBoolean();
        dimMiningID = config.get(CATEGORY_ID, "Mining Dim ID", 96, "Default Dimension ID for the Mining-World_LA").getInt();
        dimMiningheight = config.get(genCategory, "worldHeight", 69, "The height of the Mining World").getInt();
        dimMiningonlyDay = config.get(CATEGORY_WORLD_MODIFIER, "onlyDay", true, "Set to 'true' for a Mining World zenith-phase").getBoolean();
        dimMiningSpawnMobs = config.get(CATEGORY_WORLD_MODIFIER, "spawnMobs", true, "Set to 'false' if you want mobs to only spawn from artificially from Spawners etc.").getBoolean();
        biomeMiningID = config.get(CATEGORY_ID, "Mining Biome ID", 196, "This is the ID for the Mining Biome").getInt();
        dimMininggrassTop = config.getBoolean("grassTop", "World Modifiers", false, "Set to 'true' if you'd like Grass blocks instead of Stone");

        canTeleportDangerously = config.getBoolean("Dangerous Teleporting", "Teleport Wand", false, "If true, you may teleport inside blocks and suffocate.");
        canTeleportResetFallDamage = config.getBoolean("Negate Fall Damage", "Teleport Wand", false, "If true, teleporting will negate fall damage.");
        canTeleportToAir = config.getBoolean("Aerial Teleport", "Teleport Wand", true, "If true, the wand will teleport to an air-block (similarly to EnderIO teleportation).");
        teleportDistance = config.getFloat("Teleport Distance", "Teleport Wand", 45, 0, 250, "Extreme values may cause performance issues.");



        straight2Ingots = config.get(genCategory, "smeltToIngots", true, "Makes new ores be smelted straight to their ingot form, instead of turning into vanilla ores first.").getBoolean(true);
        zombiePigsAttack = config.get(genCategory, "zombiePigmenAggro", true, "Zombie Pigmen will attack players who mine nether ores. Set to false to disable").getBoolean(true);
        supportNewDims = config.get(modCategory, "customDimensions", true, "Allows custom generation in modded dimensions.").getBoolean(true);
        changeVanilla = config.get(modCategory, "changeVanillaOreSpawn", true, "Should LA override Vanilla ore-generation?").getBoolean(true);
        supportForestry = config.get(modCategory, "supportForestry", true, "Support for Forestry ores").getBoolean(true);
        supportIC = config.get(modCategory, "supportIndustrialCraft", true, "Support for IC2 ores").getBoolean(true);
        config.save();
    }
}
