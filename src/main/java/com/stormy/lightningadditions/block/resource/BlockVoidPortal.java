/*
 * ********************************************************************************
 * Copyright (c) 2017 StormyMode, MiningMark48. All Rights Reserved!
 * This file is part of Lightning Additions (MC-Mod).
 *
 * This project cannot be copied and/or distributed without the express
 * permission of StormyMode, MiningMark48 (Developers)!
 * ********************************************************************************
 */

package com.stormy.lightningadditions.block.resource;

import com.stormy.lightningadditions.init.ModSounds;
import com.stormy.lightninglib.lib.utils.KeyChecker;
import com.stormy.lightninglib.lib.utils.TranslateUtils;
import com.stormy.lightningadditions.config.ConfigurationManagerLA;
import com.stormy.lightningadditions.world.dimvoid.VoidWorldTeleport;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.FMLCommonHandler;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Random;

public class BlockVoidPortal extends Block
{
    private static EntityPlayer playerIn;
    public BlockVoidPortal() {
        super(Material.PORTAL);
        setHardness(5.0F);
        setResistance(Integer.MAX_VALUE);
        Random random = new Random();
        int randNum = random.nextInt(10) + 1;
        setSoundType(new SoundType(0.85F, randNum, SoundEvents.BLOCK_STONE_BREAK, ModSounds.void_block, ModSounds.void_block, ModSounds.void_block, ModSounds.void_block));
    }

    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing side, float hitX, float hitY, float hitZ)
    {

        if (!worldIn.isRemote && !playerIn.isSneaking()) {
            playerIn.playSound(ModSounds.void_block, 1.0f, 1.0f);
            if (worldIn.provider.getDimension() != ConfigurationManagerLA.dimID)
            {
                FMLCommonHandler.instance().getMinecraftServerInstance().getPlayerList().transferPlayerToDimension((EntityPlayerMP) playerIn, ConfigurationManagerLA.dimID, new VoidWorldTeleport(playerIn.getServer().getWorld(ConfigurationManagerLA.dimID), pos));
            } else
                {
                FMLCommonHandler.instance().getMinecraftServerInstance().getPlayerList().transferPlayerToDimension((EntityPlayerMP) playerIn, 0, new VoidWorldTeleport(playerIn.getServer().getWorld(0), pos));
            }
            return true;
        }
        return super.onBlockActivated(worldIn, pos, state, playerIn, hand, side, hitX, hitY, hitZ);
    }

    @Override
    public float getBlockHardness(IBlockState blockState, World worldIn, BlockPos pos)
    {
        if(worldIn.provider.getDimension() == ConfigurationManagerLA.dimID)
        { return 1000F; }
        return super.getBlockHardness(blockState, worldIn, pos);
    }

    public boolean isFullCube(IBlockState state)
    { return true; }

    public boolean isOpaqueCube(IBlockState state)
    { return false; }

    //Custom Tooltip
    @Override
    public void addInformation(ItemStack par1ItemStack, @Nullable World world, List par3List, ITooltipFlag par4) {
        if (KeyChecker.isHoldingShift()) {
            par3List.add(TextFormatting.ITALIC + TranslateUtils.toLocal("tooltip.block.void_block.line1"));
        } else {
            par3List.add(TranslateUtils.toLocal("tooltip.item.hold") + " " + TextFormatting.AQUA + TextFormatting.ITALIC + TranslateUtils.toLocal("tooltip.item.shift"));
        }
    }

}
