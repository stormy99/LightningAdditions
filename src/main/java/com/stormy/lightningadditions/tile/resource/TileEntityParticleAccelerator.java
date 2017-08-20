/*
 * *******************************************************************************
 * Copyright (c) 2017 StormyMode, MiningMark48. All Rights Reserved!
 * This file is part of Lightning Additions (MC-Mod).
 *
 * This project cannot be copied and/or distributed without the express
 * permission of StormyMode, MiningMark48 (Developers)!
 * *******************************************************************************
 */

package com.stormy.lightningadditions.tile.resource;

import com.stormy.lightningadditions.crafting.RegistryParticleAccelerator;
import com.stormy.lightningadditions.tile.base.LATile;
import com.stormy.lightningadditions.utility.logger.LALogger;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.NonNullList;
import net.minecraftforge.common.util.Constants;

import javax.annotation.Nonnull;
import java.util.Map;

/**
 * Created by KitsuneAlex & MiningMark48
 */
public class TileEntityParticleAccelerator extends LATile implements ISidedInventory {

    private int defaultCooldown = 100;
    private int cooldown = defaultCooldown;
    private double progress = 0;

    private NonNullList<ItemStack> inventory = NonNullList.withSize(4, ItemStack.EMPTY);

    @Override
    public void readFromNBT(NBTTagCompound tag) {
        super.readFromNBT(tag);
        NBTTagList inventoryTagList = tag.getTagList("inventory", Constants.NBT.TAG_COMPOUND);

        for(int i = 0; i < inventoryTagList.tagCount(); i++){
            NBTTagCompound stackTag = inventoryTagList.getCompoundTagAt(i);
            int slot = stackTag.getShort("slot");
            this.inventory.set(slot, new ItemStack(stackTag));
        }

        this.cooldown = tag.getInteger("cooldown");
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
        tag.setInteger("cooldown", this.cooldown);
        return tag;
    }

    @Override
    public void update() {
        if(!this.world.isRemote) {

            if(!this.isBurning()) {

                if(this.cooldown <= 0) {
                    this.cooldown = this.defaultCooldown;

                    Map<ItemStack, ItemStack> entries = RegistryParticleAccelerator.instance().getResult(this.getStackInSlot(1));

                    Map.Entry<ItemStack, ItemStack> entry = entries.entrySet().iterator().next();
                    this.inventory.set(2, entry.getKey().copy());
                    this.inventory.set(3, entry.getValue().copy());

                    this.inventory.get(0).shrink(1);
                    this.inventory.get(1).shrink(1);

                    if(this.inventory.get(0).isEmpty()) {
                        this.inventory.set(0, ItemStack.EMPTY);
                    }
                    if(this.inventory.get(1).isEmpty()) {
                        this.inventory.set(1, ItemStack.EMPTY);
                    }
                    if(this.inventory.get(2).isEmpty()) {
                        this.inventory.set(2, ItemStack.EMPTY);
                    }
                    if(this.inventory.get(3).isEmpty()) {
                        this.inventory.set(3, ItemStack.EMPTY);
                    }
                }
            }

            if(this.isBurning() && this.canUse()) {
                this.cooldown--;
                this.progress = ((double) (this.defaultCooldown - this.cooldown) / (double) this.defaultCooldown) * 100;
                LALogger.debug("Current Progress: " + getProgress());
            }else if (!this.canUse()){
                this.cooldown = this.defaultCooldown;
            }

            this.markDirty();
        }
    }

    private boolean canUse(){
        if (!this.inventory.get(0).isEmpty() && !this.inventory.get(1).isEmpty() && ((this.inventory.get(2).isEmpty() && this.inventory.get(3).isEmpty()) || (this.inventory.get(2).isStackable() && this.inventory.get(3).isStackable()))){
            if (isRecipe(this.getStackInSlot(1))){
                return true;
            }
            return false;
        }
        return false;
    }

    public double getProgress(){
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

    public boolean isBurning() {
        return this.cooldown > 0;
    }

    public boolean isRecipe(ItemStack stack){
        ItemStack itemstack = ItemStack.EMPTY;
        Map<ItemStack, ItemStack> entries = RegistryParticleAccelerator.instance().getResult(stack);
        if (entries != null) {
            Map.Entry<ItemStack, ItemStack> entry = entries.entrySet().iterator().next();
            itemstack = entry.getKey();
        }

        if (itemstack.isEmpty())
        {
            return false;
        }
        else
        {
            ItemStack itemstack1 = (this.getStackInSlot(2));
            if (itemstack1.isEmpty()) return true;
            if (!itemstack1.isItemEqual(itemstack)) return false;
            int result = itemstack1.getCount() + itemstack.getCount();
            return result <= this.getInventoryStackLimit() && result <= itemstack1.getMaxStackSize();
        }
    }

}
