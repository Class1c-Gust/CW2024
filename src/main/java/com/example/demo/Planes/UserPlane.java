package com.example.demo.Planes;

import com.example.demo.GameObject;
import com.example.demo.Projectiles.ProjectileFactory;
import com.example.demo.config.Config;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class UserPlane extends FighterPlane {

	private int VerticalVelocityMultiplier;
	private int HorizontalVelocityMultiplier;
	private boolean multiShotEnabled = false;
	private int numberOfKills;
	ProjectileFactory projectileFactory = new ProjectileFactory(Config.Player.IMAGE_NAME, Config.Player.IMAGE_HEIGHT);
	private long lastTimeFired = 0; // Track the last time the user fired a projectile

	public UserPlane(int initialHealth) {
		super(Config.Player.IMAGE_NAME, Config.Player.IMAGE_HEIGHT,
		      Config.Player.INITIAL_X_POSITION, Config.Player.INITIAL_Y_POSITION,
		      initialHealth);
		VerticalVelocityMultiplier = 0;
		this.HorizontalVelocityMultiplier = 0;
	}


	/**
	 * Updates position of the plane vertically and horizontally on the screen
	 */
	@Override
	public void updatePosition() {
		if (isMovingVertically()) {
			double overallVerticalVelocity = VerticalVelocityMultiplier * Config.Player.VERTICAL_VELOCITY;
			double yPosition = getLayoutY() + getTranslateY() + (overallVerticalVelocity);
			if (yPosition >= Config.Player.Y_UPPER_BOUND && yPosition <= Config.Player.Y_LOWER_BOUND) {
				moveVertically(overallVerticalVelocity);
			}
		}

		// Update horizontal position
		if (isMovingHorizontally()) {
			double overallHorizontalVelocity = HorizontalVelocityMultiplier * Config.Player.HORIZONTAL_VELOCITY;
			double xPosition = getLayoutX() + getTranslateX() + (overallHorizontalVelocity);
			if (xPosition >= Config.Player.X_LEFT_BOUND && xPosition <= Config.Player.X_RIGHT_BOUND) {
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
		if (currentTime - lastTimeFired >= Config.Player.PROJECTILE_COOLDOWN){
			lastTimeFired = currentTime;
			return projectileFactory.createUserProjectile(getLayoutX() + getTranslateX(),
					getProjectileYPosition(Config.Player.PROJECTILE_Y_POSITION_OFFSET));
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
			if (currentTime - lastTimeFired >= Config.Player.PROJECTILE_COOLDOWN) {
				lastTimeFired = currentTime;
				if (multiShotEnabled) {
					double yPos = getProjectileYPosition(Config.Player.PROJECTILE_Y_POSITION_OFFSET);
					projectiles.add(projectileFactory.createUserProjectile(getLayoutX() + getTranslateX(), yPos - 20));
					projectiles.add(projectileFactory.createUserProjectile(getLayoutX() + getTranslateX(), yPos));
					projectiles.add(projectileFactory.createUserProjectile(getLayoutX() + getTranslateX(), yPos + 20));
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
		Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(Config.Powerup.MULTISHOT_DURATION), e -> this.multiShotEnabled = false));
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
