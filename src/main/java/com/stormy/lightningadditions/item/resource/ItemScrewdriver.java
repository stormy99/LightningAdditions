/*
 * ********************************************************************************
 * Copyright (c) 2017 StormyMode, MiningMark48. All Rights Reserved!
 * This file is part of Lightning Additions (MC-Mod).
 *
 * This project cannot be copied and/or distributed without the express
 * permission of StormyMode, MiningMark48 (Developers)!
 * ********************************************************************************
 */

package com.stormy.lightningadditions.item.resource;

import cofh.block.IDismantleable;
import cofh.item.IToolHammer;
import com.stormy.lightningadditions.init.ModSounds;
import com.stormy.lightningadditions.item.base.ItemLA;
import com.stormy.lightninglib.lib.utils.KeyChecker;
import com.stormy.lightninglib.lib.utils.TranslateUtils;
import com.stormy.lightningadditions.utility.inventory.BlockHelper;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.network.NetHandlerPlayClient;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityZombieVillager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.play.client.CPacketPlayerTryUseItemOnBlock;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.fml.client.FMLClientHandler;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;
import net.minecraftforge.fml.common.eventhandler.Event;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;
import java.util.List;


public class ItemScrewdriver extends ItemLA implements IToolHammer
{

//UNI 0
    //TODO [Open Iron Door Code] - Universal Mode
    //TODO [Detonation of Objects/Circuitry] - Universal Mode
//TRAK 1
    //TODO [Player Tracking mode?] - Tracking Mode
    //TODO [Bed/Home Tracker?] - Tracking Mode

    /**
     * Current List of functions implemented:
     * --> Curing Infected Villagers
     * --> Rotation of Blocks
     * --> Defensive Mechanism on swing
     */

    public static class ServerHelper
    {   //Determination of ServerWorld or not
        private ServerHelper() {}
        public static boolean isServerWorld(World world) {return !world.isRemote;}
        public static void sendItemUsePacket(World world, BlockPos pos, EnumFacing hitSide, EnumHand hand, float hitX, float hitY, float hitZ)
        {if (isServerWorld(world)) {return;}
            NetHandlerPlayClient netClientHandler = (NetHandlerPlayClient) FMLClientHandler.instance().getClientPlayHandler();
            netClientHandler.sendPacket(new CPacketPlayerTryUseItemOnBlock(pos, hitSide, hand, hitX, hitY, hitZ));}
    }

    @Override
    @SideOnly(Side.CLIENT)
    public EnumRarity getRarity(ItemStack par1ItemStack) {
        return EnumRarity.RARE;
    }

    public ItemScrewdriver()
    {
        this.setNoRepair();
        this.setMaxStackSize(1);
        this.setContainerItem(this);
        this.setHarvestLevel("sonic", 1);
    }

    @Override
    public boolean doesSneakBypassUse(ItemStack stack, IBlockAccess world, BlockPos pos, EntityPlayer player)
    {return true;}

    @Override
    public EnumActionResult onItemUseFirst(EntityPlayer player, World world, BlockPos pos, EnumFacing side, float hitX, float hitY, float hitZ, EnumHand hand) {

        IBlockState state = world.getBlockState(pos);
        Block block = state.getBlock();

        if (world.isAirBlock(pos)) {
            return EnumActionResult.PASS;
        }
        PlayerInteractEvent event = new PlayerInteractEvent.RightClickBlock(player, hand, pos, side, new Vec3d(hitX, hitY, hitZ));
        if (MinecraftForge.EVENT_BUS.post(event) || event.getResult() == Event.Result.DENY) {
            return EnumActionResult.PASS;
        }

        if (ServerHelper.isServerWorld(world) && player.isSneaking() && block instanceof IDismantleable && ((IDismantleable) block).canDismantle(world, pos, state, player)) {
            ((IDismantleable) block).dismantleBlock(world, pos, state, player, false);
            return EnumActionResult.SUCCESS;
        }

        if (BlockHelper.canRotate(block)) {
            world.setBlockState(pos, BlockHelper.rotateVanillaBlock(world, state, pos), 3);
            player.swingArm(hand);
            return ServerHelper.isServerWorld(world) ? EnumActionResult.SUCCESS : EnumActionResult.PASS;
        } else if (!player.isSneaking() && block.rotateBlock(world, pos, side)) {
            player.swingArm(hand);
            return ServerHelper.isServerWorld(world) ? EnumActionResult.SUCCESS : EnumActionResult.PASS;
        }
        return EnumActionResult.PASS;
    }

