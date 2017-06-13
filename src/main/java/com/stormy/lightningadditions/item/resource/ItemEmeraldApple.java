package com.stormy.lightningadditions.item.resource;


import com.stormy.lightningadditions.reference.KeyChecker;
import com.stormy.lightningadditions.reference.Translate;
import com.stormy.lightningadditions.utility.UtilChat;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityZombieVillager;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.EnumHand;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.village.MerchantRecipe;
import net.minecraftforge.event.entity.player.PlayerInteractEvent.EntityInteract;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.List;

public class ItemEmeraldApple extends Item {
    private static final int CONVTIME = 1200;

    public ItemEmeraldApple() {
        setMaxStackSize(16);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public EnumRarity getRarity(ItemStack par1ItemStack) {
        return EnumRarity.RARE;
    }

    private void startConverting(EntityZombieVillager v, int t) {
        //      v.conversionTime = t;
        ObfuscationReflectionHelper.setPrivateValue(EntityZombieVillager.class, v, t, "conversionTime", "field_82234_d");
        v.addPotionEffect(new PotionEffect(MobEffects.STRENGTH, t, Math.min(v.world.getDifficulty().getDifficultyId() - 1, 0)));
        v.world.setEntityState(v, (byte) 16);
        try {
            //       v.getDataManager().set(CONVERTING, Boolean.valueOf(true));
            DataParameter<Boolean> CONVERTING = ObfuscationReflectionHelper.getPrivateValue(EntityZombieVillager.class, v, "CONVERTING", "field_184739_bx");
            v.getDataManager().set(CONVERTING, Boolean.valueOf(true));
        }
        catch (Exception e) {}
    }
    @SubscribeEvent
    public void onEntityInteractEvent(EntityInteract event) {
        if (event.getEntity() instanceof EntityPlayer == false) { return; }
        EntityPlayer player = (EntityPlayer) event.getEntity();
        //    ItemStack held = player.getHeldItemMainhand();
        ItemStack itemstack = event.getItemStack();
        if (itemstack != null && itemstack.getItem() instanceof ItemEmeraldApple && itemstack.getCount() > 0) {
            if (event.getTarget() instanceof EntityVillager) {
                EntityVillager villager = ((EntityVillager) event.getTarget());
                int count = 0;
                for (MerchantRecipe merchantrecipe : villager.getRecipes(player)) {
                    if (merchantrecipe.isRecipeDisabled()) {
                        //vanilla code as of 1.9.4 odes this (2d6+2)
                        merchantrecipe.increaseMaxTradeUses(villager.getEntityWorld().rand.nextInt(6) + villager.getEntityWorld().rand.nextInt(6) + 2);
                        count++;
                    }
                }
                if (count > 0) {
                    UtilChat.addChatMessage(player, UtilChat.lang("item.apple_emerald.merchant") + count);
                    itemstack.shrink(1);
                    if (itemstack.getCount() == 0) {
                        itemstack = ItemStack.EMPTY;
                    }
                }
                event.setCanceled(true);// stop the GUI inventory opening && horse mounting
            }
        }
    }
    @Override
    public boolean itemInteractionForEntity(ItemStack itemstack, EntityPlayer player, EntityLivingBase entity, EnumHand hand) {
        if (entity instanceof EntityZombieVillager) {
            EntityZombieVillager zombie = ((EntityZombieVillager) entity);
            //this is what we WANT to do, but the method is protected. we have to fake it by faking the interact event
            //      zombie.startConverting(1200);
            startConverting(zombie, CONVTIME);
            return true;
        }
        return super.itemInteractionForEntity(itemstack, player, entity, hand);
    }

    @Override
    public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par3List, boolean par4) {
        if (KeyChecker.isHoldingShift())
        { par3List.add(TextFormatting.GREEN + Translate.toLocal("tooltip.item.emerald_apple.line1"));
        }
        else{ par3List.add(Translate.toLocal("tooltip.item.hold") + " " + TextFormatting.AQUA + TextFormatting.ITALIC + Translate.toLocal("tooltip.item.shift")); }
    }

}
