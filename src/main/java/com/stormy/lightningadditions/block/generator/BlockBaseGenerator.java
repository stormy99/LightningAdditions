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

package com.stormy.lightningadditions.block.generator;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;
import java.util.List;

public abstract class BlockBaseGenerator extends BlockContainer{

    public static final PropertyBool ACTIVE = PropertyBool.create("active");

    private AxisAlignedBB BOUNDING_BOX_ON = null;
    private AxisAlignedBB BOUNDING_BOX_OFF = null;

    public BlockBaseGenerator(Material mat, AxisAlignedBB bounding_box_active, AxisAlignedBB bounding_box_deactive) {
        super(mat);
        this.setDefaultState(this.blockState.getBaseState().withProperty(ACTIVE, true));
        this.BOUNDING_BOX_ON = bounding_box_active;
        this.BOUNDING_BOX_OFF = bounding_box_deactive;
    }

    public BlockBaseGenerator(Material mat, AxisAlignedBB bounding_box) {
        super(mat);
        this.setDefaultState(this.blockState.getBaseState().withProperty(ACTIVE, true));
        this.BOUNDING_BOX_ON = bounding_box;
        this.BOUNDING_BOX_OFF = bounding_box;
    }

    public BlockBaseGenerator(Material mat) {
        super(mat);
        this.setDefaultState(this.blockState.getBaseState().withProperty(ACTIVE, true));
        this.BOUNDING_BOX_ON = new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 1.0D, 1.0D);
        this.BOUNDING_BOX_OFF = new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 1.0D, 1.0D);
    }

    @Override
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos){
        if (state.getValue(ACTIVE)){
            return BOUNDING_BOX_ON;
        }
        return BOUNDING_BOX_OFF;
    }

    @Override
    public void addCollisionBoxToList(IBlockState state, World worldIn, BlockPos pos, AxisAlignedBB entityBox, List<AxisAlignedBB> collidingBoxes, @Nullable Entity entityIn, boolean p_185477_7_) {
        if (state.getValue(ACTIVE)) {
            super.addCollisionBoxToList(pos, entityBox, collidingBoxes, BOUNDING_BOX_ON);
        }
        super.addCollisionBoxToList(pos, entityBox, collidingBoxes, BOUNDING_BOX_ON);
    }

    @Override
    public void breakBlock(World worldIn, BlockPos pos, IBlockState state)
    {
        TileEntity tileentity = worldIn.getTileEntity(pos);

        if (tileentity instanceof IInventory)
        {
            InventoryHelper.dropInventoryItems(worldIn, pos, (IInventory)tileentity);
            worldIn.updateComparatorOutputLevel(pos, this);
        }

        super.breakBlock(worldIn, pos, state);
    }

    @Override
    public EnumBlockRenderType getRenderType(IBlockState state)
    {
        return EnumBlockRenderType.MODEL;
    }

    @SuppressWarnings("deprecation")
    public boolean isFullCube(IBlockState state)
    {
        return false;
    }

    @SuppressWarnings("deprecation")
    public boolean isOpaqueCube(IBlockState state)
    {
        return false;
    }

    @SideOnly(Side.CLIENT)
    @SuppressWarnings("deprecation")
    public boolean shouldSideBeRendered(IBlockState blockState, IBlockAccess blockAccess, BlockPos pos, EnumFacing side)
    {
        return true;
    }

    @Override
    @SuppressWarnings("deprecation")
    public IBlockState getStateForPlacement(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer) {
        return this.getDefaultState().withProperty(ACTIVE, true);
    }

    @Override
    public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack)
    {
        worldIn.setBlockState(pos, state.withProperty(ACTIVE, true), 2);
    }

    @Override
    @SuppressWarnings("deprecation")
    public IBlockState getStateFromMeta(int meta)
    {
        boolean powered = true;
        if (meta == 1)
        {
            powered = true;
        }

        return this.getDefaultState().withProperty(ACTIVE, powered);
    }

    @Override
    public int getMetaFromState(IBlockState state)
    {
        if (state.getValue(ACTIVE))
        {
            return 1;
        }

        return 0;
    }

    @Override
    protected BlockStateContainer createBlockState()
    {
        return new BlockStateContainer(this, new IProperty[] { ACTIVE });
    }

    @Override
    public void onBlockAdded(World worldIn, BlockPos pos, IBlockState state)
    {
        super.onBlockAdded(worldIn, pos, state);
        worldIn.setBlockState(pos, state.withProperty(ACTIVE, false));
    }

    public void setState(World worldIn, BlockPos pos, boolean isActive)
    {
        TileEntity tileentity = worldIn.getTileEntity(pos);

        worldIn.setBlockState(pos, this.getDefaultState().withProperty(ACTIVE, isActive), 3);
        worldIn.setBlockState(pos, this.getDefaultState().withProperty(ACTIVE, isActive), 3);

        if (tileentity != null)
        {
            tileentity.validate();
            worldIn.setTileEntity(pos, tileentity);
        }
    }

}
