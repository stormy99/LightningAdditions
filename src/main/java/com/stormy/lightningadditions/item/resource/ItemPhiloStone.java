package com.stormy.lightningadditions.item.resource;

import com.stormy.lightningadditions.reference.KeyChecker;
import com.stormy.lightningadditions.reference.Translate;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

import java.util.List;

public class ItemPhiloStone extends Item
{
    public ItemPhiloStone() {
        this.setMaxDamage(20000);
        this.setNoRepair();
        this.setMaxStackSize(1);
        this.setContainerItem(this);
    }

    //TODO Transmutation Sound Effect on Right-Click
    //TODO "Savitar Features" -- The Flash CW TV Show


    @Override
    public EnumActionResult onItemUse(EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {

        //Equal Block Transmutations
             if (worldIn.getBlockState(pos).getBlock() == Blocks.GRASS) { worldIn.setBlockState(pos, Blocks.DIRT.getDefaultState()); }
        else if (worldIn.getBlockState(pos).getBlock() == Blocks.DIRT) { worldIn.setBlockState(pos, Blocks.GRAVEL.getDefaultState()); }
        else if (worldIn.getBlockState(pos).getBlock() == Blocks.GRAVEL) { worldIn.setBlockState(pos, Blocks.SAND.getDefaultState()); }
        else if (worldIn.getBlockState(pos).getBlock() == Blocks.SAND) { worldIn.setBlockState(pos, Blocks.GRASS.getDefaultState()); }

        //Water Transmutations
        else if (worldIn.getBlockState(pos).getBlock() == Blocks.WATER) { worldIn.setBlockState(pos, Blocks.SNOW.getDefaultState()); }
        else if (worldIn.getBlockState(pos).getBlock() == Blocks.SNOW) { worldIn.setBlockState(pos, Blocks.ICE.getDefaultState()); }
        else if (worldIn.getBlockState(pos).getBlock() == Blocks.ICE) { worldIn.setBlockState(pos, Blocks.FROSTED_ICE.getDefaultState()); }
        else if (worldIn.getBlockState(pos).getBlock() == Blocks.FROSTED_ICE) { worldIn.setBlockState(pos, Blocks.PACKED_ICE.getDefaultState()); }
        else if (worldIn.getBlockState(pos).getBlock() == Blocks.PACKED_ICE) { worldIn.setBlockState(pos, Blocks.WATER.getDefaultState()); }

        //Lava Transmutations
             else if (worldIn.getBlockState(pos).getBlock() == Blocks.LAVA) { worldIn.setBlockState(pos, Blocks.NETHERRACK.getDefaultState()); }
             else if (worldIn.getBlockState(pos).getBlock() == Blocks.NETHERRACK) { worldIn.setBlockState(pos, Blocks.MAGMA.getDefaultState()); }
             else if (worldIn.getBlockState(pos).getBlock() == Blocks.MAGMA) { worldIn.setBlockState(pos, Blocks.LAVA.getDefaultState()); }
             //TODO More Transmutations Methods

        //Misc Transmutations
             else if (worldIn.getBlockState(pos).getBlock() == Blocks.END_STONE) { worldIn.setBlockState(pos, Blocks.OBSIDIAN.getDefaultState()); }
             else if (worldIn.getBlockState(pos).getBlock() == Blocks.OBSIDIAN) { worldIn.setBlockState(pos, Blocks.END_STONE.getDefaultState()); }

        return null;
    }

    @Override
    public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par3List, boolean par4) {
        if (KeyChecker.isHoldingShift())
        { par3List.add(TextFormatting.ITALIC + Translate.toLocal("tooltip.item.philosopher_stone.line1"));
        }
        else{ par3List.add(Translate.toLocal("tooltip.item.hold") + " " + TextFormatting.AQUA + TextFormatting.ITALIC + Translate.toLocal("tooltip.item.shift")); }
    }
}
