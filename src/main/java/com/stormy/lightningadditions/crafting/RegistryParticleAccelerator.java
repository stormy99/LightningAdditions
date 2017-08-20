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

import com.google.common.collect.Maps;
import com.stormy.lightningadditions.init.ModItems;
import com.stormy.lightningadditions.utility.logger.LALogger;
import net.minecraft.item.ItemStack;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.HashMap;
import java.util.Map;

public class RegistryParticleAccelerator {

    private static RegistryParticleAccelerator INSTANCE = new RegistryParticleAccelerator();
    private final Map<Map<ItemStack, ItemStack>, ItemStack> recipes = new HashMap<>();

    public static RegistryParticleAccelerator instance()
    {
        return INSTANCE;
    }

    public void registerRecipes(){
        recipes.clear();
        addRecipe(new ItemStack(ModItems.atomic_magnet), new ItemStack(ModItems.COPPER_INGOT), new ItemStack(ModItems.emerald_apple));
        addRecipe(new ItemStack(ModItems.atomic_inhibitor), new ItemStack(ModItems.SILVER_INGOT), ItemStack.EMPTY);
    }

    /**
     *
     * @param itemToCraft
     *  Item that is to be crafted
     * @param itemToUse
     *  Item that is to be used
     * @param bonus
     *  Item that is to be given as a bonus.
     *  Use ItemStack.EMPTY if you wish to not have an output.
     *
     */
    public void addRecipe(@Nonnull ItemStack itemToCraft, @Nonnull ItemStack itemToUse, @Nonnull ItemStack bonus){
        Map<ItemStack, ItemStack> outputs = new HashMap<ItemStack, ItemStack>();
        if (!itemToCraft.isEmpty() && !itemToUse.isEmpty()) {
            if (!bonus.isEmpty()){
                outputs.put(itemToCraft, bonus);
            }else{
                outputs.put(itemToCraft, ItemStack.EMPTY);
            }
        }
        this.recipes.put(outputs, itemToUse);
    }

    public Map<Map<ItemStack, ItemStack>, ItemStack> getRecipes(){
        return recipes;
    }

    /**
     *
     * @param stack
     *  Stack to get result for
     * @return
     *  Returns HashMap of result <Main, Bonus>
     */
    public Map<ItemStack, ItemStack> getResult(ItemStack stack) {
        Map<ItemStack, ItemStack> entryToReturn = new HashMap<>();
        for (Map.Entry<Map<ItemStack, ItemStack>, ItemStack> entry : getRecipes().entrySet())
        {
            if (this.compareItemStacks(stack, (ItemStack)entry.getValue()))
            {
                entryToReturn = entry.getKey();
                return entryToReturn;
            }
        }

        entryToReturn.put(ItemStack.EMPTY, ItemStack.EMPTY);
        return entryToReturn;
    }

    private boolean compareItemStacks(ItemStack stack1, ItemStack stack2)
    {
        return stack2.getItem() == stack1.getItem() && (stack2.getMetadata() == 32767 || stack2.getMetadata() == stack1.getMetadata());
    }

}
