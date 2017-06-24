/*
 * ********************************************************************************
 * Copyright (c) 2017 StormyMode, MiningMark48. All Rights Reserved!
 * This file is part of Lightning Additions (MC-Mod).
 *
 * This project cannot be copied and/or distributed without the express
 * permission of StormyMode, MiningMark48 (Developers)!
 * ********************************************************************************
 */

package com.stormy.lightningadditions.block.ore;

import java.util.Random;
import com.stormy.lightningadditions.creativetab.CreativeTabLA;
import net.minecraft.block.material.Material;


public class OverworldOreBlock extends LAOreBase
{

    public OverworldOreBlock(String unlocalizedName)
    {
        super(unlocalizedName, Material.ROCK, 3f, 15f);
        this.setRegistryName(unlocalizedName);
        this.setCreativeTab(CreativeTabLA.LA_TAB);
    }
}
