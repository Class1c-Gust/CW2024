package com.example.demo.Planes;

import com.example.demo.GameObject;
import com.example.demo.Levels.LevelViewBossOne;
import com.example.demo.Projectiles.BossMissile;
import com.example.demo.Projectiles.BossProjectile;
import javafx.scene.effect.DropShadow;
import javafx.scene.paint.Color;

import java.util.*;

public class Boss extends FighterPlane {

    private static final double INITIAL_X_POSITION = 1000.0;
    private static final double INITIAL_Y_POSITION = 400;
    private static final double PROJECTILE_Y_POSITION_OFFSET = 75.0;
    private static final int IMAGE_HEIGHT = 200;
    private final BossConfiguration config;
    private final LevelViewBossOne levelview;
    private final List<Integer> movePattern;
    private boolean isShielded;
    private int consecutiveMovesInSameDirection;
    private int indexOfCurrentMove;
    private int framesWithShieldActivated;
    private int currentHealth;
    private double currentPosition;
    private boolean hasFiredMissile;
    private int missilesFired;
    private final DropShadow shieldGlowEffect;

    public Boss(BossConfiguration config, LevelViewBossOne p_levelview, int levelNumber) {
        super(("bossplane" + (levelNumber / 3) + ".png"), IMAGE_HEIGHT, INITIAL_X_POSITION, INITIAL_Y_POSITION, config.getMaxHealth());
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
        this.hasFiredMissile = false;
        this.missilesFired = 0;

        shieldGlowEffect = new DropShadow();
        shieldGlowEffect.setColor(Color.RED); // Shield emits a red glow
        shieldGlowEffect.setRadius(30);
        shieldGlowEffect.setSpread(0.5);
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
        updateShield();
    }

    @Override
    public GameObject fireProjectile() {
        if (missileEnabled()){
            firedMissile();
            return new BossMissile(getProjectileInitialPosition());
        }
        else {
            return bossFiresInCurrentFrame() ? new BossProjectile(getProjectileInitialPosition()) : null;
        }

    }

    private void firedMissile(){
        missilesFired += 1;
    }

    private boolean missileEnabled(){
        return (Math.random() < config.getMissileProbability()) && missilesFired < config.getMissilesLimit();
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
            movePattern.add((int)config.getVelocity());
            movePattern.add((int)-config.getVelocity());
            movePattern.add(0);
        }
        Collections.shuffle(movePattern);
    }

    private void updateShield() {
        if (isShielded) framesWithShieldActivated++;
        else if (shieldShouldBeActivated()) activateShield();    
        if (shieldExhausted()) deactivateShield();
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
        setEffect(shieldGlowEffect);
//        levelview.showShield();
    }

    private void deactivateShield() {
        isShielded = false;
        framesWithShieldActivated = 0;
        setEffect(null);
    }
    
    public int getHealth(){
        return currentHealth;
    }
    
    public int getMaxHealth(){
        return config.getMaxHealth();
    }
}
