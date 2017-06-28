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
import com.google.gson.stream.JsonWriter;
import com.stormy.lightningadditions.reference.ModInformation;
import com.stormy.lightningadditions.utility.logger.LALogger;

import java.io.*;

public class JsonCreator
{
    static File fileDir = new File(ModInformation.LOCATION.toString());
    static File jsonFile = new File(fileDir + "\\OreGen2.json");
    static File updateFile = new File(ModInformation.LOCATION + "/OreGen2.json.bak");
    static boolean runOnce = true;

    public static void makeJson(){

        if(!fileDir.exists()){
            fileDir.mkdirs();
        }

        try {

            Writer writer = new OutputStreamWriter(new FileOutputStream(jsonFile), "UTF-8");
            JsonWriter root = new JsonWriter(writer);
            root.setIndent("  ");

            root.beginObject(); //1
            root.name("Vanilla");
            root.beginObject(); //2
            
            root.name("Overworld");
            root.beginArray(); //3

            root.beginObject();
            root.name("ore").value("OverworldCoalOre");
            root.name("minY").value(0);
            root.name("maxY").value(213);
            root.name("rarity").value(24);
            root.name("veinMinimum").value(6);
            root.name("veinMultiplier").value(31);
            root.name("disableOre").value(false);
            root.endObject();

            root.beginObject();
            root.name("ore").value("OverworldDiamondOre");
            root.name("minY").value(0);
            root.name("maxY").value(21);
            root.name("rarity").value(4);
            root.name("veinMinimum").value(3);
            root.name("veinMultiplier").value(6);
            root.name("disableOre").value(false);
            root.endObject();

            root.beginObject();
            root.name("ore").value("OverworldEmeraldOre");
            root.name("minY").value(0);
            root.name("maxY").value(33);
            root.name("rarity").value(8);
            root.name("veinMinimum").value(6);
            root.name("veinMultiplier").value(6);
            root.name("disableOre").value(false);
            root.endObject();

            root.beginObject();
            root.name("ore").value("OverworldGoldOre");
            root.name("minY").value(0);
            root.name("maxY").value(34);
            root.name("rarity").value(12);
            root.name("veinMinimum").value(9);
            root.name("veinMultiplier").value(9);
            root.name("disableOre").value(false);
            root.endObject();

            root.beginObject();
            root.name("ore").value("OverworldIronOre");
            root.name("minY").value(0);
            root.name("maxY").value(69);
            root.name("rarity").value(20);
            root.name("veinMinimum").value(5);
            root.name("veinMultiplier").value(19);
            root.name("disableOre").value(false);
            root.endObject();

            root.beginObject();
            root.name("ore").value("OverworldLapisOre");
            root.name("minY").value(0);
            root.name("maxY").value(34);
            root.name("rarity").value(16);
            root.name("veinMinimum").value(7);
            root.name("veinMultiplier").value(7);
            root.name("disableOre").value(false);
            root.endObject();

            root.beginObject();
            root.name("ore").value("OverworldRedstoneOre");
            root.name("minY").value(0);
            root.name("maxY").value(17);
            root.name("rarity").value(24);
            root.name("veinMinimum").value(3);
            root.name("veinMultiplier").value(7);
            root.name("disableOre").value(false);
            root.endObject();


            root.endArray(); //3


            root.name("Nether");
            root.beginArray(); //4

            root.beginObject();
            root.name("ore").value("NetherCoalOre");
            root.name("minY").value(0);
            root.name("maxY").value(213);
            root.name("rarity").value(24);
            root.name("veinMinimum").value(6);
            root.name("veinMultiplier").value(31);
            root.name("disableOre").value(false);
            root.endObject();

            root.beginObject();
            root.name("ore").value("NetherDiamondOre");
            root.name("minY").value(0);
            root.name("maxY").value(21);
            root.name("rarity").value(4);
            root.name("veinMinimum").value(3);
            root.name("veinMultiplier").value(6);
            root.name("disableOre").value(false);
            root.endObject();

            root.beginObject();
            root.name("ore").value("NetherEmeraldOre");
            root.name("minY").value(0);
            root.name("maxY").value(33);
            root.name("rarity").value(8);
            root.name("veinMinimum").value(6);
            root.name("veinMultiplier").value(6);
            root.name("disableOre").value(false);
            root.endObject();

            root.beginObject();
            root.name("ore").value("NetherGoldOre");
            root.name("minY").value(0);
            root.name("maxY").value(34);
            root.name("rarity").value(12);
            root.name("veinMinimum").value(9);
            root.name("veinMultiplier").value(9);
            root.name("disableOre").value(false);
            root.endObject();

            root.beginObject();
            root.name("ore").value("NetherIronOre");
            root.name("minY").value(0);
            root.name("maxY").value(69);
            root.name("rarity").value(20);
            root.name("veinMinimum").value(5);
            root.name("veinMultiplier").value(19);
            root.name("disableOre").value(false);
            root.endObject();

            root.beginObject();
            root.name("ore").value("NetherLapisOre");
            root.name("minY").value(0);
            root.name("maxY").value(34);
            root.name("rarity").value(16);
            root.name("veinMinimum").value(7);
            root.name("veinMultiplier").value(7);
            root.name("disableOre").value(false);
            root.endObject();

            root.beginObject();
            root.name("ore").value("NetherRedstoneOre");
            root.name("minY").value(0);
            root.name("maxY").value(17);
            root.name("rarity").value(24);
            root.name("veinMinimum").value(3);
            root.name("veinMultiplier").value(7);
            root.name("disableOre").value(false);
            root.endObject();

            root.endArray(); //4

            root.endObject(); //2

            
            root.name("Modded");
            root.beginObject(); //2

            root.name("Overworld");
            root.beginArray(); //3

            root.beginObject();
            root.name("ore").value("OverworldCopperOre");
            root.name("minY").value(12);
            root.name("maxY").value(256);
            root.name("rarity").value(30);
            root.name("veinMinimum").value(5);
            root.name("veinMultiplier").value(19);
            root.name("disableOre").value(false);
            root.endObject();

            root.beginObject();
            root.name("ore").value("OverworldLeadOre");
            root.name("minY").value(16);
            root.name("maxY").value(60);
            root.name("rarity").value(20);
            root.name("veinMinimum").value(5);
            root.name("veinMultiplier").value(19);
            root.name("disableOre").value(false);
            root.endObject();

            root.beginObject();
            root.name("ore").value("OverworldSilverOre");
            root.name("minY").value(20);
            root.name("maxY").value(30);
            root.name("rarity").value(72);
            root.name("veinMinimum").value(5);
            root.name("veinMultiplier").value(19);
            root.name("disableOre").value(false);
            root.endObject();

            root.beginObject();
            root.name("ore").value("OverworldTinOre");
            root.name("minY").value(12);
            root.name("maxY").value(256);
            root.name("rarity").value(30);
            root.name("veinMinimum").value(5);
            root.name("veinMultiplier").value(19);
            root.name("disableOre").value(false);
            root.endObject();

            root.endArray(); //3


            root.name("Nether");
            root.beginArray(); //4

            root.beginObject();
            root.name("ore").value("NetherCopperOre");
            root.name("minY").value(12);
            root.name("maxY").value(256);
            root.name("rarity").value(30);
            root.name("veinMinimum").value(5);
            root.name("veinMultiplier").value(19);
            root.name("disableOre").value(false);
            root.endObject();

            root.beginObject();
            root.name("ore").value("NetherLeadOre");
            root.name("minY").value(16);
            root.name("maxY").value(60);
            root.name("rarity").value(20);
            root.name("veinMinimum").value(5);
            root.name("veinMultiplier").value(19);
            root.name("disableOre").value(false);
            root.endObject();

            root.beginObject();
            root.name("ore").value("NetherSilverOre");
            root.name("minY").value(20);
            root.name("maxY").value(30);
            root.name("rarity").value(72);
            root.name("veinMinimum").value(5);
            root.name("veinMultiplier").value(19);
            root.name("disableOre").value(false);
            root.endObject();

            root.beginObject();
            root.name("ore").value("NetherTinOre");
            root.name("minY").value(12);
            root.name("maxY").value(256);
            root.name("rarity").value(30);
            root.name("veinMinimum").value(5);
            root.name("veinMultiplier").value(19);
            root.name("disableOre").value(false);
            root.endObject();

            root.endArray(); //4


            root.endObject(); //2
            
            root.endObject(); //1


            writer.close();

            LALogger.info("OreGen json was created.");

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

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
        makeJson();
    }

    public static String JsonFormatting(JsonObject obj)
    {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        String prettyJson = gson.toJson(obj);

        return prettyJson;
    }
}
