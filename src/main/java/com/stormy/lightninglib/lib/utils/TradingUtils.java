package com.stormy.lightninglib.lib.utils;

import net.minecraft.entity.passive.EntityVillager.ListItemForEmeralds;
import net.minecraft.entity.passive.EntityVillager.PriceInfo;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.VillagerRegistry.VillagerCareer;

public class TradingUtils
{ private TradingUtils() {}

    public static void addSellTrade(VillagerCareer career, ItemStack forSale, int minPrice, int maxPrice)
    { addSellTrade(1, career, forSale, minPrice, maxPrice); }

    public static void addSellTrade(int level, VillagerCareer career, ItemStack forSale, int minPrice, int maxPrice)
    { career.addTrade(level, new ListItemForEmeralds(forSale, new PriceInfo(minPrice, maxPrice))); }
}
