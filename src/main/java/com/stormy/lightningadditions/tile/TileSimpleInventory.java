package com.stormy.lightningadditions.tile;

import java.util.Random;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;

public abstract class TileSimpleInventory extends LATile implements IInventory
{

    ItemStack[] inventorySlots = new ItemStack[getSizeInventory()];
    //TODO : Eventually Move to NonNull list and remove hacky constructor
    public TileSimpleInventory(){

        for(int i = 0; i < getSizeInventory(); i++)
            inventorySlots[i] = ItemStack.EMPTY;


    }

    public void readCustomNBT(NBTTagCompound par1NBTTagCompound)
    {
        NBTTagList var2 = par1NBTTagCompound.getTagList("Items", 10);
        inventorySlots = new ItemStack[getSizeInventory()];
        for (int var3 = 0; var3 < var2.tagCount(); ++var3) {
            NBTTagCompound var4 = var2.getCompoundTagAt(var3);
            byte var5 = var4.getByte("Slot");
            if (var5 >= 0 && var5 < inventorySlots.length)
                inventorySlots[var5] = new ItemStack(var4);
        }
    }


    public void writeCustomNBT(NBTTagCompound par1NBTTagCompound)
    {
        NBTTagList var2 = new NBTTagList();
        for (int var3 = 0; var3 < inventorySlots.length; ++var3)
        {
            if (inventorySlots[var3] != null) {
                NBTTagCompound var4 = new NBTTagCompound();
                var4.setByte("Slot", (byte)var3);
                inventorySlots[var3].writeToNBT(var4);
                var2.appendTag(var4);
            }
        }
        par1NBTTagCompound.setTag("Items", var2);
    }

    @Override
    public ItemStack getStackInSlot(int i)
    {
        return inventorySlots[i];
    }

    @Override
    public ItemStack decrStackSize(int i, int j)
    {
        if (inventorySlots[i] != null)
        {
            ItemStack stackAt;

            if (inventorySlots[i].getCount() <= j)
            {
                stackAt = inventorySlots[i];
                inventorySlots[i] = ItemStack.EMPTY;
                return stackAt;
            } else {
                stackAt = inventorySlots[i].splitStack(j);

                if (inventorySlots[i].getCount() == 0)
                    inventorySlots[i] = ItemStack.EMPTY;

                return stackAt;
            }
        }

        return null;
    }



    @Override
    public void setInventorySlotContents(int i, ItemStack itemstack)
    {

        if(itemstack != null)
            inventorySlots[i] = itemstack;
        else
            inventorySlots[i] = ItemStack.EMPTY;
    }

    @Override
    public int getInventoryStackLimit()
    {
        return 64;
    }

    @Override
    public boolean isItemValidForSlot(int i, ItemStack itemstack)
    {
        return true;
    }


    @Override
    public ITextComponent getDisplayName()
    {

        return null;
    }

    @Override
    public String getName()
    {

        return null;
    }

    @Override
    public boolean hasCustomName()
    {

        return false;
    }

    @Override
    public void openInventory(EntityPlayer player)
    {


    }

    @Override
    public void closeInventory(EntityPlayer player)
    {


    }

    @Override
    public int getField(int id)
    {

        return 0;
    }

    @Override
    public void setField(int id, int value)
    {


    }

    @Override
    public int getFieldCount()
    {

        return 0;
    }

    @Override
    public void clear()
    {
        for(int i =0; i<inventorySlots.length; i++)
        {

            inventorySlots[i] = ItemStack.EMPTY;

        }

    }

    @Override
    public ItemStack removeStackFromSlot(int index)
    {

        if(inventorySlots[index] == null || inventorySlots[index].isEmpty())
            return null;

        ItemStack is =  inventorySlots[index].copy();

        inventorySlots[index] = ItemStack.EMPTY;

        return is;
    }


    @Override
    public int getSizeInventory()
    {

        return 0;
    }

    @Override
    public boolean isEmpty()
    {

        for(int i = 0; i < getSizeInventory(); i++){

            if(!inventorySlots[i].isEmpty())
                return false;
        }

        return true;
    }

    @Override
    public boolean isUsableByPlayer(EntityPlayer player)
    {

        return false;
    }

    public static void breakBlock(World w, BlockPos pos, IBlockState state)
    {
        TileSimpleInventory te = (TileSimpleInventory)w.getTileEntity(pos);



        for(ItemStack is : te.inventorySlots)
        {

            if(is.isEmpty())
                continue;

            Random RANDOM = w.rand;

            int x = pos.getX();
            int y = pos.getY();
            int z = pos.getZ();

            float f = RANDOM.nextFloat() * 0.8F + 0.1F;
            float f1 = RANDOM.nextFloat() * 0.8F + 0.1F;
            float f2 = RANDOM.nextFloat() * 0.8F + 0.1F;


            EntityItem entityitem = new EntityItem(w, x + (double)f, y + (double)f1, z + (double)f2, is);
            float f3 = 0.05F;
            entityitem.motionX = RANDOM.nextGaussian() * 0.05000000074505806D;
            entityitem.motionY = RANDOM.nextGaussian() * 0.05000000074505806D + 0.20000000298023224D;
            entityitem.motionZ = RANDOM.nextGaussian() * 0.05000000074505806D;
            w.spawnEntity(entityitem);




        }


    }



}
