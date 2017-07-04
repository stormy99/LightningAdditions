/*
 * ********************************************************************************
 * Copyright (c) 2017 StormyMode, MiningMark48. All Rights Reserved!
 * This file is part of Lightning Additions (MC-Mod).
 *
 * This project cannot be copied and/or distributed without the express
 * permission of StormyMode, MiningMark48 (Developers)!
 * ********************************************************************************
 */

package com.stormy.lightningadditions.init;

import com.stormy.lightningadditions.tile.*;
import com.stormy.lightningadditions.tile.generator.TileEntityFuelGenerator;
import com.stormy.lightningadditions.tile.generator.TileEntitySolarGenerator;
import net.minecraftforge.fml.common.registry.GameRegistry;

public final class ModTileEntities {

    public static void init()
    {
        GameRegistry.registerTileEntity(TileEntitySky.class, "sky_block");
        GameRegistry.registerTileEntity(TileEntitySharingXP.class, "share_xp");
        GameRegistry.registerTileEntity(TileSoundMuffler.class, "noise_muffler");
        GameRegistry.registerTileEntity(TileEntityPlacer.class, "placer");
        GameRegistry.registerTileEntity(TileEnderHopper.class, "ender_hopper");
        GameRegistry.registerTileEntity(TileEntityMagnetizedChest.class, "magnetized_chest");
        GameRegistry.registerTileEntity(TileEntityTrashCan.class, "trash_can");
        GameRegistry.registerTileEntity(TileEntitySolarGenerator.class, "solar_generator");
        GameRegistry.registerTileEntity(TileEntityFuelGenerator.class, "fuel_generator");
    }

}
