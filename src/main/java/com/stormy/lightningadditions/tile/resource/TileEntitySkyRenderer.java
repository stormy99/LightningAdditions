/*
 * ********************************************************************************
 * Copyright (c) 2017 StormyMode, MiningMark48. All Rights Reserved!
 * This file is part of Lightning Additions (MC-Mod).
 *
 * This project cannot be copied and/or distributed without the express
 * permission of StormyMode, MiningMark48 (Developers)!
 * ********************************************************************************
 */

package com.stormy.lightningadditions.tile.resource;

import com.stormy.lightninglib.lib.utils.ReflectionUtils.AClass;
import com.stormy.lightningadditions.utility.WTWRenderer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.EntityRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.glu.Project;

import java.util.LinkedList;
import java.util.Queue;

public class TileEntitySkyRenderer extends TileEntitySpecialRenderer<TileEntitySky>
{
    public TileEntitySkyRenderer()
    { MinecraftForge.EVENT_BUS.register(this); }

    private Queue<Runnable> skyBlocks = new LinkedList<Runnable>();

    @SubscribeEvent
    public void renderLast(RenderWorldLastEvent event)
    {
        if(!skyBlocks.isEmpty()) WTWRenderer.render(() -> {
            while(!skyBlocks.isEmpty())
                skyBlocks.poll().run();
        }, () -> renderSky(event.getPartialTicks()));
    }

    void renderSky(float partialTicks){
        Minecraft mc = Minecraft.getMinecraft();
        EntityRenderer renderer = mc.entityRenderer;
        AClass<EntityRenderer> entityRenderer = new AClass<>(EntityRenderer.class);
        float fov = entityRenderer.<Float> getDeclaredMethod(new String[]{"getFOVModifier", "func_78481_a"}, float.class, boolean.class).setAccessible(true).invoke(renderer, partialTicks, true);

        GlStateManager.pushMatrix();

        new AClass<>(EntityRenderer.class).getDeclaredMethod(new String[]{"setupFog", "func_78468_a"}, int.class, float.class).setAccessible(true).invoke(renderer, -1, partialTicks);

        {
            GlStateManager.pushMatrix();
            GlStateManager.disableDepth();
            GlStateManager.depthFunc(GL11.GL_ALWAYS);
            renderer.setupOverlayRendering();

            GlStateManager.disableTexture2D();
            float red = entityRenderer.<Float> getDeclaredField("fogColorRed", "field_175080_Q").setAccessible(true).get(renderer);
            float green = entityRenderer.<Float> getDeclaredField("fogColorGreen", "field_175082_R").setAccessible(true).get(renderer);
            float blue = entityRenderer.<Float> getDeclaredField("fogColorBlue", "field_175081_S").setAccessible(true).get(renderer);
            Tessellator tess = new Tessellator(20);
            BufferBuilder buffer = tess.getBuffer();
            buffer.begin(GL11.GL_QUADS, DefaultVertexFormats.POSITION_COLOR);
            buffer.pos(0, 0, 1).color(red, green, blue, 0.5f).endVertex();
            buffer.pos(0, mc.displayHeight, 1).color(red, green, blue, 0.5f).endVertex();
            buffer.pos(mc.displayWidth, mc.displayHeight, 1).color(red, green, blue, 0.5f).endVertex();
            buffer.pos(mc.displayWidth, 0, 1).color(red, green, blue, 0.5f).endVertex();
            tess.draw();
            GlStateManager.enableTexture2D();

            GlStateManager.depthFunc(GL11.GL_LESS);
            GlStateManager.popMatrix();
        }

        GlStateManager.matrixMode(GL11.GL_PROJECTION);
        GlStateManager.loadIdentity();
        Project.gluPerspective(fov, (float) mc.displayWidth / (float) mc.displayHeight, 0.05F, mc.gameSettings.renderDistanceChunks * 16 * 4.0F);
        GlStateManager.matrixMode(GL11.GL_MODELVIEW);

        EntityPlayer player = mc.player;
        double prevPosY = player.prevPosY;
        double posY = player.posY;
        player.prevPosY = 256;
        player.posY = 256;

        GlStateManager.color(0, 0, 0);
        mc.renderGlobal.renderSky(partialTicks, 2);

        player.prevPosY = prevPosY;
        player.posY = posY;

        GlStateManager.disableFog();
        GlStateManager.depthFunc(GL11.GL_LEQUAL);

        GlStateManager.matrixMode(GL11.GL_PROJECTION);
        GlStateManager.loadIdentity();
        Project.gluPerspective(fov, (float) mc.displayWidth / (float) mc.displayHeight, 0.05F, mc.gameSettings.renderDistanceChunks * 16 * MathHelper.SQRT_2);
        GlStateManager.matrixMode(GL11.GL_MODELVIEW);

        GlStateManager.popMatrix();
    }
}
