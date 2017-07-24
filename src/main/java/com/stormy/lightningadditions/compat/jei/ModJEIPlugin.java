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

package com.stormy.lightningadditions.compat.jei;

import com.stormy.lightningadditions.client.gui.generator.GuiBioFuelGenerator;
import com.stormy.lightningadditions.compat.jei.generator.biofuel.GeneratorBioFuelCategory;
import com.stormy.lightningadditions.compat.jei.generator.biofuel.GeneratorBioFuelHandler;
import com.stormy.lightningadditions.compat.jei.generator.biofuel.GeneratorBioFuelRecipeMaker;
import com.stormy.lightningadditions.init.ModBlocks;
import com.stormy.lightningadditions.reference.ModInformation;
import mezz.jei.api.*;
import mezz.jei.api.ingredients.IModIngredientRegistration;
import mezz.jei.api.recipe.IRecipeCategoryRegistration;
import net.minecraft.item.ItemStack;

@JEIPlugin
public class ModJEIPlugin implements IModPlugin{

    public static IJeiHelpers jeiHelpers;

    @Override
    public void registerItemSubtypes(ISubtypeRegistry iSubtypeRegistry) {

    }

    @Override
    public void registerIngredients(IModIngredientRegistration iModIngredientRegistration) {

    }

    @Override
    public void registerCategories(IRecipeCategoryRegistration iRecipeCategoryRegistration) {

    }

    @Override
    public void register(IModRegistry registry) {

        jeiHelpers = registry.getJeiHelpers();
        final IGuiHelper guiHelper = jeiHelpers.getGuiHelper();

        //Hide from JEI
        jeiHelpers.getIngredientBlacklist().addIngredientToBlacklist(new ItemStack(ModBlocks.mining_portal));

        //Biofuel Generator
        registry.addRecipeCategories(
                new GeneratorBioFuelCategory(guiHelper)
        );
        registry.addRecipeHandlers(
                new GeneratorBioFuelHandler()
        );
        registry.addRecipeCategoryCraftingItem(new ItemStack(ModBlocks.biofuel_generator), ModInformation.MODID + ".generator.bio_fuel");
        registry.addRecipes(GeneratorBioFuelRecipeMaker.getFuelRecipes(jeiHelpers));
        registry.addRecipeClickArea(GuiBioFuelGenerator.class, 64, 16, 102, 27, ModInformation.MODID + ".generator.bio_fuel");
    }

    @Override
    public void onRuntimeAvailable(IJeiRuntime iJeiRuntime) {

    }
}
