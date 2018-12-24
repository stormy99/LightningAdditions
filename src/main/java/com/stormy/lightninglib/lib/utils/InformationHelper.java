package com.stormy.lightninglib.lib.utils;

import cofh.redstoneflux.api.IEnergyContainerItem;
import net.minecraft.client.resources.I18n;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextFormatting;
import org.lwjgl.input.Keyboard;

import java.text.DecimalFormat;
import java.util.List;

public class InformationHelper
{
    public static class FormatUtils
    {
        /**
         * Value formatting forked! DecimalFormatting:
         */
        private static DecimalFormat energyValue = new DecimalFormat("###,###,###,###,###");

        public static String formatNumber(double value) {
            if (value < 1000D) return String.valueOf(value);
            else if (value < 1000000D) return String.valueOf(Math.round(value) / 1000D) + "K";
            else if (value < 1000000000D) return String.valueOf(Math.round(value / 1000D) / 1000D) + "M";
            else if (value < 1000000000000D) return String.valueOf(Math.round(value / 1000000D) / 1000D) + "B";
            else return String.valueOf(Math.round(value / 1000000000D) / 1000D) + "T"; }

        public static String formatNumber(long value) {
            if (value < 1000L) return String.valueOf(value);
            else if (value < 1000000L) return String.valueOf(Math.round(value) / 1000D) + "K";
            else if (value < 1000000000L) return String.valueOf(Math.round(value / 1000L) / 1000D) + "M";
            else if (value < 1000000000000L) return String.valueOf(Math.round(value / 1000000L) / 1000D) + "B";
            else if (value < 1000000000000000L) return String.valueOf(Math.round(value / 1000000000L) / 1000D) + "T";
            else if (value < 1000000000000000000L) return String.valueOf(Math.round(value / 1000000000000L) / 1000D) + "Quad";
            else if (value <= Long.MAX_VALUE) return String.valueOf(Math.round(value / 1000000000000000L) / 1000D) + "Quin";
            else return "What on Earth are you doing? Something has gone very wrong!"; }

        /**
         * Add commas to a number e.g. 269969642 > 269,969,642
         */
        public static String addCommas(int value) { return energyValue.format(value); }
        public static String addCommas(long value) { return energyValue.format(value); }

        /**
         * Rounds the number of decimal places based on the given multiplier.<br>
         * e.g.<br>
         * Input: 17.5245743<br>
         * multiplier: 1000<br>
         * Output: 17.534<br>
         * multiplier: 10<br>
         * Output 17.5<br><br>
         *
         * @param number The input value.
         * @param multiplier The multiplier.
         * @return The input rounded to a number of decimal places based on the multiplier.
         */
        public static double round(double number, double multiplier) {
            return Math.round(number * multiplier) / multiplier;
        }

        public static int getNearestMultiple(int number, int multiple) {
            int result = number;

            if (number < 0) result *= -1;

            if (result % multiple == 0) return number;
            else if (result % multiple < multiple / 2) result = result - result % multiple;
            else result = result + (multiple - result % multiple);

            if (number < 0) result *= -1;

            return result;
        }
    }

    @SuppressWarnings("unchecked")
    public static void addEnergyInfo(ItemStack stack, List list) {
        IEnergyContainerItem item = (IEnergyContainerItem)stack.getItem();
        String energy = FormatUtils.formatNumber(item.getEnergyStored(stack));
        String maxEnergy = FormatUtils.formatNumber(item.getMaxEnergyStored(stack));
        list.add(I18n.format("info.ll.charge.txt") + ": " + energy + " / " + maxEnergy + " RF");
    }

    /**
     * KeyChecker for Information (e.g. "Hold Shift - for More Info")
     */
    @SuppressWarnings("unchecked")
    public static boolean holdShiftForDetails(List list, boolean inverted)
    { if (isShiftKeyDown() == inverted)
            list.add(I18n.format("info.ll.holdShiftForDetails.txt", TextFormatting.AQUA + "" + TextFormatting.ITALIC, TextFormatting.RESET + "" + TextFormatting.GRAY));
        return isShiftKeyDown(); }

    public static boolean holdShiftForDetails(List list) { return holdShiftForDetails(list, false); }
    public static boolean isShiftKeyDown() { return Keyboard.isKeyDown(Keyboard.KEY_LSHIFT) || Keyboard.isKeyDown(Keyboard.KEY_RSHIFT); }
    public static boolean isCtrlKeyDown() { return Keyboard.isKeyDown(Keyboard.KEY_LCONTROL) || Keyboard.isKeyDown(Keyboard.KEY_RCONTROL); }
}
