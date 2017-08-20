/*
 * *******************************************************************************
 * Copyright (c) 2017 StormyMode, MiningMark48. All Rights Reserved!
 * This file is part of Lightning Additions (MC-Mod).
 *
 * This project cannot be copied and/or distributed without the express
 * permission of StormyMode, MiningMark48 (Developers)!
 * *******************************************************************************
 */

package com.stormy.lightningadditions.container.resource;

import com.stormy.lightningadditions.container.slot.SlotOutput;
import com.stormy.lightningadditions.tile.resource.TileEntityParticleAccelerator;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

/**
 * Created by KitsuneAlex
 */
public class ContainerParticleAccelerator extends Container {

    private TileEntityParticleAccelerator tile;

    public ContainerParticleAccelerator(InventoryPlayer inventoryPlayer, TileEntityParticleAccelerator tile){
        this.tile = tile;
        this.bindPlayerInventory(inventoryPlayer, 8, 84);
    }

    private void bindPlayerInventory(InventoryPlayer inventoryPlayer, int x, int y) {

        //Fuel
        this.addSlotToContainer(new Slot(tile, 0, 40, 53));
        //Crafting Item
        this.addSlotToContainer(new Slot(tile, 1, 40, 17));
        //Output 1
        this.addSlotToContainer(new SlotOutput(tile, 2, 100, 35));
        //Output 2
        this.addSlotToContainer(new SlotOutput(tile, 3, 126, 35));

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

    @Override
    public ItemStack transferStackInSlot(EntityPlayer playerIn, int fromSlot) {
        ItemStack previous = ItemStack.EMPTY;
        Slot slot = (Slot) this.inventorySlots.get(fromSlot);

        if (slot != null && slot.getHasStack()) {
            ItemStack current = slot.getStack();
            previous = current.copy();

            if (fromSlot < 1) {
                // From TE Inventory to Player Inventory
                if (!this.mergeItemStack(current, 1, this.inventorySlots.size(), true)){
                    return ItemStack.EMPTY;
                }
            } else {
                // From Player Inventory to TE Inventory
                if (!this.mergeItemStack(current, 0, 1, false))
                    return ItemStack.EMPTY;
            }

            if (current.isEmpty())
                slot.putStack(ItemStack.EMPTY);
            else
                slot.onSlotChanged();
        }
        return previous;
    }

}
