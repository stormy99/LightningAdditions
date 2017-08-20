/*
 * *******************************************************************************
 * Copyright (c) 2017 StormyMode, MiningMark48. All Rights Reserved!
 * This file is part of Lightning Additions (MC-Mod).
 *
 * This project cannot be copied and/or distributed without the express
 * permission of StormyMode, MiningMark48 (Developers)!
 * *******************************************************************************
 */

package com.stormy.lightningadditions.client.gui;

import com.stormy.lightningadditions.container.ContainerParticleAccellerator;
import com.stormy.lightningadditions.tile.TileEntityParticleAccelerator;
import com.stormy.lightningadditions.utility.logger.LALogger;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * Created by KitsuneAlex & MiningMark48
 */
public class GuiParticleAccellerator extends GuiContainer {

    private TileEntityParticleAccelerator te;

    private static final ResourceLocation TEXTURE = new ResourceLocation("lightningadditions", "textures/gui/particle_accellerator.png");

    public GuiParticleAccellerator(IInventory inventoryPlayer, TileEntityParticleAccelerator te) {
        super(new ContainerParticleAccellerator(inventoryPlayer, te));
        this.te = te;

        this.xSize = 176;
        this.ySize = 168;
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
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
