/*
 *
 *  * ********************************************************************************
 *  * Copyright (c) 2017 StormyMode, MiningMark48. All Rights Reserved!
 *  * This file is part of Lightning Additions (MC-Mod).
 *  *
 *  * This project cannot be copied and/or distributed without the express
 *  * permission of StormyMode, MiningMark48 (Developers)!
 *  * ********************************************************************************
 *
 */

package com.stormy.lightningadditions.tile.renderer;

import com.stormy.lightningadditions.tile.resource.TileEntitySoundMuffler;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;

public class TileEntityRendererSoundMuffler extends TileEntitySpecialRenderer<TileEntitySoundMuffler>{

    @Override
    public void renderTileEntityAt(TileEntitySoundMuffler tile, double x, double y, double z, float f, int destroyStage){
        GlStateManager.pushMatrix();

        GlStateManager.popMatrix();
    }
}
