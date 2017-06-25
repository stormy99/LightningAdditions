/*
 * ********************************************************************************
 * Copyright (c) 2017 StormyMode, MiningMark48. All Rights Reserved!
 * This file is part of Lightning Additions (MC-Mod).
 *
 * This project cannot be copied and/or distributed without the express
 * permission of StormyMode, MiningMark48 (Developers)!
 * ********************************************************************************
 */

package com.stormy.lightningadditions.world;

import java.util.Random;

import com.stormy.lightningadditions.world.jsonhelper.JsonNeutralModBlocks;
import com.stormy.lightningadditions.world.jsonhelper.JsonParser;
import com.stormy.lightningadditions.compat.LAModConstants;
import com.stormy.lightningadditions.init.ModBlocks;
import com.stormy.lightningadditions.utility.logger.ConfigurationManagerLA;
import forestry.core.PluginCore;
import forestry.core.blocks.BlockResourceOre;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkGenerator;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.fml.common.IWorldGenerator;
import net.minecraftforge.fml.common.Optional;

public class LAWorldOreGen implements IWorldGenerator
{
    // Vanilla Surface Ores
    private WorldGenerator coalOre;
    private WorldGenerator diamondOre;
    private WorldGenerator emeraldOre;
    private WorldGenerator goldOre;
    private WorldGenerator ironOre;
    private WorldGenerator lapisOre;
    private WorldGenerator redstoneOre;
    // Vanilla Nether Ores
    private WorldGenerator netherCoalOre;
    private WorldGenerator netherDiamondOre;
    private WorldGenerator netherEmeraldOre;
    private WorldGenerator netherGoldOre;
    private WorldGenerator netherIronOre;
    private WorldGenerator netherLapisOre;
    private WorldGenerator netherRedstoneOre;

    // Modded Surface Ores
    private WorldGenerator OverworldCopperOre;
    private WorldGenerator OverworldLeadOre;
    private WorldGenerator OverworldTinOre;
    private WorldGenerator OverworldSilverOre;
    // Modded Nether Ores
    private WorldGenerator netherCopperOre;
    private WorldGenerator netherLeadOre;
    private WorldGenerator netherTinOre;
    private WorldGenerator netherSilverOre;


