package com.stormy.lightningadditions;

//import lombok.SneakyThrows;
import com.stormy.lightningadditions.client.gui.GuiHandler;
import com.stormy.lightningadditions.init.*;
import com.stormy.lightningadditions.client.lightchunkutil.ConfigHandler;
import com.stormy.lightningadditions.config.ConfigurationHandler;
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
import net.minecraftforge.fml.relauncher.Side;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static com.stormy.lightningadditions.reference.ModInformation.MODID;
import static com.stormy.lightningadditions.reference.ModInformation.MODNAME;


@Mod(   modid = MODID,
        name = MODNAME,
        version = ModInformation.VERSION,
        acceptedMinecraftVersions = ModInformation.acceptedMinecraftVersions)

public class LightningAdditions {


    @Instance("lightningadditions")
    public static LightningAdditions INSTANCE = new LightningAdditions();
    @SidedProxy(clientSide = ModInformation.ClientProxy, serverSide = ModInformation.CommonProxy)
    public static CommonProxy proxy;
    public static SimpleNetworkWrapper network;
    public static final Logger logger = LogManager.getLogger(MODID);
    public static int renderIdFull;

    public LightningAdditions() {
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

        network = NetworkRegistry.INSTANCE.newSimpleChannel(ModInformation.MODID);
        network.registerMessage(new SPacketUpdate.Handler(), SPacketUpdate.class, 0, Side.CLIENT);
        network.registerMessage(new CPacketRequest.Handler(), CPacketRequest.class, 1, Side.SERVER);

        ModTileEntities.init();
        ModSounds.registerSounds();
        ModItems.init();
        ModItems.register();
        ModBlocks.init();
        ModBlocks.register();

        NetworkRegistry.INSTANCE.registerGuiHandler(this, new GuiHandler());

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
