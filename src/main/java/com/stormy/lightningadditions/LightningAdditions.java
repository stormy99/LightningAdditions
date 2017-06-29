/*
 * ********************************************************************************
 * Copyright (c) 2017 StormyMode, MiningMark48. All Rights Reserved!
 * This file is part of Lightning Additions (MC-Mod).
 *
 * This project cannot be copied and/or distributed without the express
 * permission of StormyMode, MiningMark48 (Developers)!
 * ********************************************************************************
 */

package com.stormy.lightningadditions;

import com.stormy.lightningadditions.block.ore.ModOreDict;
import com.stormy.lightningadditions.block.ore.TooltipEventTemp;
import com.stormy.lightningadditions.tile.TileEntitySky;
import com.stormy.lightningadditions.tile.TileEntitySkyRenderer;
import com.stormy.lightningadditions.world.WorldGen;
import com.stormy.lightningadditions.world.dimvoid.VoidCreator;
import com.stormy.lightningadditions.world.dimvoid.VoidWorldTeleport;
import com.stormy.lightningadditions.world.jsonhelper.JsonLoader;
import com.stormy.lightningadditions.client.gui.GuiHandler;
import com.stormy.lightningadditions.init.*;
import com.stormy.lightningadditions.feature.lightchunkutil.ConfigHandler;
import com.stormy.lightningadditions.config.ConfigurationHandler;
import com.stormy.lightningadditions.proxy.CommonProxy;
import com.stormy.lightningadditions.reference.ModInformation;
import com.stormy.lightningadditions.feature.calc.CalcKey;
import com.stormy.lightningadditions.feature.harvest.Harvest;
import com.stormy.lightningadditions.utility.logger.ConfigurationManagerLA;
import com.stormy.lightningadditions.utility.logger.LALogger;
import com.stormy.lightningadditions.utility.xpshare.CPacketRequest;
import com.stormy.lightningadditions.utility.xpshare.SPacketUpdate;
import net.minecraft.client.Minecraft;
import net.minecraft.world.DimensionType;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.*;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.event.*;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;

import static com.stormy.lightningadditions.reference.ModInformation.MODID;
import static com.stormy.lightningadditions.reference.ModInformation.MODNAME;

@Mod(   modid = MODID,
        name = MODNAME,
        version = ModInformation.VERSION,
        acceptedMinecraftVersions = ModInformation.acceptedMinecraftVersions )

public class LightningAdditions
{


    @Instance("lightningadditions")
    public static LightningAdditions INSTANCE = new LightningAdditions();
    @SidedProxy(clientSide = ModInformation.ClientProxy, serverSide = ModInformation.CommonProxy)
    public static CommonProxy proxy;
    public static SimpleNetworkWrapper network;
    public static final Logger logger = LogManager.getLogger(MODID);
    public static DimensionType DimType;

    public LightningAdditions() {
    }


    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        LALogger.log("LA Pre-Initialisation!");
        ModInformation.LOCATION = new File(event.getModConfigurationDirectory().getAbsolutePath() + "/" + ModInformation.MODID);
        JsonLoader.loadData();
        ConfigurationManagerLA manager = new ConfigurationManagerLA(event);
        ConfigHandler.init(event);
        if (proxy != null)
            proxy.preInit(event);

        //Ore Generation


        //General
        MinecraftForge.EVENT_BUS.register(this);
        ConfigurationHandler.init(event.getSuggestedConfigurationFile());
        MinecraftForge.EVENT_BUS.register(new ConfigurationHandler());
        MinecraftForge.EVENT_BUS.register(new CalcKey());
        DimType = DimensionType.register("lightningadditions", "void", ConfigurationManagerLA.dimID, VoidCreator.class, false);
        DimensionManager.registerDimension(ConfigurationManagerLA.dimID, DimType);




        //XP Network Sharing
        network = NetworkRegistry.INSTANCE.newSimpleChannel(ModInformation.MODID);
        network.registerMessage(new SPacketUpdate.Handler(), SPacketUpdate.class, 0, Side.CLIENT);
        network.registerMessage(new CPacketRequest.Handler(), CPacketRequest.class, 1, Side.SERVER);
        Harvest.preInit();

        //Mod Content Implementation
        ModBlockContainers.preInit();
        ModTileEntities.init();
        ModSounds.registerSounds();
        ModItems.init();
        ModItems.register();
        ModBlocks.init();
        ModBlocks.register();
        ModOreDict.registerOres();
        NetworkRegistry.INSTANCE.registerGuiHandler(this, new GuiHandler());

    }


    @EventHandler
    public void init(FMLInitializationEvent event) {
        LALogger.log("LA Initialisation!");
        proxy.registerRenders();
        proxy.init(event);

        GameRegistry.registerWorldGenerator(new WorldGen(), 0);

    }


    @EventHandler
    public void postInit(FMLPostInitializationEvent event)
    {
        LALogger.log("LA Post-Initialisation!");
        proxy.postInit(event);

        Minecraft.getMinecraft().getFramebuffer().enableStencil();
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntitySky.class, new TileEntitySkyRenderer());

        MinecraftForge.EVENT_BUS.register(new TooltipEventTemp());

    }
}
