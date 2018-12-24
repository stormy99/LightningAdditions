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

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;

public class GuiTranButton extends GuiButton {

    public GuiTranButton(int id, int x, int y, int width, int height, String text) {
        super(id, x, y, width, height, text);
    }

    @Override
    public void drawButtonForegroundLayer(int mouseX, int mouseY) {
        if (visible) {
            hovered = mouseX >= x && mouseY >= y && mouseX < x + width && mouseY < y + height;
            final float state = getHoverState(hovered);
            final float f = state / 2 * 0.9F + 0.1F;
            final int color = (int) (255.0F * f);

            RenderUtilsCalc.drawRect(x, y, x + width, y + height, color / 2 << 24);
            drawCenteredString(Minecraft.getMinecraft().fontRenderer, displayString, x + width / 2, y + (height - 8) / 2, 0xffffff);
        }
    }

    @Override
    protected int getHoverState(boolean mouseOver) {
        int state = 2;
        if (!enabled) {
            state = 5;
        } else if (mouseOver) {
            state = 4;
        }

        return state;
    }

}