/*
 * ********************************************************************************
 * Copyright (c) 2017 StormyMode, MiningMark48. All Rights Reserved!
 * This file is part of Lightning Additions (MC-Mod).
 *
 * This project cannot be copied and/or distributed without the express
 * permission of StormyMode, MiningMark48 (Developers)!
 * ********************************************************************************
 */

package com.stormy.lightningadditions.feature.harvest;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ListIterator;
import java.util.concurrent.ExecutionException;
import net.minecraft.block.Block;
import net.minecraft.block.BlockCrops;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.launchwrapper.Launch;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.ReflectionHelper;

@SuppressWarnings("deprecation")
@Mod.EventBusSubscriber
public class Harvest
{
    private static Method ageProperty;
    private static Method seedItem;
    private static BlockCrops crop;
    private static LoadingCache<BlockCrops, Boolean> custom = CacheBuilder.newBuilder().maximumSize(100L).build(new Loader(null));

    @Mod.EventHandler
    public static void preInit()
    {
        ageProperty = ReflectionHelper.findMethod(BlockCrops.class, null, new String[] { "func_185524_e", "getAgeProperty" }, new Class[0]);
        seedItem = ReflectionHelper.findMethod(BlockCrops.class, null, new String[] { "func_149866_i", "getSeed" }, new Class[0]);
    }

    @SubscribeEvent
    public static void onRightClick(PlayerInteractEvent.RightClickBlock event)
            throws InvocationTargetException, IllegalAccessException, ExecutionException
    {
        if ((event.getEntityPlayer() == null) || (event.getHand() == EnumHand.OFF_HAND) || (event.getEntityPlayer().isSneaking())) {
            return;
        }
        World world = event.getWorld();
        IBlockState state = world.getBlockState(event.getPos());
        if ((state.getBlock() instanceof BlockCrops))
        {
            BlockCrops block = (BlockCrops)state.getBlock();
            if ((!((Boolean)custom.get(block)).booleanValue()) && (block.isMaxAge(state))) {
                if (!world.isRemote)
                {
                    crop = block;
                    block.dropBlockAsItemWithChance(world, event.getPos(), state, 1.0F, 0);
                    crop = null;
                    world.setBlockState(event.getPos(), state.withProperty((PropertyInteger)ageProperty.invoke(block, new Object[0]), Integer.valueOf(0)));
                    event.setCanceled(true);
                }
                else
                {
                    event.getEntityPlayer().swingArm(EnumHand.MAIN_HAND);
                }
            }
        }
    }

    @SubscribeEvent
    public static void onDrop(BlockEvent.HarvestDropsEvent event)
            throws InvocationTargetException, IllegalAccessException
    {
        if (crop == null) {
            return;
        }
        Item seed = (Item)seedItem.invoke(crop, new Object[0]);
        ListIterator<ItemStack> it = event.getDrops().listIterator(event.getDrops().size());
        while (it.hasPrevious())
        {
            ItemStack stack = (ItemStack)it.previous();
            if (stack.getItem() == seed)
            {
                stack.shrink(1);
                break;
            }
        }
    }

    private static class Loader
            extends CacheLoader<BlockCrops, Boolean>
    {
        public Loader(Object o) {
        }

        public Boolean load(BlockCrops key)
                throws Exception
        {
            Class<?>[] parameters = { World.class, BlockPos.class, IBlockState.class, EntityPlayer.class, EnumHand.class, EnumFacing.class, Float.TYPE, Float.TYPE, Float.TYPE };
            try
            {
                Method m = key.getClass().getMethod(((Boolean)Launch.blackboard.get("fml.deobfuscatedEnvironment")).booleanValue() ? "onBlockActivated" : "func_180639_a", parameters);
                return Boolean.valueOf(m.getDeclaringClass() != Block.class);
            }
            catch (Exception ignored) {}
            return Boolean.TRUE;
        }
    }
}
