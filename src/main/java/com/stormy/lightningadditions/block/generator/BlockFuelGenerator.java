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
import com.stormy.lightningadditions.network.GuiHandler;
import com.stormy.lightningadditions.tile.generator.TileEntityFuelGenerator;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;
import java.util.Random;

public class BlockFuelGenerator extends BlockBaseGenerator{

    public BlockFuelGenerator() {
        super(Material.ROCK);
        setHardness(1.0f);
        setResistance(0.5f);
    }

    @Nullable
    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta) {
        return new TileEntityFuelGenerator();
    }

    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing side, float hitX, float hitY, float hitZ) {
        if (!worldIn.isRemote){
            if (playerIn != null) {
                playerIn.openGui(LightningAdditions.INSTANCE, GuiHandler.gui_id_fuel_generator, worldIn, pos.getX(), pos.getY(), pos.getZ());
            }
        }
        return true;
    }

    @Override
    public void setState(World worldIn, BlockPos pos, boolean isActive)
    {
        super.setState(worldIn, pos, isActive);
    }

    @SideOnly(Side.CLIENT)
    @SuppressWarnings("incomplete-switch")
    public void randomDisplayTick(IBlockState stateIn, World worldIn, BlockPos pos, Random rand) {
        if (worldIn.getTileEntity(pos) != null && worldIn.getTileEntity(pos) instanceof TileEntityFuelGenerator)
        if (((TileEntityFuelGenerator) worldIn.getTileEntity(pos)).isActive()) {
            double d0 = (double) pos.getX() + 0.5D;
            double d1 = (double) pos.getY() + rand.nextDouble() * 2.0D / 16.0D;
            double d2 = (double) pos.getZ() + 0.5D;
            double d3 = 0.37D;
            double d4 = rand.nextDouble() * 0.6D - 0.3D;
            double d5 = rand.nextDouble() * 0.6D - 0.3D;

            if (rand.nextDouble() < 0.1D) {
                worldIn.playSound((double) pos.getX() + 0.5D, (double) pos.getY(), (double) pos.getZ() + 0.5D, SoundEvents.BLOCK_FURNACE_FIRE_CRACKLE, SoundCategory.BLOCKS, 1.0F, 1.0F, false);
            }

            worldIn.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, d0 + d4, d1 + d3, d2 + d5, 0.0D, 0.0D, 0.0D, new int[0]);
            worldIn.spawnParticle(EnumParticleTypes.FLAME, d0 + d4, d1 + d3, d2 + d5, 0.0D, 0.0D, 0.0D, new int[0]);

        }

    }
}
