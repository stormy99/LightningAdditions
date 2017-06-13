package com.stormy.lightningadditions.utility.tubing;

import com.stormy.lightningadditions.tile.TileEntityTube;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;

public class GuiHandlerTube implements IGuiHandler
{
    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
    {
        return new ContainerWTube((TileEntityTube) world.getTileEntity(new BlockPos(x, y, z)));
    }

    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
    {
        return new GuiHandlerWTube((TileEntityTube) world.getTileEntity(new BlockPos(x, y, z)));
    }
}
