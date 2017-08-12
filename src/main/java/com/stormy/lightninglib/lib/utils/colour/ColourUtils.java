package com.stormy.lightninglib.lib.utils.colour;

import com.google.common.base.Preconditions;

public class ColourUtils
{
    public static int bitmaskToVanilla(int color) {
        int high = Integer.numberOfLeadingZeros(color);
        int low = Integer.numberOfTrailingZeros(color);
        Preconditions.checkArgument(high == 31 - low && low <= 16, "Invalid color value: %sb", Integer.toBinaryString(color));
        return low;
    }

    public static ColourMeta findNearestColor(RGB target, int tolerance) {
        ColourMeta result = null;
        int distSq = Integer.MAX_VALUE;

        for (ColourMeta meta : ColourMeta.VALUES) {
            final int currentDistSq = meta.rgbWrap.distance(target);
            if (currentDistSq < distSq) {
                result = meta;
                distSq = currentDistSq;
            }
        }

        return (distSq < 3 * tolerance * tolerance)? result : null;
    }
}
