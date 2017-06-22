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

import com.google.common.collect.UnmodifiableIterator;
import java.io.File;
import java.util.Map.Entry;

import com.stormy.lightningadditions.utility.logger.IModLogger;
import net.minecraftforge.common.config.Configuration;
import org.apache.commons.lang3.time.StopWatch;
import org.apache.commons.lang3.tuple.Pair;

public class SimpleModConfig implements IModConfig {
    private ConfigProperties properties;

    public SimpleModConfig(File file, ConfigCategories categories, ConfigProperties properties, IModLogger logger) {
        Configuration config = new Configuration(file);
        StopWatch timer = new StopWatch();
        logger.logInfo("Loading config...");
        timer.start();
        config.load();
        UnmodifiableIterator var7;
        if (categories != null && !categories.getCategories().isEmpty()) {
            var7 = categories.getCategories().iterator();

            while(var7.hasNext()) {
                Pair<String, String> category = (Pair)var7.next();
                config.addCustomCategoryComment((String)category.getLeft(), (String)category.getRight());
            }
        }

        if (properties != null && !properties.getProperties().isEmpty()) {
            var7 = properties.getProperties().entrySet().iterator();

            while(var7.hasNext()) {
                Entry<String, IConfigProperty<?>> property = (Entry)var7.next();
                ((IConfigProperty)property.getValue()).addProperty(config);
            }
        }

        config.save();
        timer.stop();
        logger.logInfo("Done loading config in " + timer.getTime() + "ms!");
        this.properties = properties;
    }

    public IConfigProperty<? extends Object> getProperty(String name) {
        return this.properties.getProperty(name);
    }
}
