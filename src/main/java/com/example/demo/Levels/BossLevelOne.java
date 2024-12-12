package com.example.demo.Levels;

import com.example.demo.Planes.Boss;
import com.example.demo.Planes.BossFactory;
import com.example.demo.Managers.SoundManager;

/**
 * Class representing the first boss level
 */
public class BossLevelOne extends LevelParent {

	private static final String BACKGROUND_IMAGE_NAME = "/com/example/demo/images/background2.jpg";
	private static final int PLAYER_INITIAL_HEALTH = 5;
	private final Boss boss;
	private LevelViewBossOne levelView;
	private final int levelNumber;
	private final SoundManager soundManager;
	private String musicFile;

	public BossLevelOne(double screenHeight, double screenWidth, int levelNumber) {
		super(screenHeight, screenWidth, levelNumber);
		boss = BossFactory.createBoss(levelNumber, levelView);
		this.levelNumber = levelNumber;
		this.soundManager = SoundManager.getInstance();
		this.musicFile = soundManager.getLevelMusic(levelNumber);
		playLevelMusic(musicFile);
	}

	@Override
	protected void initializeFriendlyUnits() {
		getRoot().getChildren().add(getUser());
	}

	@Override
	protected void checkIfGameOver() {
		if (userIsDestroyed()) {
			soundManager.stopBackgroundMusic();
			loseGame();
		}
		else if (boss.isDestroyed()) {
			soundManager.stopBackgroundMusic();
			if (levelNumber == 9){
				winGame();
			}
			else{
				soundManager.playLevelUpSound();
				goToNextLevel("LEVEL_" + (levelNumber+1));
			}
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
