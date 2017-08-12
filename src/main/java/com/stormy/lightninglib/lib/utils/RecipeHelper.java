package com.stormy.lightninglib.lib.utils;

import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.oredict.RecipeSorter;
import net.minecraftforge.oredict.ShapelessOreRecipe;
import java.util.List;

public class RecipeHelper
{
    public static CraftingManager getCraftingManager()
    { return CraftingManager.getInstance(); }

    public static List<Object> getRecipeOutput(ShapelessOreRecipe recipe)
    { return recipe.getInput(); }

    public static void registerRecipeSorter(ResourceLocation name, Class<? extends IRecipe> recipeClass)
    { registerRecipeSorter(name, recipeClass, RecipeSorter.Category.SHAPELESS); }

    public static void registerRecipeSorter(ResourceLocation name, Class<? extends IRecipe> recipeClass, RecipeSorter.Category category)
    { RecipeSorter.register(name.toString(), recipeClass, category, ""); }
}
