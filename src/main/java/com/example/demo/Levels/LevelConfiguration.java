package com.example.demo.Levels;

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
        this.totalEnemies = 2 + (levelNumber);
        this.killsToAdvance = 10 + (levelNumber * 2);
        this.enemySpawnProbability = 0.20 + (levelNumber * 0.01);
        this.advancedEnemyProbability = (levelNumber * 0.1);
        this.playerInitialHealth = 5;
        this.heartSpawnProbability = levelNumber * 0.0001;
        this.freezeSpawnProbability = levelNumber * 0.0001;
        this.multishotSpawnProbability = levelNumber * 0.0001;
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