package com.stormy.lightningadditions.init;

import com.stormy.lightningadditions.tile.TileEntityPlacer;
import com.stormy.lightningadditions.tile.TileEnderHopper;
import com.stormy.lightningadditions.tile.TileEntitySharingXP;
import com.stormy.lightningadditions.tile.TileSoundMuffler;
import net.minecraftforge.fml.common.registry.GameRegistry;

public final class ModTileEntities {

    public static void init()
    {
        GameRegistry.registerTileEntity(TileEntitySharingXP.class, "share_xp");
        GameRegistry.registerTileEntity(TileSoundMuffler.class, "noise_muffler");
        GameRegistry.registerTileEntity(TileEntityPlacer.class, "placer");
        GameRegistry.registerTileEntity(TileEnderHopper.class, "ender_hopper");
    }

}
