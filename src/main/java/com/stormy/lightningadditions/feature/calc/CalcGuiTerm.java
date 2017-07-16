/*
 * ********************************************************************************
 * Copyright (c) 2017 StormyMode, MiningMark48. All Rights Reserved!
 * This file is part of Lightning Additions (MC-Mod).
 *
 * This project cannot be copied and/or distributed without the express
 * permission of StormyMode, MiningMark48 (Developers)!
 * ********************************************************************************
 */

package com.stormy.lightningadditions.feature.calc;

import com.stormy.lightningadditions.feature.calc.Expression.ExpressionException;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiScreen;
import org.lwjgl.input.Keyboard;

import java.io.IOException;

public class CalcGuiTerm extends Gui {

    private static final Minecraft mc = Minecraft.getMinecraft();

    private static String expression = "";
    private static String term = "";
    private static String operator = "";
    private static boolean errored = false;

    private FontRenderer fontRenderer;
    private int xPosition;
    private int yPosition;
    private int width;
    private int height;

    public CalcGuiTerm(FontRenderer fontRenderer, int x, int y, int width, int height) {
        this.fontRenderer = fontRenderer;
        this.xPosition = x;
        this.yPosition = y;
        this.width = width;
        this.height = height;
    }

    protected void keyTyped(char typedChar, int keyCode) throws IOException {
        if (GuiScreen.isShiftKeyDown()) {
            if (keyCode == Keyboard.KEY_EQUALS) {
                add();
            } else if (keyCode == Keyboard.KEY_8) {
                multiply();
            } else if (keyCode == Keyboard.KEY_5) {
                remainder();
            }
        } else {
            if (keyCode == Keyboard.KEY_0 || keyCode == Keyboard.KEY_NUMPAD0) {
                insertTerm(0);
            } else if (keyCode == Keyboard.KEY_1 || keyCode == Keyboard.KEY_NUMPAD1) {
                insertTerm(1);
            } else if (keyCode == Keyboard.KEY_2 || keyCode == Keyboard.KEY_NUMPAD2) {
                insertTerm(2);
            } else if (keyCode == Keyboard.KEY_3 || keyCode == Keyboard.KEY_NUMPAD3) {
                insertTerm(3);
            } else if (keyCode == Keyboard.KEY_4 || keyCode == Keyboard.KEY_NUMPAD4) {
                insertTerm(4);
            } else if (keyCode == Keyboard.KEY_5 || keyCode == Keyboard.KEY_NUMPAD5) {
                insertTerm(5);
            } else if (keyCode == Keyboard.KEY_6 || keyCode == Keyboard.KEY_NUMPAD6) {
                insertTerm(6);
            } else if (keyCode == Keyboard.KEY_7 || keyCode == Keyboard.KEY_NUMPAD7) {
                insertTerm(7);
            } else if (keyCode == Keyboard.KEY_8 || keyCode == Keyboard.KEY_NUMPAD8) {
                insertTerm(8);
            } else if (keyCode == Keyboard.KEY_9 || keyCode == Keyboard.KEY_NUMPAD9) {
                insertTerm(9);
            } else if (keyCode == Keyboard.KEY_PERIOD || keyCode == Keyboard.KEY_DECIMAL) {
                insertPeriod();
            } else if (keyCode == Keyboard.KEY_SUBTRACT) {
                subtract();
            } else if (keyCode == Keyboard.KEY_SLASH) {
                divide();
            } else if (keyCode == Keyboard.KEY_EQUALS) {
                evaluate();
            }
        }

        if (keyCode == Keyboard.KEY_C) {
            clear();
        } else if (keyCode == Keyboard.KEY_ADD) {
            add();
        } else if (keyCode == Keyboard.KEY_MINUS) {
            subtract();
        } else if (keyCode == Keyboard.KEY_MULTIPLY || keyCode == Keyboard.KEY_X) {
            multiply();
        } else if (keyCode == Keyboard.KEY_DIVIDE) {
            divide();
        } else if (keyCode == Keyboard.KEY_RETURN || keyCode == Keyboard.KEY_NUMPADENTER) {
            evaluate();
        } else if (keyCode == Keyboard.KEY_BACK) {
            back();
        }
    }

    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        RenderUtilsCalc.drawRect(xPosition, yPosition, xPosition + width, yPosition + height, 255 / 2 << 24);
        final String trimmed = fontRenderer.trimStringToWidth(getTermOperatorPair(), width - 10, true);
        fontRenderer.drawString(trimmed, xPosition + width - 5 - fontRenderer.getStringWidth(trimmed), yPosition + 5, 0xffffff);
    }

    public void error() {
        term = "Error";
        expression = "";
        operator = "";
    }

    public void insertTerm(String s) {
        if (!errored) {
            if (!hasTerm() || !hasOperator()) {
                term += s;
            } else {
                expression += term + operator;
                term = s;
                operator = "";
            }
        }
    }

    public void insertTerm(String s, int index) {
        if (!errored) {
            if (!hasTerm() || !hasOperator()) {
                term = new StringBuilder(term).insert(index, s).toString();
            } else {
                expression += term + operator;
                term = s;
                operator = "";
            }
        }
    }

    public void insertTerm(int i) {
        insertTerm(String.valueOf(i));
    }

    public void setOperator(String s) {
        if (!errored && hasTerm()) {
            operator = s;
        }
    }

    public void insertPeriod() {
        if (!hasTerm() || hasOperator()) {
            insertTerm("0.");
        } else if (!term.contains(".")) {
            insertTerm(".");
        }
    }

    public void add() {
        setOperator("+");
    }

    public void subtract() {
        setOperator("-");
    }

    public void multiply() {
        setOperator("*");
    }

    public void divide() {
        setOperator("/");
    }

    public void remainder() {
        setOperator("%");
    }

    public void clear() {
        term = "";
        operator = "";
        expression = "";
    }

    public void negateTerm() {
        if (term.startsWith("-")) {
            term = term.substring(1);
        } else {
            insertTerm("-", 0);
        }
    }

    public void back() {
        if (hasOperator()) {
            operator = operator.substring(0, operator.length() - 1);
        } else if (hasTerm()) {
            term = term.substring(0, term.length() - 1);
        }
    }

    public void evaluate() {
        try {
            expression += term + operator;
            final double result = new Expression(expression).eval().doubleValue();
            clear();
            if ((result % 1) == 0) {
                term = String.valueOf((int) result);
            } else {
                term = String.valueOf(result);
            }
        } catch (ExpressionException e) {
            error();
        }
    }

    public boolean hasTerm() {
        return !term.isEmpty();
    }

    public boolean hasOperator() {
        return !operator.isEmpty();
    }

    public static String getTermOperatorPair() {
        if (!errored) {
            return term + " " + operator;
        }

        return "Error";
    }

}
