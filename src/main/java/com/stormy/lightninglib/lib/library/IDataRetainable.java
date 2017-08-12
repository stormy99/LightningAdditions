package com.stormy.lightninglib.lib.library;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

/**
 * TileEntity interface to retain data on harvest.
 */
public interface IDataRetainable
{

    NBTTagCompound writeToItemStack(ItemStack stack, boolean willHarvest);
    NBTTagCompound readFromItemStack(ItemStack stack);

    default boolean saveToItem() { return true; } }
