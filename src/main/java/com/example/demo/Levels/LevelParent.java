package com.example.demo.Levels;

import java.awt.*;
import java.util.*;
import java.util.List;
import java.util.function.Consumer;
import com.example.demo.*;
import com.example.demo.Planes.EnemyPlaneFactory;
import com.example.demo.Planes.FighterPlane;
import com.example.demo.Planes.UserPlane;
import com.example.demo.Planes.UserPlaneFactory;
import com.example.demo.Powerups.Freeze;
import com.example.demo.Powerups.Powerup;
import com.example.demo.Powerups.Heart;
import com.example.demo.Powerups.Multishot;
import javafx.animation.*;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.*;
import javafx.scene.image.Image;
import javafx.scene.input.*;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Font;
import javafx.util.Duration;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;

public abstract class LevelParent {

	private static final double SCREEN_HEIGHT_ADJUSTMENT = 150;
	private static final double MILLISECOND_DELAY = 16.67;
	private final double screenHeight;
	private final double screenWidth;
	private final double enemyMaximumYPosition;

	private final Group root;
	private final Timeline timeline;
	private final UserPlane user;
	private final Scene scene;
	private final ImageView background;

	private final List<GameObject> friendlyUnits;
	private final List<GameObject> enemyUnits;
	private final List<GameObject> userProjectiles;
	private final List<GameObject> enemyProjectiles;
	private final List<Powerup> powerups;
	
	private int currentNumberOfEnemies;
	private final int initialPlayerHealth;
	private final double heartSpawnProbability;
	private final double freezeSpawnProbability;
	private final double multishotSpawnProbability;
	private final int powerupLimit;
	private final int weaponSpawnLimit;
	private final LevelView levelView;
	private Consumer<String> levelchange;
	private final EnemyPlaneFactory enemyFactory;

//	private final ProjectileFactory projectileFactory;

	public LevelParent(String backgroundImageName, double screenHeight, double screenWidth, int playerInitialHealth, int powerupLimit,
					   double heartSpawnProbability, double freezeSpawnProbability) {
		this.root = new Group();
		this.scene = new Scene(root, screenWidth, screenHeight);
		this.timeline = new Timeline();
		UserPlaneFactory userFactory = new UserPlaneFactory(playerInitialHealth);
		this.user = userFactory.createUserPlane();
		this.enemyFactory = new EnemyPlaneFactory();
//		this.projectileFactory = new ProjectileFactory(backgroundImageName, screenHeight);
		this.friendlyUnits = new ArrayList<>();
		this.enemyUnits = new ArrayList<>();
		this.powerups = new ArrayList<>();
		this.userProjectiles = new ArrayList<>();
		this.enemyProjectiles = new ArrayList<>();
		this.background = new ImageView(new Image(Objects.requireNonNull(getClass().getResource(backgroundImageName)).toExternalForm()));
		this.screenHeight = screenHeight;
		this.screenWidth = screenWidth;
		this.enemyMaximumYPosition = screenHeight - SCREEN_HEIGHT_ADJUSTMENT;
		this.levelView = instantiateLevelView();
		this.initialPlayerHealth = playerInitialHealth;
		this.powerupLimit = powerupLimit;
		this.heartSpawnProbability = heartSpawnProbability;
		this.currentNumberOfEnemies = 0;
		this.freezeSpawnProbability = freezeSpawnProbability;
		this.multishotSpawnProbability = .001;
		this.weaponSpawnLimit = 3;
		initializeTimeline();
		friendlyUnits.add(user);
	}

	/**
	 * Getter method
	 * @return the enemyFactory instance
	 */
	public EnemyPlaneFactory getEnemyFactory() {
		return enemyFactory;
	}

	protected abstract void initializeFriendlyUnits();

	protected abstract void checkIfGameOver();

	protected abstract void spawnEnemyUnits();

	protected abstract LevelView instantiateLevelView();

	public Scene initializeScene() {
		initializeBackground();
		initializeFriendlyUnits();
		levelView.showHeartDisplay();
		return scene;
	}


