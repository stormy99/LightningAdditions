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

package com.stormy.lightningadditions.world.dimMining;

import com.stormy.lightningadditions.world.dimMining.biome.BiomeMining;
import com.stormy.lightningadditions.world.dimMining.biome.BiomeMiningDecorator;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.init.Biomes;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.ChunkPrimer;
import net.minecraft.world.gen.IChunkGenerator;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Random;

public class ChunkGeneratorMining implements IChunkGenerator
{
    private final World world;
    private final ChunkPrimer primer;
    private final Random rng;
    private final BlockPos decoratePos = new BlockPos(BlockPos.ORIGIN);
    private static final byte biomeId = (byte)Biome.getIdForBiome(BiomeMining.biomeMining);

    private BiomeMiningDecorator biomeMiningDecorator;

    public ChunkGeneratorMining(World world, long seed, boolean featuresEnabled)
    {
        this.world = world;
        this.rng = new Random(seed);
        this.primer = new ChunkPrimer();
        this.biomeMiningDecorator = new BiomeMiningDecorator();

        IBlockState stone = Blocks.STONE.getDefaultState();
        IBlockState dirt = Blocks.DIRT.getDefaultState();
        IBlockState bedrock = Blocks.BEDROCK.getDefaultState();
        IBlockState grass = Blocks.GRASS.getDefaultState();

        int x = 0;int y = 0;int z = 0;
        for (y = 0; y < 5; y++) {
            for (x = 0; x < 16; x++) {
                for (z = 0; z < 16; z++) {
                    this.primer.setBlockState(x, y, z, bedrock);
                }
            }
        }
        for (y = 5; y < 61; y++) {
            for (x = 0; x < 16; x++) {
                for (z = 0; z < 16; z++) {
                    this.primer.setBlockState(x, y, z, stone);
                }
            }
        }
        for (y = 61; y < 63; y++) {
            for (x = 0; x < 16; x++) {
                for (z = 0; z < 16; z++) {
                    this.primer.setBlockState(x, y, z, dirt);
                }
            }
        }
        for (y = 63; y < 64; y++) {
            for (x = 0; x < 16; x++) {
                for (z = 0; z < 16; z++) {
                    this.primer.setBlockState(x, y, z, grass);
                }
            }
        }
    }

    public Chunk provideChunk(int x, int z)
    {
        Chunk chunk = new Chunk(this.world, this.primer, x, z);
        byte[] biomes = chunk.getBiomeArray();
        int len = biomes.length;
        for (int i = 0; i < len; i++) {
            biomes[i] = biomeId;
        }
        chunk.generateSkylightMap();
        return chunk;
    }

    @Override
    public Chunk generateChunk(int x, int z) {
        return null;
    }

    public void populate(int x, int z)
    {
        net.minecraft.block.BlockFalling.fallInstantly = true;
        this.rng.setSeed(this.world.getSeed());
        long i1 = this.rng.nextLong() / 2L * 2L + 1L;
        long j1 = this.rng.nextLong() / 2L * 2L + 1L;
        this.rng.setSeed(x * i1 + z * j1 ^ this.world.getSeed());

        biomeMiningDecorator.decorate(BiomeMining.biomeMining, this.world, this.rng, new BlockPos(x * 16, 0, z * 16));
        net.minecraft.block.BlockFalling.fallInstantly = false;
    }

    public boolean generateStructures(Chunk chunkIn, int x, int z) {
        return true;
    }

    public List<Biome.SpawnListEntry> getPossibleCreatures(EnumCreatureType creatureType, BlockPos pos)    {
        return Biomes.EXTREME_HILLS.getSpawnableList(creatureType);
    }

    @Nullable
    @Override
    public BlockPos getNearestStructurePos(World worldIn, String structureName, BlockPos position, boolean findUnexplored) {
        return null;
    }

    public BlockPos getStrongholdGen(World worldIn, String structureName, BlockPos position, boolean flag) {return null;}
    public void recreateStructures(Chunk chunkIn, int x, int z) {}

    @Override
    public boolean isInsideStructure(World worldIn, String structureName, BlockPos pos) {
        return false;
    }

}
