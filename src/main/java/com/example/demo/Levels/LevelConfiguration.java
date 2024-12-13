package com.example.demo.Levels;

import com.example.demo.config.Config;

/**
 * Represents the configuration for a game level.
 * This class provides parameters for enemy spawning, player health, and power-up probabilities,
 * based on the current level number.
 */
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

    /**
     * Constructs a LevelConfiguration instance for the specified level number.
     *
     * @param levelNumber the number of the current level
     */
    public LevelConfiguration(int levelNumber) {
        this.totalEnemies = Config.Level.BASE_ENEMY_COUNT + levelNumber;
        this.killsToAdvance = Config.Level.BASE_KILLS_TO_ADVANCE + (levelNumber * 2);
        this.enemySpawnProbability = Config.Level.BASE_ENEMY_SPAWN_PROBABILITY + (levelNumber * 0.01);
        this.advancedEnemyProbability = levelNumber * 0.0090;
        this.playerInitialHealth = Config.Player.DEFAULT_HEALTH;
        this.heartSpawnProbability = levelNumber * 0.0001;
        this.freezeSpawnProbability = levelNumber * 0.0003;
        this.multishotSpawnProbability = levelNumber * 0.00001;
        this.powerupLimit = levelNumber - 1;
    }

    /**
     * Gets the total number of enemies in the level.
     *
     * @return the total number of enemies
     */
    public int getTotalEnemies() {
        return totalEnemies;
    }

    /**
     * Gets the number of kills required to advance to the next level.
     *
     * @return the number of kills required
     */
    public int getKillsToAdvance() {
        return killsToAdvance;
    }

    /**
     * Gets the probability of spawning an enemy.
     *
     * @return the enemy spawn probability
     */
    public double getEnemySpawnProbability() {
        return enemySpawnProbability;
    }

    /**
     * Gets the probability of spawning an advanced enemy.
     *
     * @return the advanced enemy spawn probability
     */
    public double getAdvancedEnemyProbability() {
        return advancedEnemyProbability;
    }

    /**
     * Gets the maximum number of power-ups that can be spawned in the level.
     *
     * @return the power-up limit
     */
    public int getPowerupLimit() {
        return powerupLimit;
    }

    /**
     * Gets the probability of spawning a freeze power-up.
     *
     * @return the freeze spawn probability
     */
    public double getFreezeSpawnProbability() {
        return freezeSpawnProbability;
    }

    /**
     * Gets the probability of spawning a multishot power-up.
     *
     * @return the multishot spawn probability
     */
    public double getMultishotSpawnProbability() {
        return multishotSpawnProbability;
    }

    /**
     * Gets the probability of spawning a heart power-up.
     *
     * @return the heart spawn probability
     */
    public double getHeartSpawnProbability() {
        return heartSpawnProbability;
    }

    /**
     * Gets the initial health of the player.
     *
     * @return the player's initial health
     */
    public int getPlayerInitialHealth() {
        return playerInitialHealth;
    }
}