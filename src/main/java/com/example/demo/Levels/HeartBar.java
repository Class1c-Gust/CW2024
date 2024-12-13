package com.example.demo.Levels;

import com.example.demo.HeartDisplay;
import com.example.demo.config.Config;
import javafx.scene.Group;
import javafx.scene.layout.HBox;
import org.junit.jupiter.api.DynamicContainer;

/**
 * Represents a heart bar in the game.
 * This class handles the display and updating of heart icons representing the player's health.
 */
public class HeartBar {

	private final Group root;
	private final HeartDisplay heartDisplay;

	/**
	 * Constructs a HeartBar instance.
	 *
	 * @param root            the root node of the scene
	 * @param heartsToDisplay the initial number of hearts to display
	 */
	public HeartBar(Group root, int heartsToDisplay) {
		this.root = root;
		this.heartDisplay = new HeartDisplay(
				Config.Level.HEART_DISPLAY_X_POSITION,
				Config.Level.HEART_DISPLAY_Y_POSITION,
				heartsToDisplay
		);
	}

	/**
	 * Adds the heart display to the scene.
	 */
	public void showHeartDisplay() {
		root.getChildren().add(heartDisplay.getContainer());
	}

	/**
	 * Updates the heart display to match the player's current health.
	 * Hearts are added or removed as needed to reflect the difference
	 * between the current health and the displayed hearts.
	 *
	 * @param currentHealth the current health of the player
	 */
	public void updateHearts(int currentHealth) {
		int currentNumberOfHearts = heartDisplay.getContainer().getChildren().size();
		int difference = currentNumberOfHearts - currentHealth;

		// Adjust the number of hearts displayed
		for (int i = 0; i < Math.abs(difference); i++) {
			if (difference > 0) {
				// Remove hearts if there are more displayed than the current health
				heartDisplay.removeHeart();
			} else {
				// Add hearts if there are fewer displayed than the current health
				heartDisplay.addHeart();
			}
		}
	}
	/**
	 * Gets the Hbox heart display container.
	 *
	 * @return the getContainer() of the Hbox instance
	 */
	public HBox getContainer() {
		return heartDisplay.getContainer();
	}
}
