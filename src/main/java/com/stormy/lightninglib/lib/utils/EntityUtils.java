package com.stormy.lightninglib.lib.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.item.EntityXPOrb;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;

public class EntityUtils {

    public static List<Entity> getItemExp(World world, AxisAlignedBB range)
    {
        List<Entity> all = new ArrayList<Entity>();
        all.addAll(world.getEntitiesWithinAABB(EntityItem.class, range));
        all.addAll(world.getEntitiesWithinAABB(EntityXPOrb.class, range));
        return all; }

    public static Entity getRenderViewEntity() {
        return Minecraft.getMinecraft().getRenderViewEntity();
    }

    public static List<Entity> getEntitiesInRange(Class<? extends Entity> entityType, World world, double x, double y, double z, double radius) {
        return world.getEntitiesWithinAABB(entityType, new AxisAlignedBB(x - radius, y - radius, z - radius, x + radius, y + radius, z + radius)); }

    public static AxisAlignedBB makeBoundingBox(BlockPos center, int hRadius, int vRadius) {
        //so if radius is 1, it goes 1 in each direction, and boom, 3x3 selected
        return new AxisAlignedBB(center).expand(hRadius, vRadius, hRadius);
    }
    public static AxisAlignedBB makeBoundingBox(double x, double y, double z, int hRadius, int vRadius) {
        return new AxisAlignedBB(
                x - hRadius, y - vRadius, z - hRadius,
                x + hRadius, y + vRadius, z + hRadius); }

    public static IBlockState getBlockStateBelowEntity(Entity entity, int depth) {
        int blockX = MathUtils.floor(entity.posX);
        int blockY = MathUtils.floor(entity.getEntityBoundingBox().minY - depth);
        int blockZ = MathUtils.floor(entity.posZ);
        return MappingUtils.world(entity).getBlockState(new BlockPos(blockX, blockY, blockZ)); }

    public static Block getBlockBelowEntity(Entity entity, int depth) {
        return getBlockStateBelowEntity(entity, depth).getBlock(); }

    public static IBlockState getBlockStateAboveEntity(Entity entity, int depth) {
        int blockX = MathUtils.floor(entity.posX);
        int blockY = MathUtils.floor(entity.getEntityBoundingBox().maxY + depth);
        int blockZ = MathUtils.floor(entity.posZ);
        return MappingUtils.world(entity).getBlockState(new BlockPos(blockX, blockY, blockZ));
    }

    public static List<EntityLivingBase> getLivingHostile(World world, AxisAlignedBB range)
    {
        List<EntityLivingBase> all = world.getEntitiesWithinAABB(EntityLivingBase.class, range);
        List<EntityLivingBase> nonPlayer = new ArrayList<EntityLivingBase>();
        for (EntityLivingBase ent : all)
        {
            if (ent instanceof EntityPlayer == false && ent.isCreatureType(EnumCreatureType.MONSTER, false)) {//players are not monsters so, redundant?
                nonPlayer.add(ent); } }
        return nonPlayer; }

    public static void speedupEntity(EntityLivingBase entity, float factor) {
        entity.motionX += net.minecraft.util.math.MathHelper.sin(-entity.rotationYaw * 0.017453292F) * factor;
        entity.motionZ += net.minecraft.util.math.MathHelper.cos(entity.rotationYaw * 0.017453292F) * factor; }

    public static void speedupEntityIfMoving(EntityLivingBase entity, float factor)
    { if (entity.moveForward > 0) {
        if (entity.getRidingEntity() != null && entity.getRidingEntity() instanceof EntityLivingBase) {
                speedupEntity((EntityLivingBase) entity.getRidingEntity(), factor); }
            else { speedupEntity(entity, factor); } }
    }

    public static EntityVillager getVillager(World world, int x, int y, int z) {
        List<EntityVillager> all = world.getEntitiesWithinAABB(EntityVillager.class, new AxisAlignedBB(new BlockPos(x, y, z)));
        if (all.size() == 0)
            return null;
        else
            return all.get(0);
    }
    public static int getVillagerCareer(EntityVillager merchant) {
        return ObfuscationReflectionHelper.getPrivateValue(EntityVillager.class, merchant, "careerId", "field_175563_bv"); }

    public static void setVillagerCareer(EntityVillager merchant, int c) {
        ObfuscationReflectionHelper.setPrivateValue(EntityVillager.class, merchant, c, "careerId", "field_175563_bv"); }

    public static String getCareerName(EntityVillager merchant) {
        return merchant.getDisplayName().getFormattedText();//getProfessionForge().getCareer(maybeC).getName();
    }

