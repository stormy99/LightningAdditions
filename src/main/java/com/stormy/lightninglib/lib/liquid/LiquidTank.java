package com.stormy.lightninglib.lib.liquid;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTank;
import net.minecraftforge.fml.common.network.NetworkRegistry;

public class LiquidTank extends FluidTank
{
    private final String name;
    private FluidStack lastBeforeUpdate = null;
    Fluid lastFluid;

    public LiquidTank(String name, int capacity, TileEntity tile)
    {
        super(capacity);
        this.name = name;
        this.tile = tile; }

    public boolean isEmpty() { return getFluid() == null || getFluid().amount <= 0; }

    public boolean isFull() { return getFluid() != null && getFluid().amount >= getCapacity(); }

    public Fluid getFluidType() { return getFluid() != null ? getFluid().getFluid() : null; }

    @Override
    public final NBTTagCompound writeToNBT(NBTTagCompound nbt)
    {
        NBTTagCompound tankData = new NBTTagCompound();
        super.writeToNBT(tankData);
        nbt.setTag(name, tankData);
        return nbt; }

    public void setFluidAmount(int amount) {
        if (fluid != null) { fluid.amount = amount; }
    }

    @Override
    public final FluidTank readFromNBT(NBTTagCompound nbt)
    { if (nbt.hasKey(name))
        {// Ability to hook into Empty Tanks
            setFluid(null);
            NBTTagCompound tankData = nbt.getCompoundTag(name);
            super.readFromNBT(tankData); }
        return this; }

    public void compareAndUpdate()
    { if (tile == null || tile.getWorld().isRemote) { return; }
        if (lastFluid == null || (lastFluid != null && (this.getFluid() == null) || this.getFluid().getFluid() == null) || (lastFluid != this.getFluid().getFluid()))
        { if (this.getFluid() == null)
        { lastFluid = null; }
        else { lastFluid = this.getFluid().getFluid(); } }
    }
}
