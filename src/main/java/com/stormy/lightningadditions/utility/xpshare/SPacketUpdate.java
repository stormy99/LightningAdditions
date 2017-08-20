/*
 * ********************************************************************************
 * Copyright (c) 2017 StormyMode, MiningMark48. All Rights Reserved!
 * This file is part of Lightning Additions (MC-Mod).
 *
 * This project cannot be copied and/or distributed without the express
 * permission of StormyMode, MiningMark48 (Developers)!
 * ********************************************************************************
 */

package com.stormy.lightningadditions.utility.xpshare;

import com.stormy.lightningadditions.tile.resource.TileEntitySharingXP;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class SPacketUpdate implements IMessage
{
    private BlockPos pos;
    private int storedLevels;

    public SPacketUpdate() {}

    public SPacketUpdate(TileEntitySharingXP te)
    {
        this(te.getPos(), te.getStoredLevels());
    }

    public SPacketUpdate(BlockPos p, int sL)
    {
        pos = p;
        storedLevels = sL;
    }

    @Override
    public void toBytes(ByteBuf buf)
    {
        buf.writeLong(pos.toLong());
        buf.writeInt(storedLevels);
    }

    @Override
    public void fromBytes(ByteBuf buf)
    {
        pos = BlockPos.fromLong(buf.readLong());
        storedLevels = buf.readInt();
    }

    public static class Handler implements IMessageHandler<SPacketUpdate, IMessage>
    {
        @Override
        public IMessage onMessage(SPacketUpdate message, MessageContext ctx)
        {
            Minecraft.getMinecraft().addScheduledTask(() -> {
                ((TileEntitySharingXP)Minecraft.getMinecraft().world.getTileEntity(message.pos)).setStoredLevels(message.storedLevels);
            });
            return null;
        }
    }
}
