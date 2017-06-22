/*
 * ********************************************************************************
 * Copyright (c) 2017 StormyMode, MiningMark48. All Rights Reserved!
 * This file is part of Lightning Additions (MC-Mod).
 *
 * This project cannot be copied and/or distributed without the express
 * permission of StormyMode, MiningMark48 (Developers)!
 * ********************************************************************************
 */

package com.stormy.lightningadditions.client.gui;

import com.stormy.lightningadditions.client.container.ContainerPlacer;
import com.stormy.lightningadditions.init.ModBlocks;
import com.stormy.lightningadditions.tile.TileEntityPlacer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;

public class GuiHandler implements IGuiHandler{

    public static int gui_id_placer = 0;

    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        BlockPos pos = new BlockPos(x, y, z);
        if (ID == gui_id_placer){
            return ID == gui_id_placer && world.getBlockState(new BlockPos(x, y, z)).getBlock() == ModBlocks.placer ? new ContainerPlacer(player.inventory, (TileEntityPlacer) world.getTileEntity(pos), player) : null;
        }
        return null;
    }

    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        BlockPos pos = new BlockPos(x, y, z);
        if (ID == gui_id_placer){
            return ID == gui_id_placer && world.getBlockState(new BlockPos(x, y, z)).getBlock() == ModBlocks.placer ? new GuiPlacer(player.inventory, (TileEntityPlacer) world.getTileEntity(pos), player) : null;
        }
        return null;
    }

}
