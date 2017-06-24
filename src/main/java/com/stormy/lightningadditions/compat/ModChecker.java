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
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.Optional;

public class ModChecker
{
    public static boolean check(String modid)
    {
        return Loader.isModLoaded(modid);
    }

    @Optional.Method(modid = "forestry")
    public static void checkForForestry()
    {
        if(ConfigurationManagerLA.supportForestry)
        {
            ForestryCompat.load();
        }
    }

    @Optional.Method(modid = "ic2")
    public static void checkForIC()
    {
        if(ConfigurationManagerLA.supportIC)
        {
            ICCompat.load();
        }
    }
}
