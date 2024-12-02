package com.example.demo;

import com.example.demo.Projectiles.Projectile;

/**
 * New class for different powerups later to be added
 */
public abstract class Powerup extends Projectile {

    public Powerup(String imageName, int imageHeight, double initialXPos, double initialYPos) {
        super(imageName, imageHeight, initialXPos, initialYPos);
    }

    @Override
    public void updatePosition() {
    }

    @Override
    public void updateActor() {
        updatePosition();
    }

}
