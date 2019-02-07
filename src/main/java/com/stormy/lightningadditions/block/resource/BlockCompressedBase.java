package com.stormy.lightningadditions.block.resource;

import com.stormy.lightningadditions.config.ConfigValues;
import com.stormy.lightningadditions.init.ModSounds;
import com.stormy.lightningadditions.tile.resource.TileEntityCompressedBase;
import com.stormy.lightningadditions.world.StructureBase;
import com.stormy.lightningadditions.world.StructureBase.Directions;
import com.stormy.lightninglib.lib.utils.KeyChecker;
import com.stormy.lightninglib.lib.utils.TranslateUtils;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.*;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import static com.stormy.lightninglib.lib.utils.UtilWorld.*;

public class BlockCompressedBase extends BlockContainer {

    private static AxisAlignedBB BOUNDING_BOX = new AxisAlignedBB(0.21875D, 0.0D, 0.21875D, 0.78125D, 0.3125D, 0.78125D);

    public static final PropertyDirection FACING = PropertyDirection.create("facing");

    public BlockCompressedBase() {
        super(Material.ROCK, MapColor.GRAY);
        this.setHardness(2.0f);
        this.setResistance(1.0f);
        this.setDefaultState(this.blockState.getBaseState().withProperty(FACING, EnumFacing.NORTH));
    }

