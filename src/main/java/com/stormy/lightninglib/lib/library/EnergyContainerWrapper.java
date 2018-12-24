package com.stormy.lightninglib.lib.library;

import cofh.redstoneflux.api.IEnergyContainerItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.energy.IEnergyStorage;
import javax.annotation.Nullable;

public class EnergyContainerWrapper implements IEnergyStorage, ICapabilityProvider {

    private ItemStack itemStack;

    public EnergyContainerWrapper(ItemStack itemStack) { this.itemStack = itemStack; }

    private boolean isStackValid() { return !itemStack.isEmpty() && itemStack.getItem() instanceof IEnergyContainerItem; }

    private IEnergyContainerItem getContainer() { return (IEnergyContainerItem) itemStack.getItem(); }

    @Override
    public int receiveEnergy(int maxReceive, boolean simulate) {
        if (isStackValid()) { return getContainer().receiveEnergy(itemStack, maxReceive, simulate); }
        return 0; }

    @Override
    public int extractEnergy(int maxExtract, boolean simulate)
    { if (isStackValid()) { return getContainer().extractEnergy(itemStack, maxExtract, simulate); }
        return 0; }

    @Override
    public int getEnergyStored() { return isStackValid() ? getContainer().getEnergyStored(itemStack) : 0; }

    @Override
    public int getMaxEnergyStored() { return isStackValid() ? getContainer().getMaxEnergyStored(itemStack) : 0; }

    @Override
    public boolean canExtract() { return isStackValid(); }

    @Override
    public boolean canReceive() { return isStackValid(); }

    @Override
    public boolean hasCapability(Capability<?> capability, @Nullable EnumFacing facing) { return capability == CapabilityEnergy.ENERGY; }

    @Override
    public <T> T getCapability(Capability<T> capability, @Nullable EnumFacing facing) {
        if (capability == CapabilityEnergy.ENERGY) { return CapabilityEnergy.ENERGY.cast(this); }
        return null; }

}
