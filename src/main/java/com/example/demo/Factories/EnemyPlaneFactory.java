package com.example.demo.Factories;

import com.example.demo.Objects.GameObject;
import com.example.demo.Objects.Planes.EnemyPlane;
import com.example.demo.config.Config;

/**
 * Factory class for creating enemy planes in the game.
 * <p>
 * This class extends {@link PlaneFactory} and provides methods to create different types of enemy planes
 * based on specific configurations. It supports the creation of both normal and fast enemy planes
 * through the {@link EnemyType} enumeration.
 */
public class EnemyPlaneFactory extends PlaneFactory {

    /**
     * Enumeration defining the types of enemy planes.
     * <p>
     * Each type specifies an image, speed, and fire rate for the plane:
     * <ul>
     *     <li>{@code NORMAL}: Represents a standard enemy plane with default attributes.</li>
     *     <li>{@code FAST}: Represents a faster enemy plane with increased speed and fire rate.</li>
     * </ul>
     */
    public enum EnemyType {
        /** Normal enemy plane configuration. */
        NORMAL(Config.Enemy.NORMAL_IMAGE, Config.Enemy.NORMAL_SPEED, Config.Enemy.NORMAL_FIRE_RATE),

        /** Fast enemy plane configuration. */
        FAST(Config.Enemy.FAST_IMAGE, Config.Enemy.FAST_SPEED, Config.Enemy.FAST_FIRE_RATE);

        /** The image file representing the plane. */
        private final String image;

        /** The horizontal speed of the plane. */
        private final int speed;

        /** The probability of the plane firing projectiles per frame. */
        private final double fireRate;

        /**
         * Constructor for {@link EnemyType}.
         *
         * @param image    the image file name for the plane.
         * @param speed    the speed of the plane.
         * @param fireRate the fire rate of the plane.
         */
        EnemyType(String image, int speed, double fireRate) {
            this.image = image;
            this.speed = speed;
            this.fireRate = fireRate;
        }
    }

    /**
     * Constructs an {@code EnemyPlaneFactory} with default settings for normal enemy planes.
     */
    public EnemyPlaneFactory() {
        super(Config.Enemy.NORMAL_IMAGE, Config.Enemy.IMAGE_HEIGHT, Config.Enemy.INITIAL_HEALTH);
    }

    /**
     * Placeholder method for creating an entity. This method is not implemented in this class.
     *
     * @param xPos   the X-coordinate position of the entity.
     * @param yPos   the Y-coordinate position of the entity.
     * @param health the health of the entity.
     * @return always returns {@code null}.
     */
    @Override
    public GameObject createEntity(double xPos, double yPos, int health) {
        return null;
    }

    /**
     * Creates an {@link EnemyPlane} instance based on the specified parameters.
     * <p>
     * The type of enemy plane (normal or fast) is determined by the specified probability.
     *
     * @param xPos           the X-coordinate position of the plane.
     * @param yPos           the Y-coordinate position of the plane.
     * @param fastProbability the probability of spawning a fast enemy plane. A higher value increases the likelihood.
     * @return an {@code EnemyPlane} instance with the selected type's configuration.
     */
    public GameObject createEntity(double xPos, double yPos, double fastProbability) {
        EnemyType type = Math.random() < fastProbability ? EnemyType.FAST : EnemyType.NORMAL;
        return new EnemyPlane(type.image, xPos, yPos, type.speed, type.fireRate);
    }
}
