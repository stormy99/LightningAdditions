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

import com.stormy.lightningadditions.reference.ModInformation;
import com.stormy.lightningadditions.config.ConfigurationManagerLA;
import com.stormy.lightningadditions.utility.logger.LALogger;
import com.stormy.lightningadditions.world.dimMining.WorldProviderMining;
import com.stormy.lightningadditions.world.dimvoid.VoidCreator;
import net.minecraft.world.DimensionType;
import net.minecraftforge.common.DimensionManager;

public class ModDimensions {

    public static DimensionType VoidDimType;
    public static DimensionType MiningDimType;

    public static void init(){
        VoidDimType = DimensionType.register(ModInformation.MODID, "void", ConfigurationManagerLA.dimID, VoidCreator.class, true);
        MiningDimType = DimensionType.register(ModInformation.MODID, "mining", ConfigurationManagerLA.dimMiningID, WorldProviderMining.class, true);
        DimensionManager.registerDimension(ConfigurationManagerLA.dimID, VoidDimType);
        DimensionManager.registerDimension(ConfigurationManagerLA.dimMiningID, MiningDimType);

        LALogger.log("LA Dimensions registered.");
    }

}
