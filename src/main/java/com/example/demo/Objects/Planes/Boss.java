package com.example.demo.Objects.Planes;

import com.example.demo.Objects.GameObject;
import com.example.demo.Objects.Projectiles.BossMissile;
import com.example.demo.Objects.Projectiles.BossProjectile;
import com.example.demo.Managers.SoundManager;
import com.example.demo.config.Config;
import javafx.scene.effect.DropShadow;
import javafx.scene.paint.Color;

import java.util.*;

/**
 * Represents a boss enemy in the game. The boss has special behaviors such as shield activation,
 * missile firing, and a unique movement pattern. It extends the FighterPlane class and implements
 * additional logic for its specific actions.
 */
public class Boss extends FighterPlane {
    private final BossConfiguration config; // Configuration object for the boss
    private final List<Integer> movePattern; // List of possible movement directions for the boss
    private boolean isShielded; // Indicates if the boss is shielded
    private int consecutiveMovesInSameDirection; // Counter for consecutive moves in the same direction
    private int indexOfCurrentMove; // Index of the current move in the pattern
    private int framesWithShieldActivated; // Number of frames the shield has been activated
    private int currentHealth; // Current health of the boss
    private int missilesFired; // Counter for missiles fired by the boss
    private final DropShadow shieldGlowEffect; // Visual effect for the boss's shield
    private final SoundManager soundManager; // SoundManager instance for playing sounds

    /**
     * Constructor to initialize the boss with specific configuration and level view.
     *
     * @param config The configuration object for the boss.
     * @param levelNumber The current level number.
     */
    public Boss(BossConfiguration config, int levelNumber) {
        super(("bossplane" + (levelNumber / 3) + ".png"), Config.Boss.IMAGE_HEIGHT, Config.Boss.INITIAL_X_POSITION,
                Config.Boss.INITIAL_Y_POSITION, config.getMaxHealth());
        this.setFitWidth(300);
        this.setFitHeight(200);
        this.config = config;
        // The level view containing the boss
        movePattern = new ArrayList<>();
        consecutiveMovesInSameDirection = 0;
        indexOfCurrentMove = 0;
        framesWithShieldActivated = 0;
        isShielded = false;
        this.currentHealth = config.getMaxHealth();
        // Indicates if the boss has fired a missile
        this.missilesFired = 0;

        // Initialize the shield effect with a red glow
        shieldGlowEffect = new DropShadow();
        shieldGlowEffect.setColor(Color.RED);
        shieldGlowEffect.setRadius(30);
        shieldGlowEffect.setSpread(0.5);

        // Get the SoundManager instance
        this.soundManager = SoundManager.getInstance();

        // Initialize the boss's movement pattern
        initializeMovePattern();
    }

    /**
     * Updates the boss's position based on its movement pattern.
     */
    @Override
    public void updatePosition() {
        double initialTranslateY = getTranslateY();
        moveVertically(getNextMove()); // Move the boss vertically according to the next move
        // Current Y position of the boss
        double currentPosition = getLayoutY() + getTranslateY();

        // Ensure the boss stays within vertical bounds
        if (currentPosition < Config.Player.Y_UPPER_BOUND || currentPosition > Config.Player.Y_LOWER_BOUND) {
            setTranslateY(initialTranslateY);
        }
    }

    /**
     * Updates the boss by updating its position and shield status.
     */
    @Override
    public void updateActor() {
        updatePosition();
        updateShield(); // Update the shield if necessary
    }

    /**
     * Fires a projectile (either a missile or a regular projectile) depending on conditions.
     *
     * @return The projectile fired by the boss (either BossMissile or BossProjectile), or null if no projectile is fired.
     */
    @Override
    public GameObject fireProjectile() {
        if (missileEnabled()) {
            firedMissile(); // Track missile firing
            soundManager.playMissileFiredSound(); // Play missile sound
            return new BossMissile(getProjectileInitialPosition()); // Return a new missile
        }
        else if (bossFiresInCurrentFrame()) {
            soundManager.playBossSound(); // Play boss sound
            return new BossProjectile(getProjectileInitialPosition()); // Return a regular projectile
        }
        else {
            return null; // No projectile fired
        }
    }

    /**
     * Increments the missile count when a missile is fired.
     */
    private void firedMissile() {
        missilesFired += 1;
    }

