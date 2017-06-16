package com.stormy.lightningadditions.proxy;

import com.stormy.lightningadditions.block.model.BlockModel;
import com.stormy.lightningadditions.client.lightchunkutil.ChunkBoundariesHandler;
import com.stormy.lightningadditions.client.lightchunkutil.LightChunkKeyBinds;
import com.stormy.lightningadditions.client.lightchunkutil.LightOverlayHandler;
import com.stormy.lightningadditions.init.ModBlocks;
import com.stormy.lightningadditions.init.ModItems;
import com.stormy.lightningadditions.reference.ModInformation;
import keri.ninetaillib.lib.render.RenderingRegistry;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.client.FMLClientHandler;
import net.minecraftforge.fml.common.event.*;

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
    public void registerRenders() {
        ModItems.registerRenders();
        ModBlocks.registerRenders();

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
