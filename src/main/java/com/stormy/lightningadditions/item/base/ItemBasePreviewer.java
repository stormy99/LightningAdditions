package com.stormy.lightningadditions.item.base;

import com.stormy.lightningadditions.block.resource.BlockCompressedBase;
import com.stormy.lightninglib.lib.item.ItemBase;
import com.stormy.lightninglib.lib.utils.TranslateUtils;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;

public class ItemBasePreviewer extends ItemBase {

    public ItemBasePreviewer() {
        setMaxStackSize(1);
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer player, EnumHand hand) {
        ItemStack item = player.getHeldItem(hand);

        if (!item.hasTagCompound()) {
            item.setTagCompound(new NBTTagCompound());
        }

        NBTTagCompound tag = item.getTagCompound();
        assert tag != null;
        if (!player.isSneaking()) {
            tag.setInteger("pos_x", 0);
            tag.setInteger("pos_y", 0);
            tag.setInteger("pos_z", 0);
            tag.setBoolean("is_bound", false);
            player.sendStatusMessage(new TextComponentString(TextFormatting.GREEN + TranslateUtils.toLocal("chat.actionbar.item.basePreviewer.cleared")), true);
            player.playSound(SoundEvents.ENTITY_EXPERIENCE_ORB_PICKUP, 1f, 0.5f);
        } else {
            player.sendStatusMessage(new TextComponentString(TextFormatting.YELLOW + TranslateUtils.toLocal("chat.actionbar.item.basePreviewer.howBind")), true);
        }

        return super.onItemRightClick(worldIn, player, hand);
    }

    @Override
    public EnumActionResult onItemUse(EntityPlayer player, World world, BlockPos pos, EnumHand hand, EnumFacing side, float hitX, float hitY, float hitZ) {
        ItemStack item = player.getHeldItem(hand);

        if (!item.hasTagCompound()) {
            item.setTagCompound(new NBTTagCompound());
        }

        NBTTagCompound tag = item.getTagCompound();
        assert tag != null;

        if (player.isSneaking()) {
            tag.setInteger("pos_x", pos.getX());
            tag.setInteger("pos_z", pos.getZ());

            if (world.getBlockState(pos).getBlock() instanceof BlockCompressedBase) {
                tag.setInteger("pos_y", pos.getY());
            } else {
                tag.setInteger("pos_y", pos.up().getY());
            }

            tag.setBoolean("is_bound", true);

            player.sendStatusMessage(new TextComponentString(TextFormatting.DARK_GREEN + TranslateUtils.toLocal("chat.actionbar.item.basePreviewer.bound") + TextFormatting.AQUA + tag.getInteger("pos_x") + ", " + tag.getInteger("pos_y") + ", " + tag.getInteger("pos_z")), true);;
            player.playSound(SoundEvents.ENTITY_EXPERIENCE_ORB_PICKUP, 1f, 1f);
        } else {
            tag.setInteger("pos_x", 0);
            tag.setInteger("pos_y", 0);
            tag.setInteger("pos_z", 0);
            tag.setBoolean("is_bound", false);
            player.sendStatusMessage(new TextComponentString(TextFormatting.GREEN + TranslateUtils.toLocal("chat.actionbar.item.basePreviewer.cleared")), true);
            player.playSound(SoundEvents.ENTITY_EXPERIENCE_ORB_PICKUP, 1f, 0.5f);
        }

        return EnumActionResult.SUCCESS;
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World playerIn, List<String> tooltip, ITooltipFlag advanced) {
        if (!stack.hasTagCompound()) {
            stack.setTagCompound(new NBTTagCompound());
            NBTTagCompound tag = stack.getTagCompound();
            assert tag != null;
            tag.setBoolean("is_bound", false);
        }

        NBTTagCompound tag = stack.getTagCompound();
        if (tag.getBoolean("is_bound")) {
            tooltip.add(TextFormatting.GREEN + TranslateUtils.toLocal("tooltip.item.basePreviewer.bound") + TextFormatting.AQUA + tag.getInteger("pos_x") + ", " + tag.getInteger("pos_y") + ", " + tag.getInteger("pos_z"));
            tooltip.add(TextFormatting.LIGHT_PURPLE + TranslateUtils.toLocal("tooltip.item.basePreviewer.howUnbind"));
        } else {
            tooltip.add(TextFormatting.RED + "Not Bound!");
            tooltip.add(TextFormatting.LIGHT_PURPLE + TranslateUtils.toLocal("tooltip.item.basePreviewer.howBind"));
        }
    }

    @Override
    public boolean hasEffect(ItemStack stack) {
        if (stack.hasTagCompound()){
            assert stack.getTagCompound() != null;
            return stack.getTagCompound().getBoolean("is_bound");
        } else {
            return false;
        }
    }
}
