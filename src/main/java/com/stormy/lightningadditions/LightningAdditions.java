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
import com.stormy.lightningadditions.config.ConfigurationHandler;
import com.stormy.lightningadditions.feature.harvest.Harvest;
import com.stormy.lightningadditions.feature.lightchunkutil.ConfigHandler;
import com.stormy.lightningadditions.handler.generator.BioFuelRegistry;
import com.stormy.lightningadditions.handler.ritual.EventHandlerRitualCommon;
import com.stormy.lightningadditions.init.*;
import com.stormy.lightningadditions.network.GuiHandler;
import com.stormy.lightningadditions.proxy.CommonProxy;
import com.stormy.lightningadditions.reference.ModInformation;
import com.stormy.lightningadditions.utility.logger.ConfigurationManagerLA;
import com.stormy.lightningadditions.utility.logger.LALogger;
import com.stormy.lightningadditions.utility.xpshare.CPacketRequest;
import com.stormy.lightningadditions.utility.xpshare.SPacketUpdate;
import com.stormy.lightningadditions.world.WorldGen;
import com.stormy.lightningadditions.world.dimMining.WorldProviderMining;
import com.stormy.lightningadditions.world.dimvoid.VoidCreator;
import com.stormy.lightningadditions.world.jsonhelper.JsonLoader;
import net.minecraft.world.DimensionType;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
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


    @Instance(ModInformation.MODID)
    public static LightningAdditions INSTANCE;
    @SidedProxy(clientSide = ModInformation.ClientProxy, serverSide = ModInformation.CommonProxy)
    public static CommonProxy proxy;
    public static SimpleNetworkWrapper network;
    public static final Logger logger = LogManager.getLogger(MODID);
    public static DimensionType DimType;
    public static DimensionType MiningDimType;

    public LightningAdditions() {}


    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        LALogger.log("LA Pre-Initialisation!");
        ModInformation.LOCATION = new File(event.getModConfigurationDirectory().getAbsolutePath() + "/" + ModInformation.MODID);
        JsonLoader.loadData();
        ConfigurationManagerLA manager = new ConfigurationManagerLA(event);
        ConfigHandler.init(event);
//        if (proxy != null)

        //General
        MinecraftForge.EVENT_BUS.register(this);
        MinecraftForge.EVENT_BUS.register(new EventHandlerRitualCommon());
        ConfigurationHandler.init(event.getSuggestedConfigurationFile());
        MinecraftForge.EVENT_BUS.register(new ConfigurationHandler());
        DimType = DimensionType.register("lightningadditions", "void", ConfigurationManagerLA.dimID, VoidCreator.class, true);
        MiningDimType = DimensionType.register("lightningadditions", "mining", ConfigurationManagerLA.dimMiningID, WorldProviderMining.class, true);

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

        BioFuelRegistry.init();

    }


    @EventHandler
    public void postInit(FMLPostInitializationEvent event)
    {
        LALogger.log("LA Post-Initialisation!");
        proxy.postInit(event);

        MinecraftForge.EVENT_BUS.register(new TooltipEventTemp());

    }


}
