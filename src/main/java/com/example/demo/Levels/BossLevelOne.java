package com.example.demo.Levels;

import com.example.demo.Planes.Boss;
import com.example.demo.Planes.BossFactory;

/**
 * Class representing the first boss level
 */
public class BossLevelOne extends LevelParent {

	private static final String BACKGROUND_IMAGE_NAME = "/com/example/demo/images/background2.jpg";
	private static final int PLAYER_INITIAL_HEALTH = 5;
	private static final double HEART_SPAWN_PROBABILITY = .002;
	private static final double FREEZE_SPAWN_PROBABILITY = .002;
	private static final int HEART_SPAWN_LIMIT = 3;
	private final Boss boss;
	private LevelViewBossOne levelView;
	private final int levelNumber;
	public BossLevelOne(double screenHeight, double screenWidth, int levelNumber) {
		super(BACKGROUND_IMAGE_NAME, screenHeight, screenWidth, PLAYER_INITIAL_HEALTH, HEART_SPAWN_LIMIT, HEART_SPAWN_PROBABILITY, FREEZE_SPAWN_PROBABILITY);
		boss = BossFactory.createBoss(levelNumber, levelView);
		this.levelNumber = levelNumber;
	}

	@Override
	protected void initializeFriendlyUnits() {
		getRoot().getChildren().add(getUser());
	}

	@Override
	protected void checkIfGameOver() {
		if (userIsDestroyed()) {
			loseGame();
		}
		else if (boss.isDestroyed()) {
			goToNextLevel("LEVEL_" + (levelNumber+1));
		}
	}

	@Override
	protected void spawnEnemyUnits() {
		if (getCurrentNumberOfEnemies() == 0) {
			addEnemyUnit(boss);
		}
	}

	@Override
	protected LevelView instantiateLevelView() {
		levelView = new LevelViewBossOne(getRoot(), PLAYER_INITIAL_HEALTH);
		return levelView;
	}
	@Override
	protected void updateScene(){
		super.updateScene();
		levelView.updateBossHealth(boss.getHealth(), boss.getMaxHealth());
	}

}
