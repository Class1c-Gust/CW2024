package com.example.demo.Planes;

import com.example.demo.GameObject;

/** Subclass of "PlaneFactory" class
 * To create EnemyPlane products
 */
public class EnemyPlaneFactory extends PlaneFactory {
    private static final String ENEMY_IMAGE = "enemyplane.png";
    private static final int ENEMY_HEIGHT = 150;
    private static final int DEFAULT_HEALTH = 1;

    public EnemyPlaneFactory() {
        super(ENEMY_IMAGE, ENEMY_HEIGHT, DEFAULT_HEALTH);
    }

    @Override
    public GameObject createEntity(double xPos, double yPos, int health) {
        return new EnemyPlane(xPos, yPos);
    }
}
