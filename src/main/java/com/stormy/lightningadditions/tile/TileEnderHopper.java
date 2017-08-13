/*
 * ********************************************************************************
 * Copyright (c) 2017 StormyMode, MiningMark48. All Rights Reserved!
 * This file is part of Lightning Additions (MC-Mod).
 *
 * This project cannot be copied and/or distributed without the express
 * permission of StormyMode, MiningMark48 (Developers)!
 * ********************************************************************************
 */

package com.stormy.lightningadditions.tile;

import com.stormy.lightningadditions.block.BlockEnderHopper;
import com.stormy.lightninglib.lib.utils.InventoryUtils;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryEnderChest;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntityHopper;
import net.minecraft.util.EnumFacing;

public class TileEnderHopper extends TileSimpleInventory {

    @Override
    public int getSizeInventory() {

        return 1;
    }

    public String pName = "";

    int ticks = 0;

    @Override
    public void update() {

        ticks++;

        if(ticks % 8 != 0)
            return;

        EnumFacing enumf = BlockEnderHopper.getFacing(this.getBlockMetadata());

        if(InventoryUtils.getInventory(getWorld(), getPos().up()) != null && this.getWorld().getBlockState(getPos().up()).getBlock() != Blocks.ENDER_CHEST){

            IInventory ii = InventoryUtils.getInventory(getWorld(), getPos().up());

            for(int i = 0; i < ii.getSizeInventory(); i++){
                if(ii.getStackInSlot(i).isEmpty())
                    continue;

                ItemStack is = ii.getStackInSlot(i);

                if(this.getStackInSlot(0).isEmpty())
                    this.setInventorySlotContents(0, ii.decrStackSize(i, 1));
                else if(is.getItem() == this.getStackInSlot(0).getItem() && is.getItemDamage() == this.getStackInSlot(0).getItemDamage() && is.getCount() + ii.getStackInSlot(i).getCount() <= is.getMaxStackSize())
                    this.setInventorySlotContents(0, new ItemStack(is.getItem(),this.getStackInSlot(0).getCount()+ii.getStackInSlot(i).splitStack(1).getCount(),is.getItemDamage()));

                ii.markDirty();
                this.markDirty();
            }

        }else if(this.getWorld().getBlockState(getPos().up()).getBlock() == Blocks.ENDER_CHEST){

            ItemStack is = this.getStackInSlot(0);


            EntityPlayer player = this.getWorld().getPlayerEntityByName(pName);

            if(player == null)
                return;

            InventoryEnderChest ii = player.getInventoryEnderChest();


            int slotChosen = -1;
            for(int i = 18; i<27; i++){

                if(ii.getStackInSlot(i).isEmpty())
                    continue;


                if(is == null || is.isEmpty()){
                    this.setInventorySlotContents(0, ii.decrStackSize(i, 1));
                    ii.markDirty();
                    this.markDirty();

                }

            }

        }

        if(InventoryUtils.getInventory(getWorld(), getPos().add(enumf.getDirectionVec())) != null && this.getWorld().getBlockState(getPos().up()).getBlock() == Blocks.ENDER_CHEST){

            if(this.getStackInSlot(0).isEmpty())
                return;

            TileEntityHopper.putStackInInventoryAllSlots(this, InventoryUtils.getInventory(getWorld(), getPos().add(enumf.getDirectionVec())), this.decrStackSize(0, 1), enumf.getOpposite());
            this.markDirty();
            InventoryUtils.getInventory(getWorld(), getPos().add(enumf.getDirectionVec())).markDirty();


        }else if(this.getWorld().getBlockState(getPos().add(enumf.getDirectionVec())).getBlock() == Blocks.ENDER_CHEST){

            if(this.getStackInSlot(0).isEmpty())
                return;

            ItemStack is = this.getStackInSlot(0);

            EntityPlayer player = this.getWorld().getPlayerEntityByName(pName);

            if(player == null)
                return;

            InventoryEnderChest ii = player.getInventoryEnderChest();

            int slotChosen = -1;
            for(int i = 18; i<27; i++){

                if(!ii.getStackInSlot(i).isEmpty()){

                    if(ii.getStackInSlot(i).getItem() == is.getItem() && ii.getStackInSlot(i).getItemDamage() == is.getItemDamage()){


                        if(ii.getStackInSlot(i).getCount() == ii.getStackInSlot(i).getMaxStackSize())
                            continue;

                        if(is.getCount() + ii.getStackInSlot(i).getCount() <= is.getMaxStackSize()){

                            ii.setInventorySlotContents(i, new ItemStack(is.getItem(),is.getCount()+ii.getStackInSlot(i).getCount(),is.getItemDamage()));
                            this.setInventorySlotContents(0, ItemStack.EMPTY);
                            ii.markDirty();
                            this.markDirty();
                            return;
                        }else{

                            ii.setInventorySlotContents(i, new ItemStack(is.getItem(),is.getMaxStackSize(),is.getItemDamage()));
                            this.setInventorySlotContents(0, new ItemStack(is.getItem(),is.getCount()+ii.getStackInSlot(i).getCount()-is.getMaxStackSize(),is.getItemDamage()));
                            ii.markDirty();
                            this.markDirty();
                            return;

                        }

                    }

                }else{
                    slotChosen = i;
                    break;
                }

            }

            if(slotChosen != -1){
                ii.setInventorySlotContents(slotChosen, is);
                this.setInventorySlotContents(0, ItemStack.EMPTY);
                ii.markDirty();
                this.markDirty();
            }


        }

    }

    @Override
    public void readCustomNBT(NBTTagCompound par1nbtTagCompound) {

        super.readCustomNBT(par1nbtTagCompound);

        this.pName = par1nbtTagCompound.getString("PNAME");

        this.ticks = par1nbtTagCompound.getInteger("TICKS");

    }

    @Override
    public void writeCustomNBT(NBTTagCompound par1nbtTagCompound) {

        super.writeCustomNBT(par1nbtTagCompound);

        par1nbtTagCompound.setString("PNAME", this.pName);

        par1nbtTagCompound.setInteger("TICKS", this.ticks);
    }


}
