package com.stormy.lightninglib.lib.block;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.Explosion;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockBaseSafe extends BlockBase
{
    public BlockBaseSafe(Material material) { super(material); }

    @Override
    public boolean canEntityDestroy(IBlockState state, IBlockAccess world, BlockPos pos, Entity entity)
    { return entity instanceof EntityPlayer;//Fake-player Harvest//
    }

    @Override
    public boolean canDropFromExplosion(Explosion explosionIn)
    { return false; }

    @Override
    public void onBlockExploded(World world, BlockPos pos, Explosion explosion) {}
}
