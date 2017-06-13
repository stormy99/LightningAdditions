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
