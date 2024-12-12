package com.example.demo.Levels;

import com.example.demo.Planes.Boss;
import com.example.demo.Planes.BossFactory;
import com.example.demo.Managers.SoundManager;
import com.example.demo.config.Config;

/**
 * Class representing the first boss level
 */
public class BossLevelOne extends LevelParent {

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
				System.out.println("ass");
				soundManager.stopBackgroundMusic();
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
	protected HeartBar instantiateLevelView() {
		levelView = new LevelViewBossOne(getRoot(), Config.Player.DEFAULT_HEALTH);
		return levelView;
	}
	@Override
	protected void updateScene(){
		super.updateScene();
		levelView.updateBossHealth(boss.getHealth(), boss.getMaxHealth());
	}

}
