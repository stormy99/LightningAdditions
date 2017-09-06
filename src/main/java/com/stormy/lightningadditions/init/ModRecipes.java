package com.stormy.lightningadditions.init;

import com.stormy.lightningadditions.utility.logger.LALogger;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
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

        GameRegistry.addRecipe(new ItemStack(ModItems.stone_stick, 4), "x", "x", 'x', new ItemStack(Blocks.COBBLESTONE));
        GameRegistry.addRecipe(new ItemStack(ModItems.stone_stick, 4), "x", "x", 'x', new ItemStack(Blocks.STONE, 1, OreDictionary.WILDCARD_VALUE));
        GameRegistry.addRecipe(new ItemStack(ModItems.ender_backpack), "xyx", "xzx", "xax", 'x', new ItemStack(Items.LEATHER), 'y', new ItemStack(Items.IRON_INGOT), 'z', new ItemStack(Items.ENDER_EYE), 'a', new ItemStack(Blocks.CHEST, 1, OreDictionary.WILDCARD_VALUE));
        GameRegistry.addRecipe(new ItemStack(ModItems.emerald_apple), "xxx", "xyx", "xxx", 'x', new ItemStack(Items.EMERALD), 'y', new ItemStack(Items.APPLE));
        GameRegistry.addRecipe(new ItemStack(ModItems.sponge_stick), "  x", " y ", "y  ", 'x', new ItemStack(Blocks.SPONGE), 'y', new ItemStack(Items.STICK, 1, OreDictionary.WILDCARD_VALUE));
        GameRegistry.addRecipe(new ItemStack(ModItems.sponge_stick), "  x", " y ", "y  ", 'x', new ItemStack(ModBlocks.sponge), 'y', new ItemStack(Items.STICK, 1, OreDictionary.WILDCARD_VALUE));

        //Blocks
        GameRegistry.addRecipe(new ItemStack(ModBlocks.particle_accellerator), "xyx", "yzy", "aba", 'x', new ItemStack(Items.DIAMOND), 'y', new ItemStack(Items.IRON_INGOT), 'z', new ItemStack(Items.GLOWSTONE_DUST), 'a', new ItemStack(Items.GOLD_INGOT), 'b', new ItemStack(Items.REDSTONE));

        LALogger.log("LA Recipes registered.");
    }

}
