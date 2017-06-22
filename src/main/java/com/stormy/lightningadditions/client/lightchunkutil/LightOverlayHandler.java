/*
 * ********************************************************************************
 * Copyright (c) 2017 StormyMode, MiningMark48. All Rights Reserved!
 * This file is part of Lightning Additions (MC-Mod).
 *
 * This project cannot be copied and/or distributed without the express
 * permission of StormyMode, MiningMark48 (Developers)!
 * ********************************************************************************
 */

package com.stormy.lightningadditions.client.lightchunkutil;

import net.minecraft.client.Minecraft;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

public class LightOverlayHandler {

    public static boolean enabled = false;

    public static void init() { MinecraftForge.EVENT_BUS.register(new LightOverlayHandler());
    }

    @SubscribeEvent
    public void renderWorldLastEvent(RenderWorldLastEvent event)
    { if (enabled)
            LightOverlayRender.renderOverlays(); }
    @SubscribeEvent
    public void onClientTick(TickEvent.ClientTickEvent event) {
        if(Minecraft.getMinecraft().world!=null && enabled && event.phase == TickEvent.Phase.END &&
                (Minecraft.getMinecraft().currentScreen==null || !Minecraft.getMinecraft().currentScreen.doesGuiPauseGame())){
            LightOverlayRender.refreshCache();
        }

    }
    public static void toggleMode()
    {
        enabled = !enabled;
        if (!enabled)
            LightOverlayRender.clearCache();
    }}
