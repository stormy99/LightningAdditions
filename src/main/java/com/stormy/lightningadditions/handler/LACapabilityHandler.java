package com.stormy.lightningadditions.handler;

import com.stormy.lightningadditions.capabilities.DimensionalTranslocator.DimensionalTranslocatorFactory;
import com.stormy.lightningadditions.capabilities.DimensionalTranslocator.DimensionalTranslocatorStorage;
import com.stormy.lightningadditions.capabilities.DimensionalTranslocator.IDimensionalTranslocator;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.CapabilityManager;

public class LACapabilityHandler {
    @CapabilityInject(IDimensionalTranslocator.class)
    public static Capability<IDimensionalTranslocator> CAPABILITY_DIMENSIONAL_TRANSLOCATOR = null;

    public static void register()
    {
        CapabilityManager.INSTANCE.register(IDimensionalTranslocator.class, new DimensionalTranslocatorStorage(), new DimensionalTranslocatorFactory());

        MinecraftForge.EVENT_BUS.register(new LACapabilityHandler());
    }
}