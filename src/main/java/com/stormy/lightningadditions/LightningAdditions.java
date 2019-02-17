package com.stormy.lightningadditions;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.client.MinecraftClient;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.crash.CrashException;
import net.minecraft.util.crash.CrashReport;

public class LightningAdditions implements ModInitializer {

	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.

		System.out.println("[Lightning Additions!]: Initialised");
	}

	public static MinecraftServer getServer() {
		if(FabricLoader.getInstance().getEnvironmentType() == EnvType.CLIENT){
			return MinecraftClient.getInstance().getServer();
		} else {
			return (MinecraftServer) FabricLoader.getInstance().getGameInstance();
		}
	}

	public static void crashReport(String message, Throwable throwable)
	{ throw new CrashException(new CrashReport(message, throwable)); }

}
