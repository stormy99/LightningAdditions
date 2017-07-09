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

import com.stormy.lightningadditions.block.generator.BlockSolarGenerator;
import com.stormy.lightningadditions.init.ModBlocks;
import com.stormy.lightningadditions.init.ModItems;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.energy.CapabilityEnergy;

public class TileEntitySolarGenerator extends TileEntityBaseGenerator {

    public boolean isDay = true;
    public boolean canSeeSky = true;
    private boolean isActive = false;

    //TODO: Make configs for all values

    public int ipt_passive = 64;
    public int ipt_tach = 640;
    private int increase_per_tick;

    private static int maxExtract = 1000;
    private static int maxRF = 150000;
    private int current_RF;
    private int cooldown;
    public int cooldownMax = 120;

    public TileEntitySolarGenerator(){
        super(NonNullList.withSize(1, ItemStack.EMPTY), maxExtract, maxRF);
    }


    @Override
    public int getField(int id) {
        switch (id){
            case 0:
                return this.current_RF;
            case 1:
                return this.maxRF;
            case 2:
                return this.cooldown;
            case 3:
                return this.increase_per_tick;
        }
        return 0;
    }

    @Override
    public void setField(int id, int value) {
        switch(id) {
            case 0:
                this.current_RF = value;
            case 1:
                break;
            case 2:
                this.cooldown = value;
            case 3:
                break;
        }
    }

    @Override
    public int getFieldCount() {
        return 4;
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

        compound.setInteger("currentRF", this.current_RF);
        compound.setInteger("cooldown", this.cooldown);
        compound.setInteger("ipt", this.increase_per_tick);

        return compound;

    }

    @Override
    public void readFromNBT(NBTTagCompound compound) {
        super.readFromNBT(compound);

        this.current_RF = compound.getInteger("currentRF");
        this.cooldown = compound.getInteger("cooldown");
        this.increase_per_tick = compound.getInteger("ipt");

    }

    @Override
    public void update() {
        if (this.world != null){

            if (this.world.getWorldTime() >= 1000 && this.world.getWorldTime() <= 13000){
                this.isDay = true;
            }else{
                this.isDay = false;
            }

            if (this.world.canSeeSky(pos)){
                this.canSeeSky = true;
            }else{
                this.canSeeSky = false;
            }

            if (this.isDay && this.canSeeSky) {
                if (this.current_RF < maxRF) {
                    this.isActive = true;

                    if (this.cooldown <= 0) {
                        if (this.getStackInSlot(0).getItem() == ModItems.tachyon_shard) {
                            this.increase_per_tick = ipt_tach;
                            this.getStackInSlot(0).shrink(1);
                        } else {
                            this.increase_per_tick = ipt_passive;
                        }
                        this.cooldown = cooldownMax;
                    }

                    if (this.cooldown > 0) {
                        this.cooldown--;
                        this.current_RF += this.getField(3);
                    }
                }else{
                    this.isActive = false;
                }
            }

            if (this.world.getBlockState(pos).getBlock() == ModBlocks.solar_generator){
                BlockSolarGenerator solar = (BlockSolarGenerator) this.world.getBlockState(pos).getBlock();
                solar.setState(this.world, this.pos, isActive && isDay && canSeeSky);
            }

            if (this.getEnergyStored() > 0) {
                pushEnergy(this.world, this.pos);
            }

            this.markDirty();

        }
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
