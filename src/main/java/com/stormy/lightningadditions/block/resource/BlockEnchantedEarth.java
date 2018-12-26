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

package com.stormy.lightningadditions.block.resource;

import com.stormy.lightningadditions.init.ModBlocks;
import de.kitsunealex.frame.block.BlockBase;
import net.minecraft.block.Block;
import net.minecraft.block.BlockDirt;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.monster.*;
import net.minecraft.entity.passive.*;
import net.minecraft.init.Blocks;
import net.minecraft.init.MobEffects;
import net.minecraft.item.Item;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.Random;

public class BlockEnchantedEarth extends BlockBase {

    //Only works in light, won't burn. Spawns in light.

    public static int powered = 0;

    public BlockEnchantedEarth() {
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
        return 40;
    }

    @Override
    public void randomTick(World worldIn, BlockPos pos, IBlockState state, Random rand) {
        if (rand.nextInt(2) == 0) {
            if (worldIn.getLightFromNeighbors(pos) >= 9 && worldIn.getGameRules().getBoolean("doMobSpawning")) doEntitySpawns(worldIn, pos, rand);
        } else {

            //Spreading
            if (worldIn.getLightFromNeighbors(pos.up()) >= 9)
            {
                for (int i = 0; i < 4; ++i)
                {
                    BlockPos blockpos = pos.add(rand.nextInt(3) - 1, rand.nextInt(5) - 3, rand.nextInt(3) - 1);

                    if (blockpos.getY() >= 0 && blockpos.getY() < 256 && !worldIn.isBlockLoaded(blockpos))
                    {
                        return;
                    }

                    IBlockState iblockstate = worldIn.getBlockState(blockpos.up());
                    IBlockState iblockstate1 = worldIn.getBlockState(blockpos);

                    if (((iblockstate1.getBlock() == Blocks.DIRT && iblockstate1.getValue(BlockDirt.VARIANT) == BlockDirt.DirtType.DIRT) || iblockstate1.getBlock() == Blocks.GRASS) && worldIn.getLightFromNeighbors(blockpos.up()) >= 9 && iblockstate.getLightOpacity(worldIn, pos.up()) <= 2)
                    {
                        worldIn.setBlockState(blockpos, ModBlocks.enchanted_earth.getDefaultState());
                    }
                }
            }

            //Turn to dirt
            if (worldIn.getBlockState(pos.up()).isFullBlock()){
                if (rand.nextInt(2) == 0){
                    worldIn.setBlockState(pos, Blocks.DIRT.getDefaultState());
                }else {
                    worldIn.setBlockState(pos, Blocks.DIRT.getStateFromMeta(1));
                }
            }

        }
    }

    private ArrayList<EntityLiving> getEntitiesToSpawnWithEffects(World world, BlockPos pos){
        ArrayList<EntityLiving> entitiesList = new ArrayList<>();
        entitiesList.add(new EntityCow(world));
        entitiesList.add(new EntityPig(world));
        entitiesList.add(new EntitySheep(world));
        entitiesList.add(new EntityChicken(world));
        entitiesList.add(new EntityHorse(world));
        entitiesList.add(new EntityDonkey(world));
        entitiesList.add(new EntityLlama(world));
        entitiesList.add(new EntityWolf(world));
        if (world.getBiome(pos).isSnowyBiome()) entitiesList.add(new EntityPolarBear(world));
        if (world.getBiome(pos).isSnowyBiome()) entitiesList.add(new EntitySnowman(world));

        for (EntityLiving entity : entitiesList){
            entity.addPotionEffect(new PotionEffect(MobEffects.REGENERATION, 1000, 0, true, false));
            entity.addPotionEffect(new PotionEffect(MobEffects.SPEED, 1000, 1, true, false));
            entity.addPotionEffect(new PotionEffect(MobEffects.STRENGTH, 1000, 0, true, false));
        }

        return entitiesList;
    }

    private void doEntitySpawns(World worldIn, BlockPos pos, Random rand){
        ArrayList<EntityLiving> entitiesToSpawn = getEntitiesToSpawnWithEffects(worldIn, pos);

        EntityLiving entityToSpawn = entitiesToSpawn.get(rand.nextInt(entitiesToSpawn.size()));
        entityToSpawn.setLocationAndAngles(pos.getX(), pos.getY() + 1.5, pos.getZ(), rand.nextFloat() * 360F, rand.nextFloat() * 360F);

        if (!worldIn.getBlockState(pos.up()).causesSuffocation()) worldIn.spawnEntity(entityToSpawn);
    }

}
