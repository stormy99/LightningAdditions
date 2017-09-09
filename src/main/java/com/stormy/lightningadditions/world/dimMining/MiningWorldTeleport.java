package com.stormy.lightningadditions.world.dimMining;

import com.stormy.lightningadditions.init.ModBlocks;
import com.stormy.lightningadditions.config.ConfigurationManagerLA;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.Teleporter;
import net.minecraft.world.WorldServer;

public class MiningWorldTeleport extends Teleporter
{
    BlockPos pos;
    WorldServer world;

    public MiningWorldTeleport(WorldServer worldIn, BlockPos pos)
    {
        super(worldIn);
        this.pos = pos;
        world = worldIn;
    }

    @Override
    public void placeInPortal(Entity entityIn, float rotationYaw) {
        if (world.provider.getDimension() != ConfigurationManagerLA.dimMiningID && entityIn instanceof EntityPlayer) {
            boolean foundBlock = false;
            BlockPos.MutableBlockPos mutableBlockPos = new BlockPos.MutableBlockPos(pos.getX(), 0, pos.getZ());
            for (int y = 0; y < 256; y++) {
                mutableBlockPos.setY(y);
                if (world.getBlockState(mutableBlockPos).getBlock() == ModBlocks.mining_portal) {
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
        if (world.provider.getDimension() == ConfigurationManagerLA.dimMiningID)
        {
            pos = new BlockPos(pos.getX(), 64, pos.getZ());
            if (world.getBlockState(pos).getBlock() != ModBlocks.mining_portal)
            {

                int color = world.rand.nextInt(15);
                int color2 = world.rand.nextInt(15);
                int colorToUse;
                for (int x = -3; x < 4; x++) {
                    for (int z = -3; z < 4; z++) {

                        for (int y = 1; y <= 20; y++){
                            if (world.getTileEntity(pos.add(x, y, z)) == null) world.setBlockToAir(pos.add(x, y, z));
                        }

                        BlockPos pos2 = pos.add(x, 0, z);
                        if (world.isAirBlock(pos2) || world.getBlockState(pos2).getBlock().isReplaceable(world, pos2) || world.getBlockState(pos2).getBlock() == Blocks.RED_FLOWER || world.getBlockState(pos2).getBlock() == Blocks.YELLOW_FLOWER) {
                            if ((pos2.getX() % 2 == 0 && pos2.getZ() % 2 == 0) || (pos2.getX() % 2 == 1 && pos2.getZ() % 2 == 1 )) {
                                colorToUse = color;
                            }else{
                                colorToUse = color2;
                            }

                            world.setBlockState(pos2, Blocks.STAINED_HARDENED_CLAY.getStateFromMeta(colorToUse));

                        }

                    }
                }
                world.setBlockState(pos.up(), ModBlocks.mining_portal.getDefaultState());
                for(EnumFacing facing : EnumFacing.HORIZONTALS){
//                    if (world.isAirBlock(pos.down(2).offset(facing)) || world.getBlockState(pos.up().offset(facing)).getBlock().isReplaceable(world, pos.up().offset(facing)) || world.getBlockState(pos.up().offset(facing)).getBlock() == Blocks.RED_FLOWER || world.getBlockState(pos.up().offset(facing)).getBlock() == Blocks.YELLOW_FLOWER) world.setBlockState(pos.up().offset(facing), Blocks.REDSTONE_TORCH.getDefaultState());
                    world.setBlockState(pos.offset(facing, 2), Blocks.SEA_LANTERN.getDefaultState());
                }

            }

            if (entityIn instanceof EntityPlayer){
                EntityPlayer player = (EntityPlayer) entityIn;
                if (!world.isRemote) player.sendMessage(new TextComponentString(TextFormatting.GOLD + "Welcome to Miner's Paradise!"));
            }

        }

        entityIn.setLocationAndAngles((double) pos.getX() + 0.5, (double) pos.getY() + 1, (double) pos.getZ() + 0.5, entityIn.rotationYaw, 0.0F);
        entityIn.motionX = 0.0D;
        entityIn.motionY = 0.0D;
        entityIn.motionZ = 0.0D;

    }


}
