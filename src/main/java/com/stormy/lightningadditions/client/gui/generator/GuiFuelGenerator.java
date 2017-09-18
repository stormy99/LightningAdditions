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

package com.stormy.lightningadditions.client.gui.generator;

import com.stormy.lightningadditions.container.generator.ContainerFuelGenerator;
import com.stormy.lightningadditions.reference.ModInformation;
import com.stormy.lightninglib.lib.utils.TranslateUtils;
import com.stormy.lightningadditions.tile.generator.TileEntityFuelGenerator;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.inventory.IInventory;
import net.minecraft.util.ResourceLocation;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

public class GuiFuelGenerator extends GuiContainer{

    private TileEntityFuelGenerator te;
    private IInventory playerInv;

    public GuiFuelGenerator(IInventory playerInv, TileEntityFuelGenerator te) {
        super(new ContainerFuelGenerator(playerInv, te));

        this.te = te;
        this.playerInv = playerInv;

        this.xSize = 176;
        this.ySize = 168;
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
        GlStateManager.color(1.0f, 1.0f, 1.0f, 1.0f);
        this.mc.getTextureManager().bindTexture(new ResourceLocation(ModInformation.MODID + ":textures/gui/generator/gui_fuel_generator.png"));
        this.drawTexturedModalRect(this.guiLeft, this.guiTop, 0, 0, this.xSize, this.ySize);
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {

        //Energy Bar
        this.mc.getTextureManager().bindTexture(new ResourceLocation(ModInformation.MODID + ":textures/gui/generator/gui_fuel_generator.png"));
        this.drawTexturedModalRect(8, 66 - getProgressLevel(51), 180, 21, 21, getProgressLevel(51));

        //Progress
        this.mc.getTextureManager().bindTexture(new ResourceLocation(ModInformation.MODID + ":textures/gui/generator/gui_fuel_generator.png"));
        this.drawTexturedModalRect(40, 46 - getCooldownLevel(14), 180, 74, 12, getCooldownLevel(15));
        if (this.te.getField(2) > 0){
            int s = this.te.getField(2) / 20;
            int m = s / 60;
            s = s % 60;
            this.fontRenderer.drawString( ( m > 0 ? m + "m " : "") + s + TranslateUtils.toLocal("gui.generator.info.seconds"), 67, 32, 4210752);
        }

        if (this.te.getField(0) >= this.te.getField(1)){
            this.fontRenderer.drawString(TranslateUtils.toLocal("gui.generator.info.storage_full"), 67, 19, 4210752);
        } else if (this.te.getField(0) < this.te.getField(1)) {
            if (this.te.getField(2) > 0) {
                NumberFormat format = NumberFormat.getInstance();
                this.fontRenderer.drawString(TranslateUtils.toLocal("gui.generator.info.rft") + " " + format.format(this.te.getField(3)), 67, 19, 4210752);
            }
        }

        String s = this.te.getDisplayName().getUnformattedText();
        this.fontRenderer.drawString(s, 88 - this.fontRenderer.getStringWidth(s) / 2, 6, 4210752);
        this.fontRenderer.drawString(this.playerInv.getDisplayName().getUnformattedText(), 8, 75, 4210752);

        if (this.isMouseOver(mouseX, mouseY, 7, 14, 29, 66)){
            Minecraft mc = Minecraft.getMinecraft();

            List<String> text = new ArrayList<String>();
            text.add(this.getOverlayText());
            net.minecraftforge.fml.client.config.GuiUtils.drawHoveringText(text, mouseX - ((this.width - this.xSize) / 2), mouseY - ((this.height - this.ySize) / 2), mc.displayWidth, mc.displayHeight, -1, mc.fontRenderer);
        }

    }

    private boolean isMouseOver(int mouseX, int mouseY, int minX, int minY, int maxX, int maxY){
        int actualX = mouseX - ((this.width - this.xSize) / 2);
        int actualY = mouseY - ((this.height - this.ySize) / 2);
        return (actualX >= minX) && (actualX <= maxX) && (actualY >= minY) && (actualY <= maxY);
    }

    private String getOverlayText(){
        NumberFormat format = NumberFormat.getInstance();
        return String.format("%s/%s " + TranslateUtils.toLocal("gui.generator.info.rf"), format.format(this.te.getField(0)), format.format(this.te.getField(1)));
    }

    private int getProgressLevel(int pixels) {
        int rf = this.te.getField(0);
        int maxRF = this.te.getField(1);
        return maxRF != 0 && rf != 0 ? (rf * pixels) / maxRF : 0;

    }

    private int getCooldownLevel(int pixels) {

        if (this.te.getField(2) <= 0){
            return 0;
        }

        int cooldown = this.te.getField(2);
        int maxCooldown = this.te.maxCooldown;
        return maxCooldown != 0 && cooldown != 0 ? (cooldown * pixels) / maxCooldown : 0;

    }

}
