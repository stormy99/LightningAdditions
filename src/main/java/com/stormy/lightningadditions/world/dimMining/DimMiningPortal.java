/*
 * ********************************************************************************
 * Copyright (c) 2017 StormyMode, MiningMark48. All Rights Reserved!
 * This file is part of Lightning Additions (MC-Mod).
 *
 * This project cannot be copied and/or distributed without the express
 * permission of StormyMode, MiningMark48 (Developers)!
 * ********************************************************************************
 */

package com.stormy.lightningadditions.world.dimMining;

import com.stormy.lightningadditions.init.ModSounds;
import com.stormy.lightningadditions.utility.logger.ConfigurationManagerLA;
import net.minecraft.block.Block;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.Random;

public class DimMiningPortal extends Block{

    private int tickcount = 0;

    public DimMiningPortal() {
        super(Material.ROCK, MapColor.BLUE);
        this.setTickRandomly(true);
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
    public void randomDisplayTick(IBlockState stateIn, World worldIn, BlockPos pos, Random rand) {

        tickcount++;

        double centerX = pos.getX() + 0.5D;
        double centerZ = pos.getZ() + 0.5D;
        float circleRadius = 0.25f;

        double particleX = centerX + (circleRadius * Math.cos(tickcount));
        double particleZ = centerZ + (circleRadius * Math.sin(tickcount));

        double particleX2 = centerX + (circleRadius * 1.75 * Math.cos(tickcount));
        double particleZ2 = centerZ + (circleRadius * 1.75 * Math.sin(tickcount));

        for (int i = 0; i <= 100; i++) {
            worldIn.spawnParticle(EnumParticleTypes.PORTAL, particleX, pos.getY() + 1 + i/20, particleZ, 0, 1.5f, 0);
            worldIn.spawnParticle(EnumParticleTypes.PORTAL, particleX2, pos.getY() + 1 + i/20, particleZ2, 0, -1.5f, 0);

            worldIn.spawnParticle(EnumParticleTypes.REDSTONE, particleX, pos.getY() + 1, particleZ, 0, 0, 0);
        }

        if (tickcount > 8) tickcount = 0;

    }

    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        if (!worldIn.isRemote) {
            player.playSound(ModSounds.void_block, 1.0f, 1.0f);
            if (worldIn.provider.getDimension() != ConfigurationManagerLA.dimMiningID) {
                FMLCommonHandler.instance().getMinecraftServerInstance().getPlayerList().transferPlayerToDimension((EntityPlayerMP) player, ConfigurationManagerLA.dimMiningID, new MiningWorldTeleport(player.getServer().worldServerForDimension(ConfigurationManagerLA.dimMiningID), pos));
            } else {
                FMLCommonHandler.instance().getMinecraftServerInstance().getPlayerList().transferPlayerToDimension((EntityPlayerMP) player, 0, new MiningWorldTeleport(player.getServer().worldServerForDimension(0), pos));
            }
            return true;
        }
        return false;
    }
}
