/*
 * ********************************************************************************
 * Copyright (c) 2017 StormyMode, MiningMark48. All Rights Reserved!
 * This file is part of Lightning Additions (MC-Mod).
 *
 * This project cannot be copied and/or distributed without the express
 * permission of StormyMode, MiningMark48 (Developers)!
 * ********************************************************************************
 */

package com.stormy.lightningadditions.block.resource;

import com.stormy.lightningadditions.init.ModSounds;
import com.stormy.lightninglib.lib.utils.KeyChecker;
import com.stormy.lightninglib.lib.utils.TranslateUtils;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Items;
import net.minecraft.init.PotionTypes;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionUtils;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;

@SuppressWarnings("deprecation")
public class BlockWaterTank extends Block {

    private static AxisAlignedBB BOUNDING_BOX = new AxisAlignedBB(0.125, 0.0D, 0.125D, 0.875D, 0.875D, 0.875D);

    public BlockWaterTank() {
        super(Material.GLASS);
        setSoundType(SoundType.STONE);
        setHardness(1.0f);
        setHarvestLevel("pickaxe", 0);
    }

    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn,
                                    EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        ItemStack itemstack = playerIn.getHeldItemMainhand();
        playerIn.playSound(ModSounds.water_place, 2.0f, 1.0f);
        if (itemstack.isEmpty()) {
            return true; }

            else
                { Item item = playerIn.getHeldItemMainhand().getItem();
                    if (item == Items.BUCKET) {
                        itemstack.shrink(1);
                        if (itemstack.isEmpty()) {
                    playerIn.setHeldItem(hand, new ItemStack(Items.WATER_BUCKET));
                } else if (!playerIn.inventory.addItemStackToInventory(new ItemStack(Items.WATER_BUCKET))) {
                    playerIn.dropItem(new ItemStack(Items.WATER_BUCKET), false);
                }

                return true;
            } else if (item == Items.GLASS_BOTTLE) {

                itemstack.shrink(1);

                ItemStack itemstack1 = PotionUtils.addPotionToItemStack(new ItemStack(Items.POTIONITEM),
                        PotionTypes.WATER);

                if (itemstack.isEmpty()) {
                    playerIn.setHeldItem(hand, itemstack1);
                } else if (!playerIn.inventory.addItemStackToInventory(itemstack1)) {
                    playerIn.dropItem(itemstack1, false);
                } else if (playerIn instanceof EntityPlayerMP) {
                    ((EntityPlayerMP) playerIn).sendContainerToPlayer(playerIn.inventoryContainer);
                }

                return true;
            }
            return false;
        }

    }

    @Override
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos){
        return BOUNDING_BOX;
    }

    @Override
    public void addCollisionBoxToList(IBlockState state, World worldIn, BlockPos pos, AxisAlignedBB entityBox, List<AxisAlignedBB> collidingBoxes, @Nullable Entity entityIn, boolean p_185477_7_) {
        super.addCollisionBoxToList(pos, entityBox, collidingBoxes, BOUNDING_BOX);
    }

    @Override
    public boolean isFullCube(IBlockState state){
        return false;
    }

    @Override
    public boolean isOpaqueCube(IBlockState state){
        return false;
    }

    //Custom Tooltip
    @Override
    public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par3List, boolean par4) {
        if (KeyChecker.isHoldingShift()) {
            par3List.add(TextFormatting.AQUA + TranslateUtils.toLocal("tooltip.block.water_drum.line1"));
        } else {
            par3List.add(TranslateUtils.toLocal("tooltip.item.hold") + " " + TextFormatting.AQUA + TextFormatting.ITALIC + TranslateUtils.toLocal("tooltip.item.shift"));
        }
    }




}