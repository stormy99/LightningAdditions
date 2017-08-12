package com.stormy.lightningadditions.handler.fatality;

import net.minecraft.scoreboard.IScoreCriteria;
import net.minecraft.scoreboard.ScoreObjective;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.event.world.WorldEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class FatalityEventHandler
{

    @SubscribeEvent
    public void onWordGenerateSpawnPoint( WorldEvent.Load event)
    {
        World world = event.getWorld();
        ScoreObjective objective = world.getScoreboard().getObjective("deaths");
        if (objective == null)
        {
            objective = world.getScoreboard().addScoreObjective("deaths", IScoreCriteria.DEATH_COUNT);
            objective.setDisplayName(TextFormatting.LIGHT_PURPLE + "Fatalities");
            world.getScoreboard().setObjectiveInDisplaySlot(0, objective); }
    }

    @SubscribeEvent
    public void onWordGenerateSpawnPoint( WorldEvent.CreateSpawnPosition event)
    {
        World world = event.getWorld();
        ScoreObjective objective = world.getScoreboard().addScoreObjective("deaths", IScoreCriteria.DEATH_COUNT);
        objective.setDisplayName(TextFormatting.LIGHT_PURPLE + "Fatalities");
        world.getScoreboard().setObjectiveInDisplaySlot(0, objective); }

}
