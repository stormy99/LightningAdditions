package com.stormy.lightningadditions.creativetab;

import com.stormy.lightningadditions.config.ConfigurationHandler;
import com.stormy.lightningadditions.init.ModItems;
import com.stormy.lightningadditions.reference.ModInformation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;

public class CreativeTabLA{

    public static final CreativeTabs LA_TAB = new CreativeTabs(ModInformation.MODID) {

        @Override
        public ItemStack getTabIconItem() {
            return new ItemStack(ModItems.tachyon_shard);
        }

        @Override
        public boolean hasSearchBar() {
            return ConfigurationHandler.creativeTabSearchBar;
        }
    };

}
