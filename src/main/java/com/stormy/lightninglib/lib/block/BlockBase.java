package com.stormy.lightninglib.lib.block;

import com.stormy.lightninglib.lib.library.IActivatable;
import com.stormy.lightninglib.lib.library.IChange;
import com.stormy.lightninglib.lib.library.IDataRetainable;
import com.stormy.lightninglib.lib.library.IRedstone;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;

import java.util.HashMap;
import java.util.Map;

public class BlockBase extends Block
{
    protected boolean isFullCube = true;
    private boolean ifcSet = false;
    protected boolean canProvidePower = false;
    protected boolean hasSubItemTypes = false;
    public Map<Integer, String> nameOverrides = new HashMap<>();

    public BlockBase(Material material)
    {
        super(material);
        this.setHardness(5F);
        setSoundType(SoundType.STONE);
        this.setResistance(10F); }
    public BlockBase() { this(Material.ROCK, MapColor.GRAY); }



    public BlockBase(Material blockMaterial, MapColor blockMapColor, float blockHardness, float blockResistance) {
        super(blockMaterial, blockMapColor);
        setHardness(1.5f);
        setResistance(1.0f);
    }

    public BlockBase(Material blockMaterial, MapColor blockMapColor) {
        super(blockMaterial, blockMapColor);
        setHardness(1.5f);
        setResistance(1.0f);
    }

    @Override
    public boolean addLandingEffects(IBlockState state, WorldServer worldObj, BlockPos blockPosition, IBlockState iblockstate, EntityLivingBase entity, int numberOfParticles) { return true; }

    @Override
    public void getSubBlocks(CreativeTabs itemIn, NonNullList<ItemStack> tab) {
        super.getSubBlocks(itemIn, tab);
    }

    public BlockBase setHarvestTool(String toolClass, int level) { this.setHarvestLevel(toolClass, level);
        return this; }

    @Override
    public boolean canProvidePower(IBlockState state) {
        return canProvidePower;
    }

    @Override
    public boolean canConnectRedstone(IBlockState state, IBlockAccess world, BlockPos pos, EnumFacing side) {
        if (hasTileEntity(state)) {
            TileEntity tile = world.getTileEntity(pos);
            return tile instanceof IRedstone || canProvidePower;
        }

        return super.canConnectRedstone(state, world, pos, side);
    }


    @Override
    public int getStrongPower(IBlockState blockState, IBlockAccess blockAccess, BlockPos pos, EnumFacing side) {
        if (hasTileEntity(blockState)) {
            TileEntity tile = blockAccess.getTileEntity(pos);
            if (tile instanceof IRedstone) {
                return ((IRedstone) tile).getStrongPower(blockState, side);
            }
        }
        return super.getStrongPower(blockState, blockAccess, pos, side);
    }

    @Override
    public int getWeakPower(IBlockState blockState, IBlockAccess blockAccess, BlockPos pos, EnumFacing side) {
        if (hasTileEntity(blockState)) {
            TileEntity tile = blockAccess.getTileEntity(pos);
            if (tile instanceof IRedstone) {
                return ((IRedstone) tile).getWeakPower(blockState, side);
            }
        }
        return super.getWeakPower(blockState, blockAccess, pos, side);
    }

    @Override
    public boolean shouldCheckWeakPower(IBlockState state, IBlockAccess world, BlockPos pos, EnumFacing side) {
        if (hasTileEntity(state)) {
            TileEntity tile = world.getTileEntity(pos);
            return tile instanceof IChange;
        }

        return super.shouldCheckWeakPower(state, world, pos, side);
    }

    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        if (hasTileEntity(state)) {
            TileEntity tile = worldIn.getTileEntity(pos);
            if (tile instanceof IActivatable) {
                return ((IActivatable) tile).onBlockActivated(state, playerIn, hand, facing, hitX, hitY, hitZ);
            }
        }

        return super.onBlockActivated(worldIn, pos, state, playerIn, hand, facing, hitX, hitY, hitZ);
    }

    @Override
    public void neighborChanged(IBlockState state, World world, BlockPos pos, Block blockIn, BlockPos fromPos) {
        if (hasTileEntity(state)) {
            TileEntity tile = world.getTileEntity(pos);
            if (tile instanceof IChange) {
                ((IChange) tile).onNeighborChange(fromPos);
            }
        }
        super.neighborChanged(state, world, pos, blockIn, fromPos);
    }

    @Override
    public void harvestBlock(World world, EntityPlayer player, BlockPos pos, IBlockState state, TileEntity te, ItemStack heldStack) {
        if (te instanceof IDataRetainable && ((IDataRetainable) te).saveToItem()) {
            ItemStack stack = new ItemStack(this, 1, damageDropped(state));
            ((IDataRetainable) te).writeToItemStack(stack, true);
            spawnAsEntity(world, pos, stack);
            world.removeTileEntity(pos); }
        else { super.harvestBlock(world, player, pos, state, te, heldStack); }
    }

    @Override
    public void onBlockPlacedBy(World world, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack) {
        TileEntity tile = world.getTileEntity(pos);

        if (tile instanceof IDataRetainable) {
            ((IDataRetainable) tile).readFromItemStack(stack);
        }
    }

    @Deprecated
    public BlockBase setIsFullCube(boolean value) {
        ifcSet = true;
        isFullCube = value;
        return this;
    }

    public boolean uberIsBlockFullCube() {
        return !ifcSet || isFullCube;
    }

    @Override
    public boolean isFullCube(IBlockState state) {
        return uberIsBlockFullCube();
    }

    @Override
    public boolean isOpaqueCube(IBlockState state) {
        return uberIsBlockFullCube();
    }
}
