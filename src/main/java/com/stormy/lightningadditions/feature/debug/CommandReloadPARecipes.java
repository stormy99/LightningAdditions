/*
 *
 *  * ********************************************************************************
 *  * Copyright (c) 2017 StormyMode, MiningMark48. All Rights Reserved!
 *  * This file is part of Lightning Additions (MC-Mod).
 *  *
 *  * This project cannot be copied and/or distributed without the express
 *  * permission of StormyMode, MiningMark48 (Developers)!
 *  * ********************************************************************************
 *
 */

package com.stormy.lightningadditions.feature.debug;

import com.stormy.lightningadditions.crafting.RegistryParticleAccelerator;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.text.TextComponentString;

import java.util.ArrayList;
import java.util.List;

import static com.stormy.lightninglib.lib.utils.StringHelper.*;
import static net.minecraft.util.text.TextFormatting.AQUA;

public class CommandReloadPARecipes extends CommandBase{

    private final List aliases;

    private final String prefix = "la";

    public CommandReloadPARecipes(){
        aliases = new ArrayList();
        aliases.add(String.format("%s_pareload", prefix));
    }

    @Override
    public String getName() {
        return String.format("%s_pareload", prefix);
    }



    @Override
    public String getUsage(ICommandSender sender) {
        return String.format("%s_pareload", prefix);
    }

    @Override
    public List<String> getAliases() {
        return this.aliases;
    }

    @Override
    public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
        sender.sendMessage(new TextComponentString(AQUA + ITALIC + "Reloading recipes... " + END + LIGHT_RED + "Potential Lag incoming."));
        RegistryParticleAccelerator.instance().registerRecipes();
    }

    @Override
    public int getRequiredPermissionLevel() {
        return 2;
    }

    @Override
    public boolean isUsernameIndex(String[] args, int index) {
        return false;
    }

    @Override
    public int compareTo(ICommand o) {
        return 0;
    }
}
