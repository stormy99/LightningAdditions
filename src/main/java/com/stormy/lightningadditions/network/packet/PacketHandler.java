package com.stormy.lightningadditions.network.packet;

import com.stormy.lightningadditions.reference.ModInformation;
import com.stormy.lightninglib.lib.utils.ICustomField;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.inventory.IInventory;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;

public class PacketHandler {
    public static final SimpleNetworkWrapper INSTANCE = NetworkRegistry.INSTANCE.newSimpleChannel(ModInformation.MODID);
    private static int ID = 0;

    public static void register() {
        INSTANCE.registerMessage(MessageSetFieldClient.class, MessageSetFieldClient.class, ID++, Side.CLIENT);
        INSTANCE.registerMessage(MessageSetFieldServer.class, MessageSetFieldServer.class, ID++, Side.SERVER);
        INSTANCE.registerMessage(MessagePlaySound.class, MessagePlaySound.class, ID++, Side.CLIENT);
    }

    public static void sendTo(IMessage m, EntityPlayerMP p) {
        INSTANCE.sendTo(m, p);
    }

    public static void sendTo(IMessage m) {
        INSTANCE.sendToServer(m);
    }

    public static void sendTo(IMessage m, int range, int dim, BlockPos center) {
        INSTANCE.sendToAllAround(m, new NetworkRegistry.TargetPoint(dim, center.getX(), center.getY(), center.getZ(), range));
    }

    public static void syncFieldClient(EntityPlayer pl, TileEntity te, int startField, int endField) {
        if (!(te instanceof IInventory))
            return;

        byte[] values = new byte[endField - startField + 1];
        int[] fields = new int[endField - startField + 1];

        int index = 0;
        for (int i = startField; i <= endField; i++) {
            fields[index] = i;
            values[index] = (byte) ((IInventory) te).getField(i);
            index++;
        }
        sendTo(new MessageSetFieldClient(fields, values, te.getPos()), (EntityPlayerMP) pl);
    }

    public static void syncStringFieldClient(EntityPlayer pl, TileEntity te, int fieldID) {
        if (!(te instanceof ICustomField))
            return;

        String value = ((ICustomField) te).getStringField(fieldID);
        sendTo(new MessageSetFieldClient(fieldID, value, te.getPos()), (EntityPlayerMP) pl);
    }

    public static void sendToServer(IMessage msg) {
        INSTANCE.sendToServer(msg);
    }

    public static void writeBlockPos(ByteBuf to, BlockPos pos) {
        to.writeInt(pos.getX());
        to.writeInt(pos.getY());
        to.writeInt(pos.getZ());
    }

    public static BlockPos readBlockPos(ByteBuf from) {
        return new BlockPos(from.readInt(), from.readInt(), from.readInt());
    }
}
