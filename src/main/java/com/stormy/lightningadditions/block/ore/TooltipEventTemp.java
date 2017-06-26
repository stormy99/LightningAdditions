package com.stormy.lightningadditions.block.ore;

/*
THIS IS TEMPORARY AT THE MOMENT, I MAY INTEGRATE THIS IN AS AN ACTUAL FEATURE. -MININGMARK48
 */

import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.oredict.OreDictionary;

public class TooltipEventTemp {

    @SubscribeEvent
    public void onItemTooltip(ItemTooltipEvent event)
    {
        for (int i : OreDictionary.getOreIDs(event.getItemStack())) {
            event.getToolTip().add(TextFormatting.GRAY + OreDictionary.getOreName(i));
        }
    }

}
