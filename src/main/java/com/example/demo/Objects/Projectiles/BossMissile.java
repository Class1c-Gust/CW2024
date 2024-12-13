package com.example.demo.Objects.Projectiles;

import com.example.demo.config.Config;

/**
 * Represents a boss missile in the game.
 * This class extends the Projectile class and provides specific behavior
 * for a boss missile, including its initial setup and movement.
 */
public class BossMissile extends Projectile {

    /**
     * Constructs a BossMissile object with a specified initial Y position.
     *
     * @param initialYPos The initial Y position of the missile.
     */
    public BossMissile(double initialYPos) {
        super(Config.Projectile.BOSS_MISSILE_IMAGE, Config.Projectile.IMAGE_HEIGHT,
                Config.Projectile.INITIAL_X_POSITION, initialYPos);

        // Set specific dimensions for the boss missile.
        this.setFitWidth(200); // Width of the missile.
        this.setFitHeight(50); // Height of the missile.
    }

    /**
     * Updates the horizontal position of the boss missile based on its velocity.
     * The missile moves horizontally to the left at a predefined velocity.
     */
    @Override
    public void updatePosition() {
        moveHorizontally(Config.Projectile.BOSS_MISSILE_HORIZONTAL_VELOCITY);
    }

    /**
     * Updates the state of the missile actor.
     * In this case, it simply calls the updatePosition method to handle movement.
     */
    @Override
    public void updateActor() {
        updatePosition();
    }
}
