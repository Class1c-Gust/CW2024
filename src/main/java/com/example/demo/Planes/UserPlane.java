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
	private static final double X_LEFT_BOUND = 0.0;
	private static final double X_RIGHT_BOUND = 900.0;
	private static final double INITIAL_X_POSITION = 5.0;
	private static final double INITIAL_Y_POSITION = 300.0;
	private static final int IMAGE_HEIGHT = 150;
	private static final double frameDelay = 0.4;
	private static final int VERTICAL_VELOCITY = (int)(8*frameDelay);
	private static final int HORIZONTAL_VELOCITY = (int)(8*frameDelay);
	private static final int PROJECTILE_X_POSITION = 110;
	private static final int PROJECTILE_Y_POSITION_OFFSET = 20;
	private int VerticalVelocityMultiplier;
	private int HorizontalVelocityMultiplier;
	private boolean multiShotEnabled = false;
	private int numberOfKills;
	ProjectileFactory projectileFactory = new ProjectileFactory(IMAGE_NAME, IMAGE_HEIGHT);
	private long lastTimeFired = 0; // Track the last time the user fired a projectile
	private static final long PROJECTILE_COOLDOWN = 200;

	public UserPlane(int initialHealth) {
		super(IMAGE_NAME, IMAGE_HEIGHT, INITIAL_X_POSITION, INITIAL_Y_POSITION, initialHealth);
		VerticalVelocityMultiplier = 0;
		this.HorizontalVelocityMultiplier = 0;
	}

	/**
	 * Updates position of the plane vertically and horizontally on the screen
	 */
	@Override
	public void updatePosition() {
		if (isMovingVertically()) {
			double overallVerticalVelocity = VerticalVelocityMultiplier * VERTICAL_VELOCITY;
			double yPosition = getLayoutY() + getTranslateY() + (overallVerticalVelocity);
			if (yPosition >= Y_UPPER_BOUND && yPosition <= Y_LOWER_BOUND) {
				moveVertically(overallVerticalVelocity);
			}
		}

		// Update horizontal position
		if (isMovingHorizontally()) {
			double overallHorizontalVelocity = HorizontalVelocityMultiplier * HORIZONTAL_VELOCITY;
			double xPosition = getLayoutX() + getTranslateX() + (overallHorizontalVelocity);
			if (xPosition >= X_LEFT_BOUND && xPosition <= X_RIGHT_BOUND) {
				moveHorizontally(overallHorizontalVelocity);
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

	private boolean isMovingVertically() {
		return VerticalVelocityMultiplier != 0;
	}

	private boolean isMovingHorizontally() {
		return HorizontalVelocityMultiplier != 0;
	}
	/**
	 * moveUp and moveDown Modified - to prevent movement if the plane is disabled
	 */
	public void moveUp() {
		if (!getDisabledStatus()) VerticalVelocityMultiplier = -1;
	}

	public void moveDown() {
		if (!getDisabledStatus())
			VerticalVelocityMultiplier = 1;
	}

	public void moveRight() {
		if (!getDisabledStatus()) HorizontalVelocityMultiplier = 1;
	}

	public void moveLeft() {
		if (!getDisabledStatus())
			HorizontalVelocityMultiplier = -1;
	}

	public void stopVertical() {
		VerticalVelocityMultiplier = 0;
	}

	public void stopHorizontal(){
		HorizontalVelocityMultiplier = 0;
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
