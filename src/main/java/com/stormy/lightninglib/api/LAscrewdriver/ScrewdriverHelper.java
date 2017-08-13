/*
 * ********************************************************************************
 * Copyright (c) 2017 StormyMode, MiningMark48. All Rights Reserved!
 * This file is part of Lightning Additions (MC-Mod).
 *
 * This project cannot be copied and/or distributed without the express
 * permission of StormyMode, MiningMark48 (Developers)!
 * ********************************************************************************
 */

package com.stormy.lightninglib.api.LAscrewdriver;

import cofh.item.IToolHammer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;

/**
 * SonicScrewdriver API
 * Default implementation that only applies to targets.
 * Used in classes to extend wrench abilities!
 */

public final class ScrewdriverHelper
{
    private ScrewdriverHelper() {}

    public static boolean isHoldingUsableScrewdriver(EntityPlayer player, RayTraceResult traceResult)
    {

        EnumHand hand = EnumHand.MAIN_HAND;
        ItemStack stack = player.getHeldItem(hand);
        if (stack.isEmpty()) {
            hand = EnumHand.OFF_HAND;
            stack = player.getHeldItem(hand);
        }
        if (stack.isEmpty()) {
            return false;
        }
        if (stack.getItem() instanceof IToolHammer) {
            BlockPos pos = traceResult.getBlockPos();
            return ((IToolHammer) stack.getItem()).isUsable(stack, player, pos);
        }
        return false; }

    public static void usedScrewdriver(EntityPlayer player, RayTraceResult traceResult)
    {

        EnumHand hand = EnumHand.MAIN_HAND;
        ItemStack stack = player.getHeldItem(hand);
        if (stack.isEmpty()) {
            hand = EnumHand.OFF_HAND;
            stack = player.getHeldItem(hand);
        }
        if (stack.isEmpty()) {
            return;
        }
        if (stack.getItem() instanceof IToolHammer)
        {BlockPos pos = traceResult.getBlockPos();
            ((IToolHammer) stack.getItem()).toolUsed(stack, player, pos); }
    }


    public static boolean isHoldingUsableWrench(EntityPlayer player, RayTraceResult traceResult) {

        EnumHand hand = EnumHand.MAIN_HAND;
        ItemStack stack = player.getHeldItem(hand);
        if (stack.isEmpty()) {
            hand = EnumHand.OFF_HAND;
            stack = player.getHeldItem(hand);
        }
        if (stack.isEmpty()) {
            return false;
        }
        if (stack.getItem() instanceof IToolHammer) {
            BlockPos pos = traceResult.getBlockPos();
            return ((IToolHammer) stack.getItem()).isUsable(stack, player, pos);
        } else if (bcWrenchExists) {
            //return canHandleBCWrench(player, hand, stack, traceResult);
        }
        return false;
    }

    public static void usedWrench(EntityPlayer player, RayTraceResult traceResult) {

        EnumHand hand = EnumHand.MAIN_HAND;
        ItemStack stack = player.getHeldItem(hand);
        if (stack.isEmpty()) {
            hand = EnumHand.OFF_HAND;
            stack = player.getHeldItem(hand);
        }
        if (stack.isEmpty()) {
            return;
        }
        if (stack.getItem() instanceof IToolHammer) {
            BlockPos pos = traceResult.getBlockPos();
            ((IToolHammer) stack.getItem()).toolUsed(stack, player, pos);
        } else if (bcWrenchExists) {
            //bcWrenchUsed(player, hand, stack, traceResult);
        }
    }

    /* HELPERS */
    private static boolean bcWrenchExists = false;

    static {
        try {
            Class.forName("buildcraft.api.tools.IToolWrench");
            bcWrenchExists = true;
        } catch (Throwable t) {
            // pokemon!
        }
    }

}
