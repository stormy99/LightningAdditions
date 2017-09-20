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

import com.stormy.lightningadditions.utility.logger.LALogger;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraftforge.fml.common.ModContainer;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.OreDictionary;

public class ModRecipes {

    public static void init(){
        //Items
        GameRegistry.addSmelting(new ItemStack(ModBlocks.NETHER_COAL_ORE), new ItemStack(Items.COAL), 0.1f);
        GameRegistry.addSmelting(new ItemStack(ModBlocks.NETHER_IRON_ORE), new ItemStack(Items.IRON_INGOT), 0.1f);
        GameRegistry.addSmelting(new ItemStack(ModBlocks.NETHER_GOLD_ORE), new ItemStack(Items.GOLD_INGOT), 0.1f);
        GameRegistry.addSmelting(new ItemStack(ModBlocks.NETHER_DIAMOND_ORE), new ItemStack(Items.DIAMOND), 0.1f);
        GameRegistry.addSmelting(new ItemStack(ModBlocks.NETHER_EMERALD_ORE), new ItemStack(Items.EMERALD), 0.1f);
        GameRegistry.addSmelting(new ItemStack(ModBlocks.NETHER_LAPIS_ORE), new ItemStack(Items.DYE, 1, 4), 0.1f);
        GameRegistry.addSmelting(new ItemStack(ModBlocks.NETHER_COPPER_ORE), new ItemStack(ModItems.COPPER_INGOT), 0.1f);
        GameRegistry.addSmelting(new ItemStack(ModBlocks.NETHER_LEAD_ORE), new ItemStack(ModItems.LEAD_INGOT), 0.1f);
        GameRegistry.addSmelting(new ItemStack(ModBlocks.NETHER_SILVER_ORE), new ItemStack(ModItems.SILVER_INGOT), 0.1f);
        GameRegistry.addSmelting(new ItemStack(ModBlocks.NETHER_TIN_ORE), new ItemStack(ModItems.TIN_INGOT), 0.1f);
        GameRegistry.addSmelting(new ItemStack(ModBlocks.OVERWORLD_COPPER_ORE), new ItemStack(ModItems.COPPER_INGOT), 0.1f);
        GameRegistry.addSmelting(new ItemStack(ModBlocks.OVERWORLD_LEAD_ORE), new ItemStack(ModItems.LEAD_INGOT), 0.1f);
        GameRegistry.addSmelting(new ItemStack(ModBlocks.OVERWORLD_SILVER_ORE), new ItemStack(ModItems.SILVER_INGOT), 0.1f);
        GameRegistry.addSmelting(new ItemStack(ModBlocks.OVERWORLD_TIN_ORE), new ItemStack(ModItems.TIN_INGOT), 0.1f);

        //Blocks
        GameRegistry.addSmelting(new ItemStack(Blocks.GLASS), new ItemStack(ModBlocks.clear_glass), 0.05f);

        LALogger.log("LA Recipes registered.");
    }

}
