/*
 * ********************************************************************************
 * Copyright (c) 2017 StormyMode, MiningMark48. All Rights Reserved!
 * This file is part of Lightning Additions (MC-Mod).
 *
 * This project cannot be copied and/or distributed without the express
 * permission of StormyMode, MiningMark48 (Developers)!
 * ********************************************************************************
 */

package com.stormy.lightningadditions.init;

import com.stormy.lightningadditions.reference.ModInformation;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

import java.util.LinkedList;
import java.util.List;


public class ModSounds {

    public static SoundEvent tachyon_zoom;
    public static SoundEvent philosopher_stone;
    public static SoundEvent water_place;
    public static SoundEvent void_block;
    public static SoundEvent record_eleventh;
    public static String load_finished;
    public static SoundEvent sonic_screwdriver;

    private static List<SoundEvent> soundEventList = new LinkedList<>();

    public static void registerSounds()
    {
        tachyon_zoom = createSound("tachyon_zoom");
        void_block = createSound("teleport");
        philosopher_stone = createSound("philosopher_stone");
        water_place = createSound("water_place");
        void_block = createSound("void_block");
        record_eleventh = createSound("record_eleventh");
        sonic_screwdriver = createSound("sonic_screwdriver");

        registerSound(tachyon_zoom);
        registerSound(void_block);
        registerSound(philosopher_stone);
        registerSound(water_place);
        registerSound(void_block);
        registerSound(record_eleventh);
        registerSound(sonic_screwdriver);
    }

    private static SoundEvent createSound(String soundName){
        final ResourceLocation soundID = new ResourceLocation(ModInformation.MODID, soundName);
        return new SoundEvent(soundID).setRegistryName(soundID);
    }

    private static void registerSound(SoundEvent event){
        soundEventList.add(event);
    }

    @SubscribeEvent
    public void registerSoundEvent(RegistryEvent.Register<SoundEvent> event){
        soundEventList.forEach(event.getRegistry()::register);
    }

}
