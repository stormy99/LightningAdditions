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

import com.stormy.lightningadditions.block.base.BlockConnectedTexture;
import com.stormy.lightningadditions.creativetab.CreativeTabLA;
import com.stormy.lightninglib.lib.utils.KeyChecker;
import com.stormy.lightninglib.lib.utils.TranslateUtils;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.boss.EntityWither;
import net.minecraft.entity.item.EntityTNTPrimed;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityWitherSkull;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.Explosion;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nonnull;
import java.util.List;
import java.util.Random;

@SuppressWarnings("deprecation")
public class BlockReinforcedGlass extends BlockConnectedTexture
{

    //Block Information
    public BlockReinforcedGlass() {
        super(Material.GLASS);
        this.setUnlocalizedName("reinforced_obsidianglass");
        this.setCreativeTab(CreativeTabLA.LA_TAB);
        this.setHardness(35.0F);
        this.setResistance(Integer.MAX_VALUE);
        this.setSoundType(SoundType.GLASS);
        this.setLightOpacity(0);
        this.setLightLevel(0.3F);
        this.setHarvestLevel("pickaxe", -1);
    }

    @Nonnull
    @Override
    @SideOnly(Side.CLIENT)
    public BlockRenderLayer getBlockLayer() {
        return BlockRenderLayer.CUTOUT;
    }

    @Override
    public boolean isFullCube(IBlockState state) {
        return false;
    }

    @Override
    public boolean isOpaqueCube(IBlockState state) {
        return false;
    }

    //Actually Wither Proof! -- prevents projectiles/tnt block destruction.
    public boolean canEntityDestroy(IBlockState state, IBlockAccess world, BlockPos pos, Entity entity) {
        return (!(entity instanceof EntityWither)) && (!(entity instanceof EntityWitherSkull)) && (!(entity instanceof EntityTNTPrimed));
    }

    public void onBlockExploded(World world, BlockPos pos, Explosion explosion) {
    }

    public boolean canDropFromExplosion(Explosion explosion) {
        return false;
    }

   public int quantityDropped(Random random) {
       return 1;
   }

    //Custom Tooltip
    @Override
    public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par3List, boolean par4) {
        if (KeyChecker.isHoldingShift()) {
            par3List.add(TranslateUtils.toLocal("tooltip.block.reinforced_obsidian.line1"));
            par3List.add(TextFormatting.ITALIC + TranslateUtils.toLocal("tooltip.block.reinforced_obsidian.line1.p2"));
        } else {
            par3List.add(TranslateUtils.toLocal("tooltip.item.hold") + " " + TextFormatting.AQUA + TextFormatting.ITALIC + TranslateUtils.toLocal("tooltip.item.shift"));
        }
    }
}
