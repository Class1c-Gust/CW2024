package com.example.demo.Objects.Planes;

import com.example.demo.Objects.GameObject;

/**
 * Abstract class representing a fighter plane in the game.
 * This class extends the GameObject class and provides functionality for health management,
 * firing projectiles, and movement.
 */
public abstract class FighterPlane extends GameObject {

	private int health;
	// Status indicating whether the fighter plane is disabled.
	private boolean isDisabled;

	/**
	 * Constructor to initialize a FighterPlane object.
	 *
	 * @param imageName The image name of the plane.
	 * @param imageHeight The height of the plane image.
	 * @param initialXPos The initial X position of the plane.
	 * @param initialYPos The initial Y position of the plane.
	 * @param health The initial health of the plane.
	 */
	public FighterPlane(String imageName, int imageHeight, double initialXPos, double initialYPos, int health) {
		super(imageName, imageHeight, initialXPos, initialYPos);
		this.health = health;
		this.isDisabled = false; // Initially the plane is not disabled.
	}

	/**
	 * Abstract method to be implemented by subclasses to fire a projectile.
	 */
	public abstract GameObject fireProjectile();

	/**
	 * Method to reduce health when the plane takes damage.
	 * If health reaches zero, the plane is destroyed.
	 */
	@Override
	public void takeDamage() {
		health--;
		if (healthAtZero()) {
			this.destroy();
		}
	}

	/**
	 * Helper method to calculate the X position for projectile firing.
	 *
	 * @param xPositionOffset The offset for the X position of the projectile.
	 * @return The calculated X position.
	 */
	protected double getProjectileXPosition(double xPositionOffset) {
		return getLayoutX() + getTranslateX() + xPositionOffset;
	}

	/**
	 * Helper method to calculate the Y position for projectile firing.
	 *
	 * @param yPositionOffset The offset for the Y position of the projectile.
	 * @return The calculated Y position.
	 */
	protected double getProjectileYPosition(double yPositionOffset) {
		return getLayoutY() + getTranslateY() + yPositionOffset;
	}

	/**
	 * Helper method to check if the plane's health has reached zero.
	 *
	 * @return True if health is zero, false otherwise.
	 */
	private boolean healthAtZero() {
		return health == 0;
	}

	/**
	 * Getter for the health of the plane.
	 *
	 * @return The current health of the plane.
	 */
	public int getHealth() {
		return health;
	}

	/**
	 * Method to increase the health of the plane by 1.
	 */
	public void addHealth() {
		this.health += 1;
	}

	/**
	 * Setter to update the disabled status of the plane.
	 *
	 * @param disabledStatus The new disabled status of the plane.
	 */
	public void setDisabledStatus(boolean disabledStatus) {
		this.isDisabled = disabledStatus;
	}

	/**
	 * Getter to check if the plane is disabled.
	 *
	 * @return True if the plane is disabled, false otherwise.
	 */
	public boolean getDisabledStatus() {
		return !this.isDisabled;
	}
}
