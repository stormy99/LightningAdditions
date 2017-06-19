package com.stormy.lightningadditions.item.resource;

import com.stormy.lightningadditions.init.ModItems;
import com.stormy.lightningadditions.item.base.ItemLA;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.Vector3d;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

import java.util.List;

public class ItemTachyonShard extends ItemLA{

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
        ItemStack stack = playerIn.getHeldItem(handIn);
        if (stack.getItem() == this && (playerIn.getItemStackFromSlot(EntityEquipmentSlot.CHEST) != ItemStack.EMPTY || playerIn.isCreative())) {
            if ((playerIn.getItemStackFromSlot(EntityEquipmentSlot.CHEST).getItem() == ModItems.tachyon_enhancer) || playerIn.isCreative()) {
                int distance = 64; //TODO: Make config
                int damageRange = 5;
                RayTraceResult rayTraceResult = Minecraft.getMinecraft().getRenderViewEntity().rayTrace(distance, 1.0F);
                int x = rayTraceResult.getBlockPos().getX();
                int y = rayTraceResult.getBlockPos().getY();
                int z = rayTraceResult.getBlockPos().getZ();

                EntityLightningBolt lightningBolt = new EntityLightningBolt(worldIn, x, y, z, true);
                if (!worldIn.isRemote) worldIn.addWeatherEffect(lightningBolt);

                List<EntityLiving> livingEntities = lightningBolt.world.getEntitiesWithinAABB(EntityLiving.class, new AxisAlignedBB(x - damageRange, y - damageRange, z - damageRange, x + damageRange, y + damageRange, z + damageRange));
                for (EntityLiving entityLiving : livingEntities) {
                    entityLiving.attackEntityFrom(DamageSource.LIGHTNING_BOLT, 2.0F);
                }

            }else{
                EntityLightningBolt lightningBolt = new EntityLightningBolt(worldIn, playerIn.posX, playerIn.posY, playerIn.posZ, true);
                if (!worldIn.isRemote) worldIn.addWeatherEffect(lightningBolt);
                playerIn.attackEntityFrom(DamageSource.LIGHTNING_BOLT, 2.0F);
            }
        }else{
            EntityLightningBolt lightningBolt = new EntityLightningBolt(worldIn, playerIn.posX, playerIn.posY, playerIn.posZ, true);
            if (!worldIn.isRemote) worldIn.addWeatherEffect(lightningBolt);
            playerIn.attackEntityFrom(DamageSource.LIGHTNING_BOLT, 2.0F);
        }

        return new ActionResult<>(EnumActionResult.SUCCESS, stack);
    }
}
