/*
 *
 *  * ********************************************************************************
 *  * Copyright (c) 2017 StormyMode, MiningMark48. All Rights Reserved!
 *  * This file is part of Lightning Additions (MC-Mod).
 *  *
 *  * This project cannot be copied and/or distributed without the express
 *  * permission of StormyMode, MiningMark48 (Developers)!
 *  * ********************************************************************************
 *
 */

package com.stormy.lightningadditions.block.resource;

import com.stormy.lightningadditions.utility.logger.LALogger;
import com.stormy.lightninglib.lib.utils.KeyChecker;
import com.stormy.lightninglib.lib.utils.TranslateUtils;
import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Random;

public class BlockCropComparator extends Block{

    private static final PropertyDirection FACING = PropertyDirection.create("facing");
    private static final PropertyBool POWERED = PropertyBool.create("powered");

    public BlockCropComparator()
    {
        super(Material.ROCK);
        this.setDefaultState(this.blockState.getBaseState().withProperty(FACING, EnumFacing.NORTH).withProperty(POWERED, false));
        this.setHardness(1.5F);
        this.setTickRandomly(true);
    }

    @Override
    public int tickRate(World worldIn) {
        return 20;
    }

    @SuppressWarnings("deprecation")
    @Override
    public void neighborChanged(IBlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos changedPos)
    {
        doCropCompare(state, worldIn, pos);
    }

    @Override
    public void onBlockAdded(World worldIn, BlockPos pos, IBlockState state)
    {
        super.onBlockAdded(worldIn, pos, state);
        this.setDefaultFacing(worldIn, pos, state);
        worldIn.setBlockState(pos, state.withProperty(POWERED, worldIn.isBlockIndirectlyGettingPowered(pos) > 0));
        this.updateTick(worldIn, pos, state, worldIn.rand);
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

    @SuppressWarnings("deprecation")
    @Override
    public IBlockState getStateForPlacement(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer)
    {
        return this.getDefaultState().withProperty(FACING, EnumFacing.getDirectionFromEntityLiving(pos, placer));
    }

    @Override
    public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack)
    {
        worldIn.setBlockState(pos, state.withProperty(FACING, EnumFacing.getDirectionFromEntityLiving(pos, placer)), 2);
    }

    @SuppressWarnings("deprecation")
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
    public void addInformation(ItemStack par1ItemStack, @Nullable World world, List par3List, ITooltipFlag par4) {
        if (KeyChecker.isHoldingShift()) {
            par3List.add(TextFormatting.GREEN + TranslateUtils.toLocal("tooltip.block.crop_comparator.line1"));
        } else {
            par3List.add(TranslateUtils.toLocal("tooltip.item.hold") + " " + TextFormatting.AQUA + TextFormatting.ITALIC + TranslateUtils.toLocal("tooltip.item.shift"));
        }
    }

    @SuppressWarnings("deprecation")
    @Override
    public boolean canProvidePower(IBlockState state) {
        return true;
    }

    @SuppressWarnings("deprecation")
    @Override
    public int getWeakPower(IBlockState blockState, IBlockAccess blockAccess, BlockPos pos, EnumFacing side) {
        return blockState.getValue(FACING) == side ? (blockState.getValue(POWERED) ? 15 : 0) : 0;
    }

    @Override
    public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand) {
        worldIn.scheduleUpdate(pos, this, 2);
        doCropCompare(state, worldIn, pos);
    }

    private static void doCropCompare(IBlockState state, World worldIn, BlockPos pos){
        EnumFacing facing = state.getValue(FACING);
        if (!worldIn.isAirBlock(pos.offset(facing))) {
            if (worldIn.getBlockState(pos.offset(facing)).getBlock() instanceof BlockCrops) {
                IBlockState cropsState = worldIn.getBlockState(pos.offset(facing));
                BlockCrops crops = (BlockCrops) cropsState.getBlock();
                boolean isMaxAge = crops.isMaxAge(cropsState);
                if (isMaxAge) {
                    worldIn.setBlockState(pos, state.withProperty(POWERED, true));
                } else {
                    worldIn.setBlockState(pos, state.withProperty(POWERED, false));
                }
            }else if (worldIn.getBlockState(pos.offset(facing)).getBlock() instanceof BlockReed){
                final int maxReedHeight = 3;
                int j = 0;
                for (int i = 0; i <= maxReedHeight; i++){
                    if (worldIn.getBlockState(pos.offset(facing).up(i)).getBlock() instanceof BlockReed){
                        j++;
                    }
                }

                if (j >= maxReedHeight) {
                    worldIn.setBlockState(pos, state.withProperty(POWERED, true));
                } else {
                    worldIn.setBlockState(pos, state.withProperty(POWERED, false));
                }
            }else if (worldIn.getBlockState(pos.offset(facing)).getBlock() instanceof BlockStem){
                BlockPos stemPos = pos.offset(facing);
                if ((worldIn.getBlockState(stemPos.offset(EnumFacing.NORTH)).getBlock() instanceof BlockMelon) || (worldIn.getBlockState(stemPos.offset(EnumFacing.SOUTH)).getBlock() instanceof BlockMelon) || (worldIn.getBlockState(stemPos.offset(EnumFacing.EAST)).getBlock() instanceof BlockMelon) || (worldIn.getBlockState(stemPos.offset(EnumFacing.WEST)).getBlock() instanceof BlockMelon) || (worldIn.getBlockState(stemPos.offset(EnumFacing.NORTH)).getBlock() instanceof BlockPumpkin) || (worldIn.getBlockState(stemPos.offset(EnumFacing.SOUTH)).getBlock() instanceof BlockPumpkin) || (worldIn.getBlockState(stemPos.offset(EnumFacing.EAST)).getBlock() instanceof BlockPumpkin) || (worldIn.getBlockState(stemPos.offset(EnumFacing.WEST)).getBlock() instanceof BlockPumpkin)){
                    worldIn.setBlockState(pos, state.withProperty(POWERED, true));
                }else{
                    worldIn.setBlockState(pos, state.withProperty(POWERED, false));
                }
            }
        }
    }

}
