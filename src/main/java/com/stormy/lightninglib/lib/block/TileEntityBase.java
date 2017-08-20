package com.stormy.lightninglib.lib.block;

import net.minecraft.block.state.IBlockState;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.NonNullList;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;

import javax.annotation.Nullable;

/**
 * Originally forked from KitsuneAlex's NTL - with permission.
 */
public class TileEntityBase extends TileEntity implements IInventory
{
    private NonNullList<ItemStack> items;
    protected int sizeInv;

    public TileEntityBase(int invSize) {
        this.sizeInv = invSize;
        items = NonNullList.withSize(invSize, ItemStack.EMPTY); }



    @Override
    public void readFromNBT(NBTTagCompound tag)
    { super.readFromNBT(tag);
        ItemStackHelper.loadAllItems(tag, this.items); }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound tag)
    { super.writeToNBT(tag);
        ItemStackHelper.saveAllItems(tag, this.items);
        return tag; }

    @Override
    public int getSizeInventory() {
        return sizeInv; }

    @Override
    public boolean isEmpty() {
        for (int i = 0; i < sizeInv; i++) {
            if (getStackInSlot(i).isEmpty())
                return true; }
        return false; }

    @Override
    public ItemStack getStackInSlot(int index) {
        if (index < getSizeInventory())
            return items.get(index);
        return null;
    }

    @Override
    public ItemStack decrStackSize(int index, int count) {
        ItemStack itemstack = ItemStackHelper.getAndSplit(items, index, count);
        if (itemstack != null)
            this.markDirty();
        return itemstack; }

    @Override
    public ItemStack removeStackFromSlot(int index) {
        return ItemStackHelper.getAndRemove(this.items, index); }

    @Override
    public void setInventorySlotContents(int index, ItemStack stack) {
        items.set(index, stack); }

    @Override
    public int getInventoryStackLimit() { return 64; }

    @Override
    public boolean isUsableByPlayer(EntityPlayer player) { return false; }

    @Override
    public void openInventory(EntityPlayer player) {}

    @Override
    public void closeInventory(EntityPlayer player) {}

    @Override
    public boolean isItemValidForSlot(int index, ItemStack stack) {
        return true; }

    @Override
    public int getField(int id) {
        return 0; }

    @Override
    public void setField(int id, int value) {}

    @Override
    public int getFieldCount() { return 0; }

    @Override
    public void clear() {}

    @Override
    public String getName() { return null; }

    @Override
    public boolean hasCustomName() {
        return false;
    }

    @Override
    public ITextComponent getDisplayName() {
        return new TextComponentString(I18n.format(getName()));
    }



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
