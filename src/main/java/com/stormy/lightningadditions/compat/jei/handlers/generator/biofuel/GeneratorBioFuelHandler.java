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

package com.stormy.lightningadditions.compat.jei.handlers.generator.biofuel;

import com.stormy.lightningadditions.reference.ModInformation;
import mezz.jei.api.recipe.IRecipeHandler;
import mezz.jei.api.recipe.IRecipeWrapper;

public class GeneratorBioFuelHandler implements IRecipeHandler<GeneratorBioFuelRecipe>{

    @Override
    public Class<GeneratorBioFuelRecipe> getRecipeClass() {
        return GeneratorBioFuelRecipe.class;
    }

    @Override
    public String getRecipeCategoryUid(GeneratorBioFuelRecipe generatorFuelRecipe) {
        return ModInformation.MODID + ".generator.bio_fuel";
    }

    @Override
    public IRecipeWrapper getRecipeWrapper(GeneratorBioFuelRecipe generatorFuelRecipe) {
        return generatorFuelRecipe;
    }

    @Override
    public boolean isRecipeValid(GeneratorBioFuelRecipe generatorFuelRecipe) {
        return true;
    }

}
