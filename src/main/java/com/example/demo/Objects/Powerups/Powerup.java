package com.example.demo.Objects.Powerups;

import com.example.demo.Objects.Planes.UserPlane;
import com.example.demo.Objects.Projectiles.Projectile;
import com.example.demo.config.Config;
import javafx.scene.Group;

/**
 * Pickup-able items that grant the user buffs when activated
 */
public abstract class Powerup extends Projectile {
    private final boolean shootable;

    /**
     * Constructor for the Powerup class
     * @param imageName Image of the instance
     * @param imageHeight Image Height of the instance
     * @param initialYPos Initial Y-Coordinate position of the powerup icon
     * @param shootable Whether instance is shootable or not
     */
    public Powerup(String imageName, int imageHeight, double initialYPos, boolean shootable) {
        super(imageName, imageHeight, Config.Powerup.INITIAL_X_POSITION, initialYPos);
        this.shootable = shootable;
    }

    /**
     * Updates position of the powerup after movement
     */
    @Override
    public void updatePosition() {
        moveHorizontally(Config.Powerup.HORIZONTAL_VELOCITY);
    }

    /**
     * Updates the instance entirely
     */
    @Override
    public void updateActor() {
        updatePosition();
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
