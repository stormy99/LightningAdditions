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

import com.stormy.lightningadditions.config.ConfigurationManagerLA;
import com.stormy.lightninglib.lib.utils.KeyChecker;
import com.stormy.lightninglib.lib.utils.TranslateUtils;
import de.kitsunealex.frame.item.ItemBase;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Random;

public class ItemAtomicInhibitor extends ItemBase
{

    private Random random = new Random();

    public ItemAtomicInhibitor(){
        setMaxStackSize(1);
    }

    @Override
    public void addInformation(ItemStack par1ItemStack, @Nullable World world, List par3List, ITooltipFlag par4) {
        if (KeyChecker.isHoldingShift()) { par3List.add(TranslateUtils.toLocal("tooltip.item.atomic_inhibitor.line1")); }
        else{ par3List.add(TranslateUtils.toLocal("tooltip.item.hold") + " " + TextFormatting.AQUA + TextFormatting.ITALIC + TranslateUtils.toLocal("tooltip.item.shift")); }
    }

    @Override
    public EnumActionResult onItemUse(EntityPlayer player, World world, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
    {

        ItemStack stack = player.getHeldItem(hand);

        if(!world.isRemote){ IBlockState state = world.getBlockState(pos); Block block = state.getBlock(); TileEntity te = world.getTileEntity(pos);
            for (int i = 0; i < (ConfigurationManagerLA.tickAmount) / (te == null ? 5 : 1); i++) {
                if (te == null)
                {
                    block.updateTick(world, pos, state, this.random);
                }

                else if ((te instanceof ITickable))
                {
                    ((ITickable)te).update();
                }
            }

            if( te instanceof ITickable)
            {
                ((ITickable)te).update();
            }
            stack.damageItem(1, player);
        }
        return EnumActionResult.SUCCESS; }

}
