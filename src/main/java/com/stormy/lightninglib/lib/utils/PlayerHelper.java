package com.stormy.lightninglib.lib.utils;

import com.mojang.authlib.GameProfile;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.server.management.PlayerProfileCache;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
import org.apache.commons.lang3.StringUtils;

import javax.annotation.Nonnull;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class PlayerHelper
{
    public static void writeProfileToNBT(GameProfile profile, NBTTagCompound tag)
    { tag.setString("Name", profile.getName());
        UUID id = profile.getId();
        if (id != null)
        {
            tag.setLong("UUIDL", id.getLeastSignificantBits());
            tag.setLong("UUIDU", id.getMostSignificantBits()); }
    }

    public static NBTTagCompound getModPlayerPersistTag(EntityPlayer player, String modName) {

        NBTTagCompound tag = player.getEntityData();

        NBTTagCompound persistTag = null;
        if (tag.hasKey(EntityPlayer.PERSISTED_NBT_TAG)) {
            persistTag = tag.getCompoundTag(EntityPlayer.PERSISTED_NBT_TAG);
        } else {
            persistTag = new NBTTagCompound();
            tag.setTag(EntityPlayer.PERSISTED_NBT_TAG, persistTag);
        }

        NBTTagCompound modTag = null;
        if (persistTag.hasKey(modName)) {
            modTag = persistTag.getCompoundTag(modName);
        } else {
            modTag = new NBTTagCompound();
            persistTag.setTag(modName, modTag);
        }

        return modTag;
    }

    public static GameProfile profileFromNBT(NBTTagCompound tag)
    { String name = tag.getString("Name");
        UUID uuid = null;
        if (tag.hasKey("UUIDL")) { uuid = new UUID(tag.getLong("UUIDU"), tag.getLong("UUIDL")); }
        else if (StringUtils.isBlank(name)) { return null; }
        return new GameProfile(uuid, name); }

    public static NBTTagCompound proifleToNBT(GameProfile profile)
    { NBTTagCompound tag = new NBTTagCompound();
        tag.setString("Name", profile.getName());
        UUID id = profile.getId();
        if (id != null)
        {
            tag.setLong("UUIDL", id.getLeastSignificantBits());
            tag.setLong("UUIDU", id.getMostSignificantBits()); }
        return tag; }

    public static EntityPlayer getPlayerFromWorld(World world, UUID player)
    { if (player == null) { return null; }
        List<EntityPlayer> players = world.playerEntities;
        for (EntityPlayer entityPlayer : players)
        { if (entityPlayer.getUniqueID().compareTo(player) != 0) { continue; }
            return entityPlayer; }
        return null; }

    public static double getBlockReachDistance(EntityPlayerMP player)
    { return player.interactionManager.getBlockReachDistance(); }

    public static Vec3d getCorrectedHeadVec(EntityPlayer player)
    { double yCoord = player.posY;
        if(player.getEntityWorld().isRemote)
        { yCoord += (player.getEyeHeight() - player.getDefaultEyeHeight()); }
        else { yCoord += player.getEyeHeight();
            if(player instanceof EntityPlayerMP && player.isSneaking())
            { yCoord -= 0.08D; }
        }
        return new Vec3d(player.posX, yCoord, player.posZ); }

    public static void sendMessageToPlayer(@Nonnull EntityPlayer player, String s)
    { sendMessageToPlayer(player, new TextComponentString(s)); }

    public static void sendMessageToPlayer(@Nonnull EntityPlayer player, ITextComponent s)
    { player.sendMessage(s); }

    public static UUID getPlayerUUID(EntityPlayer player)
    { return player.getGameProfile().getId(); }

    public static boolean arePlayersEqual(EntityPlayer player1, EntityPlayer player2)
    { return player1.getUniqueID() == player2.getUniqueID(); }

    public static void activateFlight(EntityPlayer player)
    { player.capabilities.allowFlying = true;
        player.sendPlayerAbilities(); }

    public static void deactivateFlight(EntityPlayer player)
    { player.capabilities.allowFlying = false;
        if (player.capabilities.isFlying)
            player.capabilities.isFlying = false;
        player.sendPlayerAbilities(); }

    public static void smiteEntity(EntityPlayer attacker, EntityLivingBase target)
    { target.setHealth(0);
    target.onDeath(DamageSource.causePlayerDamage(attacker));
    target.setDead(); }

    public static boolean isPlayerInCreative(EntityPlayer player){
        return player.capabilities.isCreativeMode;
    }
}
