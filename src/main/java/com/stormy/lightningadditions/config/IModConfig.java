package com.stormy.lightningadditions.config;

import com.stormy.lightningadditions.config.IConfigProperty;

public interface IModConfig {
    IConfigProperty<? extends Object> getProperty(String var1);
}
