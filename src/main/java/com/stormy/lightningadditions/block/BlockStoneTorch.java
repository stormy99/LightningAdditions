package com.stormy.lightningadditions.block;

import net.minecraft.block.BlockTorch;
import net.minecraft.block.SoundType;

public class BlockStoneTorch extends BlockTorch
{
    public BlockStoneTorch()
    {
        super();
        setHardness(0.0F);
        setLightLevel(0.9375F);
        setSoundType(SoundType.STONE); }
}
