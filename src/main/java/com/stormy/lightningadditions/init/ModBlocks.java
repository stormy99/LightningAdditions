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

import com.stormy.lightningadditions.block.*;
import com.stormy.lightningadditions.block.ore.NetherOreBlock;
import com.stormy.lightningadditions.block.ore.OverworldOreBlock;
import com.stormy.lightningadditions.creativetab.CreativeTabLA;
import com.stormy.lightningadditions.reference.ModInformation;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.OreDictionary;

public class ModBlocks
{


    //General Blocks
    public static Block clear_glass;
    public static Block share_xp;
    public static Block reinforced_obsidian;
    public static Block reinforced_obsidianglass;
    public static Block noise_muffler;
    public static Block igniter;
    public static Block sponge;
    public static Block breaker;
    public static Block placer;
    public static Block water_tank;
    public static Block compressed_bookshelf;
    public static Block magnetized_chest;
    public static Block void_block;

    //[Overworld Ores]
    public static final Block OVERWORLD_TIN_ORE = new OverworldOreBlock("overworld_tin_ore");
    public static final Block OVERWORLD_LEAD_ORE = new OverworldOreBlock("overworld_lead_ore");
    public static final Block OVERWORLD_COPPER_ORE = new OverworldOreBlock("overworld_copper_ore");
    public static final Block OVERWORLD_SILVER_ORE = new OverworldOreBlock("overworld_silver_ore");
    //[Nether Ores]
    public static final Block NETHER_TIN_ORE = new NetherOreBlock("nether_tin_ore");
    public static final Block NETHER_LEAD_ORE = new NetherOreBlock("nether_lead_ore");
    public static final Block NETHER_COPPER_ORE = new NetherOreBlock("nether_copper_ore");
    public static final Block NETHER_SILVER_ORE = new NetherOreBlock("nether_silver_ore");
    public static final Block NETHER_COAL_ORE = new NetherOreBlock("nether_coal_ore");
    public static final Block NETHER_IRON_ORE = new NetherOreBlock("nether_iron_ore");
    public static final Block NETHER_GOLD_ORE = new NetherOreBlock("nether_gold_ore");
    public static final Block NETHER_REDSTONE_ORE = new NetherOreBlock("nether_redstone_ore");
    public static final Block NETHER_LAPIS_ORE = new NetherOreBlock("nether_lapis_ore");
    public static final Block NETHER_DIAMOND_ORE = new NetherOreBlock("nether_diamond_ore");
    public static final Block NETHER_EMERALD_ORE = new NetherOreBlock("nether_emerald_ore");


    public static void init()
    {
        //General
        share_xp = new BlockShareXP().setUnlocalizedName("share_xp").setRegistryName("share_xp").setCreativeTab(CreativeTabLA.LA_TAB);
        clear_glass = new BlockClearGlass().setUnlocalizedName("clear_glass").setRegistryName("clear_glass").setCreativeTab(CreativeTabLA.LA_TAB);
        reinforced_obsidian = new BlockReinforcedObsidian().setUnlocalizedName("reinforced_obsidian").setRegistryName("reinforced_obsidian").setCreativeTab(CreativeTabLA.LA_TAB);
        reinforced_obsidianglass = new BlockReinforcedGlass().setUnlocalizedName("reinforced_obsidianglass").setRegistryName("reinforced_obsidianglass").setCreativeTab(CreativeTabLA.LA_TAB);
        noise_muffler = new BlockSoundMuffler().setUnlocalizedName("noise_muffler").setRegistryName("noise_muffler").setCreativeTab(CreativeTabLA.LA_TAB);
        igniter = new BlockIgniter().setUnlocalizedName("igniter").setRegistryName("igniter").setCreativeTab(CreativeTabLA.LA_TAB);
        sponge = new BlockSponge().setUnlocalizedName("sponge").setRegistryName("sponge").setCreativeTab(CreativeTabLA.LA_TAB);
        compressed_bookshelf = new BlockCompressedBookshelf(8.0F).setUnlocalizedName("compressed_bookshelf").setRegistryName("compressed_bookshelf").setCreativeTab(CreativeTabLA.LA_TAB);
        water_tank = new BlockWaterTank().setUnlocalizedName("water_tank").setRegistryName("water_tank").setCreativeTab(CreativeTabLA.LA_TAB);
        breaker = new BlockBreaker().setUnlocalizedName("breaker").setRegistryName("breaker").setCreativeTab(CreativeTabLA.LA_TAB);
        placer = new BlockPlacer().setUnlocalizedName("placer").setRegistryName("placer").setCreativeTab(CreativeTabLA.LA_TAB);
        magnetized_chest = new BlockMagnetizedChest().setUnlocalizedName("magnetized_chest").setRegistryName("magnetized_chest").setCreativeTab(CreativeTabLA.LA_TAB);
        void_block = new BlockVoidPortal().setUnlocalizedName("void_block").setRegistryName("void_block").setCreativeTab(CreativeTabLA.LA_TAB);
    }


