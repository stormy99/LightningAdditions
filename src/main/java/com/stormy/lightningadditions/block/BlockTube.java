package com.stormy.lightningadditions.block;

import com.stormy.lightningadditions.LightningAdditions;
import com.stormy.lightningadditions.creativetab.CreativeTabLA;
import com.stormy.lightningadditions.tile.TileEntityTube;
import com.stormy.lightningadditions.utility.tubing.EnumFlow;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;
import java.util.List;

public class BlockTube extends BlockContainer
{
    public static final PropertyEnum<EnumFlow> NORMAL = PropertyEnum.create("flow", EnumFlow.class, flow -> flow
            .ordinal() < 15);
    public static final PropertyEnum<EnumFlow> REVERSE = PropertyEnum.create("flow", EnumFlow.class, flow -> flow
            .ordinal() >= 15);
    protected static final AxisAlignedBB DN_AABB = new AxisAlignedBB(0.375D, 0.0D, 0.0D, 0.625D, 0.5D, 0.625D);
    protected static final AxisAlignedBB DS_AABB = new AxisAlignedBB(0.375D, 0.0D, 0.375D, 0.625D, 0.5D, 1.0D);
    protected static final AxisAlignedBB DW_AABB = new AxisAlignedBB(0.0D, 0.0D, 0.375D, 0.625D, 0.5D, 0.625D);
    protected static final AxisAlignedBB DE_AABB = new AxisAlignedBB(0.375D, 0.0D, 0.375D, 1.0D, 0.5D, 0.625D);
    protected static final AxisAlignedBB UN_AABB = new AxisAlignedBB(0.375D, 0.25D, 0.0D, 0.625D, 1.0D, 0.625D);
    protected static final AxisAlignedBB US_AABB = new AxisAlignedBB(0.375D, 0.25D, 0.375D, 0.625D, 1.0D, 1.0D);
    protected static final AxisAlignedBB UW_AABB = new AxisAlignedBB(0.0D, 0.25D, 0.375D, 0.625D, 1.0D, 0.625D);
    protected static final AxisAlignedBB UE_AABB = new AxisAlignedBB(0.375D, 0.25D, 0.375D, 1.0D, 1.0D, 0.625D);
    protected static final AxisAlignedBB SW_AABB = new AxisAlignedBB(0, 0.25D, 0.375D, 0.625D, 0.5D, 1.0D);
    protected static final AxisAlignedBB SE_AABB = new AxisAlignedBB(0.375D, 0.25D, 0.375D, 1, 0.5D, 1.0D);
    protected static final AxisAlignedBB NW_AABB = new AxisAlignedBB(0, 0.25D, 0.0D, 0.625D, 0.5D, 0.625D);
    protected static final AxisAlignedBB NE_AABB = new AxisAlignedBB(0.375D, 0.25D, 0.0D, 1, 0.5D, 0.625D);
    protected static final AxisAlignedBB X_AABB = new AxisAlignedBB(0.375D, 0.25D, 0.0D, 0.625D, 0.5D, 1.0D);
    protected static final AxisAlignedBB Y_AABB = new AxisAlignedBB(0.375D, 0.0D, 0.375D, 0.625D, 1.0D, 0.625D);
    protected static final AxisAlignedBB Z_AABB = new AxisAlignedBB(0.0D, 0.25D, 0.375D, 1.0D, 0.5D, 0.625D);

    public final PropertyEnum<EnumFlow> flow;

    public BlockTube(String name, PropertyEnum<EnumFlow> flow)
    {
        super(Material.IRON, MapColor.QUARTZ);
        this.flow = flow;
        setRegistryName(name);
        setUnlocalizedName("tube");
        setHardness(3);
        setResistance(8);
        setCreativeTab(CreativeTabLA.LA_TAB);
        setSoundType(SoundType.GLASS);
        setDefaultState(blockState.getBaseState()
                .withProperty(flow, (EnumFlow) flow.getAllowedValues()
                        .toArray()[0]));
    }



    @Override
    public boolean hasComparatorInputOverride(IBlockState state)
    {
        return true;
    }

    @Override
    public int getComparatorInputOverride(IBlockState blockState, World worldIn, BlockPos pos)
    {
        return Container.calcRedstone(worldIn.getTileEntity(pos));
    }

    @SideOnly(Side.CLIENT)
    @Override
    public BlockRenderLayer getBlockLayer()
    {
        return BlockRenderLayer.CUTOUT_MIPPED;
    }

    @Override
    public EnumBlockRenderType getRenderType(IBlockState state)
    {
        return EnumBlockRenderType.MODEL;
    }