    public static List<EntityVillager> getVillagers(World world, BlockPos p, int r)
    { BlockPos start = p.add(-r, -r, -r);
        BlockPos end = p.add(r, r, r);
        return world.getEntitiesWithinAABB(EntityVillager.class, new AxisAlignedBB(start, end)); }

    public static Block getBlockAboveEntity(Entity entity, int depth) {
        return getBlockStateAboveEntity(entity, depth).getBlock(); }

    public static boolean isBlockAboveEntity(Entity entity, Block block, int depth) {
        World world = entity.getEntityWorld();
        int blockX = MathUtils.ceil(entity.posX - 1);
        int blockY = MathUtils.ceil(entity.posY + depth);
        int blockZ = MathUtils.ceil(entity.posZ - 1);
        for (int i = 0; i < 3; ++i) {
            for (int j = 0; j < 3; ++j) {
                if (world.getBlockState(new BlockPos(blockX + i + j, blockY, blockZ + i + j)).getBlock().equals(block)) {
                    return true;
                }
            }
        }
        return false;
    }

    public static void addOrMergePotionEffect(EntityLivingBase player, PotionEffect newPotion)
    { if (player.isPotionActive(newPotion.getPotion()))
    { PotionEffect p = player.getActivePotionEffect(newPotion.getPotion());
            int ampMax = Math.max(p.getAmplifier(), newPotion.getAmplifier());
            int dur = newPotion.getDuration() + p.getDuration();
            player.addPotionEffect(new PotionEffect(newPotion.getPotion(), dur, ampMax)); }
        else { player.addPotionEffect(newPotion); }
    }

    public static BlockPos getPosBelowEntity(Entity entity, int depth) {
        int blockX = MathUtils.floor(entity.posX);
        int blockY = MathUtils.floor(entity.getEntityBoundingBox().minY - depth);
        int blockZ = MathUtils.floor(entity.posZ);
        return new BlockPos(blockX, blockY, blockZ);
    }

    public static Entity cloneEntity(Entity sourceEntity) {
        Entity clonedEntity = null;
        NBTTagCompound entityNBT = sourceEntity.serializeNBT();
        if (entityNBT != null && !entityNBT.hasNoTags() && entityNBT.getSize() > 0) {
            clonedEntity = EntityList.createEntityFromNBT(entityNBT, MinecraftUtils.getWorld());
        }
        else {
            Class<? extends Entity> clazz = sourceEntity.getClass();
            try {
                clonedEntity = clazz.getConstructor(World.class).newInstance(MinecraftUtils.getWorld());
            }
            catch (Exception e) {
            }
        }
        return clonedEntity;
    }

    public static EntityLivingBase getClosestEntity(World world, EntityPlayer player, List<? extends EntityLivingBase> list) {
        EntityLivingBase closest = null;
        double minDist = 999999;
        double dist, xDistance, zDistance;
        for (EntityLivingBase ent : list)
        {
            xDistance = Math.abs(player.posX - ent.posX);
            zDistance = Math.abs(player.posZ - ent.posZ);
            dist = Math.sqrt(xDistance * xDistance + zDistance * zDistance);
            if (dist < minDist)
            { minDist = dist; closest = ent; } }
        return closest; }



    public static Entity getEntityByUUID(World world, UUID uuid) {
        for (Entity entity : world.getLoadedEntityList()) {
            if (entity.getUniqueID() == uuid) {
                return entity;
            }
        }
        return null;
    }

    /**
     * Force horizontal centering, so move from 2.9, 6.2 => 2.5,6.5
     * @param entity
     * @param pos
     */
    public static void centerEntityHoriz(Entity entity, BlockPos pos)
    {
        float fixedX = pos.getX() + 0.5F;//((float) (MathHelper.floor_double(entity.posX) + MathHelper.ceiling_double_int(entity.posX))  )/ 2;
        float fixedZ = pos.getZ() + 0.5F;//((float) (MathHelper.floor_double(entity.posX) + MathHelper.ceiling_double_int(entity.posX))  )/ 2;
        entity.setPosition(fixedX, entity.posY, fixedZ); }



    public static void copyDataFromOld(Entity oldEntity, Entity newEntity) {
        NBTTagCompound nbttagcompound = oldEntity.writeToNBT(new NBTTagCompound());
        nbttagcompound.removeTag("Dimension");
        newEntity.readFromNBT(nbttagcompound);
        newEntity.timeUntilPortal = oldEntity.timeUntilPortal;
        MCPUtils.setEntityLastPortalPos(newEntity, MCPUtils.getEntityLastPortalPos(oldEntity));
        MCPUtils.setEntityLastPortalVec(newEntity, MCPUtils.getEntityLastPortalVec(oldEntity));
        MCPUtils.setEntityTeleportDirection(newEntity, MCPUtils.getEntityTeleportDirection(oldEntity));
    }



}
