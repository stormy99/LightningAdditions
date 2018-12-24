/*
 * ********************************************************************************
 * Copyright (c) 2017 StormyMode, MiningMark48. All Rights Reserved!
 * This file is part of Lightning Additions (MC-Mod).
 *
 * This project cannot be copied and/or distributed without the express
 * permission of StormyMode, MiningMark48 (Developers)!
 * ********************************************************************************
 */

package com.stormy.lightningadditions.handler.ritual;

import com.stormy.lightningadditions.world.ritual.InsomniacRitual;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.fml.common.eventhandler.Event;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

public class EventHandlerRitualCommon
{
    @SubscribeEvent
    public void onLivingDeath(LivingDeathEvent event)
    {
        EntityLivingBase entity = event.getEntityLiving();
        World world = entity.getEntityWorld();
        if (world.isRemote) {
            return;
        }
        Entity damageSource = event.getSource().getImmediateSource();
        if (!isRealPlayer(damageSource)) {
            return;
        }
        EntityPlayer player = (EntityPlayer)event.getSource().getImmediateSource();

        BlockPos enchantingTablePos = findEnchantingTable(entity);
        if (enchantingTablePos == null) {
            return;
        }
        if (!InsomniacRitual.validate(world, enchantingTablePos, player)) {
            return;
        }
        InsomniacRitual.startRitual(world, enchantingTablePos.getX(), enchantingTablePos.getY(), enchantingTablePos.getZ());
    }

    @SubscribeEvent
    public void onRightClickBlock(PlayerInteractEvent.RightClickBlock event)
    {
        World world = event.getWorld();
        if (world.isRemote) {return;}
        if (!event.getItemStack().isEmpty()) {return;}
        if (world.getBlockState(event.getPos()).getBlock() != Blocks.ENCHANTING_TABLE) {return;}
        if (!isRealPlayer(event.getEntityPlayer())) {return;}
        if (!event.getEntityPlayer().isSneaking()) {return;}
        event.setUseBlock(Event.Result.DENY);
        event.setUseItem(Event.Result.DENY);
        event.setCanceled(true);

        BlockPos pos = event.getPos();

        List<String> messages = new ArrayList();
        boolean flag = true;
        if (InsomniacRitual.redstoneCirclePresent(world, pos.getX(), pos.getY(), pos.getZ()))
        {
            messages.add("- Altar has a redstone circle");
            if (InsomniacRitual.altarOnEarth(world, pos.getX(), pos.getY(), pos.getZ()))
            {messages.add("- Altar and Circle placed on dirt");}
            else
            {flag = false; messages.add("! Altar and Circle not placed on dirt");}
        }
        else
        {messages.add("! Altar does not have a redstone circle");flag = false;}
        if (InsomniacRitual.altarCanSeeMoon(world, pos.getX(), pos.getY(), pos.getZ()))
        {messages.add("- Altar can see the moon");}
        else
        {messages.add("! Altar cannot see the moon"); flag = false;}
        if (InsomniacRitual.naturalEarth(world, pos.getX(), pos.getY(), pos.getZ()))
        {messages.add("- Altar has sufficient natural earth");}
        else
        {messages.add("! Area lacks sufficient natural earth"); flag = false;}
        if (InsomniacRitual.altarInDarkness(world, pos.getX(), pos.getY(), pos.getZ()))
        {messages.add("- Altar is in darkness");}
        else
        {messages.add("! Altar must not be lit by outside sources"); flag = false;}
        int i = InsomniacRitual.checkTime(world.getWorldTime());
        if (i == -1)
        {messages.add("! Too early, sacrifice must be made at midnight");}
        else if (i == 1)
        {messages.add("! Too late, sacrifice must be made at midnight");}
        else
        {
            messages.add("- Time is right");
            if (flag) {messages.add("Perform the sacrifice");}
        }
    }

    private static boolean isRealPlayer(Entity entity)
    {if (!(entity instanceof EntityPlayer)) {return false;}
        return (!(entity instanceof EntityPlayerMP)) || (((EntityPlayerMP)entity).connection != null);}

    @Nullable
    private static BlockPos findEnchantingTable(EntityLivingBase entity)
    {World world = entity.getEntityWorld();

        int x = entity.getPosition().getX();
        int y = (int)entity.getEntityBoundingBox().minY;
        int z = entity.getPosition().getZ();

        for (int dx = -2; dx <= 2; dx++) {
            for (int dy = -2; dy <= 0; dy++) {
                for (int dz = -2; dz <= 2; dz++)
                {
                    BlockPos pos = new BlockPos(x + dx, y + dy, z + dz);
                    if (world.getBlockState(pos).getBlock() == Blocks.ENCHANTING_TABLE) {
                        return pos;
                    }}}} return null; }
}
