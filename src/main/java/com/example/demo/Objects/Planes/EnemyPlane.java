package com.example.demo.Objects.Planes;

import com.example.demo.Objects.GameObject;
import com.example.demo.Factories.ProjectileFactory;
import com.example.demo.config.Config;

/**
 * Represents an enemy plane in the game. This class extends {@link FighterPlane} and includes
 * additional functionality specific to enemy planes, such as firing projectiles and movement behavior.
 * <p>
 * Each enemy plane has a specific image, horizontal velocity, and firing rate. The behavior of the
 * enemy plane includes movement based on its velocity and firing projectiles at a given rate.
 */
public class EnemyPlane extends FighterPlane {

	private final String imageName;
	private final int horizontalVelocity;
	private final double fireRate;

	/**
	 * Constructs an {@code EnemyPlane} instance with specified properties.
	 *
	 * @param imageName        the name of the image file representing the plane.
	 * @param initialXPos      the initial X-coordinate position of the plane.
	 * @param initialYPos      the initial Y-coordinate position of the plane.
	 * @param speed            the base horizontal speed of the plane.
	 * @param fireRate         the probability of firing a projectile in each frame.
	 */
	public EnemyPlane(String imageName, double initialXPos, double initialYPos, int speed, double fireRate) {
		super(imageName, Config.Enemy.IMAGE_HEIGHT, initialXPos, initialYPos, Config.Enemy.INITIAL_HEALTH);
		this.imageName = imageName;
		this.setFitWidth(200);
		this.setFitHeight(150);
		this.horizontalVelocity = (int) (speed * Config.Game.FRAME_DELAY);
		this.fireRate = fireRate;
	}

	/**
	 * Updates the position of the enemy plane by moving it horizontally.
	 * The position is only updated if the plane is not disabled.
	 */
	@Override
	public void updatePosition() {
		if (getDisabledStatus()) {
			moveHorizontally(horizontalVelocity);
		}
	}

	/**
	 * Fires a projectile from the enemy plane if certain conditions are met.
	 * <p>
	 * A projectile is only fired if:
	 * <ul>
	 *     <li>The random probability condition (fire rate) is satisfied.</li>
	 *     <li>The plane is not disabled.</li>
	 * </ul>
	 *
	 * @return a {@link GameObject} representing the projectile, or {@code null} if no projectile is fired.
	 */
	@Override
	public GameObject fireProjectile() {
		if (Math.random() < fireRate && getDisabledStatus()) {
			ProjectileFactory projectileFactory = new ProjectileFactory(imageName, Config.Enemy.IMAGE_HEIGHT);
			return projectileFactory.createEnemyProjectile(
					getProjectileXPosition(Config.Enemy.PROJECTILE_X_POSITION_OFFSET),
					getProjectileYPosition(Config.Enemy.PROJECTILE_Y_POSITION_OFFSET)
			);
		}
		return null;
	}

	/**
	 * Updates the state of the enemy plane by performing actions such as updating its position.
	 */
	@Override
	public void updateActor() {
		updatePosition();
	}
}
