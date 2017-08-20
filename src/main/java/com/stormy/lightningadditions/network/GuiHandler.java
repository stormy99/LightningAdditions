/*
 * *******************************************************************************
 * Copyright (c) 2017 StormyMode, MiningMark48. All Rights Reserved!
 * This file is part of Lightning Additions (MC-Mod).
 *
 * This project cannot be copied and/or distributed without the express
 * permission of StormyMode, MiningMark48 (Developers)!
 * *******************************************************************************
 */

package com.stormy.lightningadditions.network;

import com.stormy.lightningadditions.client.gui.resource.*;
import com.stormy.lightningadditions.client.gui.generator.GuiBioFuelGenerator;
import com.stormy.lightningadditions.client.gui.generator.GuiFuelGenerator;
import com.stormy.lightningadditions.client.gui.generator.GuiSolarGenerator;
import com.stormy.lightningadditions.container.resource.*;
import com.stormy.lightningadditions.container.generator.ContainerBioFuelGenerator;
import com.stormy.lightningadditions.container.generator.ContainerFuelGenerator;
import com.stormy.lightningadditions.container.generator.ContainerSolarGenerator;
import com.stormy.lightningadditions.network.packet.PacketHandler;
import com.stormy.lightningadditions.tile.resource.*;
import com.stormy.lightningadditions.tile.generator.TileEntityBioFuelGenerator;
import com.stormy.lightningadditions.tile.generator.TileEntityFuelGenerator;
import com.stormy.lightningadditions.tile.generator.TileEntitySolarGenerator;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;

//it's not client only, so dont put it in the client package kthx <3
public class GuiHandler implements IGuiHandler{

    public static int gui_id_base = 0;
    public static int gui_id_placer = gui_id_base + 0;
    public static int gui_id_magnetized_chest = gui_id_base + 1;
    public static int gui_id_trash_can = gui_id_base + 2;
    public static int gui_id_solar_generator = gui_id_base + 3;
    public static int gui_id_fuel_generator = gui_id_base + 4;
    public static int gui_id_biofuel_generator = gui_id_base + 5;
    public static int gui_id_particle_accelerator = gui_id_base + 6;
    public static int gui_id_redstoneClock = gui_id_base + 7;

    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        BlockPos pos = new BlockPos(x, y, z);
        TileEntity te = world.getTileEntity(new BlockPos(x, y, z));


        if (ID == gui_id_placer){
            return new ContainerPlacer(player.inventory, (TileEntityPlacer) world.getTileEntity(pos), player);
        }
        else if (ID == gui_id_magnetized_chest){
            return new ContainerMagnetizedChest(player.inventory, (TileEntityMagnetizedChest) world.getTileEntity(pos), player);
        }
        else if (ID == gui_id_trash_can){
            return new ContainerTrashCan(player.inventory, (TileEntityTrashCan) world.getTileEntity(pos), player);
        }
        else if (ID == gui_id_solar_generator){
            return new ContainerSolarGenerator(player.inventory, (TileEntitySolarGenerator) world.getTileEntity(pos));
        }
        else if (ID == gui_id_fuel_generator){
            return new ContainerFuelGenerator(player.inventory, (TileEntityFuelGenerator) world.getTileEntity(pos));
        }
        else if (ID == gui_id_biofuel_generator){
            return new ContainerBioFuelGenerator(player.inventory, (TileEntityBioFuelGenerator) world.getTileEntity(pos));
        }
        else if(ID == gui_id_particle_accelerator){
            return new ContainerParticleAccelerator(player.inventory, (TileEntityParticleAccelerator)world.getTileEntity(pos));
        }

        return null;
    }

    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        BlockPos pos = new BlockPos(x, y, z);
        TileEntity te = world.getTileEntity(new BlockPos(x, y, z));

        if (ID == gui_id_placer){
            return new GuiPlacer(player.inventory, (TileEntityPlacer) world.getTileEntity(pos), player);
        }
        else if (ID == gui_id_magnetized_chest){
            return new GuiMagnetizedChest(player.inventory, (TileEntityMagnetizedChest) world.getTileEntity(pos), player);
        }
        else if (ID == gui_id_trash_can){
            return new GuiTrashCan(player.inventory, (TileEntityTrashCan) world.getTileEntity(pos), player);
        }
        else if (ID == gui_id_solar_generator){
            return new GuiSolarGenerator(player.inventory, (TileEntitySolarGenerator) world.getTileEntity(pos));
        }
        else if (ID == gui_id_fuel_generator){
            return new GuiFuelGenerator(player.inventory, (TileEntityFuelGenerator) world.getTileEntity(pos));
        }
        else if (ID == gui_id_biofuel_generator){
            return new GuiBioFuelGenerator(player.inventory, (TileEntityBioFuelGenerator) world.getTileEntity(pos));
        }
        else if(ID == gui_id_particle_accelerator){
            return new GuiParticleAccelerator(player.inventory, (TileEntityParticleAccelerator)world.getTileEntity(pos));
        }

        return null;
    }

}
