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

import com.stormy.lightningadditions.container.slot.SlotOutput;
import com.stormy.lightningadditions.tile.TileEntityParticleAccelerator;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IContainerListener;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * Created by KitsuneAlex & MiningMark48
 */
public class ContainerParticleAccellerator extends Container {

    private TileEntityParticleAccelerator tile;
    private int cooldown;
    private int defaultCooldown;
    private int progress;
    private int maxProgress;

    public ContainerParticleAccellerator(IInventory inventoryPlayer, TileEntityParticleAccelerator tile){
        this.tile = tile;
        this.bindPlayerInventory(inventoryPlayer, 8, 84);
    }

    private void bindPlayerInventory(IInventory inventoryPlayer, int x, int y) {

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

    @Override
    @SideOnly(Side.CLIENT)
    public void updateProgressBar(int id, int data)
    {
        this.tile.setField(id, data);
    }

    @Override
    public void addListener(IContainerListener listener) {
        super.addListener(listener);
        listener.sendAllWindowProperties(this, this.tile);
    }

    @Override
    public void detectAndSendChanges() {
        super.detectAndSendChanges();

        for (int i = 0; i < this.listeners.size(); i++)
        {
            IContainerListener icontainerlistener = (IContainerListener)this.listeners.get(i);

            if (this.cooldown != this.tile.getField(0))
            {
                icontainerlistener.sendProgressBarUpdate(this, 0, this.tile.getField(0));
            }
            if (this.defaultCooldown != this.tile.getField(1))
            {
                icontainerlistener.sendProgressBarUpdate(this, 1, this.tile.getField(1));
            }
            if (this.progress != this.tile.getField(2))
            {
                icontainerlistener.sendProgressBarUpdate(this, 2, this.tile.getField(2));
            }
            if (this.maxProgress != this.tile.getField(3))
            {
                icontainerlistener.sendProgressBarUpdate(this, 3, this.tile.getField(3));
            }

        }

        this.cooldown = this.tile.getField(0);
        this.defaultCooldown = this.tile.getField(1);
        this.progress = this.tile.getField(2);
        this.maxProgress = this.tile.getField(3);

    }

}
