/*
 * *******************************************************************************
 * Copyright (c) 2017 StormyMode, MiningMark48. All Rights Reserved!
 * This file is part of Lightning Additions (MC-Mod).
 *
 * This project cannot be copied and/or distributed without the express
 * permission of StormyMode, MiningMark48 (Developers)!
 * *******************************************************************************
 */

package com.stormy.lightningadditions.block;

import com.stormy.lightningadditions.LightningAdditions;
import com.stormy.lightningadditions.block.base.BlockContainerLA;
import com.stormy.lightningadditions.network.GuiHandler;
import com.stormy.lightningadditions.tile.TileEntityParticleAccelerator;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import javax.annotation.Nullable;

/**
 * Created by KitsuneAlex
 */
public class BlockParticleAccelerator extends BlockContainerLA {

    public BlockParticleAccelerator() {
        super(Material.IRON, "particle_accelerator");
        this.setHardness(1.5F);
    }

    @Override
    protected Class<? extends TileEntity> tile() {
        return TileEntityParticleAccelerator.class;
    }

    @Nullable
    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta) {
        return new TileEntityParticleAccelerator();
    }

    @Override
    public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        if(!world.isRemote){
            player.openGui(LightningAdditions.INSTANCE, GuiHandler.gui_id_particle_accellerator, world, pos.getX(), pos.getY(), pos.getZ());
        }

        return true;
    }

}
