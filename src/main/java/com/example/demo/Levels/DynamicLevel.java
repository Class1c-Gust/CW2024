package com.example.demo.Levels;

import com.example.demo.Objects.GameObject;
import com.example.demo.Managers.SoundManager;
import com.example.demo.config.Config;

/**
 * Represents a dynamic level in the game.
 * This class extends LevelParent and provides functionality to handle
 * enemy spawning, user interactions, and level transitions based on a dynamic configuration.
 */
public class DynamicLevel extends LevelParent {
    private final LevelConfiguration config;
    private final int levelNumber;
    private final SoundManager soundManager;

    /**
     * Constructs a DynamicLevel instance.
     *
     * @param screenHeight the height of the game screen
     * @param screenWidth  the width of the game screen
     * @param levelNumber  the current level number
     */
    public DynamicLevel(double screenHeight, double screenWidth, int levelNumber) {
        super(screenHeight, screenWidth, levelNumber);
        this.levelNumber = levelNumber;
        this.config = new LevelConfiguration(levelNumber);
        this.soundManager = SoundManager.getInstance();
        String musicFile = soundManager.getLevelMusic(levelNumber);

        playLevelMusic(musicFile);
    }

    /**
     * Checks the game state to determine if the game is over.
     * Ends the game if the user is destroyed or transitions to the next level
     * when the required number of kills is reached.
     */
    @Override
    protected void checkIfGameOver() {
        if (userIsDestroyed()) {
            // Stop music and end the game if the user is destroyed
            soundManager.stopBackgroundMusic();
            loseGame();
        } else if (getUser().getNumberOfKills() >= config.getKillsToAdvance()) {
            // Play level-up sound and transition to the next level when kill count is met
            soundManager.playLevelUpSound();
            soundManager.stopBackgroundMusic();
            goToNextLevel("LEVEL_" + (levelNumber + 1));
        }
    }

    /**
     * Initializes friendly units in the level.
     * Adds the user-controlled unit to the scene graph.
     */
    @Override
    protected void initializeFriendlyUnits() {
        getRoot().getChildren().add(getUser());
    }

    /**
     * Spawns enemy units in the level.
     * Uses a probability-based approach to dynamically generate enemies
     * while adhering to level configuration parameters.
     */
    @Override
    protected void spawnEnemyUnits() {
        int currentNumberOfEnemies = getCurrentNumberOfEnemies();

        // Spawn enemies until the total count matches the configured limit
        for (int i = 0; i < config.getTotalEnemies() - currentNumberOfEnemies; i++) {
            if (Math.random() < config.getEnemySpawnProbability() * (1 + levelNumber * 0.1)) {
                // Randomly generate Y-position and create a new enemy
                double newEnemyInitialYPosition = Math.random() * getEnemyMaximumYPosition();
                GameObject newEnemy = getEnemyFactory().createEntity(
                        getScreenWidth(),
                        newEnemyInitialYPosition,
                        config.getAdvancedEnemyProbability()
                );
                addEnemyUnit(newEnemy);
            }
        }
    }

    /**
     * Instantiates the level view for this dynamic level.
     *
     * @return the heart bar representing the player's health
     */
    @Override
    protected HeartBar instantiateLevelView() {
        // Create a heart bar with a default health value of 5
        return new HeartBar(getRoot(), Config.Player.DEFAULT_HEALTH);
    }

    /**
     * Getter function for current level config
     * @return the level config
     */
    public LevelConfiguration getConfig(){
        return config;
    }
}
