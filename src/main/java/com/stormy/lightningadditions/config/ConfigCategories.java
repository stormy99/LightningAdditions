/*
 * ********************************************************************************
 * Copyright (c) 2017 StormyMode, MiningMark48. All Rights Reserved!
 * This file is part of Lightning Additions (MC-Mod).
 *
 * This project cannot be copied and/or distributed without the express
 * permission of StormyMode, MiningMark48 (Developers)!
 * ********************************************************************************
 */

package com.stormy.lightningadditions.config;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import org.apache.commons.lang3.tuple.Pair;

import java.util.List;

public class ConfigCategories {
    private List<Pair<String, String>> categories = Lists.newArrayList();

    public ConfigCategories() {
    }

    public void addCategory(String name, String comment) {
        if (name != null && name != "") {
            this.categories.add(Pair.of(name, comment));
        } else {
            throw new IllegalArgumentException("Category name can't be null or empty!");
        }
    }

    public ImmutableList<Pair<String, String>> getCategories() {
        return ImmutableList.copyOf(this.categories);
    }
}
