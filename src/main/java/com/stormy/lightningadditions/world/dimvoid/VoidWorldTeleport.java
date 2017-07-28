/*
 * ********************************************************************************
 * Copyright (c) 2017 StormyMode, MiningMark48. All Rights Reserved!
 * This file is part of Lightning Additions (MC-Mod).
 *
 * This project cannot be copied and/or distributed without the express
 * permission of StormyMode, MiningMark48 (Developers)!
 * ********************************************************************************
 */

package com.stormy.lightningadditions.world.dimvoid;

import com.stormy.lightningadditions.init.ModBlocks;
import com.stormy.lightningadditions.utility.logger.ConfigurationManagerLA;
import net.minecraft.block.BlockStone;
import net.minecraft.block.BlockStoneBrick;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.Teleporter;
import net.minecraft.world.WorldServer;

public class VoidWorldTeleport extends Teleporter
{
    BlockPos pos;
    WorldServer world;

    public VoidWorldTeleport(WorldServer worldIn, BlockPos pos)
    {
        super(worldIn);
        this.pos = pos;
        world = worldIn;
    }

    @Override
    public void placeInPortal(Entity entityIn, float rotationYaw) {
        if (world.provider.getDimension() != ConfigurationManagerLA.dimID && entityIn instanceof EntityPlayer) {
            boolean foundBlock = false;
            BlockPos.MutableBlockPos mutableBlockPos = new BlockPos.MutableBlockPos(pos.getX(), 0, pos.getZ());
            for (int y = 0; y < 256; y++) {
                mutableBlockPos.setY(y);
                if (world.getBlockState(mutableBlockPos).getBlock() == ModBlocks.void_block) {
                    pos = new BlockPos(pos.getX(), y + 1, pos.getZ());
                    foundBlock = true;
                    break;
                }
            }
            if(!foundBlock){
                pos = ((EntityPlayer) entityIn).getBedLocation(world.provider.getDimension());
                if(pos == null){
                    pos = world.provider.getRandomizedSpawnPoint();
                }
            }

        }
        if (world.provider.getDimension() == ConfigurationManagerLA.dimID)
        {
            pos = new BlockPos(pos.getX(), 64, pos.getZ());
            if (world.getBlockState(pos).getBlock() != ModBlocks.void_block)
            {
                IBlockState blockStateToUse;
                for (int x = -3; x < 4; x++) {
                    for (int z = -3; z < 4; z++) {

                        for (int y = 1; y <= 20; y++){
                            if (world.getTileEntity(pos.add(x, y, z)) == null) world.setBlockToAir(pos.add(x, y, z));
                        }

                        BlockPos pos2 = pos.add(x, 0, z);
                        if (world.isAirBlock(pos2) || world.getBlockState(pos2).getBlock().isReplaceable(world, pos2) || world.getBlockState(pos2).getBlock() == Blocks.RED_FLOWER || world.getBlockState(pos2).getBlock() == Blocks.YELLOW_FLOWER) {
                            if ((pos2.getX() % 2 == 0 && pos2.getZ() % 2 == 0) || (pos2.getX() % 2 == 1 && pos2.getZ() % 2 == 1 )) {
                                blockStateToUse = Blocks.STONEBRICK.getDefaultState();
                            }else{
                                blockStateToUse = Blocks.STONEBRICK.getStateFromMeta(BlockStoneBrick.CRACKED_META);
                            }

                            world.setBlockState(pos2, blockStateToUse);

                        }

                    }
                }
                world.setBlockState(pos, ModBlocks.void_block.getDefaultState());
                for(EnumFacing facing : EnumFacing.HORIZONTALS){
                    if (world.isAirBlock(pos.down(2).offset(facing)) || world.getBlockState(pos.up().offset(facing)).getBlock().isReplaceable(world, pos.up().offset(facing)) || world.getBlockState(pos.up().offset(facing)).getBlock() == Blocks.RED_FLOWER || world.getBlockState(pos.up().offset(facing)).getBlock() == Blocks.YELLOW_FLOWER) world.setBlockState(pos.up().offset(facing, 2), Blocks.TORCH.getDefaultState());
                }

            }
        }

        entityIn.setLocationAndAngles((double) pos.getX() + 0.5, (double) pos.getY() + 1, (double) pos.getZ() + 0.5, entityIn.rotationYaw, 0.0F);
        entityIn.motionX = 0.0D;
        entityIn.motionY = 0.0D;
        entityIn.motionZ = 0.0D;

    }


}
