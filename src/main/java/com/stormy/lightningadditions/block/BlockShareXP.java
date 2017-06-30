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
import com.stormy.lightningadditions.tile.TileEntitySharingXP;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

import java.util.List;

@SuppressWarnings("deprecation")
public class BlockShareXP extends Block
{
    public static final PropertyDirection FACING = PropertyDirection.create("facing");

    public BlockShareXP() {
        super(Material.IRON);
        setHardness(12.5F);
        setResistance(2000.0F);
        setSoundType(SoundType.METAL);
        setLightLevel(1.0F);
        setLightOpacity(16);
    }
    @Override
    public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par3List, boolean par4)
    {
        if (KeyChecker.isHoldingShift())
        { par3List.add(TextFormatting.GOLD + Translate.toLocal("tooltip.block.share_xp.line1"));
          par3List.add(TextFormatting.GREEN + Translate.toLocal("tooltip.block.share_xp.line1.p2"));
        }
        else
        {
            par3List.add(Translate.toLocal("tooltip.item.hold") + " " + TextFormatting.AQUA + TextFormatting.ITALIC + Translate.toLocal("tooltip.item.shift"));
        }
    }

    @Override
    public IBlockState getStateForPlacement(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer)
    {return this.getDefaultState().withProperty(FACING, EnumFacing.getDirectionFromEntityLiving(pos, placer)); }

    @Override
    public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack)
    {
        if (EnumFacing.getDirectionFromEntityLiving(pos, placer) != EnumFacing.UP && EnumFacing.getDirectionFromEntityLiving(pos, placer) != EnumFacing.DOWN) {
            worldIn.setBlockState(pos, state.withProperty(FACING, EnumFacing.getDirectionFromEntityLiving(pos, placer).rotateY()), 2);
        }else{
            worldIn.setBlockState(pos, state.withProperty(FACING, EnumFacing.getDirectionFromEntityLiving(pos, placer)), 2);
        }
    }

    @Override
    public boolean isOpaqueCube(IBlockState state)
    { return false; }

    @Override
    public boolean isFullCube(IBlockState state)
    { return false; }

    @Override
    public BlockRenderLayer getBlockLayer()
    { return BlockRenderLayer.CUTOUT; }

    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
    { if(!worldIn.isRemote)
        {
            if(playerIn.isSneaking()) //add all levels to the block
            {
                ((TileEntitySharingXP)worldIn.getTileEntity(pos)).addLevel(playerIn.experienceLevel);
                playerIn.removeExperienceLevel(playerIn.experienceLevel);
            }
            else //remove one level from the block
            {
                TileEntitySharingXP te = ((TileEntitySharingXP)worldIn.getTileEntity(pos));

                if(te.getStoredLevels() == 0)
                    return true;

                te.removeLevel();
                playerIn.addExperienceLevel(1);
            }
        }

        return true;
    }




    @Override
    public boolean hasTileEntity(IBlockState state)
    {
        return true;
    }

    @Override
    public TileEntity createTileEntity(World world, IBlockState state)
    {
        return new TileEntitySharingXP();
    }

    @Override
    protected BlockStateContainer createBlockState()
    {
        return new BlockStateContainer(this, new IProperty[] { FACING });
    }

    @Override
    @SuppressWarnings("deprecation")
    public IBlockState getStateFromMeta(int meta)
    {
        return this.getDefaultState().withProperty(FACING, EnumFacing.values()[meta]);
    }

    @Override
    public int getMetaFromState(IBlockState state)
    {
        int i = state.getValue(FACING).getIndex();

        return i;
    }

}
