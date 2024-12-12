package com.example.demo.Planes;

import com.example.demo.GameObject;
import com.example.demo.Projectiles.ProjectileFactory;
import com.example.demo.config.Config;

public class EnemyPlane extends FighterPlane {
	private final String imageName;
	private final int horizontalVelocity;
	private final double fireRate;
	
	public EnemyPlane(String imageName, double initialXPos, double initialYPos, int speed, double fireRate) {
		super(imageName, Config.Enemy.IMAGE_HEIGHT, initialXPos, initialYPos, Config.Enemy.INITIAL_HEALTH);
		this.imageName = imageName;
		this.setFitWidth(200);
		this.setFitHeight(150);
		this.horizontalVelocity = (int)(speed * Config.Game.FRAME_DELAY);
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
			ProjectileFactory projectileFactory = new ProjectileFactory(imageName, Config.Enemy.IMAGE_HEIGHT);
			return projectileFactory.createEnemyProjectile(getProjectileXPosition(Config.Enemy.PROJECTILE_X_POSITION_OFFSET),
					getProjectileYPosition(Config.Enemy.PROJECTILE_Y_POSITION_OFFSET));
		}
		return null;
	}

	@Override
	public void updateActor() {
		updatePosition();
	}

}
