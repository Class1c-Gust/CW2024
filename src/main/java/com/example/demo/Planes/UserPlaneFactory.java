package com.example.demo.Planes;

import com.example.demo.GameObject;

/** Subclass of "PlaneFactory" class
 * To create UserPlane products
 */
public class UserPlaneFactory extends PlaneFactory {
    private static final String USER_IMAGE = "userplane.png";
    private static final int USER_HEIGHT = 150;

    public UserPlaneFactory(int defaultHealth) {
        super(USER_IMAGE, USER_HEIGHT, defaultHealth);
    }

    @Override
    public GameObject createEntity(double xPos, double yPos, int health) {
        return new UserPlane(health);
    }

    public UserPlane createUserPlane() {
        return new UserPlane(defaultHealth);
    }
}
