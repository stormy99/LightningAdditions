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

package com.stormy.lightningadditions.client.gui.resource;

import com.stormy.lightningadditions.container.resource.ContainerTrashCan;
import com.stormy.lightningadditions.reference.ModInformation;
import com.stormy.lightningadditions.tile.resource.TileEntityTrashCan;
import com.stormy.lightningadditions.utility.GuiUtils;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class GuiTrashCan extends GuiContainer{

    private IInventory playerInv;
    private TileEntityTrashCan te;

    private static String texture = ModInformation.MODID + ":textures/gui/gui_trashcan.png";

    public GuiTrashCan(IInventory playerInv, TileEntityTrashCan te, EntityPlayer player) {
        super(new ContainerTrashCan(playerInv, te, player));

        this.playerInv = playerInv;
        this.te = te;

        this.xSize = 176;
        this.ySize = 174;

    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
        GL11.glColor4f(1F, 1F, 1F, 1F);
        this.mc.getTextureManager().bindTexture(new ResourceLocation(texture));
        this.drawTexturedModalRect(this.guiLeft, this.guiTop, 0, 0, this.xSize, this.ySize);
    }

    @Override
    protected  void drawGuiContainerForegroundLayer(int mouseX, int mouseY){
        this.mc.getTextureManager().bindTexture(new ResourceLocation(texture));
        this.drawTexturedModalRect(-24, 12, 0, 176, 27, 79);
        this.mc.getTextureManager().bindTexture(new ResourceLocation(texture));
        this.drawTexturedModalRect(-24, 146, 31, 176, 27, 25);

        String s = this.te.getDisplayName().getUnformattedComponentText();
        //this.fontRendererObj.drawString(s, 88 - this.fontRendererObj.getStringWidth(s) / 2, 6, 4210752);
        int x = GuiUtils.getXCenter(s, this.fontRendererObj, xSize);
        this.fontRendererObj.drawString(s, x, 5, 0x404040);
    }

}
