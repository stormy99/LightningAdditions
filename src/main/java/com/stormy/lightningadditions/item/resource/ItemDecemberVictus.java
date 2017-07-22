package com.stormy.lightningadditions.item.resource;

import com.stormy.lightningadditions.reference.KeyChecker;
import com.stormy.lightningadditions.reference.Translate;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.stats.AchievementList;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

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

                player.addStat(AchievementList.OVERPOWERED);
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
    public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par3List, boolean par4)
    { if (KeyChecker.isHoldingShift()) { par3List.add(TextFormatting.GOLD + Translate.toLocal("tooltip.item.lauren_december.line1"));
        par3List.add(TextFormatting.ITALIC + Translate.toLocal("tooltip.item.lauren_december.line2")); }
        else
        { par3List.add(Translate.toLocal("tooltip.item.hold") + " " + TextFormatting.AQUA + TextFormatting.ITALIC + Translate.toLocal("tooltip.item.shift")); }
    }



}
