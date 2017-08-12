package com.stormy.lightninglib.lib.item;

import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemFood;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;

public class ItemBaseFood extends ItemFood
{
    private String modid;
    private String itemName;
    @SideOnly(Side.CLIENT)
    private TextureAtlasSprite texture;

    public ItemBaseFood(String itemName, int amount, boolean isWolfFood) {
        super(amount, isWolfFood);
        this.itemName = itemName;
        this.setCreativeTab(this.getCreativeTab()); }

    public ItemBaseFood(String itemName, int amount, float saturation, boolean isWolfFood) {
        super(amount, saturation, isWolfFood);
        this.itemName = itemName;
        this.setCreativeTab(this.getCreativeTab()); }

    @Nullable
    @Override
    public CreativeTabs getCreativeTab() { return CreativeTabs.FOOD; }

}
