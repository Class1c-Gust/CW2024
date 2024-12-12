package com.example.demo.Projectiles;

import com.example.demo.config.Config;

public class UserProjectile extends Projectile {

	public UserProjectile(double initialXPos, double initialYPos) {
		super(Config.Projectile.USER_PROJECTILE_IMAGE, Config.Projectile.USER_PROJECTILE_IMAGE_HEIGHT
				, initialXPos, initialYPos);
	}

	@Override
	public void updatePosition() {
		moveHorizontally(Config.Projectile.USER_PROJECTILE_HORIZONTAL_VELOCITY);
	}
	
	@Override
	public void updateActor() {
		updatePosition();
	}
	
}