    public LAWorldOreGen()
    {
        // Vanilla Surface Ores
        this.coalOre = new LAOreGenMining(Blocks.COAL_ORE.getDefaultState(), blockSize(JsonParser.loadOverworldCoalOre().get("veinMinimum").getAsInt(), JsonParser.loadOverworldCoalOre().get("veinMultiplier").getAsInt()), 0);
        this.diamondOre = new LAOreGenMining(Blocks.DIAMOND_ORE.getDefaultState(), blockSize(JsonParser.loadOverworldDiamondOre().get("veinMinimum").getAsInt(), JsonParser.loadOverworldDiamondOre().get("veinMultiplier").getAsInt()), 0);
        this.emeraldOre = new LAOreGenMining(Blocks.EMERALD_ORE.getDefaultState(), blockSize(JsonParser.loadOverworldEmeraldOre().get("veinMinimum").getAsInt(), JsonParser.loadOverworldEmeraldOre().get("veinMultiplier").getAsInt()), 0);
        this.goldOre = new LAOreGenMining(Blocks.GOLD_ORE.getDefaultState(), blockSize(JsonParser.loadOverworldGoldOre().get("veinMinimum").getAsInt(), JsonParser.loadOverworldGoldOre().get("veinMultiplier").getAsInt()), 0);
        this.ironOre = new LAOreGenMining(Blocks.IRON_ORE.getDefaultState(), blockSize(JsonParser.loadOverworldIronOre().get("veinMinimum").getAsInt(), JsonParser.loadOverworldIronOre().get("veinMultiplier").getAsInt()), 0);
        this.lapisOre = new LAOreGenMining(Blocks.LAPIS_ORE.getDefaultState(), blockSize(JsonParser.loadOverworldLapisOre().get("veinMinimum").getAsInt(), JsonParser.loadOverworldLapisOre().get("veinMultiplier").getAsInt()), 0);
        this.redstoneOre = new LAOreGenMining(Blocks.REDSTONE_ORE.getDefaultState(), blockSize(JsonParser.loadOverworldRedstoneOre().get("veinMinimum").getAsInt(),JsonParser.loadOverworldRedstoneOre().get("veinMultiplier").getAsInt()), 0);
        // Vanilla Nether Ores
        this.netherCoalOre = new LAOreGenMining(ModBlocks.NETHER_COAL_ORE.getDefaultState(), blockSize(JsonParser.loadNetherCoalOre().get("veinMinimum").getAsInt(), JsonParser.loadNetherCoalOre().get("veinMultiplier").getAsInt()), -1);
        this.netherDiamondOre = new LAOreGenMining(ModBlocks.NETHER_DIAMOND_ORE.getDefaultState(), blockSize(JsonParser.loadNetherDiamondOre().get("veinMinimum").getAsInt(), JsonParser.loadNetherDiamondOre().get("veinMultiplier").getAsInt()), -1);
        this.netherEmeraldOre = new LAOreGenMining(ModBlocks.NETHER_EMERALD_ORE.getDefaultState(), blockSize(JsonParser.loadNetherEmeraldOre().get("veinMinimum").getAsInt(), JsonParser.loadNetherEmeraldOre().get("veinMultiplier").getAsInt()), -1);
        this.netherGoldOre = new LAOreGenMining(ModBlocks.NETHER_GOLD_ORE.getDefaultState(), blockSize(JsonParser.loadNetherGoldOre().get("veinMinimum").getAsInt(), JsonParser.loadNetherGoldOre().get("veinMultiplier").getAsInt()), -1);
        this.netherIronOre = new LAOreGenMining(ModBlocks.NETHER_IRON_ORE.getDefaultState(), blockSize(JsonParser.loadNetherIronOre().get("veinMinimum").getAsInt(), JsonParser.loadNetherIronOre().get("veinMultiplier").getAsInt()), -1);
        this.netherLapisOre = new LAOreGenMining(ModBlocks.NETHER_LAPIS_ORE.getDefaultState(), blockSize(JsonParser.loadNetherLapisOre().get("veinMinimum").getAsInt(), JsonParser.loadNetherLapisOre().get("veinMultiplier").getAsInt()), -1);
        this.netherRedstoneOre = new LAOreGenMining(ModBlocks.NETHER_REDSTONE_ORE.getDefaultState(), blockSize(JsonParser.loadNetherRedstoneOre().get("veinMinimum").getAsInt(), JsonParser.loadNetherRedstoneOre().get("veinMultiplier").getAsInt()), -1);

        // Modded Surface Ores
        this.OverworldSilverOre = new LAOreGenMining(ModBlocks.OVERWORLD_SILVER_ORE.getDefaultState(), blockSize(JsonNeutralModBlocks.loadOverworldSilver().get("veinMinimum").getAsInt(), JsonNeutralModBlocks.loadOverworldSilver().get("veinMultiplier").getAsInt()), 0);
        this.OverworldCopperOre = new LAOreGenMining(ModBlocks.OVERWORLD_COPPER_ORE.getDefaultState(), blockSize(JsonNeutralModBlocks.loadOverworldCopper().get("veinMinimum").getAsInt(), JsonNeutralModBlocks.loadOverworldCopper().get("veinMultiplier").getAsInt()), 0);
        this.OverworldLeadOre = new LAOreGenMining(ModBlocks.OVERWORLD_LEAD_ORE.getDefaultState(), blockSize(JsonNeutralModBlocks.loadOverworldLead().get("veinMinimum").getAsInt(), JsonNeutralModBlocks.loadOverworldLead().get("veinMultiplier").getAsInt()), 0);
        this.OverworldTinOre = new LAOreGenMining(ModBlocks.OVERWORLD_TIN_ORE.getDefaultState(), blockSize(JsonNeutralModBlocks.loadOverworldTin().get("veinMinimum").getAsInt(), JsonNeutralModBlocks.loadOverworldTin().get("veinMultiplier").getAsInt()), 0);
        // Modded Nether Ores
        this.netherCopperOre = new LAOreGenMining(ModBlocks.NETHER_COPPER_ORE.getDefaultState(), blockSize(JsonNeutralModBlocks.loadNetherCopper().get("veinMinimum").getAsInt(), JsonNeutralModBlocks.loadNetherCopper().get("veinMultiplier").getAsInt()), -1);
        this.netherLeadOre = new LAOreGenMining(ModBlocks.NETHER_LEAD_ORE.getDefaultState(), blockSize(JsonNeutralModBlocks.loadNetherLead().get("veinMinimum").getAsInt(), JsonNeutralModBlocks.loadNetherLead().get("veinMultiplier").getAsInt()), -1);
        this.netherTinOre = new LAOreGenMining(ModBlocks.NETHER_TIN_ORE.getDefaultState(), blockSize(JsonNeutralModBlocks.loadNetherTin().get("veinMinimum").getAsInt(), JsonNeutralModBlocks.loadNetherTin().get("veinMultiplier").getAsInt()), -1);
        this.netherSilverOre = new LAOreGenMining(ModBlocks.NETHER_SILVER_ORE.getDefaultState(), blockSize(JsonNeutralModBlocks.loadNetherSilver().get("veinMinimum").getAsInt(), JsonNeutralModBlocks.loadNetherSilver().get("veinMultiplier").getAsInt()), -1);

    }
    @Override
    public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider)
    {
        switch(world.provider.getDimension())
        {
            case 0:
                if(ConfigurationManagerLA.changeVanilla)
                {
                    generateVanillaOverworldOres(random, chunkX, chunkZ, world, chunkGenerator, chunkProvider);
                }
                generateModdedOverworldOres(random, chunkX, chunkZ, world, chunkGenerator, chunkProvider);
                break;
            case -1:
                generateVanillaNetherOres(random, chunkX, chunkZ, world, chunkGenerator, chunkProvider);
                generateModdedNetherOres(random, chunkX, chunkZ, world, chunkGenerator, chunkProvider);
                break;
            case 1:
                break;
            default:
                if(ConfigurationManagerLA.supportNewDims)
                {
                    generateVanillaOverworldOres(random, chunkX, chunkZ, world, chunkGenerator, chunkProvider);
                    generateModdedOverworldOres(random, chunkX, chunkZ, world, chunkGenerator, chunkProvider);
                }
                break;
        }
    }

    // Randomly choose how many blocks can be in a vein
    private int blockSize(int min, int max)
    {
        return min + (int) (Math.random() * max);
    }

    private void generateVanillaOverworldOres(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider)
    {
        if(!JsonParser.loadOverworldCoalOre().get("disableOre").getAsBoolean())
        {
            runGenerator(this.coalOre, world, random, chunkX, chunkZ, JsonParser.loadOverworldCoalOre().get("rarity").getAsInt(), JsonParser.loadOverworldCoalOre().get("minY").getAsInt(), JsonParser.loadOverworldCoalOre().get("maxY").getAsInt());
        }

        if(!JsonParser.loadOverworldDiamondOre().get("disableOre").getAsBoolean())
        {
            runGenerator(this.diamondOre, world, random, chunkX, chunkZ, JsonParser.loadOverworldDiamondOre().get("rarity").getAsInt(), JsonParser.loadOverworldDiamondOre().get("minY").getAsInt(), JsonParser.loadOverworldDiamondOre().get("maxY").getAsInt());
        }

        if(!JsonParser.loadOverworldEmeraldOre().get("disableOre").getAsBoolean())
        {
            runGenerator(this.emeraldOre, world, random, chunkX, chunkZ, JsonParser.loadOverworldEmeraldOre().get("rarity").getAsInt(), JsonParser.loadOverworldEmeraldOre().get("minY").getAsInt(), JsonParser.loadOverworldEmeraldOre().get("maxY").getAsInt());
        }

        if(!JsonParser.loadOverworldGoldOre().get("disableOre").getAsBoolean())
        {
            runGenerator(this.goldOre, world, random, chunkX, chunkZ, JsonParser.loadOverworldGoldOre().get("rarity").getAsInt(), JsonParser.loadOverworldGoldOre().get("minY").getAsInt(), JsonParser.loadOverworldGoldOre().get("maxY").getAsInt());
        }

        if(!JsonParser.loadOverworldIronOre().get("disableOre").getAsBoolean())
        {
            runGenerator(this.ironOre, world, random, chunkX, chunkZ, JsonParser.loadOverworldIronOre().get("rarity").getAsInt(), JsonParser.loadOverworldIronOre().get("minY").getAsInt(), JsonParser.loadOverworldIronOre().get("maxY").getAsInt());
        }

        if(!JsonParser.loadOverworldLapisOre().get("disableOre").getAsBoolean())
        {
            runGenerator(this.lapisOre, world, random, chunkX, chunkZ, JsonParser.loadOverworldLapisOre().get("rarity").getAsInt(), JsonParser.loadOverworldLapisOre().get("minY").getAsInt(), JsonParser.loadOverworldLapisOre().get("maxY").getAsInt());
        }

        if(!JsonParser.loadOverworldRedstoneOre().get("disableOre").getAsBoolean())
        {
            runGenerator(this.redstoneOre, world, random, chunkX, chunkZ, JsonParser.loadOverworldRedstoneOre().get("rarity").getAsInt(), JsonParser.loadOverworldRedstoneOre().get("minY").getAsInt(), JsonParser.loadOverworldRedstoneOre().get("maxY").getAsInt());
        }
    }

    private void generateModdedOverworldOres(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider)
    {
        if(LAModConstants.copperOre)
        {
            if(!JsonNeutralModBlocks.loadOverworldCopper().get("disableOre").getAsBoolean())
            {
                runGenerator(this.OverworldCopperOre, world, random, chunkX, chunkZ, JsonNeutralModBlocks.loadOverworldCopper().get("rarity").getAsInt(), JsonNeutralModBlocks.loadOverworldCopper().get("minY").getAsInt(), JsonNeutralModBlocks.loadOverworldCopper().get("maxY").getAsInt());
            }
        }

        if(LAModConstants.leadOre)
        {
            if(!JsonNeutralModBlocks.loadOverworldLead().get("disableOre").getAsBoolean())
            {
                runGenerator(this.OverworldLeadOre, world, random, chunkX, chunkZ, JsonNeutralModBlocks.loadOverworldLead().get("rarity").getAsInt(), JsonNeutralModBlocks.loadOverworldLead().get("minY").getAsInt(), JsonNeutralModBlocks.loadOverworldLead().get("maxY").getAsInt());
            }
        }

        if(LAModConstants.tinOre)
        {
            if(!JsonNeutralModBlocks.loadOverworldTin().get("disableOre").getAsBoolean())
            {
                runGenerator(this.OverworldTinOre, world, random, chunkX, chunkZ, JsonNeutralModBlocks.loadOverworldTin().get("rarity").getAsInt(), JsonNeutralModBlocks.loadOverworldTin().get("minY").getAsInt(), JsonNeutralModBlocks.loadOverworldTin().get("maxY").getAsInt());
            }
        }

        if(LAModConstants.silverOre)
        {
            if(!JsonNeutralModBlocks.loadOverworldSilver().get("disableOre").getAsBoolean())
            {
                runGenerator(this.OverworldSilverOre, world, random, chunkX, chunkZ, JsonNeutralModBlocks.loadOverworldSilver().get("rarity").getAsInt(), JsonNeutralModBlocks.loadOverworldSilver().get("minY").getAsInt(), JsonNeutralModBlocks.loadOverworldSilver().get("maxY").getAsInt());
            }
        }
    }

    @Optional.Method(modid = "forestry")
    private void generateForestrySurfaceOres(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider)
    { BlockResourceOre resourcesBlock = PluginCore.getBlocks().resources; }

    private void generateVanillaNetherOres(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider)
    {
        if(!JsonParser.loadNetherCoalOre().get("disableOre").getAsBoolean())
        {
            runGenerator(this.netherCoalOre, world, random, chunkX, chunkZ, JsonParser.loadNetherCoalOre().get("rarity").getAsInt(), JsonParser.loadNetherCoalOre().get("minY").getAsInt(), JsonParser.loadNetherCoalOre().get("maxY").getAsInt());
        }

        if(!JsonParser.loadNetherDiamondOre().get("disableOre").getAsBoolean())
        {
            runGenerator(this.netherDiamondOre, world, random, chunkX, chunkZ, JsonParser.loadNetherDiamondOre().get("rarity").getAsInt(), JsonParser.loadNetherDiamondOre().get("minY").getAsInt(), JsonParser.loadNetherDiamondOre().get("maxY").getAsInt());
        }

        if(!JsonParser.loadNetherEmeraldOre().get("disableOre").getAsBoolean())
        {
            runGenerator(this.netherEmeraldOre, world, random, chunkX, chunkZ, JsonParser.loadNetherEmeraldOre().get("rarity").getAsInt(), JsonParser.loadNetherEmeraldOre().get("minY").getAsInt(), JsonParser.loadNetherEmeraldOre().get("maxY").getAsInt());
        }

        if(!JsonParser.loadNetherGoldOre().get("disableOre").getAsBoolean())
        {
            runGenerator(this.netherGoldOre, world, random, chunkX, chunkZ, JsonParser.loadNetherGoldOre().get("rarity").getAsInt(), JsonParser.loadNetherGoldOre().get("minY").getAsInt(), JsonParser.loadNetherGoldOre().get("maxY").getAsInt());
        }

        if(!JsonParser.loadNetherIronOre().get("disableOre").getAsBoolean())
        {
            runGenerator(this.netherIronOre, world, random, chunkX, chunkZ, JsonParser.loadNetherIronOre().get("rarity").getAsInt(), JsonParser.loadNetherIronOre().get("minY").getAsInt(), JsonParser.loadNetherIronOre().get("maxY").getAsInt());
        }

        if(!JsonParser.loadNetherLapisOre().get("disableOre").getAsBoolean())
        {
            runGenerator(this.netherLapisOre, world, random, chunkX, chunkZ, JsonParser.loadNetherLapisOre().get("rarity").getAsInt(), JsonParser.loadNetherLapisOre().get("minY").getAsInt(), JsonParser.loadNetherLapisOre().get("maxY").getAsInt());
        }

        if(!JsonParser.loadNetherRedstoneOre().get("disableOre").getAsBoolean())
        {
            runGenerator(this.netherRedstoneOre, world, random, chunkX, chunkZ, JsonParser.loadNetherRedstoneOre().get("rarity").getAsInt(), JsonParser.loadNetherRedstoneOre().get("minY").getAsInt(), JsonParser.loadNetherRedstoneOre().get("maxY").getAsInt());
        }
    }

    private void generateModdedNetherOres(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider)
    {
        if(LAModConstants.copperOre)
        {
            if(!JsonNeutralModBlocks.loadNetherCopper().get("disableOre").getAsBoolean())
            {
                runGenerator(this.netherCopperOre, world, random, chunkX, chunkZ, JsonNeutralModBlocks.loadNetherCopper().get("rarity").getAsInt(), JsonNeutralModBlocks.loadNetherCopper().get("minY").getAsInt(), JsonNeutralModBlocks.loadNetherCopper().get("maxY").getAsInt());
            }
        }

        if(LAModConstants.leadOre)
        {
            if(!JsonNeutralModBlocks.loadNetherLead().get("disableOre").getAsBoolean())
            {
                runGenerator(this.netherLeadOre, world, random, chunkX, chunkZ, JsonNeutralModBlocks.loadNetherLead().get("rarity").getAsInt(), JsonNeutralModBlocks.loadNetherLead().get("minY").getAsInt(), JsonNeutralModBlocks.loadNetherLead().get("maxY").getAsInt());
            }
        }

        if(LAModConstants.tinOre)
        {
            if(!JsonNeutralModBlocks.loadNetherTin().get("disableOre").getAsBoolean())
            {
                runGenerator(this.netherTinOre, world, random, chunkX, chunkZ, JsonNeutralModBlocks.loadNetherTin().get("rarity").getAsInt(), JsonNeutralModBlocks.loadNetherTin().get("minY").getAsInt(), JsonNeutralModBlocks.loadNetherTin().get("maxY").getAsInt());
            }
        }

        if(LAModConstants.silverOre)
        {
            if(!JsonNeutralModBlocks.loadNetherSilver().get("disableOre").getAsBoolean())
            {
                runGenerator(this.netherSilverOre, world, random, chunkX, chunkZ, JsonNeutralModBlocks.loadNetherSilver().get("rarity").getAsInt(), JsonNeutralModBlocks.loadNetherSilver().get("minY").getAsInt(), JsonNeutralModBlocks.loadNetherSilver().get("maxY").getAsInt());
            }
        }
    }

    // Taken from vanilla
    private void runGenerator (WorldGenerator generator, World world, Random rand, int chunkX, int chunkZ, int chanceToSpawn, int minHeight, int maxHeight)
    {
        if (minHeight < 0 || maxHeight > 256 || minHeight > maxHeight)
        {
            throw new IllegalArgumentException("Minimum or Maximum height out of bounds");
        }

        int heightDiff = maxHeight - minHeight + 1;

        for (int i = 0; i < chanceToSpawn; i++)
        {
            int x = chunkX * 16 + rand.nextInt(16);
            int y = minHeight + rand.nextInt(heightDiff);
            int z = chunkZ * 16 + rand.nextInt(16);
            generator.generate(world, rand, new BlockPos(x, y, z));
        }
    }
}
