/*
 * ********************************************************************************
 * Copyright (c) 2017 StormyMode, MiningMark48. All Rights Reserved!
 * This file is part of Lightning Additions (MC-Mod).
 *
 * This project cannot be copied and/or distributed without the express
 * permission of StormyMode, MiningMark48 (Developers)!
 * ********************************************************************************
 */

package com.stormy.lightningadditions.world.jsonhelper;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

public class JsonParserVanillaOres
{
    private static JsonObject obj = JsonLoader.getJsonObject();

    private static JsonArray loadModOres(String type)
    {
        JsonObject modOres = obj.getAsJsonObject("basic");

        return (JsonArray) modOres.get(type);
    }

    private static JsonArray loadOverworldOresVanilla()
    {
        return loadModOres("OverworldVanilla").getAsJsonArray();
    }

    private static JsonArray loadNetherOresVanilla()
    {
        return loadModOres("NetherVanilla").getAsJsonArray();
    }

    public static JsonObject loadOverworldCoalOre()
    {
        JsonArray array = loadOverworldOresVanilla();
        return array.get(0).getAsJsonObject();
    }

    public static JsonObject loadOverworldDiamondOre()
    {
        JsonArray array = loadOverworldOresVanilla();
        return array.get(1).getAsJsonObject();
    }

    public static JsonObject loadOverworldEmeraldOre()
    {
        JsonArray array = loadOverworldOresVanilla();
        return array.get(2).getAsJsonObject();
    }

    public static JsonObject loadOverworldGoldOre()
    {
        JsonArray array = loadOverworldOresVanilla();
        return array.get(3).getAsJsonObject();
    }

    public static JsonObject loadOverworldIronOre()
    {
        JsonArray array = loadOverworldOresVanilla();
        return array.get(4).getAsJsonObject();
    }

    public static JsonObject loadOverworldLapisOre()
    {
        JsonArray array = loadOverworldOresVanilla();
        return array.get(5).getAsJsonObject();
    }

    public static JsonObject loadOverworldRedstoneOre()
    {
        JsonArray array = loadOverworldOresVanilla();
        return array.get(6).getAsJsonObject();
    }

    public static JsonObject loadNetherCoalOre()
    {
        JsonArray array = loadNetherOresVanilla();
        return array.get(0).getAsJsonObject();
    }

    public static JsonObject loadNetherDiamondOre()
    {
        JsonArray array = loadNetherOresVanilla();
        return array.get(1).getAsJsonObject();
    }

    public static JsonObject loadNetherEmeraldOre()
    {
        JsonArray array = loadNetherOresVanilla();
        return array.get(2).getAsJsonObject();
    }

    public static JsonObject loadNetherGoldOre()
    {
        JsonArray array = loadNetherOresVanilla();
        return array.get(3).getAsJsonObject();
    }

    public static JsonObject loadNetherIronOre()
    {
        JsonArray array = loadNetherOresVanilla();
        return array.get(4).getAsJsonObject();
    }

    public static JsonObject loadNetherLapisOre()
    {
        JsonArray array = loadNetherOresVanilla();
        return array.get(5).getAsJsonObject();
    }

    public static JsonObject loadNetherRedstoneOre()
    {
        JsonArray array = loadNetherOresVanilla();
        return array.get(6).getAsJsonObject();
    }


}
