package com.example.demo.Factories;

import com.example.demo.Objects.GameObject;
import com.example.demo.Objects.GameObjectFactory;
import com.example.demo.Objects.Projectiles.EnemyProjectile;
import com.example.demo.Objects.Projectiles.UserProjectile;

/**
 * A factory class for creating different types of projectiles.
 * It provides methods to create user projectiles, enemy projectiles, and boss projectiles.
 */
public class ProjectileFactory implements GameObjectFactory {
    protected String imageName;
    protected int imageHeight;

    /**
     * Constructs a ProjectileFactory object with specified image name and height.
     *
     * @param imageName   The default image name for projectiles.
     * @param imageHeight The default height for projectiles.
     */
    public ProjectileFactory(String imageName, int imageHeight) {
        this.imageName = imageName;
        this.imageHeight = imageHeight;
    }

    /**
     * Creates a generic projectile entity. Currently returns null.
     *
     * @param xPos   The initial X position of the entity.
     * @param yPos   The initial Y position of the entity.
     * @param health The health of the entity.
     * @return Always returns null in this implementation.
     */
    public GameObject createEntity(double xPos, double yPos, int health) {
        return null;
    }

    /**
     * Creates a generic projectile entity. Currently returns null.
     *
     * @param xPos The initial X position of the entity.
     * @param yPos The initial Y position of the entity.
     * @return Always returns null in this implementation.
     */
    public GameObject createEntity(double xPos, double yPos) {
        return null;
    }

    /**
     * Creates a user projectile at the specified position.
     *
     * @param xPos The initial X position of the user projectile.
     * @param yPos The initial Y position of the user projectile.
     * @return A new UserProjectile object.
     */
    public UserProjectile createUserProjectile(double xPos, double yPos) {
        return new UserProjectile(xPos, yPos);
    }
    /**
     * Creates an enemy projectile at the specified position.
     *
     * @param xPos The initial X position of the enemy projectile.
     * @param yPos The initial Y position of the enemy projectile.
     * @return A new EnemyProjectile object.
     */
    public EnemyProjectile createEnemyProjectile(double xPos, double yPos) {
        return new EnemyProjectile(xPos, yPos);
    }

}
