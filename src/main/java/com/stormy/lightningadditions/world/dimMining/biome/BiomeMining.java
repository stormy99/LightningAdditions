package com.stormy.lightningadditions.world.dimMining.biome;

import net.minecraft.block.BlockSilverfish;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraft.world.gen.feature.WorldGenerator;

import java.util.Random;

public class BiomeMining extends Biome{

    public static BiomeMining biomeMining = new BiomeMining(BiomeMiningProperties.getBiomeProperties());

    private final WorldGenerator theWorldGenerator = new WorldGenMinable(Blocks.MONSTER_EGG.getDefaultState().withProperty(BlockSilverfish.VARIANT, BlockSilverfish.EnumType.STONE), 9);

    public BiomeMining(BiomeProperties properties) {
        super(properties);
    }

    public void decorate(World worldIn, Random rand, BlockPos pos)
    {
        super.decorate(worldIn, rand, pos);
        net.minecraftforge.common.MinecraftForge.ORE_GEN_BUS.post(new net.minecraftforge.event.terraingen.OreGenEvent.Pre(worldIn, rand, pos));

        for (int i = 0; i < 7; ++i)
        {
            int j1 = rand.nextInt(16);
            int k1 = rand.nextInt(64);
            int l1 = rand.nextInt(16);
            if (net.minecraftforge.event.terraingen.TerrainGen.generateOre(worldIn, rand, theWorldGenerator, pos.add(j1, k1, l1), net.minecraftforge.event.terraingen.OreGenEvent.GenerateMinable.EventType.SILVERFISH))
                this.theWorldGenerator.generate(worldIn, rand, pos.add(j1, k1, l1));
        }
        net.minecraftforge.common.MinecraftForge.ORE_GEN_BUS.post(new net.minecraftforge.event.terraingen.OreGenEvent.Post(worldIn, rand, pos));
    }

}