    public static void register()
    {
        //General
        registerBlock(share_xp);
        registerBlock(clear_glass);
        registerBlock(reinforced_obsidian);
        registerBlock(reinforced_obsidianglass);
        registerBlock(noise_muffler);
        registerBlock(igniter);
        registerBlock(sponge);
        registerBlock(breaker);
        registerBlock(placer);
        registerBlock(compressed_bookshelf);
        registerBlock(water_tank);
        registerBlock(magnetized_chest);
        registerBlock(void_block);

        //Ores
        registerBlock(OVERWORLD_TIN_ORE);
        registerBlock(OVERWORLD_LEAD_ORE);
        registerBlock(OVERWORLD_COPPER_ORE);
        registerBlock(OVERWORLD_SILVER_ORE);
        registerBlock(NETHER_TIN_ORE);
        registerBlock(NETHER_LEAD_ORE);
        registerBlock(NETHER_COPPER_ORE);
        registerBlock(NETHER_SILVER_ORE);
        registerBlock(NETHER_COAL_ORE);
        registerBlock(NETHER_IRON_ORE);
        registerBlock(NETHER_GOLD_ORE);
        registerBlock(NETHER_REDSTONE_ORE);
        registerBlock(NETHER_LAPIS_ORE);
        registerBlock(NETHER_DIAMOND_ORE);
        registerBlock(NETHER_EMERALD_ORE);

    }

    public static void registerRenders()
    {
        //General
        registerRender(share_xp);
        registerRender(clear_glass);
        registerRender(reinforced_obsidian);
        registerRender(reinforced_obsidianglass);
        registerRender(noise_muffler);
        registerRender(igniter);
        registerRender(sponge);
        registerRender(breaker);
        registerRender(placer);
        registerRender(compressed_bookshelf);
        registerRender(water_tank);
        registerRender(magnetized_chest);
        registerRender(void_block);

        //Ores
        registerRender(OVERWORLD_TIN_ORE);
        registerRender(OVERWORLD_LEAD_ORE);
        registerRender(OVERWORLD_COPPER_ORE);
        registerRender(OVERWORLD_SILVER_ORE);
        registerRender(NETHER_TIN_ORE);
        registerRender(NETHER_LEAD_ORE);
        registerRender(NETHER_COPPER_ORE);
        registerRender(NETHER_SILVER_ORE);
        registerRender(NETHER_COAL_ORE);
        registerRender(NETHER_IRON_ORE);
        registerRender(NETHER_GOLD_ORE);
        registerRender(NETHER_REDSTONE_ORE);
        registerRender(NETHER_LAPIS_ORE);
        registerRender(NETHER_DIAMOND_ORE);
        registerRender(NETHER_EMERALD_ORE);
    }

    public static void registerOreDict()
    {
        OreDictionary.registerOre("oreTin", OVERWORLD_TIN_ORE);
        OreDictionary.registerOre("oreLead", OVERWORLD_LEAD_ORE);
        OreDictionary.registerOre("oreCopper", OVERWORLD_COPPER_ORE);
        OreDictionary.registerOre("oreSilver", OVERWORLD_SILVER_ORE);
        OreDictionary.registerOre("oreTin", NETHER_TIN_ORE);
        OreDictionary.registerOre("oreLead", NETHER_LEAD_ORE);
        OreDictionary.registerOre("oreCopper", NETHER_COPPER_ORE);
        OreDictionary.registerOre("oreSilver", NETHER_SILVER_ORE);
        OreDictionary.registerOre("oreCoal", NETHER_COAL_ORE);
        OreDictionary.registerOre("oreIron", NETHER_IRON_ORE);
        OreDictionary.registerOre("oreGold", NETHER_GOLD_ORE);
        OreDictionary.registerOre("oreRedstone", NETHER_REDSTONE_ORE);
        OreDictionary.registerOre("oreLapis", NETHER_LAPIS_ORE);
        OreDictionary.registerOre("oreDiamond", NETHER_DIAMOND_ORE);
        OreDictionary.registerOre("oreEmerald", NETHER_EMERALD_ORE);
    }

    private static void registerBlock(Block block)
    {
        GameRegistry.register(block);
        ItemBlock item = new ItemBlock(block);
        item.setRegistryName(block.getRegistryName());
        GameRegistry.register(item);
    }

    public static void registerRender(Block block)
    {
        Item item = Item.getItemFromBlock(block);
        Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(item, 0, new ModelResourceLocation(ModInformation.MODID + ":" + block.getUnlocalizedName().substring(5), "inventory"));
    }


}
