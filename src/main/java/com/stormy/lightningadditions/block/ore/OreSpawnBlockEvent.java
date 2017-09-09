/*
 * ********************************************************************************
 * Copyright (c) 2017 StormyMode, MiningMark48. All Rights Reserved!
 * This file is part of Lightning Additions (MC-Mod).
 *
 * This project cannot be copied and/or distributed without the express
 * permission of StormyMode, MiningMark48 (Developers)!
 * ********************************************************************************
 */

package com.stormy.lightningadditions.block.ore;

import com.stormy.lightningadditions.config.ConfigurationManagerLA;
import com.stormy.lightningadditions.utility.logger.LALogger;
import net.minecraftforge.event.terraingen.OreGenEvent;
import net.minecraftforge.fml.common.eventhandler.Event;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.ArrayList;

public class OreSpawnBlockEvent
{
    public static ArrayList<OreGenEvent.GenerateMinable.EventType> ores = new ArrayList();

    @SubscribeEvent
    public void oreSpawnBlock(OreGenEvent.GenerateMinable event)
    {
        int dimID = event.getWorld().provider.getDimension();
        if (ConfigurationManagerLA.changeVanilla) {
            if (ConfigurationManagerLA.supportNewDims) {
                blockOres(event);
            } else if ((dimID == -1) || (dimID == 0)) {
                blockOres(event);
            }
        }
    }

    private void blockOres(OreGenEvent.GenerateMinable event)
    {
        for (int i = 0; i < ores.size() - 2; i++) {
            if (event.getType() == ores.get(i))
            {
                LALogger.debug("Blocked ore of type: " + event.getType() + " From spawning.");

                event.setResult(Event.Result.DENY);
            }
            else
            {
                blockCustomOres(event);
            }
        }
    }

    public static void populateOreType()
    {
        OreGenEvent.GenerateMinable.EventType[] types = OreGenEvent.GenerateMinable.EventType.values();

        OreGenEvent.GenerateMinable.EventType[] remove = { OreGenEvent.GenerateMinable.EventType.ANDESITE, OreGenEvent.GenerateMinable.EventType.DIORITE, OreGenEvent.GenerateMinable.EventType.GRANITE, OreGenEvent.GenerateMinable.EventType.GRAVEL, OreGenEvent.GenerateMinable.EventType.SILVERFISH, OreGenEvent.GenerateMinable.EventType.CUSTOM };
        for (int i = 0; i < types.length; i++) {
            ores.add(types[i]);
        }
        for (int i = 0; i < remove.length; i++) {
            if (ores.contains(remove[i])) {
                ores.remove(remove[i]);
            }
        }
    }

    private void blockCustomOres(OreGenEvent.GenerateMinable event) {}
}