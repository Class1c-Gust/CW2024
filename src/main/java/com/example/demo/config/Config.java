package com.example.demo.config;

import javafx.scene.paint.Color;

public final class Config {
    private Config() {} // Prevent instantiation

    public static final class Screen {
        public static final int WIDTH = 1300;
        public static final int HEIGHT = 750;
        public static final String TITLE = "Sky Battle";
        public static final double HEIGHT_ADJUSTMENT = 150;
    }

    public static final class Game {
        public static final double FRAME_DELAY = 0.4;
        public static final double MILLISECOND_DELAY = 16.67;
    }

    public static final class Player {
        public static final String IMAGE_NAME = "userplane.png";
        public static final int IMAGE_HEIGHT = 150;
        public static final double INITIAL_X_POSITION = 5.0;
        public static final double INITIAL_Y_POSITION = 300.0;
        public static final double Y_UPPER_BOUND = -40;
        public static final double Y_LOWER_BOUND = 600.0;
        public static final double X_LEFT_BOUND = 0.0;
        public static final double X_RIGHT_BOUND = 900.0;
        public static final int VERTICAL_VELOCITY = (int)(8 * Game.FRAME_DELAY);
        public static final int HORIZONTAL_VELOCITY = (int)(8 * Game.FRAME_DELAY);
        public static final int PROJECTILE_Y_POSITION_OFFSET = 20;
        public static final long PROJECTILE_COOLDOWN = 200;
        public static final int DEFAULT_HEALTH = 5;
    }

    public static final class Enemy {
        public static final String NORMAL_IMAGE = "enemyplane.png";
        public static final String FAST_IMAGE = "enemyplane2.png";
        public static final int IMAGE_HEIGHT = 150;
        public static final int NORMAL_SPEED = -6;
        public static final int FAST_SPEED = -9;
        public static final double NORMAL_FIRE_RATE = 0.001;
        public static final double FAST_FIRE_RATE = 0.003;
        public static final int DEFAULT_HEALTH = 1;
    }

    public static final class Boss {
        public static final double INITIAL_X_POSITION = 1000.0;
        public static final double INITIAL_Y_POSITION = 400.0;
        public static final double BASE_FIRE_RATE = 0.009;
        public static final double BASE_SHIELD_PROBABILITY = 0.002;
        public static final double BASE_MISSILE_PROBABILITY = 0.002;
    }

    public static final class Projectile {
        public static final String ENEMY_PROJECTILE_IMAGE = "enemyFire.png";
        public static final String USER_PROJECTILE_IMAGE = "userfire.png";
        public static final int IMAGE_HEIGHT = 50;
        public static final int ENEMY_HORIZONTAL_VELOCITY = (int)(-8 * Game.FRAME_DELAY);
        public static final int USER_HORIZONTAL_VELOCITY = (int)(8 * Game.FRAME_DELAY);
    }

    public static final class Powerup {
        public static final double BASE_HEART_SPAWN_PROBABILITY = 0.002;
        public static final double BASE_FREEZE_SPAWN_PROBABILITY = 0.02;
        public static final double BASE_MULTISHOT_SPAWN_PROBABILITY = 0.001;
        public static final int DEFAULT_POWERUP_LIMIT = 2;
        public static final int MULTISHOT_DURATION = 10; // seconds
    }

    public static final class Level {
        public static final String BACKGROUND_PATH = "/com/example/demo/images/background";
        public static final int BASE_ENEMY_COUNT = 5;
        public static final int BASE_KILLS_TO_ADVANCE = 10;
        public static final double BASE_ENEMY_SPAWN_PROBABILITY = 0.20;
        public static final int BOSS_LEVEL_INTERVAL = 3;
    }
}
