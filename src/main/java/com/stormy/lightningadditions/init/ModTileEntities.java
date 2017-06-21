package com.stormy.lightningadditions.init;

import com.stormy.lightningadditions.tile.TileEntitySharingXP;
import com.stormy.lightningadditions.tile.TileSoundMuffler;
import com.stormy.lightningadditions.tile.TileWaterTank;
import net.minecraftforge.fml.common.registry.GameRegistry;

public final class ModTileEntities {

    public static void init()
    {
        GameRegistry.registerTileEntity(TileEntitySharingXP.class, "share_xp");
        GameRegistry.registerTileEntity(TileSoundMuffler.class, "noise_muffler");
        GameRegistry.registerTileEntity(TileWaterTank.class, "water_tank");
    }

}
