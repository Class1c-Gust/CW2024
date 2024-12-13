package com.example.demo.Factories;

import com.example.demo.Objects.Planes.Boss;
import com.example.demo.Objects.Planes.BossConfiguration;

/**
 * Produces concrete Boss products for various boss creation using factory pattern method
 */
public class BossFactory {
    /**
     * Creates an instance of a boss
     * @param level the level number
     * @return the boss instance
     */
    public static Boss createBoss(int level) {
        BossConfiguration config = new BossConfiguration(level);
        return new Boss(config, level);
    }
}
