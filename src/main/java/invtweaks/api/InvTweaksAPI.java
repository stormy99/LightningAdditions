package invtweaks.api;

import invtweaks.api.container.ContainerSection;
import net.minecraft.item.ItemStack;

import javax.annotation.Nonnull;

/**
 * Interface to access functions exposed by Inventory Tweaks
 * <p>
 * The main @Mod instance of the mod implements this interface, so a refernce to it can
 * be obtained via @Instance("inventorytweaks") or methods in net.minecraftforge.fml.common.Loader
 * <p>
 * All of these functions currently have no effect if called on a dedicated server.
 */
@SuppressWarnings("unused")
public interface InvTweaksAPI {
    /**
     * Add a listener for ItemTree load events
     */
    void addOnLoadListener(IItemTreeListener listener);

    /**
     * Remove a listener for ItemTree load events
     *
     * @return true if the listener was previously added
     */
    boolean removeOnLoadListener(IItemTreeListener listener);

    /**
     * Toggle sorting shortcut state.
     */
    void setSortKeyEnabled(boolean enabled);

    /**
     * Toggle sorting shortcut supression.
     * Unlike setSortKeyEnabled, this flag is automatically cleared when GUIs are closed.
     */
    void setTextboxMode(boolean enabled);

    /**
     * Compare two items using the default (non-rule based) algorithm,
     * sutable for an implementation of Comparator&lt;ItemStack&gt;.
     *
     * @param i
     * @param j
     * @return A value with a sign representing the relative order of the item stacks
     */
    int compareItems(@Nonnull ItemStack i, @Nonnull ItemStack j);

    /**
     * Initiate a sort as if the player had clicked on a sorting button or pressed the sort key.
     */
    void sort(ContainerSection section, SortingMethod method);
}
