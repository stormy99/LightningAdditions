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

package com.stormy.lightningadditions.crafting;

import com.google.common.collect.Lists;
import com.stormy.lightningadditions.init.ModItems;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;

import java.util.List;

public class RegistryParticleAccelerator {

    private final List<IRecipe> recipes = Lists.<IRecipe>newArrayList();

    public void registerRecipes(){
        addRecipe(new ItemStack(ModItems.atomic_magnet), new ItemStack(ModItems.COPPER_INGOT), new ItemStack(ModItems.emerald_apple));
        addRecipe(new ItemStack(ModItems.atomic_inhibitor), new ItemStack(ModItems.SILVER_INGOT));
    }

    public void addRecipe(ItemStack itemToCraft, ItemStack itemToUse, ItemStack bonus){
        this.recipes.add(new ParticleAcceleratorRecipe(itemToCraft, bonus, itemToUse));
    }

    public void addRecipe(ItemStack itemToCraft, ItemStack itemToUse){
        this.recipes.add(new ParticleAcceleratorRecipe(itemToCraft, ItemStack.EMPTY, itemToUse));
    }

    public List<IRecipe> getRecipes(){
        return recipes;
    }

}
