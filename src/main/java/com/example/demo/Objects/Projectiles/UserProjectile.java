package com.example.demo.Objects.Projectiles;

import com.example.demo.config.Config;

/**
 * Represents a projectile fired by the user in the game.
 * This class extends the Projectile class and provides behavior specific to user projectiles.
 */
public class UserProjectile extends Projectile {

	/**
	 * Constructs a UserProjectile object with specified initial X and Y positions.
	 *
	 * @param initialXPos The initial X position of the projectile.
	 * @param initialYPos The initial Y position of the projectile.
	 */
	public UserProjectile(double initialXPos, double initialYPos) {
		super(Config.Projectile.USER_PROJECTILE_IMAGE, Config.Projectile.USER_PROJECTILE_IMAGE_HEIGHT,
				initialXPos, initialYPos);
	}

	/**
	 * Updates the horizontal position of the user projectile based on its velocity.
	 * The projectile moves horizontally to the right.
	 */
	@Override
	public void updatePosition() {
		moveHorizontally(Config.Projectile.USER_PROJECTILE_HORIZONTAL_VELOCITY);
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
