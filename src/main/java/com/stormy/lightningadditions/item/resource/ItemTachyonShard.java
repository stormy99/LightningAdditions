package com.stormy.lightningadditions.item.resource;

import com.stormy.lightningadditions.init.ModItems;
import com.stormy.lightningadditions.item.base.ItemLA;
import com.stormy.lightningadditions.reference.Translate;
import com.sun.javafx.scene.control.behavior.TableRowBehavior;
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
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
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

                summonLightning(worldIn, x, y, z);

                List<EntityLiving> livingEntities = worldIn.getEntitiesWithinAABB(EntityLiving.class, new AxisAlignedBB(x - damageRange, y - damageRange, z - damageRange, x + damageRange, y + damageRange, z + damageRange));
                for (EntityLiving entityLiving : livingEntities) {
                    entityLiving.attackEntityFrom(DamageSource.LIGHTNING_BOLT, 4.0F);
                }

                if (!playerIn.isCreative()) playerIn.getItemStackFromSlot(EntityEquipmentSlot.CHEST).damageItem(10, playerIn);

            }else{
                missingEnhancer(playerIn);
            }
        }else{
            missingEnhancer(playerIn);
        }

        return new ActionResult<>(EnumActionResult.SUCCESS, stack);
    }

    private static void summonLightning(World world, double x, double y, double z){
        EntityLightningBolt lightningBolt = new EntityLightningBolt(world, x, y, z, true);
        if (!world.isRemote) world.addWeatherEffect(lightningBolt);
    }

    private static void missingEnhancer(EntityPlayer player){
        summonLightning(player.getEntityWorld(), player.posX, player.posY, player.posZ);
        player.attackEntityFrom(DamageSource.LIGHTNING_BOLT, 6.0F);
        if (!player.world.isRemote) player.sendMessage(new TextComponentString(TextFormatting.RED + Translate.toLocal("chat.tachyon_shard.missing_enhancer")));
    }

}