package com.stormy.lightningadditions.mod;

import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public interface IContentRegister {
    void handlePreInit(FMLPreInitializationEvent var1);

    void handleInit(FMLInitializationEvent var1);

    void handlePostInit(FMLPostInitializationEvent var1);
}
