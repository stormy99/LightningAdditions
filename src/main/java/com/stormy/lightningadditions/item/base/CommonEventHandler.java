package com.stormy.lightningadditions.item.base;

import com.stormy.lightningadditions.item.resource.ItemSleepingBag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.event.entity.player.SleepingLocationCheckEvent;
import net.minecraftforge.fml.common.eventhandler.Event;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.relauncher.Side;

public class CommonEventHandler {

    @SubscribeEvent
    public void onPlayerTick(TickEvent.PlayerTickEvent event) {
        if(event.side == Side.CLIENT)
            return;
        EntityPlayer player = event.player; }

    @SubscribeEvent
    public void handleSleepLocationCheck(SleepingLocationCheckEvent event) {
        if (ItemSleepingBag.isWearingSleepingBag(event.getEntityPlayer())) {
            event.setResult(Event.Result.ALLOW);
        }
    }
}
