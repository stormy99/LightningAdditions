package invtweaks.api;

import net.minecraft.nbt.NBTTagCompound;

import java.util.Collection;
import java.util.List;
import java.util.Random;

@SuppressWarnings("unused")
public interface IItemTree {
    void registerOre(String category, String name, String oreName, int order);

    boolean matches(List<IItemTreeItem> items, String keyword);

    boolean isKeywordValid(String keyword);

    Collection<IItemTreeCategory> getAllCategories();

    IItemTreeCategory getRootCategory();

    void setRootCategory(IItemTreeCategory category);

    IItemTreeCategory getCategory(String keyword);

    boolean isItemUnknown(String id, int damage);

    List<IItemTreeItem> getItems(String id, int damage, NBTTagCompound extra);

    List<IItemTreeItem> getItems(String id, int damage);

    List<IItemTreeItem> getItems(String name);

    IItemTreeItem getRandomItem(Random r);

    boolean containsItem(String name);

    boolean containsCategory(String name);

    IItemTreeCategory addCategory(String parentCategory, String newCategory) throws NullPointerException;

    void addCategory(String parentCategory, IItemTreeCategory newCategory) throws NullPointerException;

    IItemTreeItem addItem(String parentCategory, String name, String id, int damage, int order)
            throws NullPointerException;

    IItemTreeItem addItem(String parentCategory, String name, String id, int damage, NBTTagCompound extra, int order)
            throws NullPointerException;

    void addItem(String parentCategory, IItemTreeItem newItem) throws NullPointerException;

    int getKeywordDepth(String keyword);

    int getKeywordOrder(String keyword);
}
