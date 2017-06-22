/*
 * ********************************************************************************
 * Copyright (c) 2017 StormyMode, MiningMark48. All Rights Reserved!
 * This file is part of Lightning Additions (MC-Mod).
 *
 * This project cannot be copied and/or distributed without the express
 * permission of StormyMode, MiningMark48 (Developers)!
 * ********************************************************************************
 */

package com.stormy.lightningadditions.block;

import java.util.List;
import javax.annotation.Nonnull;

import com.stormy.lightningadditions.reference.KeyChecker;
import com.stormy.lightningadditions.reference.Translate;
import com.stormy.lightningadditions.sounds.SoundMuffler;
import com.stormy.lightningadditions.tile.LATileSearch;
import com.stormy.lightningadditions.tile.TileSoundMuffler;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.ISound;
import net.minecraft.client.audio.ITickableSound;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.client.event.sound.PlaySoundEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockSoundMuffler
        extends Block
{
    public BlockSoundMuffler()
    {
        super(Material.CLOTH);
        MinecraftForge.EVENT_BUS.register(this);
    }

    public String getTexture(IBlockState state, EnumFacing side)
    {
        return "noise_muffler";
    }

    public boolean hasTileEntity(IBlockState state)
    {
        return true;
    }

    @Nonnull
    public TileEntity createTileEntity(@Nonnull World world, @Nonnull IBlockState state)
    {
        return new TileSoundMuffler();
    }

    @SubscribeEvent
    @SideOnly(Side.CLIENT)
    public void supressSound(PlaySoundEvent event)
    {
        WorldClient theWorld = Minecraft.getMinecraft().world;
        if (theWorld == null) {
            return;
        }
        ISound sound = event.getSound();
        if ((sound instanceof ITickableSound)) {
            return;
        }
        AxisAlignedBB expand = new AxisAlignedBB(sound.getXPosF(), sound.getYPosF(), sound.getZPosF(), sound.getXPosF(), sound.getYPosF(), sound.getZPosF()).expand(8.0D, 8.0D, 8.0D);

        List<TileSoundMuffler> tileSoundMufflers = LATileSearch.searchAABBForTiles(theWorld, expand, TileSoundMuffler.class, true, null);
        if (tileSoundMufflers.isEmpty()) {
            return;
        }
        float volume = 0.05F;

        event.setResultSound(new SoundMuffler(sound, volume));
    }

    //Custom Tooltip
    @Override
    public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par3List, boolean par4) {
        if (KeyChecker.isHoldingShift()) {
            par3List.add(TextFormatting.LIGHT_PURPLE + Translate.toLocal("tooltip.block.noise_muffler.line1"));
        } else {
            par3List.add(Translate.toLocal("tooltip.item.hold") + " " + TextFormatting.AQUA + TextFormatting.ITALIC + Translate.toLocal("tooltip.item.shift"));
        }
    }
}
