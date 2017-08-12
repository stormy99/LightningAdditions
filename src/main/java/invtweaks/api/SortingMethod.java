package invtweaks.api;

@SuppressWarnings("unused")
public enum SortingMethod {
    /**
     * Standard 'r' sorting for generic inventories
     */
    DEFAULT,
    /**
     * Sort method creating vertical columns of items.
     * Used for chests only, requires container to have a valid row size for correct results.
     */
    VERTICAL,
    /**
     * Sort method creating horizontal rows of items.
     * Used for chests only, requires container to have a valid row size for correct results.
     */
    HORIZONTAL,
    /**
     * Sort method for player inventory.
     * Applies to extra player-specified sorting rules for the main inventory.
     * Will always operate on main inventory.
     */
    INVENTORY,
    /**
     * Attempts to even the number of items in each stack of the same type of item, without moving full stacks.
     * Used in crafting grid sorting.
     */
    EVEN_STACKS,
}
