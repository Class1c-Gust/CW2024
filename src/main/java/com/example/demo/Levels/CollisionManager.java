package com.example.demo.Levels;

import com.example.demo.GameObject;
import com.example.demo.Planes.UserPlane;
import com.example.demo.Powerups.Powerup;
import javafx.scene.Group;

import java.util.List;

/**
 * Manages collisions between all sprites in the game
 */
public class CollisionManager {
    private final Group root;
    private final UserPlane user;
    private final List<GameObject> friendlyUnits;
    private final List<GameObject> enemyUnits;
    private final List<GameObject> userProjectiles;
    private final List<GameObject> enemyProjectiles;
    private final List<Powerup> powerups;

    /**
     * Constructor for CollisionManager
     * @param root the main root
     * @param user the user plane
     * @param friendlyUnits list of friendly units
     * @param enemyUnits list of enemy units
     * @param userProjectiles list of user projectiles
     * @param enemyProjectiles list of enemy projectiles
     * @param powerups list of power-ups
     */
    public CollisionManager(Group root, UserPlane user, List<GameObject> friendlyUnits, List<GameObject> enemyUnits,
    List<GameObject> userProjectiles, List<GameObject> enemyProjectiles, List<Powerup> powerups){
        this.root = root;
        this.user = user;
        this.friendlyUnits = friendlyUnits;
        this.enemyUnits = enemyUnits;
        this.userProjectiles = userProjectiles;
        this.enemyProjectiles = enemyProjectiles;
        this.powerups = powerups;
    }

    /**
     * Calls all sub collisions in the game
     */
    public void handleGameCollisions(){
        handleEnemyPenetration();
        handleUserProjectileCollisions();
        handleEnemyProjectileCollisions();
        handlePowerupCollisions();
        handlePlaneCollisions();
    }

    /**
     * Handles collisions between user plane and enemy planes
     */
    private void handlePlaneCollisions() {
        handleCollisions(friendlyUnits, enemyUnits);
    }

    /**
     * Handles collisions between user projectiles and enemy planes
     */
    private void handleUserProjectileCollisions() {
        handleCollisions(userProjectiles, enemyUnits);
    }

    /**
     * Handles collisions between the user/user projectiles and power-ups
     */
    public void handlePowerupCollisions(){
        for (Powerup powerup : powerups){
            if (powerup.getExactBounds().intersects(user.getExactBounds())){
                powerup.activatePower(root, user);
                powerup.destroy(); // destroy powerup icon after activated
            }
            else{
                for (GameObject projectile : userProjectiles){
                    if (powerup.getExactBounds().intersects(projectile.getExactBounds()) && powerup.isShootable()) {
                        powerup.activatePower(root, user);
                        powerup.destroy();
                    }
                }
            }
        }
    }

    /**
     * Handles collisions between enemy projectiles and the user plane
     */
    private void handleEnemyProjectileCollisions() {
        handleCollisions(enemyProjectiles, friendlyUnits);
    }

    /**
     * Handles collisions between 2 actors
     * @param actors1
     * @param actors2
     */
    private void handleCollisions(List<GameObject> actors1,
                                  List<GameObject> actors2) {
        for (GameObject actor : actors2) {
            for (GameObject otherActor : actors1) {
                if (actor.getExactBounds().intersects(otherActor.getExactBounds())) {
                    actor.takeDamage();
                    otherActor.takeDamage();
                }
            }
        }
    }

    /**
     * Returns true if enemy has crossed the left side of the screen
     * @param enemy enemy plane
     * @return boolean value of whether enemy has crossed the left side of the screen
     */
    private boolean enemyHasPenetratedDefenses(GameObject enemy) {
        return Math.abs(enemy.getTranslateX()) > 1300;
    }

    /**
     * Handles collision between enemy plane and the left side of the screen
     */
    private void handleEnemyPenetration() {
        for (GameObject enemy : enemyUnits) {
            if (enemyHasPenetratedDefenses(enemy)) {
                user.takeDamage();
                enemy.destroy();
            }
        }
    }
}
