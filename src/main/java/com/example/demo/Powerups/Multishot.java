package com.example.demo.Powerups;

import com.example.demo.Planes.UserPlane;
import javafx.scene.Group;

/**
 * Multishot powerup that fires multiple shots when activated
 */
public class Multishot extends Powerup {
    private static final String IMAGE_NAME = "triplefire.png";
    private static final int IMAGE_HEIGHT = 50;
    private static final double frameDelay = 0.4;
    private static final int HORIZONTAL_VELOCITY = (int)(-10*frameDelay);

    public Multishot(double initialYPos) {
        super(IMAGE_NAME, IMAGE_HEIGHT, initialYPos, false);
    }
    @Override
    public void updatePosition() {
        moveHorizontally(HORIZONTAL_VELOCITY);
    }

//    public void updatePosition(int y) {
//        this.setTranslateY(y);
//    }
//
//    @Override
//    public void updateActor() {
//        updatePosition();
////        this.laser.updateActor();
//
//    }

    /**
     * Destroys the instance and enables the user to use multishot
     * @param root The main scene
     * @param user The user plane
     */
    @Override
    public void activatePower(Group root, UserPlane user){
        this.destroy();
        user.enableMultiShot();


    }

}


