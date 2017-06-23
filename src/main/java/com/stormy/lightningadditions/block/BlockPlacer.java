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

import com.stormy.lightningadditions.client.gui.GuiHandler;
import com.stormy.lightningadditions.reference.KeyChecker;
import com.stormy.lightningadditions.reference.ModInformation;
import com.stormy.lightningadditions.reference.Translate;
import com.stormy.lightningadditions.tile.TileEntityPlacer;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.BlockHorizontal;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Random;

public class BlockPlacer extends BlockContainer{

    public static final PropertyDirection FACING = PropertyDirection.create("facing");
    public static final PropertyBool POWERED = PropertyBool.create("powered");

    private static boolean keepInventory;

    public BlockPlacer() {
        super(Material.ROCK);
        setHardness(2.0F);
        setResistance(2.0F);
        this.setDefaultState(this.blockState.getBaseState().withProperty(FACING, EnumFacing.NORTH).withProperty(POWERED, false));
    }

    @Nullable
    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta) {
        return new TileEntityPlacer();
    }

    @Override
    @SuppressWarnings("deprecation")
    public void neighborChanged(IBlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos changedPos) {
        EnumFacing facing = state.getValue(FACING);
        boolean powered = state.getValue(POWERED);
        boolean newPowered = worldIn.isBlockIndirectlyGettingPowered(pos) > 0;
        setState(newPowered, worldIn, pos);
        if (newPowered && !powered) {
            if (worldIn.getTileEntity(pos) != null)
                if (worldIn.getTileEntity(pos) instanceof TileEntityPlacer) {
                    TileEntityPlacer tePlacer = (TileEntityPlacer) worldIn.getTileEntity(pos);
                    if (worldIn.isAirBlock(pos.offset(facing)) && tePlacer != null) {
                        int slot = 0;
                        if (tePlacer.getStackInSlot(slot) != ItemStack.EMPTY && tePlacer.getStackInSlot(0).getItem() instanceof ItemBlock) {
                            Item item = tePlacer.getStackInSlot(slot).getItem();
                            int meta = item.getMetadata(tePlacer.getStackInSlot(slot).getItemDamage());
                            IBlockState blockToPlace = ((ItemBlock) item).getBlock().getStateFromMeta(meta);

                            if (blockToPlace.getProperties().containsKey(FACING)){
                                worldIn.setBlockState(pos.offset(facing), blockToPlace.withProperty(FACING, facing));
                            }else if(blockToPlace.getProperties().containsKey(BlockHorizontal.FACING)){
                                worldIn.setBlockState(pos.offset(facing), blockToPlace.withProperty(BlockHorizontal.FACING, facing));
                            }else{
                                worldIn.setBlockState(pos.offset(facing), blockToPlace);
                            }
                            tePlacer.getStackInSlot(0).shrink(1);
                            worldIn.playSound(null, pos, blockToPlace.getBlock().getSoundType(blockToPlace, worldIn, pos, null).getPlaceSound(), SoundCategory.BLOCKS, 1.0F, new Random().nextFloat() * 0.4F + 0.8F);
                        }
                    }
                }
        }
    }

    private void setState(boolean newPowered, World worldIn, BlockPos pos)
    {
        IBlockState iblockstate = worldIn.getBlockState(pos);
        TileEntity tileentity = worldIn.getTileEntity(pos);
        keepInventory = true;

        worldIn.setBlockState(pos, this.getDefaultState().withProperty(FACING, iblockstate.getValue(FACING)).withProperty(POWERED, newPowered), 3);
        worldIn.setBlockState(pos, this.getDefaultState().withProperty(FACING, iblockstate.getValue(FACING)).withProperty(POWERED, newPowered), 3);

        keepInventory = false;

        if (tileentity != null)
        {
            tileentity.validate();
            worldIn.setTileEntity(pos, tileentity);
        }
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
    public void onBlockAdded(World worldIn, BlockPos pos, IBlockState state)
    { super.onBlockAdded(worldIn, pos, state);
        this.setDefaultFacing(worldIn, pos, state);
        worldIn.setBlockState(pos, state.withProperty(POWERED, worldIn.isBlockIndirectlyGettingPowered(pos) > 0));
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
    @SuppressWarnings("deprecation")
    public IBlockState getStateForPlacement(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer)
    { return this.getDefaultState().withProperty(FACING, EnumFacing.getDirectionFromEntityLiving(pos, placer));
    }

    @Override
    public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack)
    {
        worldIn.setBlockState(pos, state.withProperty(FACING, EnumFacing.getDirectionFromEntityLiving(pos, placer)), 2);
    }

    @Override
    @SuppressWarnings("deprecation")
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

    @Override
    public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing side, float hitX, float hitY, float hitZ){
        if (player != null) {
            if (!player.isSneaking()) {
                player.openGui(ModInformation.MODID, GuiHandler.gui_id_placer, world, pos.getX(), pos.getY(), pos.getZ());
                return true;
            }
            return false;
        }
        return false;
    }

    //Custom Tooltip
    @Override
    public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par3List, boolean par4) {
        if (KeyChecker.isHoldingShift()) {
            par3List.add(TextFormatting.DARK_AQUA + Translate.toLocal("tooltip.block.breaker.line1"));
        } else {
            par3List.add(Translate.toLocal("tooltip.item.hold") + " " + TextFormatting.AQUA + TextFormatting.ITALIC + Translate.toLocal("tooltip.item.shift"));
        }
    }

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

}
