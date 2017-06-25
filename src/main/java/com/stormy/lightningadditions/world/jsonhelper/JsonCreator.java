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

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.stormy.lightningadditions.reference.ModInformation;
import com.stormy.lightningadditions.utility.logger.LALogger;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

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

        JsonObject root = new JsonObject();
        JsonArray basic = new JsonArray();
        JsonArray vanilla = new JsonArray();
        JsonArray neutral = new JsonArray();
        JsonArray forestry = new JsonArray();
        JsonArray ic = new JsonArray();

        JsonObject vCoalOre = new JsonObject();
        vCoalOre.addProperty("ore", "OverworldCoalOre");
        vCoalOre.addProperty("minY", Integer.valueOf(surfaceMinY));
        vCoalOre.addProperty("maxY", Integer.valueOf(213));
        vCoalOre.addProperty("rarity", Integer.valueOf(24));
        vCoalOre.addProperty("veinMinimum", Integer.valueOf(6));
        vCoalOre.addProperty("veinMultiplier", Integer.valueOf(31));
        vCoalOre.addProperty("disableOre", Boolean.valueOf(false));
        vanilla.add(vCoalOre);

        JsonObject vDiamondOre = new JsonObject();
        vDiamondOre.addProperty("ore", "OverworldDiamondOre");
        vDiamondOre.addProperty("minY", Integer.valueOf(surfaceMinY));
        vDiamondOre.addProperty("maxY", Integer.valueOf(21));
        vDiamondOre.addProperty("rarity", Integer.valueOf(4));
        vDiamondOre.addProperty("veinMinimum", Integer.valueOf(3));
        vDiamondOre.addProperty("veinMultiplier", Integer.valueOf(6));
        vDiamondOre.addProperty("disableOre", Boolean.valueOf(false));
        vanilla.add(vDiamondOre);

        JsonObject vEmeraldOre = new JsonObject();
        vEmeraldOre.addProperty("ore", "OverworldEmeraldOre");
        vEmeraldOre.addProperty("minY", Integer.valueOf(surfaceMinY));
        vEmeraldOre.addProperty("maxY", Integer.valueOf(33));
        vEmeraldOre.addProperty("rarity", Integer.valueOf(8));
        vEmeraldOre.addProperty("veinMinimum", Integer.valueOf(6));
        vEmeraldOre.addProperty("veinMultiplier", Integer.valueOf(6));
        vEmeraldOre.addProperty("disableOre", Boolean.valueOf(false));
        vanilla.add(vEmeraldOre);

        JsonObject vGoldOre = new JsonObject();
        vGoldOre.addProperty("ore", "OverworldGoldOre");
        vGoldOre.addProperty("minY", Integer.valueOf(surfaceMinY));
        vGoldOre.addProperty("maxY", Integer.valueOf(34));
        vGoldOre.addProperty("rarity", Integer.valueOf(12));
        vGoldOre.addProperty("veinMinimum", Integer.valueOf(9));
        vGoldOre.addProperty("veinMultiplier", Integer.valueOf(9));
        vGoldOre.addProperty("disableOre", Boolean.valueOf(false));
        vanilla.add(vGoldOre);

        JsonObject vIronOre = new JsonObject();
        vIronOre.addProperty("ore", "OverworldIronOre");
        vIronOre.addProperty("minY", Integer.valueOf(surfaceMinY));
        vIronOre.addProperty("maxY", Integer.valueOf(69));
        vIronOre.addProperty("rarity", Integer.valueOf(20));
        vIronOre.addProperty("veinMinimum", Integer.valueOf(5));
        vIronOre.addProperty("veinMultiplier", Integer.valueOf(19));
        vIronOre.addProperty("disableOre", Boolean.valueOf(false));
        vanilla.add(vIronOre);

        JsonObject vLapisOre = new JsonObject();
        vLapisOre.addProperty("ore", "OverworldLapisOre");
        vLapisOre.addProperty("minY", Integer.valueOf(surfaceMinY));
        vLapisOre.addProperty("maxY", Integer.valueOf(34));
        vLapisOre.addProperty("rarity", Integer.valueOf(16));
        vLapisOre.addProperty("veinMinimum", Integer.valueOf(7));
        vLapisOre.addProperty("veinMultiplier", Integer.valueOf(7));
        vLapisOre.addProperty("disableOre", Boolean.valueOf(false));
        vanilla.add(vLapisOre);

        JsonObject vRedstoneOre = new JsonObject();
        vRedstoneOre.addProperty("ore", "OverworldRedstoneOre");
        vRedstoneOre.addProperty("minY", Integer.valueOf(surfaceMinY));
        vRedstoneOre.addProperty("maxY", Integer.valueOf(17));
        vRedstoneOre.addProperty("rarity", Integer.valueOf(24));
        vRedstoneOre.addProperty("veinMinimum", Integer.valueOf(3));
        vRedstoneOre.addProperty("veinMultiplier", Integer.valueOf(7));
        vRedstoneOre.addProperty("disableOre", Boolean.valueOf(false));
        vanilla.add(vRedstoneOre);

        JsonArray nether = new JsonArray();

        JsonObject nCoalOre = new JsonObject();
        nCoalOre.addProperty("ore", "NetherCoalOre");
        nCoalOre.addProperty("minY", Integer.valueOf(netherMinY));
        nCoalOre.addProperty("maxY", Integer.valueOf(netherMaxY));
        nCoalOre.addProperty("rarity", Integer.valueOf(36));
        nCoalOre.addProperty("veinMinimum", Integer.valueOf(2));
        nCoalOre.addProperty("veinMultiplier", Integer.valueOf(17));
        nCoalOre.addProperty("disableOre", Boolean.valueOf(false));
        nether.add(nCoalOre);

        JsonObject nDiamondOre = new JsonObject();
        nDiamondOre.addProperty("ore", "NetherDiamondOre");
        nDiamondOre.addProperty("minY", Integer.valueOf(netherMinY));
        nDiamondOre.addProperty("maxY", Integer.valueOf(netherMaxY));
        nDiamondOre.addProperty("rarity", Integer.valueOf(6));
        nDiamondOre.addProperty("veinMinimum", Integer.valueOf(3));
        nDiamondOre.addProperty("veinMultiplier", Integer.valueOf(6));
        nDiamondOre.addProperty("disableOre", Boolean.valueOf(false));
        nether.add(nDiamondOre);

        JsonObject nEmeraldOre = new JsonObject();
        nEmeraldOre.addProperty("ore", "NetherEmeraldOre");
        nEmeraldOre.addProperty("minY", Integer.valueOf(netherMinY));
        nEmeraldOre.addProperty("maxY", Integer.valueOf(netherMaxY));
        nEmeraldOre.addProperty("rarity", Integer.valueOf(12));
        nEmeraldOre.addProperty("veinMinimum", Integer.valueOf(6));
        nEmeraldOre.addProperty("veinMultiplier", Integer.valueOf(6));
        nEmeraldOre.addProperty("disableOre", Boolean.valueOf(false));
        nether.add(nEmeraldOre);

        JsonObject nGoldOre = new JsonObject();
        nGoldOre.addProperty("ore", "NetherGoldOre");
        nGoldOre.addProperty("minY", Integer.valueOf(netherMinY));
        nGoldOre.addProperty("maxY", Integer.valueOf(netherMaxY));
        nGoldOre.addProperty("rarity", Integer.valueOf(18));
        nGoldOre.addProperty("veinMinimum", Integer.valueOf(9));
        nGoldOre.addProperty("veinMultiplier", Integer.valueOf(9));
        nGoldOre.addProperty("disableOre", Boolean.valueOf(false));
        nether.add(nGoldOre);

        JsonObject nIronOre = new JsonObject();
        nIronOre.addProperty("ore", "NetherIronOre");
        nIronOre.addProperty("minY", Integer.valueOf(netherMinY));
        nIronOre.addProperty("maxY", Integer.valueOf(netherMaxY));
        nIronOre.addProperty("rarity", Integer.valueOf(30));
        nIronOre.addProperty("veinMinimum", Integer.valueOf(5));
        nIronOre.addProperty("veinMultiplier", Integer.valueOf(19));
        nIronOre.addProperty("disableOre", Boolean.valueOf(false));
        nether.add(nIronOre);

        JsonObject nLapisOre = new JsonObject();
        nLapisOre.addProperty("ore", "NetherLapisOre");
        nLapisOre.addProperty("minY", Integer.valueOf(netherMinY));
        nLapisOre.addProperty("maxY", Integer.valueOf(netherMaxY));
        nLapisOre.addProperty("rarity", Integer.valueOf(24));
        nLapisOre.addProperty("veinMinimum", Integer.valueOf(7));
        nLapisOre.addProperty("veinMultiplier", Integer.valueOf(7));
        nLapisOre.addProperty("disableOre", Boolean.valueOf(false));
        nether.add(nLapisOre);

        JsonObject nRedstoneOre = new JsonObject();
        nRedstoneOre.addProperty("ore", "NetherRedstoneOre");
        nRedstoneOre.addProperty("minY", Integer.valueOf(netherMinY));
        nRedstoneOre.addProperty("maxY", Integer.valueOf(netherMaxY));
        nRedstoneOre.addProperty("rarity", Integer.valueOf(72));
        nRedstoneOre.addProperty("veinMinimum", Integer.valueOf(3));
        nRedstoneOre.addProperty("veinMultiplier", Integer.valueOf(7));
        nRedstoneOre.addProperty("disableOre", Boolean.valueOf(false));
        nether.add(nRedstoneOre);


        basic.add(vanilla);
        basic.add(nether);
        root.add("basic", basic);

        //Overworld Modded Ores
        JsonObject oCopper = new JsonObject();
        oCopper.addProperty("ore", "OverworldCopperOre");
        oCopper.addProperty("minY", Integer.valueOf(10));
        oCopper.addProperty("maxY", Integer.valueOf(256));
        oCopper.addProperty("rarity", Integer.valueOf(30));
        oCopper.addProperty("veinMinimum", Integer.valueOf(5));
        oCopper.addProperty("veinMultiplier", Integer.valueOf(19));
        oCopper.addProperty("disableOre", Boolean.valueOf(false));
        neutral.add(oCopper);

        JsonObject oTin = new JsonObject();
        oTin.addProperty("ore", "OverworldTinOre");
        oTin.addProperty("minY", Integer.valueOf(20));
        oTin.addProperty("maxY", Integer.valueOf(256));
        oTin.addProperty("rarity", Integer.valueOf(72));
        oTin.addProperty("veinMinimum", Integer.valueOf(3));
        oTin.addProperty("veinMultiplier", Integer.valueOf(19));
        oTin.addProperty("disableOre", Boolean.valueOf(false));
        neutral.add(oTin);

        JsonObject oLead = new JsonObject();
        oLead.addProperty("ore", "OverworldLeadOre");
        oLead.addProperty("minY", Integer.valueOf(16));
        oLead.addProperty("maxY", Integer.valueOf(60));
        oLead.addProperty("rarity", Integer.valueOf(20));
        oLead.addProperty("veinMinimum", Integer.valueOf(5));
        oLead.addProperty("veinMultiplier", Integer.valueOf(19));
        oLead.addProperty("disableOre", Boolean.valueOf(false));
        neutral.add(oLead);

        JsonObject oSilver = new JsonObject();
        oTin.addProperty("ore", "OverworldSilverOre");
        oTin.addProperty("minY", Integer.valueOf(20));
        oTin.addProperty("maxY", Integer.valueOf(30));
        oTin.addProperty("rarity", Integer.valueOf(72));
        oTin.addProperty("veinMinimum", Integer.valueOf(3));
        oTin.addProperty("veinMultiplier", Integer.valueOf(8));
        oTin.addProperty("disableOre", Boolean.valueOf(false));
        neutral.add(oSilver);

        //Nether Modded Ores
        JsonObject nCopper = new JsonObject();
        nCopper.addProperty("ore", "NetherCopperOre");
        nCopper.addProperty("minY", Integer.valueOf(netherMinY));
        nCopper.addProperty("maxY", Integer.valueOf(netherMaxY));
        nCopper.addProperty("rarity", Integer.valueOf(30));
        nCopper.addProperty("veinMinimum", Integer.valueOf(5));
        nCopper.addProperty("veinMultiplier", Integer.valueOf(19));
        nCopper.addProperty("disableOre", Boolean.valueOf(false));
        neutral.add(nCopper);

        JsonObject nTin = new JsonObject();
        nTin.addProperty("ore", "NetherTinOre");
        nTin.addProperty("minY", Integer.valueOf(netherMinY));
        nTin.addProperty("maxY", Integer.valueOf(netherMaxY));
        nTin.addProperty("rarity", Integer.valueOf(72));
        nTin.addProperty("veinMinimum", Integer.valueOf(5));
        nTin.addProperty("veinMultiplier", Integer.valueOf(19));
        nTin.addProperty("disableOre", Boolean.valueOf(false));
        neutral.add(nTin);

        JsonObject nLead = new JsonObject();
        nLead.addProperty("ore", "NetherLeadOre");
        nLead.addProperty("minY", Integer.valueOf(netherMinY));
        nLead.addProperty("maxY", Integer.valueOf(netherMaxY));
        nLead.addProperty("rarity", Integer.valueOf(30));
        nLead.addProperty("veinMinimum", Integer.valueOf(5));
        nLead.addProperty("veinMultiplier", Integer.valueOf(19));
        nLead.addProperty("disableOre", Boolean.valueOf(false));
        neutral.add(nLead);

        JsonObject nSilver = new JsonObject();
        nTin.addProperty("ore", "NetherSilverOre");
        nTin.addProperty("minY", Integer.valueOf(netherMinY));
        nTin.addProperty("maxY", Integer.valueOf(netherMaxY));
        nTin.addProperty("rarity", Integer.valueOf(72));
        nTin.addProperty("veinMinimum", Integer.valueOf(2));
        nTin.addProperty("veinMultiplier", Integer.valueOf(6));
        nTin.addProperty("disableOre", Boolean.valueOf(false));
        neutral.add(nSilver);

        //Root Addition
        root.add("neutral", neutral);
        root.add("forestry", forestry);
        root.add("IC2", ic);

        createFile(root);
        return JsonFormatting(root);
    }

    public static void createFile(JsonObject obj)
    {
        try
        {
            PrintWriter writer = new PrintWriter(jsonFile);Throwable localThrowable3 = null;
            try
            {
                writer.print(JsonFormatting(obj));
            }
            catch (Throwable localThrowable1)
            {
                localThrowable3 = localThrowable1;throw localThrowable1;
            }
            finally
            {
                if (writer != null) {
                    if (localThrowable3 != null) {
                        try
                        {
                            writer.close();
                        }
                        catch (Throwable localThrowable2)
                        {
                            localThrowable3.addSuppressed(localThrowable2);
                        }
                    } else {
                        writer.close();
                    }
                }
            }
        }
        catch (FileNotFoundException localFileNotFoundException) {}
    }

    public static void updateFile()
    {
        if (runOnce)
        {
            LALogger.log("LA has been updated since previous version");
            runOnce = false;
        }
        if ((updateFile.exists()) && (!updateFile.isDirectory())) {
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
