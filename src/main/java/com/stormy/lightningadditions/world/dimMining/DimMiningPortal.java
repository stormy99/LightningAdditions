/*
 * ********************************************************************************
 * Copyright (c) 2017 StormyMode, MiningMark48. All Rights Reserved!
 * This file is part of Lightning Additions (MC-Mod).
 *
 * This project cannot be copied and/or distributed without the express
 * permission of StormyMode, MiningMark48 (Developers)!
 * ********************************************************************************
 */

package com.stormy.lightningadditions.world.dimMining;

import com.stormy.lightningadditions.init.ModBlocks;
import com.stormy.lightningadditions.init.ModSounds;
import com.stormy.lightningadditions.utility.UtilChat;
import com.stormy.lightningadditions.utility.logger.ConfigurationManagerLA;
import com.stormy.lightningadditions.world.dimvoid.VoidWorldTeleport;
import net.minecraft.block.Block;
import net.minecraft.block.BlockPortal;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.network.play.server.SPacketSetExperience;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.Random;

public class DimMiningPortal extends BlockPortal
{
    public DimMiningPortal()
    {
        this.setTickRandomly(false);
        this.setBlockUnbreakable(); }

    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing side, float hitX, float hitY, float hitZ)
    {

        if (!worldIn.isRemote && !playerIn.isSneaking()) {
            playerIn.playSound(ModSounds.void_block, 1.0f, 1.0f);
            if (worldIn.provider.getDimension() != ConfigurationManagerLA.dimMiningID)
            {
                FMLCommonHandler.instance().getMinecraftServerInstance().getPlayerList().transferPlayerToDimension((EntityPlayerMP) playerIn, ConfigurationManagerLA.dimMiningID, new MiningWorldTeleport(playerIn.getServer().worldServerForDimension(ConfigurationManagerLA.dimMiningID), pos));
            } else
            {
                FMLCommonHandler.instance().getMinecraftServerInstance().getPlayerList().transferPlayerToDimension((EntityPlayerMP) playerIn, 0, new MiningWorldTeleport(playerIn.getServer().worldServerForDimension(0), pos));
            }
            return true;
        }
        return super.onBlockActivated(worldIn, pos, state, playerIn, hand, side, hitX, hitY, hitZ);
    }

    public boolean trySpawnPortal(World worldIn, BlockPos pos)
    {
        ASize size = new ASize(worldIn, pos, EnumFacing.Axis.X);
        if ((size.isValid()) && (size.portalBlockCount == 0))
        {
            size.placePortalBlocks();
            return true;
        }
        ASize size1 = new ASize(worldIn, pos, EnumFacing.Axis.Z);
        if ((size1.isValid()) && (size1.portalBlockCount == 0))
        {
            size1.placePortalBlocks();
            return true;
        }
        return false;
    }

    @Override
    public void onEntityCollidedWithBlock(World world, BlockPos pos, IBlockState state, Entity entity)
    {
        if ((entity.getRidingEntity() == null) && (!entity.isBeingRidden())) {
            if ((entity instanceof EntityPlayerMP))
            {
                EntityPlayerMP thePlayer = (EntityPlayerMP)entity;
                if (entity.isSneaking()) {
                    if (entity.dimension != ConfigurationManagerLA.dimMiningID)
                    {
                        thePlayer.connection.sendPacket(new SPacketSetExperience(thePlayer.experience, thePlayer.experienceTotal, thePlayer.experienceLevel));
                        FMLCommonHandler.instance().getMinecraftServerInstance().getPlayerList().transferPlayerToDimension(thePlayer, ConfigurationManagerLA.dimMiningID, new MiningWorldTeleport(thePlayer.getServer().worldServerForDimension(ConfigurationManagerLA.dimMiningID), pos));
                        if ((!world.isRemote))
                        {
                            UtilChat.addChatMessage(thePlayer, TextFormatting.AQUA + "Welcome to the Mining Dimension!");
                        }
                    }
                    else
                    {
                        FMLCommonHandler.instance().getMinecraftServerInstance().getPlayerList().transferPlayerToDimension(thePlayer, 0, new MiningWorldTeleport(thePlayer.getServer().worldServerForDimension(0), pos));
                        thePlayer.connection.sendPacket(new SPacketSetExperience(thePlayer.experience, thePlayer.experienceTotal, thePlayer.experienceLevel));
                    }
                }
            }
        }
    }


    @Deprecated
    public void neighborChanged(IBlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos p_189540_5_)
    {
        EnumFacing.Axis axis = (EnumFacing.Axis)state.getValue(AXIS);
        if (axis == EnumFacing.Axis.X)
        {
            ASize size = new ASize(worldIn, pos, EnumFacing.Axis.X);
            if ((!size.isValid()) || (size.portalBlockCount < size.width * size.height))
            {worldIn.setBlockState(pos, Blocks.AIR.getDefaultState());}
        }
        else if (axis == EnumFacing.Axis.Z)
        {
            ASize size = new ASize(worldIn, pos, EnumFacing.Axis.Z);
            if ((!size.isValid()) || (size.portalBlockCount < size.width * size.height))
            {worldIn.setBlockState(pos, Blocks.AIR.getDefaultState());}
        }
    }

    protected BlockStateContainer createBlockState() {return new BlockStateContainer(this, new IProperty[] { AXIS });}

    public static class ASize
    {
        private final World world;
        private final EnumFacing.Axis axis;
        private final EnumFacing rightDir;
        private final EnumFacing leftDir;
        private int portalBlockCount = 0;
        private BlockPos bottomLeft;
        private int height;
        private int width;

        public ASize(World worldIn, BlockPos p_i45694_2_, EnumFacing.Axis p_i45694_3_)
        {
            this.world = worldIn;
            this.axis = p_i45694_3_;
            if (p_i45694_3_ == EnumFacing.Axis.X)
            {
                this.leftDir = EnumFacing.EAST;
                this.rightDir = EnumFacing.WEST;
            }
            else
            {
                this.leftDir = EnumFacing.NORTH;
                this.rightDir = EnumFacing.SOUTH;
            }
            for (BlockPos blockpos = p_i45694_2_; (p_i45694_2_.getY() > blockpos.getY() - 21) && (p_i45694_2_.getY() > 0) && (isEmptyBlock(worldIn.getBlockState(p_i45694_2_.down()), p_i45694_2_.down())); p_i45694_2_ = p_i45694_2_.down()) {}
            int i = getDistanceUntilEdge(p_i45694_2_, this.leftDir) - 1;
            if (i >= 0)
            {
                this.bottomLeft = p_i45694_2_.offset(this.leftDir, i);
                this.width = getDistanceUntilEdge(this.bottomLeft, this.rightDir);
                if ((this.width < 1) || (this.width > 21))
                {
                    this.bottomLeft = null;
                    this.width = 0;
                }
            }
            if (this.bottomLeft != null) {
                this.height = calculatePortalHeight();
            }
        }


        protected int getDistanceUntilEdge(BlockPos p_180120_1_, EnumFacing p_180120_2_) {
            int i;
            for (i = 0; i < 22; i++) {
                BlockPos blockpos = p_180120_1_.offset(p_180120_2_, i);
                if ((!isEmptyBlock(this.world.getBlockState(blockpos), blockpos)) || (this.world.getBlockState(blockpos.down()).getBlock() != ModBlocks.reinforced_obsidian)) {
                    break;
                }
            }
            Block block = this.world.getBlockState(p_180120_1_.offset(p_180120_2_, i)).getBlock();
            return block == ModBlocks.reinforced_obsidian ? 22 : 0;
        }

        public int getHeight()
        {return this.height;}

        public int getWidth()
        {return this.width;}

        protected int calculatePortalHeight()
        {
            for (this.height = 0; this.height < 21; this.height += 1) {
                for (int i = 0; i < this.width; i++)
                {
                    BlockPos blockpos = this.bottomLeft.offset(this.rightDir, i).up(this.height);
                    IBlockState state = this.world.getBlockState(blockpos);
                    Block block = state.getBlock();
                    if (!isEmptyBlock(state, blockpos)) {break;}
                    if (block == ModBlocks.reinforced_obsidian) {
                        this.portalBlockCount += 1;
                    }
                    if (i == 0)
                    {
                        block = this.world.getBlockState(blockpos.offset(this.leftDir)).getBlock();
                        if (block != ModBlocks.reinforced_obsidian) {break;}
                    }
                    else if (i == this.width - 1)
                    {
                        block = this.world.getBlockState(blockpos.offset(this.rightDir)).getBlock();
                        if (block != ModBlocks.reinforced_obsidian) {break;}
                    }
                }
            }
            label190:
            for (int j = 0; j < this.width; j++) {
                if (this.world.getBlockState(this.bottomLeft.offset(this.rightDir, j).up(this.height)).getBlock() != ModBlocks.reinforced_obsidian)
                {
                    this.height = 0;
                    break;
                }
            }
            if ((this.height <= 21) && (this.height >= 2)) {
                return this.height;
            }
            this.bottomLeft = null;
            this.width = 0;
            this.height = 0;
            return 0;
        }

        protected boolean isEmptyBlock(IBlockState blockIn, BlockPos pos)
        {
            return (blockIn.getBlock().isAir(blockIn, this.world, pos)) || (blockIn.getBlock() == ModBlocks.mining_portal);
        }

        public boolean isValid()
        {
            return (this.bottomLeft != null) && (this.width >= 1) && (this.width <= 21) && (this.height >= 2) && (this.height <= 21);
        }

        public void placePortalBlocks()
        {
            for (int i = 0; i < this.width; i++)
            {
                BlockPos blockpos = this.bottomLeft.offset(this.rightDir, i);
                for (int j = 0; j < this.height; j++) {
                    this.world.setBlockState(blockpos.up(j), ModBlocks.mining_portal.getDefaultState().withProperty(BlockPortal.AXIS, this.axis), 2);
                }
            }
        }
    }

    public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand) {}

    @SideOnly(Side.CLIENT)
    public void randomDisplayTick(IBlockState worldIn, World pos, BlockPos state, Random rand) {}


}
