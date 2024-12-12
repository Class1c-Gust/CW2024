package com.example.demo.Levels;

import com.example.demo.config.Config;

public class LevelConfiguration {
    private final int totalEnemies;
    private final int killsToAdvance;
    private final double enemySpawnProbability;
    private final int playerInitialHealth;
    private final double heartSpawnProbability;
    private final double freezeSpawnProbability;
    private final double multishotSpawnProbability;
    private final int powerupLimit;
    private final double advancedEnemyProbability;


    public LevelConfiguration(int levelNumber) {
        this.totalEnemies = Config.Level.BASE_ENEMY_COUNT + (levelNumber);
        this.killsToAdvance = Config.Level.BASE_KILLS_TO_ADVANCE + (levelNumber * 2);
        this.enemySpawnProbability = Config.Level.BASE_ENEMY_SPAWN_PROBABILITY + (levelNumber * 0.01);
        this.advancedEnemyProbability = (levelNumber * 0.0040);
        this.playerInitialHealth = Config.Player.DEFAULT_HEALTH;
        this.heartSpawnProbability = levelNumber * 0.0001;
        this.freezeSpawnProbability = levelNumber * 0.0003;
        this.multishotSpawnProbability = levelNumber * 0.00001;
        this.powerupLimit = levelNumber-1;
    }

    public int getTotalEnemies() {
        return totalEnemies;
    }

    public int getKillsToAdvance() {
        return killsToAdvance;
    }

    public double getEnemySpawnProbability() {
        return enemySpawnProbability;
    }
    public double getAdvancedEnemyProbability() {
        return advancedEnemyProbability;
    }

    public int getPowerupLimit() {
        return powerupLimit;
    }

    public double getFreezeSpawnProbability() {
        return freezeSpawnProbability;
    }

    public double getMultishotSpawnProbability() {
        return multishotSpawnProbability;
    }

    public double getHeartSpawnProbability() {
        return heartSpawnProbability;
    }

    public int getPlayerInitialHealth() {
        return playerInitialHealth;
    }
}