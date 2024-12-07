package com.example.demo.Planes;

import com.example.demo.GameObject;
import com.example.demo.Projectiles.ProjectileFactory;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class UserPlane extends FighterPlane {

	private static final String IMAGE_NAME = "userplane.png";
	private static final double Y_UPPER_BOUND = -40;
	private static final double Y_LOWER_BOUND = 600.0;
	private static final double INITIAL_X_POSITION = 5.0;
	private static final double INITIAL_Y_POSITION = 300.0;
	private static final int IMAGE_HEIGHT = 150;
	private static final int VERTICAL_VELOCITY = 8;
	private static final int PROJECTILE_X_POSITION = 110;
	private static final int PROJECTILE_Y_POSITION_OFFSET = 20;
	private int velocityMultiplier;
	private boolean multiShotEnabled = false;
	private int numberOfKills;
	ProjectileFactory projectileFactory = new ProjectileFactory(IMAGE_NAME, IMAGE_HEIGHT);
	private long lastTimeFired = 0; // Track the last time the user fired a projectile
	private static final long PROJECTILE_COOLDOWN = 200;

	public UserPlane(int initialHealth) {
		super(IMAGE_NAME, IMAGE_HEIGHT, INITIAL_X_POSITION, INITIAL_Y_POSITION, initialHealth);
		velocityMultiplier = 0;
	}
	
	@Override
	public void updatePosition() {
		if (isMoving()) {
			double initialTranslateY = getTranslateY();
			this.moveVertically(VERTICAL_VELOCITY * velocityMultiplier);
			double newPosition = getLayoutY() + getTranslateY();
			if (newPosition < Y_UPPER_BOUND || newPosition > Y_LOWER_BOUND) {
				this.setTranslateY(initialTranslateY);
			}
		}
	}
	
	@Override
	public void updateActor() {
		updatePosition();
	}

	@Override
	public GameObject fireProjectile() {
		long currentTime = System.currentTimeMillis();
		if (currentTime - lastTimeFired >= PROJECTILE_COOLDOWN){
			lastTimeFired = currentTime;
			return projectileFactory.createUserProjectile(PROJECTILE_X_POSITION, getProjectileYPosition(PROJECTILE_Y_POSITION_OFFSET));
		}
		return null;
	}

	private boolean isMoving() {
		return velocityMultiplier != 0;
	}
	/**
	 * moveUp and moveDown Modified - to prevent movement if the plane is disabled
	 */
	public void moveUp() {
		if (!getDisabledStatus()) velocityMultiplier = -1;
	}

	public void moveDown() {
		if (!getDisabledStatus())
			velocityMultiplier = 1;
	}

	public void stop() {
		velocityMultiplier = 0;
	}

	public int getNumberOfKills() {
		return numberOfKills;
	}

	public void incrementKillCount() {
		numberOfKills++;
	}

	/**
	 * New function to fire three shots in one if cooldown isnt active and plane isnt disabled
	 * @return list of projectiles or empty list if disabled or on cooldown
	 */
	public List<GameObject> fireMultiShot() {
		if (!getDisabledStatus()){
			List<GameObject> projectiles = new ArrayList<>();
			long currentTime = System.currentTimeMillis();
			if (currentTime - lastTimeFired >= PROJECTILE_COOLDOWN) {
				lastTimeFired = currentTime;
				if (multiShotEnabled) {
					double yPos = getProjectileYPosition(PROJECTILE_Y_POSITION_OFFSET);
					projectiles.add(projectileFactory.createUserProjectile(PROJECTILE_X_POSITION, yPos - 20));
					projectiles.add(projectileFactory.createUserProjectile(PROJECTILE_X_POSITION, yPos));
					projectiles.add(projectileFactory.createUserProjectile(PROJECTILE_X_POSITION, yPos + 20));
				} else {
					projectiles.add(fireProjectile());
				}
				return projectiles;
			}
			return Collections.emptyList();

		}
		return Collections.emptyList();
	}

	/**
	 * New function - enables multishot for a period of 10 seconds
	 */
	public void enableMultiShot() {
		this.multiShotEnabled = true;

		// Schedule deactivation after 10 seconds
		Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(10), e -> this.multiShotEnabled = false));
		timeline.setCycleCount(1);
		timeline.play();
	}

	/**
	 * New function - Gets status of multishot
	 * @return multishot boolean
	 */
	public boolean isMultishotEnabled(){
		return multiShotEnabled;
	}

}