    /**
     * Determines if the boss is enabled to fire a missile based on probability and missile limit.
     *
     * @return true if the missile can be fired, false otherwise.
     */
    private boolean missileEnabled() {
        return (Math.random() < config.getMissileProbability()) && missilesFired < config.getMissilesLimit();
    }

    /**
     * Reduces the boss's health if it is not shielded. This method is called when the boss takes damage.
     */
    @Override
    public void takeDamage() {
        if (!isShielded) {
            currentHealth--;
            super.takeDamage(); // Call the parent method to handle additional damage logic
        }
    }

    /**
     * Initializes the movement pattern for the boss based on its configuration.
     */
    private void initializeMovePattern() {
        // Add moves to the pattern (up, down, or idle)
        for (int i = 0; i < config.getMoveFrequency(); i++) {
            movePattern.add((int)config.getVelocity());
            movePattern.add((int)-config.getVelocity());
            movePattern.add(0);
        }
        // Shuffle the movement pattern for more variability
        Collections.shuffle(movePattern);
    }

    /**
     * Updates the shield status of the boss (activates or deactivates the shield).
     */
    private void updateShield() {
        if (isShielded) {
            framesWithShieldActivated++; // Increase the shield active time
        } else if (shieldShouldBeActivated()) {
            activateShield(); // Activate the shield if needed
        }
        if (shieldExhausted()) {
            deactivateShield(); // Deactivate the shield after it has been active for too long
        }
    }

    /**
     * Returns the next move in the boss's movement pattern.
     * If the boss has made 10 consecutive moves in the same direction, the pattern is shuffled.
     *
     * @return The next move (positive for down, negative for up, 0 for idle).
     */
    private int getNextMove() {
        int currentMove = movePattern.get(indexOfCurrentMove);
        consecutiveMovesInSameDirection++;

        // Shuffle the move pattern after 10 consecutive moves in the same direction
        if (consecutiveMovesInSameDirection == Config.Boss.MAX_CONSECUTIVE_MOVES_SAME_DIRECTION) {
            Collections.shuffle(movePattern);
            consecutiveMovesInSameDirection = 0;
            indexOfCurrentMove++;
        }

        // Reset to the first move if the end of the pattern is reached
        if (indexOfCurrentMove == movePattern.size()) {
            indexOfCurrentMove = 0;
        }
        return currentMove;
    }

    /**
     * Determines if the boss should fire a projectile in the current frame.
     *
     * @return true if the boss fires a projectile, false otherwise.
     */
    private boolean bossFiresInCurrentFrame() {
        return Math.random() < config.getFireRate();
    }

    /**
     * Calculates the initial position for firing a projectile.
     *
     * @return The Y-coordinate for the projectile's initial position.
     */
    private double getProjectileInitialPosition() {
        return getLayoutY() + getTranslateY() + Config.Boss.PROJECTILE_Y_POSITION_OFFSET;
    }

    /**
     * Determines if the boss's shield should be activated based on probability.
     *
     * @return true if the shield should be activated, false otherwise.
     */
    private boolean shieldShouldBeActivated() {
        return Math.random() < config.getShieldProbability();
    }

    /**
     * Determines if the boss's shield has been active for too long and should be deactivated.
     *
     * @return true if the shield is exhausted and should be deactivated, false otherwise.
     */
    private boolean shieldExhausted() {
        return framesWithShieldActivated == Config.Boss.MAX_SHIELD_FRAMES; // Shield lasts for 500 frames
    }

    /**
     * Activates the boss's shield and applies a visual effect.
     */
    private void activateShield() {
        isShielded = true;
        setEffect(shieldGlowEffect); // Apply the red glow effect to the boss
    }

    /**
     * Deactivates the boss's shield and resets the shield activation frames.
     */
    private void deactivateShield() {
        isShielded = false;
        framesWithShieldActivated = 0;
        setEffect(null); // Remove the shield visual effect
    }

    /**
     * Returns the current health of the boss.
     *
     * @return The current health.
     */
    public int getHealth() {
        return currentHealth;
    }

    /**
     * Returns the maximum health of the boss as defined in its configuration.
     *
     * @return The maximum health.
     */
    public int getMaxHealth() {
        return config.getMaxHealth();
    }
}
