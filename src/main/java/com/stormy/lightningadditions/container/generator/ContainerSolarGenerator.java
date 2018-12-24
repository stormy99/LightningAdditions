/*
 *
 *  * ********************************************************************************
 *  * Copyright (c) 2017 StormyMode, MiningMark48. All Rights Reserved!
 *  * This file is part of Lightning Additions (MC-Mod).
 *  *
 *  * This project cannot be copied and/or distributed without the express
 *  * permission of StormyMode, MiningMark48 (Developers)!
 *  * ********************************************************************************
 *
 */

package com.stormy.lightningadditions.container.generator;

import com.stormy.lightningadditions.tile.generator.TileEntitySolarGenerator;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IContainerListener;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ContainerSolarGenerator extends Container{

    private TileEntitySolarGenerator te;

    private int currentRF;
    private int maxRF;
    private int cooldown;
    private int rfPerTick;

    public ContainerSolarGenerator(IInventory playerInv, TileEntitySolarGenerator te){
        this.te = te;

        this.addSlotToContainer(new SlotTachyon(te, 0, 39, 50));

        //Player Inventory
        for (int y = 0; y < 3; ++y) {
            for (int x = 0; x < 9; ++x) {
                this.addSlotToContainer(new Slot(playerInv, x + y * 9 + 9, 8 + x * 18, 84 + y * 18));
            }
        }
        //Player Hotbar
        for (int x = 0; x < 9; ++x) {
            this.addSlotToContainer(new Slot(playerInv, x, 8 + x * 18, 142));
        }
    }

    @Override
    public boolean canInteractWith(EntityPlayer playerIn) {
        return this.te.isUsableByPlayer(playerIn);
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
        this.te.setField(id, data);
    }

    @Override
    public void addListener(IContainerListener listener) {
        super.addListener(listener);
        listener.sendAllWindowProperties(this, this.te);
    }

    @Override
    public void detectAndSendChanges() {
        super.detectAndSendChanges();

        for (int i = 0; i < this.listeners.size(); i++)
        {
            IContainerListener icontainerlistener = (IContainerListener)this.listeners.get(i);

            if (this.currentRF != this.te.getField(0))
            {
                icontainerlistener.sendWindowProperty(this, 0, this.te.getField(0));
            }
            if (this.maxRF != this.te.getField(1))
            {
                icontainerlistener.sendWindowProperty(this, 1, this.te.getField(1));
            }
            if (this.cooldown != this.te.getField(2))
            {
                icontainerlistener.sendWindowProperty(this, 2, this.te.getField(2));
            }
            if (this.rfPerTick != this.te.getField(3))
            {
                icontainerlistener.sendWindowProperty(this, 3, this.te.getField(3));
            }

        }

        this.currentRF = this.te.getField(0);
        this.maxRF = this.te.getField(1);
        this.cooldown = this.te.getField(2);
        this.rfPerTick = this.te.getField(3);

    }

}
