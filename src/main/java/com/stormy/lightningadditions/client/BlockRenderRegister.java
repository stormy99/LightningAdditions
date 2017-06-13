package com.stormy.lightningadditions.client;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;

import com.stormy.lightningadditions.LightningAdditions;

public interface BlockRenderRegister
{
    void reg(Block block);
}
