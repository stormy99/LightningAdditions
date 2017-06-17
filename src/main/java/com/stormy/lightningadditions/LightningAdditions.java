package com.stormy.lightningadditions;

import lombok.SneakyThrows;
import com.google.common.collect.ImmutableSet;
import com.stormy.lightningadditions.utility.tubing.GuiHandlerTube;
import com.stormy.lightningadditions.block.BlockTube;
import com.stormy.lightningadditions.block.BlockWTube;
import com.stormy.lightningadditions.client.lightchunkutil.ConfigHandler;
import com.stormy.lightningadditions.config.ConfigurationHandler;
import com.stormy.lightningadditions.init.ModBlocks;
import com.stormy.lightningadditions.init.ModItems;
import com.stormy.lightningadditions.init.ModSounds;
import com.stormy.lightningadditions.init.ModTileEntities;
import com.stormy.lightningadditions.proxy.CommonProxy;
import com.stormy.lightningadditions.reference.ModInformation;
import com.stormy.lightningadditions.utility.calc.CalcKey;
import com.stormy.lightningadditions.utility.xpshare.CPacketRequest;
import com.stormy.lightningadditions.utility.xpshare.SPacketUpdate;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.*;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.event.*;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.common.versioning.DefaultArtifactVersion;
import net.minecraftforge.fml.common.versioning.InvalidVersionSpecificationException;
import net.minecraftforge.fml.common.versioning.VersionRange;
import net.minecraftforge.fml.relauncher.Side;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;

import static com.stormy.lightningadditions.reference.ModInformation.MODID;
import static com.stormy.lightningadditions.reference.ModInformation.MODNAME;


@Mod(   modid = MODID,
        name = MODNAME,
        version = ModInformation.VERSION,
        dependencies = ModInformation.DEPS,
        acceptedMinecraftVersions = ModInformation.acceptedMinecraftVersions)

public class LightningAdditions {


    @Instance("lightningadditions")
    public static LightningAdditions INSTANCE = new LightningAdditions();
    @SidedProxy(clientSide = ModInformation.ClientProxy, serverSide = ModInformation.CommonProxy)
    public static CommonProxy proxy;
    public static SimpleNetworkWrapper network;
    public static final Logger logger = LogManager.getLogger(MODID);
    public static GuiHandlerTube guiHandlerTube = new GuiHandlerTube();
    public static int renderIdFull;

    public LightningAdditions() {
    }

    public static final BlockTube TUBE = new BlockTube("tube_normal", BlockTube.NORMAL);
    public static final BlockTube TUBE_REVERSE = new BlockTube("tube_reverse", BlockTube.REVERSE);
    public static final BlockTube TUBE_WINDOWED = new BlockWTube("tube_windowed");

    @Mod.EventHandler
    @SneakyThrows
    public void onConstruct(FMLConstructionEvent event) {
        if (FMLCommonHandler.instance().getSide().isClient()) {
            VersionRange range = null;
            try {
                range = VersionRange.createFromVersionSpec("[MC1.10.2-0.1.0.10,)");
            } catch (InvalidVersionSpecificationException e) {
                e.printStackTrace();
            }
            if (!Loader.isModLoaded("ctm") || !range.containsVersion(Loader.instance().getIndexedModList().get("ctm").getProcessedVersion())) {
                throw new MissingModsException(ImmutableSet.of(new DefaultArtifactVersion("ctm", range)), MODID, MODNAME);
            }
        }
    }


    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        ConfigHandler.init(event);
        if (proxy != null)
            proxy.preInit(event);

        MinecraftForge.EVENT_BUS.register(this);
        ConfigurationHandler.init(event.getSuggestedConfigurationFile());
        MinecraftForge.EVENT_BUS.register(new ConfigurationHandler());
        MinecraftForge.EVENT_BUS.register(new CalcKey());
        NetworkRegistry.INSTANCE.registerGuiHandler(LightningAdditions.INSTANCE, LightningAdditions.guiHandlerTube);

        network = NetworkRegistry.INSTANCE.newSimpleChannel(ModInformation.MODID);
        network.registerMessage(new SPacketUpdate.Handler(), SPacketUpdate.class, 0, Side.CLIENT);
        network.registerMessage(new CPacketRequest.Handler(), CPacketRequest.class, 1, Side.SERVER);



        ModTileEntities.init();
        ModSounds.registerSounds();
        ModItems.init();
        ModItems.register();
        ModBlocks.init();
        ModBlocks.register();
    }


    @EventHandler
    public void init(FMLInitializationEvent event) {
        proxy.registerRenders();
        proxy.init(event);
    }


    @EventHandler
    public void postInit(FMLPostInitializationEvent event) {
        proxy.postInit(event);
    }
}
