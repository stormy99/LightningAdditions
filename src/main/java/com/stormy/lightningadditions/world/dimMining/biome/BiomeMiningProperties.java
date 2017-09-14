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

package com.stormy.lightningadditions.world.dimMining.biome;

import net.minecraft.world.biome.Biome;

public class BiomeMiningProperties {

    public static Biome.BiomeProperties getBiomeProperties(){
        Biome.BiomeProperties properties = new Biome.BiomeProperties("Miner's Paradise");
        properties.setRainDisabled();

        return properties;
    }

}
