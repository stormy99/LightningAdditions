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

package com.stormy.lightningadditions.init;

import com.stormy.lightningadditions.reference.ModInformation;
import com.stormy.lightningadditions.world.dimMining.biome.BiomeMining;
import com.stormy.lightninglib.lib.item.ModItemBlock;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.LinkedList;
import java.util.List;

public class ModRegistry {

    private static List<Item> itemList = new LinkedList<>();
    private static List<Block> blockList = new LinkedList<>();
    private static List<Biome> biomeList = new LinkedList<>();

    public static void init(){
        registerItems();
        registerBlocks();
        registerBiomes();
    }

    private static void registerItems(){
        registerItem(ModItems.tachyon_shard);
        registerItem(ModItems.charged_tachyon);
        registerItem(ModItems.inert_tachyon);
        registerItem(ModItems.tachyon_enhancer);
        registerItem(ModItems.ender_backpack);
        registerItem(ModItems.atomic_inhibitor);
        registerItem(ModItems.atomic_magnet);
        registerItem(ModItems.emerald_apple);
        registerItem(ModItems.sponge_stick);
        registerItem(ModItems.philosopher_stone);
        registerItem(ModItems.sonic_screwdriver);
        registerItem(ModItems.teleport_wand);
        registerItem(ModItems.stone_stick);
        registerItem(ModItems.lauren_december);
        registerItem(ModItems.lauren_january);
        registerItem(ModItems.record_eleventh);

        registerItem(ModItems.TIN_INGOT);
        registerItem(ModItems.LEAD_INGOT);
        registerItem(ModItems.COPPER_INGOT);
        registerItem(ModItems.SILVER_INGOT);
    }

    private static void registerBlocks(){
        //General
        registerBlock(ModBlocks.share_xp);
        registerBlock(ModBlocks.clear_glass);
        registerBlock(ModBlocks.reinforced_obsidian);
        registerBlock(ModBlocks.reinforced_obsidianglass);
        registerBlock(ModBlocks.noise_muffler);
        registerBlock(ModBlocks.igniter);
        registerBlock(ModBlocks.sponge);
        registerBlock(ModBlocks.breaker);
        registerBlock(ModBlocks.placer);
        registerBlock(ModBlocks.compressed_bookshelf);
        registerBlock(ModBlocks.water_tank);
        registerBlock(ModBlocks.magnetized_chest);
        registerBlock(ModBlocks.void_block);
        registerBlock(ModBlocks.trash_can);
        registerBlock(ModBlocks.cursed_earth);
        registerBlock(ModBlocks.enchanted_earth);
        registerBlock(ModBlocks.bewitched_endstone);
        registerBlock(ModBlocks.cursed_netherrack);
        registerBlock(ModBlocks.mining_portal);
        registerBlock(ModBlocks.ender_hopper);
        registerBlock(ModBlocks.particle_accellerator);

        //Generators
        registerBlock(ModBlocks.solar_generator);
        registerBlock(ModBlocks.fuel_generator);
        registerBlock(ModBlocks.biofuel_generator);

        //Ores
        registerBlock(ModBlocks.OVERWORLD_TIN_ORE);
        registerBlock(ModBlocks.OVERWORLD_LEAD_ORE);
        registerBlock(ModBlocks.OVERWORLD_COPPER_ORE);
        registerBlock(ModBlocks.OVERWORLD_SILVER_ORE);
        registerBlock(ModBlocks.NETHER_TIN_ORE);
        registerBlock(ModBlocks.NETHER_LEAD_ORE);
        registerBlock(ModBlocks.NETHER_COPPER_ORE);
        registerBlock(ModBlocks.NETHER_SILVER_ORE);
        registerBlock(ModBlocks.NETHER_COAL_ORE);
        registerBlock(ModBlocks.NETHER_IRON_ORE);
        registerBlock(ModBlocks.NETHER_GOLD_ORE);
        registerBlock(ModBlocks.NETHER_REDSTONE_ORE);
        registerBlock(ModBlocks.NETHER_LAPIS_ORE);
        registerBlock(ModBlocks.NETHER_DIAMOND_ORE);
        registerBlock(ModBlocks.NETHER_EMERALD_ORE);
    }

    public static void registerBiomes(){
        registerBiome(BiomeMining.biomeMining);
    }