    @Override
    public IBlockState getStateForPlacement(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY,
                                            float hitZ, int meta, EntityLivingBase placer)
    {
        EnumFacing out = EnumFacing.getDirectionFromEntityLiving(pos, placer);
        EnumFacing in = facing.getOpposite();
        return constructStateFromFlow(EnumFlow.get(in, out));
    }

    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand
            hand, EnumFacing facing, float hitX, float hitY, float hitZ)
    {
        if (playerIn.getHeldItem(EnumHand.MAIN_HAND) != ItemStack.EMPTY)
            return false;

        if (worldIn.isRemote)
            return true;

        EnumFlow flow = EnumFlow.get(getInput(state), facing);

        if (flow != null)
            worldIn.setBlockState(pos, constructStateFromFlow(flow));

        return true;
    }

    @Override
    public boolean isAssociatedBlock(Block other)
    {
        return other == LightningAdditions.TUBE || other == LightningAdditions.TUBE_REVERSE;
    }

    protected IBlockState constructStateFromFlow(EnumFlow flow)
    {
        if (flow == null)
            flow = EnumFlow.DE;
        BlockTube block = flow.ordinal() < 15 ? LightningAdditions.TUBE : LightningAdditions.TUBE_REVERSE;
        return block.getDefaultState()
                .withProperty(block.flow, flow);
    }

    @Override
    public AxisAlignedBB getCollisionBoundingBox(IBlockState blockState, IBlockAccess worldIn, BlockPos pos)
    {
        return getBoundingBox(blockState, worldIn, pos);
    }

    @Override
    public void addCollisionBoxToList(IBlockState state, World worldIn, BlockPos pos, AxisAlignedBB entityBox,
                                      List<AxisAlignedBB> collidingBoxes, @Nullable Entity entityIn, boolean
                                              p_185477_7_)
    {
        addCollisionBoxToList(pos, entityBox, collidingBoxes, DN_AABB);
        addCollisionBoxToList(pos, entityBox, collidingBoxes, DS_AABB);
        addCollisionBoxToList(pos, entityBox, collidingBoxes, DW_AABB);
        addCollisionBoxToList(pos, entityBox, collidingBoxes, DE_AABB);
        addCollisionBoxToList(pos, entityBox, collidingBoxes, UN_AABB);
        addCollisionBoxToList(pos, entityBox, collidingBoxes, US_AABB);
        addCollisionBoxToList(pos, entityBox, collidingBoxes, UW_AABB);
        addCollisionBoxToList(pos, entityBox, collidingBoxes, UE_AABB);
        addCollisionBoxToList(pos, entityBox, collidingBoxes, SW_AABB);
        addCollisionBoxToList(pos, entityBox, collidingBoxes, SE_AABB);
        addCollisionBoxToList(pos, entityBox, collidingBoxes, NW_AABB);
        addCollisionBoxToList(pos, entityBox, collidingBoxes, NE_AABB);
        addCollisionBoxToList(pos, entityBox, collidingBoxes, X_AABB);
        addCollisionBoxToList(pos, entityBox, collidingBoxes, Y_AABB);
        addCollisionBoxToList(pos, entityBox, collidingBoxes, Z_AABB);
    }

    @Override
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos)
    {
        switch (EnumFlow.values()[getMetaFromState(state)])
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
            case NW:
            case WN:
                return NW_AABB;
            case NE:
            case EN:
                return NE_AABB;
            case SW:
            case WS:
                return SW_AABB;
            case SE:
            case ES:
                return SE_AABB;
            case DN:
            case ND:
                return DN_AABB;
            case DS:
            case SD:
                return DS_AABB;
            case DW:
            case WD:
                return DW_AABB;
            case DE:
            case ED:
                return DE_AABB;
            case UN:
            case NU:
                return UN_AABB;
            case US:
            case SU:
                return US_AABB;
            case UW:
            case WU:
                return UW_AABB;
            case UE:
            case EU:
                return UE_AABB;
        }
    }

    @Override
    public boolean isFullCube(IBlockState state)
    {
        return false;
    }

    @Override
    public boolean isOpaqueCube(IBlockState state)
    {
        return false;
    }

    @Override
    public IBlockState getStateFromMeta(int meta)
    {
        if (flow == REVERSE)
            meta += 15;
        return constructStateFromFlow(EnumFlow.values()[meta]);
    }

    @Override
    public int getMetaFromState(IBlockState state)
    {
        EnumFlow flow = state.getValue(((BlockTube) state.getBlock()).flow);
        return flow.ordinal() % 15;
    }

    @Override
    protected BlockStateContainer createBlockState()
    {
        return new BlockStateContainer(this, LightningAdditions.TUBE == null ? NORMAL : REVERSE);
    }

    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta)
    {
        return new TileEntityTube();
    }

    @Override
    public void breakBlock(World worldIn, BlockPos pos, IBlockState state)
    {
        TileEntity tileentity = worldIn.getTileEntity(pos);

        if (tileentity instanceof TileEntityTube)
        {
            InventoryHelper.dropInventoryItems(worldIn, pos, (TileEntityTube) tileentity);
            worldIn.updateComparatorOutputLevel(pos, this);
        }

        super.breakBlock(worldIn, pos, state);
    }

    public static EnumFacing getInput(IBlockState state)
    {
        BlockTube block = (BlockTube) state.getBlock();
        return state.getValue(block.flow)
                .getInput();
    }

    public static EnumFacing getOutput(IBlockState state)
    {
        BlockTube block = (BlockTube) state.getBlock();
        return state.getValue(block.flow)
                .getOutput();
    }

    @Override
    public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack
            stack)
    {
        super.onBlockPlacedBy(worldIn, pos, state, placer, stack);

        if (stack.hasDisplayName())
        {
            TileEntity te = worldIn.getTileEntity(pos);

            if (te instanceof TileEntityTube)
            {
                ((TileEntityTube) te).setCustomName(stack.getDisplayName());
            }
        }
    }
}
