package com.stormy.lightninglib.lib.utils;

public class UnitHelper
{
    public static enum SpeedUnit
    {
        M_PER_TICK("m/tick", 1, "%.2f %s"),
        M_PER_S("m/s", 1.0 * 20.0, "%.2f %s"),
        MI_PER_H("mph", 2.23694 * 20.0, "%.2f %s");

        public final String name;
        public final double factor;
        public final String format;

        private SpeedUnit(String name, double factor, String format)
        { this.name = name;
            this.factor = factor;
            this.format = format; }

        public String format(double value)
        { return String.format(format, value * factor, name); }
    }

    public static enum DistanceUnit
    {
        M("m", 1, "%.0f %s"),
        KM("km", 0.001, "%.2f %s"),
        MI("miles", 0.000621371, "%.f %s");

        public final String name;
        public final double factor;
        public final String format;

        private DistanceUnit(String name, double factor, String format)
        { this.name = name;
            this.factor = factor;
            this.format = format; }

        public String format(double value)
        { return String.format(format, value * factor, name); }
    }
}
