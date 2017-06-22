/*
 * ********************************************************************************
 * Copyright (c) 2017 StormyMode, MiningMark48. All Rights Reserved!
 * This file is part of Lightning Additions (MC-Mod).
 *
 * This project cannot be copied and/or distributed without the express
 * permission of StormyMode, MiningMark48 (Developers)!
 * ********************************************************************************
 */

package com.stormy.lightningadditions.utility.calc;

import java.io.IOException;

import org.lwjgl.input.Keyboard;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.resources.I18n;

public class GuiCalc extends GuiScreen {

    private GuiScreen parentScreen;

    private GuiTranButton buttonAllClear;

    private GuiTranButton buttonModulus; private GuiTranButton buttonDivide; private GuiTranButton buttonMultiply; private GuiTranButton buttonSubtract; private GuiTranButton buttonAdd;
    private GuiTranButton buttonEquals;

    private GuiTranButton button0;
    private GuiTranButton button1;
    private GuiTranButton button2;
    private GuiTranButton button3;
    private GuiTranButton button4;
    private GuiTranButton button5;
    private GuiTranButton button6;
    private GuiTranButton button7;
    private GuiTranButton button8;
    private GuiTranButton button9;

    private GuiTranButton buttonNegate;
    private GuiTranButton buttonPeriod;

    private GuiTranButton buttonDistance;
    private GuiTranButton buttonConvertNether;

    private GuiTranButton buttonBack;

    private CalcGuiTerm terminal;

    private int buttonID;

    public GuiCalc(GuiScreen parentScreen) {
        this.parentScreen = parentScreen;

        buttonID = -1;
    }

    @Override
    public void initGui() {
        setupButtons();

        terminal = new CalcGuiTerm(fontRendererObj, width / 2 - 46, 46, 92, 20);
    }

    @Override
    public void onGuiClosed() {
        Keyboard.enableRepeatEvents(false);
    }

    @Override
    protected void actionPerformed(GuiButton button) throws IOException {
        if (button.enabled) {
            if (button.id >= 0) {
                terminal.insertTerm(button.id);
            } else if (button == buttonAllClear) {
                terminal.clear();
            } else if (button == buttonModulus) {
                terminal.remainder();
            } else if (button == buttonDivide) {
                terminal.divide();
            } else if (button == buttonMultiply) {
                terminal.multiply();
            } else if (button == buttonAdd) {
                terminal.add();
            } else if (button == buttonSubtract) {
                terminal.subtract();
            } else if (button == buttonEquals) {
                terminal.evaluate();
            } else if (button == buttonNegate) {
                terminal.negateTerm();
            } else if (button == buttonPeriod) {
                terminal.insertPeriod();
            }

        }  else if (button == buttonBack) {
                mc.displayGuiScreen(parentScreen);
            }
        }

    @Override
    protected void keyTyped(char typedChar, int keyCode) throws IOException {
        terminal.keyTyped(typedChar, keyCode);
        if (keyCode == Keyboard.KEY_ESCAPE) {
            mc.displayGuiScreen(null);
        }
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        drawDefaultBackground();
        drawCenteredString(fontRendererObj, I18n.format("calculations.calc"), width / 2, 15, 0xffffff);
        terminal.drawScreen(mouseX, mouseY, partialTicks);

        super.drawScreen(mouseX, mouseY, partialTicks);
    }

    @Override
    protected <T extends GuiButton> T addButton(T button) {
        buttonList.add(button);
        buttonID--;
        return button;
    }

    private void setupButtons() {
        buttonList.clear();

        buttonAllClear = addButton(new GuiTranButton(buttonID, width / 2 - 46, 70, 20, 20, "AC"));
        buttonModulus = addButton(new GuiTranButton(buttonID, width / 2 - 22, 70, 20, 20, "%"));
        buttonDivide = addButton(new GuiTranButton(buttonID, width / 2 + 26, 70, 20, 20, "\u00f7"));
        button7 = addButton(new GuiTranButton(7, width / 2 - 46, 94, 20, 20, "7"));
        button8 = addButton(new GuiTranButton(8, width / 2 - 22, 94, 20, 20, "8"));
        button9 = addButton(new GuiTranButton(9, width / 2 + 2, 94, 20, 20, "9"));
        buttonMultiply = addButton(new GuiTranButton(-5, width / 2 + 26, 94, 20, 20, "x"));
        button4 = addButton(new GuiTranButton(4, width / 2 - 46, 118, 20, 20, "4"));
        button5 = addButton(new GuiTranButton(5, width / 2 - 22, 118, 20, 20, "5"));
        button6 = addButton(new GuiTranButton(6, width / 2 + 2, 118, 20, 20, "6"));
        buttonSubtract = addButton(new GuiTranButton(buttonID, width / 2 + 26, 118, 20, 20, "-"));
        button1 = addButton(new GuiTranButton(1, width / 2 - 46, 142, 20, 20, "1"));
        button2 = addButton(new GuiTranButton(2, width / 2 - 22, 142, 20, 20, "2"));
        button3 = addButton(new GuiTranButton(3, width / 2 + 2, 142, 20, 20, "3"));
        buttonAdd = addButton(new GuiTranButton(buttonID, width / 2 + 26, 142, 20, 20, "+"));
        buttonNegate = addButton(new GuiTranButton(buttonID, width / 2 - 46, 166, 20, 20, "\u00b1"));
        button0 = addButton(new GuiTranButton(0, width / 2 - 22, 166, 20, 20, "0"));
        buttonPeriod = addButton(new GuiTranButton(buttonID, width / 2 + 2, 166, 20, 20, "."));
        buttonEquals = addButton(new GuiTranButton(buttonID, width / 2 + 26, 166, 20, 20, "="));
    }

}
