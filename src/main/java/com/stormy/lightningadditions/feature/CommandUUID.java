package com.stormy.lightningadditions.feature;

import com.stormy.lightningadditions.reference.ModInformation;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiConfirmOpenLink;
import net.minecraft.client.gui.GuiYesNoCallback;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import org.lwjgl.input.Keyboard;
import javax.annotation.Nonnull;
import java.awt.*;
import java.awt.datatransfer.ClipboardOwner;
import java.awt.datatransfer.StringSelection;

import static com.stormy.lightninglib.lib.utils.StringHelper.BOLD;
import static com.stormy.lightninglib.lib.utils.StringHelper.ITALIC;

public class CommandUUID extends CommandBase implements GuiYesNoCallback {
    public CommandUUID()
    {}

    private static String UUID = String.valueOf(Minecraft.getMinecraft().getSession().getProfile().getId());

    @Override
    public String getName() { return "la_uuid"; }

    @Override
    public String getUsage(ICommandSender sender) { return "/la_uuid"; }

    @Override
    public void execute(@Nonnull MinecraftServer server, @Nonnull ICommandSender sender, @Nonnull String[] args) throws CommandException {

        if(sender instanceof EntityPlayer)
        { sender.sendMessage(new TextComponentString(TextFormatting.AQUA + ITALIC + BOLD + "Username: " + Minecraft.getMinecraft().getSession().getProfile().getName()));
          sender.sendMessage(new TextComponentString(TextFormatting.DARK_AQUA + ITALIC + BOLD + "UUID: " + Minecraft.getMinecraft().getSession().getProfile().getId()));

              Minecraft mc = Minecraft.getMinecraft();
              GuiConfirmOpenLink gui = new GuiConfirmOpenLink(this, UUID, 0, true);
              gui.disableSecurityWarning();
              mc.displayGuiScreen(gui); }
    }

    @Override
    public boolean isUsernameIndex(String[] args, int index) { return false; }

    @Override
    public int getRequiredPermissionLevel() { return 0; }

    @Override
    public boolean checkPermission(MinecraftServer server, ICommandSender sender) { return sender instanceof EntityPlayer; }

    private void CopyToClipboard(String uuid) { Toolkit.getDefaultToolkit().getSystemClipboard().setContents(new StringSelection(uuid), (ClipboardOwner)null); }

    @Override
    public void confirmClicked(boolean result, int id) { CopyToClipboard(UUID); }

    public static class CopyKey
    {
        private static KeyBinding copyKey = new KeyBinding("key.copyKey", Keyboard.KEY_LCONTROL + Keyboard.KEY_C, "key." + ModInformation.MODID + ".category");
        private static final Minecraft mc = Minecraft.getMinecraft();

        public static void init() {
            ClientRegistry.registerKeyBinding(copyKey);
            MinecraftForge.EVENT_BUS.register(new CopyKey()); }

        }


}
