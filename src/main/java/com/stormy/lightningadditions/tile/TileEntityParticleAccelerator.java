/*
 * *******************************************************************************
 * Copyright (c) 2017 StormyMode, MiningMark48. All Rights Reserved!
 * This file is part of Lightning Additions (MC-Mod).
 *
 * This project cannot be copied and/or distributed without the express
 * permission of StormyMode, MiningMark48 (Developers)!
 * *******************************************************************************
 */

package com.stormy.lightningadditions.tile;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.NonNullList;
import net.minecraftforge.common.util.Constants;

import javax.annotation.Nonnull;

/**
 * Created by KitsuneAlex
 */
public class TileEntityParticleAccelerator extends LATile implements ISidedInventory {

    private NonNullList<ItemStack> inventory = NonNullList.withSize(4, ItemStack.EMPTY);
    private int progress = 0;

    @Override
    public void readFromNBT(NBTTagCompound tag) {
        super.readFromNBT(tag);
        NBTTagList inventoryTagList = tag.getTagList("inventory", Constants.NBT.TAG_COMPOUND);

        for(int i = 0; i < inventoryTagList.tagCount(); i++){
            NBTTagCompound stackTag = inventoryTagList.getCompoundTagAt(i);
            int slot = stackTag.getShort("slot");
            this.inventory.set(slot, new ItemStack(stackTag));
        }

        this.progress = tag.getInteger("progress");
    }

    @Nonnull
    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound tag) {
        super.writeToNBT(tag);
        NBTTagList inventoryTagList = new NBTTagList();

        for(int i = 0; i < this.getSizeInventory(); i++){
            NBTTagCompound stackTag = new NBTTagCompound();
            stackTag.setShort("slot", (short)i);
            this.inventory.get(i).writeToNBT(stackTag);
            inventoryTagList.appendTag(stackTag);
        }

        tag.setTag("inventory", inventoryTagList);
        tag.setInteger("progress", this.progress);
        return tag;
    }

    @Override
    public void update() {

    }

    public int getProgress(){
        return this.progress;
    }

    //Inventory

    @Override
    public int[] getSlotsForFace(EnumFacing side) {
        return new int[0];
    }

    @Override
    public boolean canInsertItem(int index, ItemStack stack, EnumFacing direction) {
        return true;
    }

    @Override
    public boolean canExtractItem(int index, ItemStack stack, EnumFacing direction) {
        return true;
    }

    @Override
    public int getSizeInventory() {
        return this.inventory.size();
    }

    @Override
    public boolean isEmpty() {
        for(ItemStack stack : this.inventory){
            return !stack.isEmpty();
        }

        return true;
    }

    @Override
    public ItemStack getStackInSlot(int index) {
        return this.inventory.get(index);
    }

    @Override
    public ItemStack decrStackSize(int index, int count) {
        ItemStack stack = this.getStackInSlot(index);

        if(!stack.isEmpty()){
            if(stack.getCount() <= count){
                this.setInventorySlotContents(index, ItemStack.EMPTY);
                this.markDirty();
                return stack;
            }

            ItemStack splitStack = stack.splitStack(count);

            if(splitStack.getCount() == 0){
                this.setInventorySlotContents(index, ItemStack.EMPTY);
            }
            else{
                this.setInventorySlotContents(index, stack);
            }

            this.markDirty();
            return splitStack;
        }

        return ItemStack.EMPTY;
    }

    @Override
    public ItemStack removeStackFromSlot(int index) {
        ItemStack stack = this.getStackInSlot(index);
        this.setInventorySlotContents(index, ItemStack.EMPTY);
        return stack;
    }

    @Override
    public void setInventorySlotContents(int index, ItemStack stack) {
        this.inventory.set(index, stack);
        this.markDirty();
    }

    @Override
    public int getInventoryStackLimit() {
        return 64;
    }

    @Override
    public boolean isUsableByPlayer(EntityPlayer player) {
        return player.getDistanceSq(this.pos.add(0.5D, 0.5D, 0.5D)) <= 64D;
    }

    @Override
    public void openInventory(EntityPlayer player) {

    }

    @Override
    public void closeInventory(EntityPlayer player) {

    }

    @Override
    public boolean isItemValidForSlot(int index, ItemStack stack) {
        return true;
    }

    @Override
    public int getField(int id) {
        return 0;
    }

    @Override
    public void setField(int id, int value) {

    }

    @Override
    public int getFieldCount() {
        return 0;
    }

    @Override
    public void clear() {
        for(int i = 0; i < this.getSizeInventory(); i++){
            this.setInventorySlotContents(i, ItemStack.EMPTY);
        }

        this.markDirty();
    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public boolean hasCustomName() {
        return false;
    }

}
