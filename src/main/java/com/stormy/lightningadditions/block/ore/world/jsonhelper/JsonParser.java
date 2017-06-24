/*
 * ********************************************************************************
 * Copyright (c) 2017 StormyMode, MiningMark48. All Rights Reserved!
 * This file is part of Lightning Additions (MC-Mod).
 *
 * This project cannot be copied and/or distributed without the express
 * permission of StormyMode, MiningMark48 (Developers)!
 * ********************************************************************************
 */

package com.stormy.lightningadditions.block.ore.world.jsonhelper;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

public class JsonParser
{
    static JsonObject obj = JsonLoader.getJsonObject();

    public static JsonArray loadModOres(int place)
    {
        JsonArray modOres = obj.getAsJsonArray("basic");
        return (JsonArray) modOres.get(place);
    }

    public static JsonArray loadOverworldOres()
    {
        return loadModOres(1);
    }

    public static JsonObject loadOverworldCoalOre()
    {
        JsonArray array = loadOverworldOres();
        return (JsonObject) array.get(1);
    }

    public static JsonObject loadOverworldDiamondOre()
    {
        JsonArray array = loadOverworldOres();
        return (JsonObject) array.get(2);
    }

    public static JsonObject loadOverworldEmeraldOre()
    {
        JsonArray array = loadOverworldOres();
        return (JsonObject) array.get(3);
    }

    public static JsonObject loadOverworldGoldOre()
    {
        JsonArray array = loadOverworldOres();
        return (JsonObject) array.get(4);
    }

    public static JsonObject loadOverworldIronOre()
    {
        JsonArray array = loadOverworldOres();
        return (JsonObject) array.get(5);
    }

    public static JsonObject loadOverworldLapisOre()
    {
        JsonArray array = loadOverworldOres();
        return (JsonObject) array.get(6);
    }

    public static JsonObject loadOverworldRedstoneOre()
    {
        JsonArray array = loadOverworldOres();
        return (JsonObject) array.get(7);
    }

    // --------------------------------------- End Surface Ores -------------------------------------------------- \\

    public static JsonArray loadNetherOres()
    {
        return loadModOres(2);
    }

    public static JsonObject loadNetherCoalOre()
    {
        JsonArray array = loadNetherOres();
        return (JsonObject) array.get(0);
    }

    public static JsonObject loadNetherDiamondOre()
    {
        JsonArray array = loadNetherOres();
        return (JsonObject) array.get(1);
    }

    public static JsonObject loadNetherEmeraldOre()
    {
        JsonArray array = loadNetherOres();
        return (JsonObject) array.get(2);
    }

    public static JsonObject loadNetherGoldOre()
    {
        JsonArray array = loadNetherOres();
        return (JsonObject) array.get(3);
    }

    public static JsonObject loadNetherIronOre()
    {
        JsonArray array = loadNetherOres();
        return (JsonObject) array.get(4);
    }

    public static JsonObject loadNetherLapisOre()
    {
        JsonArray array = loadNetherOres();
        return (JsonObject) array.get(5);
    }

    public static JsonObject loadNetherRedstoneOre()
    {
        JsonArray array = loadNetherOres();
        return (JsonObject) array.get(6);
    }


    // --------------------------------------- End Nether Ores -------------------------------------------------- \\
}
