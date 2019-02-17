package com.stormy.lightningadditions.client.model;

import com.stormy.lightninglib.lib.utils.ResourceHelperLA;
import net.minecraft.client.model.ModelBiped;

public class ModelAmethystArmor extends ModelBiped
{
    public ModelRenderOBJ helmet;
    public ModelRenderOBJ chestplate;
    public ModelRenderOBJ leggings;
    public ModelRenderOBJ boots;

    public ModelAmethystArmor(float modelSize, boolean isHelmet, boolean isChestPiece, boolean isLeggings, boolean isBoots) {
        super(modelSize);

        this.helmet = new ModelRenderOBJ(this, ResourceHelperLA.getResource("models/armor/amethyst/amethyst_helmet.obj"), ResourceHelperLA.getResource("models/armor/amethyst/amethyst_armor"));
        this.chestplate = new ModelRenderOBJ(this, ResourceHelperLA.getResource("models/armor/amethyst/amethyst_chestplate.obj"), ResourceHelperLA.getResource("models/armor/amethyst/amethyst_armor"));
        this.leggings = new ModelRenderOBJ(this, ResourceHelperLA.getResource("models/armor/amethyst/amethyst_leggings.obj"), ResourceHelperLA.getResource("models/armor/amethyst/amethyst_armor"));
        this.boots = new ModelRenderOBJ(this, ResourceHelperLA.getResource("models/armor/amethyst/amethyst_boots.obj"), ResourceHelperLA.getResource("models/armor/amethyst/amethyst_armor"));
    }

}
