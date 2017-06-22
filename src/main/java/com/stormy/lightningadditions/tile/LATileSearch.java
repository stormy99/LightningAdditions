/*
 * ********************************************************************************
 * Copyright (c) 2017 StormyMode, MiningMark48. All Rights Reserved!
 * This file is part of Lightning Additions (MC-Mod).
 *
 * This project cannot be copied and/or distributed without the express
 * permission of StormyMode, MiningMark48 (Developers)!
 * ********************************************************************************
 */

package com.stormy.lightningadditions.tile;

import com.google.common.collect.Lists;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;

import java.util.List;
import java.util.Map;

public class LATileSearch {

    public static <T extends TileEntity> List<T> searchAABBForTiles(World world, AxisAlignedBB area, Class<T> tileClazz, boolean firstOnly, List<T> list) {
        int x0 = (int) Math.floor(area.minX) >> 4;
        int x1 = (int) Math.ceil(area.maxX) >> 4;
        int z0 = (int) Math.floor(area.minZ) >> 4;
        int z1 = (int) Math.ceil(area.maxZ) >> 4;
        if (list == null) {
            list = Lists.newArrayList();
        }
        for (int x = x0; x <= x1; x++) {
            for (int z = z0; z <= z1; z++) {
                Chunk chunk = world.getChunkFromChunkCoords(x, z);
                for (Map.Entry<BlockPos, TileEntity> entry : chunk.getTileEntityMap().entrySet()) {
                    BlockPos pos = (BlockPos) entry.getKey();
                    if ((tileClazz == ((TileEntity) entry.getValue()).getClass()) && (area.isVecInside(new Vec3d(pos.getX() + 0.5D, pos.getY() + 0.5D, pos.getZ() + 0.5D)))) {
                        list.add((T) entry.getValue());
                        if (firstOnly) {
                            return list;
                        }
                    }
                }
            }
        }
        return list;
    }
}
