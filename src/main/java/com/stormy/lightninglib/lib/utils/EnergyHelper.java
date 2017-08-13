package com.stormy.lightninglib.lib.utils;

import cofh.energy.IEnergyContainerItem;
import cofh.energy.IEnergyStorage;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;

import java.util.List;

/**
 * This class contains helper functions related to RF.
 */
public class EnergyHelper
{
    @CapabilityInject(IEnergyStorage.class)
    public static final Capability<IEnergyStorage> ENERGY_HANDLER = null;

    private EnergyHelper() {}

    /* NBT TAG HELPER */
    public static void addEnergyInformation(ItemStack stack, List<String> list) {

        if (stack.getItem() instanceof IEnergyContainerItem) {
            list.add(StringHelper.localize("info.lightninglib.charge") + ": " + StringHelper.getScaledNumber(stack.getTagCompound().getInteger("Energy")) + " / " + StringHelper.getScaledNumber(((IEnergyContainerItem) stack.getItem()).getMaxEnergyStored(stack)) + " RF");
        }
    }

}
