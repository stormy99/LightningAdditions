/*
 * ********************************************************************************
 * Copyright (c) 2017 StormyMode, MiningMark48. All Rights Reserved!
 * This file is part of Lightning Additions (MC-Mod).
 *
 * This project cannot be copied and/or distributed without the express
 * permission of StormyMode, MiningMark48 (Developers)!
 * ********************************************************************************
 */

package com.stormy.lightninglib.lib.utils;

import com.stormy.lightningadditions.utility.inventory.NewInventory;
import net.minecraft.block.Block;
import net.minecraft.block.BlockChest;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryLargeChest;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ILockableContainer;
import net.minecraft.world.World;

public class InventoryUtils {

    public static void insertItem(ItemStack ism, IInventory ii){

        if(itemsLeft(ism, ii) != 0)
            return;


        ItemStack is = ism.copy();

        for(int i = 0; i< ii.getSizeInventory();i++){

            ItemStack slot = ii.getStackInSlot(i);

            if(is == null || is.getCount() == 0)
                return;

            if(slot == null){
                ii.setInventorySlotContents(i, is);
                ii.markDirty();
                return;
            }

            if(slot.areItemsEqual(is, slot)){

                if(slot.getCount() +is.getCount() <= slot.getMaxStackSize()){
                    ii.setInventorySlotContents(i, new ItemStack(slot.getItem(),slot.getCount()+is.getCount(),slot.getItemDamage()));
                    ii.markDirty();
                    break;
                }else{

                    is.splitStack(slot.getMaxStackSize()-slot.getCount());
                    ii.setInventorySlotContents(i, new ItemStack(slot.getItem(),slot.getMaxStackSize(),slot.getItemDamage()));
                    ii.markDirty();
                }


            }
        }

    }

    public static int itemsLeft(ItemStack ism, IInventory iio){

        ItemStack is = ism.copy();

        NewInventory ii = new NewInventory(iio);


        for(int i = 0; i< ii.getSizeInventory();i++){

            ItemStack slot = ii.getStackInSlot(i);

            if(is == null || is.isEmpty() || is.getCount() == 0)
                return 0;

            if(slot == null){
                ii.setInventorySlotContents(i, is);
                return 0;
            }

            if(slot.areItemsEqual(is, slot)){

                if(slot.getCount() +is.getCount() <= slot.getMaxStackSize()){
                    ii.setInventorySlotContents(i, new ItemStack(slot.getItem(),slot.getCount() + is.getCount(),slot.getItemDamage()));
                    return 0;
                }else{

                    is.splitStack(slot.getMaxStackSize()-slot.getCount());
                    ii.setInventorySlotContents(i, new ItemStack(slot.getItem(),slot.getMaxStackSize(),slot.getItemDamage()));

                }


            }
        }

        if(is == null  || is.isEmpty() || is.getCount() == 0)
            return 0;

        return is.getCount();
    }

    public static IInventory getInventory(World w, BlockPos bp){

        if(w.getTileEntity(bp) == null)
            return null;

        if(!(w.getTileEntity(bp) instanceof IInventory))
            return null;

        if(w.getTileEntity(bp) instanceof TileEntityChest){
            Block chestBlock = w.getBlockState(bp).getBlock();
            ILockableContainer tc = ((BlockChest)chestBlock).getLockableContainer(w, bp);


            if(w.getBlockState(bp.add(-1, 0, 0)).getBlock() == chestBlock)
                return new InventoryLargeChest("Large chest", ((BlockChest)w.getBlockState(bp.add(-1, 0, 0)).getBlock()).getLockableContainer(w, bp.add(-1, 0, 0)),tc);
            if(w.getBlockState(bp.add(-1, 0, 0)).getBlock() == chestBlock)
                return new InventoryLargeChest("Large chest", ((BlockChest)w.getBlockState(bp.add(1, 0, 0)).getBlock()).getLockableContainer(w, bp.add(1, 0, 0)),tc);
            if(w.getBlockState(bp.add(0, 0, -1)).getBlock() == chestBlock)
                return new InventoryLargeChest("Large chest", ((BlockChest)w.getBlockState(bp.add(0, 0, -1)).getBlock()).getLockableContainer(w, bp.add(0, 0, -1)),tc);
            if(w.getBlockState(bp.add(0, 0, 1)).getBlock() == chestBlock)
                return new InventoryLargeChest("Large chest", ((BlockChest)w.getBlockState(bp.add(0, 0, 1)).getBlock()).getLockableContainer(w, bp.add(0, 0, 1)),tc);
        }


        return (IInventory)w.getTileEntity(bp);
    }

    public static int countofItemStack(IInventory ii, ItemStack is){

        int amount = 0;

        for(int i =0; i< ii.getSizeInventory(); i++){

            if(ItemStack.areItemsEqual(is, ii.getStackInSlot(i)))
                amount += ii.getStackInSlot(i).getCount();


        }

        return amount;
    }

}
