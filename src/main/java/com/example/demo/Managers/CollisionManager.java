package com.example.demo.Managers;

import com.example.demo.Objects.GameObject;
import com.example.demo.Objects.Planes.UserPlane;
import com.example.demo.Objects.Powerups.Powerup;
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
    private final SoundManager soundManager;

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
        this.soundManager = SoundManager.getInstance();
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
        handleCollisions(friendlyUnits, enemyUnits, 2);
    }

    /**
     * Handles collisions between user projectiles and enemy planes
     */
    private void handleUserProjectileCollisions() {
        handleCollisions(userProjectiles, enemyUnits, 1);
    }

    /**
     * Handles collisions between the user/user projectiles and power-ups
     */
    public void handlePowerupCollisions(){
        for (Powerup powerup : powerups){
            if (powerup.getExactBounds().intersects(user.getExactBounds())){
                powerup.activatePower(root, user);
                powerup.destroy(); // destroy powerup icon after activated
                soundManager.playPowerupSound();
            }
            else{
                for (GameObject projectile : userProjectiles){
                    if (powerup.getExactBounds().intersects(projectile.getExactBounds()) && powerup.isShootable()) {
                        powerup.activatePower(root, user);
                        soundManager.playPowerupSound();
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
        handleCollisions(enemyProjectiles, friendlyUnits, 2);
    }

    /**
     * Handles collisions between 2 actors
     * @param actors1 1st List of gameObjects
     * @param actors2 2nd List of gameObjects
     */
    private void handleCollisions(List<GameObject> actors1,
                                  List<GameObject> actors2, int type) {
        boolean hasCollided = false;
        for (GameObject actor : actors2) {
            for (GameObject otherActor : actors1) {
                if (actor.getExactBounds().intersects(otherActor.getExactBounds())) {
                    actor.takeDamage();
                    otherActor.takeDamage();
                    hasCollided = true;
                }
            }
        }
        if (hasCollided) {
            if (type == 2) {
                soundManager.playCrashSound();
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
                soundManager.playCrashSound();
            }
        }
    }
}
