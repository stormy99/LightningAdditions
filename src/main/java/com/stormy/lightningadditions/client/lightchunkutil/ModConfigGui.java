package com.stormy.lightningadditions.client.lightchunkutil;

import com.stormy.lightningadditions.reference.ModInformation;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.util.text.translation.I18n;
import net.minecraftforge.common.config.ConfigElement;
import net.minecraftforge.fml.client.config.GuiConfig;
import net.minecraftforge.fml.client.config.IConfigElement;

import java.util.ArrayList;
import java.util.List;

public class ModConfigGui extends GuiConfig {
    public ModConfigGui(GuiScreen parentScreen) {
        super(parentScreen, getConfigElements(), ModInformation.MODID, false, false, I18n.translateToLocal("gui.config."+ ModInformation.MODID+".tile"));
    }

    private static List<IConfigElement> getConfigElements(){
        List<IConfigElement> elements = new ArrayList<>();
        for(String category : ConfigHandler.categories){
            elements.add(new ConfigElement(ConfigHandler.config.getCategory(category).setLanguageKey("config."+ModInformation.MODID+".category."+category)));
        }
        return elements;
    }
}
