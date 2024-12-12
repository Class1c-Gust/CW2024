package com.example.demo.Planes;

import com.example.demo.Levels.LevelViewBossOne;

public class BossFactory {
    public static Boss createBoss(int level, LevelViewBossOne levelView) {
        BossConfiguration config = new BossConfiguration(level);
        return new Boss(config, levelView, level);
    }
}
