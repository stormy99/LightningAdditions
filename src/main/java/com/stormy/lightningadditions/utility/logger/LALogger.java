/*
 * ********************************************************************************
 * Copyright (c) 2017 StormyMode, MiningMark48. All Rights Reserved!
 * This file is part of Lightning Additions (MC-Mod).
 *
 * This project cannot be copied and/or distributed without the express
 * permission of StormyMode, MiningMark48 (Developers)!
 * ********************************************************************************
 */

package com.stormy.lightningadditions.utility.logger;

import com.stormy.lightningadditions.reference.ModInformation;
import net.minecraftforge.fml.common.FMLLog;
import org.apache.logging.log4j.Level;

import java.util.logging.Logger;

public class LALogger
{
    private static Logger logger = Logger.getLogger(ModInformation.LOG); // Not used anymore, ignore it
    public static void log(Level logLevel, String string)
    {
        FMLLog.log(ModInformation.LOG, logLevel, string);
    }
    public static void log(String string)
    {
        FMLLog.log(ModInformation.LOG, Level.INFO, string);
    }
    public static void debug(String string) { FMLLog.log(ModInformation.LOG, Level.INFO, string); }
    public static void all(String string)
    {
        log(Level.ALL, string);
    }
    public static void error(String string)
    {
        log(Level.ERROR, string);
    }
    public static void fatal(String string)
    {
        log(Level.FATAL, string);
    }
    public static void info(String string)
    {
        log(Level.INFO, string);
    }
    public static void off(String string)
    {
        log(Level.OFF, string);
    }
    public static void warn(String string)
    {
        log(Level.WARN, string);
    }
    public static void trace(String string) { log(Level.TRACE, string); }
}
