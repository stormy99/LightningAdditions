package com.stormy.lightningadditions.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import javax.annotation.Nonnull;
import java.util.Random;

public class BlockCursedEarth extends Block {

    public static enum Type
    {
        CURSED_NORMAL;
        private Type() {}
    }

    public static int powered = 0;

    public BlockCursedEarth() {
        super(Material.GRASS);
        this.setTickRandomly(true);
        this.setHardness(0.5F);
        this.blockResistance = 200.0F;
    }

    @SuppressWarnings("deprecation")
    public boolean isFullCube(IBlockState state)
    {
        return true;
    }

    public EnumBlockRenderType getRenderType(IBlockState state)
    {
        return EnumBlockRenderType.MODEL;
    }

    @SuppressWarnings("deprecation")
    public boolean isOpaqueCube(IBlockState state)
    {
        return false;
    }

    public boolean canSilkHarvest(World world, EntityPlayer player, int x, int y, int z, int metadata) {return true;}


    public Item getItemDropped(IBlockState state, Random rand, int fortune) {return Item.getItemFromBlock(Blocks.DIRT);}

    public int damageDropped(IBlockState state) {
        return 0;
    }

    public boolean isFireSource(@Nonnull World world, BlockPos pos, EnumFacing side) {
        return side == EnumFacing.UP;
    }


}
