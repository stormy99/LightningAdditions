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

public class JsonNeutralModBlocks
{
    static JsonObject obj = JsonLoader.getJsonObject();

    public static JsonArray loadModOres()
    {
        JsonArray modOres = obj.getAsJsonArray("neutral");
        return modOres;
    }

    public static JsonObject loadOverworldCopper()
    {
        JsonArray array = loadModOres();
        return (JsonObject)array.get(0);
    }

    public static JsonObject loadOverworldTin()
    {
        JsonArray array = loadModOres();
        return (JsonObject)array.get(1);
    }

    public static JsonObject loadOverworldLead()
    {
        JsonArray array = loadModOres();
        return (JsonObject)array.get(2);
    }

    public static JsonObject loadNetherCopper()
    {
        JsonArray array = loadModOres();
        return (JsonObject)array.get(3);
    }

    public static JsonObject loadNetherTin()
    {
        JsonArray array = loadModOres();
        return (JsonObject)array.get(4);
    }

    public static JsonObject loadNetherLead()
    {
        JsonArray array = loadModOres();
        return (JsonObject)array.get(5);
    }

    public static JsonObject loadNetherSilver()
    {
        JsonArray array = loadModOres();
        return (JsonObject)array.get(6);
    }

    public static JsonObject loadOverworldSilver()
    {
        JsonArray array = loadModOres();
        return (JsonObject)array.get(7);
    }

}