/*
 * ********************************************************************************
 * Copyright (c) 2017 StormyMode, MiningMark48. All Rights Reserved!
 * This file is part of Lightning Additions (MC-Mod).
 *
 * This project cannot be copied and/or distributed without the express
 * permission of StormyMode, MiningMark48 (Developers)!
 * ********************************************************************************
 */

package com.stormy.lightningadditions.init;

import com.stormy.lightningadditions.block.BlockEnderHopper;
import net.minecraft.block.Block;
import net.minecraft.item.Item;

import java.util.ArrayList;
import java.util.List;

public class ModBlockContainers
{
    public static List<Block> containerblocks = new ArrayList<Block>();

    public static Block ender_hopper;

    public static void preInit()
    {
        ender_hopper = new BlockEnderHopper();
    }

    public static void registerRenders()
    {

        for(Block i : containerblocks){
            ModItems.registerItemRender(Item.getItemFromBlock(i), 0);
        }


    }
}
