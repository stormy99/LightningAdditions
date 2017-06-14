package com.stormy.lightningadditions.proxy;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.event.*;

public abstract class CommonProxy
{


    public void preInit(FMLPreInitializationEvent event){ }


    public void registerModel(Item item){

    }

    public void registerRenders(){

    }

    public void init(FMLInitializationEvent event){ }

    public World getClientWorld() {
        return null;
    }
    public EntityPlayer getClientPlayer() {
        return null;
    }

    public void postInit(FMLPostInitializationEvent event){ }






}
