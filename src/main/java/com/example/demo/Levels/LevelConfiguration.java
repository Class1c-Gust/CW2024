package com.example.demo.Levels;

public class LevelConfiguration {
    private final int totalEnemies;
    private final int killsToAdvance;
    private final double enemySpawnProbability;
    private final int playerInitialHealth;

    public LevelConfiguration(int levelNumber) {
        this.totalEnemies = 5 + (levelNumber);
        this.killsToAdvance = 10 + (levelNumber * 2);
        this.enemySpawnProbability = 0.20 + (levelNumber * 0.01);
        this.playerInitialHealth = 5;
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

    public int getPlayerInitialHealth() {
        return playerInitialHealth;
    }
}