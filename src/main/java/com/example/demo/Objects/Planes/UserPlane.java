package com.example.demo.Objects.Planes;

import com.example.demo.Objects.GameObject;
import com.example.demo.Objects.Projectiles.ProjectileFactory;
import com.example.demo.config.Config;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Represents the user's plane in the game. The user plane can move in all directions, fire projectiles,
 * and can use a multi-shot feature to fire multiple projectiles at once. It tracks kills and includes
 * various functionalities such as controlling the plane's movement and handling the firing mechanics.
 */
public class UserPlane extends FighterPlane {

	private int VerticalVelocityMultiplier; // Multiplier for vertical movement speed
	private int HorizontalVelocityMultiplier; // Multiplier for horizontal movement speed
	private boolean multiShotEnabled = false; // Whether the multi-shot power-up is active
	private int numberOfKills;
	private final ProjectileFactory projectileFactory = new ProjectileFactory(Config.Player.IMAGE_NAME, Config.Player.IMAGE_HEIGHT);
	private long lastTimeFired = 0; // Track the last time the user fired a projectile

	/**
	 * Constructor to initialize the user's plane with specified health.
	 *
	 * @param initialHealth The initial health of the user's plane.
	 */
	public UserPlane(int initialHealth) {
		super(Config.Player.IMAGE_NAME, Config.Player.IMAGE_HEIGHT,
				Config.Player.INITIAL_X_POSITION, Config.Player.INITIAL_Y_POSITION,
				initialHealth);
		VerticalVelocityMultiplier = 0;
		this.HorizontalVelocityMultiplier = 0;
	}

	/**
	 * Updates the position of the plane both vertically and horizontally based on movement multipliers.
	 * Ensures the plane stays within the game boundaries.
	 */
	@Override
	public void updatePosition() {
		// Vertical movement update
		if (isMovingVertically()) {
			double overallVerticalVelocity = VerticalVelocityMultiplier * Config.Player.VERTICAL_VELOCITY;
			double yPosition = getLayoutY() + getTranslateY() + (overallVerticalVelocity);
			if (yPosition >= Config.Player.Y_UPPER_BOUND && yPosition <= Config.Player.Y_LOWER_BOUND) {
				moveVertically(overallVerticalVelocity);
			}
		}

		// Horizontal movement update
		if (isMovingHorizontally()) {
			double overallHorizontalVelocity = HorizontalVelocityMultiplier * Config.Player.HORIZONTAL_VELOCITY;
			double xPosition = getLayoutX() + getTranslateX() + (overallHorizontalVelocity);
			if (xPosition >= Config.Player.X_LEFT_BOUND && xPosition <= Config.Player.X_RIGHT_BOUND) {
				moveHorizontally(overallHorizontalVelocity);
			}
		}
	}

	/**
	 * Updates the plane by updating its position.
	 */
	@Override
	public void updateActor() {
		updatePosition();
	}

	/**
	 * Fires a projectile from the user's plane if the cooldown period has passed.
	 *
	 * @return The projectile fired by the user or null if the cooldown is still active.
	 */
	@Override
	public GameObject fireProjectile() {
		long currentTime = System.currentTimeMillis();
		if (currentTime - lastTimeFired >= Config.Player.PROJECTILE_COOLDOWN) {
			lastTimeFired = currentTime;
			return projectileFactory.createUserProjectile(getLayoutX() + getTranslateX(),
					getProjectileYPosition(Config.Player.PROJECTILE_Y_POSITION_OFFSET));
		}
		return null;
	}

	/**
	 * Checks whether the plane is moving vertically.
	 *
	 * @return true if the plane is moving vertically, false otherwise.
	 */
	private boolean isMovingVertically() {
		return VerticalVelocityMultiplier != 0;
	}

	/**
	 * Checks whether the plane is moving horizontally.
	 *
	 * @return true if the plane is moving horizontally, false otherwise.
	 */
	private boolean isMovingHorizontally() {
		return HorizontalVelocityMultiplier != 0;
	}

