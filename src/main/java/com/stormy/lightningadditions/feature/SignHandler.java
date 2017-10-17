package com.stormy.lightningadditions.feature;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntitySign;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber
public class SignHandler
{
    @SubscribeEvent
    public static void editSign(PlayerInteractEvent.RightClickBlock event)
    {
        BlockPos pos = new BlockPos(event.getHitVec());
        IBlockState state = event.getWorld().getBlockState(pos);
        if (state.getBlock() != Blocks.WALL_SIGN && state.getBlock() != Blocks.STANDING_SIGN) { return; }

        EntityPlayer player = event.getEntityPlayer();
        TileEntity tileentity = event.getWorld().getTileEntity(pos);
        if (tileentity instanceof TileEntitySign) { player.openEditSign((TileEntitySign) tileentity); }
    }
}
