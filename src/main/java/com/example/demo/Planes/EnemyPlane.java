package com.example.demo.Planes;

import com.example.demo.GameObject;
import com.example.demo.Projectiles.ProjectileFactory;

public class EnemyPlane extends FighterPlane {

	private static final String IMAGE_NAME = "enemyplane.png";
	private static final int IMAGE_HEIGHT = 150;
	private static final int HORIZONTAL_VELOCITY = -6;
	private static final double PROJECTILE_X_POSITION_OFFSET = -100.0;
	private static final double PROJECTILE_Y_POSITION_OFFSET = 50.0;
	private static final int INITIAL_HEALTH = 1;
	private static final double FIRE_RATE = .01;

	public EnemyPlane(double initialXPos, double initialYPos) {
		super(IMAGE_NAME, IMAGE_HEIGHT, initialXPos, initialYPos, INITIAL_HEALTH);
	}

	@Override
	public void updatePosition() {
		moveHorizontally(HORIZONTAL_VELOCITY);
	}

	/**
	 * Modified - Integrated yse of the projectile factory to create projectile instances
	 * @return - Projectile object
	 */
	@Override
	public GameObject fireProjectile() {
		if (Math.random() < FIRE_RATE) {
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
