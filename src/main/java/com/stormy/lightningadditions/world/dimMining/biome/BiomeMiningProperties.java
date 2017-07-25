package com.stormy.lightningadditions.world.dimMining.biome;

import net.minecraft.world.biome.Biome;

public class BiomeMiningProperties {

    public static Biome.BiomeProperties getBiomeProperties(){
        Biome.BiomeProperties properties = new Biome.BiomeProperties("Miner's Paradise");
        properties.setRainDisabled();

        return properties;
    }

}
