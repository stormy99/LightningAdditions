package com.stormy.lightningadditions.utility.tubing;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public final class Stack
{
    public static ItemStack of(Block block)
    {
        return new ItemStack(block);
    }

    public static ItemStack of(Block block, int amount)
    {
        return new ItemStack(block, amount);
    }

    public static ItemStack of(Block block, int amount, int meta)
    {
        return new ItemStack(block, amount, meta);
    }

    public static ItemStack ofMeta(Block block, int meta)
    {
        return new ItemStack(block, 1, meta);
    }

    public static ItemStack of(Item item)
    {
        return new ItemStack(item);
    }

    public static ItemStack of(Item item, int amount)
    {
        return new ItemStack(item, amount);
    }

    public static ItemStack of(Item item, int amount, int meta)
    {
        return new ItemStack(item, amount, meta);
    }

    public static ItemStack ofMeta(Item item, int meta)
    {
        return new ItemStack(item, 1, meta);
    }

    public static boolean canCombine(ItemStack stack1, ItemStack stack2)
    {
        if (stack1.getItem() == stack2.getItem())
            if (stack1.getMetadata() == stack2.getMetadata())
                if (stack1.getCount() <= stack1.getMaxStackSize())
                    if (ItemStack.areItemStackTagsEqual(stack1, stack2))
                        return true;
        return false;
    }
}
