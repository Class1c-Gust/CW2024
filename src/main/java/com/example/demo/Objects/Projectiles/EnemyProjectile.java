package com.example.demo.Objects.Projectiles;

import com.example.demo.config.Config;

/**
 * Represents a projectile fired by an enemy in the game.
 * This class extends the Projectile class and provides behavior specific to enemy projectiles.
 */
public class EnemyProjectile extends Projectile {

	/**
	 * Constructs an EnemyProjectile object with specified initial X and Y positions.
	 *
	 * @param initialXPos The initial X position of the projectile.
	 * @param initialYPos The initial Y position of the projectile.
	 */
	public EnemyProjectile(double initialXPos, double initialYPos) {
		super(Config.Projectile.ENEMY_PROJECTILE_IMAGE, Config.Projectile.IMAGE_HEIGHT,
				initialXPos, initialYPos);
	}

	/**
	 * Updates the horizontal position of the enemy projectile based on its velocity.
	 * The projectile moves horizontally to the left.
	 */
	@Override
	public void updatePosition() {
		moveHorizontally(Config.Projectile.ENEMY_PROJECTILE_HORIZONTAL_VELOCITY);
	}

	/**
	 * Updates the state of the projectile.
	 * In this case, it simply calls the updatePosition method to handle movement.
	 */
	@Override
	public void updateActor() {
		updatePosition();
	}
}
