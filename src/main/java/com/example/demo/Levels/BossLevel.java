package com.example.demo.Levels;

import com.example.demo.Objects.Planes.Boss;
import com.example.demo.Objects.Planes.BossFactory;
import com.example.demo.Managers.SoundManager;
import com.example.demo.config.Config;

/**
 * Represents the boss levels in the game.
 * This class extends LevelParent and includes logic for managing the boss,
 * user interactions, and level transitions.
 */
public class BossLevel extends LevelParent {

	private final Boss boss;
	private LevelViewBoss levelView;
	private final int levelNumber;
	private final SoundManager soundManager;

    /**
	 * Constructs a BossLevel instance.
	 *
	 * @param screenHeight the height of the game screen
	 * @param screenWidth  the width of the game screen
	 * @param levelNumber  the current level number
	 */
	public BossLevel(double screenHeight, double screenWidth, int levelNumber) {
		super(screenHeight, screenWidth, levelNumber);
		// Initialize boss using the factory pattern
		boss = BossFactory.createBoss(levelNumber);
		this.levelNumber = levelNumber;
		this.soundManager = SoundManager.getInstance();
        String musicFile = soundManager.getLevelMusic(levelNumber);
		playLevelMusic(musicFile);
	}

	/**
	 * Initializes friendly units in the level.
	 * Adds the user-controlled unit to the scene.
	 */
	@Override
	protected void initializeFriendlyUnits() {
		getRoot().getChildren().add(getUser());
	}

	/**
	 * Checks the game state to determine if the game is over.
	 * Ends the game if the user is destroyed or transitions to the next level
	 * if the boss is defeated.
	 */
	@Override
	protected void checkIfGameOver() {
		if (userIsDestroyed()) {
			// Stop music and end the game if the user is destroyed
			soundManager.stopBackgroundMusic();
			loseGame();
		} else if (boss.isDestroyed()) {
			// Stop music and handle level transitions if the boss is defeated
			if (levelNumber == 9) { // if boss level
				winGame();
			} else {
				// Play level-up sound and transition to the next level
				soundManager.playLevelUpSound();
				goToNextLevel("LEVEL_" + (levelNumber + 1));
			}
		}
	}

	/**
	 * Spawns enemy units in the level.
	 * Ensures the boss is added to the scene when no enemies are present.
	 */
	@Override
	protected void spawnEnemyUnits() {
		if (getCurrentNumberOfEnemies() == 0) {
			addEnemyUnit(boss);
		}
	}

	/**
	 * Instantiates the level view for the boss level.
	 *
	 * @return the heart bar representing the player's health
	 */
	@Override
	protected HeartBar instantiateLevelView() {
		// Create a new view for the boss level
		levelView = new LevelViewBoss(getRoot(), Config.Player.DEFAULT_HEALTH);
		return levelView;
	}

	/**
	 * Updates the game scene.
	 * Includes logic for updating the boss health bar in the level view.
	 */
	@Override
	protected void updateScene() {
		// Call the superclass method to handle generic updates
		super.updateScene();

		// Update the health bar for the boss
		levelView.updateBossHealth(boss.getHealth(), boss.getMaxHealth());
	}
}
