package com.example.demo.Levels;

import com.example.demo.GameObject;
import com.example.demo.Managers.SoundManager;

public class DynamicLevel extends LevelParent {
    private final LevelConfiguration config;
    private final int levelNumber;
    private final SoundManager soundManager;
    private String musicFile;
    public DynamicLevel(double screenHeight, double screenWidth, int levelNumber) {
        super(screenHeight, screenWidth, levelNumber);
        this.levelNumber = levelNumber;
        this.config = new LevelConfiguration(levelNumber);
        this.soundManager = SoundManager.getInstance();
        this.musicFile = soundManager.getLevelMusic(levelNumber);
        playLevelMusic(musicFile);
    }

    @Override
    protected void checkIfGameOver() {
        if (userIsDestroyed()) {
            soundManager.stopBackgroundMusic();
            loseGame();
        } else if (getUser().getNumberOfKills() >= config.getKillsToAdvance()) {
//            soundManager.stopBackgroundMusic();
            soundManager.playLevelUpSound();
            soundManager.stopBackgroundMusic();
            goToNextLevel("LEVEL_" + (levelNumber + 1));
        }
    }

    @Override
    protected void initializeFriendlyUnits() {
        getRoot().getChildren().add(getUser());
    }

    @Override
    protected void spawnEnemyUnits() {
        int currentNumberOfEnemies = getCurrentNumberOfEnemies();
        for (int i = 0; i < config.getTotalEnemies() - currentNumberOfEnemies; i++) {
            if (Math.random() < config.getEnemySpawnProbability() * (1 + levelNumber * 0.1)) {
                double newEnemyInitialYPosition = Math.random() * getEnemyMaximumYPosition();
                GameObject newEnemy = getEnemyFactory().createEntity(getScreenWidth(), newEnemyInitialYPosition, config.getAdvancedEnemyProbability());
                addEnemyUnit(newEnemy);
            }
        }
    }

    @Override
    protected LevelView instantiateLevelView() {
        return new LevelView(getRoot(), 5);
    }
}
