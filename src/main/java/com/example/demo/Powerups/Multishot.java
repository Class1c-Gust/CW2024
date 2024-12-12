package com.example.demo.Powerups;

import com.example.demo.Planes.UserPlane;
import com.example.demo.config.Config;
import javafx.scene.Group;

/**
 * Multishot powerup that fires multiple shots when activated
 */
public class Multishot extends Powerup {

    public Multishot(double initialYPos) {
        super(Config.Powerup.MULTISHOT_IMAGE_NAME, Config.Powerup.IMAGE_HEIGHT, initialYPos, false);
    }

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


