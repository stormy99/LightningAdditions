package com.stormy.lightningadditions.item.resource;

import com.stormy.lightningadditions.capabilities.DimensionalTranslocator.DimensionalTranslocatorProvider;
import com.stormy.lightninglib.lib.utils.KeyChecker;
import com.stormy.lightninglib.lib.utils.TranslateUtils;
import de.kitsunealex.frame.item.ItemBase;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.ICapabilityProvider;

import javax.annotation.Nullable;

import java.util.List;

import static com.stormy.lightningadditions.handler.LACapabilityHandler.CAPABILITY_DIMENSIONAL_TRANSLOCATOR;

public class ItemDimensionalTranslocator extends ItemBase
{
    public ItemDimensionalTranslocator()
    {
        super();
    }

    @Override
    public EnumActionResult onItemUseFirst(EntityPlayer player, World world, BlockPos pos, EnumFacing side, float hitX, float hitY, float hitZ, EnumHand hand) {
        if (player.getHeldItem(hand).getItem() instanceof ItemDimensionalTranslocator) {
            ItemStack heldWrench = player.getHeldItem(hand);

            if (world.getTileEntity(pos) instanceof TileEntity && !heldWrench.getCapability(CAPABILITY_DIMENSIONAL_TRANSLOCATOR, null).canStoreBlock()) {
                return serializeNBT(world, pos, player, hand);
            } else if(heldWrench.getCapability(CAPABILITY_DIMENSIONAL_TRANSLOCATOR, null).canStoreBlock())
                placeBlockFromWrench(world, pos, player, hand);
        }
        return super.onItemUseFirst(player, world, pos, side, hitX, hitY, hitZ, hand);
    }

    @Nullable
    @Override
    public ICapabilityProvider initCapabilities(ItemStack stack, @Nullable NBTTagCompound nbt) {
        if(!stack.hasCapability(CAPABILITY_DIMENSIONAL_TRANSLOCATOR, null))
            return new DimensionalTranslocatorProvider(CAPABILITY_DIMENSIONAL_TRANSLOCATOR, null);
        else
            return null;
    }

    private EnumActionResult serializeNBT(World world, BlockPos pos, EntityPlayer player, EnumHand hand){
        ItemStack heldWrench = player.getHeldItem(hand);
        IBlockState state = world.getBlockState(pos);

        if(heldWrench.hasCapability(CAPABILITY_DIMENSIONAL_TRANSLOCATOR, null)) {
            String blockName = state.getBlock().getLocalizedName();
            TileEntity tileEntity = world.getTileEntity(pos);
            NBTTagCompound nbt = new NBTTagCompound();

            if(tileEntity != null){
                tileEntity.writeToNBT(nbt);
                heldWrench.getCapability(CAPABILITY_DIMENSIONAL_TRANSLOCATOR, null).storeBlockNBT(nbt);
                heldWrench.getCapability(CAPABILITY_DIMENSIONAL_TRANSLOCATOR, null).storeBlockState(world.getBlockState(pos));
                heldWrench.getCapability(CAPABILITY_DIMENSIONAL_TRANSLOCATOR, null).setBlockName(blockName);
                world.removeTileEntity(pos);
                world.setBlockToAir(pos);
                world.markBlockRangeForRenderUpdate(pos, pos);
            }
            return EnumActionResult.SUCCESS;
        }
        return EnumActionResult.FAIL;
    }

    private EnumActionResult placeBlockFromWrench(World world, BlockPos pos, EntityPlayer player, EnumHand hand) {
        ItemStack heldWrench = player.getHeldItem(hand);
        if(heldWrench.getCapability(CAPABILITY_DIMENSIONAL_TRANSLOCATOR, null).getStoredBlockState() != null) {
            NBTTagCompound tileCmp = heldWrench.getCapability(CAPABILITY_DIMENSIONAL_TRANSLOCATOR, null).getStoredBlockNBT();
            IBlockState state = heldWrench.getCapability(CAPABILITY_DIMENSIONAL_TRANSLOCATOR, null).getStoredBlockState();
            TileEntity te = TileEntity.create(world, tileCmp);
            world.setBlockState(pos.offset(EnumFacing.UP), state);
            world.setTileEntity(pos.offset(EnumFacing.UP), te);
            heldWrench.getCapability(CAPABILITY_DIMENSIONAL_TRANSLOCATOR, null).clearBlockStorage();
        }
        return EnumActionResult.SUCCESS;
    }

    @Override
    public void addInformation(ItemStack par1ItemStack, @Nullable World world, List par3List, ITooltipFlag par4) {
        if (KeyChecker.isHoldingShift()) {
            par3List.add(TextFormatting.GOLD + TranslateUtils.toLocal("tooltip.item.dimensional_translocator.line1"));
        } else {
            par3List.add(TranslateUtils.toLocal("tooltip.item.hold") + " " + TextFormatting.AQUA + TextFormatting.ITALIC + TranslateUtils.toLocal("tooltip.item.shift"));
        }
    }
}
