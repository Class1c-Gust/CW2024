package com.example.demo.Planes;

import com.example.demo.GameObject;

import java.util.List;

public class BossConfiguration {
    private final int maxHealth;
    private final double fireRate;
    private final double shieldProbability;
    private final double missileProbability;
    private final int moveFrequency;
    private final double projectileSpeed;
    private final int level;
    private final int missilesLimit;
    private static final double frameDelay = 0.4;
    private final int verticalVelocity;

    public BossConfiguration(int level) {
        this.level = level;
        this.maxHealth = 1 + (level * 2);
        this.fireRate = 0.001 + (level * 0.0001);
        this.shieldProbability = 0.002 + (level * 0.0005);
        this.moveFrequency = 5 + level;
        this.projectileSpeed = 5 + (level * 0.5);
        this.missileProbability = 0.002 + (level * 0.0005);
        this.missilesLimit = (level == 9) ? 100 : (level/3);
        this.verticalVelocity = (int)(8*frameDelay) + level/3;
    }

    public int getMaxHealth() { return maxHealth; }
    public double getFireRate() { return fireRate; }
    public double getShieldProbability() { return shieldProbability; }
    public int getMoveFrequency() { return moveFrequency; }
    public double getProjectileSpeed() { return projectileSpeed; }
    public double getMissileProbability(){return missileProbability;}
    public int getLevel() { return level; }
    public double getVelocity(){return verticalVelocity;}
    public int getMissilesLimit() { return missilesLimit; }
}
