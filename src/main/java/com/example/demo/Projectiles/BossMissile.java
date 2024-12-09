package com.example.demo.Projectiles;

public class BossMissile extends Projectile {

    private static final String IMAGE_NAME = "missile.png";
    private static final int IMAGE_HEIGHT = 75;
    private static final double frameDelay = 0.4;
    private static final int HORIZONTAL_VELOCITY = (int)(-25*frameDelay);
    private static final int INITIAL_X_POSITION = 950;

    public BossMissile(double initialYPos) {
        super(IMAGE_NAME, IMAGE_HEIGHT, INITIAL_X_POSITION, initialYPos);
        this.setFitWidth(200);
        this.setFitHeight(50);
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
