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

public class JsonParserModdedOres {

    private static JsonObject obj = JsonLoader.getJsonObject();

    private static JsonArray loadModOres(String type)
    {
        JsonObject modOres = obj.getAsJsonObject("Modded");

        return (JsonArray) modOres.get(type);
    }

    private static JsonArray loadOverworldOresModded()
    {
        return loadModOres("Overworld").getAsJsonArray();
    }

    private static JsonArray loadNetherOresModded()
    {
        return loadModOres("Nether").getAsJsonArray();
    }

    public static JsonObject loadOverworldCopperOre()
    {
        JsonArray array = loadOverworldOresModded();
        return array.get(0).getAsJsonObject();
    }

    public static JsonObject loadOverworldLeadOre()
    {
        JsonArray array = loadOverworldOresModded();
        return array.get(1).getAsJsonObject();
    }

    public static JsonObject loadOverworldSilverOre()
    {
        JsonArray array = loadOverworldOresModded();
        return array.get(2).getAsJsonObject();
    }

    public static JsonObject loadOverworldTinOre()
    {
        JsonArray array = loadOverworldOresModded();
        return array.get(3).getAsJsonObject();
    }

    public static JsonObject loadNetherCopperOre()
    {
        JsonArray array = loadNetherOresModded();
        return array.get(0).getAsJsonObject();
    }

    public static JsonObject loadNetherLeadOre()
    {
        JsonArray array = loadNetherOresModded();
        return array.get(1).getAsJsonObject();
    }

    public static JsonObject loadNetherSilverOre()
    {
        JsonArray array = loadNetherOresModded();
        return array.get(2).getAsJsonObject();
    }

    public static JsonObject loadNetherTinOre()
    {
        JsonArray array = loadNetherOresModded();
        return array.get(3).getAsJsonObject();
    }

}
