package com.example.demo.Planes;

import com.example.demo.GameObject;
import com.example.demo.Levels.LevelViewBossOne;
import com.example.demo.Projectiles.BossProjectile;

import java.util.*;

public class Boss extends FighterPlane {

    private static final double INITIAL_X_POSITION = 1000.0;
    private static final double INITIAL_Y_POSITION = 400;
    private static final double PROJECTILE_Y_POSITION_OFFSET = 75.0;
    private static final int IMAGE_HEIGHT = 200;
    private static final double frameDelay = 0.4;
    private static final int VERTICAL_VELOCITY = (int)(8*frameDelay);
    private final BossConfiguration config;
    private final LevelViewBossOne levelview;
    private final List<Integer> movePattern;
    private boolean isShielded;
    private int consecutiveMovesInSameDirection;
    private int indexOfCurrentMove;
    private int framesWithShieldActivated;
    private int currentHealth;
    private double currentPosition;

    public Boss(BossConfiguration config, LevelViewBossOne p_levelview, int levelNumber) {
        super(("bossplane" + (levelNumber / 2) + ".png"), IMAGE_HEIGHT, INITIAL_X_POSITION, INITIAL_Y_POSITION, config.getMaxHealth());
        this.setFitWidth(300);
        this.setFitHeight(200);
        this.config = config;
        this.levelview = p_levelview;
        movePattern = new ArrayList<>();
        consecutiveMovesInSameDirection = 0;
        indexOfCurrentMove = 0;
        framesWithShieldActivated = 0;
        isShielded = false;
        this.currentHealth = config.getMaxHealth();
        initializeMovePattern();
    }

    @Override
    public void updatePosition() {
        double initialTranslateY = getTranslateY();
        moveVertically(getNextMove());
        currentPosition = getLayoutY() + getTranslateY();
        if (currentPosition < -100 || currentPosition > 475) {
            setTranslateY(initialTranslateY);
        }
    }
    
    @Override
    public void updateActor() {
        updatePosition();
        updateShield(currentPosition);
    }

    @Override
    public GameObject fireProjectile() {
        return bossFiresInCurrentFrame() ? new BossProjectile(getProjectileInitialPosition()) : null;
    }
    
    @Override
    public void takeDamage() {
        if (!isShielded) {
            currentHealth--;
            super.takeDamage();
        }
    }

    private void initializeMovePattern() {
        for (int i = 0; i < config.getMoveFrequency(); i++) {
            movePattern.add(VERTICAL_VELOCITY);
            movePattern.add(-VERTICAL_VELOCITY);
            movePattern.add(0);
        }
        Collections.shuffle(movePattern);
    }

    private void updateShield(double y) {
        if (isShielded) framesWithShieldActivated++;
        else if (shieldShouldBeActivated()) activateShield();    
        if (shieldExhausted()) deactivateShield();
        levelview.updateShield(y);
    }

    private int getNextMove() {
        int currentMove = movePattern.get(indexOfCurrentMove);
        consecutiveMovesInSameDirection++;
        if (consecutiveMovesInSameDirection == 10) {
            Collections.shuffle(movePattern);
            consecutiveMovesInSameDirection = 0;
            indexOfCurrentMove++;
        }
        if (indexOfCurrentMove == movePattern.size()) {
            indexOfCurrentMove = 0;
        }
        return currentMove;
    }

    private boolean bossFiresInCurrentFrame() {
        return Math.random() < config.getFireRate();
    }

    private double getProjectileInitialPosition() {
        return getLayoutY() + getTranslateY() + PROJECTILE_Y_POSITION_OFFSET;
    }

    private boolean shieldShouldBeActivated() {
        return Math.random() < config.getShieldProbability();
    }

    private boolean shieldExhausted() {
        return framesWithShieldActivated == 500;
    }

    private void activateShield() {
        isShielded = true;
        levelview.showShield();
    }

    private void deactivateShield() {
        isShielded = false;
        levelview.hideShield();
        framesWithShieldActivated = 0;
    }
    
    public int getHealth(){
        return currentHealth;
    }
    
    public int getMaxHealth(){
        return config.getMaxHealth();
    }
}
