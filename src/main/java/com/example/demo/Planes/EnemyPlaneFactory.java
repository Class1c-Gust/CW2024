package com.example.demo.Planes;

import com.example.demo.GameObject;
import com.example.demo.Levels.LevelConfiguration;

/** Subclass of "PlaneFactory" class
 * To create EnemyPlane products
 */
public class EnemyPlaneFactory extends PlaneFactory {
    private static final int ENEMY_HEIGHT = 150;
    private static final int DEFAULT_HEALTH = 1;

    public enum EnemyType {
        NORMAL("enemyplane.png", -6, 0.001),
        FAST("enemyplane2.png", -9, 0.003);

        private final String image;
        private final int speed;
        private final double fireRate;

        EnemyType(String image, int speed, double fireRate) {
            this.image = image;
            this.speed = speed;
            this.fireRate = fireRate;
        }
    }

    public EnemyPlaneFactory() {
        super("enemyplane.png", ENEMY_HEIGHT, DEFAULT_HEALTH);
    }

    @Override
    public GameObject createEntity(double xPos, double yPos, int health) {
        return null;
    }

    public GameObject createEntity(double xPos, double yPos, double advancedProbability) {
        EnemyType type = Math.random() < advancedProbability ? EnemyType.FAST : EnemyType.NORMAL;
        return new EnemyPlane(type.image, xPos, yPos, type.speed, type.fireRate);
    }
}
