package com.stormy.lightningadditions.tile.renderer;

import com.stormy.lightningadditions.block.resource.BlockCompressedBase;
import com.stormy.lightningadditions.client.RenderPreview;
import com.stormy.lightningadditions.tile.resource.TileEntityCompressedBase;

import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;

public class TESRCompressedBase extends TileEntitySpecialRenderer<TileEntityCompressedBase> {

    public void func_192841_a(TileEntityCompressedBase te, double x, double y, double z, float p_192841_8_, int p_192841_9_, float p_192841_10_) {
        if (te != null) {
            if (!te.getTileData().hasKey("show_preview")) {
                te.getTileData().setBoolean("show_preview", false);
            }

            if (te.getTileData().getBoolean("show_preview")) {
                RenderPreview.doRender(te, 0, 0);

                if (BlockCompressedBase.isCompressedBaseExtension(te.getWorld(), te.getPos().north())) RenderPreview.doRender(te, 0, -12);    //North render
                if (BlockCompressedBase.isCompressedBaseExtension(te.getWorld(), te.getPos().south())) RenderPreview.doRender(te, 0, 12);     //South render
                if (BlockCompressedBase.isCompressedBaseExtension(te.getWorld(), te.getPos().east())) RenderPreview.doRender(te, 12, 0);      //East render
                if (BlockCompressedBase.isCompressedBaseExtension(te.getWorld(), te.getPos().west())) RenderPreview.doRender(te, -12, 0);     //West render

            }
        }

    }

}
