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

import com.stormy.lightningadditions.utility.logger.ConfigurationManagerLA;
import com.stormy.lightningadditions.utility.logger.LALogger;
import forestry.core.config.Config;

public class ForestryCompat
{
    public static void load()
    {
        fixConfig();
        loadOre();
        LALogger.log("Forestry Compatibility Enabled!");
    }

    public static void fixConfig()
    {
        if(ConfigurationManagerLA.supportForestry)
        {
            Config.generateCopperOre = false;
            Config.generateTinOre = false;
        }
    }

    public static void loadOre()
    {
        if(ConfigurationManagerLA.supportForestry)
        {
            LAModConstants.copperOre = true;
            LAModConstants.tinOre = true;
        }
    }
}
