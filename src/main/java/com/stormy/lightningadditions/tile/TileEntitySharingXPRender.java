/*
 * ********************************************************************************
 * Copyright (c) 2017 StormyMode, MiningMark48. All Rights Reserved!
 * This file is part of Lightning Additions (MC-Mod).
 *
 * This project cannot be copied and/or distributed without the express
 * permission of StormyMode, MiningMark48 (Developers)!
 * ********************************************************************************
 */

package com.stormy.lightningadditions.tile;

import com.stormy.lightningadditions.init.ModItems;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.block.model.IBakedModel;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms.TransformType;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraftforge.client.ForgeHooksClient;
import org.lwjgl.opengl.GL11;

public class TileEntitySharingXPRender extends TileEntitySpecialRenderer<TileEntitySharingXP>
{
    private ItemStack tachyon = new ItemStack(ModItems.tachyon_shard, 1);

    public void render(TileEntitySharingXP te, double x, double y, double z, float partialTicks, int destroyStage, float alpha) {
        ITextComponent levelsText = new TextComponentString("" + te.getStoredLevels());

        if (te != null && te.getPos() != null && rendererDispatcher.cameraHitResult != null && rendererDispatcher.cameraHitResult.getBlockPos() != null && rendererDispatcher.cameraHitResult.getBlockPos().equals(te.getPos())) {
            setLightmapDisabled(true);
            drawNameplate(te, levelsText.getFormattedText(), x, y, z, 12);
            setLightmapDisabled(false);
        }

        double offset = Math.sin((te.getWorld().getTotalWorldTime() + partialTicks) / 8.0D) / 10.0D;
        IBakedModel model = Minecraft.getMinecraft().getRenderItem().getItemModelWithOverrides(tachyon, te.getWorld(), null);

        GlStateManager.enableRescaleNormal();
        GlStateManager.alphaFunc(GL11.GL_GREATER, 0.1F);
        GlStateManager.enableBlend();
        RenderHelper.enableStandardItemLighting();
        GlStateManager.tryBlendFuncSeparate(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA, 1, 0);
        GlStateManager.pushMatrix();
        GlStateManager.translate(x + 0.5D, y + 2D + offset, z + 0.5D);
        GlStateManager.rotate((te.getWorld().getTotalWorldTime() + partialTicks) * 4.0F, 0.0F, 1.0F, 0.0F);
        model = ForgeHooksClient.handleCameraTransforms(model, TransformType.GROUND, false);
        Minecraft.getMinecraft().getTextureManager().bindTexture(TextureMap.LOCATION_BLOCKS_TEXTURE);
        Minecraft.getMinecraft().getRenderItem().renderItem(tachyon, model);
        GlStateManager.popMatrix();
        GlStateManager.disableRescaleNormal();
        GlStateManager.disableBlend();
    }
}
