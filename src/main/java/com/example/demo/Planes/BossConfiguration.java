package com.example.demo.Planes;

import com.example.demo.GameObject;
import com.example.demo.config.Config;

import java.util.List;

public class BossConfiguration {
    private final int maxHealth;
    private final double fireRate;
    private final double shieldProbability;
    private final double missileProbability;
    private final int moveFrequency;
    private final int level;
    private final int missilesLimit;
    private final int verticalVelocity;

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

    public int getMaxHealth() { return maxHealth; }
    public double getFireRate() { return fireRate; }
    public double getShieldProbability() { return shieldProbability; }
    public int getMoveFrequency() { return moveFrequency; }
    public double getMissileProbability(){return missileProbability;}
    public int getLevel() { return level; }
    public double getVelocity(){return verticalVelocity;}
    public int getMissilesLimit() { return missilesLimit; }
}
