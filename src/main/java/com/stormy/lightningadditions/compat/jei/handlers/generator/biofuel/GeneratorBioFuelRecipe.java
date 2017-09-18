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

import mezz.jei.api.IGuiHelper;
import mezz.jei.api.gui.IDrawableAnimated;
import mezz.jei.api.gui.IDrawableStatic;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.BlankRecipeWrapper;
import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

import java.awt.*;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class GeneratorBioFuelRecipe extends BlankRecipeWrapper{

    private final List<List<ItemStack>> inputs;
    private final String cooldownString;
    private final String totalRF;
    private final int type;
    private final String allTypes = "All types!";
    private final IDrawableAnimated flame;

    public GeneratorBioFuelRecipe(IGuiHelper guiHelper, Collection<ItemStack> input, int cooldown, int rfPerTick, int isAllTypes){
        List<ItemStack> inputList = new ArrayList<ItemStack>(input);
        this.inputs = Collections.singletonList(inputList);

        NumberFormat numberFormat = NumberFormat.getNumberInstance();
        this.cooldownString = String.format("Use Time: %ss", numberFormat.format(cooldown/20));
        this.totalRF = String.format("Total RF: %s", numberFormat.format(rfPerTick * cooldown));
        this.type = isAllTypes;

        ResourceLocation backgroundLocation = new ResourceLocation("minecraft", "textures/gui/container/furnace.png");
        IDrawableStatic flameDrawable = guiHelper.createDrawable(backgroundLocation, 176, 0, 14, 14);
        flame = guiHelper.createAnimatedDrawable(flameDrawable, 300, IDrawableAnimated.StartDirection.TOP, true);

    }

    @Override
    public void getIngredients(IIngredients iIngredients) {
        iIngredients.setInputLists(ItemStack.class, inputs);
    }

    @Override
    public void drawInfo(Minecraft minecraft, int recipeWidth, int recipeHeight, int mouseX, int mouseY) {
        flame.draw(minecraft, 2, 0);
        minecraft.fontRenderer.drawString(cooldownString, 24, 3, Color.DARK_GRAY.getRGB());
        minecraft.fontRenderer.drawString(totalRF, 24, 13, Color.DARK_GRAY.getRGB());
       if (type > 0) {
           switch (type){
               default:
               case 1:
                   minecraft.fontRenderer.drawString("All Seeds", 24, 23, Color.DARK_GRAY.getRGB());
                   break;
               case 2:
                   minecraft.fontRenderer.drawString("All Saplings", 24, 23, Color.DARK_GRAY.getRGB());
                   break;
               case 3:
                   minecraft.fontRenderer.drawString("All Food", 24, 23, Color.DARK_GRAY.getRGB());
                   break;
           }
       }
    }


}
