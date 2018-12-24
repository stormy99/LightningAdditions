package com.stormy.lightninglib.lib.library;

import cofh.redstoneflux.api.IEnergyContainerItem;
import cofh.redstoneflux.api.IEnergyHandler;
import cofh.redstoneflux.api.IEnergyProvider;
import cofh.redstoneflux.api.IEnergyReceiver;
import com.stormy.lightninglib.lib.utils.LogUtils;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.energy.IEnergyStorage;

/**
  * Universal Energy Helpers/Utils
 */
public class EnergyUtils
{
    public enum EnergyType
    { RF, Tesla, Forge, NONE }

    public static boolean isEnergyReceiverFromSide(TileEntity te, EnumFacing facing)
    { return (te instanceof IEnergyReceiver) ? ((IEnergyReceiver) te).canConnectEnergy(facing) : false; }

    public static ItemStack setFullPowerRF(ItemStack stack)
    {
        ItemStack fullItem = stack.copy();
        IEnergyContainerItem container = (IEnergyContainerItem) fullItem.getItem();
        int total = container.getMaxEnergyStored(fullItem);
        while (container.getEnergyStored(fullItem) < container.getMaxEnergyStored(fullItem)) {
            container.receiveEnergy(fullItem, total, false); }
        return fullItem; }

    public static int getRF(IEnergyHandler handler)
    {
        if (handler != null) { return handler.getEnergyStored(EnumFacing.DOWN); }
        return 0; }

    public static int getRFCapacity(IEnergyHandler handler)
    { if (handler != null) { return handler.getMaxEnergyStored(EnumFacing.DOWN); }
        return 0; }

    public static IEnergyHandler getRFTile(World world, BlockPos pos)
    { TileEntity te = world.getTileEntity(pos);
        if (te instanceof IEnergyHandler) { IEnergyHandler handler = (IEnergyHandler) te;
            return handler; }
        return null; }

    public static boolean isEnergyTile(TileEntity tile) { return isEnergyTile(tile, null); }

    public static boolean isEnergyTile(TileEntity tile, EnumFacing side) { return tile instanceof IEnergyReceiver || tile.hasCapability(CapabilityEnergy.ENERGY, side); }

    public static int getEnergyStored(TileEntity tile) { return getEnergyStored(tile, null); }

    public static int getEnergyStored(TileEntity tile, EnumFacing side) { if (tile instanceof IEnergyHandler)
    { return ((IEnergyHandler) tile).getEnergyStored(side); }
        else if (tile.hasCapability(CapabilityEnergy.ENERGY, side))
        { IEnergyStorage cap = tile.getCapability(CapabilityEnergy.ENERGY, null);
            if (cap == null) { LogUtils.error("Detected broken energy for tile: " + tile.getClass() + " Please report to the author(s) of the mod!");
                return 0; }
            return cap.getEnergyStored(); }
        else { return 0; }
    }

    public static int getMaxEnergyStored(TileEntity tile) { return getEnergyStored(tile, null); }

    public static int getMaxEnergyStored(TileEntity tile, EnumFacing side)
    { if (tile instanceof IEnergyHandler) { return ((IEnergyHandler) tile).getMaxEnergyStored(side); }
        else if (tile.hasCapability(CapabilityEnergy.ENERGY, side)) { net.minecraftforge.energy.IEnergyStorage cap = tile.getCapability(CapabilityEnergy.ENERGY, null);
            if (cap == null)
            { LogUtils.error("Detected broken energy for tile: " + tile.getClass() + " Please report to the author(s) of the mod!");
                return 0; }
            return cap.getMaxEnergyStored();
        }
        else { return 0; }
    }

    public static boolean canReceiveEnergy(TileEntity tile) { return canReceiveEnergy(tile, null); }

    public static boolean canReceiveEnergy(TileEntity tile, EnumFacing side) {
        return (tile instanceof IEnergyReceiver && (side == null || ((IEnergyReceiver) tile).canConnectEnergy(side)))
                    || (tile.hasCapability(CapabilityEnergy.ENERGY, side) && tile.getCapability(CapabilityEnergy.ENERGY, side).canReceive()); }

    public static int insertEnergy(TileEntity tile, int energy, boolean simulate) { return insertEnergy(tile, energy, null, simulate); }

    public static int insertEnergy(TileEntity tile, int energy, EnumFacing side, boolean simulate)
    { if (tile.getWorld().isRemote) { return 0; }
        if (tile instanceof IEnergyReceiver) { return ((IEnergyReceiver) tile).receiveEnergy(side, energy, simulate); }
        else if (tile.hasCapability(CapabilityEnergy.ENERGY, side)) { net.minecraftforge.energy.IEnergyStorage cap = tile.getCapability(CapabilityEnergy.ENERGY, side);
            if (cap == null)
            { LogUtils.error("Detected broken energy for tile: " + tile.getClass() + " Please report to the author(s) of the mod!");
                return 0; }
            if (cap.canReceive()) { return cap.receiveEnergy(energy, simulate); }
        }
        return 0; }

    public static boolean canExtractEnergy(TileEntity tile) { return canReceiveEnergy(tile, null); }

