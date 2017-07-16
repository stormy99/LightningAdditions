/*
 * ********************************************************************************
 * Copyright (c) 2017 StormyMode, MiningMark48. All Rights Reserved!
 * This file is part of Lightning Additions (MC-Mod).
 *
 * This project cannot be copied and/or distributed without the express
 * permission of StormyMode, MiningMark48 (Developers)!
 * ********************************************************************************
 */

package com.stormy.lightningadditions.block.ore;

import com.stormy.lightningadditions.creativetab.CreativeTabLA;
import com.stormy.lightningadditions.init.ModBlocks;
import com.stormy.lightningadditions.utility.logger.ConfigurationManagerLA;
import com.stormy.lightningadditions.utility.logger.LALogger;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.monster.EntityPigZombie;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

import java.util.List;
import java.util.Random;

public class NetherOreBlock extends LAOreBase
{
    private static int aggroRange = 32;

    public NetherOreBlock(String unlocalizedName)
    {
        super(unlocalizedName, Material.ROCK, 3f, 15f);
        this.setRegistryName(unlocalizedName);
        this.setCreativeTab(CreativeTabLA.LA_TAB_ORES);

    }

    @Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune)
    {
        return this == ModBlocks.NETHER_COAL_ORE ? Items.COAL :
                this == ModBlocks.NETHER_DIAMOND_ORE ? Items.DIAMOND :
                        this == ModBlocks.NETHER_EMERALD_ORE ? Items.EMERALD :
                                this == ModBlocks.NETHER_LAPIS_ORE ? Items.DYE :
                                                this == ModBlocks.NETHER_REDSTONE_ORE ? Items.REDSTONE :
                                                        Item.getItemFromBlock(this);
    }

    @Override
    public int quantityDropped(Random random)
    {
        return  this == ModBlocks.NETHER_COAL_ORE     ? 1 + random.nextInt(2) :
                this == ModBlocks.NETHER_DIAMOND_ORE  ? 1 + random.nextInt(8) :
                        this == ModBlocks.NETHER_EMERALD_ORE  ? 1 + random.nextInt(3) :
                                this == ModBlocks.NETHER_LAPIS_ORE    ? 1 + random.nextInt(5) :
                                                this == ModBlocks.NETHER_REDSTONE_ORE ? 1 + random.nextInt(5) :
                                                        1;
    }

    @Override
    public int damageDropped(IBlockState state)
    { return this == ModBlocks.NETHER_LAPIS_ORE ? EnumDyeColor.BLUE.getDyeDamage() : 0; }

    @Override
    public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World world, BlockPos pos, EntityPlayer player)
    {
        if(this == ModBlocks.NETHER_LAPIS_ORE)
        { return new ItemStack(Item.getItemFromBlock(ModBlocks.NETHER_LAPIS_ORE)); }
        else
        { return super.getPickBlock(state, target, world, pos, player); }
    }

    @Override
    public boolean isFireSource(World world, BlockPos blockPos, EnumFacing facing) {
        return facing == EnumFacing.UP;
    }

    @Override
    public void harvestBlock(World world, EntityPlayer player, BlockPos pos, IBlockState state, TileEntity te, ItemStack stack)
    {
        super.harvestBlock(world, player, pos, state, te, stack);
        if(ConfigurationManagerLA.zombiePigsAttack)
        { angerPigmen(player, world, pos); }
    }

    private void angerPigmen(EntityPlayer player, World world, BlockPos pos)
    {
        int x = pos.getX(), y = pos.getY(), z = pos.getZ();
        List<?> list = world.getEntitiesWithinAABB(EntityPigZombie.class, new AxisAlignedBB(x - aggroRange, y - aggroRange, z - aggroRange, x + aggroRange, y + aggroRange, z + aggroRange));
        for(int i = 0; i < list.size(); i++)
        {
            Entity entity = (Entity)list.get(i);
            if(entity instanceof EntityPigZombie)
            {
                EntityPigZombie zombiePig = (EntityPigZombie)entity;
                zombiePig.setRevengeTarget(player);
                LALogger.log("Zombie Pigmen hostility targeting: " + player.getDisplayNameString());
            }
        }
    }

}