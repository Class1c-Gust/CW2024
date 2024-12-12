package com.example.demo.Powerups;

import com.example.demo.Planes.UserPlane;
import com.example.demo.config.Config;
import javafx.scene.Group;

/**
 * Heart powerup which allows users to regain an extra life when activated
 */
public class Heart extends Powerup {

    public Heart(double initialYPos) {
        super(Config.Powerup.HEART_IMAGE_NAME, Config.Powerup.IMAGE_HEIGHT, initialYPos, false);
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
        this.destroy();
        user.addHealth();

    }

}
