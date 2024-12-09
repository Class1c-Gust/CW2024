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

    public BossConfiguration(int level) {
        this.level = level;
        this.maxHealth = 10 + (level * 2);
        this.fireRate = 0.009 + (level * 0.01);
        this.shieldProbability = 0.002 + (level * 0.0005);
        this.moveFrequency = 5 + level;
        this.projectileSpeed = 5 + (level * 0.5);
        this.missileProbability = 0.002 + (level * 0.0005);
        this.missilesLimit = (level/3) + 1;
    }

    public int getMaxHealth() { return maxHealth; }
    public double getFireRate() { return fireRate; }
    public double getShieldProbability() { return shieldProbability; }
    public int getMoveFrequency() { return moveFrequency; }
    public double getProjectileSpeed() { return projectileSpeed; }
    public double getMissileProbability(){return missileProbability;}
    public int getLevel() { return level; }
    public int getMissilesLimit() { return missilesLimit; }
}
