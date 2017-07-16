/*
 * ********************************************************************************
 * Copyright (c) 2017 StormyMode, MiningMark48. All Rights Reserved!
 * This file is part of Lightning Additions (MC-Mod).
 *
 * This project cannot be copied and/or distributed without the express
 * permission of StormyMode, MiningMark48 (Developers)!
 * ********************************************************************************
 */

package com.stormy.lightningadditions.block;

import com.stormy.lightningadditions.reference.KeyChecker;
import com.stormy.lightningadditions.reference.Translate;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

import java.util.List;
import java.util.Random;

@SuppressWarnings("deprecation")
public class BlockIgniter extends Block
{
    public static final PropertyDirection FACING = PropertyDirection.create("facing");
    public static final PropertyBool POWERED = PropertyBool.create("powered");

    public BlockIgniter()
    {
        super(Material.ROCK);
        this.setDefaultState(this.blockState.getBaseState().withProperty(FACING, EnumFacing.NORTH).withProperty(POWERED, false));
        this.setHardness(1.5F);
    }

    @Override
    public boolean isFireSource(World world, BlockPos pos, EnumFacing side)
    {
        EnumFacing facing = world.getBlockState(pos).getValue(FACING);
        return side == facing; }

    @Override
    public void neighborChanged(IBlockState state, World worldIn, BlockPos pos, Block blockIn,BlockPos changedPos)
    { EnumFacing facing = state.getValue(FACING);
        boolean powered = state.getValue(POWERED);
        boolean newPowered = worldIn.isBlockIndirectlyGettingPowered(pos) > 0;
        worldIn.setBlockState(pos, state.withProperty(POWERED, newPowered));
        if (powered && !newPowered)
        {
            IBlockState front = worldIn.getBlockState(pos.offset(facing));
            IBlockState frontUp = worldIn.getBlockState(pos.offset(facing).up(1));

            if (front.getBlock() == Blocks.FIRE) {
                worldIn.setBlockToAir(pos.offset(facing));
                worldIn.playSound(null, pos, SoundEvents.ITEM_FLINTANDSTEEL_USE, SoundCategory.BLOCKS, 1.0F, new Random().nextFloat() * -0.4F - 0.8F);
            } else if (frontUp.getBlock() == Blocks.FIRE){
                worldIn.setBlockToAir(pos.offset(facing).up(1));
                worldIn.playSound(null, pos, SoundEvents.ITEM_FLINTANDSTEEL_USE, SoundCategory.BLOCKS, 1.0F, new Random().nextFloat() * -0.4F - 0.8F);
            }
        } else if (newPowered && !powered)
        {
            if (worldIn.isAirBlock(pos.offset(facing))) {
                worldIn.playSound(null, pos, SoundEvents.ITEM_FLINTANDSTEEL_USE, SoundCategory.BLOCKS, 1.0F, new Random().nextFloat() * 0.4F + 0.8F);
                worldIn.setBlockState(pos.offset(facing), Blocks.FIRE.getDefaultState());
            } else if (worldIn.isAirBlock(pos.offset(facing).up(1))){
                worldIn.playSound(null, pos, SoundEvents.ITEM_FLINTANDSTEEL_USE, SoundCategory.BLOCKS, 1.0F, new Random().nextFloat() * 0.4F + 0.8F);
                worldIn.setBlockState(pos.offset(facing).up(1), Blocks.FIRE.getDefaultState());
            }
        }
        else
        { return;
        }
    }



    @Override
    public void onBlockAdded(World worldIn, BlockPos pos, IBlockState state)
    { super.onBlockAdded(worldIn, pos, state);
        this.setDefaultFacing(worldIn, pos, state);
        worldIn.setBlockState(pos, state.withProperty(POWERED, worldIn.isBlockIndirectlyGettingPowered(pos) > 0 ? true : false));
    }

    private void setDefaultFacing(World worldIn, BlockPos pos, IBlockState state)
    {
        if (!worldIn.isRemote)
        { IBlockState iblockstate = worldIn.getBlockState(pos.north()); IBlockState iblockstate1 = worldIn.getBlockState(pos.south());
        IBlockState iblockstate2 = worldIn.getBlockState(pos.west()); IBlockState iblockstate3 = worldIn.getBlockState(pos.east());
            EnumFacing enumfacing = state.getValue(FACING);
            if (enumfacing == EnumFacing.NORTH && iblockstate.isFullBlock() && !iblockstate1.isFullBlock())
            {
                enumfacing = EnumFacing.SOUTH;
            } else if (enumfacing == EnumFacing.SOUTH && iblockstate1.isFullBlock() && !iblockstate.isFullBlock())
            {
                enumfacing = EnumFacing.NORTH;
            } else if (enumfacing == EnumFacing.WEST && iblockstate2.isFullBlock() && !iblockstate3.isFullBlock())
            {
                enumfacing = EnumFacing.EAST;
            } else if (enumfacing == EnumFacing.EAST && iblockstate3.isFullBlock() && !iblockstate2.isFullBlock())
            {
                enumfacing = EnumFacing.WEST;
            }

            worldIn.setBlockState(pos, state.withProperty(FACING, enumfacing), 2);
        }
    }

    @Override
    public IBlockState getStateForPlacement(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer)
    { return this.getDefaultState().withProperty(FACING, EnumFacing.getDirectionFromEntityLiving(pos, placer));
    }

    @Override
    public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack)
    {
        worldIn.setBlockState(pos, state.withProperty(FACING, EnumFacing.getDirectionFromEntityLiving(pos, placer)), 2);
    }

    @Override
    public IBlockState getStateFromMeta(int meta)
    {
        boolean powered = false;
        if (meta >= 6)
        {
            meta -= 6;
            powered = true;
        }

        return this.getDefaultState().withProperty(FACING, EnumFacing.values()[meta]).withProperty(POWERED, powered);
    }

    @Override
    public int getMetaFromState(IBlockState state)
    {
        int i = state.getValue(FACING).getIndex();

        if (state.getValue(POWERED))
        {
            i += 6;
        }

        return i;
    }

    @Override
    protected BlockStateContainer createBlockState()
    {
        return new BlockStateContainer(this, new IProperty[] { FACING, POWERED });
    }

    //Custom Tooltip
    @Override
    public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par3List, boolean par4) {
        if (KeyChecker.isHoldingShift()) {
            par3List.add(TextFormatting.LIGHT_PURPLE + Translate.toLocal("tooltip.block.igniter.line1"));
        } else {
            par3List.add(Translate.toLocal("tooltip.item.hold") + " " + TextFormatting.AQUA + TextFormatting.ITALIC + Translate.toLocal("tooltip.item.shift"));
        }
    }
}
