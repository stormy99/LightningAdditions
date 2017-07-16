/*
 * *******************************************************************************
 * Copyright (c) 2017 StormyMode, MiningMark48. All Rights Reserved!
 * This file is part of Lightning Additions (MC-Mod).
 *
 * This project cannot be copied and/or distributed without the express
 * permission of StormyMode, MiningMark48 (Developers)!
 * *******************************************************************************
 */

package com.stormy.lightningadditions.container;

import com.stormy.lightningadditions.tile.TileEntityParticleAccelerator;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;

/**
 * Created by KitsuneAlex
 */
public class ContainerParticleAccellerator extends Container {

    private TileEntityParticleAccelerator tile;

    public ContainerParticleAccellerator(InventoryPlayer inventoryPlayer, TileEntityParticleAccelerator tile){
        this.tile = tile;
        this.bindPlayerInventory(inventoryPlayer, 0, 0);
    }

    private void bindPlayerInventory(InventoryPlayer inventoryPlayer, int x, int y) {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 9; col++) {
                this.addSlotToContainer(new Slot(inventoryPlayer, col + row * 9 + 9, x + col * 18, y + row * 18));
            }
        }
        for (int slot = 0; slot < 9; slot++) {
            this.addSlotToContainer(new Slot(inventoryPlayer, slot, x + slot * 18, y + 58));
        }
    }

    @Override
    public boolean canInteractWith(EntityPlayer player) {
        return this.tile.isUsableByPlayer(player);
    }

}
