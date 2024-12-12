package com.example.demo.Projectiles;

import com.example.demo.config.Config;

public class BossMissile extends Projectile {
    public BossMissile(double initialYPos) {
        super(Config.Projectile.BOSS_MISSILE_IMAGE, Config.Projectile.IMAGE_HEIGHT,
                Config.Projectile.INITIAL_X_POSITION, initialYPos);
        this.setFitWidth(200);
        this.setFitHeight(50);
    }

    @Override
    public void updatePosition() {
        moveHorizontally(Config.Projectile.BOSS_MISSILE_HORIZONTAL_VELOCITY);
    }

    @Override
    public void updateActor() {
        updatePosition();
    }

}
