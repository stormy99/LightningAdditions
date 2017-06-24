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

import java.util.ArrayList;

import com.stormy.lightningadditions.utility.logger.ConfigurationManagerLA;
import com.stormy.lightningadditions.utility.logger.LALogger;
import net.minecraftforge.event.terraingen.OreGenEvent;
import net.minecraftforge.event.terraingen.OreGenEvent.GenerateMinable.EventType;
import net.minecraftforge.fml.common.eventhandler.Event.Result;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class OreSpawnBlockEvent
{
    public static ArrayList<EventType> ores = new ArrayList<EventType>();

    @SubscribeEvent
    public void oreSpawnBlock(OreGenEvent.GenerateMinable event)
    {
        int dimID = event.getWorld().provider.getDimension();

        if(ConfigurationManagerLA.changeVanilla)
        {
            if(ConfigurationManagerLA.supportNewDims)
            {
                blockOres(event);
            }
            else
            {
                if(dimID == -1 || dimID == 0) // Only block ore spawns in the nether and overworld
                {
                    blockOres(event);
                }
            }
        }
    }

    private void blockOres(OreGenEvent.GenerateMinable event)
    {

        for(int i = 0; i < (ores.size() - 2); i++)
        {
            if(event.getType() == ores.get(i))
            {
                LALogger.debug("Blocked Ore: " + event.getType() + " from spawning.");

                event.setResult(Result.DENY);
            }
            else
            {
                blockCustomOres(event);
            }
        }
    }

    public static void populateOreType()
    {
        EventType[] types = EventType.values(); // A list of all the types
        // The types to remove from the above list
        EventType[] remove =
                {
                        EventType.ANDESITE,
                        EventType.DIORITE,
                        EventType.GRANITE,
                        EventType.GRAVEL,
                        EventType.SILVERFISH,
                        EventType.CUSTOM
                };

        for(int i = 0; i < types.length; i++)
        {
            ores.add(types[i]);
        }
        for(int i = 0; i < remove.length; i++)
        {
            if(ores.contains(remove[i]))
            {
                ores.remove(remove[i]);
            }
        }
    }

    private void blockCustomOres(OreGenEvent.GenerateMinable event)
    {
        //Future Placeholder.
    }
}
