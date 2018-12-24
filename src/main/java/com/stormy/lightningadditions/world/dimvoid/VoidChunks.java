/*
 * ********************************************************************************
 * Copyright (c) 2017 StormyMode, MiningMark48. All Rights Reserved!
 * This file is part of Lightning Additions (MC-Mod).
 *
 * This project cannot be copied and/or distributed without the express
 * permission of StormyMode, MiningMark48 (Developers)!
 * ********************************************************************************
 */

package com.stormy.lightningadditions.world.dimvoid;

import net.minecraft.entity.EnumCreatureType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.ChunkPrimer;
import net.minecraft.world.gen.IChunkGenerator;

import javax.annotation.Nullable;
import java.util.List;

public class VoidChunks implements IChunkGenerator
{
    private final World world;

    public VoidChunks(World world)
    { this.world = world; }

    @Override
    public Chunk generateChunk(int x, int z) {
        ChunkPrimer chunkprimer = new ChunkPrimer();
        Chunk chunk = new Chunk(this.world, chunkprimer, x, z);
        chunk.generateSkylightMap();
        byte[] abyte = chunk.getBiomeArray();
        for (int i1 = 0; i1 < abyte.length; ++i1)
        { abyte[i1] = (byte) Biome.getIdForBiome(Biome.getBiome(1)); }
        chunk.generateSkylightMap();
        return chunk;
    }

    @Override
    public void populate(int x, int z) { }

    @Override
    public boolean generateStructures(Chunk chunkIn, int x, int z)
    { return false; }

    @Override
    public List<Biome.SpawnListEntry> getPossibleCreatures(EnumCreatureType creatureType, BlockPos pos)
    { return null; }

    @Nullable
    @Override
    public BlockPos getNearestStructurePos(World worldIn, String structureName, BlockPos position, boolean findUnexplored)
    { return null; }

    @Override
    public void recreateStructures(Chunk chunkIn, int x, int z) { }

    @Override
    public boolean isInsideStructure(World worldIn, String structureName, BlockPos pos)
    { return false; }


}
