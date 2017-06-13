package com.stormy.lightningadditions.reference;

public class Translate {

    public static String toLocal(String key){
        return net.minecraft.util.text.translation.I18n.translateToLocal(key);
    }

    public static String toLocal(){
        return "Error: Missing, report to mod-author (@StormyMode).";
    }

}
