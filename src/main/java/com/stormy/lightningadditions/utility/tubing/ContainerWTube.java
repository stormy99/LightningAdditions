package com.stormy.lightningadditions.utility.tubing;

import com.stormy.lightningadditions.tile.TileEntityTube;
import net.minecraft.entity.item.EntityMinecartChest;
import net.minecraft.entity.item.EntityMinecartHopper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryBasic;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;

import static com.stormy.lightningadditions.utility.tubing.InventoryUtils.getInventoryAtPosition;


public class ContainerWTube extends Container
{
    public ContainerWTube(TileEntityTube te)
    {
        addSlotToContainer(new Slot(te, 0, 79, 78)
        {
            @Override
            public boolean canTakeStack(EntityPlayer playerIn)
            {
                return false;
            }
        });

        addViewingSlotForSide(te, te.getInput(), 31, 78);
        addViewingSlotForSide(te, te.getOutput(), 127, 78);

    }

    private void addViewingSlotForSide(TileEntityTube te, EnumFacing side, int x, int y)
    {
        IInventory inv = getInventoryAtPosition(te.getWorld(), te.getXPos() + side.getFrontOffsetX(),
                te.getYPos() + side.getFrontOffsetY(), te.getZPos() + side.getFrontOffsetZ()
        );
        if (inv != null)
        {
            if (inv instanceof TileEntityTube)
            {
                addSlotToContainer(createViewingSlot(inv, 0, x, y));
            } else
            {
                IInventory dummy = new InventoryBasic("", false, 1);
                ItemStack stack = ItemStack.EMPTY;

                if (inv instanceof TileEntity)
                {
                    TileEntity te1 = te.getWorld()
                            .getTileEntity(new BlockPos(te.getXPos() + side.getFrontOffsetX(), te.getYPos()
                                    + side.getFrontOffsetY(), te.getZPos() + side.getFrontOffsetZ()));
                    if (te1 != null)
                    {
                        stack = new ItemStack(te1.getBlockType());
                    }
                } else if (inv instanceof EntityMinecartChest)
                {
                    stack = new ItemStack(Items.CHEST_MINECART);
                } else if (inv instanceof EntityMinecartHopper)
                {
                    stack = new ItemStack(Items.HOPPER_MINECART);
                } else if (inv instanceof InventoryPlayer)
                {
                    stack = new ItemStack(Items.SKULL, 1, 3);
                    stack.setStackDisplayName(inv.getDisplayName()
                            .getUnformattedText());
                }

                dummy.setInventorySlotContents(0, stack);
                addSlotToContainer(createViewingSlot(dummy, 0, x, y));
            }
        }
    }

    private Slot createViewingSlot(IInventory inv, int index, int x, int y)
    {
        return new Slot(inv, index, x, y)
        {
            @Override
            public boolean canTakeStack(EntityPlayer playerIn)
            {
                return false;
            }
        };
    }

    @Override
    public boolean canInteractWith(EntityPlayer playerIn)
    {
        return true;
    }
}
