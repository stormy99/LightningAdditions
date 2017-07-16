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
import com.stormy.lightningadditions.tile.TileEntityParticleAccellerator;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * Created by KitsuneAlex
 */
@SideOnly(Side.CLIENT)
public class GuiParticleAccellerator extends GuiContainer {

    private static final ResourceLocation TEXTURE = new ResourceLocation("lightningadditions", "textures/gui/particle_accellerator.png");

    public GuiParticleAccellerator(InventoryPlayer inventoryPlayer, TileEntityParticleAccellerator tile) {
        super(new ContainerParticleAccellerator(inventoryPlayer, tile));
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
        this.mc.getTextureManager().bindTexture(TEXTURE);
        this.drawTexturedModalRect(this.guiLeft, this.guiTop, 0, 0, this.xSize, this.ySize);
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
        super.drawGuiContainerForegroundLayer(mouseX, mouseY);
    }

}
