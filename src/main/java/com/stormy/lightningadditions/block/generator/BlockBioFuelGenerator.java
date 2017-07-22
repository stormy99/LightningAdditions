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
import com.stormy.lightningadditions.tile.generator.TileEntityBioFuelGenerator;
import com.stormy.lightningadditions.tile.generator.TileEntityFuelGenerator;
import com.stormy.lightningadditions.utility.logger.LALogger;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;
import java.util.Random;

public class BlockBioFuelGenerator extends BlockBaseGenerator{

    private static final AxisAlignedBB BOUNDING_BOX = new AxisAlignedBB(0.0, 0.0D, 0.0D, 1.0D, 0.9375D, 1.0D);

    public BlockBioFuelGenerator() {
        super(Material.ROCK, BOUNDING_BOX);
        setHardness(1.0f);
        setResistance(0.5f);
    }

    @Nullable
    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta) {
        return new TileEntityBioFuelGenerator();
    }

    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing side, float hitX, float hitY, float hitZ) {
        if (!worldIn.isRemote){
            if (playerIn != null) {
                playerIn.openGui(LightningAdditions.INSTANCE, GuiHandler.gui_id_biofuel_generator, worldIn, pos.getX(), pos.getY(), pos.getZ());
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
        if (stateIn.getValue(ACTIVE)) {
            for (int i = 0; i <= rand.nextInt(5) + 1; i++) {
                double d0 = (double) pos.getX() + 0.5D;
                double d1 = (double) pos.getY() + 1.015D;
                double d2 = (double) pos.getZ() + 0.5D;
                double d4 = rand.nextDouble() * 0.25D - 0.18D;
                double d5 = rand.nextDouble() * 0.25D - 0.18D;

                worldIn.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, d0 + d4, d1, d2 + d5, 0.0D, 0.0D, 0.0D, new int[0]);
            }

        }
    }

}
