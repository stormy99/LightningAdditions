package com.stormy.lightningadditions.sounds;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import net.minecraft.client.audio.ISound;
import net.minecraft.client.audio.Sound;
import net.minecraft.client.audio.SoundEventAccessor;
import net.minecraft.client.audio.SoundHandler;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;

public class SoundMuffler
        implements ISound
{
    final ISound original;
    final float volModifier;

    public SoundMuffler(ISound original, float volModifier)
    {
        this.original = original;
        this.volModifier = volModifier;
    }

    @Nonnull
    public Sound getSound()
    {
        return this.original.getSound();
    }

    @Nonnull
    public SoundCategory getCategory()
    {
        return this.original.getCategory();
    }

    @Nonnull
    public ResourceLocation getSoundLocation()
    {
        return this.original.getSoundLocation();
    }

    @Nullable
    public SoundEventAccessor createAccessor(@Nonnull SoundHandler handler)
    {
        return this.original.createAccessor(handler);
    }

    public boolean canRepeat()
    {
        return this.original.canRepeat();
    }

    public int getRepeatDelay()
    {
        return this.original.getRepeatDelay();
    }

    public float getVolume()
    {
        return this.original.getVolume() * this.volModifier;
    }

    public float getPitch()
    {
        return this.original.getPitch();
    }

    public float getXPosF()
    {
        return this.original.getXPosF();
    }

    public float getYPosF()
    {
        return this.original.getYPosF();
    }

    public float getZPosF()
    {
        return this.original.getZPosF();
    }

    @Nonnull
    public ISound.AttenuationType getAttenuationType()
    {
        return this.original.getAttenuationType();
    }
}

