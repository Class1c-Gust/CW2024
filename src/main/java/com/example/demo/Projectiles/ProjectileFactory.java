package com.example.demo.Projectiles;

import com.example.demo.GameObject;
import com.example.demo.GameObjectFactory;

/**
 * Create Different types of Projectile products
 * Will likely improve this in the next update
 */
public class ProjectileFactory implements GameObjectFactory {
    protected String imageName;
    protected int imageHeight;


    public ProjectileFactory(String imageName, int imageHeight) {
        this.imageName = imageName;
        this.imageHeight = imageHeight;
    }
    public GameObject createEntity(double xPos, double yPos, int health){
        return null;
    }
    public GameObject createEntity(double xPos, double yPos){
        return null;
    }

    public UserProjectile createUserProjectile(double xPos, double yPos) {
        return new UserProjectile(xPos, yPos);
    }

    public EnemyProjectile createEnemyProjectile(double xPos, double yPos) {
        return new EnemyProjectile(xPos, yPos);
    }

    public BossProjectile createBossProjectile(double yPos) {
        return new BossProjectile(yPos);
    }

}
