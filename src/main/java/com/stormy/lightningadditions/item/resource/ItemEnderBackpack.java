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

import com.stormy.lightningadditions.init.ModItems;
import com.stormy.lightningadditions.item.base.ItemLA;
import com.stormy.lightningadditions.reference.Translate;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.GameRegistry;

import javax.annotation.Nonnull;
import java.util.List;

public class ItemEnderBackpack extends ItemLA {

    public ItemEnderBackpack(){
        setMaxStackSize(1);
    }

    @Nonnull
    @Override
    public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, @Nonnull EnumHand hand) {
        ItemStack stack = player.getHeldItem(hand);
                player.displayGUIChest(player.getInventoryEnderChest());
            player.playSound(SoundEvents.ENTITY_ENDERMEN_TELEPORT, 1F, 1F);
            return ActionResult.newResult(EnumActionResult.SUCCESS, stack);
        }

    @Override
    public boolean itemInteractionForEntity(ItemStack stack, EntityPlayer player, EntityLivingBase entity, EnumHand hand) {
                player.displayGUIChest(((EntityPlayer) entity).getInventoryEnderChest());
            player.playSound(SoundEvents.ENTITY_ENDERMEN_TELEPORT, 1F, 1F);
            return true;
        }

    @Override
    public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par3List, boolean par4) {
        if (par1ItemStack.getItem() == ModItems.ender_backpack) {
            par3List.add(Translate.toLocal("tooltip.item.ender_backpack.line1"));
        }
    }

    private void init()
    {
        GameRegistry.register(this);
    }
}
