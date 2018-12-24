/*
 * ********************************************************************************
 * Copyright (c) 2017 StormyMode, MiningMark48. All Rights Reserved!
 * This file is part of Lightning Additions (MC-Mod).
 *
 * This project cannot be copied and/or distributed without the express
 * permission of StormyMode, MiningMark48 (Developers)!
 * ********************************************************************************
 */

package com.stormy.lightningadditions.tile.resource;

import com.stormy.lightningadditions.config.ConfigurationManagerLA;
import com.stormy.lightningadditions.init.ModBlocks;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityHopper;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.ITickable;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;

public class TileEntityMagnetizedChest extends TileEntity implements IInventory, ITickable{

    public static int INV_SIZE = 9;

    private NonNullList<ItemStack> inventory = NonNullList.withSize(INV_SIZE, ItemStack.EMPTY);

    public TileEntityMagnetizedChest(){

    }

    @Override
    public int getSizeInventory() {
        return inventory.size();
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

    @Override
    public ItemStack getStackInSlot(int index) {
        if (index < 0 || index >= this.getSizeInventory()){
            return ItemStack.EMPTY;
        }
        return inventory.get(index);
    }

    @Override
    public ItemStack decrStackSize(int index, int count) {
        if (this.getStackInSlot(index) != null){
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
                }

                this.markDirty();
                return itemStack;
            }
        }else {
            return ItemStack.EMPTY;
        }
    }

    @Override
    public ItemStack removeStackFromSlot(int index) {
        ItemStack stack = getStackInSlot(index);
        setInventorySlotContents(index, ItemStack.EMPTY);
        return stack;
    }

    @Override
    public void setInventorySlotContents(int index, ItemStack stack) {
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
            this.setInventorySlotContents(i, null);
        }
    }

    @Override
    public String getName() {
        return this.getDisplayName().toString();
    }

    @Override
    public boolean hasCustomName() {
        return false;
    }

    @Nullable
    @Override
    public ITextComponent getDisplayName() {
        return new TextComponentString(ModBlocks.magnetized_chest.getLocalizedName());
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound nbt){
        super.writeToNBT(nbt);

        NBTTagList list = new NBTTagList();
        for (int i = 0; i < this.getSizeInventory(); i++){
            if (this.getStackInSlot(i) != null){
                NBTTagCompound stackTag = new NBTTagCompound();
                stackTag.setByte("Slot", (byte) i);
                this.getStackInSlot(i).writeToNBT(stackTag);
                list.appendTag(stackTag);
            }
        }

        nbt.setTag("Items", list);

        return nbt;
    }

    @Override
    public void readFromNBT(NBTTagCompound nbt) {
        super.readFromNBT(nbt);

        NBTTagList list = nbt.getTagList("Items", 10);
        for (int i = 0; i < list.tagCount(); i++) {
            NBTTagCompound stackTag = list.getCompoundTagAt(i);
            int slot = stackTag.getByte("Slot") & 255;
            this.setInventorySlotContents(slot, new ItemStack(stackTag));
        }

    }

    @Override
    public void update() {
        int range = ConfigurationManagerLA.atomicMagnetRange;
        float suckRange = 1.25f;
        float pullSpeed = ConfigurationManagerLA.atomicMagnetPullSpeed;
        World world = this.getWorld();

        double x = pos.getX();
        double y = pos.getY();
        double z = pos.getZ();

        List<EntityItem> items = world.getEntitiesWithinAABB(EntityItem.class, new AxisAlignedBB(x - range, y - range, z - range, x + range, y + range, z + range));
        for (EntityItem e: items){
            if (!world.isBlockPowered(pos)){

                e.addVelocity((x - e.posX) * pullSpeed, (y - e.posY) * pullSpeed, (z - e.posZ) * pullSpeed);

                world.spawnParticle(EnumParticleTypes.END_ROD, e.posX, e.posY + 0.3, e.posZ, 0.0D, 0.0D, 0.0D);

            }
        }

        List<EntityItem> itemsNear = world.getEntitiesWithinAABB(EntityItem.class, new AxisAlignedBB(x - suckRange, y - suckRange, z - suckRange, x + suckRange, y + suckRange, z + suckRange));
        if (!itemsNear.isEmpty()) {
            for (EntityItem e : itemsNear) {
                ItemStack stack = e.getItem();
                if (!world.isBlockPowered(pos) && !e.isDead) {
                    TileEntityMagnetizedChest te = (TileEntityMagnetizedChest) world.getTileEntity(pos);
                    if (te != null) {
                        ItemStack left = TileEntityHopper.putStackInInventoryAllSlots(this, this, stack, null); //TODO: Make another class for this method since it's used with TileEnderHopper also?
                        e.setItem(left);
                        this.markDirty();

                        if (left.isEmpty()) {
                            for (int i = 1; i <= 15; i++) {
                                world.spawnParticle(EnumParticleTypes.SPELL_MOB, e.posX, e.posY + 0.3, e.posZ, 0.070588D, 0.941176D, 0D);
                                world.spawnParticle(EnumParticleTypes.SPELL_MOB, e.posX + (1/i), e.posY + 0.3, e.posZ + (1/i), 1D, 0.262745D, 0D);
                                world.spawnParticle(EnumParticleTypes.SPELL_MOB, e.posX - (1/i), e.posY + 0.3, e.posZ - (1/i), 1D, 0.615686D, 0D);
                            }
                        }

                    }
                }
            }
        }

    }

}
