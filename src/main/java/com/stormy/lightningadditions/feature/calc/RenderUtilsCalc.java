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

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.VertexBuffer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;

public class RenderUtilsCalc
{
    public static void drawRect(int left, int top, int right, int bottom, int color) {
        if (left < right) {
            int temp = left;
            left = right;
            right = temp;
        }
        if (top < bottom)
        { int temp = top; top = bottom; bottom = temp; }
        final float red = (float) (color >> 16 & 255) / 255.0F;
        final float green = (float) (color >> 8 & 255) / 255.0F;

        final float blue = (float) (color & 255) / 255.0F;
        final float alpha = (float) (color >> 24 & 255) / 255.0F;
        final Tessellator tessellator = Tessellator.getInstance();
        final VertexBuffer buffer = tessellator.getBuffer();



        GlStateManager.enableBlend(); GlStateManager.disableTexture2D();
        GlStateManager.tryBlendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
        GlStateManager.color(red, green, blue, alpha);

        buffer.begin(7, DefaultVertexFormats.POSITION);
        buffer.pos((double) left, (double) bottom, 0.0D).endVertex();
        buffer.pos((double) right, (double) bottom, 0.0D).endVertex();
        buffer.pos((double) right, (double) top, 0.0D).endVertex();
        buffer.pos((double) left, (double) top, 0.0D).endVertex();
        tessellator.draw();


        GlStateManager.enableTexture2D();
        GlStateManager.disableBlend();
    }

}
