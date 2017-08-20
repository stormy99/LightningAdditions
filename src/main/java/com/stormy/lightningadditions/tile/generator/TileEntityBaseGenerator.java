/*
 *
 *  * ********************************************************************************
 *  * Copyright (c) 2017 StormyMode, MiningMark48. All Rights Reserved!
 *  * This file is part of Lightning Additions (MC-Mod).
 *  *
 *  * This project cannot be copied and/or distributed without the express
 *  * permission of StormyMode, MiningMark48 (Developers)!
 *  * ********************************************************************************
 *
 */

package com.stormy.lightningadditions.tile.generator;

import cofh.energy.IEnergyProvider;
import com.stormy.lightningadditions.tile.base.LATile;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.energy.IEnergyStorage;

import javax.annotation.Nullable;

public abstract class TileEntityBaseGenerator extends LATile implements ITickable, IEnergyStorage, IInventory, IEnergyProvider {

    private int maxExtract;
    private int maxRF;
    private int current_RF;

    public NonNullList<ItemStack> inventory;
    private String customName;

    public TileEntityBaseGenerator(NonNullList inventory, int maxExtract, int maxRF){
        this.inventory = inventory;
        this.maxExtract = maxExtract;
        this.maxRF = maxRF;
    }

    public String getCustomName(){
        return customName;
    }

    public void setCustomName(String customName){
        this.customName = customName;
    }

    @Override
    public String getName() {
        return customName;
    }

    @Override
    public boolean hasCustomName() {
        return this.customName != null && !this.customName.equals("");
    }

    @Nullable
    @Override
    public ITextComponent getDisplayName() {
        return this.hasCustomName() ? new TextComponentString(this.customName) : new TextComponentString(world.getBlockState(pos).getBlock().getLocalizedName());
    }

    @Override
    public int getSizeInventory() {
        return this.inventory.size();
    }

    @Override
    public boolean isEmpty() {
        for (ItemStack itemstack : this.inventory)
        {
            if (!itemstack.isEmpty())
            {
                return false;
            }
        }

        return true;
    }

    @Nullable
    @Override
    public ItemStack getStackInSlot(int index) {
        if (index < 0 || index >= this.getSizeInventory()){
            return ItemStack.EMPTY;
        }
        return this.inventory.get(index);

    }

    @Nullable
    @Override
    public ItemStack decrStackSize(int index, int count) {
        if (this.getStackInSlot(index) != ItemStack.EMPTY){
            ItemStack itemStack;

            if (this.getStackInSlot(index).getCount() <= count){
                itemStack = this.getStackInSlot(index);
                this.setInventorySlotContents(index, ItemStack.EMPTY);
                this.markDirty();
                return itemStack;
            }else{
                itemStack = this.getStackInSlot(index).splitStack(count);

                if (this.getStackInSlot(index).getCount() <= 0){
                    this.setInventorySlotContents(index, ItemStack.EMPTY);
                }else{
                    this.setInventorySlotContents(index, this.getStackInSlot(index));
                }

                this.markDirty();
                return itemStack;

            }
        }else{
            return ItemStack.EMPTY;
        }
    }

    @Nullable
    @Override
    public ItemStack removeStackFromSlot(int index) {
        ItemStack stack = this.getStackInSlot(index);
        this.setInventorySlotContents(index, ItemStack.EMPTY);
        return stack;
    }

    @Override
    public void setInventorySlotContents(int index, @Nullable ItemStack stack) {
        if (index < 0 || index >= this.getSizeInventory()){
            return;
        }

        if (stack != ItemStack.EMPTY && stack.getCount() > this.getInventoryStackLimit()){
            stack.setCount(this.getInventoryStackLimit());
        }

        if (stack != ItemStack.EMPTY && stack.isEmpty()){
            stack = ItemStack.EMPTY;
        }

        this.inventory.set(index, stack);
        this.markDirty();

    }

    @Override
    public int getInventoryStackLimit() {
        return 64;
    }

