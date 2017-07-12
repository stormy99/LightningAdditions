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

import com.stormy.lightningadditions.handler.generator.BioFuelRegistry;
import mezz.jei.api.IGuiHelper;
import mezz.jei.api.IJeiHelpers;
import mezz.jei.api.recipe.IStackHelper;
import net.minecraft.block.BlockSapling;
import net.minecraft.init.Items;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemSeeds;
import net.minecraft.item.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class GeneratorBioFuelRecipeMaker {

    private GeneratorBioFuelRecipeMaker(){

    }

    public static List<GeneratorBioFuelRecipe> getFuelRecipes(IJeiHelpers helpers){
        IGuiHelper guiHelper = helpers.getGuiHelper();
        IStackHelper stackHelper = helpers.getStackHelper();
        List<ItemStack> fuelStacks = BioFuelRegistry.getFuels();
        List<GeneratorBioFuelRecipe> fuelRecipes = new ArrayList<GeneratorBioFuelRecipe>(fuelStacks.size());

        for (ItemStack fuelStack : fuelStacks){
            if (fuelStack == null){
                continue;
            }

            List<ItemStack> fuels = stackHelper.getSubtypes(fuelStack);
            if (fuels.isEmpty()){
                continue;
            }
            int cooldownTime = getBurnTime(fuels.get(0));
            int rfPerTick = getRFPerTick(fuels.get(0));
            int type = getType(fuels.get(0));
            fuelRecipes.add(new GeneratorBioFuelRecipe(guiHelper, fuels, cooldownTime, rfPerTick, type));
        }

        return fuelRecipes;

    }

    private static int getBurnTime(ItemStack stack){
        return BioFuelRegistry.getFuelValue(stack);
    }

    private static int getRFPerTick(ItemStack stack){
        return 16;
    }

    private static int getType(ItemStack stack){

        if (stack.isItemEqual(new ItemStack(Items.NETHER_WART))){
            return 0;
        }else if (stack.isItemEqual(new ItemStack(Items.ROTTEN_FLESH))){
            return 0;
        }else if (stack.isItemEqual(new ItemStack(Items.SPIDER_EYE))){
            return 0;
        }

        else if (stack.getItem() instanceof ItemSeeds){
            return 1;
        }else if (stack.getItem() instanceof ItemBlock && ((ItemBlock) stack.getItem()).getBlock() instanceof BlockSapling){
            return 2;
        }else if (stack.getItem() instanceof ItemFood){
            return 3;
        }

        return 0;

    }

}
