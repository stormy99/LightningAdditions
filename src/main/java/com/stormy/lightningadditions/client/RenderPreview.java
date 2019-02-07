package com.stormy.lightningadditions.client;

import com.stormy.lightningadditions.block.resource.BlockCompressedBase;
import com.stormy.lightningadditions.tile.resource.TileEntityCompressedBase;
import com.stormy.lightninglib.lib.utils.UtilWorld;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BlockRendererDispatcher;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL14;

import java.util.ArrayList;

import static net.minecraft.block.BlockColored.COLOR;

public class RenderPreview {

    private static ArrayList<BlockPos> coordinates = new ArrayList<>();

    public static void doRenderItem(EntityPlayer player, ItemStack item) {
        BlockPos posToRenderAt;
        World world = player.world;

        if (!item.hasTagCompound()) {
            return;
        }

        NBTTagCompound tag = item.getTagCompound();
        assert tag != null;

        if (tag.getBoolean("is_bound")) {
            posToRenderAt = new BlockPos(tag.getInteger("pos_x"), tag.getInteger("pos_y"), tag.getInteger("pos_z"));

            doRender(posToRenderAt, world, 0, 0);
            if (BlockCompressedBase.isCompressedBaseExtension(world, posToRenderAt.north())) RenderPreview.doRender(posToRenderAt, world, 0, -12);    //North render
            if (BlockCompressedBase.isCompressedBaseExtension(world, posToRenderAt.south())) RenderPreview.doRender(posToRenderAt, world, 0, 12);     //South render
            if (BlockCompressedBase.isCompressedBaseExtension(world, posToRenderAt.east())) RenderPreview.doRender(posToRenderAt, world, 12, 0);      //East render
            if (BlockCompressedBase.isCompressedBaseExtension(world, posToRenderAt.west())) RenderPreview.doRender(posToRenderAt, world, -12, 0);     //West render
        }
    }

    public static void doRender(TileEntityCompressedBase te, float xOffset, float zOffset) {
        doRender(te.getPos(), te.getWorld(), xOffset, zOffset);
    }

    public static void doRender(BlockPos pos, World world, float xOffset, float zOffset) {
        ArrayList<BlockPos> sortedCoordinates;

        IBlockState renderState = Blocks.COBBLESTONE.getDefaultState();
        EntityPlayer player = Minecraft.getMinecraft().player;
        double doubleX = player.posX - 0.5;
        double doubleY = player.posY + 0.1;
        double doubleZ = player.posZ - 0.5;

        float offset = 0.25f;

        float xSize = 9f + offset;
        float ySize = 6f + offset;
        float zSize = 9f + offset;

        float mx = pos.getX() + xOffset + (-xSize / 2);
        float my = pos.getY();
        float mz = pos.getZ() + zOffset + (-zSize / 2);

        int horizontalSize = 8;
        int vertSize = 4;

        GlStateManager.disableLighting();
        Tessellator tess = Tessellator.getInstance();
        BufferBuilder buffer = tess.getBuffer();

        BlockRendererDispatcher dispatcher = Minecraft.getMinecraft().getBlockRendererDispatcher();
        Minecraft.getMinecraft().renderEngine.bindTexture(TextureMap.LOCATION_BLOCKS_TEXTURE);

        for (int j = 0; j <= vertSize; j++) {
            for (int i = 0; i <= horizontalSize; i++) {
                addToCords(new BlockPos(mx, my, mz).east(i).up(j));
            }
            for (int i = 0; i <= horizontalSize; i++) {
                addToCords(new BlockPos(mx, my, mz).south(i).up(j));
            }
            for (int i = 0; i <= horizontalSize; i++) {
                addToCords(new BlockPos(mx + horizontalSize, my, mz + horizontalSize).east(-i).up(j));
            }
            for (int i = 0; i <= horizontalSize; i++) {
                addToCords(new BlockPos(mx + horizontalSize, my, mz + horizontalSize).south(-i).up(j));
            }
        }

        for (int i = 0; i <= horizontalSize; i++) {
            for (int j = 0; j <= horizontalSize; j++) {
                addToCords(new BlockPos(mx, my, mz).up(vertSize).east(i).south(j));
            }
        }

        sortedCoordinates = sortCords(player);

        for (BlockPos coord : sortedCoordinates) {
            renderBlock(doubleX, doubleY, doubleZ, renderState, coord, world, buffer, tess, dispatcher);
        }

        coordinates.clear();

        //Shows blocks that will get replaced (Don't know if I'll keep)
//        for (BlockPos errorPos : WorldUtil.getBlocksWithinArea(te.getPos(), 4, 4, 0)) {
//            if ((!te.getWorld().isAirBlock(errorPos)) && (!errorPos.equals(te.getPos()))) {
//                renderErrorBlock(doubleX, doubleY, doubleZ, Blocks.AIR.getDefaultState(), errorPos, te.getWorld(), buffer, tess, dispatcher);
//            }
//        }

    }

