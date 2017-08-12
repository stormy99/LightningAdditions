/*
 * ********************************************************************************
 * Copyright (c) 2017 StormyMode, MiningMark48. All Rights Reserved!
 * This file is part of Lightning Additions (MC-Mod).
 *
 * This project cannot be copied and/or distributed without the express
 * permission of StormyMode, MiningMark48 (Developers)!
 * ********************************************************************************
 */

package com.stormy.lightninglib.api.LAscrewdriver;

import net.minecraft.entity.Entity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

import javax.annotation.Nullable;

/**
 * Validation for what is being targeted by the screwdriver.
 */
public class SonicTarget
{
    private final RayTraceResult.Type type;
    private final Entity entity;
    private final BlockPos pos;
    private final EnumFacing side;
    private final World world;

    protected SonicTarget(RayTraceResult.Type type, World world, BlockPos pos, EnumFacing side, Entity entity) {
        this.type = type;
        this.entity = entity;
        this.pos = pos;
        this.side = side;
        this.world = world; }

    public RayTraceResult.Type getType() {return type;}
    public @Nullable Entity getEntity() {return entity;}
    public @Nullable BlockPos getPos() {return pos;}
    public @Nullable EnumFacing getSide() {return side;}
    public @Nullable World getWorld() {return world;}

    public static SonicTarget forEntity(Entity entity)
    {return new SonicTarget(RayTraceResult.Type.ENTITY, null, null, null, entity);}

    public static SonicTarget forNone()
    {return new SonicTarget(RayTraceResult.Type.MISS, null, null, null, null);}

    public static SonicTarget forBlock(World world, BlockPos pos, EnumFacing side)
    {return new SonicTarget(RayTraceResult.Type.BLOCK, world, pos, side, null);}


}
