package com.stormy.lightningadditions.item.resource;

import com.stormy.lightninglib.lib.utils.KeyChecker;
import com.stormy.lightninglib.lib.utils.TranslateUtils;
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

import java.util.List;

public class ItemJanuaryBlues extends ItemFood
{
    public ItemJanuaryBlues(int amount, float saturation, boolean isWolfFood) {
        super(amount, saturation, isWolfFood);
        this.setHasSubtypes(true);
    }

    @SideOnly(Side.CLIENT)
    public boolean hasEffect(ItemStack stack) { return true; }

    public EnumRarity getRarity(ItemStack stack) {
        return EnumRarity.EPIC;
    }

    protected void onFoodEaten(ItemStack stack, World worldIn, EntityPlayer player) {
        if (!worldIn.isRemote) {
            player.addPotionEffect(new PotionEffect(MobEffects.HASTE, 2400, Integer.MAX_VALUE));
            player.addPotionEffect(new PotionEffect(MobEffects.WEAKNESS, 2400, Integer.MAX_VALUE));
            player.addPotionEffect(new PotionEffect(MobEffects.INVISIBILITY, 1200, Integer.MAX_VALUE));
            player.addPotionEffect(new PotionEffect(MobEffects.REGENERATION, 1000, Integer.MAX_VALUE));
            player.addPotionEffect(new PotionEffect(MobEffects.SPEED, 3500, 2));
        }
    }

    //Custom Tooltip
    @Override
    public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par3List, boolean par4) {
        if (KeyChecker.isHoldingShift()) {
            par3List.add(TextFormatting.DARK_RED + TranslateUtils.toLocal("tooltip.item.lauren_january.line1"));
            par3List.add(TextFormatting.ITALIC + TranslateUtils.toLocal("tooltip.item.lauren_january.line2"));
        } else {
            par3List.add(TranslateUtils.toLocal("tooltip.item.hold") + " " + TextFormatting.AQUA + TextFormatting.ITALIC + TranslateUtils.toLocal("tooltip.item.shift"));
        }
    }

}
