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
import com.google.gson.JsonObject;

public class JsonLoaderForestryBlocks
{
    static JsonObject obj = JsonLoader.getJsonObject();

    public static JsonArray loadModOres()
    {
        JsonArray modOres = obj.getAsJsonArray("forestry");
        return modOres;
    }
}
