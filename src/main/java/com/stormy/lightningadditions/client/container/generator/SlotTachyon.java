package com.stormy.lightningadditions.client.container.generator;

import com.stormy.lightningadditions.init.ModItems;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class SlotTachyon extends Slot{

    public SlotTachyon(IInventory inventoryIn, int index, int xPosition, int yPosition) {
        super(inventoryIn, index, xPosition, yPosition);
    }

    @Override
    public boolean isItemValid(ItemStack stack) {
        if (stack.getItem() == ModItems.tachyon_shard){
            return true;
        }
        return false;
    }
}
