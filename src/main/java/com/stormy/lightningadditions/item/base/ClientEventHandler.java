package com.stormy.lightningadditions.item.base;

import com.stormy.lightningadditions.item.resource.ItemSleepingBag;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.client.event.RenderPlayerEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ClientEventHandler {

    public boolean needsToPop;

    @SubscribeEvent(priority = EventPriority.LOWEST)
    public void onRender(RenderPlayerEvent.Pre event)
    {

        if (event.getEntity() instanceof EntityPlayer)
        {

            EntityPlayer playerEntity = (EntityPlayer) event.getEntity();
            if (playerEntity.isPlayerSleeping())
            {

                if (ItemSleepingBag.isWearingSleepingBag(playerEntity))
                {
                    GlStateManager.pushMatrix();
                    GlStateManager.translate(0F, 0.24F, 0F);
                    this.needsToPop = true;
                }

            }

        }

    }

    @SubscribeEvent(priority = EventPriority.LOWEST, receiveCanceled = true)
    public void onRender(RenderPlayerEvent.Post event)
    {

        if (this.needsToPop)

        {
            this.needsToPop = false;
            GlStateManager.popMatrix();
        }
    }
}
