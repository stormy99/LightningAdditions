package com.stormy.lightningadditions.block;

import com.stormy.lightningadditions.LightningAdditions;
import com.stormy.lightningadditions.utility.tubing.EnumFlow;
import net.minecraft.block.Block;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;

import static com.stormy.lightningadditions.utility.tubing.EnumFlow.*;


public class BlockWTube extends BlockTube
{
    public static final PropertyEnum<EnumFlow> FLOW = PropertyEnum.create("flow", EnumFlow.class,
            DU, NS, WE, UD, SN, EW
    );

    public BlockWTube(String name)
    {
        super(name, FLOW);
        setDefaultState(blockState.getBaseState()
                .withProperty(flow, DU));
    }

    @Override
    protected IBlockState constructStateFromFlow(EnumFlow flow)
    {
        if (!FLOW.getAllowedValues()
                .contains(flow))
            flow = NS;
        return getDefaultState().withProperty(FLOW, flow);
    }

    @Override
    public boolean isAssociatedBlock(Block other)
    {
        return this == other;
    }

    @Override
    public void addCollisionBoxToList(IBlockState state, World worldIn, BlockPos pos, AxisAlignedBB entityBox,
                                      List<AxisAlignedBB> collidingBoxes, @Nullable Entity entityIn, boolean
                                              p_185477_7_)
    {
        addCollisionBoxToList(pos, entityBox, collidingBoxes, X_AABB);
        addCollisionBoxToList(pos, entityBox, collidingBoxes, Y_AABB);
        addCollisionBoxToList(pos, entityBox, collidingBoxes, Z_AABB);
    }

    @Override
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos)
    {
        switch (state.getValue(FLOW))
        {
            default:
            case DU:
            case UD:
                return Y_AABB;
            case WE:
            case EW:
                return Z_AABB;
            case NS:
            case SN:
                return X_AABB;
        }
    }

    @Override
    public IBlockState getStateFromMeta(int meta)
    {
        return constructStateFromFlow(EnumFlow.values()[meta * 5]);
    }

    @Override
    public int getMetaFromState(IBlockState state)
    {
        return state.getValue(FLOW)
                .ordinal() / 5;
    }

    @Override
    public IBlockState getStateForPlacement(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY,
                                            float hitZ, int meta, EntityLivingBase placer)
    {
        EnumFacing out = facing;
        EnumFacing in = facing.getOpposite();
        return constructStateFromFlow(EnumFlow.get(in, out));
    }

    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand
            hand, EnumFacing facing, float hitX, float hitY, float hitZ)
    {
        if (!worldIn.isRemote)
        {
            playerIn.openGui(LightningAdditions.INSTANCE, 0, worldIn, pos.getX(), pos.getY(), pos.getZ());
        }
        return true;
    }

    @Override
    protected BlockStateContainer createBlockState()
    {
        return new BlockStateContainer(this, FLOW);
    }
}
