/*
 * ********************************************************************************
 * Copyright (c) 2017 StormyMode, MiningMark48. All Rights Reserved!
 * This file is part of Lightning Additions (MC-Mod).
 *
 * This project cannot be copied and/or distributed without the express
 * permission of StormyMode, MiningMark48 (Developers)!
 * ********************************************************************************
 */

package com.stormy.lightningadditions.feature.calc;

import com.stormy.lightningadditions.reference.ModInformation;
import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.ClientTickEvent;
import org.lwjgl.input.Keyboard;

public class CalcKey {

    private static KeyBinding openCalculator = new KeyBinding("key.openCalculator", Keyboard.KEY_C, "key." + ModInformation.MODID + ".category");

    private static final Minecraft mc = Minecraft.getMinecraft();

    public static void init() {
        ClientRegistry.registerKeyBinding(openCalculator);
        MinecraftForge.EVENT_BUS.register(new CalcKey());
    }

    @SubscribeEvent
    public void onClientTick(ClientTickEvent event) {
        if (openCalculator.isPressed()) {
            mc.displayGuiScreen(new GuiCalc(mc.currentScreen));
        }
    }

}
