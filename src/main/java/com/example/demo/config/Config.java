package com.example.demo.config;

/**
 * This class contains all the configuration constants used throughout the game.
 * It is a final class to prevent instantiation, as it solely holds static configuration values.
 */
public final class Config {

    // Private constructor to prevent instantiation
    private Config() {}

    /**
     * Screen-related constants such as window size, title, and other screen configuration.
     */
    public static final class Screen {
        public static final int WIDTH = 1300;
        public static final int HEIGHT = 750;
        public static final String TITLE = "Sky Battle";
        public static final double SCREEN_HEIGHT_ADJUSTMENT = 150;
        public static final double MILLISECOND_DELAY = 16.67;
        public static final double BUTTON_WIDTH = 200;
        public static final double BUTTON_HEIGHT = 50;
    }

    /**
     * Audio-related constants, including file paths for background music, sound effects, and volume settings.
     */
    public static final class Audio {
        public static final String MENU_MUSIC = "/Audio/menu.mp3";
        public static final String LEVEL_ONE_MUSIC = "/Audio/level1music.mp3";
        public static final String LEVEL_TWO_MUSIC = "/Audio/level2music.mp3";
        public static final String LEVEL_THREE_MUSIC = "/Audio/level3music.mp3";
        public static final String LEVEL_FOUR_MUSIC = "/Audio/level4music.mp3";
        public static final String LEVEL_FIVE_MUSIC = "/Audio/level5music.mp3";
        public static final String LEVEL_SIX_MUSIC = "/Audio/level6music.mp3";
        public static final String LEVEL_SEVEN_MUSIC = "/Audio/level7music.mp3";
        public static final String LEVEL_EIGHT_MUSIC = "/Audio/level8music.mp3";
        public static final String LEVEL_NINE_MUSIC = "/Audio/level9music.mp3";
        public static final String GAME_OVER_MUSIC = "/Audio/gameOver.mp3";
        public static final String SHOOT_SOUND = "/Audio/gunshot.mp3";
        public static final String BOSS_SHOOT_SOUND = "/Audio/bossshoot.mp3";
        public static final String CRASH_SOUND = "/Audio/healthDecrease.mp3";
        public static final String LEVEL_UP = "/Audio/levelup.mp3";
        public static final String MISSILE_FIRE = "/Audio/missilefire.mp3";
        public static final String POWER_UP = "/Audio/powerup.mp3";
        public static final double VOLUME = 0.2;
        public static final String[] LEVEL_MUSIC = {
                LEVEL_ONE_MUSIC, LEVEL_TWO_MUSIC, LEVEL_THREE_MUSIC, LEVEL_FOUR_MUSIC, LEVEL_FIVE_MUSIC,
                LEVEL_SIX_MUSIC, LEVEL_SEVEN_MUSIC, LEVEL_EIGHT_MUSIC, LEVEL_NINE_MUSIC
        };
    }

    /**
     * Game-related constants such as frame delay, image locations, and backgrounds.
     */
    public static final class Game {
        public static final double FRAME_DELAY = 0.4;
        public static final String IMAGE_LOCATION = "/com/example/demo/images/";
        public static final String MENU_BACKGROUND_IMAGE = "/com/example/demo/images/background1.jpg";
        public static final String WIN_BACKGROUND = "/com/example/demo/images/background1.jpg";
        public static final String LOSE_BACKGROUND = "/com/example/demo/images/background1.jpg";
    }

    /**
     * Player-related constants including player image, starting positions, velocities, and health.
     */
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

    /**
     * Enemy-related constants, including image paths, health, and projectile settings.
     */
    public static final class Enemy {
        public static final String NORMAL_IMAGE = "enemyplane.png";
        public static final String FAST_IMAGE = "enemyplane2.png";
        public static final int IMAGE_HEIGHT = 150;
        public static final double PROJECTILE_X_POSITION_OFFSET = -100.0;
        public static final double PROJECTILE_Y_POSITION_OFFSET = 50.0;
        public static final int INITIAL_HEALTH = 1;
        public static final int NORMAL_SPEED = -6;
        public static final int FAST_SPEED = -10;
        public static final double NORMAL_FIRE_RATE = 0.001;
        public static final double FAST_FIRE_RATE = 0.003;
    }

