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

import com.stormy.lightningadditions.block.generator.BlockBioFuelGenerator;
import com.stormy.lightningadditions.handler.generator.BioFuelRegistry;
import com.stormy.lightningadditions.init.ModBlocks;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.energy.CapabilityEnergy;

public class TileEntityBioFuelGenerator extends TileEntityBaseGenerator{

    private boolean isActive = false;

    //TODO: Make configs for all values

    private int increase_per_tick = 16;

    private static int maxExtract = 1000;
    private static int maxRF = 75000;
    private int current_RF;
    private int cooldown;
    public int maxCooldown;

    public TileEntityBioFuelGenerator(){
        super(NonNullList.withSize(1, ItemStack.EMPTY), maxExtract, maxRF);
    }

    public boolean isItemBioFuel(ItemStack stack){
        return BioFuelRegistry.getFuelValue(stack) > 0;
    }

    @Override
    public boolean isItemValidForSlot(int index, ItemStack stack) {
        return isItemBioFuel(stack);
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
    public NBTTagCompound writeToNBT(NBTTagCompound compound) {

        super.writeToNBT(compound);

        compound.setInteger("currentRF", this.current_RF);
        compound.setInteger("cooldown", this.cooldown);
        compound.setInteger("ipt", this.increase_per_tick);
        compound.setInteger("maxCooldown", this.maxCooldown);

        return compound;

    }

    @Override
    public void readFromNBT(NBTTagCompound compound) {
        super.readFromNBT(compound);

        this.current_RF = compound.getInteger("currentRF");
        this.cooldown = compound.getInteger("cooldown");
        this.increase_per_tick = compound.getInteger("ipt");
        this.maxCooldown = compound.getInteger("maxCooldown");

    }

    @Override
    public void update() {
        if (this.world != null){

            if (this.current_RF < maxRF) {
                this.isActive = true;

                if (!this.getStackInSlot(0).isEmpty()) {

                    ItemStack stack = this.getStackInSlot(0);

                    if (this.cooldown <= 0) {
                        if (isItemBioFuel(stack)) {
                            this.cooldown = BioFuelRegistry.getFuelValue(stack);
                            this.maxCooldown = this.cooldown;
                            stack.shrink(1);
                        }
                    }
                }

                if (this.cooldown > 0) {
                    this.cooldown--;
                    this.current_RF += this.getField(3);
                }else{
                    this.isActive = false;
                }

            } else {
                this.isActive = false;
            }

            if (this.world.getBlockState(pos).getBlock() == ModBlocks.biofuel_generator){
                BlockBioFuelGenerator gen = (BlockBioFuelGenerator) this.world.getBlockState(pos).getBlock();
                gen.setState(this.world, this.pos, isActive);
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
