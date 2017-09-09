/*
 *
 *  * ********************************************************************************
 *  * Copyright (c) 2017 StormyMode, MiningMark48. All Rights Reserved!
 *  * This file is part of Lightning Additions (MC-Mod).
 *  *
 *  * This project cannot be copied and/or distributed without the express
 *  * permission of StormyMode, MiningMark48 (Developers)!
 *  * ********************************************************************************
 *
 */

package com.stormy.lightningadditions.block.ore;

import com.stormy.lightningadditions.config.ConfigurationManagerLA;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.oredict.OreDictionary;

import java.util.Arrays;

import static com.stormy.lightninglib.lib.utils.StringHelper.ITALIC;
import static com.stormy.lightninglib.lib.utils.StringHelper.LIGHT_GRAY;

public class OreDictTooltipEvent {

    @SubscribeEvent
    public void onItemTooltip(ItemTooltipEvent event)
    {
        if (ConfigurationManagerLA.addOreDictTooltip) Arrays.stream(OreDictionary.getOreIDs(event.getItemStack())).forEach(id -> event.getToolTip().add(ITALIC + LIGHT_GRAY + OreDictionary.getOreName(id)));
//        for (int i : OreDictionary.getOreIDs(event.getItemStack())) {
//            event.getToolTip().add(TextFormatting.GRAY + OreDictionary.getOreName(i));
//        }
    }

}
