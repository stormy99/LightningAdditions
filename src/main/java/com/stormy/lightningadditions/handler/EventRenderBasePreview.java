package com.stormy.lightningadditions.handler;

import com.stormy.lightningadditions.client.RenderPreview;
import com.stormy.lightningadditions.item.base.ItemBasePreviewer;
import com.stormy.lightningadditions.reference.ModInformation;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;

@Mod.EventBusSubscriber(value = Side.CLIENT, modid = ModInformation.MODID)
public class EventRenderBasePreview {

    @SubscribeEvent
    public static void renderWorldLastEvent(RenderWorldLastEvent event) {
        Minecraft mc = Minecraft.getMinecraft();
        EntityPlayer p = mc.player;
        ItemStack heldItem = p.getHeldItemMainhand();

        if (!(heldItem.getItem() instanceof ItemBasePreviewer)) {
            heldItem = p.getHeldItemOffhand();
            if (!(heldItem.getItem() instanceof ItemBasePreviewer)) {
                return;
            }
        }

        if (heldItem.getItem() instanceof ItemBasePreviewer) {
            RenderPreview.doRenderItem(p, heldItem);
        }
    }

}