    @Override
    public boolean onEntitySwing(EntityLivingBase entityLiving, ItemStack stack) {
        final EntityPlayer player = entityLiving instanceof EntityPlayer ? ((EntityPlayer) entityLiving) : null;
        player.playSound(ModSounds.sonic_screwdriver, 0.8f, 1.0f);
        return false;
    }

    public boolean canSonic(EntityPlayer player, EnumHand hand, ItemStack sonic, RayTraceResult rayTrace)
    {ItemStack stack = player.getHeldItemMainhand(); return true;}

    public void sonicUsed(EntityPlayer player, EnumHand hand, ItemStack sonic, RayTraceResult rayTrace)
    {}

    public boolean canWrench(EntityPlayer player, EnumHand hand, ItemStack wrench, RayTraceResult rayTrace)
    {ItemStack stack = player.getHeldItemMainhand(); return true;}

    public void wrenchUsed(EntityPlayer player, EnumHand hand, ItemStack wrench, RayTraceResult rayTrace)
    {}

    @Override
    public boolean isUsable(ItemStack item, EntityLivingBase user, BlockPos pos) {return true;}
    @Override
    public boolean isUsable(ItemStack item, EntityLivingBase user, Entity entity) {return true;}
    @Override
    public void toolUsed(ItemStack item, EntityLivingBase user, BlockPos pos) {}
    @Override
    public void toolUsed(ItemStack item, EntityLivingBase user, Entity entity) {}

    @Override
    public boolean isFull3D()
    {return true;}

    public boolean hitEntity(ItemStack par1ItemStack, EntityLivingBase par2EntityLivingBase, EntityLivingBase par3EntityLivingBase)
    {   par2EntityLivingBase.addPotionEffect(new PotionEffect(Potion.getPotionById(9), 700, 1));  //Nausea
        par2EntityLivingBase.addPotionEffect(new PotionEffect(Potion.getPotionById(20), 50 * 5, 1)); //Wither
        par2EntityLivingBase.addPotionEffect(new PotionEffect(Potion.getPotionById(15), 700, 1)); //Blindness
        par2EntityLivingBase.addPotionEffect(new PotionEffect(Potion.getPotionById(2), Integer.MAX_VALUE , 1)); //Slowness
        return true; }

    private void startConverting(EntityZombieVillager v, int t) {
        ObfuscationReflectionHelper.setPrivateValue(EntityZombieVillager.class, v, t, "conversionTime", "field_82234_d");
        v.addPotionEffect(new PotionEffect(MobEffects.STRENGTH, t, Math.min(v.world.getDifficulty().getDifficultyId() - 1, 0)));
        v.world.setEntityState(v, (byte) 16);
        try {
            DataParameter<Boolean> CONVERTING = ObfuscationReflectionHelper.getPrivateValue(EntityZombieVillager.class, v, "CONVERTING", "field_184739_bx");
            v.getDataManager().set(CONVERTING, Boolean.valueOf(true));
        }
        catch (Exception e) {}
    }

    private static final int CONVTIME = 125;

    @Override
    public boolean itemInteractionForEntity(ItemStack itemstack, EntityPlayer player, EntityLivingBase entity, EnumHand hand) {

        if (entity instanceof EntityZombieVillager) {
            EntityZombieVillager zombie = ((EntityZombieVillager) entity);
            startConverting(zombie, CONVTIME);
            player.playSound(ModSounds.sonic_screwdriver, 0.8f, 1.0f);
            return true; }
        return super.itemInteractionForEntity(itemstack, player, entity, hand);}

    //Custom Tooltip
    @Override
    public void addInformation(ItemStack par1ItemStack, @Nullable World world, List par3List, ITooltipFlag par4) {
        if (KeyChecker.isHoldingShift()) { par3List.add(TextFormatting.ITALIC + TranslateUtils.toLocal("tooltip.item.sonic_screwdriver.line1"));}
        else{ par3List.add(TranslateUtils.toLocal("tooltip.item.hold") + " " + TextFormatting.AQUA + TextFormatting.ITALIC + TranslateUtils.toLocal("tooltip.item.shift")); }
    }


}
