package com.stormy.lightningadditions.utility;

import net.minecraft.client.gui.FontRenderer;

public class GuiUtils {

    public static int getXCenter(String text, FontRenderer renderer, int width){
        int x = (width / 2) - (renderer.getStringWidth(text) / 2);
        return x;
    }

}
