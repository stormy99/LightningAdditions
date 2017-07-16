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
import net.minecraft.block.BlockSapling;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemSeeds;
import net.minecraft.item.ItemStack;

import javax.annotation.Nonnull;
import java.util.List;

public class BioFuelRegistry {

    private static List<IBioFuelHandler> fuelHandlers = Lists.newArrayList();
    private static List<ItemStack> fuels = Lists.newArrayList();

    public static void init(){
        registerFuelHandler(new BioFuelHandler());

        //JEI
        registerFuels(new ItemStack(Items.WHEAT));
        registerFuels(new ItemStack(Items.LEATHER));
        registerFuels(new ItemStack(Items.ROTTEN_FLESH));
        registerFuels(new ItemStack(Items.RABBIT_HIDE));
        registerFuels(new ItemStack(Items.RABBIT_FOOT));
        registerFuels(new ItemStack(Items.NETHER_WART));
        registerFuels(new ItemStack(Items.SPIDER_EYE));
        registerFuels(new ItemStack(Items.FERMENTED_SPIDER_EYE));
        registerFuels(new ItemStack(Items.SPECKLED_MELON));
        registerFuels(new ItemStack(Items.REEDS));

        registerFuels(new ItemStack(Blocks.SAPLING));
        registerFuels(new ItemStack(Items.APPLE));
        registerFuels(new ItemStack(Items.WHEAT_SEEDS));
    }

    public static void registerFuelHandler(IBioFuelHandler handler){
        fuelHandlers.add(handler);
    }

    public static void registerFuels(ItemStack stack){
        fuels.add(stack);
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

    public static List<ItemStack> getFuels(){
        return fuels;
    }

    private static class BioFuelHandler implements IBioFuelHandler{

        @Override
        public int getBurntime(ItemStack fuel) {

            if (fuel.isItemEqual(new ItemStack(Items.WHEAT))) {
                return 60;
            }else if (fuel.isItemEqual(new ItemStack(Items.LEATHER))){
                return 140;
            }else if (fuel.isItemEqual(new ItemStack(Items.ROTTEN_FLESH))){
                return 40;
            }else if (fuel.isItemEqual(new ItemStack(Items.RABBIT_HIDE))){
                return 40;
            }else if (fuel.isItemEqual(new ItemStack(Items.RABBIT_FOOT))){
                return 160;
            }else if (fuel.isItemEqual(new ItemStack(Items.NETHER_WART))){
                return 120;
            }else if (fuel.isItemEqual(new ItemStack(Items.SPIDER_EYE))){
                return 80;
            }else if (fuel.isItemEqual(new ItemStack(Items.FERMENTED_SPIDER_EYE))){
                return 120;
            }else if (fuel.isItemEqual(new ItemStack(Items.SPECKLED_MELON))){
                return 180;
            }else if (fuel.isItemEqual(new ItemStack(Items.REEDS))){
                return 40;
            }


            else if (fuel.getItem() instanceof ItemSeeds){
                return 20;
            }else if (fuel.getItem() instanceof ItemBlock && ((ItemBlock) fuel.getItem()).getBlock() instanceof BlockSapling){
                return 20;
            }else if (fuel.getItem() instanceof ItemFood){
                return 60;
            }

            return 0;
        }

    }

}
