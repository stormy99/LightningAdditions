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

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.block.model.IBakedModel;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms.TransformType;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.ForgeHooksClient;
import org.lwjgl.opengl.GL11;

public class TileEntitySharingXPRender extends TileEntitySpecialRenderer<TileEntitySharingXP>
{
    private ItemStack emerald = new ItemStack(Items.EMERALD, 1);

    @Override
    public void renderTileEntityAt(TileEntitySharingXP te, double x, double y, double z, float partialTicks, int destroyStage)
    {
        double offset = Math.sin((te.getWorld().getTotalWorldTime() + partialTicks) / 8.0D) / 10.0D;
        IBakedModel model = Minecraft.getMinecraft().getRenderItem().getItemModelWithOverrides(emerald, te.getWorld(), null);

        GlStateManager.enableRescaleNormal();
        GlStateManager.alphaFunc(GL11.GL_GREATER, 0.1F);
        GlStateManager.enableBlend();
        RenderHelper.enableStandardItemLighting();
        GlStateManager.tryBlendFuncSeparate(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA, 1, 0);
        GlStateManager.pushMatrix();
        GlStateManager.translate(x + 0.5D, y + 0.4D + offset, z + 0.5D);
        GlStateManager.rotate((te.getWorld().getTotalWorldTime() + partialTicks) * 4.0F, 0.0F, 1.0F, 0.0F);
        model = ForgeHooksClient.handleCameraTransforms(model, TransformType.GROUND, false);
        Minecraft.getMinecraft().getTextureManager().bindTexture(TextureMap.LOCATION_BLOCKS_TEXTURE);
        Minecraft.getMinecraft().getRenderItem().renderItem(emerald, model);
        GlStateManager.popMatrix();
        GlStateManager.disableRescaleNormal();
        GlStateManager.disableBlend();

    }
}
