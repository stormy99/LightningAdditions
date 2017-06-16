package com.stormy.lightningadditions.init;

import com.stormy.lightningadditions.creativetab.CreativeTabLA;
import com.stormy.lightningadditions.item.base.ItemLA;
import com.stormy.lightningadditions.item.resource.*;
import com.stormy.lightningadditions.reference.ModInformation;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.fml.common.registry.GameRegistry;

import java.util.EnumMap;

public class ModItems
{
        //General Items
         public static Item tachyon_shard;
         public static Item ender_backpack;
         public static Item atomic_inhibitor;
         public static Item atomic_magnet;
         public static Item emerald_apple;
         public static Item sponge_stick;



    public static void init()
    {


        //General Items
        tachyon_shard = new ItemLA().setUnlocalizedName("tachyon_shard").setRegistryName("tachyon_shard").setCreativeTab(CreativeTabLA.LA_TAB);
        ender_backpack = new ItemEnderBackpack().setUnlocalizedName("ender_backpack").setRegistryName("ender_backpack").setCreativeTab(CreativeTabLA.LA_TAB);
        atomic_inhibitor = new ItemAtomicInhibitor().setUnlocalizedName("atomic_inhibitor").setRegistryName("atomic_inhibitor").setCreativeTab(CreativeTabLA.LA_TAB);
        atomic_magnet = new ItemAtomicMagnet().setUnlocalizedName("atomic_magnet").setRegistryName("atomic_magnet").setCreativeTab(CreativeTabLA.LA_TAB);
        emerald_apple = new ItemEmeraldApple().setUnlocalizedName("emerald_apple").setRegistryName("emerald_apple").setCreativeTab(CreativeTabLA.LA_TAB);
        sponge_stick = new ItemStickSponge().setUnlocalizedName("sponge_stick").setRegistryName("sponge_stick").setCreativeTab(CreativeTabLA.LA_TAB);


    }


    public static void register()
    {
        //General Items
        GameRegistry.register(tachyon_shard);
        GameRegistry.register(ender_backpack);
        GameRegistry.register(atomic_inhibitor);
        GameRegistry.register(atomic_magnet);
        GameRegistry.register(emerald_apple);
        GameRegistry.register(sponge_stick);
    }


    public static void registerRenders()
    {
        //General Items
        registerRender(tachyon_shard);
        registerRender(ender_backpack);
        registerRender(atomic_inhibitor);
        registerRender(atomic_magnet);
        registerRender(emerald_apple);
        registerRender(sponge_stick);
    }






    public static void registerRender(Item item){
        Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(item, 0, new ModelResourceLocation(ModInformation.MODID + ":" + item.getUnlocalizedName().substring(5), "inventory"));
    }

    public static void registerRender(Item item, int meta, String name){
        Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(item, meta, new ModelResourceLocation(ModInformation.MODID + ":" + name, "inventory"));
    }

    public static EnumMap<EnumDyeColor, ItemBlock> elevators = new EnumMap<EnumDyeColor, ItemBlock>(EnumDyeColor.class);

}
