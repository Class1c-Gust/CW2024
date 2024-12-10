package com.example.demo.Planes;

import com.example.demo.GameObject;
import com.example.demo.Projectiles.ProjectileFactory;

public class EnemyPlane extends FighterPlane {

	private static final int IMAGE_HEIGHT = 150;
	private static final double frameDelay = 0.4;
	
	private final String imageName;
	private final int horizontalVelocity;
	private final double fireRate;
	private static final double PROJECTILE_X_POSITION_OFFSET = -100.0;
	private static final double PROJECTILE_Y_POSITION_OFFSET = 50.0;
	private static final int INITIAL_HEALTH = 1;
	
	public EnemyPlane(String imageName, double initialXPos, double initialYPos, int speed, double fireRate) {
		super(imageName, IMAGE_HEIGHT, initialXPos, initialYPos, INITIAL_HEALTH);
		this.imageName = imageName;
		this.setFitWidth(200);
		this.setFitHeight(150);
		this.horizontalVelocity = (int)(speed * frameDelay);
		this.fireRate = fireRate;
	}

	/**
	 * Modified so position is not updated when the plane is disabled
	 */
	@Override
	public void updatePosition() {
		if (!getDisabledStatus()) {
			moveHorizontally(horizontalVelocity);
		}
	}

	/**
	 * Modified - Integrated use of the projectile factory to create projectile instances
	 * Projectile isn't fired when the plane is disabled
	 * @return - Projectile object
	 */
	@Override
	public GameObject fireProjectile() {
		if (Math.random() < fireRate && !getDisabledStatus()) {
			ProjectileFactory projectileFactory = new ProjectileFactory(imageName, IMAGE_HEIGHT);
			return projectileFactory.createEnemyProjectile(getProjectileXPosition(PROJECTILE_X_POSITION_OFFSET), getProjectileYPosition(PROJECTILE_Y_POSITION_OFFSET));
		}
		return null;
	}

	@Override
	public void updateActor() {
		updatePosition();
	}

}
