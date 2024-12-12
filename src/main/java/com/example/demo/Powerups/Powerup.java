package com.example.demo.Powerups;

import com.example.demo.Planes.UserPlane;
import com.example.demo.Projectiles.Projectile;
import com.example.demo.config.Config;
import javafx.scene.Group;

/**
 * Super class for power up functionality
 */
public abstract class Powerup extends Projectile {
    private final boolean shootable;

    public Powerup(String imageName, int imageHeight, double initialYPos, boolean shootable) {
        super(imageName, imageHeight, Config.Powerup.INITIAL_X_POSITION, initialYPos);
        this.shootable = shootable;
    }

    @Override
    public void updatePosition() {
        moveHorizontally(Config.Powerup.HORIZONTAL_VELOCITY);
    }

    @Override
    public void updateActor() {
        updatePosition();
    }

    public void updateActor(double y){updatePosition(y);}

    public void updatePosition(double y_movement) {
        this.setTranslateY(y_movement);
    }

    /**
     * Activates the specific powerup's affect when called
     * @param root The main scene
     * @param user The user plane
     */
    public abstract void activatePower(Group root, UserPlane user);

    /**
     * Getter for the shootable attribute
     * @return Boolean value
     */
    public boolean isShootable(){
        return this.shootable;
    }

}
