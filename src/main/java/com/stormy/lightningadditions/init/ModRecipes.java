package com.stormy.lightningadditions.init;

import com.stormy.lightningadditions.utility.logger.LALogger;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
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

        GameRegistry.addRecipe(new ItemStack(ModItems.stone_stick, 4), "x", "x", 'x', new ItemStack(Blocks.COBBLESTONE));
        GameRegistry.addRecipe(new ItemStack(ModItems.stone_stick, 4), "x", "x", 'x', new ItemStack(Blocks.STONE, 1, OreDictionary.WILDCARD_VALUE));
        GameRegistry.addRecipe(new ItemStack(ModItems.ender_backpack), "xyx", "xzx", "xax", 'x', new ItemStack(Items.LEATHER), 'y', new ItemStack(Items.IRON_INGOT), 'z', new ItemStack(Items.ENDER_EYE), 'a', new ItemStack(Blocks.CHEST, 1, OreDictionary.WILDCARD_VALUE));
        GameRegistry.addRecipe(new ItemStack(ModItems.emerald_apple), "xxx", "xyx", "xxx", 'x', new ItemStack(Items.EMERALD), 'y', new ItemStack(Items.APPLE));
        GameRegistry.addRecipe(new ItemStack(ModItems.sponge_stick), "  x", " y ", "y  ", 'x', new ItemStack(Blocks.SPONGE), 'y', new ItemStack(Items.STICK, 1, OreDictionary.WILDCARD_VALUE));
        GameRegistry.addRecipe(new ItemStack(ModItems.sponge_stick), "  x", " y ", "y  ", 'x', new ItemStack(ModBlocks.sponge), 'y', new ItemStack(Items.STICK, 1, OreDictionary.WILDCARD_VALUE));

        //Blocks
        GameRegistry.addRecipe(new ItemStack(Blocks.GLASS), new ItemStack(ModBlocks.clear_glass), 0.05f);

        GameRegistry.addRecipe(new ItemStack(ModBlocks.breaker), "xxx", "xyz", "xxx", 'x', new ItemStack(Blocks.COBBLESTONE), 'y', new ItemStack(Items.REDSTONE), 'z', new ItemStack(Items.DIAMOND_PICKAXE));
        GameRegistry.addRecipe(new ItemStack(ModBlocks.placer), "xxx", "xyz", "xxx", 'x', new ItemStack(Blocks.COBBLESTONE), 'y', new ItemStack(Items.REDSTONE), 'z', new ItemStack(Items.REDSTONE));
        GameRegistry.addRecipe(new ItemStack(ModBlocks.igniter), "xxx", "xyz", "xxx", 'x', new ItemStack(Blocks.COBBLESTONE), 'y', new ItemStack(Items.REDSTONE), 'z', new ItemStack(Items.FLINT_AND_STEEL));
        GameRegistry.addRecipe(new ItemStack(ModBlocks.compressed_bookshelf), "xxx", "zyz", "xxx", 'x', new ItemStack(Blocks.BOOKSHELF), 'y', new ItemStack(Items.BOOK), 'z', new ItemStack(Items.GOLD_INGOT));
        GameRegistry.addRecipe(new ItemStack(ModBlockContainers.ender_hopper), "xxx", "zyz", " a ", 'x', new ItemStack(Items.IRON_INGOT), 'y', new ItemStack(Blocks.HOPPER), 'z', new ItemStack(Items.ENDER_EYE), 'a', new ItemStack(ModItems.tachyon_shard));
        GameRegistry.addRecipe(new ItemStack(ModBlocks.magnetized_chest), "xxx", "yzy", "xxx", 'x', new ItemStack(Blocks.COBBLESTONE), 'y', new ItemStack(Blocks.CHEST), 'z', new ItemStack(ModItems.tachyon_shard));
        GameRegistry.addRecipe(new ItemStack(ModBlocks.reinforced_obsidian, 4), "xyx", "yxy", "xyx", 'x', new ItemStack(Blocks.OBSIDIAN), 'y', new ItemStack(Blocks.IRON_BARS));
        GameRegistry.addRecipe(new ItemStack(ModBlocks.reinforced_obsidianglass, 4), "xyx", "yxy", "xyx", 'x', new ItemStack(ModBlocks.reinforced_obsidian), 'y', new ItemStack(Blocks.GLASS));
        GameRegistry.addRecipe(new ItemStack(ModBlocks.share_xp), "x x", "yzy", "aaa", 'x', new ItemStack(Items.ENDER_EYE), 'y', new ItemStack(Items.GLOWSTONE_DUST), 'z', new ItemStack(ModItems.tachyon_shard), 'a', new ItemStack(Blocks.OBSIDIAN));
        GameRegistry.addRecipe(new ItemStack(ModBlocks.void_block), "xxx", "zyz", "xxx", 'x', new ItemStack(Blocks.WOOL));
        GameRegistry.addRecipe(new ItemStack(ModBlocks.particle_accellerator), "xyx", "yzy", "aba", 'x', new ItemStack(Items.DIAMOND), 'y', new ItemStack(Items.IRON_INGOT), 'z', new ItemStack(Items.GLOWSTONE_DUST), 'a', new ItemStack(Items.GOLD_INGOT), 'b', new ItemStack(Items.REDSTONE));

        LALogger.log("LA Recipes registered.");
    }

}
