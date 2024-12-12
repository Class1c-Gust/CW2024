package com.example.demo.Projectiles;

import com.example.demo.config.Config;

public class BossProjectile extends Projectile {

	public BossProjectile(double initialYPos) {
		super(Config.Projectile.BOSS_PROJECTILE_IMAGE, Config.Projectile.IMAGE_HEIGHT,
				Config.Projectile.INITIAL_X_POSITION, initialYPos);
	}

	@Override
	public void updatePosition() {
		moveHorizontally(Config.Projectile.BOSS_PROJECTILE_HORIZONTAL_VELOCITY);
	}
	
	@Override
	public void updateActor() {
		updatePosition();
	}
	
}
