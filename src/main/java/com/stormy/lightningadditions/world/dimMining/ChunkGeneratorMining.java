package com.stormy.lightningadditions.world.dimMining;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.init.Biomes;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.ChunkPrimer;
import net.minecraft.world.chunk.IChunkGenerator;

import java.util.List;
import java.util.Random;

public class ChunkGeneratorMining implements IChunkGenerator
{
    private final World world;
    private final ChunkPrimer primer;
    private final Random rng;
    private final BlockPos decoratePos = new BlockPos(BlockPos.ORIGIN);
    private static final byte biomeId = (byte)Biome.getIdForBiome(Biomes.EXTREME_HILLS);

    public ChunkGeneratorMining(World world, long seed, boolean featuresEnabled)
    {
        this.world = world;
        this.rng = new Random(seed);
        this.primer = new ChunkPrimer();

        IBlockState stone = Blocks.STONE.getDefaultState();
        IBlockState dirt = Blocks.STONE.getDefaultState();
        IBlockState bedrock = Blocks.BEDROCK.getDefaultState();
        IBlockState grass = Blocks.STONE.getDefaultState();

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
        for (x = 0; x < 16; x++) {
            for (z = 0; z < 16; z++) {
                this.primer.setBlockState(x, 63, z, grass);
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

    public void populate(int x, int z)
    {
        net.minecraft.block.BlockFalling.fallInstantly = true;
        this.rng.setSeed(this.world.getSeed());
        long i1 = this.rng.nextLong() / 2L * 2L + 1L;
        long j1 = this.rng.nextLong() / 2L * 2L + 1L;
        this.rng.setSeed(x * i1 + z * j1 ^ this.world.getSeed());

        Biomes.EXTREME_HILLS.decorate(this.world, this.rng, new BlockPos(x * 16, 0, z * 16));
        net.minecraft.block.BlockFalling.fallInstantly = false;
    }

    public boolean generateStructures(Chunk chunkIn, int x, int z) {return true;}
    public List<Biome.SpawnListEntry> getPossibleCreatures(EnumCreatureType creatureType, BlockPos pos)
    {return Biomes.EXTREME_HILLS.getSpawnableList(creatureType);}

    public BlockPos getStrongholdGen(World worldIn, String structureName, BlockPos position, boolean flag) {return null;}
    public void recreateStructures(Chunk chunkIn, int x, int z) {}
}
