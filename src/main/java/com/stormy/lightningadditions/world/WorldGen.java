package com.stormy.lightningadditions.world;

import com.google.gson.JsonObject;
import com.stormy.lightningadditions.compat.LAModConstants;
import com.stormy.lightningadditions.init.ModBlocks;
import com.stormy.lightningadditions.utility.logger.LALogger;
import com.stormy.lightningadditions.world.jsonhelper.JsonNeutralModBlocks;
import com.stormy.lightningadditions.world.jsonhelper.JsonParser;
import net.minecraft.block.Block;
import net.minecraft.block.state.pattern.BlockMatcher;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkGenerator;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraftforge.fml.common.IWorldGenerator;

import java.util.Random;

public class WorldGen implements IWorldGenerator{

    @Override
    public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider) {
        switch(world.provider.getDimension()){
            case -1: //Nether
                generateNetherVanilla(world, random, chunkX, chunkZ);
                generateNetherModded(world, random, chunkX, chunkZ);
                break;
            case 0: //Overworld
                generateSurfaceVanilla(world, random, chunkX, chunkZ);
                generateSurfaceModded(world, random, chunkX, chunkZ);
                break;
            case 1: //End
                generateEnd(world, random, chunkX, chunkZ);
                break;
        }

    }

    private void generateSurfaceVanilla(World world, Random random, int x, int z)
    {
        if(!JsonParser.loadOverworldCoalOre().get("disableOre").getAsBoolean()) generateOre(Blocks.COAL_ORE, world, random, x, z, 1, blockSize(JsonParser.loadOverworldCoalOre().get("veinMinimum").getAsInt(), JsonParser.loadOverworldCoalOre().get("veinMultiplier").getAsInt()), JsonParser.loadOverworldCoalOre().get("rarity").getAsInt(), JsonParser.loadOverworldCoalOre().get("minY").getAsInt(), JsonParser.loadOverworldCoalOre().get("maxY").getAsInt(), Blocks.STONE);
        if(!JsonParser.loadOverworldDiamondOre().get("disableOre").getAsBoolean()) generateOre(Blocks.DIAMOND_ORE, world, random, x, z, 1, blockSize(JsonParser.loadOverworldDiamondOre().get("veinMinimum").getAsInt(), JsonParser.loadOverworldDiamondOre().get("veinMultiplier").getAsInt()), JsonParser.loadOverworldDiamondOre().get("rarity").getAsInt(), JsonParser.loadOverworldDiamondOre().get("minY").getAsInt(), JsonParser.loadOverworldDiamondOre().get("maxY").getAsInt(), Blocks.STONE);
        if(!JsonParser.loadOverworldEmeraldOre().get("disableOre").getAsBoolean()) generateOre(Blocks.EMERALD_ORE, world, random, x, z, 1, blockSize(JsonParser.loadOverworldEmeraldOre().get("veinMinimum").getAsInt(), JsonParser.loadOverworldEmeraldOre().get("veinMultiplier").getAsInt()), JsonParser.loadOverworldEmeraldOre().get("rarity").getAsInt(), JsonParser.loadOverworldEmeraldOre().get("minY").getAsInt(), JsonParser.loadOverworldEmeraldOre().get("maxY").getAsInt(), Blocks.STONE);
        if(!JsonParser.loadOverworldGoldOre().get("disableOre").getAsBoolean()) generateOre(Blocks.GOLD_ORE, world, random, x, z, 1, blockSize(JsonParser.loadOverworldGoldOre().get("veinMinimum").getAsInt(), JsonParser.loadOverworldGoldOre().get("veinMultiplier").getAsInt()), JsonParser.loadOverworldGoldOre().get("rarity").getAsInt(), JsonParser.loadOverworldGoldOre().get("minY").getAsInt(), JsonParser.loadOverworldGoldOre().get("maxY").getAsInt(), Blocks.STONE);
        if(!JsonParser.loadOverworldIronOre().get("disableOre").getAsBoolean()) generateOre(Blocks.IRON_ORE, world, random, x, z, 1, blockSize(JsonParser.loadOverworldIronOre().get("veinMinimum").getAsInt(), JsonParser.loadOverworldIronOre().get("veinMultiplier").getAsInt()), JsonParser.loadOverworldIronOre().get("rarity").getAsInt(), JsonParser.loadOverworldIronOre().get("minY").getAsInt(), JsonParser.loadOverworldIronOre().get("maxY").getAsInt(), Blocks.STONE);
        if(!JsonParser.loadOverworldLapisOre().get("disableOre").getAsBoolean()) generateOre(Blocks.LAPIS_ORE, world, random, x, z, 1, blockSize(JsonParser.loadOverworldLapisOre().get("veinMinimum").getAsInt(), JsonParser.loadOverworldLapisOre().get("veinMultiplier").getAsInt()), JsonParser.loadOverworldLapisOre().get("rarity").getAsInt(), JsonParser.loadOverworldLapisOre().get("minY").getAsInt(), JsonParser.loadOverworldLapisOre().get("maxY").getAsInt(), Blocks.STONE);
        if(!JsonParser.loadOverworldRedstoneOre().get("disableOre").getAsBoolean()) generateOre(Blocks.REDSTONE_ORE, world, random, x, z, 1, blockSize(JsonParser.loadOverworldRedstoneOre().get("veinMinimum").getAsInt(), JsonParser.loadOverworldRedstoneOre().get("veinMultiplier").getAsInt()), JsonParser.loadOverworldRedstoneOre().get("rarity").getAsInt(), JsonParser.loadOverworldRedstoneOre().get("minY").getAsInt(), JsonParser.loadOverworldRedstoneOre().get("maxY").getAsInt(), Blocks.STONE);

    }

    private void generateSurfaceModded(World world, Random random, int x, int z)
    {
        if(LAModConstants.copperOre && !JsonNeutralModBlocks.loadOverworldCopper().get("disableOre").getAsBoolean()) generateOre(ModBlocks.OVERWORLD_COPPER_ORE, world, random, x, z, 1, blockSize(JsonNeutralModBlocks.loadOverworldCopper().get("veinMinimum").getAsInt(), JsonNeutralModBlocks.loadOverworldCopper().get("veinMultiplier").getAsInt()), JsonNeutralModBlocks.loadOverworldCopper().get("rarity").getAsInt(), JsonNeutralModBlocks.loadOverworldCopper().get("minY").getAsInt(), JsonNeutralModBlocks.loadOverworldCopper().get("maxY").getAsInt(), Blocks.STONE);
        if(LAModConstants.leadOre && !JsonNeutralModBlocks.loadOverworldLead().get("disableOre").getAsBoolean()) generateOre(ModBlocks.OVERWORLD_LEAD_ORE, world, random, x, z, 1, blockSize(JsonNeutralModBlocks.loadOverworldLead().get("veinMinimum").getAsInt(), JsonNeutralModBlocks.loadOverworldLead().get("veinMultiplier").getAsInt()), JsonNeutralModBlocks.loadOverworldLead().get("rarity").getAsInt(), JsonNeutralModBlocks.loadOverworldLead().get("minY").getAsInt(), JsonNeutralModBlocks.loadOverworldLead().get("maxY").getAsInt(), Blocks.STONE);
        if(LAModConstants.tinOre && !JsonNeutralModBlocks.loadOverworldTin().get("disableOre").getAsBoolean()) generateOre(ModBlocks.OVERWORLD_TIN_ORE, world, random, x, z, 1, blockSize(JsonNeutralModBlocks.loadOverworldTin().get("veinMinimum").getAsInt(), JsonNeutralModBlocks.loadOverworldTin().get("veinMultiplier").getAsInt()), JsonNeutralModBlocks.loadOverworldTin().get("rarity").getAsInt(), JsonNeutralModBlocks.loadOverworldTin().get("minY").getAsInt(), JsonNeutralModBlocks.loadOverworldTin().get("maxY").getAsInt(), Blocks.STONE);
        if(LAModConstants.silverOre && !JsonNeutralModBlocks.loadOverworldSilver().get("disableOre").getAsBoolean()) generateOre(ModBlocks.OVERWORLD_SILVER_ORE, world, random, x, z, 1, blockSize(JsonNeutralModBlocks.loadOverworldSilver().get("veinMinimum").getAsInt(), JsonNeutralModBlocks.loadOverworldSilver().get("veinMultiplier").getAsInt()), JsonNeutralModBlocks.loadOverworldSilver().get("rarity").getAsInt(), JsonNeutralModBlocks.loadOverworldSilver().get("minY").getAsInt(), JsonNeutralModBlocks.loadOverworldSilver().get("maxY").getAsInt(), Blocks.STONE);
    }

    private void generateNetherVanilla(World world, Random random, int x, int z)
    {
        if(!JsonParser.loadNetherCoalOre().get("disableOre").getAsBoolean()) generateOre(ModBlocks.NETHER_COAL_ORE, world, random, x, z, 1, blockSize(JsonParser.loadNetherCoalOre().get("veinMinimum").getAsInt(), JsonParser.loadNetherCoalOre().get("veinMultiplier").getAsInt()), JsonParser.loadNetherCoalOre().get("rarity").getAsInt(), JsonParser.loadNetherCoalOre().get("minY").getAsInt(), JsonParser.loadNetherCoalOre().get("maxY").getAsInt(), Blocks.NETHERRACK);
        if(!JsonParser.loadNetherDiamondOre().get("disableOre").getAsBoolean()) generateOre(ModBlocks.NETHER_DIAMOND_ORE, world, random, x, z, 1, blockSize(JsonParser.loadNetherDiamondOre().get("veinMinimum").getAsInt(), JsonParser.loadNetherDiamondOre().get("veinMultiplier").getAsInt()), JsonParser.loadNetherDiamondOre().get("rarity").getAsInt(), JsonParser.loadNetherDiamondOre().get("minY").getAsInt(), JsonParser.loadNetherDiamondOre().get("maxY").getAsInt(), Blocks.NETHERRACK);
        if(!JsonParser.loadNetherEmeraldOre().get("disableOre").getAsBoolean()) generateOre(ModBlocks.NETHER_EMERALD_ORE, world, random, x, z, 1, blockSize(JsonParser.loadNetherEmeraldOre().get("veinMinimum").getAsInt(), JsonParser.loadNetherEmeraldOre().get("veinMultiplier").getAsInt()), JsonParser.loadNetherEmeraldOre().get("rarity").getAsInt(), JsonParser.loadNetherEmeraldOre().get("minY").getAsInt(), JsonParser.loadNetherEmeraldOre().get("maxY").getAsInt(), Blocks.NETHERRACK);
        if(!JsonParser.loadNetherGoldOre().get("disableOre").getAsBoolean()) generateOre(ModBlocks.NETHER_GOLD_ORE, world, random, x, z, 1, blockSize(JsonParser.loadNetherGoldOre().get("veinMinimum").getAsInt(), JsonParser.loadNetherGoldOre().get("veinMultiplier").getAsInt()), JsonParser.loadNetherGoldOre().get("rarity").getAsInt(), JsonParser.loadNetherGoldOre().get("minY").getAsInt(), JsonParser.loadNetherGoldOre().get("maxY").getAsInt(), Blocks.NETHERRACK);
        if(!JsonParser.loadNetherIronOre().get("disableOre").getAsBoolean()) generateOre(ModBlocks.NETHER_IRON_ORE, world, random, x, z, 1, blockSize(JsonParser.loadNetherIronOre().get("veinMinimum").getAsInt(), JsonParser.loadNetherIronOre().get("veinMultiplier").getAsInt()), JsonParser.loadNetherIronOre().get("rarity").getAsInt(), JsonParser.loadNetherIronOre().get("minY").getAsInt(), JsonParser.loadNetherIronOre().get("maxY").getAsInt(), Blocks.NETHERRACK);
        if(!JsonParser.loadNetherLapisOre().get("disableOre").getAsBoolean()) generateOre(ModBlocks.NETHER_LAPIS_ORE, world, random, x, z, 1, blockSize(JsonParser.loadNetherLapisOre().get("veinMinimum").getAsInt(), JsonParser.loadNetherLapisOre().get("veinMultiplier").getAsInt()), JsonParser.loadNetherLapisOre().get("rarity").getAsInt(), JsonParser.loadNetherLapisOre().get("minY").getAsInt(), JsonParser.loadNetherLapisOre().get("maxY").getAsInt(), Blocks.NETHERRACK);
        if(!JsonParser.loadNetherRedstoneOre().get("disableOre").getAsBoolean()) generateOre(ModBlocks.NETHER_REDSTONE_ORE, world, random, x, z, 1, blockSize(JsonParser.loadNetherRedstoneOre().get("veinMinimum").getAsInt(), JsonParser.loadNetherRedstoneOre().get("veinMultiplier").getAsInt()), JsonParser.loadNetherRedstoneOre().get("rarity").getAsInt(), JsonParser.loadNetherRedstoneOre().get("minY").getAsInt(), JsonParser.loadNetherRedstoneOre().get("maxY").getAsInt(), Blocks.NETHERRACK);
    }

    private void generateNetherModded(World world, Random random, int x, int z)
    {
        try {
            if (LAModConstants.copperOre && !JsonNeutralModBlocks.loadNetherCopper().get("disableOre").getAsBoolean())
                generateOre(ModBlocks.NETHER_COPPER_ORE, world, random, x, z, 1, blockSize(JsonNeutralModBlocks.loadNetherCopper().get("veinMinimum").getAsInt(), JsonNeutralModBlocks.loadNetherCopper().get("veinMultiplier").getAsInt()), JsonNeutralModBlocks.loadNetherCopper().get("rarity").getAsInt(), JsonNeutralModBlocks.loadNetherCopper().get("minY").getAsInt(), JsonNeutralModBlocks.loadNetherCopper().get("maxY").getAsInt(), Blocks.NETHERRACK);
            if (LAModConstants.leadOre && !JsonNeutralModBlocks.loadNetherLead().get("disableOre").getAsBoolean())
                generateOre(ModBlocks.NETHER_LEAD_ORE, world, random, x, z, 1, blockSize(JsonNeutralModBlocks.loadNetherLead().get("veinMinimum").getAsInt(), JsonNeutralModBlocks.loadNetherLead().get("veinMultiplier").getAsInt()), JsonNeutralModBlocks.loadNetherLead().get("rarity").getAsInt(), JsonNeutralModBlocks.loadNetherLead().get("minY").getAsInt(), JsonNeutralModBlocks.loadNetherLead().get("maxY").getAsInt(), Blocks.NETHERRACK);
            if (LAModConstants.tinOre && !JsonNeutralModBlocks.loadNetherTin().get("disableOre").getAsBoolean())
                generateOre(ModBlocks.NETHER_TIN_ORE, world, random, x, z, 1, blockSize(JsonNeutralModBlocks.loadNetherTin().get("veinMinimum").getAsInt(), JsonNeutralModBlocks.loadNetherTin().get("veinMultiplier").getAsInt()), JsonNeutralModBlocks.loadNetherTin().get("rarity").getAsInt(), JsonNeutralModBlocks.loadNetherTin().get("minY").getAsInt(), JsonNeutralModBlocks.loadNetherTin().get("maxY").getAsInt(), Blocks.NETHERRACK);
            if (LAModConstants.silverOre && !JsonNeutralModBlocks.loadNetherSilver().get("disableOre").getAsBoolean())
                generateOre(ModBlocks.NETHER_SILVER_ORE, world, random, x, z, 1, blockSize(JsonNeutralModBlocks.loadNetherSilver().get("veinMinimum").getAsInt(), JsonNeutralModBlocks.loadNetherSilver().get("veinMultiplier").getAsInt()), JsonNeutralModBlocks.loadNetherSilver().get("rarity").getAsInt(), JsonNeutralModBlocks.loadNetherSilver().get("minY").getAsInt(), JsonNeutralModBlocks.loadNetherSilver().get("maxY").getAsInt(), Blocks.NETHERRACK);
        }catch (NullPointerException e){
            LALogger.fatal("NETHER ORES (MODDED) FAILED TO GENERATE -- A CRASH WAS PREVENTED"); //This is a temporary Try Catch
        }
    }

    private void generateEnd(World world, Random rand, int x, int z)
    {
        //TODO?
    }

    public void generateOre(Block block, World world, Random random, int chunkX, int chunkZ, int minVeinSize, int maxVeinSize, int chance, int minY, int maxY, Block generateIn)
    {
        int veinSize = minVeinSize + random.nextInt(maxVeinSize - minVeinSize);
        int heightRange = maxY - minY;
        WorldGenMinable gen = new WorldGenMinable(block.getDefaultState(), veinSize, BlockMatcher.forBlock(generateIn));
        for (int i = 0; i < chance; i++){
            int xRand = chunkX * 16 + random.nextInt(16);
            int yRand = random.nextInt(heightRange) + minY;
            int zRand = chunkZ * 16 + random.nextInt(16);
            gen.generate(world, random, new BlockPos(xRand, yRand, zRand));
            //LogHelper.info(block.getUnlocalizedName() + " at " + xRand + " " + yRand + " " + zRand);
        }
    }

    private int blockSize(int min, int max)
    {
        return min + (int) (Math.random() * max);
    }

}
