package com.stormy.lightningadditions.world;

import com.stormy.lightningadditions.compat.LAModConstants;
import com.stormy.lightningadditions.init.ModBlocks;
import com.stormy.lightningadditions.utility.logger.LALogger;
import com.stormy.lightningadditions.world.jsonhelper.JsonParserModdedOres;
import com.stormy.lightningadditions.world.jsonhelper.JsonParserVanillaOres;
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
        if(!JsonParserVanillaOres.loadOverworldCoalOre().get("disableOre").getAsBoolean()) generateOre(Blocks.COAL_ORE, world, random, x, z, 1, blockSize(JsonParserVanillaOres.loadOverworldCoalOre().get("veinMinimum").getAsInt(), JsonParserVanillaOres.loadOverworldCoalOre().get("veinMultiplier").getAsInt()), JsonParserVanillaOres.loadOverworldCoalOre().get("rarity").getAsInt(), JsonParserVanillaOres.loadOverworldCoalOre().get("minY").getAsInt(), JsonParserVanillaOres.loadOverworldCoalOre().get("maxY").getAsInt(), Blocks.STONE);
        if(!JsonParserVanillaOres.loadOverworldDiamondOre().get("disableOre").getAsBoolean()) generateOre(Blocks.DIAMOND_ORE, world, random, x, z, 1, blockSize(JsonParserVanillaOres.loadOverworldDiamondOre().get("veinMinimum").getAsInt(), JsonParserVanillaOres.loadOverworldDiamondOre().get("veinMultiplier").getAsInt()), JsonParserVanillaOres.loadOverworldDiamondOre().get("rarity").getAsInt(), JsonParserVanillaOres.loadOverworldDiamondOre().get("minY").getAsInt(), JsonParserVanillaOres.loadOverworldDiamondOre().get("maxY").getAsInt(), Blocks.STONE);
        if(!JsonParserVanillaOres.loadOverworldEmeraldOre().get("disableOre").getAsBoolean()) generateOre(Blocks.EMERALD_ORE, world, random, x, z, 1, blockSize(JsonParserVanillaOres.loadOverworldEmeraldOre().get("veinMinimum").getAsInt(), JsonParserVanillaOres.loadOverworldEmeraldOre().get("veinMultiplier").getAsInt()), JsonParserVanillaOres.loadOverworldEmeraldOre().get("rarity").getAsInt(), JsonParserVanillaOres.loadOverworldEmeraldOre().get("minY").getAsInt(), JsonParserVanillaOres.loadOverworldEmeraldOre().get("maxY").getAsInt(), Blocks.STONE);
        if(!JsonParserVanillaOres.loadOverworldGoldOre().get("disableOre").getAsBoolean()) generateOre(Blocks.GOLD_ORE, world, random, x, z, 1, blockSize(JsonParserVanillaOres.loadOverworldGoldOre().get("veinMinimum").getAsInt(), JsonParserVanillaOres.loadOverworldGoldOre().get("veinMultiplier").getAsInt()), JsonParserVanillaOres.loadOverworldGoldOre().get("rarity").getAsInt(), JsonParserVanillaOres.loadOverworldGoldOre().get("minY").getAsInt(), JsonParserVanillaOres.loadOverworldGoldOre().get("maxY").getAsInt(), Blocks.STONE);
        if(!JsonParserVanillaOres.loadOverworldIronOre().get("disableOre").getAsBoolean()) generateOre(Blocks.IRON_ORE, world, random, x, z, 1, blockSize(JsonParserVanillaOres.loadOverworldIronOre().get("veinMinimum").getAsInt(), JsonParserVanillaOres.loadOverworldIronOre().get("veinMultiplier").getAsInt()), JsonParserVanillaOres.loadOverworldIronOre().get("rarity").getAsInt(), JsonParserVanillaOres.loadOverworldIronOre().get("minY").getAsInt(), JsonParserVanillaOres.loadOverworldIronOre().get("maxY").getAsInt(), Blocks.STONE);
        if(!JsonParserVanillaOres.loadOverworldLapisOre().get("disableOre").getAsBoolean()) generateOre(Blocks.LAPIS_ORE, world, random, x, z, 1, blockSize(JsonParserVanillaOres.loadOverworldLapisOre().get("veinMinimum").getAsInt(), JsonParserVanillaOres.loadOverworldLapisOre().get("veinMultiplier").getAsInt()), JsonParserVanillaOres.loadOverworldLapisOre().get("rarity").getAsInt(), JsonParserVanillaOres.loadOverworldLapisOre().get("minY").getAsInt(), JsonParserVanillaOres.loadOverworldLapisOre().get("maxY").getAsInt(), Blocks.STONE);
        if(!JsonParserVanillaOres.loadOverworldRedstoneOre().get("disableOre").getAsBoolean()) generateOre(Blocks.REDSTONE_ORE, world, random, x, z, 1, blockSize(JsonParserVanillaOres.loadOverworldRedstoneOre().get("veinMinimum").getAsInt(), JsonParserVanillaOres.loadOverworldRedstoneOre().get("veinMultiplier").getAsInt()), JsonParserVanillaOres.loadOverworldRedstoneOre().get("rarity").getAsInt(), JsonParserVanillaOres.loadOverworldRedstoneOre().get("minY").getAsInt(), JsonParserVanillaOres.loadOverworldRedstoneOre().get("maxY").getAsInt(), Blocks.STONE);

    }

    private void generateSurfaceModded(World world, Random random, int x, int z)
    {
        if(LAModConstants.copperOre && !JsonParserModdedOres.loadOverworldCopperOre().get("disableOre").getAsBoolean()) generateOre(ModBlocks.OVERWORLD_COPPER_ORE, world, random, x, z, 1, blockSize(JsonParserModdedOres.loadOverworldCopperOre().get("veinMinimum").getAsInt(), JsonParserModdedOres.loadOverworldCopperOre().get("veinMultiplier").getAsInt()), JsonParserModdedOres.loadOverworldCopperOre().get("rarity").getAsInt(), JsonParserModdedOres.loadOverworldCopperOre().get("minY").getAsInt(), JsonParserModdedOres.loadOverworldCopperOre().get("maxY").getAsInt(), Blocks.STONE);
        if(LAModConstants.leadOre && !JsonParserModdedOres.loadOverworldLeadOre().get("disableOre").getAsBoolean()) generateOre(ModBlocks.OVERWORLD_LEAD_ORE, world, random, x, z, 1, blockSize(JsonParserModdedOres.loadOverworldLeadOre().get("veinMinimum").getAsInt(), JsonParserModdedOres.loadOverworldLeadOre().get("veinMultiplier").getAsInt()), JsonParserModdedOres.loadOverworldLeadOre().get("rarity").getAsInt(), JsonParserModdedOres.loadOverworldLeadOre().get("minY").getAsInt(), JsonParserModdedOres.loadOverworldLeadOre().get("maxY").getAsInt(), Blocks.STONE);
        if(LAModConstants.tinOre && !JsonParserModdedOres.loadOverworldTinOre().get("disableOre").getAsBoolean()) generateOre(ModBlocks.OVERWORLD_TIN_ORE, world, random, x, z, 1, blockSize(JsonParserModdedOres.loadOverworldTinOre().get("veinMinimum").getAsInt(), JsonParserModdedOres.loadOverworldTinOre().get("veinMultiplier").getAsInt()), JsonParserModdedOres.loadOverworldTinOre().get("rarity").getAsInt(), JsonParserModdedOres.loadOverworldTinOre().get("minY").getAsInt(), JsonParserModdedOres.loadOverworldTinOre().get("maxY").getAsInt(), Blocks.STONE);
        if(LAModConstants.silverOre && !JsonParserModdedOres.loadOverworldSilverOre().get("disableOre").getAsBoolean()) generateOre(ModBlocks.OVERWORLD_SILVER_ORE, world, random, x, z, 1, blockSize(JsonParserModdedOres.loadOverworldSilverOre().get("veinMinimum").getAsInt(), JsonParserModdedOres.loadOverworldSilverOre().get("veinMultiplier").getAsInt()), JsonParserModdedOres.loadOverworldSilverOre().get("rarity").getAsInt(), JsonParserModdedOres.loadOverworldSilverOre().get("minY").getAsInt(), JsonParserModdedOres.loadOverworldSilverOre().get("maxY").getAsInt(), Blocks.STONE);
    }

    private void generateNetherVanilla(World world, Random random, int x, int z)
    {
        if(!JsonParserVanillaOres.loadNetherCoalOre().get("disableOre").getAsBoolean()) generateOre(ModBlocks.NETHER_COAL_ORE, world, random, x, z, 1, blockSize(JsonParserVanillaOres.loadNetherCoalOre().get("veinMinimum").getAsInt(), JsonParserVanillaOres.loadNetherCoalOre().get("veinMultiplier").getAsInt()), JsonParserVanillaOres.loadNetherCoalOre().get("rarity").getAsInt(), JsonParserVanillaOres.loadNetherCoalOre().get("minY").getAsInt(), JsonParserVanillaOres.loadNetherCoalOre().get("maxY").getAsInt(), Blocks.NETHERRACK);
        if(!JsonParserVanillaOres.loadNetherDiamondOre().get("disableOre").getAsBoolean()) generateOre(ModBlocks.NETHER_DIAMOND_ORE, world, random, x, z, 1, blockSize(JsonParserVanillaOres.loadNetherDiamondOre().get("veinMinimum").getAsInt(), JsonParserVanillaOres.loadNetherDiamondOre().get("veinMultiplier").getAsInt()), JsonParserVanillaOres.loadNetherDiamondOre().get("rarity").getAsInt(), JsonParserVanillaOres.loadNetherDiamondOre().get("minY").getAsInt(), JsonParserVanillaOres.loadNetherDiamondOre().get("maxY").getAsInt(), Blocks.NETHERRACK);
        if(!JsonParserVanillaOres.loadNetherEmeraldOre().get("disableOre").getAsBoolean()) generateOre(ModBlocks.NETHER_EMERALD_ORE, world, random, x, z, 1, blockSize(JsonParserVanillaOres.loadNetherEmeraldOre().get("veinMinimum").getAsInt(), JsonParserVanillaOres.loadNetherEmeraldOre().get("veinMultiplier").getAsInt()), JsonParserVanillaOres.loadNetherEmeraldOre().get("rarity").getAsInt(), JsonParserVanillaOres.loadNetherEmeraldOre().get("minY").getAsInt(), JsonParserVanillaOres.loadNetherEmeraldOre().get("maxY").getAsInt(), Blocks.NETHERRACK);
        if(!JsonParserVanillaOres.loadNetherGoldOre().get("disableOre").getAsBoolean()) generateOre(ModBlocks.NETHER_GOLD_ORE, world, random, x, z, 1, blockSize(JsonParserVanillaOres.loadNetherGoldOre().get("veinMinimum").getAsInt(), JsonParserVanillaOres.loadNetherGoldOre().get("veinMultiplier").getAsInt()), JsonParserVanillaOres.loadNetherGoldOre().get("rarity").getAsInt(), JsonParserVanillaOres.loadNetherGoldOre().get("minY").getAsInt(), JsonParserVanillaOres.loadNetherGoldOre().get("maxY").getAsInt(), Blocks.NETHERRACK);
        if(!JsonParserVanillaOres.loadNetherIronOre().get("disableOre").getAsBoolean()) generateOre(ModBlocks.NETHER_IRON_ORE, world, random, x, z, 1, blockSize(JsonParserVanillaOres.loadNetherIronOre().get("veinMinimum").getAsInt(), JsonParserVanillaOres.loadNetherIronOre().get("veinMultiplier").getAsInt()), JsonParserVanillaOres.loadNetherIronOre().get("rarity").getAsInt(), JsonParserVanillaOres.loadNetherIronOre().get("minY").getAsInt(), JsonParserVanillaOres.loadNetherIronOre().get("maxY").getAsInt(), Blocks.NETHERRACK);
        if(!JsonParserVanillaOres.loadNetherLapisOre().get("disableOre").getAsBoolean()) generateOre(ModBlocks.NETHER_LAPIS_ORE, world, random, x, z, 1, blockSize(JsonParserVanillaOres.loadNetherLapisOre().get("veinMinimum").getAsInt(), JsonParserVanillaOres.loadNetherLapisOre().get("veinMultiplier").getAsInt()), JsonParserVanillaOres.loadNetherLapisOre().get("rarity").getAsInt(), JsonParserVanillaOres.loadNetherLapisOre().get("minY").getAsInt(), JsonParserVanillaOres.loadNetherLapisOre().get("maxY").getAsInt(), Blocks.NETHERRACK);
        if(!JsonParserVanillaOres.loadNetherRedstoneOre().get("disableOre").getAsBoolean()) generateOre(ModBlocks.NETHER_REDSTONE_ORE, world, random, x, z, 1, blockSize(JsonParserVanillaOres.loadNetherRedstoneOre().get("veinMinimum").getAsInt(), JsonParserVanillaOres.loadNetherRedstoneOre().get("veinMultiplier").getAsInt()), JsonParserVanillaOres.loadNetherRedstoneOre().get("rarity").getAsInt(), JsonParserVanillaOres.loadNetherRedstoneOre().get("minY").getAsInt(), JsonParserVanillaOres.loadNetherRedstoneOre().get("maxY").getAsInt(), Blocks.NETHERRACK);
    }

    private void generateNetherModded(World world, Random random, int x, int z)
    {
        if (LAModConstants.copperOre && !JsonParserModdedOres.loadNetherCopperOre().get("disableOre").getAsBoolean()) generateOre(ModBlocks.NETHER_COPPER_ORE, world, random, x, z, 1, blockSize(JsonParserModdedOres.loadNetherCopperOre().get("veinMinimum").getAsInt(), JsonParserModdedOres.loadNetherCopperOre().get("veinMultiplier").getAsInt()), JsonParserModdedOres.loadNetherCopperOre().get("rarity").getAsInt(), JsonParserModdedOres.loadNetherCopperOre().get("minY").getAsInt(), JsonParserModdedOres.loadNetherCopperOre().get("maxY").getAsInt(), Blocks.NETHERRACK);
        if (LAModConstants.leadOre && !JsonParserModdedOres.loadNetherLeadOre().get("disableOre").getAsBoolean()) generateOre(ModBlocks.NETHER_LEAD_ORE, world, random, x, z, 1, blockSize(JsonParserModdedOres.loadNetherLeadOre().get("veinMinimum").getAsInt(), JsonParserModdedOres.loadNetherLeadOre().get("veinMultiplier").getAsInt()), JsonParserModdedOres.loadNetherLeadOre().get("rarity").getAsInt(), JsonParserModdedOres.loadNetherLeadOre().get("minY").getAsInt(), JsonParserModdedOres.loadNetherLeadOre().get("maxY").getAsInt(), Blocks.NETHERRACK);
        if (LAModConstants.tinOre && !JsonParserModdedOres.loadNetherTinOre().get("disableOre").getAsBoolean()) generateOre(ModBlocks.NETHER_TIN_ORE, world, random, x, z, 1, blockSize(JsonParserModdedOres.loadNetherTinOre().get("veinMinimum").getAsInt(), JsonParserModdedOres.loadNetherTinOre().get("veinMultiplier").getAsInt()), JsonParserModdedOres.loadNetherTinOre().get("rarity").getAsInt(), JsonParserModdedOres.loadNetherTinOre().get("minY").getAsInt(), JsonParserModdedOres.loadNetherTinOre().get("maxY").getAsInt(), Blocks.NETHERRACK);
        if (LAModConstants.silverOre && !JsonParserModdedOres.loadNetherSilverOre().get("disableOre").getAsBoolean()) generateOre(ModBlocks.NETHER_SILVER_ORE, world, random, x, z, 1, blockSize(JsonParserModdedOres.loadNetherSilverOre().get("veinMinimum").getAsInt(), JsonParserModdedOres.loadNetherSilverOre().get("veinMultiplier").getAsInt()), JsonParserModdedOres.loadNetherSilverOre().get("rarity").getAsInt(), JsonParserModdedOres.loadNetherSilverOre().get("minY").getAsInt(), JsonParserModdedOres.loadNetherSilverOre().get("maxY").getAsInt(), Blocks.NETHERRACK);
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
