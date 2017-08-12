package invtweaks.api;

import java.util.Collection;
import java.util.List;

@SuppressWarnings("unused")
public interface IItemTreeCategory {
    boolean contains(IItemTreeItem item);

    void addCategory(IItemTreeCategory category);

    void addItem(IItemTreeItem item);

    Collection<IItemTreeCategory> getSubCategories();

    Collection<List<IItemTreeItem>> getItems();

    String getName();

    int getCategoryOrder();

    int findCategoryOrder(String keyword);

    int findKeywordDepth(String keyword);
}
