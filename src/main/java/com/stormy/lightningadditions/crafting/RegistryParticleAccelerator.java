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

import com.stormy.lightningadditions.init.ModItems;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

import javax.annotation.Nonnull;
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
        addRecipe(new ItemStack(ModItems.tachyon_shard), new ItemStack(ModItems.charged_tachyon), new ItemStack(Items.GLOWSTONE_DUST));
        addRecipe(new ItemStack(ModItems.charged_tachyon), new ItemStack(ModItems.inert_tachyon), new ItemStack(Items.GLOWSTONE_DUST));
        addRecipe(new ItemStack(ModItems.inert_tachyon), new ItemStack(Items.DIAMOND), new ItemStack(Items.GLOWSTONE_DUST, 2));
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
            if (bonus != ItemStack.EMPTY){
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
