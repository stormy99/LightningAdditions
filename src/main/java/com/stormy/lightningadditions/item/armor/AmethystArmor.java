package com.stormy.lightningadditions.item.armor;

import com.stormy.lightningadditions.client.model.ModelAmethystArmor;
import com.stormy.lightninglib.lib.library.EnergyContainerWrapper;
import com.stormy.lightninglib.lib.utils.InfoHelper;
import com.stormy.lightninglib.lib.utils.ItemNBTHelper;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.NonNullList;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;
import java.util.List;

public class AmethystArmor extends ItemArmor implements ICustomArmor
{
    private static ArmorMaterial amethystMaterial = EnumHelper.addArmorMaterial("amethystArmor", "lightningadditions:amethyst_armor", -1, new int[]{3, 6, 8, 3}, 30, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 2.0F);

    protected float baseProtectionPoints;
    protected float baseRecovery;

    public AmethystArmor(int renderIndexIn, EntityEquipmentSlot equipmentSlotIn) {
        super(amethystMaterial, renderIndexIn, equipmentSlotIn);
        this.setMaxDamage(-1);
        this.baseProtectionPoints = 256F;
        this.baseRecovery = 2F; }


    public AmethystArmor(ArmorMaterial materialIn, int renderIndexIn, EntityEquipmentSlot equipmentSlotIn) {
        super(materialIn, renderIndexIn, equipmentSlotIn);
        this.setMaxDamage(-1);
        this.baseProtectionPoints = 256F;
        this.baseRecovery = 2F;
    }

    @Override
    public boolean isDamageable() { return false; }

