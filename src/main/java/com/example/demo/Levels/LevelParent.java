package com.example.demo.Levels;

import java.util.*;
import java.util.List;
import java.util.function.Consumer;

import com.example.demo.Managers.CollisionManager;
import com.example.demo.Managers.SoundManager;
import com.example.demo.Objects.GameObject;
import com.example.demo.Factories.EnemyPlaneFactory;
import com.example.demo.Objects.Planes.FighterPlane;
import com.example.demo.Objects.Planes.UserPlane;
import com.example.demo.Factories.UserPlaneFactory;
import com.example.demo.Objects.Powerups.Freeze;
import com.example.demo.Objects.Powerups.Powerup;
import com.example.demo.Objects.Powerups.Heart;
import com.example.demo.Objects.Powerups.Multishot;
import com.example.demo.config.Config;
import javafx.animation.*;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.*;
import javafx.util.Duration;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

/**
 * Superclass for level instances, handles all common level interactions
 * Includes level transitioning, scene initializing and updating, object state updating etc.
 */
public abstract class LevelParent {
	private final double screenHeight;
	private final double screenWidth;
	private final double enemyMaximumYPosition;
	private final Group root;
	private final Timeline timeline;
	private final UserPlane user;
	private final Scene scene;
	private final ImageView background;
	private final HeartBar levelView;
	private final SoundManager soundManager;

	private final List<GameObject> friendlyUnits;
	private final List<GameObject> enemyUnits;
	private final List<GameObject> userProjectiles;
	private final List<GameObject> enemyProjectiles;
	private final List<Powerup> powerups;

	private int currentNumberOfEnemies;
	private final int powerupLimit;
	private Consumer<String> levelchange;
	private final EnemyPlaneFactory enemyFactory;
	private final CollisionManager collisionManager;
	private final LevelConfiguration levelConfig;

	/**
	 * Constructs a LevelParent instance and initializes the game state.
	 *
	 * @param screenHeight The height of the screen.
	 * @param screenWidth  The width of the screen.
	 * @param levelnumber  The number of the current level.
	 */
	public LevelParent(double screenHeight, double screenWidth, int levelnumber) {
		this.root = new Group();
		this.soundManager = SoundManager.getInstance();
		this.scene = new Scene(root, screenWidth, screenHeight);
		this.timeline = new Timeline();
        this.levelConfig = new LevelConfiguration(levelnumber);
		UserPlaneFactory userFactory = new UserPlaneFactory(levelConfig.getPlayerInitialHealth());
		this.user = userFactory.createUserPlane();
		this.enemyFactory = new EnemyPlaneFactory();
		this.friendlyUnits = new ArrayList<>();
		this.enemyUnits = new ArrayList<>();
		this.powerups = new ArrayList<>();
		this.userProjectiles = new ArrayList<>();
		this.enemyProjectiles = new ArrayList<>();
		this.background = new ImageView(new Image(Objects.requireNonNull(getClass().getResource(
				Config.Level.BACKGROUND_PATH + (levelnumber) + ".jpg")).toExternalForm()));
		this.screenHeight = screenHeight;
		this.screenWidth = screenWidth;
		this.enemyMaximumYPosition = screenHeight - Config.Screen.SCREEN_HEIGHT_ADJUSTMENT;
		this.levelView = instantiateLevelView();
		this.powerupLimit = levelConfig.getPowerupLimit();
		this.currentNumberOfEnemies = 0;
		initializeTimeline();
		friendlyUnits.add(user);
		this.collisionManager = new CollisionManager(root, user, friendlyUnits, enemyUnits,
				userProjectiles, enemyProjectiles, powerups);
	}

	/**
	 * Provides access to the factory for creating enemy planes.
	 *
	 * @return The EnemyPlaneFactory instance.
	 */
	public EnemyPlaneFactory getEnemyFactory() {
		return enemyFactory;
	}


	/**
	 * Plays the specified level's background music.
	 *
	 * @param music The filename of the music to be played.
	 */
	protected void playLevelMusic(String music) {
		soundManager.playBackgroundMusic(music);
	}

	/**
	 * Abstract method to initialize friendly units, implemented by subclasses.
	 */
	protected abstract void initializeFriendlyUnits();

	/**
	 * Abstract method to check whether the game is over, implemented by subclasses.
	 */
	protected abstract void checkIfGameOver();

	/**
	 * Abstract method to spawn enemy units, implemented by subclasses.
	 */
	protected abstract void spawnEnemyUnits();

	/**
	 * Abstract method to instantiate the level's UI view.
	 *
	 * @return The HeartBar instance for the level.
	 */
	protected abstract HeartBar instantiateLevelView();

	/**
	 * Initializes the scene by setting up the background, friendly units, and level view.
	 *
	 * @return The initialized Scene instance.
	 */
	public Scene initializeScene() {
		initializeBackground(false);
		initializeFriendlyUnits();
		levelView.showHeartDisplay();
		return scene;
	}

	/**
	 * Starts the game timeline and focuses on the background for input handling.
	 */
	public void startGame() {
		background.requestFocus();
		timeline.play();
	}

