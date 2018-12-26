package com.stormy.lightningadditions.energy;

import cofh.redstoneflux.impl.EnergyStorage;

public class LAEnergyStorage extends EnergyStorage
{
    public LAEnergyStorage(int capacity, int maxReceive)
    { super(capacity, maxReceive, 0); }

    public void setEnergy(int energy)
    { this.energy = energy; }

    public void consumePower(int energy) {
        this.energy -= energy;
        if(this.energy < 0) { this.energy = 0; }
    }
}
