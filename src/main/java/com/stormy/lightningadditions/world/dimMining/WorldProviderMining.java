package com.stormy.lightningadditions.world.dimMining;

import com.stormy.lightningadditions.LightningAdditions;
import com.stormy.lightningadditions.init.ModDimensions;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.DimensionType;
import net.minecraft.world.Teleporter;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.IChunkGenerator;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class WorldProviderMining extends WorldProvider
{
    private static final Vec3d zeroVector = new Vec3d(0.0D, 0.0D, 0.0D);

    public WorldProviderMining()
    {
        this.doesWaterVaporize = false;
    }

    public IChunkGenerator createChunkGenerator()
    {return new ChunkGeneratorMining(this.world, this.world.getSeed(), false);}

    public String getSaveFolder()
    {return "LA_MINING_" + getDimension();}

    public boolean canRespawnHere()
    {return false;}

    @SideOnly(Side.CLIENT)
    public double getVoidFogYFactor()
    {return 1.0D;}

    public boolean isDaytime()
    {return true;}

    public void calculateInitialWeather()
    {resetRainAndThunder();}

    public void updateWeather()
    {
        if (this.world.isRaining()) {
            this.world.getWorldInfo().setRaining(false);
        }
        if (this.world.isThundering()) {
            this.world.getWorldInfo().setThundering(false);
        }
    }

    public Teleporter getDefaultTeleporter()
    {return null;}

    public long getWorldTime() {return 6000L;}

    public float calculateCelestialAngle(long par1, float par3)
    {return 0.0F;}

    public boolean canDoLightning(Chunk chunk)
    {return false;}

    public boolean canDoRainSnowIce(Chunk chunk)
    {return false;}

    public DimensionType getDimensionType()
    {return ModDimensions.MiningDimType;}
}
