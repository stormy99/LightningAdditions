package com.stormy.lightningadditions.client.lightchunkutil;

import java.util.List;

import static com.stormy.lightningadditions.client.lightchunkutil.ConfigHandler.config;


public class Config {

    public static int light_UpRange;
    public static int light_DownRange;
    public static int light_HRange;
    public static boolean light_IgnoreLayer;
    public static int light_SaveLevel;
    public static int chunk_EdgeRadius;
    public static boolean chunk_ShowMiddle;
    public static int render_chunkEdgeColor;
    public static int render_chunkGridColor;
    public static int render_chunkMiddleColor;
    public static float render_chunkLineWidth;
    public static int render_spawnAColor;
    public static int render_spawnNColor;
    public static float render_spawnLineWidth;

    public static void loadValues()
    {

        config.setCategoryComment("lightoverlay","Settings for Light Overlay");
        light_UpRange = config.get("lightoverlay","uprange",4,"Range of the Light (Y-axis pos)").getInt();
        light_DownRange =  config.get("lightoverlay","downrange",16,"Range of the Light (Y-axis neg)").getInt();
        light_HRange =  config.get("lightoverlay","hrange",16,"Range of the Ligt (Horizontal Compass)").getInt();
        light_IgnoreLayer =  config.get("lightoverlay","ignoreLayer", false,"Ignore if 2 Block Space(Less lag if true)").getBoolean();
        light_SaveLevel =  config.get("lightoverlay","saveLevel", 8,"Minimum safe light level - No hostile entity spawning").getInt();
        config.setCategoryComment("chunkbounds","Settings for Chunk Boundaries");
        chunk_EdgeRadius = config.get("chunkbounds","radius", 1, "Chunk Radius to show edges (red lines)").getInt();
        chunk_ShowMiddle = config.get("chunkbounds","middle", true, "Middle of chunk (yellow lines)").getBoolean();
        config.setCategoryComment("rendersettings","Settings for lines & colors\nValues: 0xRRGGBB (Hex)");
        render_chunkEdgeColor = config.get("rendersettings","cedgecolor", 0xFF0000, "Color for the c-edge").getInt();
        render_chunkGridColor = config.get("rendersettings","cgridcolor", 0x00FF00, "Color for the c-grid").getInt();
        render_chunkMiddleColor = config.get("rendersettings","cmidcolor", 0xFFFF00, "Color for the middle c-line").getInt();
        render_chunkLineWidth = (float) config.get("rendersettings","clwidth", 1.5, "Line width for c-boundaries").getDouble();
        render_spawnAColor = config.get("rendersettings","sacolor", 0xFF0000, "Color the X that marks \"Spawns always possible\"").getInt();
        render_spawnNColor = config.get("rendersettings","sncolor", 0xFFFF00, "Color the X that marks \"Spawns at night possible\"").getInt();
        render_spawnLineWidth = (float) config.get("rendersettings","slwidth", 2 , "Line width for spawn indication").getDouble();
        if(config.hasChanged())
            config.save();
    }

    public static void getCategories(List<String> list)
    { list.add("lightoverlay"); list.add("chunkbounds"); list.add("rendersettings");
    }
}