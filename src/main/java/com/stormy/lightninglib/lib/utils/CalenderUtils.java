package com.stormy.lightninglib.lib.utils;

import java.util.Calendar;

public class CalenderUtils
{
    public static boolean xmas;
    public static boolean halloween;
    public static boolean newyear;

    public static void loadCalender()
    {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());

        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int month = calendar.get(Calendar.MONTH) + 1; //Months start at 0

        /**
         * Christmas Checker
         */
        if (month == 12) { if (day >= 20 && day <= 30)
        { xmas = true;
          LogUtils.info("Happy Holidays! Lotta love from the LightningCoders! \\o//"); } }

        /**
         * Halloween Checker
         */
        else if (month == 10) {
            if (day >= 26 && day <= 31) {
                halloween = true;
                LogUtils.info("Happy Halloween! Best Jumpscares, love from the LightningCoders! \\o//"); } }

        /**
         * New Year Checker
         */
         else if (month == 1) {
            if (day >= 1 && day <= 4) {
                newyear = true;
                LogUtils.info("Happy New Year! Best wishes, from the LightningCoders! \\o//"); } }


    }

}
