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

import com.stormy.lightningadditions.creativetab.CreativeTabLA;
import com.stormy.lightningadditions.init.ModBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraftforge.oredict.OreDictionary;


public class OverworldOreBlock extends LAOreBase
{

    public OverworldOreBlock(String unlocalizedName)
    {
        super(unlocalizedName, Material.ROCK, 3f, 15f);
        this.setRegistryName(unlocalizedName);
        this.setCreativeTab(CreativeTabLA.LA_TAB_ORES);

        if (getName() != null && getOre() != null) OreDictionary.registerOre(getName(), getOre());

    }

    private String getName(){
        return this == ModBlocks.OVERWORLD_COPPER_ORE ? "oreCopper" :
                this == ModBlocks.OVERWORLD_LEAD_ORE ? "oreLead" :
                        this == ModBlocks.OVERWORLD_SILVER_ORE ? "oreSilver" :
                                this == ModBlocks.OVERWORLD_TIN_ORE ? "oreTin" :
                                        null;
    }

    private Block getOre(){
        return this;
    }

}
