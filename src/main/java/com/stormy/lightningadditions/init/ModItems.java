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

import com.stormy.lightningadditions.creativetab.CreativeTabLA;
import com.stormy.lightningadditions.item.base.ItemIngotLA;
import com.stormy.lightningadditions.item.resource.*;
import com.stormy.lightningadditions.item.resource.record.ItemRecordEleventh;
import com.stormy.lightningadditions.reference.ModInformation;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.OreDictionary;
import java.util.EnumMap;
import static com.stormy.lightningadditions.init.ModBlockContainers.ender_hopper;
import static com.stormy.lightningadditions.init.ModBlocks.*;

public class ModItems
{
    //General Items
    public static Item tachyon_shard;
    public static Item tachyon_enhancer;
    public static Item ender_backpack;
    public static Item atomic_inhibitor;
    public static Item atomic_magnet;
    public static Item emerald_apple;
    public static Item sponge_stick;
    public static Item philosopher_stone;
    public static Item sonic_screwdriver;

    //Music
    public static Item record_eleventh;

    //Ingots
    public static final Item SILVER_INGOT = new ItemIngotLA("silver_ingot");
    public static final Item TIN_INGOT = new ItemIngotLA("tin_ingot");
    public static final Item LEAD_INGOT = new ItemIngotLA("lead_ingot");
    public static final Item COPPER_INGOT = new ItemIngotLA("copper_ingot");

    public static void init()
    {
        //General Items
        tachyon_shard = new ItemTachyonShard().setUnlocalizedName("tachyon_shard").setRegistryName("tachyon_shard").setCreativeTab(CreativeTabLA.LA_TAB);
        tachyon_enhancer = new ItemTachyonEnhancer().setUnlocalizedName("tachyon_enhancer").setRegistryName("tachyon_enhancer").setCreativeTab(CreativeTabLA.LA_TAB);
        ender_backpack = new ItemEnderBackpack().setUnlocalizedName("ender_backpack").setRegistryName("ender_backpack").setCreativeTab(CreativeTabLA.LA_TAB);
        atomic_inhibitor = new ItemAtomicInhibitor().setUnlocalizedName("atomic_inhibitor").setRegistryName("atomic_inhibitor").setCreativeTab(CreativeTabLA.LA_TAB);
        atomic_magnet = new ItemAtomicMagnet().setUnlocalizedName("atomic_magnet").setRegistryName("atomic_magnet").setCreativeTab(CreativeTabLA.LA_TAB);
        emerald_apple = new ItemEmeraldApple().setUnlocalizedName("emerald_apple").setRegistryName("emerald_apple").setCreativeTab(CreativeTabLA.LA_TAB);
        sponge_stick = new ItemStickSponge().setUnlocalizedName("sponge_stick").setRegistryName("sponge_stick").setCreativeTab(CreativeTabLA.LA_TAB);
        philosopher_stone = new ItemPhiloStone().setUnlocalizedName("philosopher_stone").setRegistryName("philosopher_stone").setCreativeTab(CreativeTabLA.LA_TAB);
        sonic_screwdriver = new ItemScrewdriver().setUnlocalizedName("sonic_screwdriver").setRegistryName("sonic_screwdriver").setCreativeTab(CreativeTabLA.LA_TAB);

        //Music
        record_eleventh = new ItemRecordEleventh();
    }

    public static void register()
    {
        //General Items
        GameRegistry.register(tachyon_shard);
        GameRegistry.register(tachyon_enhancer);
        GameRegistry.register(ender_backpack);
        GameRegistry.register(atomic_inhibitor);
        GameRegistry.register(atomic_magnet);
        GameRegistry.register(emerald_apple);
        GameRegistry.register(sponge_stick);
        GameRegistry.register(philosopher_stone);
        GameRegistry.register(sonic_screwdriver);

        //Music
        //GameRegistry.register(record_eleventh);

        //Ingots
        GameRegistry.register(TIN_INGOT);
        GameRegistry.register(LEAD_INGOT);
        GameRegistry.register(COPPER_INGOT);
        GameRegistry.register(SILVER_INGOT);

    }

    public static void registerRenders()
    {
        //General Items
        registerRender(tachyon_shard);
        registerRender(tachyon_enhancer);
        registerRender(ender_backpack);
        registerRender(atomic_inhibitor);
        registerRender(atomic_magnet);
        registerRender(emerald_apple);
        registerRender(sponge_stick);
        registerRender(philosopher_stone);
        registerRender(record_eleventh);
        registerRender(sonic_screwdriver);

        //Ingots
        registerRender(TIN_INGOT);
        registerRender(LEAD_INGOT);
        registerRender(COPPER_INGOT);
        registerRender(SILVER_INGOT);

        //ItemBlocks
        registerRender(Item.getItemFromBlock(ender_hopper));
        registerRender(Item.getItemFromBlock(void_block));
        registerRender(Item.getItemFromBlock(cursed_earth));
        registerRender(Item.getItemFromBlock(OVERWORLD_COPPER_ORE));
        registerRender(Item.getItemFromBlock(OVERWORLD_LEAD_ORE));
        registerRender(Item.getItemFromBlock(OVERWORLD_TIN_ORE));
        registerRender(Item.getItemFromBlock(OVERWORLD_SILVER_ORE));
        registerRender(Item.getItemFromBlock(NETHER_COPPER_ORE));
        registerRender(Item.getItemFromBlock(NETHER_LEAD_ORE));
        registerRender(Item.getItemFromBlock(NETHER_TIN_ORE));
        registerRender(Item.getItemFromBlock(NETHER_SILVER_ORE));
        registerRender(Item.getItemFromBlock(NETHER_COAL_ORE));
        registerRender(Item.getItemFromBlock(NETHER_IRON_ORE));
        registerRender(Item.getItemFromBlock(NETHER_GOLD_ORE));
        registerRender(Item.getItemFromBlock(NETHER_LAPIS_ORE));
        registerRender(Item.getItemFromBlock(NETHER_REDSTONE_ORE));
        registerRender(Item.getItemFromBlock(NETHER_DIAMOND_ORE));
        registerRender(Item.getItemFromBlock(NETHER_EMERALD_ORE));
    }

    public static void registerOreDict()
    {
        OreDictionary.registerOre("ingotCopper", COPPER_INGOT);
        OreDictionary.registerOre("ingotLead", LEAD_INGOT);
        OreDictionary.registerOre("ingotTin", TIN_INGOT);
        OreDictionary.registerOre("ingotSilver", SILVER_INGOT);
    }


    public static void registerRender(Item item){
        Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(item, 0, new ModelResourceLocation(ModInformation.MODID + ":" + item.getUnlocalizedName().substring(5), "inventory"));
    }

    public static void registerRender(Item item, int meta, String name){
        Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(item, meta, new ModelResourceLocation(ModInformation.MODID + ":" + name, "inventory"));
    }

    public static EnumMap<EnumDyeColor, ItemBlock> elevators = new EnumMap<EnumDyeColor, ItemBlock>(EnumDyeColor.class);

    //Used in ModBlockContainers (init) -- getItemFromBlock
    public static void registerItemRender(Item i, int meta){
        Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(i, meta, new ModelResourceLocation(i.getUnlocalizedName().substring(5)+ (meta == 0 ? "" : String.valueOf(meta)), "inventory"));
    }

}