    public static void registerRenderItems(){
        //General Items
        registerItemRender(ModItems.tachyon_shard);
        registerItemRender(ModItems.charged_tachyon);
        registerItemRender(ModItems.inert_tachyon);
        registerItemRender(ModItems.tachyon_enhancer);
        registerItemRender(ModItems.ender_backpack);
        registerItemRender(ModItems.atomic_inhibitor);
        registerItemRender(ModItems.atomic_magnet);
        registerItemRender(ModItems.emerald_apple);
        registerItemRender(ModItems.sponge_stick);
        registerItemRender(ModItems.philosopher_stone);
        registerItemRender(ModItems.record_eleventh);
        registerItemRender(ModItems.sonic_screwdriver);
        registerItemRender(ModItems.teleport_wand);
        registerItemRender(ModItems.stone_stick);
        registerItemRender(ModItems.lauren_december);
        registerItemRender(ModItems.lauren_january);

        //Ingots
        registerItemRender(ModItems.TIN_INGOT);
        registerItemRender(ModItems.LEAD_INGOT);
        registerItemRender(ModItems.COPPER_INGOT);
        registerItemRender(ModItems.SILVER_INGOT);
    }

    public static void registerRenderBlocks(){
        //General
        registerBlockRender(ModBlocks.share_xp);
        registerBlockRender(ModBlocks.clear_glass);
        registerBlockRender(ModBlocks.reinforced_obsidian);
        registerBlockRender(ModBlocks.reinforced_obsidianglass);
        registerBlockRender(ModBlocks.noise_muffler);
        registerBlockRender(ModBlocks.igniter);
        registerBlockRender(ModBlocks.sponge);
        registerBlockRender(ModBlocks.breaker);
        registerBlockRender(ModBlocks.placer);
        registerBlockRender(ModBlocks.compressed_bookshelf);
        registerBlockRender(ModBlocks.water_tank);
        registerBlockRender(ModBlocks.magnetized_chest);
        registerBlockRender(ModBlocks.void_block);
        registerBlockRender(ModBlocks.trash_can);
        registerBlockRender(ModBlocks.cursed_earth);
        registerBlockRender(ModBlocks.enchanted_earth);
        registerBlockRender(ModBlocks.bewitched_endstone);
        registerBlockRender(ModBlocks.cursed_netherrack);
        registerBlockRender(ModBlocks.mining_portal);
        registerBlockRender(ModBlocks.particle_accellerator);
        registerBlockRender(ModBlocks.ender_hopper);

        //Generator
        registerBlockRender(ModBlocks.solar_generator);
        registerBlockRender(ModBlocks.fuel_generator);
        registerBlockRender(ModBlocks.biofuel_generator);

        //Ores
        registerBlockRender(ModBlocks.OVERWORLD_TIN_ORE);
        registerBlockRender(ModBlocks.OVERWORLD_LEAD_ORE);
        registerBlockRender(ModBlocks.OVERWORLD_COPPER_ORE);
        registerBlockRender(ModBlocks.OVERWORLD_SILVER_ORE);
        registerBlockRender(ModBlocks.NETHER_TIN_ORE);
        registerBlockRender(ModBlocks.NETHER_LEAD_ORE);
        registerBlockRender(ModBlocks.NETHER_COPPER_ORE);
        registerBlockRender(ModBlocks.NETHER_SILVER_ORE);
        registerBlockRender(ModBlocks.NETHER_COAL_ORE);
        registerBlockRender(ModBlocks.NETHER_IRON_ORE);
        registerBlockRender(ModBlocks.NETHER_GOLD_ORE);
        registerBlockRender(ModBlocks.NETHER_REDSTONE_ORE);
        registerBlockRender(ModBlocks.NETHER_LAPIS_ORE);
        registerBlockRender(ModBlocks.NETHER_DIAMOND_ORE);
        registerBlockRender(ModBlocks.NETHER_EMERALD_ORE);
    }

    //Registry
    @SubscribeEvent
    public void onItemRegistry(RegistryEvent.Register<Item> event){
        itemList.forEach(event.getRegistry()::register);
    }

    @SubscribeEvent
    public void onBlockRegistry(RegistryEvent.Register<Block> event){
        blockList.forEach(event.getRegistry()::register);
    }

    private static void registerBlock(Block block){
        blockList.add(block);
        registerItem(new ModItemBlock(block));
    }

    private static void registerItem(Item item){
        itemList.add(item);
    }

    private static void registerBlockRender(Block block){
        Item item = Item.getItemFromBlock(block);
        Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(item, 0, new ModelResourceLocation(ModInformation.MODID + ":" + block.getUnlocalizedName().substring(5), "inventory"));
    }

    public static void registerItemRender(Item item){
        Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(item, 0, new ModelResourceLocation(ModInformation.MODID + ":" + item.getUnlocalizedName().substring(5), "inventory"));
    }

    public static void registerItemRender(Item item, int meta, String name){
        Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(item, meta, new ModelResourceLocation(ModInformation.MODID + ":" + name, "inventory"));
    }

    private static void registerBiome(Biome biome){
        biomeList.add(biome);
    }

    @SubscribeEvent
    public void onBiomeRegistry(RegistryEvent.Register<Biome> event){
        biomeList.forEach(event.getRegistry()::register);
    }

}
