package com.stormy.lightninglib.lib.library;

import net.minecraft.block.state.IBlockState;
import net.minecraft.util.EnumFacing;

/**
 * Redstone Interface that can be implemented by tiles!
 */
public interface IRedstone
{
    int getWeakPower(IBlockState blockState, EnumFacing side);
    int getStrongPower(IBlockState blockState, EnumFacing side); }
