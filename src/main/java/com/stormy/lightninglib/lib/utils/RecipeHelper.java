package com.stormy.lightninglib.lib.utils;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.RecipeSorter;
import org.apache.commons.lang3.Validate;

public class RecipeHelper
{

    public static void registerRecipeSorter(ResourceLocation name, Class<? extends IRecipe> recipeClass, RecipeSorter.Category category)
    { RecipeSorter.register(name.toString(), recipeClass, category, ""); }

    static boolean validateRecipes = false;

    public static void addSmelting(Block input, ItemStack output, float xp) {
        if (validateRecipes)
        {
            Validate.notNull(input);
            Validate.notNull(output);
            Validate.notNull(output.getItem()); }
        GameRegistry.addSmelting(input, output, xp);
    }

    public static void addSmelting(Item input, ItemStack output, float xp)
    { if (validateRecipes)
    {
            Validate.notNull(input);
            Validate.notNull(output);
            Validate.notNull(output.getItem()); }
        GameRegistry.addSmelting(input, output, xp); }

    public static void addSmelting(ItemStack input, ItemStack output, float xp) {
        if (validateRecipes)
        { Validate.notNull(input);
            Validate.notNull(input.getItem());
            Validate.notNull(output);
            Validate.notNull(output.getItem()); }
        GameRegistry.addSmelting(input, output, xp); }

    public static void addSmelting(ItemStack input, ItemStack output) { addSmelting(input, output, 1F); }

    public static void addSmelting(Item input, ItemStack output) { addSmelting(input, output, 1F); }

    public static void addSmelting(Block input, ItemStack output) { addSmelting(input, output, 1F); }
}
