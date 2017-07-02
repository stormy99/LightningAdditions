/*
 * ********************************************************************************
 * Copyright (c) 2017 StormyMode, MiningMark48. All Rights Reserved!
 * This file is part of Lightning Additions (MC-Mod).
 *
 * This project cannot be copied and/or distributed without the express
 * permission of StormyMode, MiningMark48 (Developers)!
 * ********************************************************************************
 */

package com.stormy.lightningadditions.feature.loadSound;

import com.stormy.lightningadditions.init.ModSounds;
import com.stormy.lightningadditions.utility.logger.ConfigurationManagerLA;
import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.client.gui.GuiMainMenu;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.client.event.GuiOpenEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.FMLLog;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.common.network.FMLNetworkEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.apache.logging.log4j.Level;

public class loadFinish
{
    public static boolean played = false;
    public static boolean playWorld = false;

    public static String name = "lightningadditions:sounds.load_finished";
    public static double pitch = 1.0D;
    public static String nameWorld = "lightningadditions:sounds.load_finished";
    public static double pitchWorld = 1.0D;
    public static int playOn = 1;

    @Mod.EventHandler
    public static void preInit()
    {
        name = ConfigurationManagerLA.LOAD_NAME;
        pitch = ConfigurationManagerLA.LOAD_PITCH;
        nameWorld = ConfigurationManagerLA.LOAD_NAMEWORLD;
        pitchWorld = ConfigurationManagerLA.LOAD_PITCHWORLD;
        playOn = ConfigurationManagerLA.LOAD_PLAYON;
    }

    @SubscribeEvent
    @SideOnly(Side.CLIENT)
    public void onGuiOpen(GuiOpenEvent event)
    {
        if (((event.getGui() instanceof GuiMainMenu)) && (!played)) {
            played = true;
            if ((playOn == 1) || (playOn == 3)) {
                SoundEvent sound = (SoundEvent) SoundEvent.REGISTRY.getObject(new ResourceLocation(name));
                if (sound != null) {
                    Minecraft.getMinecraft().getSoundHandler().playSound(PositionedSoundRecord.getMasterRecord(sound, (float) pitch));
                } else {
                    FMLLog.log("LightningAdditions", Level.WARN, "Could not find loading sounds: %s", new Object[]{new ResourceLocation(name)});
                }
            }
        }
    }

    @SubscribeEvent
    @SideOnly(Side.CLIENT)
    public void onConnectToServer(FMLNetworkEvent.ClientConnectedToServerEvent event)
    {
        playWorld = true;
    }

    @SubscribeEvent
    @SideOnly(Side.CLIENT)
    public void onWorldTick(TickEvent.WorldTickEvent event)
    {
        if ((playWorld) && (event.phase == TickEvent.Phase.END) && (Minecraft.getMinecraft().player != null) && ((Minecraft.getMinecraft().player.ticksExisted > 20) || (Minecraft.getMinecraft().isGamePaused())))
        {
            playWorld = false;
            if ((playOn == 2) || (playOn == 3))
            {
                SoundEvent sound = (SoundEvent)SoundEvent.REGISTRY.getObject(new ResourceLocation(nameWorld));
                if (sound != null) {
                    Minecraft.getMinecraft().getSoundHandler().playSound(PositionedSoundRecord.getMasterRecord(sound, (float)pitchWorld));
                } else {
                    FMLLog.log("LightningAdditions", Level.WARN, "Could not find loading sounds: %s", new Object[] { new ResourceLocation(nameWorld) });
                }
            }
        }
    }



}
