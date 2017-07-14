package com.stormy.lightningadditions.block;

import com.google.common.collect.Lists;
import net.minecraft.block.Block;
import net.minecraft.block.BlockDirt;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.IAttribute;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.event.ForgeEventFactory;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nonnull;
import javax.swing.*;
import java.util.*;

//TODO getItemFromBlock
public class BlockCursedEarth extends Block {

    public static final BlockCursedEarth earth = null;
    static final UUID uuid = UUID.fromString("E53E0344-EA5E-4F71-98F6-40791198D8FE");
    public static final int MAX_DECAY = 15;
    public static final PropertyInteger DECAY = PropertyInteger.create("decay", 0, 15);

    public static int powered = 0;

    public BlockCursedEarth() {
        super(Material.GRASS);
        this.setTickRandomly(true);
        this.setHardness(0.5F);
        this.blockResistance = 200.0F;
    }

    @SideOnly(Side.CLIENT)
    public BlockRenderLayer getBlockLayer() {return BlockRenderLayer.CUTOUT_MIPPED;}

    public boolean canSilkHarvest(World world, EntityPlayer player, int x, int y, int z, int metadata) {
        return true;
    }

    private static void applyAttribute(EntityLivingBase mob, IAttribute attackDamage, AttributeModifier modifier) {
        IAttributeInstance instance = mob.getEntityAttribute(attackDamage);
        if (instance != null) {
            instance.applyModifier(modifier);
        }
    }

    public static void startFastSpread(World worldIn, BlockPos pos) {

        IBlockState cursedEarthState = earth.getDefaultState();
        worldIn.setBlockState(pos, cursedEarthState);
        worldIn.scheduleUpdate(pos.toImmutable(), earth, 1);
        worldIn.playEvent(1027, pos, 0);
    }

    private static void trySpawnMob(WorldServer world, BlockPos pos, EntityLiving mob) {
        if (mob != null) {
            boolean shouldCenter = world.rand.nextBoolean();
            float x = (float) pos.getX() + (shouldCenter ? 0.5F : world.rand.nextFloat());
            float y = (float) (pos.getY() + 1);
            float z = (float) pos.getZ() + (shouldCenter ? 0.5F : world.rand.nextFloat());
            mob.setLocationAndAngles((double) x, (double) y, (double) z, world.rand.nextFloat() * 360.0F, 0.0F);
            if (!ForgeEventFactory.doSpecialSpawn(mob, world, x, y, z)) {
                mob.onInitialSpawn(world.getDifficultyForLocation(new BlockPos(mob)), (IEntityLivingData) null);
            }

            if (mob.isNotColliding() && mob.getCanSpawnHere()) {
                if (spawnMobAsCursed(mob)) {
                    mob.playLivingSound();
                }

            } else {
                mob.setDead();
            }
        }
    }

    public static boolean spawnMobAsCursed(Entity mob) {
        mob.forceSpawn = true;
        if (mob instanceof EntityLivingBase) {
            mob.getEntityData().setInteger("CursedEarth", 60);
            EntityLivingBase living = (EntityLivingBase) mob;
            applyAttribute(living, SharedMonsterAttributes.ATTACK_DAMAGE, new AttributeModifier(uuid, "CursedEarth", 1.5D, 1));
            applyAttribute(living, SharedMonsterAttributes.MOVEMENT_SPEED, new AttributeModifier(uuid, "CursedEarth", 1.2D, 1));
            if (living instanceof EntityZombie) {
                IAttributeInstance attributeInstanceByName = living.getAttributeMap().getAttributeInstanceByName("zombie.spawnReinforcements");
                if (attributeInstanceByName != null) {
                    attributeInstanceByName.setBaseValue(0.0D);
                }
            }
        }

        if (!mob.world.spawnEntity(mob)) {
            return false;
        } else {
            if (mob.isBeingRidden()) {
                mob.getPassengers().forEach(BlockCursedEarth::spawnMobAsCursed);
            }

            return true;
        }
    }

    public Item getItemDropped(IBlockState state, Random rand, int fortune) {
        return Item.getItemFromBlock(Blocks.DIRT);
    }

    public int damageDropped(IBlockState state) {
        return 0;
    }

    public boolean canSilkHarvest(World world, BlockPos pos, @Nonnull IBlockState state, EntityPlayer player) {
        return ((Integer) state.getValue(DECAY)).intValue() == 0;
    }

    public boolean isFireSource(@Nonnull World world, BlockPos pos, EnumFacing side) {
        return side == EnumFacing.UP;
    }

