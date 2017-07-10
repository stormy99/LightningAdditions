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

package com.stormy.lightningadditions.handler.generator;

import com.google.common.collect.Lists;
import net.minecraft.init.Items;
import net.minecraft.item.ItemSeedFood;
import net.minecraft.item.ItemSeeds;
import net.minecraft.item.ItemStack;

import javax.annotation.Nonnull;
import java.util.List;

public class BioFuelRegistry {

    private static List<IBioFuelHandler> fuelHandlers = Lists.newArrayList();

    public static void init(){
        registerFuelHandler(new BioFuelHandler());
    }

    public static void registerFuelHandler(IBioFuelHandler handler){
        fuelHandlers.add(handler);
    }

    public static int getFuelValue(@Nonnull ItemStack itemStack)
    {
        int fuelValue = 0;
        for (IBioFuelHandler handler : fuelHandlers)
        {
            fuelValue = Math.max(fuelValue, handler.getBurntime(itemStack));
        }
        return fuelValue;
    }

    private static class BioFuelHandler implements IBioFuelHandler{

        @Override
        public int getBurntime(ItemStack fuel) {
            if (fuel.isItemEqual(new ItemStack(Items.WHEAT))) {
                return 60;
            }else if (fuel.isItemEqual(new ItemStack(Items.CARROT))){
                return 120;
            }

            if (fuel.getItem() instanceof ItemSeeds){
                return 20;
            }

            return 0;
        }

    }

}