	/**
	 * Sets a callback to handle level change events.
	 *
	 * @param callback A Consumer accepting the name of the next level.
	 */
	public void levelChangeStatus(Consumer<String> callback) {
		levelchange = callback;
	}

	/**
	 * Transitions to the next level after a countdown.
	 *
	 * @param levelName The name of the next level to load.
	 */
	public void goToNextLevel(String levelName) {
		if (levelchange != null) {
			timeline.stop();
			final int[] countdown = {5};
			Label countdownLabel = new Label();
			Label levelClearedText = new Label("LEVEL CLEARED! NEXT LEVEL COMMENCING IN ");
			countdownLabel.setStyle("-fx-font-family: 'Trebuchet MS'; -fx-font-weight: bold; -fx-font-size: 20;");
			levelClearedText.setStyle("-fx-font-family: 'Trebuchet MS'; -fx-font-weight: bold; -fx-font-size: 20;");

			levelClearedText.setLayoutX((screenWidth / 2 - 250));
			levelClearedText.setLayoutY(screenHeight / 2 - 50); // Center horizontally
			countdownLabel.setLayoutX(screenWidth / 2 - 50);
			countdownLabel.setLayoutY(1 + screenHeight / 2);

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
	private void resetScreen() {
		this.root.getChildren().clear();
		initializeBackground(true);

	}

	/**
	 * Updates all game components and checks for collisions, level state, and game over conditions.
	 */
	protected void updateScene() {
		updateHearts();
		updateMultishot();
		updateFreezeSpawn();
		spawnEnemyUnits();
		updateActors();
		generateEnemyFire();
		updateNumberOfEnemies();
		collisionManager.handleGameCollisions();
		removeDestroyedPowerups(powerups);
		removeAllDestroyedActors();
		updateKillCount();
		updateLevelView();
		checkIfGameOver();
	}

	/**
	 * Sets up the game timeline for periodic updates.
	 */
	private void initializeTimeline() {
		timeline.setCycleCount(Timeline.INDEFINITE);
		KeyFrame gameLoop = new KeyFrame(Duration.millis(Config.Screen.MILLISECOND_DELAY), e -> updateScene());
		timeline.getKeyFrames().add(gameLoop);
	}

	/**
	 * Configures the background image and sets up key event handlers.
	 *
	 * @param transitionedNextLevel Whether transitioning to the next level.
	 */
	private void initializeBackground(boolean transitionedNextLevel) {
		background.setFocusTraversable(true);
		background.setFitHeight(screenHeight);
		background.setFitWidth(screenWidth);
		background.setOnKeyPressed(e -> {
			KeyCode keyCode = e.getCode();
			if (keyCode == KeyCode.UP) user.moveUp();
			if (keyCode == KeyCode.DOWN) user.moveDown();
			if (keyCode == KeyCode.LEFT) user.moveLeft(); // Added horizontal movement functionality
			if (keyCode == KeyCode.RIGHT) user.moveRight();
			if (keyCode == KeyCode.SPACE && !transitionedNextLevel) fireProjectile();
		});
		background.setOnKeyReleased(e -> {
			KeyCode keyCode = e.getCode();
			if (keyCode == KeyCode.UP || keyCode == KeyCode.DOWN)
				user.stopVertical(); // split movement between horizontal and vertical
			if (keyCode == KeyCode.LEFT || keyCode == KeyCode.RIGHT) user.stopHorizontal();
		});
		root.getChildren().add(background);
	}

	/**
	 * Fires multiple projectiles for multishot powerup
	 *
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
	 * Fires a single or multiple projectiles based on the user's power-up state.
	 */
	private void fireProjectile() {
		if (user.isMultishotEnabled()) {
			List<GameObject> projectiles = user.fireMultiShot();
			fireProjectiles(projectiles);
		} else {
			GameObject projectile = user.fireProjectile();
			if (projectile != null) {
				root.getChildren().add(projectile);
				userProjectiles.add(projectile);
			}
		}
		soundManager.playShootSound();
	}

	/**
	 * Spawns a heart if below the set spawn limit and the players health has decreased
	 * Probability of heart spawning, spawn limit can be changed depending on level
	 */
	private void updateHearts() {
		if (powerups.size() < powerupLimit) {
			if (getUser().getHealth() < levelConfig.getPlayerInitialHealth()) {
				if (Math.random() < levelConfig.getHeartSpawnProbability()) {
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
	private void updateMultishot() {
		if (powerups.size() < powerupLimit) {
			if (Math.random() < levelConfig.getMultishotSpawnProbability()) {
				Multishot multiShotIcon = new Multishot(randomYposition());
				root.getChildren().add(multiShotIcon);
				powerups.add(multiShotIcon);
			}
		}
	}

	/**
	 * Creates freeze powerup if probability is met and adds it to the powerup list
	 */
	private void updateFreezeSpawn() {
		if (Math.random() < levelConfig.getFreezeSpawnProbability() && powerups.size() < powerupLimit) {
			Freeze freeze = new Freeze(randomYposition());
			root.getChildren().add(freeze);
			powerups.add(freeze);
		}

	}

	/**
	 * Generates a random Y-position for placing objects.
	 *
	 * @return A random Y-coordinate within the screen bounds.
	 */
	private double randomYposition() {
		return 100 + (Math.random() * (this.screenHeight - 200));
	}

	/**
	 * Commands enemy units to fire projectiles.
	 */
	private void generateEnemyFire() {
		enemyUnits.forEach(enemy -> spawnEnemyProjectile(((FighterPlane) enemy).fireProjectile()));
	}

	/**
	 * Adds a new projectile fired by an enemy to the scene.
	 *
	 * @param projectile The projectile to spawn.
	 */
	private void spawnEnemyProjectile(GameObject projectile) {
		if (projectile != null) {
			root.getChildren().add(projectile);
			enemyProjectiles.add(projectile);
		}
	}

	/**
	 * Updates the state of all actors in the game, including friendly units, enemy units,
	 * user projectiles, enemy projectiles, and power-ups.
	 */
	private void updateActors() {
		friendlyUnits.forEach(GameObject::updateActor);
		enemyUnits.forEach(GameObject::updateActor);
		userProjectiles.forEach(GameObject::updateActor);
		enemyProjectiles.forEach(GameObject::updateActor);
		powerups.forEach(GameObject::updateActor); // Update powerups
	}

	/**
	 * Removes all destroyed game objects from their respective lists and the game scene.
	 */
	private void removeAllDestroyedActors() {
		removeDestroyedActors(friendlyUnits);
		removeDestroyedActors(enemyUnits);
		removeDestroyedActors(userProjectiles);
		removeDestroyedActors(enemyProjectiles);
		removeDestroyedPowerups(powerups); // Destroy powerup objects
	}

	/**
	 * Removes destroyed game objects from the specified list and the game scene.
	 *
	 * @param actors The list of game objects to check and remove if destroyed.
	 */
	private void removeDestroyedActors(List<GameObject> actors) {
		List<GameObject> destroyedActors = actors.stream().filter(GameObject::isDestroyed)
				.toList();
		root.getChildren().removeAll(destroyedActors);
		actors.removeAll(destroyedActors);
	}

	/**
	 * Remove destroyed powerups from the screen, without removing them from the list of powerups
	 * This is so the powerup is still activated
	 *
	 * @param actors List of powerups
	 */
	private void removeDestroyedPowerups(List<Powerup> actors) {
		List<Powerup> destroyedActors = actors.stream().filter(Powerup::isDestroyed)
				.toList();
		root.getChildren().removeAll(destroyedActors);
		actors.removeAll(destroyedActors);
	}

	/**
	 * Updates the level view by synchronizing it with the user's current health.
	 */
	private void updateLevelView() {
		levelView.updateHearts(user.getHealth());
	}

	/**
	 * Updates the kill count of the user based on the number of destroyed enemy units.
	 */
	private void updateKillCount() {
		for (int i = 0; i < currentNumberOfEnemies - enemyUnits.size(); i++) {
			user.incrementKillCount();
		}
	}

	/**
	 * Ends the game with a win state, stopping the timeline and triggering the level change.
	 */
	protected void winGame() {
		timeline.stop();
		levelchange.accept("WIN");
	}

	/**
	 * Ends the game with a lose state, stopping the timeline, playing the game-over sound,
	 * and triggering the level change.
	 */
	protected void loseGame() {
		timeline.stop();
		levelchange.accept("LOSE");
		soundManager.playGameOverSound();
	}

	/**
	 * Retrieves the user-controlled plane in the current level.
	 *
	 * @return The user's plane instance.
	 */
	protected UserPlane getUser() {
		return user;
	}

	/**
	 * Retrieves the root node of the game scene.
	 *
	 * @return The root group of the scene.
	 */
	protected Group getRoot() {
		return root;
	}

	/**
	 * Retrieves the current number of active enemies in the level.
	 *
	 * @return The count of enemy units.
	 */
	protected int getCurrentNumberOfEnemies() {
		return enemyUnits.size();
	}

	/**
	 * Adds a new enemy unit to the game scene and the list of enemy units.
	 *
	 * @param enemy The enemy unit to add.
	 */
	protected void addEnemyUnit(GameObject enemy) {
		enemyUnits.add(enemy);
		root.getChildren().add(enemy);
	}

	/**
	 * Retrieves the maximum Y-position allowed for enemy movement on the screen.
	 *
	 * @return The maximum Y-coordinate for enemies.
	 */
	protected double getEnemyMaximumYPosition() {
		return enemyMaximumYPosition;
	}

	/**
	 * Retrieves the screen width of the game.
	 *
	 * @return The screen width.
	 */
	protected double getScreenWidth() {
		return screenWidth;
	}

	/**
	 * Checks if the user's plane is destroyed.
	 *
	 * @return True if the user's plane is destroyed; false otherwise.
	 */
	protected boolean userIsDestroyed() {
		return user.isDestroyed();
	}

	/**
	 * Updates the count of current enemy units.
	 */
	private void updateNumberOfEnemies() {
		currentNumberOfEnemies = enemyUnits.size();
	}
}






