package com.stormy.lightningadditions.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockCompressedBookshelf extends Block {

    private float enchant_power;

    public BlockCompressedBookshelf(float enchant_power) {
        super(Material.ROCK, MapColor.BROWN);
        this.enchant_power = enchant_power;
    }

    @Override
    public float getEnchantPowerBonus(World world, BlockPos pos) {
        return this.enchant_power;
    }
}
