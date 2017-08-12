package com.stormy.lightninglib.lib.utils;

import net.minecraftforge.fml.common.LoaderState;
import net.minecraftforge.fml.common.event.FMLStateEvent;
import org.apache.logging.log4j.Logger;

public class LoadTimerHelper
{
    public LoadTimerHelper(Logger logger, String mod)
    { this.logger = logger;
        this.mod = mod; }

    private long start;
    private LoaderState.ModState lastState;
    private final String mod;
    private final Logger logger;


    public void startPhase(FMLStateEvent event)
    { if (lastState != null)
    { throw new IllegalStateException("Cannot start phase without ending phase " + lastState + "first."); }
        start = System.currentTimeMillis();
        lastState = event.getModState(); }

    public void endPhase(FMLStateEvent event)
    { LoaderState.ModState modState = event.getModState();
        if (this.lastState != modState) { throw new IllegalArgumentException(); }
        lastState = null;
        logger.info(mod + " has "+modState.toString()+" in "+(System.currentTimeMillis()-start)+" ms"); }
}
