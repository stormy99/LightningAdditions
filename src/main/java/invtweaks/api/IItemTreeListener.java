/*
 * Copyright (c) 2017 KitsuneAlex. All rights reserved!
 * Do not distribute or redistribute without the explicit permission
 * of the developer!
 */

package invtweaks.api;

import java.util.EventListener;

@SuppressWarnings("unused")
public interface IItemTreeListener extends EventListener {
    void onTreeLoaded(IItemTree tree);
}
