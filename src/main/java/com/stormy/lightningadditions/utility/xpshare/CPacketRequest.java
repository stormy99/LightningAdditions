package com.stormy.lightningadditions.utility.xpshare;

import com.stormy.lightningadditions.tile.TileEntitySharingXP;
import io.netty.buffer.ByteBuf;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class CPacketRequest implements IMessage
{
    private BlockPos pos;
    private int dimension;

    public CPacketRequest() {}

    public CPacketRequest(TileEntitySharingXP te)
    {
        this(te.getPos(), te.getWorld().provider.getDimension());
    }

    public CPacketRequest(BlockPos p, int dim)
    {
        pos = p;
        dimension = dim;
    }

    @Override
    public void toBytes(ByteBuf buf)
    {
        buf.writeLong(pos.toLong());
        buf.writeInt(dimension);
    }

    @Override
    public void fromBytes(ByteBuf buf)
    {
        pos = BlockPos.fromLong(buf.readLong());
        dimension = buf.readInt();
    }

    public static class Handler implements IMessageHandler<CPacketRequest, SPacketUpdate>
    {
        @Override
        public SPacketUpdate onMessage(CPacketRequest message, MessageContext ctx)
        {
            TileEntitySharingXP te = (TileEntitySharingXP) FMLCommonHandler.instance().getMinecraftServerInstance().worldServerForDimension(message.dimension).getTileEntity(message.pos);

            if(te != null)
                return new SPacketUpdate(te);
            else
                return null;
        }
    }
}