    public void randomDisplayTick(IBlockState stateIn, World worldIn, BlockPos pos, Random rand) {
        int numParticles = 15 - ((Integer) stateIn.getValue(DECAY)).intValue();
        for (int i = 0; i < numParticles; ++i) {
            worldIn.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, (double) pos.getX() + rand.nextDouble(), (double) pos.getY() + 1.01D, (double) pos.getZ() + rand.nextDouble(), 0.0D, 0.0D, 0.0D, new int[0]);
        }
    }

    public void randomTick(@Nonnull World worldIn, @Nonnull BlockPos pos, @Nonnull IBlockState state, @Nonnull Random random) {
        if(!worldIn.isRemote) {
            this.performTick(worldIn, pos, random, false);
        }
    }

    public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand) {
        if(worldIn != null && !worldIn.isRemote) {
            this.performTick(worldIn, pos, rand, true);
        }
    }


    protected void performTick(World worldIn, BlockPos pos, Random rand, boolean fastSpreading) {
        WorldServer world = (WorldServer) worldIn;
        int light = world.getLightFromNeighbors(pos.up());
        int numCreaturesNearby;
        BlockPos add;
        if (light >= 9) {
            IBlockState blockState = world.getBlockState(pos.up());
            boolean nearbyFire = blockState.getMaterial() == Material.FIRE;
            if (nearbyFire && rand.nextInt(5) == 0) {
                world.setBlockState(pos, Blocks.DIRT.getDefaultState());
            }

            if (!nearbyFire) {
                for (numCreaturesNearby = 0; numCreaturesNearby < 10; ++numCreaturesNearby) {
                    add = pos.add(rand.nextInt(9) - 4, rand.nextInt(5) - 3, rand.nextInt(9) - 4);
                    if ((add.getY() < 0 || add.getY() >= 256 || worldIn.isBlockLoaded(add)) && world.getBlockState(add).getMaterial() == Material.FIRE) {
                        nearbyFire = true;
                        break;
                    }
                }
            }

            if (nearbyFire) {
                for (numCreaturesNearby = 0; numCreaturesNearby < 40; ++numCreaturesNearby) {
                    add = pos.add(rand.nextInt(9) - 4, rand.nextInt(5) - 3, rand.nextInt(9) - 4);
                    if ((add.getY() < 0 || add.getY() >= 256 || worldIn.isBlockLoaded(add)) && world.getBlockState(add).getBlock() == this) {
                        IBlockState s = world.getBlockState(add.up());
                        if (s.getBlock().isReplaceable(worldIn, add.up())) {
                            world.setBlockState(add.up(), Blocks.FIRE.getDefaultState());
                        } else {
                            world.setBlockState(add, Blocks.DIRT.getDefaultState());
                        }
                    }
                }

                return;
            }
        }

        boolean spread = false;
        if (fastSpreading) {
            ArrayList<BlockPos> list = Lists.newArrayList(BlockPos.getAllInBox(pos.add(-1, -2, -1), pos.add(1, 2, 1)));
            Collections.shuffle(list);
            Iterator var17 = list.iterator();

            while (var17.hasNext()) {
                add = (BlockPos) var17.next();
                this.trySpread(worldIn, pos, rand, true, world, false, add);
            }
        } else {
            int i = 0;

            while (true) {
                if (i >= 4) {
                    if (light >= 9 || spread && rand.nextInt(8) != 0) {
                        return;
                    }

                    AxisAlignedBB bb = (new AxisAlignedBB(pos)).expand(-7.0D, 4.0D, 7.0D);
                    numCreaturesNearby = world.getEntitiesWithinAABB(EntityLiving.class, bb, (input) -> {
                        return input != null && input.isCreatureType(EnumCreatureType.MONSTER, false);
                    }).size();
                    if (numCreaturesNearby < 8) {
                        this.trySpawnMob(world, pos);
                    }
                    break;
                }

                BlockPos blockpos = pos.add(rand.nextInt(3) - 1, rand.nextInt(5) - 3, rand.nextInt(3) - 1);
                spread = this.trySpread(worldIn, pos, rand, false, world, spread, blockpos);
                ++i;
            }
        }

    }

    private boolean trySpread(World worldIn, BlockPos pos, Random rand, boolean fastSpreading, WorldServer world, boolean spread, BlockPos blockpos) {
        if (blockpos.getY() >= 0 && blockpos.getY() < 256 && !worldIn.isBlockLoaded(blockpos)) {
            return spread;
        } else {
            IBlockState otherBlock = worldIn.getBlockState(blockpos);
            if (otherBlock.getBlock() != Blocks.GRASS && (otherBlock.getBlock() != Blocks.DIRT || otherBlock.getValue(BlockDirt.VARIANT) != BlockDirt.DirtType.DIRT)) {
                return spread;
            } else {
                IBlockState aboveState = worldIn.getBlockState(blockpos.up());
                if (aboveState.getLightOpacity(worldIn, pos.up()) > 2) {
                    return spread;
                } else {
                    int decay = 16;
                    Iterator var11 = BlockPos.getAllInBoxMutable(blockpos.add(-1, -1, -1), blockpos.add(1, 1, 1)).iterator();

                    while (var11.hasNext()) {
                        BlockPos.MutableBlockPos p = (BlockPos.MutableBlockPos) var11.next();
                        IBlockState blockState = world.getBlockState(p);
                        if (blockState.getBlock() == this) {
                            decay = Math.min(decay, ((Integer) blockState.getValue(DECAY)).intValue() + 1);
                        }
                    }

                    if (rand.nextBoolean()) {
                        ++decay;
                    }

                    if (decay > 15) {
                        return spread;
                    } else {
                        worldIn.setBlockState(blockpos, this.getDefaultState().withProperty(DECAY, Integer.valueOf(decay)));
                        if (fastSpreading) {
                            world.playEvent(2001, blockpos, Block.getIdFromBlock(this));
                            world.scheduleUpdate(blockpos, this, 2 + rand.nextInt(8));
                        }

                        return true;
                    }
                }
            }
        }
    }

    protected void trySpawnMob(WorldServer world, BlockPos pos) {
        EnumCreatureType type = EnumCreatureType.MONSTER;
        Biome.SpawnListEntry entry = world.getSpawnListEntryForTypeAt(type, pos);
        if (entry != null && world.canCreatureTypeSpawnHere(type, entry, pos)) {
            EntityLiving mob;
            try {
                mob = (EntityLiving) entry.entityClass.getConstructor(new Class[]{World.class}).newInstance(new Object[]{world});
            } catch (Exception var7) {
                var7.printStackTrace();
                return;
            }

            trySpawnMob(world, pos, mob);
        }
    }

    public static class AICursed extends EntityAIBase {
        final EntityLiving living;
        int cursedEarth;

        public AICursed(EntityLiving living, int cursedEarth) {
            this.living = living;
            this.cursedEarth = cursedEarth;
        }
        public boolean shouldExecute() {
            return true;
        }
        public boolean shouldContinueExecuting() {
            return true;
        }

        public void updateTask() {
            if (this.living.world.getTotalWorldTime() % 20L == 0L) {
                if (this.cursedEarth >= 0) {
                    if (this.cursedEarth == 0) {
                        for (int k = 0; k < 20; ++k) {
                            Random rand = this.living.world.rand;
                            double d2 = rand.nextGaussian() * 0.02D;
                            double d0 = rand.nextGaussian() * 0.02D;
                            double d1 = rand.nextGaussian() * 0.02D;
                            this.living.world.spawnParticle(EnumParticleTypes.EXPLOSION_NORMAL, this.living.posX + (double) (rand.nextFloat() * this.living.width * 2.0F) - (double) this.living.width, this.living.posY + (double) (rand.nextFloat() * this.living.height), this.living.posZ + (double) (rand.nextFloat() * this.living.width * 2.0F) - (double) this.living.width, d2, d0, d1, new int[0]);
                        }

                        this.living.setDead();
                    } else {
                        --this.cursedEarth;
                        this.living.getEntityData().setInteger("CursedEarth", this.cursedEarth);
                    }

                }
            }
        }
    }

    @SubscribeEvent
    public void spawnInWorld(EntityJoinWorldEvent event) {
        Entity entity = event.getEntity();
        if(entity instanceof EntityLiving) {
            NBTTagCompound nbt = entity.getEntityData();
            if(nbt.hasKey("CursedEarth", 3)) {
                int cursedEarth = nbt.getInteger("CursedEarth");
                if(cursedEarth <= 0) {
                    entity.setDead();
                    event.setCanceled(true);
                } else {
                    EntityLiving living = (EntityLiving)entity;
                    living.tasks.addTask(0, new BlockCursedEarth.AICursed(living, cursedEarth));
                }
            }
        }

    }

}
