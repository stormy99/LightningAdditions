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

import net.minecraftforge.oredict.OreDictionary;

public class ModOreDict {

    public static void registerOres(){
        //Ores
        OreDictionary.registerOre("oreCoal", ModBlocks.NETHER_COAL_ORE);
        OreDictionary.registerOre("oreIron", ModBlocks.NETHER_IRON_ORE);
        OreDictionary.registerOre("oreDiamond", ModBlocks.NETHER_DIAMOND_ORE);
        OreDictionary.registerOre("oreEmerald", ModBlocks.NETHER_EMERALD_ORE);
        OreDictionary.registerOre("oreGold", ModBlocks.NETHER_GOLD_ORE);
        OreDictionary.registerOre("oreRedstone", ModBlocks.NETHER_REDSTONE_ORE);
        OreDictionary.registerOre("oreLapis", ModBlocks.NETHER_LAPIS_ORE);
        OreDictionary.registerOre("oreCopper", ModBlocks.NETHER_COPPER_ORE);
        OreDictionary.registerOre("oreCopper", ModBlocks.OVERWORLD_COPPER_ORE);
        OreDictionary.registerOre("oreLead", ModBlocks.NETHER_LEAD_ORE);
        OreDictionary.registerOre("oreLead", ModBlocks.OVERWORLD_LEAD_ORE);
        OreDictionary.registerOre("oreSilver", ModBlocks.NETHER_SILVER_ORE);
        OreDictionary.registerOre("oreSilver", ModBlocks.OVERWORLD_SILVER_ORE);
        OreDictionary.registerOre("oreTin", ModBlocks.NETHER_TIN_ORE);
        OreDictionary.registerOre("oreTin", ModBlocks.OVERWORLD_TIN_ORE);

        //Ingots
        OreDictionary.registerOre("ingotCopper", ModItems.COPPER_INGOT);
        OreDictionary.registerOre("ingotLead", ModItems.LEAD_INGOT);
        OreDictionary.registerOre("ingotSilver", ModItems.SILVER_INGOT);
        OreDictionary.registerOre("ingotTin", ModItems.TIN_INGOT);

        //Misc
        OreDictionary.registerOre("stickWood", ModItems.stone_stick);
        OreDictionary.registerOre("blockSponge", ModBlocks.sponge);

    }

}
