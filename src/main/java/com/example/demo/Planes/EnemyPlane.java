package com.example.demo.Planes;

import com.example.demo.GameObject;
import com.example.demo.Projectiles.ProjectileFactory;

public class EnemyPlane extends FighterPlane {

	private static final String IMAGE_NAME = "enemyplane.png";
	private static final int IMAGE_HEIGHT = 150;
	private static final double frameDelay = 0.4;
	private static final int HORIZONTAL_VELOCITY = (int)(-6*frameDelay);
	private static final double PROJECTILE_X_POSITION_OFFSET = -100.0;
	private static final double PROJECTILE_Y_POSITION_OFFSET = 50.0;
	private static final int INITIAL_HEALTH = 1;
	private static final double FIRE_RATE = .001;

	public EnemyPlane(double initialXPos, double initialYPos) {
		super(IMAGE_NAME, IMAGE_HEIGHT, initialXPos, initialYPos, INITIAL_HEALTH);
	}

	/**
	 * Modified so position is not updated when the plane is disabled
	 */
	@Override
	public void updatePosition() {
		if (!getDisabledStatus()) {
			moveHorizontally(HORIZONTAL_VELOCITY);
		}
	}

	/**
	 * Modified - Integrated yse of the projectile factory to create projectile instances
	 * Projectile isn't fired when the plane is disabled
	 * @return - Projectile object
	 */
	@Override
	public GameObject fireProjectile() {
		if (Math.random() < FIRE_RATE && !getDisabledStatus()) {
			ProjectileFactory projectileFactory = new ProjectileFactory(IMAGE_NAME, IMAGE_HEIGHT);
			return projectileFactory.createEnemyProjectile(getProjectileXPosition(PROJECTILE_X_POSITION_OFFSET), getProjectileYPosition(PROJECTILE_Y_POSITION_OFFSET));
		}
		return null;
	}

	@Override
	public void updateActor() {
		updatePosition();
	}

}
