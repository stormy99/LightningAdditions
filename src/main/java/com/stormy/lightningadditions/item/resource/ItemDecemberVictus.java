/*
 *
 *  * ********************************************************************************
 *  * Copyright (c) 2017 StormyMode, MiningMark48. All Rights Reserved!
 *  * This file is part of Lightning Additions (MC-Mod).
 *  *
 *  * This project cannot be copied and/or distributed without the express
 *  * permission of StormyMode, MiningMark48 (Developers)!
 *  * ********************************************************************************
 *
 */

package com.stormy.lightningadditions.item.resource;

import com.stormy.lightninglib.lib.utils.KeyChecker;
import com.stormy.lightninglib.lib.utils.TranslateUtils;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;
import java.util.List;

public class ItemDecemberVictus extends ItemFood
{
    public ItemDecemberVictus(int amount, float saturation, boolean isWolfFood)
    {
        super(amount, saturation, isWolfFood);
        this.setHasSubtypes(true); }

    @SideOnly(Side.CLIENT)
    public boolean hasEffect(ItemStack stack)
    { return true; }

    public EnumRarity getRarity(ItemStack stack)
    { return EnumRarity.EPIC; }

    protected void onFoodEaten(ItemStack stack, World worldIn, EntityPlayer player)
    {
        if (!worldIn.isRemote)
        {

                player.addPotionEffect(new PotionEffect(MobEffects.REGENERATION, 6000, Integer.MAX_VALUE));
                player.addPotionEffect(new PotionEffect(MobEffects.RESISTANCE, 6000, Integer.MAX_VALUE));
                player.addPotionEffect(new PotionEffect(MobEffects.FIRE_RESISTANCE, 6000, Integer.MAX_VALUE));
                player.addPotionEffect(new PotionEffect(MobEffects.ABSORPTION, 3500, Integer.MAX_VALUE));
                player.addPotionEffect(new PotionEffect(MobEffects.INSTANT_HEALTH, 1, Integer.MAX_VALUE));
                player.addPotionEffect(new PotionEffect(MobEffects.SATURATION, 3500, Integer.MAX_VALUE));
                player.addPotionEffect(new PotionEffect(MobEffects.NIGHT_VISION, 3500, Integer.MAX_VALUE));
                player.addPotionEffect(new PotionEffect(MobEffects.WATER_BREATHING, 3500, Integer.MAX_VALUE)); }

        }

    //Custom Tooltip
    @Override
    public void addInformation(ItemStack stack, @Nullable World playerIn, List<String> tooltip, ITooltipFlag advanced) {
        if (KeyChecker.isHoldingShift()) {
            tooltip.add(TextFormatting.GOLD + TranslateUtils.toLocal("tooltip.item.lauren_december.line1"));
            tooltip.add(TextFormatting.ITALIC + TranslateUtils.toLocal("tooltip.item.lauren_december.line2"));
        } else {
            tooltip.add(TranslateUtils.toLocal("tooltip.item.hold") + " " + TextFormatting.AQUA + TextFormatting.ITALIC + TranslateUtils.toLocal("tooltip.item.shift"));
        }
    }
}
