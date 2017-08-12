package com.stormy.lightninglib.lib.utils;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class MultiblockHelper
{

    public void forBlock(String name, World world, BlockPos pos, BlockPos startPos, int flag) { }
    public boolean checkBlock(String name, World world, BlockPos pos)
    { IBlockState state = world.getBlockState(pos);
        if (name.equals("")) { return true; }
        else if (name.equals("air")) { return state.getBlock().isAir(state, world, pos); }
        else { return Block.REGISTRY.getNameForObject(state.getBlock()).toString().equals(name); } }

    public void setBlock(String name, World world, BlockPos pos)
    { Block block = Block.REGISTRY.getObject(new ResourceLocation(name));
        if (block != null) { world.setBlockState(pos, block.getDefaultState()); }
        else { world.setBlockToAir(pos); } }

    public BlockPos invalidBlock = null;
    public String expectedBlock = null;

}
