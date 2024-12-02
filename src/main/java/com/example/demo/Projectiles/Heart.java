package com.example.demo.Projectiles;

import com.example.demo.Powerup;

/**
 * Heart is displayed which allows users to regain an extra life
 */
public class Heart extends Powerup {
    private static final String IMAGE_NAME = "heart.png";
    private static final int IMAGE_HEIGHT = 50;
    private static final int HORIZONTAL_VELOCITY = -10;
    private static final int INITIAL_X_POSITION = 1300;

    public Heart(double initialYPos) {
        super(IMAGE_NAME, IMAGE_HEIGHT, INITIAL_X_POSITION, initialYPos);
    }
    @Override
    public void updatePosition() {
        moveHorizontally(HORIZONTAL_VELOCITY);
    }

    @Override
    public void updateActor() {
        updatePosition();
    }

}
