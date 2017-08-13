/*
 * ********************************************************************************
 * Copyright (c) 2017 StormyMode, MiningMark48. All Rights Reserved!
 * This file is part of Lightning Additions (MC-Mod).
 *
 * This project cannot be copied and/or distributed without the express
 * permission of StormyMode, MiningMark48 (Developers)!
 * ********************************************************************************
 */

package com.stormy.lightninglib.lib.utils;

@SuppressWarnings("deprecation")
public class TranslateUtils {

    public static String toLocal(String key){
        return net.minecraft.util.text.translation.I18n.translateToLocal(key);
    }

    public static String toLocal(){
        return "LA Error: Translation Missing, report to mod-author!";
    }

}
