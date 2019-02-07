package com.stormy.lightningadditions.tile.resource;

import com.stormy.lightningadditions.config.ConfigValues;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;

public class TileEntityCompressedBase extends TileEntity implements ITickable {

    private final int default_preview_time = ConfigValues.getPreviewRenderTime(); //seconds
    private int preview_time = default_preview_time * 20; //seconds * game ticks
    private boolean doTimer = false;

    public TileEntityCompressedBase() {

    }

    public void startPreview() {
        this.getTileData().setBoolean("show_preview", true);
        doTimer = true;
        this.markDirty();
    }

    @Override
    public void update() {
        if (doTimer) {
            preview_time--;
            if (preview_time == 0) {
                this.getTileData().setBoolean("show_preview", false);
                preview_time = default_preview_time * 20;
                doTimer = false;
                this.markDirty();
            }
        }
    }
}