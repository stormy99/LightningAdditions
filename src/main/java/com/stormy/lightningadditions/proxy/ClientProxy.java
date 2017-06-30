/*
 * ********************************************************************************
 * Copyright (c) 2017 StormyMode, MiningMark48. All Rights Reserved!
 * This file is part of Lightning Additions (MC-Mod).
 *
 * This project cannot be copied and/or distributed without the express
 * permission of StormyMode, MiningMark48 (Developers)!
 * ********************************************************************************
 */

package com.stormy.lightningadditions.proxy;

import com.stormy.lightningadditions.block.model.BlockModel;
import com.stormy.lightningadditions.feature.lightchunkutil.ChunkBoundariesHandler;
import com.stormy.lightningadditions.feature.lightchunkutil.LightChunkKeyBinds;
import com.stormy.lightningadditions.feature.lightchunkutil.LightOverlayHandler;
import com.stormy.lightningadditions.init.ModBlocks;
import com.stormy.lightningadditions.init.ModItems;
import com.stormy.lightningadditions.model.ModelTachyonEnhancer;
import com.stormy.lightningadditions.reference.ModInformation;
import com.stormy.lightningadditions.tile.TileEntitySky;
import com.stormy.lightningadditions.tile.TileEntitySkyRenderer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.client.FMLClientHandler;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.event.*;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ClientProxy extends CommonProxy {

    @Override
    public void registerModel(Item item) {
        ModelLoader.setCustomModelResourceLocation(item, 0, new ModelResourceLocation(ModInformation.MODID + ":" + item.getUnlocalizedName().substring(5), "inventory"));
    }

    public void preInit(FMLPreInitializationEvent event)
    {
        LightChunkKeyBinds.init();
        LightOverlayHandler.init();
        ChunkBoundariesHandler.init();
    }

    @Override
    public void registerModels()
    {
        BlockModel.register();
    }

    public void init(FMLInitializationEvent event) {
    }


    public void postInit(FMLPostInitializationEvent event) {
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerRenders() {
        ModItems.registerRenders();
        ModBlocks.registerRenders();

        ClientRegistry.bindTileEntitySpecialRenderer(TileEntitySky.class, new TileEntitySkyRenderer());

    }

    @Override
    public World getClientWorld() {
        return FMLClientHandler.instance().getClient().world;
    }
    @Override
    public EntityPlayer getClientPlayer() {
        return Minecraft.getMinecraft().player;
    }

}
