package com.stormy.lightningadditions.init;

import com.stormy.lightningadditions.tile.TileEntitySharingXP;
import com.stormy.lightningadditions.tile.TileEntityTube;
import com.stormy.lightningadditions.tile.TileSoundMuffler;
import net.minecraftforge.fml.common.registry.GameRegistry;

public final class ModTileEntities {

    public static void init()
    {
        GameRegistry.registerTileEntity(TileEntityTube.class, "tileentitytube");
        GameRegistry.registerTileEntity(TileEntitySharingXP.class, "share_xp");
        GameRegistry.registerTileEntity(TileSoundMuffler.class, "noise_muffler");
    }

}
