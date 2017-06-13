package com.stormy.lightningadditions.config;

import net.minecraftforge.common.config.Configuration;

public interface IConfigProperty<T> {
    void addProperty(Configuration var1);

    T getValue();
}
