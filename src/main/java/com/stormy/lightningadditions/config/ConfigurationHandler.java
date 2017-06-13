package com.stormy.lightningadditions.config;

import com.stormy.lightningadditions.reference.ModInformation;
import com.stormy.lightningadditions.reference.Translate;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.io.File;

public class ConfigurationHandler
{
    public static Configuration configuration;

    public static int tickAmount;
    public static int atomicMagnetRange;

    public static boolean creativeTabSearchBar;
    public static boolean atomicMagnetParticles;

    public static float atomicMagnetPullSpeed;



    public static void init(File configFile)
    {
        if (configuration == null)
        {
            configuration = new Configuration(configFile);
            loadConfiguration();
        }
    }

    @SubscribeEvent
    public void onConfigurationChangedEvent(ConfigChangedEvent.OnConfigChangedEvent event){

        if (event.getModID().equalsIgnoreCase(ModInformation.MODID))
        {
            loadConfiguration();
        }
    }


    private static void loadConfiguration()
    {
        creativeTabSearchBar = configuration.getBoolean(Translate.toLocal("config.creativeTabSearchBar.title"), Configuration.CATEGORY_GENERAL, false, Translate.toLocal("config.creativeTabSearchBar.desc"));
        tickAmount = configuration.getInt("Tick Amount", Configuration.CATEGORY_GENERAL, 25, 1, Integer.MAX_VALUE, "Amount of times the block is ticked.");

        configuration.addCustomCategoryComment(Translate.toLocal("config.category.atomicMagnet.title"), Translate.toLocal("config.category.atomicMagnet.desc"));

        //Atomic Magnet
        atomicMagnetRange = configuration.getInt(Translate.toLocal("config.pearcelMagnetRange.title"), Translate.toLocal("config.category.pearcelMagnet.title"), 9, 1, Integer.MAX_VALUE, Translate.toLocal("config.pearcelMagnetRange.desc"));
        atomicMagnetPullSpeed = configuration.getFloat(Translate.toLocal("config.pearcelMagnetPullSpeed.title"), Translate.toLocal("config.category.pearcelMagnet.title"), 0.035F, 0, Float.MAX_VALUE, Translate.toLocal("config.pearcelMagnetPullSpeed.desc"));
        atomicMagnetParticles = configuration.getBoolean(Translate.toLocal("config.pearcelMagnetParticles.title"), Translate.toLocal("config.category.pearcelMagnet.title"), true, Translate.toLocal("config.pearcelMagnetParticles.desc"));



    }

}
