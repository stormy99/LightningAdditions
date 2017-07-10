/*
 * ********************************************************************************
 * Copyright (c) 2017 StormyMode, MiningMark48. All Rights Reserved!
 * This file is part of Lightning Additions (MC-Mod).
 *
 * This project cannot be copied and/or distributed without the express
 * permission of StormyMode, MiningMark48 (Developers)!
 * ********************************************************************************
 */

package com.stormy.lightningadditions.item.resource;

import cofh.api.block.IDismantleable;
import cofh.api.item.IToolHammer;
import com.stormy.lightningadditions.item.base.ItemLA;
import com.stormy.lightningadditions.utility.inventory.BlockHelper;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.network.NetHandlerPlayClient;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.network.play.client.CPacketPlayerTryUseItemOnBlock;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.fml.client.FMLClientHandler;
import net.minecraftforge.fml.common.eventhandler.Event;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.UUID;


public class ItemScrewdriver extends ItemLA implements IToolHammer
{

//UNI 0
    //TODO [Open Iron Door Code] - Universal Mode
    //TODO [Detonation of Objects/Circuitry] - Universal Mode
//TRAK 1
    //TODO [Player Tracking mode?] - Tracking Mode
    //TODO [Bed/Home Tracker?] - Tracking Mode

    public static class ServerHelper
    {   //Determination of ServerWorld or not
        private ServerHelper() {}
        public static boolean isServerWorld(World world) {return !world.isRemote;}
        public static void sendItemUsePacket(World world, BlockPos pos, EnumFacing hitSide, EnumHand hand, float hitX, float hitY, float hitZ)
        {if (isServerWorld(world)) {return;}
            NetHandlerPlayClient netClientHandler = (NetHandlerPlayClient) FMLClientHandler.instance().getClientPlayHandler();
            netClientHandler.sendPacket(new CPacketPlayerTryUseItemOnBlock(pos, hitSide, hand, hitX, hitY, hitZ));}
    }

    public ItemScrewdriver()
    {
        this.setMaxDamage(20000);
        this.setNoRepair();
        this.setMaxStackSize(1);
        this.setContainerItem(this);
        this.setHarvestLevel("sonic", 1);
    }

    @Override
    public boolean doesSneakBypassUse(ItemStack stack, IBlockAccess world, BlockPos pos, EntityPlayer player)
    {return true;}

    @SubscribeEvent
    public UUID onEvent(PlayerInteractEvent event, EntityPlayer player)
    {
        if(event.getEntityPlayer() instanceof EntityPlayer)
        {
            return player.getGameProfile().getId();
        }
        return null;
    }

    @Override
    public EnumActionResult onItemUseFirst(EntityPlayer player, World world, BlockPos pos, EnumFacing side, float hitX, float hitY, float hitZ, EnumHand hand) {

        IBlockState state = world.getBlockState(pos);
        Block block = state.getBlock();



        if (world.isAirBlock(pos)) {
            return EnumActionResult.PASS;
        }
        PlayerInteractEvent event = new PlayerInteractEvent.RightClickBlock(player, hand, pos, side, new Vec3d(hitX, hitY, hitZ));
        if (MinecraftForge.EVENT_BUS.post(event) || event.getResult() == Event.Result.DENY) {
            return EnumActionResult.PASS;
        }


        if (ServerHelper.isServerWorld(world) && player.isSneaking() && block instanceof IDismantleable && ((IDismantleable) block).canDismantle(world, pos, state, player))
        {   ((IDismantleable) block).dismantleBlock(world, pos, state, player, false); return EnumActionResult.SUCCESS;     }

        if (BlockHelper.canRotate(block)) {
            world.setBlockState(pos, BlockHelper.rotateVanillaBlock(world, state, pos), 3);
            player.swingArm(hand);
            return ServerHelper.isServerWorld(world) ? EnumActionResult.SUCCESS : EnumActionResult.PASS;
        } else if (!player.isSneaking() && block.rotateBlock(world, pos, side)) {
            player.swingArm(hand);
            return ServerHelper.isServerWorld(world) ? EnumActionResult.SUCCESS : EnumActionResult.PASS;
        }
        return EnumActionResult.PASS;
    }

    public boolean canSonic(EntityPlayer player, EnumHand hand, ItemStack sonic, RayTraceResult rayTrace)
    {ItemStack stack = player.getHeldItemMainhand(); return true;}

    public void sonicUsed(EntityPlayer player, EnumHand hand, ItemStack sonic, RayTraceResult rayTrace)
    {}

    @Override
    public boolean isUsable(ItemStack item, EntityLivingBase user, BlockPos pos) {return true;}
    @Override
    public boolean isUsable(ItemStack item, EntityLivingBase user, Entity entity) {return true;}
    @Override
    public void toolUsed(ItemStack item, EntityLivingBase user, BlockPos pos) {}
    @Override
    public void toolUsed(ItemStack item, EntityLivingBase user, Entity entity) {}

    @Override
    public boolean isFull3D()
    {return true;}



}