	/**
	 * Moves the plane up by setting the vertical velocity multiplier to -1 (upward movement).
	 * Movement is restricted if the plane is disabled.
	 */
	public void moveUp() {
		if (getDisabledStatus()) VerticalVelocityMultiplier = -1;
	}

	/**
	 * Moves the plane down by setting the vertical velocity multiplier to 1 (downward movement).
	 * Movement is restricted if the plane is disabled.
	 */
	public void moveDown() {
		if (getDisabledStatus()) VerticalVelocityMultiplier = 1;
	}

	/**
	 * Moves the plane right by setting the horizontal velocity multiplier to 1 (rightward movement).
	 * Movement is restricted if the plane is disabled.
	 */
	public void moveRight() {
		if (getDisabledStatus()) HorizontalVelocityMultiplier = 1;
	}

	/**
	 * Moves the plane left by setting the horizontal velocity multiplier to -1 (leftward movement).
	 * Movement is restricted if the plane is disabled.
	 */
	public void moveLeft() {
		if (getDisabledStatus()) HorizontalVelocityMultiplier = -1;
	}

	/**
	 * Stops the plane's vertical movement by setting the vertical velocity multiplier to 0.
	 */
	public void stopVertical() {
		VerticalVelocityMultiplier = 0;
	}

	/**
	 * Stops the plane's horizontal movement by setting the horizontal velocity multiplier to 0.
	 */
	public void stopHorizontal() {
		HorizontalVelocityMultiplier = 0;
	}

	/**
	 * Returns the current number of kills made by the user.
	 *
	 * @return The number of kills.
	 */
	public int getNumberOfKills() {
		return numberOfKills;
	}

	/**
	 * Increments the kill count by 1.
	 */
	public void incrementKillCount() {
		numberOfKills++;
	}

	/**
	 * Fires multiple projectiles at once (triple-shot) if the multi-shot power-up is enabled and
	 * the cooldown period has passed. If the power-up is not enabled, a single projectile is fired.
	 * Returns an empty list if the plane is disabled or the cooldown period is active.
	 *
	 * @return A list of projectiles fired or an empty list if conditions are not met.
	 */
	public List<GameObject> fireMultiShot() {
		if (getDisabledStatus()) {
			List<GameObject> projectiles = new ArrayList<>();
			long currentTime = System.currentTimeMillis();
			if (currentTime - lastTimeFired >= Config.Player.PROJECTILE_COOLDOWN) {
				lastTimeFired = currentTime;
				if (multiShotEnabled) {
					double yPos = getProjectileYPosition(Config.Player.PROJECTILE_Y_POSITION_OFFSET);
					projectiles.add(projectileFactory.createUserProjectile(getLayoutX() + getTranslateX(), yPos - 20));
					projectiles.add(projectileFactory.createUserProjectile(getLayoutX() + getTranslateX(), yPos));
					projectiles.add(projectileFactory.createUserProjectile(getLayoutX() + getTranslateX(), yPos + 20));
				} else {
					projectiles.add(fireProjectile()); // Fire a single projectile if multi-shot is not enabled
				}
				return projectiles;
			}
			return Collections.emptyList();
		}
		return Collections.emptyList();
	}

	/**
	 * Enables the multi-shot power-up for the user's plane for a duration of 10 seconds.
	 * During this time, the plane will fire three projectiles at once.
	 */
	public void enableMultiShot() {
		this.multiShotEnabled = true;

		// Schedule deactivation of the multi-shot after 10 seconds
		Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(Config.Powerup.MULTISHOT_DURATION), e -> this.multiShotEnabled = false));
		timeline.setCycleCount(1);
		timeline.play();
	}

	/**
	 * Returns the current status of the multi-shot power-up.
	 *
	 * @return true if multi-shot is enabled, false otherwise.
	 */
	public boolean isMultishotEnabled() {
		return multiShotEnabled;
	}
}
