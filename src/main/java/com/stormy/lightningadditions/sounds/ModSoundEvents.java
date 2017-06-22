/*
 * ********************************************************************************
 * Copyright (c) 2017 StormyMode, MiningMark48. All Rights Reserved!
 * This file is part of Lightning Additions (MC-Mod).
 *
 * This project cannot be copied and/or distributed without the express
 * permission of StormyMode, MiningMark48 (Developers)!
 * ********************************************************************************
 */

package com.stormy.lightningadditions.sounds;

import com.stormy.lightningadditions.reference.ModInformation;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

@SuppressWarnings("WeakerAccess")
public class ModSoundEvents {
    public static SoundEvent philosopher_stone;

    public static void registerSounds() {
        philosopher_stone = registerSound("philosopher_stone");
    }

    private static SoundEvent registerSound(String soundName) {
        final ResourceLocation soundID = new ResourceLocation(ModInformation.MODID, soundName);
        return GameRegistry.register(new SoundEvent(soundID).setRegistryName(soundID));
    }
}
