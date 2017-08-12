package com.stormy.lightninglib.lib.utils;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import java.util.*;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraftforge.fml.common.FMLCommonHandler;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class CommandUtils implements ICommand
{
    private String name;
    private String usage;
    private List<String> aliases;
    private int permissionLevel;
    private Map<String, ICommand> subCommands;

    public CommandUtils(String name) {
        this(name, "", Lists.newArrayList(), 0, Lists.newArrayList());
    }

    public CommandUtils(String name, String usage, List<String> aliases, int permissionLevel, List<ICommand> commands) {
        this.name = name;
        this.usage = usage;
        this.aliases = aliases;
        this.permissionLevel = permissionLevel;
        this.subCommands = new HashMap<>();
        commands.forEach(command -> subCommands.put(command.getName(), command));
    }

    @Override
    public boolean isUsernameIndex(@Nonnull String[] args, int index)
    { return false; }

    @Nonnull
    @Override
    public List<String> getTabCompletions(@Nonnull MinecraftServer server, @Nonnull ICommandSender sender, @Nonnull String[] args, @Nullable BlockPos pos)
    { if (subCommands.get(args[0]) != null) { return subCommands.get(args[0]).getTabCompletions(server, sender, args, pos); }
        return Lists.newArrayList(); }

    @Override
    public int compareTo(@Nonnull ICommand otherCommand)
    { return this.getName().compareTo(otherCommand.getName()); }

    public static List<String> filterPrefixes(String prefix, Iterable<String> proposals)
    {
        prefix = prefix.toLowerCase(Locale.ENGLISH);
        List<String> result = Lists.newArrayList();
        for (String s : proposals) if (s.toLowerCase(Locale.ENGLISH).startsWith(prefix)) result.add(s);
        return result; }

    public static List<String> filterPrefixes(String prefix, String... proposals)
    { return filterPrefixes(prefix, Arrays.asList(proposals)); }

    public static List<String> getPlayerNames()
    { MinecraftServer server = FMLCommonHandler.instance().getMinecraftServerInstance();
        if (server != null) return ImmutableList.copyOf(server.getOnlinePlayerNames());
        return ImmutableList.of(); }

    public static List<String> fiterPlayerNames(String prefix)
    { return filterPrefixes(prefix, getPlayerNames()); }

    public static void respondText(ICommandSender sender, String message)
    { sender.sendMessage(new TextComponentString(message)); }

    public static void respond(ICommandSender sender, String format, Object... args)
    { sender.sendMessage(new TextComponentTranslation(format, args)); }

    public static CommandException error(String format, Object... args) throws CommandException
    { throw new CommandException(format, args); }

    @Nonnull
    @Override
    public String getName() { return name; }

    @Nonnull
    @Override
    public String getUsage(@Nonnull ICommandSender sender) { return usage; }

    @Nonnull
    @Override
    public List<String> getAliases() { return aliases; }

    @Override
    public void execute(@Nonnull MinecraftServer server, @Nonnull ICommandSender sender, @Nonnull String[] args) throws CommandException {}

    @Override
    public boolean checkPermission(@Nonnull MinecraftServer server, @Nonnull ICommandSender sender)
    { final boolean[] goodToGood = {true};
        subCommands.forEach((name, subCommand) -> goodToGood[0] &= subCommand.checkPermission(server, sender) );
        return goodToGood[0] && sender.canUseCommand(this.permissionLevel, this.getName()); }


}
