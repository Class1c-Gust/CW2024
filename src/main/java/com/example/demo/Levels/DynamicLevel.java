package com.example.demo.Levels;

import com.example.demo.GameObject;

public class DynamicLevel extends LevelParent {
    private final LevelConfiguration config;
    private final int levelNumber;

    public DynamicLevel(double screenHeight, double screenWidth, int levelNumber) {
        super("/com/example/demo/images/background" + (levelNumber) + ".jpg",
                screenHeight,
                screenWidth,
                5, // initial health
                2, // powerup limit
                0.002 + (levelNumber * 0.0001), // heart spawn probability increases slightly
                0.02 + (levelNumber * 0.001)); // freeze spawn probability increases

        this.levelNumber = levelNumber;
        this.config = new LevelConfiguration(levelNumber);
    }

    @Override
    protected void checkIfGameOver() {
        if (userIsDestroyed()) {
            loseGame();
        } else if (getUser().getNumberOfKills() >= config.getKillsToAdvance()) {
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
            if (Math.random() < config.getEnemySpawnProbability()) {
                double newEnemyInitialYPosition = Math.random() * getEnemyMaximumYPosition();
                GameObject newEnemy = getEnemyFactory().createEntity(getScreenWidth(), newEnemyInitialYPosition);
                addEnemyUnit(newEnemy);
            }
        }
    }

    @Override
    protected LevelView instantiateLevelView() {
        return new LevelView(getRoot(), 5);
    }
}



