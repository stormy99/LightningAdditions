package com.stormy.lightningadditions.capabilities.DimensionalTranslocator;

import net.minecraft.nbt.NBTBase;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import static com.stormy.lightningadditions.handler.LACapabilityHandler.CAPABILITY_DIMENSIONAL_TRANSLOCATOR;

public class DimensionalTranslocatorProvider implements ICapabilitySerializable<NBTBase>, ICapabilityProvider
{
    private EnumFacing facing = null;
    private IDimensionalTranslocator instance = null;

    public DimensionalTranslocatorProvider(Capability<IDimensionalTranslocator> capability, EnumFacing facing){
        if(capability != null){
            CAPABILITY_DIMENSIONAL_TRANSLOCATOR = capability;
            this.facing = facing;
            this.instance = CAPABILITY_DIMENSIONAL_TRANSLOCATOR.getDefaultInstance();
        }
    }

    @Override
    public boolean hasCapability(@Nonnull Capability<?> capability, @Nullable EnumFacing facing) {
        if(capability == CAPABILITY_DIMENSIONAL_TRANSLOCATOR)
            return capability == getCapability();
        else
            return false;
    }

    @Nullable
    @Override
    public <T> T getCapability(@Nonnull Capability<T> capability, @Nullable EnumFacing facing) {
        return capability == CAPABILITY_DIMENSIONAL_TRANSLOCATOR ? CAPABILITY_DIMENSIONAL_TRANSLOCATOR.<T> cast(instance) : null;
    }

    @Override
    public NBTBase serializeNBT() {
        return CAPABILITY_DIMENSIONAL_TRANSLOCATOR.getStorage().writeNBT(CAPABILITY_DIMENSIONAL_TRANSLOCATOR, instance, null);
    }

    @Override
    public void deserializeNBT(NBTBase nbt) {
        CAPABILITY_DIMENSIONAL_TRANSLOCATOR.getStorage().readNBT(CAPABILITY_DIMENSIONAL_TRANSLOCATOR, instance, null, nbt);
    }

    public final Capability<IDimensionalTranslocator> getCapability(){
        return CAPABILITY_DIMENSIONAL_TRANSLOCATOR;
    }
}
