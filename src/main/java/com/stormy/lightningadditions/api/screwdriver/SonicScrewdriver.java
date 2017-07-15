/*
 * ********************************************************************************
 * Copyright (c) 2017 StormyMode, MiningMark48. All Rights Reserved!
 * This file is part of Lightning Additions (MC-Mod).
 *
 * This project cannot be copied and/or distributed without the express
 * permission of StormyMode, MiningMark48 (Developers)!
 * ********************************************************************************
 */

package com.stormy.lightningadditions.api.screwdriver;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.RayTraceResult;

public class SonicScrewdriver implements IScrewdriver
{
    /**
     * SonicScrewdriver API
     * Default implementation that only applies to blocks.
     */

    @Override
    public boolean canSonic(EntityPlayer player, SonicTarget target)
    {return target.getType() == RayTraceResult.Type.BLOCK;}

    @Override
    public void beforeUse(EntityPlayer player, SonicTarget target) {}

    @Override
    public void afterUse(EntityPlayer player, SonicTarget target) {}

}
