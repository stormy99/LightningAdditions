package com.stormy.lightningadditions.utility.calc;

import org.lwjgl.input.Keyboard;
import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.ClientTickEvent;

public class CalcKey {

    private static KeyBinding openCalculator = new KeyBinding("key.openCalculator", Keyboard.KEY_C, "key.category.calcs");

    private static final Minecraft mc = Minecraft.getMinecraft();

    public CalcKey() {
        ClientRegistry.registerKeyBinding(openCalculator);
    }

    @SubscribeEvent
    public void onClientTick(ClientTickEvent event) {
        if (openCalculator.isPressed()) {
            mc.displayGuiScreen(new GuiCalc(mc.currentScreen));
        }
    }

}
