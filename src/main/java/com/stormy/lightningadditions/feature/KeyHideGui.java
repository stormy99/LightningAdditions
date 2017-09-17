/*
 *
 *  * ********************************************************************************
 *  * Copyright (c) 2017 StormyMode, MiningMark48. All Rights Reserved!
 *  * This file is part of Lightning Additions (MC-Mod).
 *  *
 *  * This project cannot be copied and/or distributed without the express
 *  * permission of StormyMode, MiningMark48 (Developers)!
 *  * ********************************************************************************
 *
 */

package com.stormy.lightningadditions.feature;

import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.input.Keyboard;

@SideOnly(Side.CLIENT)
public class KeyHideGui {

    private static KeyBinding HIDE_GUI_KEY = new KeyBinding("key.hideGui", Keyboard.KEY_NONE, "key.categories.misc");

    private static final Minecraft mc = Minecraft.getMinecraft();

    public static void init() {
        ClientRegistry.registerKeyBinding(HIDE_GUI_KEY);
        MinecraftForge.EVENT_BUS.register(new KeyHideGui());
    }

    @SubscribeEvent
    public void onClientTick(TickEvent.ClientTickEvent event) {
        if (HIDE_GUI_KEY.isPressed()) {
            mc.gameSettings.hideGUI = !mc.gameSettings.hideGUI;
        }
    }

}
