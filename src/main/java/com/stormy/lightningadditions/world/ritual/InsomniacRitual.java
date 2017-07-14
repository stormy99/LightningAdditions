/*
 * ********************************************************************************
 * Copyright (c) 2017 StormyMode, MiningMark48. All Rights Reserved!
 * This file is part of Lightning Additions (MC-Mod).
 *
 * This project cannot be copied and/or distributed without the express
 * permission of StormyMode, MiningMark48 (Developers)!
 * ********************************************************************************
 */

package com.stormy.lightningadditions.world.ritual;

import com.stormy.lightningadditions.init.ModBlocks;
import com.stormy.lightningadditions.utility.UtilChat;
import com.stormy.lightningadditions.utility.logger.LALogger;
import net.minecraft.block.Block;
import net.minecraft.block.BlockDirt;
import net.minecraft.block.BlockLeaves;
import net.minecraft.block.BlockSnow;
import net.minecraft.block.material.EnumPushReaction;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import static com.stormy.lightningadditions.init.ModSounds.philosopher_stone;

/**
 * Derived from Extra Utilities 1.7
 */

public abstract class InsomniacRitual
{
    private static IBlockState dirt;
    public static int required_dirt = 18;
    public static int max_light = 7;
    public static int window_available = 400;
    public static int max_range = 10;
    private static boolean isDirtOrGrass(IBlockState state) {return (state == dirt) || (state == Blocks.GRASS.getDefaultState());}
    public static void initialize() {dirt = Blocks.DIRT.getDefaultState().withProperty(BlockDirt.VARIANT, BlockDirt.DirtType.DIRT);}

    private static boolean canShift(IBlockState state)
    {if (state.getBlock() == Blocks.AIR) {return true;}
        return (state.getMaterial() != Material.WATER) && (state.getMaterial() != Material.LAVA) && (state.getMobilityFlag() == EnumPushReaction.DESTROY);}


    //Ritual surrounded by a Redstone Circle
    public static boolean redstoneCirclePresent(World world, int x, int y, int z)
    {
        for (int dx = -1; dx <= 1; dx++) {
            for (int dz = -1; dz <= 1; dz++) {
                if (((dx != 0) || (dz != 0)) && (world.getBlockState(new BlockPos(x + dx, y, z + dz)).getBlock() != Blocks.REDSTONE_WIRE)) {
                    return false;
                }
            }
        }
        return true;
    }

    //Ritual around Natural Environment
    public static boolean altarOnEarth(World world, int x, int y, int z)
    {
        for (int dx = -1; dx <= 1; dx++) {
            for (int dz = -1; dz <= 1; dz++) {
                if (!isDirtOrGrass(world.getBlockState(new BlockPos(x + dx, y - 1, z + dz)))) {
                    return false;
                }
            }
        }
        return true;
    }

    //Ritual consumed by Darkness
    public static boolean altarInDarkness(World world, int x, int y, int z)
    {
        for (int dx = -1; dx <= 1; dx++) {
            for (int dz = -1; dz <= 1; dz++) {
                if (world.getLightFromNeighbors(new BlockPos(x + dx, y, z + dz)) > max_light) {
                    return false;
                }
            }
        }
        return world.getLightFromNeighbors(new BlockPos(x, y + 1, z)) <= max_light;
    }

    //"Dancing with the Devil in the Pale Moonlight"
    public static boolean altarCanSeeMoon(World world, int x, int y, int z) {return world.canBlockSeeSky(new BlockPos(x, y, z));}

    //Natural Environment
    public static boolean naturalEarth(World world, int x, int y, int z)
    {int num_dirt = 0;
        for (int dx = -max_range; dx <= max_range; dx++) {
            for (int dz = -max_range; dz <= max_range; dz++) {
                if (dx * dx + dz * dz < max_range * max_range)
                {
                    int topSolid = world.getTopSolidOrLiquidBlock(new BlockPos(x + dx, 0, z + dz)).getY();
                    for (int dy = Math.min(3 + topSolid - y, max_range); dy >= -max_range; dy--) {
                        if (dx * dx + dy * dy + dz * dz <= max_range * max_range)
                        {
                            BlockPos pos = new BlockPos(x + dx, y + dy, z + dz);
                            IBlockState state = world.getBlockState(pos);
                            if (isDirtOrGrass(state))
                            {
                                if (canShift(world.getBlockState(pos.up())))
                                {
                                    num_dirt++;
                                    if (num_dirt == required_dirt) {
                                        return true;
                                    }
                                }
                            }
                            else {
                                if (state.isOpaqueCube()) {
                                    break;
                                }
                            }
                        }
                        else
                        {
                            if (dy < 0) {
                                break;
                            }
                        }
                    }
                }
                else
                {
                    if (dz > 0) {
                        break;
                    }
                }
            }
        }
        return false;
    }

