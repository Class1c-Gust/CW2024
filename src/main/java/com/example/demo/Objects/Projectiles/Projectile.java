package com.example.demo.Objects.Projectiles;

import com.example.demo.Objects.GameObject;

/**
 * Represents a generic projectile in the game.
 * This class is abstract and is intended to be extended by specific projectile types.
 */
public abstract class Projectile extends GameObject {

	/**
	 * Constructs a Projectile object with specified image, size, and initial position.
	 *
	 * @param imageName   The name of the image representing the projectile.
	 * @param imageHeight The height of the projectile image.
	 * @param initialXPos The initial X position of the projectile.
	 * @param initialYPos The initial Y position of the projectile.
	 */
	public Projectile(String imageName, int imageHeight, double initialXPos, double initialYPos) {
		super(imageName, imageHeight, initialXPos, initialYPos);
	}

	/**
	 * Handles the event of the projectile taking damage.
	 * In this case, the projectile is destroyed upon taking damage.
	 */
	@Override
	public void takeDamage() {
		this.destroy();
	}

	/**
	 * Updates the position of the projectile.
	 * This method is abstract and must be implemented by subclasses to define specific movement behavior.
	 */
	@Override
	public abstract void updatePosition();
}