    public static boolean canExtractEnergy(TileEntity tile, EnumFacing side) {
        return tile instanceof IEnergyProvider || (tile.hasCapability(CapabilityEnergy.ENERGY, side)
                    && tile.getCapability(CapabilityEnergy.ENERGY, side).canExtract()); }

    public static int extractEnergy(TileEntity tile, int energy, boolean simulate) { return extractEnergy(tile, energy, null, simulate); }

    public static int extractEnergy(TileEntity tile, int energy, EnumFacing side, boolean simulate)
    {
        if (tile.getWorld().isRemote) { return 0; }
        if (tile instanceof IEnergyProvider) { return ((IEnergyProvider) tile).extractEnergy(side, energy, simulate); }
        else if (tile.hasCapability(CapabilityEnergy.ENERGY, side)) { net.minecraftforge.energy.IEnergyStorage cap = tile.getCapability(CapabilityEnergy.ENERGY, side);
            if (cap == null)
            { LogUtils.error("Detected broken energy for tile: " + tile.getClass() + " Please report to the author(s) of the mod!");
                return 0; }
            if (cap.canExtract()) { return cap.extractEnergy(energy, simulate); }
        }
        return 0; }

    public static boolean isEnergyStack(ItemStack stack) { return !stack.isEmpty() && (stack.getItem() instanceof IEnergyContainerItem
                                                                    || stack.hasCapability(CapabilityEnergy.ENERGY, null)); }
    public static int getEnergyStored(ItemStack stack) {
        if (stack.isEmpty()) { return 0; }
        else if (stack.getItem() instanceof IEnergyContainerItem) { return ((IEnergyContainerItem) stack.getItem()).getEnergyStored(stack); }
        else if (stack.hasCapability(CapabilityEnergy.ENERGY, null))
        { net.minecraftforge.energy.IEnergyStorage cap = stack.getCapability(CapabilityEnergy.ENERGY, null);
            if (cap == null)
            { LogUtils.error("Detected broken energy capability on item: " + stack.getItem().getRegistryName() + " Please report to the author(s) of the mod!");
                return 0; }
            return cap.getEnergyStored(); }
        else { return 0; }
    }

    public static int getMaxEnergyStored(ItemStack stack) {
        if (stack.isEmpty()) { return 0; }
        else if (stack.getItem() instanceof IEnergyContainerItem)
        { return ((IEnergyContainerItem) stack.getItem()).getMaxEnergyStored(stack); }
        else if (stack.hasCapability(CapabilityEnergy.ENERGY, null))
        { net.minecraftforge.energy.IEnergyStorage cap = stack.getCapability(CapabilityEnergy.ENERGY, null);
            if (cap == null)
            { LogUtils.error("Detected broken energy for item: " + stack.getItem().getRegistryName() + " Please report to the author(s) of the mod!");
                return 0; }
            return cap.getMaxEnergyStored(); }
        else { return 0; }
    }

    public static boolean canReceiveEnergy(ItemStack stack) { return !stack.isEmpty() && (stack.getItem() instanceof IEnergyContainerItem
            || (stack.hasCapability(CapabilityEnergy.ENERGY, null) && stack.getCapability(CapabilityEnergy.ENERGY, null).canReceive())); }

    public static int insertEnergy(ItemStack stack, int energy, boolean simulate) {
        if (stack.isEmpty()) { return 0; }
        else if (stack.getItem() instanceof IEnergyContainerItem) { return ((IEnergyContainerItem) stack.getItem()).receiveEnergy(stack, energy, simulate); }
        else if (stack.hasCapability(CapabilityEnergy.ENERGY, null))
        { net.minecraftforge.energy.IEnergyStorage cap = stack.getCapability(CapabilityEnergy.ENERGY, null);
            if (cap == null)
            { LogUtils.error("Detected broken energy for item: " + stack.getItem().getRegistryName() + " Please report to the author(s) of the mod!");
                return 0; }
            if (cap.canReceive()) { return cap.receiveEnergy(energy, simulate); }
        }
        return 0; }

    public static boolean canExtractEnergy(ItemStack stack) {
        return !stack.isEmpty() && (stack.getItem() instanceof IEnergyContainerItem || (stack.hasCapability(CapabilityEnergy.ENERGY, null)
                    && stack.getCapability(CapabilityEnergy.ENERGY, null).canExtract())); }

    public static int extractEnergy(ItemStack stack, int energy, boolean simulate) {
        if (stack.isEmpty()) { return 0; }
        else if (stack.getItem() instanceof IEnergyContainerItem)
        { return ((IEnergyContainerItem) stack.getItem()).extractEnergy(stack, energy, simulate); }
        else if (stack.hasCapability(CapabilityEnergy.ENERGY, null)) { net.minecraftforge.energy.IEnergyStorage cap = stack.getCapability(CapabilityEnergy.ENERGY, null);
            if (cap == null)
            { LogUtils.error("Detected broken energy on item: " + stack.getItem().getRegistryName() + " Please report to the author(s) of the mod!");
                return 0; }
            if (cap.canExtract())
            { return cap.extractEnergy(energy, simulate); }
        }
        return 0; }
}
