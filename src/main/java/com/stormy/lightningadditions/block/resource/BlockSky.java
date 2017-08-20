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

import com.stormy.lightninglib.lib.utils.KeyChecker;
import com.stormy.lightninglib.lib.utils.TranslateUtils;
import com.stormy.lightningadditions.tile.resource.TileEntitySky;
import com.stormy.lightningadditions.utility.UtilRender;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.List;
import java.util.Random;

public class BlockSky extends Block
{
    public static final PropertyBool POWERED = PropertyBool.create("powered");
    private static final int MASK_POWERED = 1 << 1;
    private static final AxisAlignedBB EMPTY = new AxisAlignedBB(0, 0, 0, 0, 0, 0);
    public static final PropertyDirection FACING = PropertyDirection.create("facing");

    public BlockSky()
    {
        super(Material.CLOTH);
        this.setHardness(1.5F);
        this.setSoundType(SoundType.CLOTH);
        setDefaultState(getDefaultState().withProperty(POWERED, false));
    }
    

    @SideOnly(Side.CLIENT)
    public int getBlockColor()
    { return UtilRender.getFogColor().getColor(); }

    @Override
    protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, POWERED);
    }

    @Override
    public IBlockState getStateFromMeta(int meta) {
        return getDefaultState()
                .withProperty(POWERED, (meta & MASK_POWERED) != 0);
    }

    @Override
    public int getMetaFromState(IBlockState state) {
        final int isPowered = state.getValue(POWERED)? MASK_POWERED : 0;

        return isPowered;
    }

    @Override
    public boolean hasTileEntity(IBlockState state)
    { return true; }

    @Override
    public TileEntity createTileEntity(World world, IBlockState state)
    { return new TileEntitySky(); }

    @Override
    public void neighborChanged(IBlockState state, World worldIn, BlockPos pos, Block blockIn,BlockPos changedPos)
    { if (!worldIn.isRemote) {
            final boolean isPowered = worldIn.isBlockIndirectlyGettingPowered(pos) > 0;
            final boolean isActive = state.getValue(POWERED);
            if (isPowered != isActive) worldIn.scheduleUpdate(pos, this, 1);
        }
    }

    @Override
    public void updateTick(World world, BlockPos pos, IBlockState state, Random random) {
        final boolean isPowered = world.isBlockIndirectlyGettingPowered(pos) > 0;

        world.setBlockState(pos, state.withProperty(POWERED, isPowered));
    }

    @Override
    public EnumBlockRenderType getRenderType(IBlockState state)
    {return state.getValue(POWERED) ? EnumBlockRenderType.MODEL : EnumBlockRenderType.ENTITYBLOCK_ANIMATED;}

    public static boolean isActive(IBlockState state) {
        boolean isPowered = state.getValue(POWERED);
        return isPowered;
    }

    @Override
    @SuppressWarnings("deprecation")
    public AxisAlignedBB getSelectedBoundingBox(IBlockState state, World world, BlockPos pos) {
        return isActive(state)? EMPTY : super.getSelectedBoundingBox(state, world, pos);
    }

    //Custom Tooltip
    @Override
    public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par3List, boolean par4) {
        if (KeyChecker.isHoldingShift()) {
            par3List.add(TextFormatting.GOLD + TranslateUtils.toLocal("tooltip.block.sky_block.line1"));
        } else {
            par3List.add(TranslateUtils.toLocal("tooltip.item.hold") + " " + TextFormatting.AQUA + TextFormatting.ITALIC + TranslateUtils.toLocal("tooltip.item.shift"));
        }
    }

}
