package com.example.demo.Objects.Planes;

import com.example.demo.config.Config;

public class BossConfiguration {
    /**
     * Responsible for holding the configuration values specific to the boss in the game.
     * It adjusts its properties based on the level of the game, providing different attributes for each boss.
     */
    private final int maxHealth;
    private final double fireRate;
    private final double shieldProbability;
    private final double missileProbability;
    private final int moveFrequency;
    private final int level;
    private final int missilesLimit;
    private final int verticalVelocity;
    /**
     * Constructor that initializes the boss configuration based on the level.
     *
     * @param level The level of the game, which determines the strength and behavior of the boss.
     */
    public BossConfiguration(int level) {
        this.level = level;
        this.maxHealth = (level * 3);
        this.fireRate = Config.Boss.BASE_FIRE_RATE + (level * 0.0001);
        this.shieldProbability = Config.Boss.BASE_SHIELD_PROBABILITY + (level * 0.0005);
        this.moveFrequency = Config.Boss.BASE_MOVE_FREQUENCY + level;
        this.missileProbability = Config.Boss.BASE_MISSILE_PROBABILITY + (level * 0.0005);
        this.missilesLimit = (level == 9) ? Config.Boss.MISSILE_LIMIT : (level/3);
        this.verticalVelocity = Config.Boss.BASE_VELOCITY + level/3;
    }

    /**
     * Gets the maximum health of the boss.
     *
     * @return The maximum health of the boss, which is based on the level.
     */
    public int getMaxHealth() { return maxHealth; }

    /**
     * Gets the fire rate of the boss.
     *
     * @return The fire rate, which determines how fast the boss fires projectiles.
     */
    public double getFireRate() { return fireRate; }

    /**
     * Gets the probability that the boss will activate its shield.
     *
     * @return The probability of the shield activation.
     */
    public double getShieldProbability() { return shieldProbability; }

    /**
     * Gets the frequency at which the boss moves.
     *
     * @return The move frequency, which increases with each level.
     */
    public int getMoveFrequency() { return moveFrequency; }

    /**
     * Gets the probability that the boss will fire missiles.
     *
     * @return The missile firing probability, which increases with the level.
     */
    public double getMissileProbability() { return missileProbability; }

    /**
     * Gets the current level of the game.
     *
     * @return The level at which the boss is currently configured.
     */
    public int getLevel() { return level; }

    /**
     * Gets the vertical velocity of the boss.
     *
     * @return The vertical velocity, which increases with the level.
     */
    public double getVelocity() { return verticalVelocity; }

    /**
     * Gets the missile limit for the boss.
     *
     * @return The missile limit, which is capped at a higher value for level 9.
     */
    public int getMissilesLimit() { return missilesLimit; }

}
