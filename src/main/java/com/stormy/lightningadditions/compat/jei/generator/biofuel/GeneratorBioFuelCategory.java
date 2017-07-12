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

package com.stormy.lightningadditions.compat.jei.generator.biofuel;

import com.stormy.lightningadditions.init.ModBlocks;
import com.stormy.lightningadditions.reference.ModInformation;
import mezz.jei.api.IGuiHelper;
import mezz.jei.api.gui.*;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.BlankRecipeCategory;
import mezz.jei.api.recipe.IRecipeWrapper;
import net.minecraft.util.ResourceLocation;

public class GeneratorBioFuelCategory<T extends IRecipeWrapper> extends BlankRecipeCategory<T>{

    private final IDrawable background;
    private final IDrawable flame;
    private final String localizedName;

    protected final ResourceLocation backgroundLocation = new ResourceLocation("minecraft", "textures/gui/container/furnace.png");

    public GeneratorBioFuelCategory(IGuiHelper guiHelper){
        background = guiHelper.createDrawable(backgroundLocation, 55, 38, 18, 32, 0, 0, 0, 80);

        IDrawableStatic flameDrawable = guiHelper.createDrawable(backgroundLocation, 176, 0, 14, 14);
        flame = guiHelper.createAnimatedDrawable(flameDrawable, 300, IDrawableAnimated.StartDirection.TOP, true);
        localizedName = ModBlocks.biofuel_generator.getLocalizedName() + " Fuel";
    }

    @Override
    public String getUid() {
        return ModInformation.MODID + ".generator.bio_fuel";
    }

    @Override
    public String getTitle() {
        return localizedName;
    }

    @Override
    public String getModName() {
        return ModInformation.MODNAME;
    }

    @Override
    public IDrawable getBackground() {
        return background;
    }

    @Override
    public void setRecipe(IRecipeLayout iRecipeLayout, T t, IIngredients iIngredients) {
        IGuiItemStackGroup guiItemStacks = iRecipeLayout.getItemStacks();

        guiItemStacks.init(0, true, 0, 14);
        guiItemStacks.set(iIngredients);
    }

}
