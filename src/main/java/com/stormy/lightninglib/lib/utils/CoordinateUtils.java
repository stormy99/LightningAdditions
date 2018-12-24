package com.stormy.lightninglib.lib.utils;

import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;

public class CoordinateUtils implements Cloneable
{
    public final int x;
    public final int y;
    public final int z;

    public CoordinateUtils(int x, int y, int z)
    {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public CoordinateUtils(double x, double y, double z)
    {
        this.x = MathHelper.floor(x);
        this.y = MathHelper.floor(y);
        this.z = MathHelper.floor(z);
    }

    public CoordinateUtils(int[] coords) { this(coords[0], coords[1], coords[2]); }
    public CoordinateUtils(BlockPos pos) { this(pos.getX(), pos.getY(), pos.getZ()); }

    public CoordinateUtils(Vec3d vec) { this(vec.x, vec.y, vec.z); }
    public CoordinateUtils offset(int ox, int oy, int oz) { return new CoordinateUtils(x + ox, y + oy, z + oz); }

    @Override
    public int hashCode()
    { return (x + 128) << 16 | (y + 128) << 8 | (z + 128); }

    @Override
    public boolean equals(Object that)
    { if (!(that instanceof CoordinateUtils)) { return false; }
        CoordinateUtils otherCoord = (CoordinateUtils)that;
        return otherCoord.x == x && otherCoord.y == y && otherCoord.z == z; }

    @Override
    public String toString()
    { return String.format("%s,%s,%s", x, y, z); }

    @Override
    public CoordinateUtils clone()
    { return new CoordinateUtils(x, y, z); }

    public BlockPos asBlockPos()
    { return new BlockPos(x, y, z); }

    public Vec3d asVector()
    { return new Vec3d(x, y, z); }

    public CoordinateUtils add(CoordinateUtils other)
    { return new CoordinateUtils(x + other.x, y + other.y, z + other.z); }

    public CoordinateUtils substract(CoordinateUtils other)
    { return new CoordinateUtils(x - other.x, y - other.y, z - other.z); }

    public int lengthSq()
    { return x * x + y * y + z * z; }

    public double length()
    { return Math.sqrt(lengthSq()); }

    public boolean isAbove(CoordinateUtils pos)
    { return pos != null? y > pos.y : false; }

    public boolean isBelow(CoordinateUtils pos)
    { return pos != null? y < pos.y : false; }

    public boolean isNorthOf(CoordinateUtils pos)
    { return pos != null? z < pos.z : false; }

    public boolean isSouthOf(CoordinateUtils pos)
    { return pos != null? z > pos.z : false; }

    public boolean isEastOf(CoordinateUtils pos)
    { return pos != null? x > pos.x : false; }

    public boolean isWestOf(CoordinateUtils pos)
    { return pos != null? x < pos.x : false; }

    public boolean isXAligned(CoordinateUtils pos)
    { return pos != null? x == pos.x : false; }

    public boolean isYAligned(CoordinateUtils pos)
    { return pos != null? y == pos.y : false; }

    public boolean isZAligned(CoordinateUtils pos)
    { return pos != null? z == pos.z : false; }
}
