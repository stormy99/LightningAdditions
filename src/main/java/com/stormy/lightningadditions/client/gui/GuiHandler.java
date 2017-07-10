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

import com.stormy.lightningadditions.client.container.*;
import com.stormy.lightningadditions.client.container.generator.*;
import com.stormy.lightningadditions.client.gui.generator.*;
import com.stormy.lightningadditions.init.ModBlocks;
import com.stormy.lightningadditions.tile.*;
import com.stormy.lightningadditions.tile.generator.*;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;

public class GuiHandler implements IGuiHandler{

    public static int gui_id_placer = 0;
    public static int gui_id_magnetized_chest = 1;
    public static int gui_id_trash_can = 2;

    public static int gui_id_solar_generator = 20;
    public static int gui_id_fuel_generator = 21;
    public static int gui_id_biofuel_generator = 22;

    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        BlockPos pos = new BlockPos(x, y, z);
        if (ID == gui_id_placer){
            return world.getBlockState(new BlockPos(x, y, z)).getBlock() == ModBlocks.placer ? new ContainerPlacer(player.inventory, (TileEntityPlacer) world.getTileEntity(pos), player) : null;
        }
        if (ID == gui_id_magnetized_chest){
            return world.getBlockState(new BlockPos(x, y, z)).getBlock() == ModBlocks.magnetized_chest ? new ContainerMagnetizedChest(player.inventory, (TileEntityMagnetizedChest) world.getTileEntity(pos), player) : null;
        }
        if (ID == gui_id_trash_can){
            return world.getBlockState(new BlockPos(x, y, z)).getBlock() == ModBlocks.trash_can ? new ContainerTrashCan(player.inventory, (TileEntityTrashCan) world.getTileEntity(pos), player) : null;
        }
        if (ID == gui_id_solar_generator){
            return world.getBlockState(new BlockPos(x, y, z)).getBlock() == ModBlocks.solar_generator ? new ContainerSolarGenerator(player.inventory, (TileEntitySolarGenerator) world.getTileEntity(pos)) : null;
        }
        if (ID == gui_id_fuel_generator){
            return world.getBlockState(new BlockPos(x, y, z)).getBlock() == ModBlocks.fuel_generator ? new ContainerFuelGenerator(player.inventory, (TileEntityFuelGenerator) world.getTileEntity(pos)) : null;
        }
        if (ID == gui_id_biofuel_generator){
            return world.getBlockState(new BlockPos(x, y, z)).getBlock() == ModBlocks.biofuel_generator ? new ContainerBioFuelGenerator(player.inventory, (TileEntityBioFuelGenerator) world.getTileEntity(pos)) : null;
        }
        return null;
    }

    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        BlockPos pos = new BlockPos(x, y, z);
        if (ID == gui_id_placer){
            return world.getBlockState(new BlockPos(x, y, z)).getBlock() == ModBlocks.placer ? new GuiPlacer(player.inventory, (TileEntityPlacer) world.getTileEntity(pos), player) : null;
        }
        if (ID == gui_id_magnetized_chest){
            return world.getBlockState(new BlockPos(x, y, z)).getBlock() == ModBlocks.magnetized_chest ? new GuiMagnetizedChest(player.inventory, (TileEntityMagnetizedChest) world.getTileEntity(pos), player) : null;
        }
        if (ID == gui_id_trash_can){
            return world.getBlockState(new BlockPos(x, y, z)).getBlock() == ModBlocks.trash_can ? new GuiTrashCan(player.inventory, (TileEntityTrashCan) world.getTileEntity(pos), player) : null;
        }
        if (ID == gui_id_solar_generator){
            return world.getBlockState(new BlockPos(x, y, z)).getBlock() == ModBlocks.solar_generator ? new GuiSolarGenerator(player.inventory, (TileEntitySolarGenerator) world.getTileEntity(pos)) : null;
        }
        if (ID == gui_id_fuel_generator){
            return world.getBlockState(new BlockPos(x, y, z)).getBlock() == ModBlocks.fuel_generator ? new GuiFuelGenerator(player.inventory, (TileEntityFuelGenerator) world.getTileEntity(pos)) : null;
        }
        if (ID == gui_id_biofuel_generator){
            return world.getBlockState(new BlockPos(x, y, z)).getBlock() == ModBlocks.biofuel_generator ? new GuiBioFuelGenerator(player.inventory, (TileEntityBioFuelGenerator) world.getTileEntity(pos)) : null;
        }
        return null;
    }

}
