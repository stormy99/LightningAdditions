/*
 * ********************************************************************************
 * Copyright (c) 2017 StormyMode, MiningMark48. All Rights Reserved!
 * This file is part of Lightning Additions (MC-Mod).
 *
 * This project cannot be copied and/or distributed without the express
 * permission of StormyMode, MiningMark48 (Developers)!
 * ********************************************************************************
 */

package com.stormy.lightningadditions.tile.resource;

import com.stormy.lightningadditions.tile.base.LATile;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class TileEntitySky extends LATile
{

    @Override
    @SideOnly(Side.CLIENT)
    public double getMaxRenderDistanceSquared()
    { return Double.POSITIVE_INFINITY; }

}
