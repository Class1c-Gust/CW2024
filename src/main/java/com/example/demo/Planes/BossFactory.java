package com.example.demo.Planes;

import com.example.demo.Levels.LevelViewBossOne;

public class BossFactory {
    private static final double INITIAL_X_POSITION = 1000.0;
    private static final double INITIAL_Y_POSITION = 400;
    
    public static Boss createBoss(int level, LevelViewBossOne levelView) {
        BossConfiguration config = new BossConfiguration(level);
        return new Boss(config, levelView, level);
    }
}
