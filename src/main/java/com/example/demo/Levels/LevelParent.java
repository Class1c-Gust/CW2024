package com.example.demo.Levels;

import java.util.*;
import java.util.function.Consumer;
import com.example.demo.*;
import com.example.demo.Planes.EnemyPlaneFactory;
import com.example.demo.Planes.FighterPlane;
import com.example.demo.Planes.UserPlane;
import com.example.demo.Planes.UserPlaneFactory;
import com.example.demo.Projectiles.Heart;
import javafx.animation.*;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.*;
import javafx.scene.input.*;
import javafx.util.Duration;

public abstract class LevelParent {

	private static final double SCREEN_HEIGHT_ADJUSTMENT = 150;
	private static final int MILLISECOND_DELAY = 50;
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
	private final List<GameObject> hearts;
	
	private int currentNumberOfEnemies;
	private int initalPlayerHealth;
	private double heartSpawnProbability = .002;
	private int heartSpawnLimit;
	private int heartsSpawned;
	private final LevelView levelView;
	private Consumer<String> levelchange;
	private final EnemyPlaneFactory enemyFactory;

//	private final ProjectileFactory projectileFactory;

	public LevelParent(String backgroundImageName, double screenHeight, double screenWidth, int playerInitialHealth, int heartSpawnLimit,
					   double heartSpawnProbability) {
		this.root = new Group();
		this.scene = new Scene(root, screenWidth, screenHeight);
		this.timeline = new Timeline();
		UserPlaneFactory userFactory = new UserPlaneFactory(playerInitialHealth);
		this.user = userFactory.createUserPlane();
		this.enemyFactory = new EnemyPlaneFactory();
//		this.projectileFactory = new ProjectileFactory(backgroundImageName, screenHeight);
		this.friendlyUnits = new ArrayList<>();
		this.enemyUnits = new ArrayList<>();
		this.hearts = new ArrayList<>();
		this.userProjectiles = new ArrayList<>();
		this.enemyProjectiles = new ArrayList<>();
		this.background = new ImageView(new Image(Objects.requireNonNull(getClass().getResource(backgroundImageName)).toExternalForm()));
		this.screenHeight = screenHeight;
		this.screenWidth = screenWidth;
		this.enemyMaximumYPosition = screenHeight - SCREEN_HEIGHT_ADJUSTMENT;
		this.levelView = instantiateLevelView();
		this.initalPlayerHealth = playerInitialHealth;
		this.heartSpawnLimit = heartSpawnLimit;
		this.heartSpawnProbability = heartSpawnProbability;
		this.heartsSpawned = 0;
		this.currentNumberOfEnemies = 0;
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
			levelchange.accept(levelName);
		}
	}

	protected void updateScene() {
		spawnHeart();
		spawnEnemyUnits();
		updateActors();
		generateEnemyFire();
		updateNumberOfEnemies();
		handleEnemyPenetration();
		handleUserProjectileCollisions();
		handleEnemyProjectileCollisions();
		handlePlaneCollisions();
		handleHeartCollisions();
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
            if (kc == KeyCode.SPACE) fireProjectile();
        });
		background.setOnKeyReleased(e -> {
            KeyCode kc = e.getCode();
            if (kc == KeyCode.UP || kc == KeyCode.DOWN) user.stop();
        });
		root.getChildren().add(background);
	}

	private void fireProjectile() {
		GameObject projectile = user.fireProjectile();
		root.getChildren().add(projectile);
		userProjectiles.add(projectile);
	}

	/**
	 * Creates a new heart instance and adds it to the scene
	 */
	public void addHeart(){
		Heart heart = new Heart((40 + ((Math.random() * this.screenHeight-100))));
		root.getChildren().add(heart);
		hearts.add(heart);
	}
	/**
	 * Spawns a heart if below the set spawn limit and the players health has decreased
	 * Probability of heart spawning, spawn limit can be changed depending on level
	 */
	protected void spawnHeart(){
		if (heartsSpawned < heartSpawnLimit){
			if (getUser().getHealth() < initalPlayerHealth) {
				if (Math.random() < heartSpawnProbability) {
					addHeart();
					heartsSpawned++;
				}
			}
		}
	}

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
		hearts.forEach(GameObject::updateActor);
	}

	private void removeAllDestroyedActors() {
		removeDestroyedActors(friendlyUnits);
		removeDestroyedActors(enemyUnits);
		removeDestroyedActors(userProjectiles);
		removeDestroyedActors(enemyProjectiles);
		removeDestroyedActors(hearts);
	}

	private void removeDestroyedActors(List<GameObject> actors) {
		List<GameObject> destroyedActors = actors.stream().filter(GameObject::isDestroyed)
				.toList();
		root.getChildren().removeAll(destroyedActors);
		actors.removeAll(destroyedActors);
	}

	private void handlePlaneCollisions() {
		handleCollisions(friendlyUnits, enemyUnits);
	}

	private void handleUserProjectileCollisions() {
		handleCollisions(userProjectiles, enemyUnits);
	}

	/**
	 * Handles heart collisions with user plane, adding health
	 */
	public void handleHeartCollisions(){
		for (GameObject heart : hearts){
			if (heart.getExactBounds().intersects(user.getExactBounds())){
				heart.takeDamage();
				user.addHealth();
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
