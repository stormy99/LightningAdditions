package com.stormy.lightningadditions.init;

import com.stormy.lightningadditions.block.*;
import com.stormy.lightningadditions.creativetab.CreativeTabLA;
import com.stormy.lightningadditions.reference.ModInformation;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.fml.common.registry.GameRegistry;

import static com.stormy.lightningadditions.LightningAdditions.TUBE;
import static com.stormy.lightningadditions.LightningAdditions.TUBE_REVERSE;
import static com.stormy.lightningadditions.LightningAdditions.TUBE_WINDOWED;

public class ModBlocks
{


    //General Blocks
    public static Block clear_glass;
    public static Block share_xp;
    public static Block reinforced_obsidian;
    public static Block reinforced_obsidianglass;
    public static Block noise_muffler;


    public static void init()
    {
        //General
        share_xp = new BlockShareXP().setUnlocalizedName("share_xp").setRegistryName("share_xp").setCreativeTab(CreativeTabLA.LA_TAB);
        clear_glass = new BlockClearGlass().setUnlocalizedName("clear_glass").setRegistryName("clear_glass").setCreativeTab(CreativeTabLA.LA_TAB);
        reinforced_obsidian = new BlockReinforcedObsidian().setUnlocalizedName("reinforced_obsidian").setRegistryName("reinforced_obsidian").setCreativeTab(CreativeTabLA.LA_TAB);
        reinforced_obsidianglass = new BlockReinforcedGlass().setUnlocalizedName("reinforced_obsidianglass").setRegistryName("reinforced_obsidianglass").setCreativeTab(CreativeTabLA.LA_TAB);
        noise_muffler = new BlockSoundMuffler().setUnlocalizedName("noise_muffler").setRegistryName("noise_muffler").setCreativeTab(CreativeTabLA.LA_TAB);
    }


    public static void register()
    {

        registerBlock(share_xp);
        registerBlock(clear_glass);
        registerBlock(TUBE); registerBlock(TUBE_REVERSE); registerBlock(TUBE_WINDOWED);
        registerBlock(reinforced_obsidian);
        registerBlock(reinforced_obsidianglass);
        registerBlock(noise_muffler);
    }

    public static void registerRenders()
    {
        registerRender(share_xp);
        registerRender(clear_glass);
        registerRender(TUBE); registerRender(TUBE_REVERSE); registerRender(TUBE_WINDOWED);
        registerRender(reinforced_obsidian);
        registerRender(reinforced_obsidianglass);
        registerRender(noise_muffler);

    }





    private static void registerBlock(Block block)
    {
        GameRegistry.register(block);
        ItemBlock item = new ItemBlock(block);
        item.setRegistryName(block.getRegistryName());
        GameRegistry.register(item);
    }

    public static void registerRender(Block block)
    {
        Item item = Item.getItemFromBlock(block);
        Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(item, 0, new ModelResourceLocation(ModInformation.MODID + ":" + block.getUnlocalizedName().substring(5), "inventory"));
    }


}
