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

package com.stormy.lightningadditions.init;

import com.stormy.lightningadditions.config.ConfigurationManagerLA;
import com.stormy.lightningadditions.utility.logger.LALogger;
import com.stormy.lightningadditions.world.dimMining.biome.BiomeMining;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.BiomeManager;

public class ModBiomes {

    public static void init(){
        Biome.registerBiome(ConfigurationManagerLA.biomeMiningID, "miningBiome", BiomeMining.biomeMining);
        BiomeManager.addBiome(BiomeManager.BiomeType.WARM, new BiomeManager.BiomeEntry(BiomeMining.biomeMining, 1));

        LALogger.log("LA Biomes registered.");
    }

}
