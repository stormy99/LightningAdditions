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

import com.stormy.lightningadditions.LightningAdditions;
import com.stormy.lightningadditions.tile.base.LATile;
import com.stormy.lightningadditions.utility.xpshare.CPacketRequest;
import com.stormy.lightningadditions.utility.xpshare.SPacketUpdate;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.fml.common.network.NetworkRegistry;

public class TileEntitySharingXP extends LATile
{
    private int storedLevels = 0;
    public void addLevel() { addLevel(1); }

    public void addLevel(int lvl)
    {
        storedLevels += lvl;
        markDirty();
        LightningAdditions.network.sendToAllAround(new SPacketUpdate(this), new NetworkRegistry.TargetPoint(world.provider.getDimension(), pos.getX(), pos.getY(), pos.getZ(), 64));
    }

    public void removeLevel()
    {
        if(storedLevels - 1 < 0)
            return;

        storedLevels -= 1;
        markDirty();
        LightningAdditions.network.sendToAllAround(new SPacketUpdate(this), new NetworkRegistry.TargetPoint(world.provider.getDimension(), pos.getX(), pos.getY(), pos.getZ(), 64));
    }

    public void setStoredLevels(int levels)
    { storedLevels = levels; }

    public int getStoredLevels()
    {
        return storedLevels; }
    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound tag)
    {
        tag.setInteger("stored_levels", storedLevels); return super.writeToNBT(tag); }
    @Override
    public void readFromNBT(NBTTagCompound tag)
    {
        storedLevels = tag.getInteger("stored_levels"); super.readFromNBT(tag); }

    @Override
    public void onLoad()
    {
        if(world.isRemote)
            LightningAdditions.network.sendToServer(new CPacketRequest(this));
    }
}
