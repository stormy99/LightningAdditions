/*
 * *******************************************************************************
 * Copyright (c) 2017 StormyMode, MiningMark48. All Rights Reserved!
 * This file is part of Lightning Additions (MC-Mod).
 *
 * This project cannot be copied and/or distributed without the express
 * permission of StormyMode, MiningMark48 (Developers)!
 * *******************************************************************************
 */

package com.stormy.lightningadditions.client.gui.resource;

import com.stormy.lightningadditions.container.ContainerParticleAccelerator;
import com.stormy.lightningadditions.tile.resource.TileEntityParticleAccelerator;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.inventory.IInventory;
import net.minecraft.util.ResourceLocation;

/**
 * Created by KitsuneAlex & MiningMark48
 */
public class GuiParticleAccelerator extends GuiContainer {

    private TileEntityParticleAccelerator te;
    private IInventory playerInv;

    private static final ResourceLocation TEXTURE = new ResourceLocation("lightningadditions", "textures/gui/particle_accellerator.png");

    public GuiParticleAccelerator(IInventory inventoryPlayer, TileEntityParticleAccelerator te) {
        super(new ContainerParticleAccelerator(inventoryPlayer, te));
        this.te = te;
        this.playerInv = inventoryPlayer;

        this.xSize = 176;
        this.ySize = 168;
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
        this.drawDefaultBackground();
        GlStateManager.color(1.0f, 1.0f, 1.0f, 1.0f);
        this.mc.getTextureManager().bindTexture(TEXTURE);
        this.drawTexturedModalRect(this.guiLeft, this.guiTop, 0, 0, this.xSize, this.ySize);
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
        this.mc.getTextureManager().bindTexture(TEXTURE);

        if(this.te.getField(2) != this.te.getField(3)){
            int l = this.getCookProgressScaled(24);
            int k = this.getBurnLeftScaled(13);

            this.drawTexturedModalRect(63, 34, 176, 14, l + 1, 16);
            this.drawTexturedModalRect(43, 37 + 12 - k, 176, 12 - k, 8, k + 1);
        }

        String s = this.te.getDisplayName().getUnformattedText();
        this.fontRenderer.drawString(s, 88 - this.fontRenderer.getStringWidth(s) / 2, 6, 4210752);
        this.fontRenderer.drawString(this.playerInv.getDisplayName().getUnformattedText(), 8, 75, 4210752);

        super.drawGuiContainerForegroundLayer(mouseX, mouseY);
    }

    private int getCookProgressScaled(int pixels)
    {
        int progress = this.te.getField(2) + 1;
        int maxProgress = this.te.getField(3);

        return maxProgress != 0 && progress != 0 ? progress * pixels / maxProgress : 0;
    }

    private int getBurnLeftScaled(int pixels)
    {
        int i = this.te.getField(3);

        if (i == 0)
        {
            i = 200;
        }

        return this.te.getField(2) * pixels / i;
    }

}
