package com.stormy.lightningadditions.block.generator;

import com.stormy.lightningadditions.LightningAdditions;
import com.stormy.lightningadditions.client.gui.GuiHandler;
import com.stormy.lightningadditions.tile.generator.TileEntitySolarGenerator;
import com.stormy.lightningadditions.utility.logger.LALogger;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class BlockSolarGenerator extends BlockContainer{

    public BlockSolarGenerator() {
        super(Material.ROCK);
        setHardness(1.0f);
        setResistance(0.5f);
    }

    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing side, float hitX, float hitY, float hitZ) {
        if (!worldIn.isRemote){
            if (playerIn != null) {
                LALogger.info("CLICKED");
                playerIn.openGui(LightningAdditions.INSTANCE, GuiHandler.gui_id_solar_generator, worldIn, pos.getX(), pos.getY(), pos.getZ());
            }
        }
        return true;
    }

    @Nullable
    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta) {
        return new TileEntitySolarGenerator();
    }
}
