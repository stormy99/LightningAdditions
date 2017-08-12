package invtweaks.api;

import net.minecraft.nbt.NBTTagCompound;

@SuppressWarnings("unused")
public interface IItemTreeItem extends Comparable<IItemTreeItem> {
    String getName();

    String getId();

    int getDamage();

    NBTTagCompound getExtraData();

    int getOrder();
}
