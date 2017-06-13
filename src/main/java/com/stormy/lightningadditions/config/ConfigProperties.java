package com.stormy.lightningadditions.config;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;

import java.util.Map;

public class ConfigProperties {
    private Map<String, IConfigProperty<? extends Object>> properties = Maps.newHashMap();

    public ConfigProperties() {
    }

    public void addProperty(String name, IConfigProperty<?> property) {
        if (name != null && name != "") {
            if (property != null) {
                this.properties.put(name, property);
            } else {
                throw new IllegalArgumentException("Property can't be null!");
            }
        } else {
            throw new IllegalArgumentException("Name can't be null or empty!");
        }
    }

    public IConfigProperty<?> getProperty(String name) {
        if (name != null && name != "") {
            if (this.properties.containsKey(name)) {
                return (IConfigProperty)this.properties.get(name);
            } else {
                throw new IllegalArgumentException("Unknown property!");
            }
        } else {
            throw new IllegalArgumentException("Name can't be null or empty!");
        }
    }

    public ImmutableMap<String, IConfigProperty<?>> getProperties() {
        return ImmutableMap.copyOf(this.properties);
    }
}
