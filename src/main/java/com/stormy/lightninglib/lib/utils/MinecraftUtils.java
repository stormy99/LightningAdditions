package com.stormy.lightninglib.lib.utils;

import java.io.File;

import com.mojang.authlib.minecraft.MinecraftProfileTexture;
import com.stormy.lightningadditions.proxy.ClientProxy;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.renderer.entity.RenderPlayer;
import net.minecraft.launchwrapper.Launch;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class MinecraftUtils
{
    public static boolean isSSP(MinecraftServer server)
    { return server.getServer().isSinglePlayer(); }

    public static boolean isSMP(MinecraftServer server)
    { return !isSSP(server); }

    public static boolean isClient()
    { return FMLCommonHandler.instance().getSide().isClient(); }

    public static boolean isServer()
    { return !isClient(); }

    public static boolean isDeobf()
    { return (boolean) Launch.blackboard.get("fml.deobfuscatedEnvironment"); }

    @SideOnly(Side.CLIENT)
    public static Minecraft mc()
    { return Minecraft.getMinecraft(); }

    @SideOnly(Side.CLIENT)
    public static World getWorld()
    { return MappingUtils.world(); }

    public File getDataDir()
    { return mc().mcDataDir; }

    @SideOnly(Side.CLIENT)
    public static class ClientUtils
    {
        /**
         * Update the current time.
         */
        public static void updateLast() { ClientUtils.lastUpdate = System.currentTimeMillis(); }
        private static long lastUpdate = System.currentTimeMillis();

        /**
         * Update a value with the default factor of 0.5.
         */
        public static float updateValue(float current, float target)
        { return ClientUtils.updateValue(current, target, 0.5F); }

        /**
         * Update a value with a custom factor. How higher the factor, how slower the update.
         */
        public static float updateValue(float current, float target, float factor) {
            float times = (System.currentTimeMillis() - ClientUtils.lastUpdate) / 16.666666666666668F;
            float off = (off = target - current) > 0.01F || off < -0.01F ? off * (float) Math.pow(factor, times) : 0.0F;
            return target - off;
        }

        public static float interpolate(float prev, float current, float partialTicks)
        { return prev + partialTicks * (current - prev); }
        public static float interpolateRotation(float prev, float current, float partialTicks)
        { float shortest = ((current - prev) % 360 + 540) % 360 - 180;
            return prev + shortest * partialTicks; }

    }

}