    /**
     * Boss-related constants, including position, fire rate, shield probability, and missile settings.
     */
    public static final class Boss {
        public static final double INITIAL_X_POSITION = 1000.0;
        public static final double INITIAL_Y_POSITION = 400.0;
        public static final double BASE_FIRE_RATE = 0.001;
        public static final double BASE_SHIELD_PROBABILITY = 0.002;
        public static final double BASE_MISSILE_PROBABILITY = 0.002;
        public static int MISSILE_LIMIT = 100;
        public static int BASE_MOVE_FREQUENCY = 5;
        public static final int BASE_VELOCITY = (int)(8 * Game.FRAME_DELAY);
        public static final double PROJECTILE_Y_POSITION_OFFSET = 75.0;
        public static final int IMAGE_HEIGHT = 200;
        public static final int MAX_CONSECUTIVE_MOVES_SAME_DIRECTION = 10;
        public static final int MAX_SHIELD_FRAMES = 500;
    }

    /**
     * Projectile-related constants, including image names, velocities, and initial positions for different projectiles.
     */
    public static final class Projectile {
        public static final String ENEMY_PROJECTILE_IMAGE = "enemyFire.png";
        public static final String USER_PROJECTILE_IMAGE = "userfire.png";
        public static final String BOSS_PROJECTILE_IMAGE = "fireball.png";
        public static final String BOSS_MISSILE_IMAGE = "missile.png";
        public static final int IMAGE_HEIGHT = 50;
        public static final int USER_PROJECTILE_IMAGE_HEIGHT = 75;
        public static final int ENEMY_PROJECTILE_HORIZONTAL_VELOCITY = (int)(-8 * Game.FRAME_DELAY);
        public static final int USER_PROJECTILE_HORIZONTAL_VELOCITY = (int)(15 * Game.FRAME_DELAY);
        public static final int BOSS_PROJECTILE_HORIZONTAL_VELOCITY = (int)(-15 * Game.FRAME_DELAY);
        public static final int BOSS_MISSILE_HORIZONTAL_VELOCITY = (int)(-25 * Game.FRAME_DELAY);
        public static final int INITIAL_X_POSITION = 950;
    }

    /**
     * Powerup-related constants, including powerup images, velocity, and duration.
     */
    public static final class Powerup {
        public static final int MULTISHOT_DURATION = 10;
        public static final String FREEZE_IMAGE_NAME = "freeze.png";
        public static final String HEART_IMAGE_NAME = "heart.png";
        public static final String MULTISHOT_IMAGE_NAME = "triplefire.png";
        public static final int IMAGE_HEIGHT = 50;
        public static final int HORIZONTAL_VELOCITY = (int)(-10 * Game.FRAME_DELAY);
        public static final int INITIAL_X_POSITION = 1300;
    }

    /**
     * Level-related constants, including names, enemy counts, and health bar positions.
     */
    public static final class Level {
        public static final String LEVEL_ONE_CLASS_NAME = "LEVEL_1";
        public static final String BACKGROUND_PATH = "/com/example/demo/images/background";
        public static final int BASE_ENEMY_COUNT = 1;
        public static final int BASE_KILLS_TO_ADVANCE = 12;
        public static final double BASE_ENEMY_SPAWN_PROBABILITY = 0.12;
        public static final double HEART_DISPLAY_X_POSITION = 5;
        public static final double HEART_DISPLAY_Y_POSITION = 25;
        public static final int HEALTH_BAR_WIDTH = 350;
        public static final int HEALTH_BAR_HEIGHT = 35;
        public static final int HEALTH_BAR_X_POSITION = 900;
        public static final int HEALTH_BAR_Y_POSITION = 20;
    }
}