    private static void addToCords(BlockPos pos) {
        if (!coordinates.contains(pos)) coordinates.add(pos);
    }

    private static ArrayList<BlockPos> sortCords(EntityPlayer player) {
        return UtilWorld.sortByDistance(coordinates, player);
    }

    private static void renderBlock(double doubleX, double doubleY, double doubleZ, IBlockState state, BlockPos pos, World world, BufferBuilder buffer, Tessellator tess, BlockRendererDispatcher dispatcher) {
        BlockPos newPos = pos.add(1, 0, 1);

//        if (!(world.getBlockState(newPos).getBlock() == Blocks.AIR)) {
//            return;
//        }

        GlStateManager.pushMatrix();
        GlStateManager.enableBlend();
        GlStateManager.blendFunc(GL11.GL_CONSTANT_ALPHA, GL11.GL_ONE_MINUS_CONSTANT_ALPHA);
        GlStateManager.translate(-doubleX, -doubleY, -doubleZ);
        buffer.begin(7, DefaultVertexFormats.BLOCK);

        IBlockState newState = state.getActualState(world, newPos);

        GL14.glBlendColor(1f, 1f, 1f, 0.5f);

        dispatcher.renderBlock(newState, newPos, world, buffer);

        //Render red if block in space
        if (!(world.getBlockState(newPos).getBlock() == Blocks.AIR) && !(world.getBlockState(newPos).getBlock().isReplaceable(world, newPos))) {
            dispatcher.renderBlock(Blocks.STAINED_GLASS.getDefaultState().withProperty(COLOR, EnumDyeColor.RED), newPos, world, buffer);
        }

        GlStateManager.translate(-0.5, 0.1, -0.5);

        tess.draw();
        GlStateManager.disableBlend();
        GlStateManager.popMatrix();
    }

    private static void renderErrorBlock(double doubleX, double doubleY, double doubleZ, IBlockState state, BlockPos pos, World world, BufferBuilder buffer, Tessellator tess, BlockRendererDispatcher dispatcher) {
        GlStateManager.pushMatrix();
        GlStateManager.enableBlend();
        GlStateManager.blendFunc(GL11.GL_CONSTANT_ALPHA, GL11.GL_ONE_MINUS_CONSTANT_ALPHA);
        GlStateManager.translate(-doubleX, -doubleY, -doubleZ);
        buffer.begin(7, DefaultVertexFormats.BLOCK);

        IBlockState newState = state.getActualState(world, pos);

        GL14.glBlendColor(1f, 1f, 1f, 0.5f);

//        dispatcher.renderBlock(newState, pos, world, buffer);
        dispatcher.renderBlock(Blocks.STAINED_GLASS.getDefaultState().withProperty(COLOR, EnumDyeColor.RED), pos, world, buffer);

        GlStateManager.translate(-0.5, 0.1, -0.5);

        tess.draw();
        GlStateManager.disableBlend();
        GlStateManager.popMatrix();
    }

}
