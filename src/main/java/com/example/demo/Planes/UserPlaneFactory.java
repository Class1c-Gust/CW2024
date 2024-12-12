package com.example.demo.Planes;

import com.example.demo.GameObject;
import com.example.demo.config.Config;

/** Subclass of "PlaneFactory" class
 * To create UserPlane products
 */
public class UserPlaneFactory extends PlaneFactory {

    public UserPlaneFactory(int defaultHealth) {
        super(Config.Player.IMAGE_NAME, Config.Player.IMAGE_HEIGHT, defaultHealth);
    }

    @Override
    public GameObject createEntity(double xPos, double yPos, int health) {
        return new UserPlane(health);
    }

    public UserPlane createUserPlane() {
        return new UserPlane(defaultHealth);
    }
}
