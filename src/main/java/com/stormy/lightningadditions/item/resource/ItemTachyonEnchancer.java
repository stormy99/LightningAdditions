package com.stormy.lightningadditions.item.resource;

import com.stormy.lightningadditions.LightningAdditions;
import com.stormy.lightningadditions.init.ModSounds;
import com.stormy.lightningadditions.reference.ModInformation;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemTachyonEnchancer extends ItemArmor{

    public static ItemArmor.ArmorMaterial tachyon = EnumHelper.addArmorMaterial("tachyon", ModInformation.MODID + ":" + "tachyon_armor", 100, new int[]{4, 7, 6, 3}, 10, ModSounds.tachyon_zoom, 10);

    boolean isSprinting = false;

    public ItemTachyonEnchancer() {
        super(tachyon, 0, EntityEquipmentSlot.CHEST);
    }

    @Override
    public void onArmorTick(World world, EntityPlayer player, ItemStack itemStack) {
        player.addPotionEffect(new PotionEffect(MobEffects.SPEED, 20, 3, true, false));
        player.addPotionEffect(new PotionEffect(MobEffects.SATURATION, 20, 0, true, false));

        if (player.isSprinting()){
            world.spawnParticle(EnumParticleTypes.CLOUD, player.posX, player.posY, player.posZ, 0.0D, 0.0D, 0.0D);
            world.spawnParticle(EnumParticleTypes.EXPLOSION_NORMAL, player.posX, player.posY, player.posZ, 0.0D, 0.0D, 0.0D);
            world.spawnParticle(EnumParticleTypes.FLAME, player.posX + 0.25, player.posY + 0.125, player.posZ, 0.0D, 0.0D, 0.0D);
            world.spawnParticle(EnumParticleTypes.FLAME, player.posX - 0.25, player.posY + 0.125, player.posZ, 0.0D, 0.0D, 0.0D);
        }

        if (player.isSprinting() && !isSprinting){
            player.playSound(ModSounds.tachyon_zoom, 2.0f, 1.0f);
            isSprinting = true;
        }else if (!player.isSprinting()){
            isSprinting = false;
        }

    }

    @Override
    @SideOnly(Side.CLIENT)
    public ModelBiped getArmorModel(EntityLivingBase entityLiving, ItemStack stack, EntityEquipmentSlot armorSlot, ModelBiped defaultModel) {

        if (stack != null) {
            if (stack.getItem() instanceof ItemArmor) {

                EntityEquipmentSlot type = ((ItemArmor) stack.getItem()).armorType;
                ModelBiped armorModel = null;
                switch (type) {
                    case CHEST:
                        armorModel = LightningAdditions.proxy.getArmorModel();
                        break;
                    default:
                        break;
                }

                armorModel.bipedBody.showModel = (armorSlot == EntityEquipmentSlot.CHEST) || (armorSlot == EntityEquipmentSlot.CHEST);

                armorModel.isSneak = defaultModel.isSneak;
                armorModel.isRiding = defaultModel.isRiding;
                armorModel.isChild = defaultModel.isChild;
                armorModel.rightArmPose = defaultModel.rightArmPose;
                armorModel.leftArmPose = defaultModel.leftArmPose;

                return armorModel;
            }
        }
        return null;
    }

}
