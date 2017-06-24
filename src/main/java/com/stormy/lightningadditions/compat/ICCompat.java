/*
 * ********************************************************************************
 * Copyright (c) 2017 StormyMode, MiningMark48. All Rights Reserved!
 * This file is part of Lightning Additions (MC-Mod).
 *
 * This project cannot be copied and/or distributed without the express
 * permission of StormyMode, MiningMark48 (Developers)!
 * ********************************************************************************
 */

package com.stormy.lightningadditions.compat;


import com.stormy.lightningadditions.block.ore.SmeltingRecipes;
import com.stormy.lightningadditions.utility.logger.ConfigurationManagerLA;
import com.stormy.lightningadditions.utility.logger.LALogger;
import ic2.core.init.MainConfig;
import ic2.core.util.Config;

public class ICCompat
{
    public static void load()
    {
        fixConfig();
        loadOre();
        addRecipes();
        LALogger.log("IC2 Compatibility Enabled!");
    }

    public static void fixConfig()
    {
        if(ConfigurationManagerLA.supportIC)
        {
            Config config = MainConfig.get();
            config.set("worldgen/copperOre", false);
            config.set("worldgen/tinOre", false);
            config.set("worldgen/uraniumOre", false);
            config.set("worldgen/leadOre", false);
        }
    }

    public static void loadOre()
    {
        if(ConfigurationManagerLA.supportIC)
        {
            LAModConstants.copperOre = true;
            LAModConstants.tinOre = true;
            LAModConstants.leadOre = true;
        }
    }

    public static void addRecipes()
    {
        SmeltingRecipes.addMaceratorRecipes();
    }
}
