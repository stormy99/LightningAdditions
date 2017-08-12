package com.stormy.lightninglib.lib.block;

import net.minecraft.block.state.IBlockState;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import javax.annotation.Nullable;

/**
 * Originally forked from KitsuneAlex's NTL - with permission.
 */
public class TileEntityBase extends TileEntity
{
    @Override
    public void readFromNBT(NBTTagCompound tag)
    { super.readFromNBT(tag); }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound tag)
    { super.writeToNBT(tag);
        return tag; }

    @Override
    public NBTTagCompound getUpdateTag()
    { return this.writeToNBT(new NBTTagCompound()); }

    @Override
    public void onDataPacket(NetworkManager manager, SPacketUpdateTileEntity packet)
    { this.readFromNBT(packet.getNbtCompound()); }

    @Nullable
    @Override
    public SPacketUpdateTileEntity getUpdatePacket()
    { return new SPacketUpdateTileEntity(this.pos, 255, this.getUpdateTag()); }

    @Override
    public void markDirty(){
        super.markDirty();

        if(this.world != null){
            IBlockState state = getWorld().getBlockState(this.pos);

            if(state != null){
                state.getBlock().updateTick(this.world, this.pos, state, this.world.rand);
                this.world.notifyBlockUpdate(pos, state, state, 3);
            }
        }
    }
}
