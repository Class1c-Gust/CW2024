package com.example.demo.Powerups;

import com.example.demo.Planes.UserPlane;
import javafx.scene.Group;

/**
 * Heart powerup which allows users to regain an extra life when activated
 */
public class Heart extends Powerup {
    private static final String IMAGE_NAME = "heart.png";
    private static final int IMAGE_HEIGHT = 50;
    private static final double frameDelay = 0.4;
    private static final int HORIZONTAL_VELOCITY = (int)(-10*frameDelay);


    public Heart(double initialYPos) {
        super(IMAGE_NAME, IMAGE_HEIGHT, initialYPos, false);
    }
    @Override
    public void updatePosition() {
        moveHorizontally(HORIZONTAL_VELOCITY);
    }

    @Override
    public void updateActor() {
        updatePosition();
    }

    /**
     * Destroys the instance before incrementing the user plane health
     * @param root The main scene
     * @param user The user plane
     */
    @Override
    public void activatePower(Group root, UserPlane user){
        user.addHealth();

    }

}
