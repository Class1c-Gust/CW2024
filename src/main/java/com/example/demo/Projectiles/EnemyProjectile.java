package com.example.demo.Projectiles;

import com.example.demo.config.Config;

import java.awt.*;

public class EnemyProjectile extends Projectile {

	public EnemyProjectile(double initialXPos, double initialYPos) {
		super(Config.Projectile.ENEMY_PROJECTILE_IMAGE, Config.Projectile.IMAGE_HEIGHT
				, initialXPos, initialYPos);
	}

	@Override
	public void updatePosition() {
		moveHorizontally(Config.Projectile.ENEMY_PROJECTILE_HORIZONTAL_VELOCITY);
	}

	@Override
	public void updateActor() {
		updatePosition();
	}


}
