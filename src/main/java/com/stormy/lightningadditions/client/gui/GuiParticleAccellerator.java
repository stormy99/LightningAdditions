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

import com.stormy.lightningadditions.client.container.ContainerParticleAccellerator;
import com.stormy.lightningadditions.tile.TileEntityParticleAccellerator;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * Created by KitsuneAlex
 */
@SideOnly(Side.CLIENT)
public class GuiParticleAccellerator extends GuiContainer {

    public GuiParticleAccellerator(InventoryPlayer inventoryPlayer, TileEntityParticleAccellerator tile) {
        super(new ContainerParticleAccellerator(inventoryPlayer, tile));
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
        this.drawDefaultBackground();
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
        super.drawGuiContainerForegroundLayer(mouseX, mouseY);
    }

}
