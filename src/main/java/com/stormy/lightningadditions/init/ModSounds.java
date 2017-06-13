package com.stormy.lightningadditions.init;

import com.stormy.lightningadditions.reference.ModInformation;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;


public class ModSounds {

    public static SoundEvent teleport;

    public static void registerSounds() {
        teleport = registerSound("teleport");
    }

    private static SoundEvent registerSound(String soundName) {
        ResourceLocation loc = new ResourceLocation(ModInformation.MODID ,soundName);
        SoundEvent e = new SoundEvent(loc);
        return GameRegistry.register(e, loc);
    }

}
