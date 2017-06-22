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

import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class ChunkBoundariesHandler {

    public static byte mode = 0;

    public static void init() {
        MinecraftForge.EVENT_BUS.register(new ChunkBoundariesHandler());
    }

    @SubscribeEvent
    public void renderWorldLastEvent(RenderWorldLastEvent event) {
        if (mode!=0)
            ChunkBoundariesRender.renderOverlays();
    }

    public static void toggleMode(){
        mode++;
        if(mode>2)
            mode=0;
    }
}
