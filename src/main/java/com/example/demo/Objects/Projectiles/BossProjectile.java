package com.example.demo.Objects.Projectiles;

import com.example.demo.config.Config;

/**
 * Represents a projectile fired by the boss in the game.
 * It inherits from the Projectile class and provides specific behavior for the boss's projectiles.
 */
public class BossProjectile extends Projectile {

	/**
	 * Constructs a BossProjectile object with a specified initial Y position.
	 *
	 * @param initialYPos The initial Y position of the projectile.
	 */
	public BossProjectile(double initialYPos) {
		super(Config.Projectile.BOSS_PROJECTILE_IMAGE, Config.Projectile.IMAGE_HEIGHT,
				Config.Projectile.INITIAL_X_POSITION, initialYPos);
	}

	/**
	 * Updates the horizontal position of the boss projectile based on its velocity.
	 * The projectile moves horizontally to the left.
	 */
	@Override
	public void updatePosition() {
		moveHorizontally(Config.Projectile.BOSS_PROJECTILE_HORIZONTAL_VELOCITY);
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
