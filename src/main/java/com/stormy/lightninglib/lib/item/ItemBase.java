package com.stormy.lightninglib.lib.item;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;

public class ItemBase extends Item
{
    public ItemBase() {}

    public static interface IHasClickToggle
    { public void toggle(EntityPlayer player, ItemStack held);
        public boolean isOn(ItemStack held); }

    private int subItemCount = 0;

    public void setSubItems(int count) {
        this.subItemCount = count;
        this.setHasSubtypes(true);
    }

    public int getSubItemCount() {
        return this.subItemCount;
    }

    @Override
    public void getSubItems(net.minecraft.item.Item itemIn, CreativeTabs tab, NonNullList<ItemStack> subItems) {
        if ( this.getSubItemCount() > 0 ) {
            for ( int i = 0; i < this.getSubItemCount(); i++ )
                subItems.add(new ItemStack(this, 1, i));
        }
        else {
            super.getSubItems(itemIn, tab, subItems);
        }
    }
}
