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

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.nio.file.Path;
import java.nio.file.Paths;
import com.google.common.io.Files;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.stormy.lightningadditions.reference.ModInformation;
import com.stormy.lightningadditions.utility.logger.LALogger;

public class JsonCreator
{
    static File jsonFile = new File(ModInformation.LOCATION + "/OreGen.json");
    static File updateFile = new File(ModInformation.LOCATION + "/OreGen.json.bak");
    static boolean runOnce = true;

    public static String createJson()
    {
        int surfaceMinY = 0;

        int netherMinY = 0;
        int netherMaxY = 126;

        int endMinY = 10;
        int endMaxY = 60;

        // The holder object
        JsonObject root = new JsonObject();

        // Holder array
        JsonArray basic = new JsonArray();

        // Vanilla Array
        JsonArray vanilla = new JsonArray();

        // Neutral Array
        JsonArray neutral = new JsonArray();

        // Forestry Array
        JsonArray forestry = new JsonArray();

        // IC2 Array
        JsonArray ic = new JsonArray();

        // vanillaCoalOre
        JsonObject vCoalOre = new JsonObject();

        vCoalOre.addProperty("ore", "SurfaceCoalOre");
        vCoalOre.addProperty("minY", surfaceMinY);
        vCoalOre.addProperty("maxY", 213);
        vCoalOre.addProperty("rarity", 12 * 2);
        vCoalOre.addProperty("veinMinimum", 6);
        vCoalOre.addProperty("veinMultiplier", 31);
        vCoalOre.addProperty("disableOre", false);

        vanilla.add(vCoalOre);

        // vanillaDiamondOre
        JsonObject vDiamondOre = new JsonObject();

        vDiamondOre.addProperty("ore", "SurfaceDiamondOre");
        vDiamondOre.addProperty("minY", surfaceMinY);
        vDiamondOre.addProperty("maxY", 21);
        vDiamondOre.addProperty("rarity", 2 * 2);
        vDiamondOre.addProperty("veinMinimum", 3);
        vDiamondOre.addProperty("veinMultiplier", 6);
        vDiamondOre.addProperty("disableOre", false);

        vanilla.add(vDiamondOre);

        // vanillaEmeraldOre
        JsonObject vEmeraldOre = new JsonObject();

        vEmeraldOre.addProperty("ore", "SurfaceEmeraldOre");
        vEmeraldOre.addProperty("minY", surfaceMinY);
        vEmeraldOre.addProperty("maxY", 33);
        vEmeraldOre.addProperty("rarity", 4 * 2);
        vEmeraldOre.addProperty("veinMinimum", 6);
        vEmeraldOre.addProperty("veinMultiplier", 6);
        vEmeraldOre.addProperty("disableOre", false);

        vanilla.add(vEmeraldOre);

        // vanillaGoldOre
        JsonObject vGoldOre = new JsonObject();

        vGoldOre.addProperty("ore", "SurfaceGoldOre");
        vGoldOre.addProperty("minY", surfaceMinY);
        vGoldOre.addProperty("maxY", 34);
        vGoldOre.addProperty("rarity", 6 * 2);
        vGoldOre.addProperty("veinMinimum", 9);
        vGoldOre.addProperty("veinMultiplier", 9);
        vGoldOre.addProperty("disableOre", false);

        vanilla.add(vGoldOre);

        // vanillaIronOre
        JsonObject vIronOre = new JsonObject();

        vIronOre.addProperty("ore", "SurfaceIronOre");
        vIronOre.addProperty("minY", surfaceMinY);
        vIronOre.addProperty("maxY", 69);
        vIronOre.addProperty("rarity", 10 * 2);
        vIronOre.addProperty("veinMinimum", 5);
        vIronOre.addProperty("veinMultiplier", 19);
        vIronOre.addProperty("disableOre", false);

        vanilla.add(vIronOre);

        // vanillaLapisOre
        JsonObject vLapisOre = new JsonObject();

        vLapisOre.addProperty("ore", "SurfaceLapisOre");
        vLapisOre.addProperty("minY", surfaceMinY);
        vLapisOre.addProperty("maxY", 34);
        vLapisOre.addProperty("rarity", 8 * 2);
        vLapisOre.addProperty("veinMinimum", 7);
        vLapisOre.addProperty("veinMultiplier", 7);
        vLapisOre.addProperty("disableOre", false);

        vanilla.add(vLapisOre);

        // vanillaRedstoneOre
        JsonObject vRedstoneOre = new JsonObject();

        vRedstoneOre.addProperty("ore", "SurfaceRedstoneOre");
        vRedstoneOre.addProperty("minY", surfaceMinY);
        vRedstoneOre.addProperty("maxY", 17);
        vRedstoneOre.addProperty("rarity", 24);
        vRedstoneOre.addProperty("veinMinimum", 3);
        vRedstoneOre.addProperty("veinMultiplier", 7);
        vRedstoneOre.addProperty("disableOre", false);

        vanilla.add(vRedstoneOre);

        // --------------------------------------- End Surface Ores -------------------------------------------------- \\

        // Nether array
        JsonArray nether = new JsonArray();

        // netherCoalOre
        JsonObject nCoalOre = new JsonObject();

        nCoalOre.addProperty("ore", "NetherCoalOre");
        nCoalOre.addProperty("minY", netherMinY);
        nCoalOre.addProperty("maxY", netherMaxY);
        nCoalOre.addProperty("rarity", 12 * 3);
        nCoalOre.addProperty("veinMinimum", 2);
        nCoalOre.addProperty("veinMultiplier", 17);
        nCoalOre.addProperty("disableOre", false);

        nether.add(nCoalOre);

        // netherDiamondOre
        JsonObject nDiamondOre = new JsonObject();

        nDiamondOre.addProperty("ore", "NetherDiamondOre");
        nDiamondOre.addProperty("minY", netherMinY);
        nDiamondOre.addProperty("maxY", netherMaxY);
        nDiamondOre.addProperty("rarity", 2 * 3);
        nDiamondOre.addProperty("veinMinimum", 3);
        nDiamondOre.addProperty("veinMultiplier", 6);
        nDiamondOre.addProperty("disableOre", false);

        nether.add(nDiamondOre);

        // netherEmeraldOre
        JsonObject nEmeraldOre = new JsonObject();

        nEmeraldOre.addProperty("ore", "NetherEmeraldOre");
        nEmeraldOre.addProperty("minY", netherMinY);
        nEmeraldOre.addProperty("maxY", netherMaxY);
        nEmeraldOre.addProperty("rarity", 4 * 3);
        nEmeraldOre.addProperty("veinMinimum", 6);
        nEmeraldOre.addProperty("veinMultiplier", 6);
        nEmeraldOre.addProperty("disableOre", false);

        nether.add(nEmeraldOre);

        // netherGoldOre
        JsonObject nGoldOre = new JsonObject();

        nGoldOre.addProperty("ore", "NetherGoldOre");
        nGoldOre.addProperty("minY", netherMinY);
        nGoldOre.addProperty("maxY", netherMaxY);
        nGoldOre.addProperty("rarity", 6 * 3);
        nGoldOre.addProperty("veinMinimum", 9);
        nGoldOre.addProperty("veinMultiplier", 9);
        nGoldOre.addProperty("disableOre", false);

        nether.add(nGoldOre);

        // netherIronOre
        JsonObject nIronOre = new JsonObject();

        nIronOre.addProperty("ore", "NetherIronOre");
        nIronOre.addProperty("minY", netherMinY);
        nIronOre.addProperty("maxY", netherMaxY);
        nIronOre.addProperty("rarity", 10 * 3);
        nIronOre.addProperty("veinMinimum", 5);
        nIronOre.addProperty("veinMultiplier", 19);
        nIronOre.addProperty("disableOre", false);

        nether.add(nIronOre);

        // netherLapisOre
        JsonObject nLapisOre = new JsonObject();

        nLapisOre.addProperty("ore", "NetherLapisOre");
        nLapisOre.addProperty("minY", netherMinY);
        nLapisOre.addProperty("maxY", netherMaxY);
        nLapisOre.addProperty("rarity", 8 * 3);
        nLapisOre.addProperty("veinMinimum", 7);
        nLapisOre.addProperty("veinMultiplier", 7);
        nLapisOre.addProperty("disableOre", false);

        nether.add(nLapisOre);

        // netherRedstoneOre
        JsonObject nRedstoneOre = new JsonObject();

        nRedstoneOre.addProperty("ore", "NetherRedstoneOre");
        nRedstoneOre.addProperty("minY", netherMinY);
        nRedstoneOre.addProperty("maxY", netherMaxY);
        nRedstoneOre.addProperty("rarity", 24 * 3);
        nRedstoneOre.addProperty("veinMinimum", 3);
        nRedstoneOre.addProperty("veinMultiplier", 7);
        nRedstoneOre.addProperty("disableOre", false);

        nether.add(nRedstoneOre);

        root.add("basic", basic);

        // --------------------------------------- Neutral Ores --------------------------------------------------- \\

        // OverworldCopperOre
        JsonObject oCopper = new JsonObject();

        oCopper.addProperty("ore", "OverworldCopperOre");
        oCopper.addProperty("minY", 10);
        oCopper.addProperty("maxY", 256);
        oCopper.addProperty("rarity", 10 * 3);
        oCopper.addProperty("veinMinimum", 5);
        oCopper.addProperty("veinMultiplier", 19);
        oCopper.addProperty("disableOre", false);

        neutral.add(oCopper);

        // OverworldTinOre
        JsonObject oTin = new JsonObject();

        oTin.addProperty("ore", "OverworldTinOre");
        oTin.addProperty("minY", 20);
        oTin.addProperty("maxY", 256);
        oTin.addProperty("rarity", 24 * 3);
        oTin.addProperty("veinMinimum", 3);
        oTin.addProperty("veinMultiplier", 19);
        oTin.addProperty("disableOre", false);

        neutral.add(oTin);

        // OverworldLeadOre
        JsonObject oLead = new JsonObject();

        oLead.addProperty("ore", "OverworldLeadOre");
        oLead.addProperty("minY", 16);
        oLead.addProperty("maxY", 60);
        oLead.addProperty("rarity", 10 * 2);
        oLead.addProperty("veinMinimum", 5);
        oLead.addProperty("veinMultiplier", 19);
        oLead.addProperty("disableOre", false);

        neutral.add(oLead);

        // OverworldSilverOre
        JsonObject oSilverOre = new JsonObject();

        oSilverOre.addProperty("ore", "SurfaceSilverOre");
        oSilverOre.addProperty("minY", surfaceMinY);
        oSilverOre.addProperty("maxY", 34);
        oSilverOre.addProperty("rarity", 8 * 2);
        oSilverOre.addProperty("veinMinimum", 7);
        oSilverOre.addProperty("veinMultiplier", 7);
        oSilverOre.addProperty("disableOre", false);

        neutral.add(oSilverOre);

        // netherCopperOre
        JsonObject nCopper = new JsonObject();

        nCopper.addProperty("ore", "NetherCopperOre");
        nCopper.addProperty("minY", netherMinY);
        nCopper.addProperty("maxY", netherMaxY);
        nCopper.addProperty("rarity", 10 * 3);
        nCopper.addProperty("veinMinimum", 5);
        nCopper.addProperty("veinMultiplier", 19);
        nCopper.addProperty("disableOre", false);

        neutral.add(nCopper);

        // netherCopperOre
        JsonObject nTin = new JsonObject();

        nTin.addProperty("ore", "NetherTinOre");
        nTin.addProperty("minY", netherMinY);
        nTin.addProperty("maxY", netherMaxY);
        nTin.addProperty("rarity", 24 * 3);
        nTin.addProperty("veinMinimum", 5);
        nTin.addProperty("veinMultiplier", 19);
        nTin.addProperty("disableOre", false);

        neutral.add(nTin);

        // netherLeadOre
        JsonObject nLead = new JsonObject();

        nLead.addProperty("ore", "NetherLeadOre");
        nLead.addProperty("minY", netherMinY);
        nLead.addProperty("maxY", netherMaxY);
        nLead.addProperty("rarity", 10 * 3);
        nLead.addProperty("veinMinimum", 5);
        nLead.addProperty("veinMultiplier", 19);
        nLead.addProperty("disableOre", false);

        neutral.add(nLead);

        // netherSilverOre
        JsonObject nSilverOre = new JsonObject();

        nSilverOre.addProperty("ore", "NetherSilverOre");
        nSilverOre.addProperty("minY", netherMinY);
        nSilverOre.addProperty("maxY", netherMaxY);
        nSilverOre.addProperty("rarity", 8 * 3);
        nSilverOre.addProperty("veinMinimum", 7);
        nSilverOre.addProperty("veinMultiplier", 7);
        nSilverOre.addProperty("disableOre", false);

        nether.add(nSilverOre);

        root.add("neutral", neutral);

        root.add("IC2", ic);

        createFile(root);
        return JsonFormatting(root);
    }

    public static void createFile(JsonObject obj)
    {
        try(PrintWriter writer = new PrintWriter(jsonFile))
        {
            writer.print(JsonFormatting(obj));
        }
        catch(FileNotFoundException fnf){}
    }

    public static void updateFile()
    {
        if(runOnce)
        {
            LALogger.log("LA Has been updated from previous version!");
            LALogger.log("OreGen.json has been renamed to OreGen.json.bak, and re-generated");
            LALogger.log("If you used custom values for old ores, re-implement them now.");

            runOnce = false;
        }

        if(updateFile.exists() && !updateFile.isDirectory())
        {
            updateFile.delete();
        }
        jsonFile.renameTo(updateFile);
        createJson();
    }

    public static String JsonFormatting(JsonObject obj)
    {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        String prettyJson = gson.toJson(obj);

        return prettyJson;
    }
}