	public void startGame() {
		background.requestFocus();
		timeline.play();
	}
	public void levelChangeStatus(Consumer<String> callback) {
		levelchange = callback;
	}
	public void goToNextLevel(String levelName) {
		if (levelchange != null){
			timeline.stop();
			final int[] countdown = {5};
			Label countdownLabel = new Label();
			Label levelClearedText = new Label("LEVEL CLEARED! NEXT LEVEL COMMENCING IN ");
			countdownLabel.setStyle("-fx-font-family: 'Trebuchet MS'; -fx-font-weight: bold; -fx-font-size: 20;");
			levelClearedText.setStyle("-fx-font-family: 'Trebuchet MS'; -fx-font-weight: bold; -fx-font-size: 20;");

			levelClearedText.setLayoutX((screenWidth / 2 - 250));
			levelClearedText.setLayoutY(screenHeight / 2 - 50); // Center horizontally
			countdownLabel.setLayoutX(screenWidth/2 - 50);
			countdownLabel.setLayoutY(5 + screenHeight/2);

			resetScreen(); // remove all screen elements
			root.getChildren().addAll(levelClearedText, countdownLabel); // add labels to screen

			Timeline mytimeline = new Timeline( // Displays countdown from 5
					new KeyFrame(Duration.seconds(1), event -> {
						countdown[0]--;
						countdownLabel.setText(String.valueOf(countdown[0]));
						if (countdown[0] <= 0) {
							countdownLabel.setText("Time's Up!");
						}
					})
			);
			mytimeline.setCycleCount(5); // 5 seconds countdown
			mytimeline.setOnFinished(event -> {
				levelchange.accept(levelName); // change level after countdown
			});
			mytimeline.play();
		}
	}

	/**
	 * Remove all elements from the scene and reinitialise the background image
	 */
	private void resetScreen(){
		this.root.getChildren().clear();
		initializeBackground();

	}

	protected void updateScene() {
		updateHearts();
		updateMultishot();
		updateFreezeSpawn();
		spawnEnemyUnits();
		updateActors();
		generateEnemyFire();
		updateNumberOfEnemies();
		handleEnemyPenetration();
		handleUserProjectileCollisions();
		handleEnemyProjectileCollisions();
		handlePowerupCollisions();
		handlePlaneCollisions();
		removeAllDestroyedActors();
		updateKillCount();
		updateLevelView();
		checkIfGameOver();
	}

	private void initializeTimeline() {
		timeline.setCycleCount(Timeline.INDEFINITE);
		KeyFrame gameLoop = new KeyFrame(Duration.millis(MILLISECOND_DELAY), e -> updateScene());
		timeline.getKeyFrames().add(gameLoop);
	}

	private void initializeBackground() {
		background.setFocusTraversable(true);
		background.setFitHeight(screenHeight);
		background.setFitWidth(screenWidth);
		background.setOnKeyPressed(e -> {
            KeyCode kc = e.getCode();
            if (kc == KeyCode.UP) user.moveUp();
            if (kc == KeyCode.DOWN) user.moveDown();
			if (kc == KeyCode.LEFT) user.moveLeft(); // Added horizontal movement functionality
			if (kc == KeyCode.RIGHT) user.moveRight();
            if (kc == KeyCode.SPACE) fireProjectile();
        });
		background.setOnKeyReleased(e -> {
            KeyCode kc = e.getCode();
            if (kc == KeyCode.UP || kc == KeyCode.DOWN) user.stopVertical(); // split movement between horizontal and vertical
			if (kc == KeyCode.LEFT || kc == KeyCode.RIGHT) user.stopHorizontal();
        });
		root.getChildren().add(background);
	}

	/**
	 * Fires multiple projectiles for multishot powerup
	 * @param projectiles List of projectiles to be fired
	 */
	private void fireProjectiles(List<GameObject> projectiles) {
		if (projectiles == null || projectiles.isEmpty()) return;

		for (GameObject projectile : projectiles) {
			root.getChildren().add(projectile);
			userProjectiles.add(projectile);
		}
	}

	/**
	 * Modified to fire multishot if enabled
	 */
	private void fireProjectile() {
		if (user.isMultishotEnabled()){
			List<GameObject> projectiles = user.fireMultiShot();
			fireProjectiles(projectiles);
		}
		else {
			GameObject projectile = user.fireProjectile();
			if (projectile != null) {
				root.getChildren().add(projectile);
				userProjectiles.add(projectile);
			}
		}
	}
	/**
	 * Spawns a heart if below the set spawn limit and the players health has decreased
	 * Probability of heart spawning, spawn limit can be changed depending on level
	 */
	private void updateHearts(){
		if (powerups.size() < powerupLimit){
			if (getUser().getHealth() < initialPlayerHealth) {
				if (Math.random() < heartSpawnProbability) {
					Heart heart = new Heart(randomYposition());
					root.getChildren().add(heart);
					powerups.add(heart);
//					heartsSpawned++;
				}
			}
		}
	}

	/**
	 * Creates multishot powerup if probability is met and adds it to the powerup list
	 */
	private void updateMultishot(){
		if (powerups.size() < powerupLimit){
			if (Math.random() < multishotSpawnProbability){
				Multishot multiShotIcon = new Multishot(randomYposition());
				root.getChildren().add(multiShotIcon);
				powerups.add(multiShotIcon);
			}
		}
	}

	/**
	 * Creates freeze powerup if probability is met and adds it to the powerup list
	 */
	private void updateFreezeSpawn(){
		if (Math.random() < freezeSpawnProbability && powerups.size() < weaponSpawnLimit){
			Freeze freeze = new Freeze(randomYposition());
			root.getChildren().add(freeze);
			powerups.add(freeze);
			}

	}

