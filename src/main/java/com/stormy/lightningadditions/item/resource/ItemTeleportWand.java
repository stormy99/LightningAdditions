package com.stormy.lightningadditions.item.resource;

import com.stormy.lightningadditions.item.base.ItemLA;
import com.stormy.lightninglib.lib.utils.KeyChecker;
import com.stormy.lightninglib.lib.utils.TranslateUtils;
import com.stormy.lightningadditions.config.ConfigurationManagerLA;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

import java.util.List;

public class ItemTeleportWand extends ItemLA
{
    public ItemTeleportWand()
    {
        setMaxStackSize(1);
        setMaxDamage(20000);
        setNoRepair(); }

        //Custom Tooltip
    @Override
    public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par3List, boolean par4) {
        if (KeyChecker.isHoldingShift())
        { par3List.add(TextFormatting.LIGHT_PURPLE + TranslateUtils.toLocal("tooltip.item.teleport_wand.line1"));
        }
        else{ par3List.add(TranslateUtils.toLocal("tooltip.item.hold") + " " + TextFormatting.AQUA + TextFormatting.ITALIC + TranslateUtils.toLocal("tooltip.item.shift")); }
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand hand)
    {

        boolean clearFallDamage = ConfigurationManagerLA.canTeleportResetFallDamage;
        boolean teleportAir = ConfigurationManagerLA.canTeleportToAir;
        boolean teleportStuck = ConfigurationManagerLA.canTeleportDangerously;
        double distance = ConfigurationManagerLA.teleportDistance;

        RayTraceResult tracedBlock = player.rayTrace(distance, 1); //Ray trace from the player hits a block
        if(tracedBlock.typeOfHit != RayTraceResult.Type.ENTITY) {
            BlockPos blockAbove = new BlockPos(tracedBlock.getBlockPos().getX(), tracedBlock.getBlockPos().getY() + 1, tracedBlock.getBlockPos().getZ());
            BlockPos blockTwoAbove = new BlockPos(tracedBlock.getBlockPos().getX(), tracedBlock.getBlockPos().getY() + 2, tracedBlock.getBlockPos().getZ());

            double endPosX = tracedBlock.getBlockPos().getX() + .5;
            double endPosY = tracedBlock.getBlockPos().getY() + 1;
            double endPosZ = tracedBlock.getBlockPos().getZ() + .5;
            boolean endPosAir = world.isAirBlock(tracedBlock.getBlockPos());
            boolean endPosStuck = !world.isAirBlock(blockAbove) || !world.isAirBlock(blockTwoAbove);

            if(!endPosAir && !endPosStuck)
            { if(clearFallDamage)
            { player.fallDistance = 0; }

                if(world.isRemote)
                { for(int i = 0; i < 10; i++)
                { world.spawnParticle(EnumParticleTypes.CRIT_MAGIC, player.posX + (world.rand.nextDouble() - .5) * player.width, player.posY + world.rand.nextDouble() * player.height - .25, player.posZ + (world.rand.nextDouble() - .5) * player.width, (world.rand.nextDouble() - .5) * 2, -world.rand.nextDouble(), (world.rand.nextDouble() - .5) * 2); }  }

                player.playSound(SoundEvents.ENTITY_ENDERMEN_TELEPORT, 1, 1);
                player.setPosition(endPosX, endPosY, endPosZ);
                player.getCooldownTracker().setCooldown(this, 5);
                player.playSound(SoundEvents.ENTITY_ENDERMEN_TELEPORT, 1, 1);

                return ActionResult.newResult(EnumActionResult.SUCCESS, player.getHeldItem(hand));
            } else if(endPosAir && !endPosStuck)
            {
                if(teleportAir) {
                    if(clearFallDamage) {
                        player.fallDistance = 0;
                    }

                    if(world.isRemote) {
                        for(int i = 0; i < 10; i++) {
                            world.spawnParticle(EnumParticleTypes.CRIT_MAGIC, player.posX + (world.rand.nextDouble() - .5) * player.width, player.posY + world.rand.nextDouble() * player.height - .25, player.posZ + (world.rand.nextDouble() - .5) * player.width, (world.rand.nextDouble() - .5) * 2, -world.rand.nextDouble(), (world.rand.nextDouble() - .5) * 2);
                        }
                    }

                    player.playSound(SoundEvents.ENTITY_ENDERMEN_TELEPORT, 1, 1);
                    player.setPosition(endPosX, endPosY, endPosZ);
                    player.getCooldownTracker().setCooldown(this, 5);
                    player.playSound(SoundEvents.ENTITY_ENDERMEN_TELEPORT, 1, 1);

                    return ActionResult.newResult(EnumActionResult.SUCCESS, player.getHeldItem(hand));
                } else {
                    return ActionResult.newResult(EnumActionResult.PASS, player.getHeldItem(hand));
                }
            } else if(!endPosAir && endPosStuck)
            {
                if(teleportStuck) {
                    if(clearFallDamage) {
                        player.fallDistance = 0;
                    }

                    if(world.isRemote) {
                        for(int i = 0; i < 10; i++) {
                            world.spawnParticle(EnumParticleTypes.CRIT_MAGIC, player.posX + (world.rand.nextDouble() - .5) * player.width, player.posY + world.rand.nextDouble() * player.height - .25, player.posZ + (world.rand.nextDouble() - .5) * player.width, (world.rand.nextDouble() - .5) * 2, -world.rand.nextDouble(), (world.rand.nextDouble() - .5) * 2);
                        }
                    }
                    player.playSound(SoundEvents.ENTITY_ENDERMEN_TELEPORT, 1, 1);
                    player.setPosition(endPosX, endPosY, endPosZ);
                    player.getCooldownTracker().setCooldown(this, 5);
                    player.playSound(SoundEvents.ENTITY_ENDERMEN_TELEPORT, 1, 1);

                    return ActionResult.newResult(EnumActionResult.SUCCESS, player.getHeldItem(hand));
                } else {
                    return ActionResult.newResult(EnumActionResult.PASS, player.getHeldItem(hand));
                }
            } else if(endPosAir && endPosStuck) {
                if(teleportAir) {
                    if(teleportStuck) {
                        if(clearFallDamage) {
                            player.fallDistance = 0;
                        }

                        if(world.isRemote) {
                            for(int i = 0; i < 10; i++) {
                                world.spawnParticle(EnumParticleTypes.CRIT_MAGIC, player.posX + (world.rand.nextDouble() - .5) * player.width, player.posY + world.rand.nextDouble() * player.height - .25, player.posZ + (world.rand.nextDouble() - .5) * player.width, (world.rand.nextDouble() - .5) * 2, -world.rand.nextDouble(), (world.rand.nextDouble() - .5) * 2);
                            }
                        }

                        player.playSound(SoundEvents.ENTITY_ENDERMEN_TELEPORT, 1, 1);
                        player.setPosition(endPosX, endPosY, endPosZ);
                        player.getCooldownTracker().setCooldown(this, 5);
                        player.playSound(SoundEvents.ENTITY_ENDERMEN_TELEPORT, 1, 1);

                        return ActionResult.newResult(EnumActionResult.SUCCESS, player.getHeldItem(hand));
                    } else {
                        return ActionResult.newResult(EnumActionResult.PASS, player.getHeldItem(hand));
                    }
                } else {
                    return ActionResult.newResult(EnumActionResult.PASS, player.getHeldItem(hand));
                }
            } else {
                return ActionResult.newResult(EnumActionResult.FAIL, player.getHeldItem(hand));
            }
        } else {
            return ActionResult.newResult(EnumActionResult.PASS, player.getHeldItem(hand));
        }
    }
}
