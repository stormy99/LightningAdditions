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

package com.stormy.lightninglib.lib.item;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;

public class ModItemBlock extends ItemBlock{

    public ModItemBlock(Block block){
        super(block);
        setRegistryName(block.getRegistryName());
    }

}
