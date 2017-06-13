package com.stormy.lightningadditions.client.lightchunkutil;

import com.stormy.lightningadditions.reference.ModInformation;
import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.client.settings.KeyConflictContext;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.input.Keyboard;

public class LightChunkKeyBinds {

    public static KeyBinding lightOverlay = new KeyBinding("key." + ModInformation.MODID + ".lightoverlay.desc", KeyConflictContext.IN_GAME, Keyboard.KEY_F7, "key." + ModInformation.MODID + ".category");
    public static KeyBinding chunkBounds = new KeyBinding("key." + ModInformation.MODID + ".chunkbounds.desc", KeyConflictContext.IN_GAME, Keyboard.KEY_F9, "key." + ModInformation.MODID + ".category");

    public static void init()
    {
        ClientRegistry.registerKeyBinding(lightOverlay);
        ClientRegistry.registerKeyBinding(chunkBounds);
        MinecraftForge.EVENT_BUS.register(new  LightChunkKeyBinds());
    }

    @SideOnly(Side.CLIENT)
    @SubscribeEvent(receiveCanceled = true)
    public void onKeyEvent(InputEvent.KeyInputEvent event) {
        if (lightOverlay.isPressed()) {
            LightOverlayHandler.toggleMode();
        }

        if (chunkBounds.isPressed()) {
            ChunkBoundariesHandler.toggleMode();
        }

    }

}
