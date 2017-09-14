/*
 *
 *  * ********************************************************************************
 *  * Copyright (c) 2017 StormyMode, MiningMark48. All Rights Reserved!
 *  * This file is part of Lightning Additions (MC-Mod).
 *  *
 *  * This project cannot be copied and/or distributed without the express
 *  * permission of StormyMode, MiningMark48 (Developers)!
 *  * ********************************************************************************
 *
 */

package com.stormy.lightningadditions.init;

import com.stormy.lightningadditions.feature.debug.CommandUUID;
import com.stormy.lightningadditions.feature.KeyHideGui;
import com.stormy.lightningadditions.feature.calc.CalcKey;
import com.stormy.lightningadditions.feature.lightchunkutil.ChunkBoundariesHandler;
import com.stormy.lightningadditions.feature.lightchunkutil.LightChunkKeyBinds;
import com.stormy.lightningadditions.feature.lightchunkutil.LightOverlayHandler;

public class ModKeys {

    public static void init() {
        LightChunkKeyBinds.init();
        LightOverlayHandler.init();
        ChunkBoundariesHandler.init();
        CalcKey.init();
        KeyHideGui.init();
        CommandUUID.CopyKey.init();
    }
}