	private double randomYposition(){return (60 + ((Math.random() * this.screenHeight-100)));}

	private void generateEnemyFire() {
		enemyUnits.forEach(enemy -> spawnEnemyProjectile(((FighterPlane) enemy).fireProjectile()));
	}

	private void spawnEnemyProjectile(GameObject projectile) {
		if (projectile != null) {
			root.getChildren().add(projectile);
			enemyProjectiles.add(projectile);
		}
	}

	private void updateActors() {
		friendlyUnits.forEach(GameObject::updateActor);
		enemyUnits.forEach(GameObject::updateActor);
		userProjectiles.forEach(GameObject::updateActor);
		enemyProjectiles.forEach(GameObject::updateActor);
		powerups.forEach(GameObject:: updateActor); // Update powerups
	}

	private void removeAllDestroyedActors() {
		removeDestroyedActors(friendlyUnits);
		removeDestroyedActors(enemyUnits);
		removeDestroyedActors(userProjectiles);
		removeDestroyedActors(enemyProjectiles);
		removeDestroyedPowerups(powerups); // Destroy powerup objects
	}

	private void removeDestroyedActors(List<GameObject> actors) {
		List<GameObject> destroyedActors = actors.stream().filter(GameObject::isDestroyed)
				.toList();
		root.getChildren().removeAll(destroyedActors);
		actors.removeAll(destroyedActors);
	}

	/**
	 * Remove destroyed powerups from the screen, without removing them from the list of powerups
	 * This is so the powerup is still activated
	 * @param actors List of powerups
	 */
	private void removeDestroyedPowerups(List<Powerup> actors){
		List<Powerup> destroyedActors = actors.stream().filter(Powerup::isDestroyed)
				.toList();
		root.getChildren().removeAll(destroyedActors);
//		actors.removeAll(destroyedActors);
	}

	private void handlePlaneCollisions() {
		handleCollisions(friendlyUnits, enemyUnits);
	}

	private void handleUserProjectileCollisions() {
		handleCollisions(userProjectiles, enemyUnits);
	}

	/**
	 * Handles powerup icon collisions with user plane
	 */
	public void handlePowerupCollisions(){
		for (Powerup powerup : powerups){
			if (powerup.getExactBounds().intersects(user.getExactBounds())){
				powerup.activatePower(root, user);
				powerup.destroy(); // destroy powerup icon after activated
			}
			else{
				for (GameObject projectile : userProjectiles){
					if (powerup.getExactBounds().intersects(projectile.getExactBounds()) && powerup.isShootable()) {
						powerup.activatePower(root, user);
						powerup.destroy();
					}
				}
			}
		}
	}

	private void handleEnemyProjectileCollisions() {
		handleCollisions(enemyProjectiles, friendlyUnits);
	}

	private void handleCollisions(List<GameObject> actors1,
			List<GameObject> actors2) {
		for (GameObject actor : actors2) {
			for (GameObject otherActor : actors1) {
				if (actor.getExactBounds().intersects(otherActor.getExactBounds())) {
					actor.takeDamage();
					otherActor.takeDamage();
				}
			}
		}
	}

	private void handleEnemyPenetration() {
		for (GameObject enemy : enemyUnits) {
			if (enemyHasPenetratedDefenses(enemy)) {
				user.takeDamage();
				enemy.destroy();
			}
		}
	}

	private void updateLevelView() {
		levelView.updateHearts(user.getHealth());
	}

	private void updateKillCount() {
		for (int i = 0; i < currentNumberOfEnemies - enemyUnits.size(); i++) {
			user.incrementKillCount();
		}
	}

	private boolean enemyHasPenetratedDefenses(GameObject enemy) {
		return Math.abs(enemy.getTranslateX()) > screenWidth;
	}

	protected void winGame() {
		timeline.stop();
		levelView.showWinImage();
	}

	protected void loseGame() {
		timeline.stop();
		levelView.showGameOverImage();
	}

	protected UserPlane getUser() {
		return user;
	}

	protected Group getRoot() {
		return root;
	}

	protected int getCurrentNumberOfEnemies() {
		return enemyUnits.size();
	}

	protected void addEnemyUnit(GameObject enemy) {
		enemyUnits.add(enemy);
		root.getChildren().add(enemy);
	}

	protected double getEnemyMaximumYPosition() {
		return enemyMaximumYPosition;
	}

	protected double getScreenWidth() {
		return screenWidth;
	}

	protected boolean userIsDestroyed() {
		return user.isDestroyed();
	}

	private void updateNumberOfEnemies() {
		currentNumberOfEnemies = enemyUnits.size();
	}

}
