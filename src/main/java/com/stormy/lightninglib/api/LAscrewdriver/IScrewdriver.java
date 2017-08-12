/*
 * ********************************************************************************
 * Copyright (c) 2017 StormyMode, MiningMark48. All Rights Reserved!
 * This file is part of Lightning Additions (MC-Mod).
 *
 * This project cannot be copied and/or distributed without the express
 * permission of StormyMode, MiningMark48 (Developers)!
 * ********************************************************************************
 */

package com.stormy.lightninglib.api.LAscrewdriver;

import net.minecraft.entity.player.EntityPlayer;

/**
 * Essentially validates whether 'Body A' can be used as a Screwdriver
 * Must be used by Block/Entity targets to determine whether 'Sonic' activation.
 * Responsible for checking if the Screwdriver can be used for this target using {@link IScrewdriver#canSonic(EntityPlayer, SonicTarget)}.
 * If it can be used, the target must first call {@link IScrewdriver#beforeUse(EntityPlayer, SonicTarget)}
 * After that the target can call its own logic.
 * Finally, the target must call {@link IScrewdriver#afterUse(EntityPlayer, SonicTarget)}.
 */

public interface IScrewdriver
{

    //IScrewdriver#canSonic
    /**
     * Check if the screwdriver can be used.
     * @param player The player.
     * @param target Target that is being sonic'd.
     * @return If it can be used.
     */
    public boolean canSonic(EntityPlayer player, SonicTarget target);


    //IScrewdriver#beforeUse
    /**
     * Called before the screwdriver is being used after the canUse check if done.
     * @param player The player.
     * @param target Target that is being sonic'd.
     */
    public void beforeUse(EntityPlayer player, SonicTarget target);


    //IScrewdriver#afterUse
    /**
     * Called after the screwdriver is used.
     * @param player The player.
     * @param target Target that is being sonic'd.
     */
    public void afterUse(EntityPlayer player, SonicTarget target);

}