    @Override
    public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> subItems) {
        if (isInCreativeTab(tab)) {
            subItems.add(new ItemStack(this));
            ItemStack stack = new ItemStack(this);
            modifyEnergy(stack, getEnergyStored(stack));
            subItems.add(stack);
        }
    }

    protected float getProtectionShare() {
        switch (armorType) {
            case HEAD:
                return 0.15F;
            case CHEST:
                return 0.40F;
            case LEGS:
                return 0.30F;
            case FEET:
                return 0.15F;
        }
        return 0;
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void addInformation(ItemStack stack, @Nullable World playerIn, List<String> tooltip, ITooltipFlag advanced) {
        InfoHelper.addEnergyInfo(stack, tooltip);
    }

    //endregion

    //region Render

    @Override
    public boolean hasEffect(ItemStack stack) {
        return false;
    }

    @SideOnly(Side.CLIENT)
    public ModelBiped model;

    @SideOnly(Side.CLIENT)
    @Override
    public ModelBiped getArmorModel(EntityLivingBase entityLiving, ItemStack itemStack, EntityEquipmentSlot armorSlot, ModelBiped _default) {

        if (model == null) {
            if (armorType == EntityEquipmentSlot.HEAD) model = new ModelAmethystArmor(0.5F, true, false, false, false);
            else if (armorType == EntityEquipmentSlot.CHEST) model = new ModelAmethystArmor(1.5F, false, true, false, false);
            else if (armorType == EntityEquipmentSlot.LEGS) model = new ModelAmethystArmor(1.5F, false, false, true, false);
            else if (armorType == EntityEquipmentSlot.FEET) model = new ModelAmethystArmor(1.5F, false, false, true, false);
            else model = new ModelAmethystArmor(1F, false, false, false, true);
            this.model.bipedHead.showModel = (armorType == EntityEquipmentSlot.HEAD);
            this.model.bipedHeadwear.showModel = (armorType == EntityEquipmentSlot.HEAD);
            this.model.bipedBody.showModel = ((armorType == EntityEquipmentSlot.CHEST) || (armorType == EntityEquipmentSlot.LEGS));
            this.model.bipedLeftArm.showModel = (armorType == EntityEquipmentSlot.CHEST);
            this.model.bipedRightArm.showModel = (armorType == EntityEquipmentSlot.CHEST);
            this.model.bipedLeftLeg.showModel = (armorType == EntityEquipmentSlot.LEGS || armorType == EntityEquipmentSlot.FEET);
            this.model.bipedRightLeg.showModel = (armorType == EntityEquipmentSlot.LEGS || armorType == EntityEquipmentSlot.FEET);
        }


        if (entityLiving == null) {
            return model;
        }

        this.model.isSneak = entityLiving.isSneaking();
        this.model.isRiding = entityLiving.isRiding();
        this.model.isChild = entityLiving.isChild();
        //     this.model.aimedBow = false;
        //     this.model.heldItemRight = (entityLiving.getHeldItem() != null ? 1 : 0);

//                 this.model.bipedHead.showModel = (armorType == EntityEquipmentSlot.HEAD);
        this.model.bipedHeadwear.showModel = (armorType == EntityEquipmentSlot.HEAD);
        this.model.bipedBody.showModel = ((armorType == EntityEquipmentSlot.CHEST) || (armorType == EntityEquipmentSlot.LEGS));
        this.model.bipedLeftArm.showModel = (armorType == EntityEquipmentSlot.CHEST);
        this.model.bipedRightArm.showModel = (armorType == EntityEquipmentSlot.CHEST);
        this.model.bipedLeftLeg.showModel = (armorType == EntityEquipmentSlot.LEGS || armorType == EntityEquipmentSlot.FEET);
        this.model.bipedRightLeg.showModel = (armorType == EntityEquipmentSlot.LEGS || armorType == EntityEquipmentSlot.FEET);


        return model;
    }

    //region ICustomArmor

    @Override
    public float getProtectionPoints(ItemStack stack) {
        return 0;
    }

    @Override
    public float getRecoveryRate(ItemStack stack) {
        return 0;
    }

    @Override
    public float getSpeedModifier(ItemStack stack, EntityPlayer player) {
        return 0;
    }

    @Override
    public float getJumpModifier(ItemStack stack, EntityPlayer player) {
        return 0;
    }

    @Override
    public boolean hasHillStep(ItemStack stack, EntityPlayer player) {
        return false;
    }

    @Override
    public float getFireResistance(ItemStack stack) {
        switch (armorType) {
            case HEAD:
                return 0.15F;
            case CHEST:
                return 0.40F;
            case LEGS:
                return 0.30F;
            case FEET:
                return 0.15F;
        }
        return 0;
    }

    @Override
    public boolean[] hasFlight(ItemStack stack) {
        return new boolean[]{false, false, false};
    }

    @Override
    public float getFlightSpeedModifier(ItemStack stack, EntityPlayer player) {
        return 0;
    }

    @Override
    public float getFlightVModifier(ItemStack stack, EntityPlayer player) {
        return 0;
    }

    @Override
    public int getEnergyPerProtectionPoint() {
        return 1000;
    }

    //region Energy

    protected int getMaxReceive(ItemStack stack) {
        return 512000;
    }

    @Override
    public int receiveEnergy(ItemStack container, int maxReceive, boolean simulate) {
        int energy = ItemNBTHelper.getInteger(container, "Energy", 0);
        int energyReceived = Math.min(getEnergyStored(container) - energy, Math.min(getMaxReceive(container), maxReceive));

        if (!simulate) {
            energy += energyReceived;
            ItemNBTHelper.setInteger(container, "Energy", energy);
        }

        return energyReceived;
    }

    @Override
    public int extractEnergy(ItemStack container, int maxExtract, boolean simulate) {
        return 0;
    }

    @Override
    public int getEnergyStored(ItemStack container) {
        return ItemNBTHelper.getInteger(container, "Energy", 0);
    }

    @Override
    public int getMaxEnergyStored(ItemStack container) {
        return getEnergyStored(container);
    }

    @Override
    public void modifyEnergy(ItemStack container, int modify) {
        int energy = ItemNBTHelper.getInteger(container, "Energy", 0);
        energy += modify;

        if (energy > getEnergyStored(container)) {
            energy = getEnergyStored(container);
        }
        else if (energy < 0) {
            energy = 0;
        }

        ItemNBTHelper.setInteger(container, "Energy", energy);
    }

    @Override
    public boolean showDurabilityBar(ItemStack stack) {
        return !(getEnergyStored(stack) == getMaxEnergyStored(stack));
    }

    @Override
    public double getDurabilityForDisplay(ItemStack stack) {
        return 1D - ((double) getEnergyStored(stack) / (double) getMaxEnergyStored(stack));
    }

    @Override
    public ICapabilityProvider initCapabilities(final ItemStack stack, NBTTagCompound nbt) {
        return new EnergyContainerWrapper(stack);
    }
    @Override
    public boolean hasCustomEntity(ItemStack stack) { return true; }

}