    @Override
    public boolean isUsableByPlayer(EntityPlayer player) {
        return this.world.getTileEntity(this.getPos()) == this && player.getDistanceSq(this.pos.add(0.5, 0.5, 0.5)) <= 64;
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
        for (int i = 0; i < this.getSizeInventory(); i++){
            this.setInventorySlotContents(i, ItemStack.EMPTY);
        }
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound) {

        super.writeToNBT(compound);

        NBTTagList list = new NBTTagList();

        for (int i = 0; i < this.getSizeInventory(); i++){
            if (this.getStackInSlot(i) != ItemStack.EMPTY && this.getStackInSlot(i) != null){
                NBTTagCompound stackTag = new NBTTagCompound();
                stackTag.setByte("Slot", (byte) i);
                this.getStackInSlot(i).writeToNBT(stackTag);
                list.appendTag(stackTag);
            }
        }

        compound.setTag("Items", list);

        if (this.hasCustomName()){
            compound.setString("CustomName", this.getCustomName());
        }

        return compound;

    }

    @Override
    public void readFromNBT(NBTTagCompound compound) {
        super.readFromNBT(compound);
        NBTTagList list = compound.getTagList("Items", 10);
        for (int i = 0; i < list.tagCount(); i++) {
            NBTTagCompound stackTag = list.getCompoundTagAt(i);
            int slot = stackTag.getByte("Slot") & 255;
            this.setInventorySlotContents(slot, new ItemStack(stackTag));
        }

        if (compound.hasKey("CustomName", 8)) {
            this.setCustomName(compound.getString("CustomName"));
        }
    }

    @Override
    public void update() {

    }

    //Energy

    @Override
    public int receiveEnergy(int maxReceive, boolean simulate) {
        return 0;
    }

    @Override
    public int extractEnergy(int maxExtract, boolean simulate) {
        if (!canExtract()){
            return 0;
        }

        int energyExtracted = Math.min(this.current_RF, Math.min(this.maxExtract, maxExtract));
        if (!simulate){
            this.current_RF -= energyExtracted;
        }

        return energyExtracted;
    }

    @Override
    public int getEnergyStored() {
        if (this.current_RF >= this.getMaxEnergyStored()){
            this.current_RF = this.getMaxEnergyStored();
        }
        return current_RF;
    }

    @Override
    public int getMaxEnergyStored() {
        return maxRF;
    }

    @Override
    public boolean canExtract() {
        if (this.getEnergyStored() > 0) {
            return true;
        }
        return false;
    }

    @Override
    public boolean canReceive() {
        return false;
    }

    public void pushEnergy(World world, BlockPos pos){
        for (EnumFacing facing: EnumFacing.values()){
            TileEntity tile = world.getTileEntity(pos.offset(facing));
            if (tile != null){
                if (tile.hasCapability(CapabilityEnergy.ENERGY, facing.getOpposite())){
                    if (tile.getCapability(CapabilityEnergy.ENERGY, facing.getOpposite()).canReceive()) {
                        tile.getCapability(CapabilityEnergy.ENERGY, facing.getOpposite()).receiveEnergy(this.extractEnergy(this.maxExtract, false), false);
                    }
                }
            }
        }
    }

    //COFH

    @Override
    public int getEnergyStored(EnumFacing from) {
        return this.getEnergyStored();
    }

    @Override
    public int getMaxEnergyStored(EnumFacing from) {
        return this.getMaxEnergyStored();
    }

    @Override
    public boolean canConnectEnergy(EnumFacing from) {
        return true;
    }

    @Override
    public int extractEnergy(EnumFacing from, int maxExtract, boolean simulate) {
        if (!canExtract()){
            return 0;
        }

        int energyExtracted = Math.min(this.current_RF, Math.min(this.maxExtract, maxExtract));
        if (!simulate){
            this.current_RF -= energyExtracted;
        }

        return energyExtracted;
    }

}
