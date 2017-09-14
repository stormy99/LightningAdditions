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

package com.stormy.lightningadditions.block.resource;

import net.minecraft.block.BlockTorch;
import net.minecraft.block.SoundType;

public class BlockStoneTorch extends BlockTorch
{
    public BlockStoneTorch()
    {
        super();
        setHardness(0.0F);
        setLightLevel(0.9375F);
        setSoundType(SoundType.STONE); }
}
