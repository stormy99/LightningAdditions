package com.stormy.lightningadditions.item.resource.food;

import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemRawBacon extends ItemFood
{
    public ItemRawBacon(int amount, float saturation, boolean isWolfFood)
    {
        super(amount, saturation, isWolfFood);
        this.setHasSubtypes(true); }

    @SideOnly(Side.CLIENT)
    public boolean hasEffect(ItemStack stack)
    { return false; }
}
