package com.stormy.lightningadditions.capabilities.DimensionalTranslocator;

import net.minecraft.block.state.IBlockState;
import net.minecraft.nbt.NBTTagCompound;

import java.util.concurrent.Callable;

public class DimensionalTranslocatorFactory implements Callable<IDimensionalTranslocator>
{
    @Override
    public IDimensionalTranslocator call() throws Exception {
        return new IDimensionalTranslocator() {

            NBTTagCompound storedBlockNBT = null;
            IBlockState storedBlockState = null;
            String name = null;


            @Override
            public boolean hasBlockStored() {
                if(storedBlockState == null)
                    return false;
                else
                    return true;
            }

            @Override
            public boolean canStoreBlock() {
                return hasBlockStored();
            }

            @Override
            public NBTTagCompound getStoredBlockNBT() {
                return storedBlockNBT;
            }

            @Override
            public IBlockState getStoredBlockState() {
                return storedBlockState;
            }

            @Override
            public String getStoredName() {
                return name;
            }

            @Override
            public void storeBlockNBT(NBTTagCompound nbt) {
                storedBlockNBT = nbt;
            }

            @Override
            public void storeBlockState(IBlockState base) {
                storedBlockState = base;
            }

            @Override
            public void setBlockName(String name) {
                this.name = name;
            }

            @Override
            public void clearBlockStorage() {
                storedBlockState = null;
                storedBlockNBT = null;
                name = null;
            }
        };
    }
}