    @Override
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos){
        return BOUNDING_BOX;
    }

    @Override
    public void addCollisionBoxToList(IBlockState state, World worldIn, BlockPos pos, AxisAlignedBB entityBox, List<AxisAlignedBB> collidingBoxes, @Nullable Entity entityIn, boolean p_185477_7_) {
        super.addCollisionBoxToList(pos, entityBox, collidingBoxes, BOUNDING_BOX);
    }

    @Override
    public void onBlockAdded(World worldIn, BlockPos pos, IBlockState state)
    {
        super.onBlockAdded(worldIn, pos, state);
        this.setDefaultFacing(worldIn, pos, state);
    }

    private void setDefaultFacing(World worldIn, BlockPos pos, IBlockState state)
    {
        if (!worldIn.isRemote)
        { IBlockState iblockstate = worldIn.getBlockState(pos.north()); IBlockState iblockstate1 = worldIn.getBlockState(pos.south());
            IBlockState iblockstate2 = worldIn.getBlockState(pos.west()); IBlockState iblockstate3 = worldIn.getBlockState(pos.east());
            EnumFacing enumfacing = state.getValue(FACING);
            if (enumfacing == EnumFacing.NORTH && iblockstate.isFullBlock() && !iblockstate1.isFullBlock())
            {
                enumfacing = EnumFacing.SOUTH;
            } else if (enumfacing == EnumFacing.SOUTH && iblockstate1.isFullBlock() && !iblockstate.isFullBlock())
            {
                enumfacing = EnumFacing.NORTH;
            } else if (enumfacing == EnumFacing.WEST && iblockstate2.isFullBlock() && !iblockstate3.isFullBlock())
            {
                enumfacing = EnumFacing.EAST;
            } else if (enumfacing == EnumFacing.EAST && iblockstate3.isFullBlock() && !iblockstate2.isFullBlock())
            {
                enumfacing = EnumFacing.WEST;
            }

            worldIn.setBlockState(pos, state.withProperty(FACING, enumfacing), 2);
        }
    }

    @Override
    @SuppressWarnings("deprecation")
    public IBlockState getStateForPlacement(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer)
    { return this.getDefaultState().withProperty(FACING, EnumFacing.getDirectionFromEntityLiving(pos, placer));
    }

    @Override
    public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack)
    {
        worldIn.setBlockState(pos, state.withProperty(FACING, EnumFacing.getDirectionFromEntityLiving(pos, placer)), 2);
    }

    @Override
    @SuppressWarnings("deprecation")
    public IBlockState getStateFromMeta(int meta)
    {
        return this.getDefaultState().withProperty(FACING, EnumFacing.values()[meta]);
    }

    @Override
    public int getMetaFromState(IBlockState state)
    {
        int i = state.getValue(FACING).getIndex();


        return i;
    }

    @Override
    protected BlockStateContainer createBlockState()
    {
        return new BlockStateContainer(this, FACING);
    }

    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        Directions directions = getDirections(worldIn, pos);

        boolean isValid = isValidArea(worldIn, pos, directions) || !ConfigValues.getDoBlockCheck();

        if (!isValid) {
            playerIn.sendStatusMessage(new TextComponentString(TextFormatting.DARK_RED + "" + TextFormatting.BOLD + TranslateUtils.toLocal("chat.actionbar.structure.invalid")), true);
        }

        if (!playerIn.isSneaking()) {

            if (!isValid) {
                return false;
            }

            // Yeah, I know. This logic is bad, but it works :D
            if (!worldIn.isRemote) {
                Rotation rotation = Rotation.NONE;
                EnumFacing chestOrientation = EnumFacing.NORTH;

                if (state.getValue(FACING) == EnumFacing.NORTH) {
                    rotation = Rotation.NONE;
                    chestOrientation = EnumFacing.NORTH;
                } else if (state.getValue(FACING) == EnumFacing.SOUTH) {
                    rotation = Rotation.CLOCKWISE_180;
                    chestOrientation = EnumFacing.SOUTH;
                } else if (state.getValue(FACING) == EnumFacing.EAST) {
                    rotation = Rotation.CLOCKWISE_90;
                    chestOrientation = EnumFacing.EAST;
                } else if (state.getValue(FACING) == EnumFacing.WEST) {
                    rotation = Rotation.COUNTERCLOCKWISE_90;
                    chestOrientation = EnumFacing.WEST;
                }

                StructureBase.generateStructure((WorldServer) worldIn, pos, new Random(), rotation, chestOrientation, directions);
                return true;
            }

            Random rand = new Random();
            if (rand.nextInt(2) == 0) {
                playerIn.playSound(ModSounds.BLOCK_COMPRESSEDBASE_DW20, 1.0f, 1.0f);
            } else {
                playerIn.playSound(SoundEvents.ENTITY_GENERIC_EXPLODE, 1.0f, 1.0f);
            }
            worldIn.spawnParticle(EnumParticleTypes.EXPLOSION_HUGE, pos.getX(), pos.getY() + 2.5, pos.getZ(), 0D, 0D, 0D);

            return true;
        } else {
            if (worldIn.getTileEntity(pos) instanceof TileEntityCompressedBase) {
                TileEntityCompressedBase te = (TileEntityCompressedBase) worldIn.getTileEntity(pos);
                assert te != null;
                if (worldIn.isRemote && ConfigValues.getShowPreviewRender()) te.startPreview();
                if (isValid) playerIn.sendStatusMessage(new TextComponentString(TextFormatting.GREEN + "" + TextFormatting.BOLD + TranslateUtils.toLocal("chat.actionbar.structure.valid")), true);
                return true;
            }
            return false;
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public void addInformation(ItemStack par1ItemStack, @Nullable World world, List par3List, ITooltipFlag par4) {
        if (KeyChecker.isHoldingShift()) {
            par3List.add(TextFormatting.GRAY + TranslateUtils.toLocal("tooltip.block.compressed_base.line2"));
            par3List.add(TextFormatting.GRAY + TranslateUtils.toLocal("tooltip.block.compressed_base.line3"));
            par3List.add(TextFormatting.GRAY + TranslateUtils.toLocal("tooltip.block.compressed_base.line4"));
        } else {
            par3List.add(TextFormatting.AQUA + TranslateUtils.toLocal("tooltip.block.compressed_base.line1.p1") + " " + TextFormatting.DARK_GREEN + TranslateUtils.toLocal("tooltip.block.compressed_base.line1.p2"));
            if (ConfigValues.getShowPreviewRender()) par3List.add(TextFormatting.LIGHT_PURPLE + TranslateUtils.toLocal("tooltip.block.compressed_base.line5"));
            par3List.add(TranslateUtils.toLocal("tooltip.item.hold") + " " + TextFormatting.YELLOW + TextFormatting.ITALIC + TranslateUtils.toLocal("tooltip.item.shift"));
        }
    }

    @Nonnull
    @Override
    @SideOnly(Side.CLIENT)
    public BlockRenderLayer getBlockLayer() {
        return BlockRenderLayer.CUTOUT;
    }

    @Override
    public EnumBlockRenderType getRenderType(IBlockState state)
    {
        return EnumBlockRenderType.MODEL;
    }

    @SuppressWarnings("deprecation")
    @Override
    public boolean isFullCube(IBlockState state)
    {
        return false;
    }

    @SuppressWarnings("deprecation")
    @Override
    public boolean isOpaqueCube(IBlockState state)
    {
        return false;
    }

    @SideOnly(Side.CLIENT)
    @SuppressWarnings("deprecation")
    @Override
    public boolean shouldSideBeRendered(IBlockState blockState, IBlockAccess blockAccess, BlockPos pos, EnumFacing side)
    {
        return false;
    }

    public static boolean isCompressedBaseExtension(World world, BlockPos pos){
        return world.getBlockState(pos).getBlock() instanceof BlockCompressedBaseExtension;
    }

    @Nullable
    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta) {
        return new TileEntityCompressedBase();
    }

    private static Directions getDirections(World world, BlockPos pos) {
        Directions directions = Directions.NONE;
        if (isCompressedBaseExtension(world, pos.north())) {
            if (isCompressedBaseExtension(world, pos.south())) {
                if (isCompressedBaseExtension(world, pos.east())) {
                    if (isCompressedBaseExtension(world, pos.west())) {
                        directions = Directions.ALL;
                    } else {
                        directions = Directions.NORTH_SOUTH_EAST;
                    }
                } else if (isCompressedBaseExtension(world, pos.west())) {
                    directions = Directions.NORTH_SOUTH_WEST;
                } else {
                    directions = Directions.NORTH_SOUTH;
                }
            } else if (isCompressedBaseExtension(world, pos.east())) {
                if (isCompressedBaseExtension(world, pos.west())) {
                    directions = Directions.NORTH_EAST_WEST;
                } else {
                    directions = Directions.NORTH_EAST;
                }
            } else if (isCompressedBaseExtension(world, pos.west())) {
                directions = Directions.NORTH_WEST;
            } else {
                directions = Directions.NORTH;
            }
        } else if (isCompressedBaseExtension(world, pos.south())) {
            if (isCompressedBaseExtension(world, pos.east())) {
                if (isCompressedBaseExtension(world, pos.west())) {
                    directions = Directions.SOUTH_EAST_WEST;
                } else {
                    directions = Directions.SOUTH_EAST;
                }
            } else if (isCompressedBaseExtension(world, pos.west())) {
                directions = Directions.SOUTH_WEST;
            } else {
                directions = Directions.SOUTH;
            }
        } else if (isCompressedBaseExtension(world, pos.east())) {
            if (isCompressedBaseExtension(world, pos.west())) {
                directions = Directions.EAST_WEST;
            } else {
                directions = Directions.EAST;
            }
        } else if (isCompressedBaseExtension(world, pos.west())) {
            directions = Directions.WEST;
        }
        return directions;
    }

    private static boolean isValidArea(World world, BlockPos centralPos, Directions directions) {
        int horizontal = 4;
        int up = 4;
        int down = 0;

        int offsetAmount = 12;

        boolean northCheck = false;
        boolean southCheck = false;
        boolean eastCheck = false;
        boolean westCheck = false;

        LinkedList<BlockPos> blocks = getBlocksWithinArea(centralPos, horizontal, up, down);
        LinkedList<BlockPos> northBlocks = getBlocksWithinArea(centralPos.north(offsetAmount), horizontal, up, down);
        LinkedList<BlockPos> southBlocks = getBlocksWithinArea(centralPos.south(offsetAmount), horizontal, up, down);
        LinkedList<BlockPos> eastBlocks = getBlocksWithinArea(centralPos.east(offsetAmount), horizontal, up, down);
        LinkedList<BlockPos> westBlocks = getBlocksWithinArea(centralPos.west(offsetAmount), horizontal, up, down);

        switch (directions) {
            default:
            case NONE:
                break;
            case NORTH:
                northCheck = doesAreaContainUnbreakable(world, northBlocks) || doesAreaContainTileEntity(world, northBlocks);
                break;
            case SOUTH:
                northCheck = doesAreaContainUnbreakable(world, southBlocks) || doesAreaContainTileEntity(world, southBlocks);
                break;
            case EAST:
                northCheck = doesAreaContainUnbreakable(world, eastBlocks) || doesAreaContainTileEntity(world, eastBlocks);
                break;
            case WEST:
                northCheck = doesAreaContainUnbreakable(world, westBlocks) || doesAreaContainTileEntity(world, westBlocks);
                break;
            case NORTH_SOUTH:
                northCheck = doesAreaContainUnbreakable(world, northBlocks) || doesAreaContainTileEntity(world, northBlocks);
                southCheck = doesAreaContainUnbreakable(world, southBlocks) || doesAreaContainTileEntity(world, southBlocks);
                break;
            case NORTH_EAST:
                northCheck = doesAreaContainUnbreakable(world, northBlocks) || doesAreaContainTileEntity(world, northBlocks);
                eastCheck = doesAreaContainUnbreakable(world, eastBlocks) || doesAreaContainTileEntity(world, eastBlocks);
                break;
            case NORTH_WEST:
                northCheck = doesAreaContainUnbreakable(world, northBlocks) || doesAreaContainTileEntity(world, northBlocks);
                westCheck = doesAreaContainUnbreakable(world, westBlocks) || doesAreaContainTileEntity(world, westBlocks);
                break;
            case SOUTH_EAST:
                southCheck = doesAreaContainUnbreakable(world, southBlocks) || doesAreaContainTileEntity(world, southBlocks);
                eastCheck = doesAreaContainUnbreakable(world, eastBlocks) || doesAreaContainTileEntity(world, eastBlocks);
                break;
            case SOUTH_WEST:
                southCheck = doesAreaContainUnbreakable(world, southBlocks) || doesAreaContainTileEntity(world, southBlocks);
                westCheck = doesAreaContainUnbreakable(world, westBlocks) || doesAreaContainTileEntity(world, westBlocks);
                break;
            case EAST_WEST:
                eastCheck = doesAreaContainUnbreakable(world, eastBlocks) || doesAreaContainTileEntity(world, eastBlocks);
                westCheck = doesAreaContainUnbreakable(world, westBlocks) || doesAreaContainTileEntity(world, westBlocks);
                break;
            case NORTH_SOUTH_EAST:
                northCheck = doesAreaContainUnbreakable(world, northBlocks) || doesAreaContainTileEntity(world, northBlocks);
                southCheck = doesAreaContainUnbreakable(world, southBlocks) || doesAreaContainTileEntity(world, southBlocks);
                eastCheck = doesAreaContainUnbreakable(world, eastBlocks) || doesAreaContainTileEntity(world, eastBlocks);
                break;
            case NORTH_SOUTH_WEST:
                northCheck = doesAreaContainUnbreakable(world, northBlocks) || doesAreaContainTileEntity(world, northBlocks);
                southCheck = doesAreaContainUnbreakable(world, southBlocks) || doesAreaContainTileEntity(world, southBlocks);
                westCheck = doesAreaContainUnbreakable(world, westBlocks) || doesAreaContainTileEntity(world, westBlocks);
                break;
            case NORTH_EAST_WEST:
                northCheck = doesAreaContainUnbreakable(world, northBlocks) || doesAreaContainTileEntity(world, northBlocks);
                eastCheck = doesAreaContainUnbreakable(world, eastBlocks) || doesAreaContainTileEntity(world, eastBlocks);
                westCheck = doesAreaContainUnbreakable(world, westBlocks) || doesAreaContainTileEntity(world, westBlocks);
                break;
            case SOUTH_EAST_WEST:
                southCheck = doesAreaContainUnbreakable(world, southBlocks) || doesAreaContainTileEntity(world, southBlocks);
                eastCheck = doesAreaContainUnbreakable(world, eastBlocks) || doesAreaContainTileEntity(world, eastBlocks);
                westCheck = doesAreaContainUnbreakable(world, westBlocks) || doesAreaContainTileEntity(world, westBlocks);
                break;
            case ALL:
                northCheck = doesAreaContainUnbreakable(world, northBlocks) || doesAreaContainTileEntity(world, northBlocks);
                southCheck = doesAreaContainUnbreakable(world, southBlocks) || doesAreaContainTileEntity(world, southBlocks);
                eastCheck = doesAreaContainUnbreakable(world, eastBlocks) || doesAreaContainTileEntity(world, eastBlocks);
                westCheck = doesAreaContainUnbreakable(world, westBlocks) || doesAreaContainTileEntity(world, westBlocks);
                break;
        }

        return !doesAreaContainUnbreakable(world, blocks) && !doesAreaContainTileEntity(world, blocks) && !northCheck && !southCheck && !eastCheck && !westCheck;
    }

}
