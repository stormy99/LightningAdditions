package com.stormy.lightningadditions.init;

import com.stormy.lightningadditions.reference.ModInformation;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;


public class ModSounds {

    public static SoundEvent tachyon_zoom;
    public static SoundEvent philosopher_stone;
    public static SoundEvent water_place;

    public static void registerSounds() {
        tachyon_zoom = registerSound("tachyon_zoom");
        philosopher_stone = registerSound("philosopher_stone");
        water_place = registerSound("water_place");
    }

    private static SoundEvent registerSound(String soundName) {
        ResourceLocation loc = new ResourceLocation(ModInformation.MODID ,soundName);
        SoundEvent e = new SoundEvent(loc);
        return GameRegistry.register(e, loc);
    }

}
