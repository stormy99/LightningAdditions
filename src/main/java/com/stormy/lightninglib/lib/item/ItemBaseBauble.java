package com.stormy.lightninglib.lib.item;

import com.stormy.lightninglib.lib.utils.ItemNBT;
import com.stormy.lightninglib.lib.utils.ItemstackUtils;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.Optional;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@Optional.Interface(iface = "baubles.api.IBauble", modid = "baubles", striprefs = true)
public class ItemBaseBauble extends ItemBase implements baubles.api.IBauble, ItemBase.IHasClickToggle
{
    public ItemBaseBauble(int durability)
    {
        super();
        this.setMaxStackSize(1);
        this.setMaxDamage(durability); }

    public ItemBaseBauble(ItemArmor.ArmorMaterial material, EntityEquipmentSlot type) {
        super();
        this.setMaxStackSize(1); }

    public void toggle(EntityPlayer player, ItemStack held) {
        NBTTagCompound tags = ItemNBT.NBTUtil.getItemStackNBT(held);
        int vnew = isOn(held) ? 0 : 1;
        tags.setInteger(NBT_STATUS, vnew);
    }
    public boolean isOn(ItemStack held) {
        NBTTagCompound tags = ItemNBT.NBTUtil.getItemStackNBT(held);
        if (tags.hasKey(NBT_STATUS) == false) { return true;//default for newlycrafted//legacy items
        }
        return tags.getInteger(NBT_STATUS) == 1;
    }

    private final static String NBT_STATUS = "onoff";
    private ItemStack repairedBy = ItemStack.EMPTY;

    public boolean canTick(ItemStack stack)
    { return isOn(stack) && (stack.getItemDamage() < stack.getMaxDamage()); }

    @SideOnly(Side.CLIENT)
    public boolean hasEffect(ItemStack stack) { return canTick(stack); }

    public void damageCharm(EntityPlayer living, ItemStack stack)
    { ItemstackUtils.damageItem(living, stack); }

    @Override
    public boolean getIsRepairable(ItemStack toRepair, ItemStack repair)
    { return repair.isItemEqual(repair) && toRepair.getItemDamage() > 0; }


    /**
     * Called each tick as long the item is on a player inventory. Uses by maps to
     * check if is on a player hand and update it's contents.
     */
    public void onTick(ItemStack arg0, EntityPlayer arg1) {}
    @Optional.Method(modid = "baubles")
    public boolean canEquip(ItemStack arg0, EntityLivingBase arg1) { return true; }
    @Optional.Method(modid = "baubles")
    public boolean canUnequip(ItemStack arg0, EntityLivingBase arg1)
    { return true; }

    @Override
    public void onUpdate(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected) {
        if (entityIn instanceof EntityPlayer) {
            onTick(stack, (EntityPlayer) entityIn); }
    }

    @Optional.Method(modid = "baubles")
    public baubles.api.BaubleType getBaubleType(ItemStack arg0) {
        try {
            if (baubles.api.BaubleType.values().length >= 4)
            { return baubles.api.BaubleType.TRINKET; }
            else { return baubles.api.BaubleType.RING; } }
        catch (Exception e) { return baubles.api.BaubleType.RING; } }

    @Optional.Method(modid = "baubles")
    public void onEquipped(ItemStack arg0, EntityLivingBase arg1) {}
    @Optional.Method(modid = "baubles")
    public void onUnequipped(ItemStack arg0, EntityLivingBase arg1) {}
    @Optional.Method(modid = "baubles")
    public void onWornTick(ItemStack stack, EntityLivingBase arg1)
    { if (!this.canTick(stack)) { return; }
        if (arg1 instanceof EntityPlayer && stack != null && stack.getCount() > 0)
        { this.onTick(stack, (EntityPlayer) arg1); } }

}
