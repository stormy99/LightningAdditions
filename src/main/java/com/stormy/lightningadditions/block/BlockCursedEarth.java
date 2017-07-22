package com.stormy.lightningadditions.block;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.passive.*;
import net.minecraft.init.Blocks;
import net.minecraft.init.MobEffects;
import net.minecraft.item.Item;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.client.FMLClientHandler;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.Random;

public class BlockCursedEarth extends Block {

    public static int powered = 0;

    public BlockCursedEarth() {
        super(Material.GRASS);
        this.setSoundType(SoundType.GROUND);
        this.setTickRandomly(true);
        this.setHardness(0.5F);
        this.blockResistance = 200.0F;
    }

    @Override
    @SuppressWarnings("deprecation")
    public boolean isFullCube(IBlockState state)
    {
        return true;
    }

    @Override
    @SuppressWarnings("deprecation")
    public EnumBlockRenderType getRenderType(IBlockState state)
    {
        return EnumBlockRenderType.MODEL;
    }

    @Override
    @SuppressWarnings("deprecation")
    public boolean isOpaqueCube(IBlockState state)
    {
        return false;
    }

    @Override
    @SuppressWarnings("deprecation")
    protected boolean canSilkHarvest() {
        return true;
    }

    public Item getItemDropped(IBlockState state, Random rand, int fortune) {return Item.getItemFromBlock(Blocks.DIRT);}

    public int damageDropped(IBlockState state) {
        return 0;
    }

    public boolean isFireSource(@Nonnull World world, BlockPos pos, EnumFacing side) {
        return side == EnumFacing.UP;
    }

    @Override
    public int tickRate(World worldIn) {
        return 20;
    }

    @Override
    public void randomTick(World worldIn, BlockPos pos, IBlockState state, Random rand) {
        if (rand.nextInt(1) == 0) {
            doEntitySpawns(worldIn, pos, rand);
        } else {
            if (worldIn.canSeeSky(pos)) {
                if (worldIn.getWorldTime() >= 1000 && worldIn.getWorldTime() <= 13000) {
                    worldIn.setBlockState(pos.up(), Blocks.FIRE.getDefaultState());
                    worldIn.setBlockState(pos, Blocks.DIRT.getDefaultState());
                }
            }
        }
    }

    private ArrayList<EntityLiving> getEntitiesToSpawnWithEffects(World world){
        ArrayList<EntityLiving> entitiesToSpawn = new ArrayList<>();
        entitiesToSpawn.add(new EntityPig(world));
        entitiesToSpawn.add(new EntityCow(world));
        entitiesToSpawn.add(new EntityBat(world));

        for (EntityLiving entity : entitiesToSpawn){
            entity.addPotionEffect(new PotionEffect(MobEffects.REGENERATION, 100, 0));
        }

        return entitiesToSpawn;
    }

    private void doEntitySpawns(World worldIn, BlockPos pos, Random rand){
        ArrayList<EntityLiving> entitiesToSpawn = getEntitiesToSpawnWithEffects(worldIn);

        for (EntityLiving entity : entitiesToSpawn){
            entity.setPositionAndUpdate(pos.getX(), pos.getY() + 1.5, pos.getZ());
        }

        worldIn.spawnEntity(entitiesToSpawn.get(rand.nextInt(entitiesToSpawn.size())));
    }

}
