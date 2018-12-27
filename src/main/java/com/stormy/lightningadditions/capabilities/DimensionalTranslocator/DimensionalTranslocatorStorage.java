package com.stormy.lightningadditions.capabilities.DimensionalTranslocator;

import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;

import javax.annotation.Nullable;

public class DimensionalTranslocatorStorage implements Capability.IStorage<IDimensionalTranslocator>
{
    @Nullable
    @Override
    public NBTBase writeNBT(Capability<IDimensionalTranslocator> capability, IDimensionalTranslocator instance, EnumFacing side) {
        NBTTagCompound nbt = new NBTTagCompound();

        if(instance.getStoredBlockNBT() != null)
            nbt.setTag("storedNBT", instance.getStoredBlockNBT());
        if(instance.getStoredName() != null)
            nbt.setString("name", instance.getStoredName());
        return nbt;
    }

    @Override
    public void readNBT(Capability<IDimensionalTranslocator> capability, IDimensionalTranslocator instance, EnumFacing side, NBTBase nbtIn) {
        if(nbtIn instanceof NBTTagCompound){
            NBTTagCompound nbt = ((NBTTagCompound) nbtIn);
            instance.storeBlockNBT(nbt.getCompoundTag("storedNBT"));
            instance.setBlockName(nbt.getString("name"));
        }
    }
}
