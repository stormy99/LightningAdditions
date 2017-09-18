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
import com.stormy.lightningadditions.item.base.ItemLA;
import com.stormy.lightningadditions.item.resource.*;
import com.stormy.lightningadditions.item.resource.record.ItemRecordEleventh;
import net.minecraft.item.Item;

public class ModItems
{
    //General Items
    public static Item tachyon_shard;
    public static Item charged_tachyon;
    public static Item inert_tachyon;
    public static Item tachyon_enhancer;
    public static Item ender_backpack;
    public static Item atomic_inhibitor;
    public static Item atomic_magnet;
    public static Item emerald_apple;
    public static Item sponge_stick;
    public static Item philosopher_stone;
    public static Item sonic_screwdriver;
    public static Item teleport_wand;
    public static Item stone_stick;
    public static Item lauren_december;
    public static Item lauren_january;

    //Music
    public static Item record_eleventh;

    //Ingots
    public static final Item SILVER_INGOT = new ItemIngotLA("silver_ingot");
    public static final Item TIN_INGOT = new ItemIngotLA("tin_ingot");
    public static final Item LEAD_INGOT = new ItemIngotLA("lead_ingot");
    public static final Item COPPER_INGOT = new ItemIngotLA("copper_ingot");

    public static void init()
    {
        //General Items (ItemLA)
        stone_stick = new ItemLA().setUnlocalizedName("stone_stick").setRegistryName("stone_stick").setCreativeTab(CreativeTabLA.LA_TAB);

        //Other Items
        tachyon_shard = new ItemTachyonShard().setUnlocalizedName("tachyon_shard").setRegistryName("tachyon_shard").setCreativeTab(CreativeTabLA.LA_TAB);
        charged_tachyon = new ItemLA().setUnlocalizedName("charged_tachyon").setRegistryName("charged_tachyon").setCreativeTab(CreativeTabLA.LA_TAB);
        inert_tachyon = new ItemLA().setUnlocalizedName("inert_tachyon").setRegistryName("inert_tachyon").setCreativeTab(CreativeTabLA.LA_TAB);
        tachyon_enhancer = new ItemTachyonEnhancer().setUnlocalizedName("tachyon_enhancer").setRegistryName("tachyon_enhancer").setCreativeTab(CreativeTabLA.LA_TAB);
        ender_backpack = new ItemEnderBackpack().setUnlocalizedName("ender_backpack").setRegistryName("ender_backpack").setCreativeTab(CreativeTabLA.LA_TAB);
        atomic_inhibitor = new ItemAtomicInhibitor().setUnlocalizedName("atomic_inhibitor").setRegistryName("atomic_inhibitor").setCreativeTab(CreativeTabLA.LA_TAB);
        atomic_magnet = new ItemAtomicMagnet().setUnlocalizedName("atomic_magnet").setRegistryName("atomic_magnet").setCreativeTab(CreativeTabLA.LA_TAB);
        emerald_apple = new ItemEmeraldApple().setUnlocalizedName("emerald_apple").setRegistryName("emerald_apple").setCreativeTab(CreativeTabLA.LA_TAB);
        sponge_stick = new ItemStickSponge().setUnlocalizedName("sponge_stick").setRegistryName("sponge_stick").setCreativeTab(CreativeTabLA.LA_TAB);
        philosopher_stone = new ItemPhiloStone().setUnlocalizedName("philosopher_stone").setRegistryName("philosopher_stone").setCreativeTab(CreativeTabLA.LA_TAB);
        sonic_screwdriver = new ItemScrewdriver().setUnlocalizedName("sonic_screwdriver").setRegistryName("sonic_screwdriver").setCreativeTab(CreativeTabLA.LA_TAB);
        teleport_wand = new ItemTeleportWand().setUnlocalizedName("teleport_wand").setRegistryName("teleport_wand").setCreativeTab(CreativeTabLA.LA_TAB);
        lauren_december = new ItemDecemberVictus(1, Integer.MAX_VALUE, true).setUnlocalizedName("lauren_december").setRegistryName("lauren_december").setCreativeTab(CreativeTabLA.LA_TAB);
        lauren_january = new ItemJanuaryBlues(1, Integer.MAX_VALUE, true).setUnlocalizedName("lauren_january").setRegistryName("lauren_january").setCreativeTab(CreativeTabLA.LA_TAB);

        //Music
        record_eleventh = new ItemRecordEleventh().setUnlocalizedName("record_eleventh").setRegistryName("record_eleventh").setCreativeTab(CreativeTabLA.LA_TAB);
    }

}
