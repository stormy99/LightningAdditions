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

import com.stormy.lightninglib.lib.utils.StringHelper;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.World;

import static com.stormy.lightninglib.lib.utils.StringHelper.*;

public class CommandToggleDownfall extends CommandBase
{
    @Override
    public String getName() { return "toggledownfall"; }

    @Override
    public int getRequiredPermissionLevel() { return 2; }

    @Override
    public String getUsage(ICommandSender icommandsender) { return "/toggledownfall"; }

    @Override
    public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException
    { Integer dimension = null;
        if (args.length > 0) { dimension = Integer.valueOf(parseInt(args[0])); }
        if (dimension == null) { dimension = getSenderDimension(sender); }
        if (dimension != null)
        {
            toggleDownfall(server, dimension.intValue());
            sendToAdmins(sender, BOLD + ITALIC + "Toggle Downfall: Success!", new Object[] { dimension }); }
        else { throw new CommandException(RED + BOLD + ITALIC + "Toggle Downfall: Failed!", new Object[0]); } }

    private void sendToAdmins(ICommandSender agent, String text, Object[] objects)
    { notifyCommandListener(agent, this, text, objects); }

    private static Integer getSenderDimension(ICommandSender sender) throws CommandException
    { World w = sender.getEntityWorld();
        if (w == null) { throw new CommandException("You must specify a dimension to use this command from the commandline", new Object[0]); }
        return Integer.valueOf(w.provider.getDimension()); }


    protected void toggleDownfall(MinecraftServer server, int dimension)
    {
        World world = server.worldServerForDimension(dimension);
        world.getWorldInfo().setRaining(!world.isRaining());
        world.getWorldInfo().setThundering(true); }
}
