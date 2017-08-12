package com.stormy.lightninglib.lib.item;

import cofh.energy.IEnergyContainerItem;
import com.stormy.lightninglib.lib.library.EnergyContainerWrapper;
import com.stormy.lightninglib.lib.utils.ItemNBT;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.NonNullList;
import net.minecraftforge.common.capabilities.ICapabilityProvider;

/**
 * RF Items for LightningLib
 */
public class ItemBaseEnergy extends ItemBase implements IEnergyContainerItem
{

    public ItemBaseEnergy() {}

    private int capacity;
    private int receive;
    private int extract;

    @Override
    public void getSubItems(Item itemIn, CreativeTabs tab, NonNullList<ItemStack> subItems) {
        subItems.add(new ItemStack(itemIn));
        ItemStack stack = new ItemStack(itemIn);
        setEnergy(stack, getCapacity(stack));
        subItems.add(stack);
    }

    /**
     * Set Capacity, Receive and Extract stats
     * */
    public void setEnergyStats(int capacity, int receive, int extract) {
        this.capacity = capacity;
        this.receive = receive;
        this.extract = extract; }

    public int getCapacity(ItemStack stack) { return capacity; }
    public int getMaxExtract(ItemStack stack) { return extract; }
    public int getMaxReceive(ItemStack stack) { return receive; }

    @Override
    public int receiveEnergy(ItemStack container, int maxReceive, boolean simulate) {
        int energy = ItemNBT.getInteger(container, "Energy", 0);
        int energyReceived = Math.min(getCapacity(container) - energy, Math.min(getMaxReceive(container), maxReceive));

        if (!simulate) {
            energy += energyReceived;
            ItemNBT.setInteger(container, "Energy", energy);
        }

        return energyReceived;
    }

    @Override
    public int extractEnergy(ItemStack container, int maxExtract, boolean simulate) {
        int energy = ItemNBT.getInteger(container, "Energy", 0);
        int energyExtracted = Math.min(energy, Math.min(getMaxExtract(container), maxExtract));

        if (!simulate) {
            energy -= energyExtracted;
            ItemNBT.setInteger(container, "Energy", energy);
        }
        return energyExtracted;
    }

    @Override
    public int getEnergyStored(ItemStack container) {
        return ItemNBT.getInteger(container, "Energy", 0);
    }

    @Override
    public int getMaxEnergyStored(ItemStack container) {
        return getCapacity(container);
    }

    public void setEnergy(ItemStack container, int energy) {
        if (energy > getCapacity(container)) {
            energy = getCapacity(container);
        }
        else if (energy < 0) {
            energy = 0;
        }

        ItemNBT.setInteger(container, "Energy", energy);
    }

    public void modifyEnergy(ItemStack container, int modify) {
        int energy = ItemNBT.getInteger(container, "Energy", 0);
        energy += modify;

        if (energy > getCapacity(container)) {
            energy = getCapacity(container);
        }
        else if (energy < 0) {
            energy = 0;
        }

        ItemNBT.setInteger(container, "Energy", energy);
    }

    @Override
    public boolean showDurabilityBar(ItemStack stack) {
        return !(getEnergyStored(stack) == getMaxEnergyStored(stack));
    }

    @Override
    public double getDurabilityForDisplay(ItemStack stack) {
        return 1D - ((double)getEnergyStored(stack) / (double)getMaxEnergyStored(stack));
    }


    @Override
    public ICapabilityProvider initCapabilities(final ItemStack stack, NBTTagCompound nbt) {
        return new EnergyContainerWrapper(stack);
    }

}
