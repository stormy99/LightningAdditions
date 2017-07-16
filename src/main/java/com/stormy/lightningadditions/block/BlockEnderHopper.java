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

import com.google.common.base.Predicate;
import com.stormy.lightningadditions.block.container.BlockContainerLA;
import com.stormy.lightningadditions.tile.TileEnderHopper;
import com.stormy.lightningadditions.tile.TileSimpleInventory;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityHopper;
import net.minecraft.util.*;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;
import java.util.List;

@SuppressWarnings("deprecation")
public class BlockEnderHopper extends BlockContainerLA {

    public static final PropertyDirection FACING = PropertyDirection.create("facing", new Predicate<EnumFacing>()
    {
        public boolean apply(EnumFacing p_apply_1_)
        {
            return p_apply_1_ != EnumFacing.UP;
        }
    });

    public BlockEnderHopper() {
        super(Material.DRAGON_EGG, "ender_hopper");
        this.setHardness(2.0F);

    }

    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta) {

        return new TileEnderHopper();
    }

    @Override
    protected Class<? extends TileEntity> tile() {

        return TileEnderHopper.class;
    }



    @Override
    public void onBlockPlacedBy(World w, BlockPos pos, IBlockState state, EntityLivingBase placer,
                                ItemStack stack) {

        ((TileEnderHopper)w.getTileEntity(pos)).pName = placer.getName();
    }

    @Override
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos)
    {
        return FULL_BLOCK_AABB;
    }

    public IBlockState onBlockPlaced(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer)
    {
        EnumFacing enumfacing = facing.getOpposite();

        if (enumfacing == EnumFacing.UP)
        {
            enumfacing = EnumFacing.DOWN;
        }

        return this.getDefaultState().withProperty(FACING, enumfacing);
    }

    public boolean isFullyOpaque(IBlockState state)
    {
        return true;
    }



    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, ItemStack heldItem, EnumFacing side, float hitX, float hitY, float hitZ)
    {
        if (worldIn.isRemote)
        {
            return true;
        }
        else
        {
            TileEntity tileentity = worldIn.getTileEntity(pos);

            if (tileentity instanceof TileEntityHopper)
            {
                playerIn.displayGUIChest((TileEntityHopper)tileentity);
                playerIn.addStat(StatList.HOPPER_INSPECTED);
            }

            return true;
        }
    }

    public void breakBlock(World worldIn, BlockPos pos, IBlockState state)
    {
        TileSimpleInventory.breakBlock(worldIn, pos, state);

        super.breakBlock(worldIn, pos, state);
    }

    protected static final AxisAlignedBB BASE_AABB = new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.625D, 1.0D);
    protected static final AxisAlignedBB SOUTH_AABB = new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 1.0D, 0.125D);
    protected static final AxisAlignedBB NORTH_AABB = new AxisAlignedBB(0.0D, 0.0D, 0.875D, 1.0D, 1.0D, 1.0D);
    protected static final AxisAlignedBB WEST_AABB = new AxisAlignedBB(0.875D, 0.0D, 0.0D, 1.0D, 1.0D, 1.0D);
    protected static final AxisAlignedBB EAST_AABB = new AxisAlignedBB(0.0D, 0.0D, 0.0D, 0.125D, 1.0D, 1.0D);



    public void addCollisionBoxToList(IBlockState state, World worldIn, BlockPos pos, AxisAlignedBB entityBox, List<AxisAlignedBB> collidingBoxes, @Nullable Entity entityIn, boolean p_185477_7_)
    {
        addCollisionBoxToList(pos, entityBox, collidingBoxes, BASE_AABB);
        addCollisionBoxToList(pos, entityBox, collidingBoxes, EAST_AABB);
        addCollisionBoxToList(pos, entityBox, collidingBoxes, WEST_AABB);
        addCollisionBoxToList(pos, entityBox, collidingBoxes, SOUTH_AABB);
        addCollisionBoxToList(pos, entityBox, collidingBoxes, NORTH_AABB);
    }

    public IBlockState getStateForPlacement(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer)
    {
        EnumFacing enumfacing = facing.getOpposite();

        if (enumfacing == EnumFacing.UP)
        {
            enumfacing = EnumFacing.DOWN;
        }

        return this.getDefaultState().withProperty(FACING, enumfacing);
    }

    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
    {
        if (worldIn.isRemote)
        {
            return true;
        }
        else
        {
            TileEntity tileentity = worldIn.getTileEntity(pos);

            if (tileentity instanceof TileEntityHopper)
            {
                playerIn.displayGUIChest((TileEntityHopper)tileentity);
                playerIn.addStat(StatList.HOPPER_INSPECTED);
            }

            return true;
        }
    }

    public EnumBlockRenderType getRenderType(IBlockState state)
    {
        return EnumBlockRenderType.MODEL;
    }

    public boolean isFullCube(IBlockState state)
    {
        return false;
    }

    public boolean isOpaqueCube(IBlockState state)
    {
        return false;
    }

    @SideOnly(Side.CLIENT)
    public boolean shouldSideBeRendered(IBlockState blockState, IBlockAccess blockAccess, BlockPos pos, EnumFacing side)
    {
        return true;
    }

    public static EnumFacing getFacing(int meta)
    {
        return EnumFacing.getFront(meta & 7);
    }

    public static boolean isEnabled(int meta)
    {
        return (meta & 8) != 8;
    }

    public boolean hasComparatorInputOverride(IBlockState state)
    {
        return true;
    }

    public int getComparatorInputOverride(IBlockState blockState, World worldIn, BlockPos pos)
    {
        return Container.calcRedstone(worldIn.getTileEntity(pos));
    }

    @SideOnly(Side.CLIENT)
    public BlockRenderLayer getBlockLayer()
    {
        return BlockRenderLayer.CUTOUT_MIPPED;
    }

    public IBlockState getStateFromMeta(int meta)
    {
        return this.getDefaultState().withProperty(FACING, getFacing(meta));
    }

    public int getMetaFromState(IBlockState state)
    {
        int i = 0;
        i = i | ((EnumFacing)state.getValue(FACING)).getIndex();



        return i;
    }

    public IBlockState withRotation(IBlockState state, Rotation rot)
    {
        return state.withProperty(FACING, rot.rotate((EnumFacing)state.getValue(FACING)));
    }

    public IBlockState withMirror(IBlockState state, Mirror mirrorIn)
    {
        return state.withRotation(mirrorIn.toRotation((EnumFacing)state.getValue(FACING)));
    }

    protected BlockStateContainer createBlockState()
    {
        return new BlockStateContainer(this, new IProperty[] {FACING});
    }

}
