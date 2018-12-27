package com.stormy.lightningadditions.capabilities.DimensionalTranslocator;

import net.minecraft.block.state.IBlockState;
import net.minecraft.nbt.NBTTagCompound;

public interface IDimensionalTranslocator
{
    boolean canStoreBlock();
    boolean hasBlockStored();

    NBTTagCompound getStoredBlockNBT();
    IBlockState getStoredBlockState();
    String getStoredName();

    void setBlockName(String name);
    void storeBlockNBT(NBTTagCompound nbt);
    void storeBlockState(IBlockState base);
    void clearBlockStorage();
}
