package com.example.demo.Factories;

import com.example.demo.Objects.GameObject;
import com.example.demo.Objects.Planes.UserPlane;
import com.example.demo.config.Config;

/** Subclass of "PlaneFactory" class
 * To create UserPlane products
 */
public class UserPlaneFactory extends PlaneFactory {
    /**
     * Constructor of UserPlaneFactory
     * @param defaultHealth the initial health of the plane
     */
    public UserPlaneFactory(int defaultHealth) {
        super(Config.Player.IMAGE_NAME, Config.Player.IMAGE_HEIGHT, defaultHealth);
    }

    /**
     * Placeholder function
     */
    @Override
    public GameObject createEntity(double xPos, double yPos, int health) {
        return null;
    }

    /**
     * Creates an instance of UserPlane
     * @return UserPlane instance
     */
    public UserPlane createUserPlane() {
        return new UserPlane(defaultHealth);
    }
}