    //Is it Midnight Yet?
    public static int checkTime(long time)
    {
        int dtime = (int)(time % 24000L);
        if (dtime < 18000 - window_available) {return -1;}
        if (dtime > 18000 + window_available) {return 1;}
        return 0;
    }

    public static boolean validate(World world, BlockPos enchantingTablePos, EntityPlayer player)
    {

        //Redstone Checker
        if (!redstoneCirclePresent(world, enchantingTablePos.getX(), enchantingTablePos.getY(), enchantingTablePos.getZ())) {return false;}

        //Pale-Moonlight Checker
        if (!altarCanSeeMoon(world, enchantingTablePos.getX(), enchantingTablePos.getY(), enchantingTablePos.getZ()))
        {UtilChat.addChatMessage(player, "Attempt Failed: This Devil wants to see the Pale Moonlight"); return false;}

        //Earth Checker
        if (!altarOnEarth(world, enchantingTablePos.getX(), enchantingTablePos.getY(), enchantingTablePos.getZ()))
        {UtilChat.addChatMessage(player, "Attempt Failed: Next time, try activation on natural earth."); return false;}

        //Light-level Checker
        if (!altarInDarkness(world, enchantingTablePos.getX(), enchantingTablePos.getY(), enchantingTablePos.getZ()))
        {UtilChat.addChatMessage(player, "Attempt Failed: Perhaps the light levels should be more gothic?"); return false;}

        //Dirt/Grass Checker
        if (!naturalEarth(world, enchantingTablePos.getX(), enchantingTablePos.getY(), enchantingTablePos.getZ()))
        {UtilChat.addChatMessage(player, "Attempt Failed: More natural earth would be appreciated!"); return false;}

        //Time Checker
        int i = checkTime(world.getWorldTime());
        if (i == -1)
        {UtilChat.addChatMessage(player, "Attempt Failed: This thing doesn't like 'early-birds'.."); return false;}
        if (i == 1)
        {UtilChat.addChatMessage(player, "Attempt Failed: 'All you had to do was catch the Train, CJ!'"); return false;}

        return true; }

    public static void startRitual(World world, int x, int y, int z)
    {
        Block cursedEarth = ModBlocks.cursed_earth;
        if (cursedEarth == Block.REGISTRY.getObject(null)) {LALogger.error("Cursed Earth not existent!"); return;}

        world.addWeatherEffect(new EntityLightningBolt(world, x, y, z, false));
        for (int dx = -max_range; dx <= max_range; dx++) {
            for (int dz = -max_range; dz <= max_range; dz++) {
                if (dx * dx + dz * dz < max_range * max_range) {
                    for (int dy = max_range; dy > -max_range; dy--) {
                        if (dx * dx + dy * dy + dz * dz <= max_range * max_range)
                        {
                            BlockPos pos = new BlockPos(x + dx, y + dy, z + dz);
                            IBlockState state = world.getBlockState(pos);
                            Block block = state.getBlock();
                            if (block != Blocks.AIR) {
                                if (isDirtOrGrass(state))
                                {
                                    world.setBlockState(pos, cursedEarth.getDefaultState());
                                }
                                else if ((block instanceof BlockLeaves))
                                {
                                    block.dropBlockAsItem(world, pos, state, 0);
                                    world.setBlockToAir(pos);
                                }
                                else if ((block instanceof BlockSnow))
                                {
                                    world.setBlockToAir(pos);
                                }
                                else if ((state.getMobilityFlag() == EnumPushReaction.DESTROY) && (block != Blocks.REDSTONE_WIRE))
                                {
                                    world.destroyBlock(pos, true);
                                }
                                else
                                {
                                    if (state.isOpaqueCube()) {
                                        break;
                                    }
                                }
                            }
                        }
                        else
                        {
                            if (dy < 0) {
                                break;
                            }
                        }
                    }
                } else {
                    if (dz > 0) {
                        break;
                    }
                }
            }
        }
        world.playSound(null, new BlockPos(x, y, z), philosopher_stone, SoundCategory.HOSTILE, 1.4F, 1.0F);
    }

}
