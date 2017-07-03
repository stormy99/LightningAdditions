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

import com.stormy.lightningadditions.LightningAdditions;
import com.stormy.lightningadditions.client.gui.GuiHandler;
import com.stormy.lightningadditions.tile.generator.TileEntitySolarGenerator;
import com.stormy.lightningadditions.utility.logger.LALogger;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;
import java.util.List;

public class BlockSolarGenerator extends BlockContainer{

    public static final PropertyBool ACTIVE = PropertyBool.create("active");

    private static final AxisAlignedBB BOUNDING_BOX_ON = new AxisAlignedBB(0.0625D, 0.0D, 0.0625D, 0.9375D, 0.75D, 0.9375D);
    private static final AxisAlignedBB BOUNDING_BOX_OFF = new AxisAlignedBB(0.0625D, 0.0D, 0.0625D, 0.9375D, 0.375D, 0.9375D);

    public BlockSolarGenerator() {
        super(Material.ROCK);
        setHardness(1.0f);
        setResistance(0.5f);
        this.setDefaultState(this.blockState.getBaseState().withProperty(ACTIVE, true));
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
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing side, float hitX, float hitY, float hitZ) {
        if (!worldIn.isRemote){
            if (playerIn != null) {
                playerIn.openGui(LightningAdditions.INSTANCE, GuiHandler.gui_id_solar_generator, worldIn, pos.getX(), pos.getY(), pos.getZ());
            }
        }
        return true;
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

    @Nullable
    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta) {
        return new TileEntitySolarGenerator();
    }

    public EnumBlockRenderType getRenderType(IBlockState state)
    {
        return EnumBlockRenderType.MODEL;
    }

    @SuppressWarnings("deprecation")
    public boolean isFullCube(IBlockState state)
    {
        return true;
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
