/**
 * This class represents the view for the boss level in the game, including the boss's health bar and health text.
 * It extends the {@link HeartBar} class to manage heart display in addition to the health bar.
 */
package com.example.demo.Levels;

import com.example.demo.config.Config;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class LevelViewBoss extends HeartBar {

	private final Rectangle healthBar;
	private final Rectangle healthBarBorder;
	private final Text bossHealthText;

	/**
	 * Constructor to initialize the boss level view.
	 *
	 * @param root the root group to which UI elements will be added.
	 * @param heartsToDisplay the number of hearts to display, passed to the parent class.
	 */
	public LevelViewBoss(Group root, int heartsToDisplay) {
		super(root, heartsToDisplay);

        // Initialize the text element for displaying boss health
		bossHealthText = new Text();
		bossHealthText.setStroke(Color.BLACK);
		bossHealthText.setFont(new Font(20));
		bossHealthText.setFill(Color.WHITE);
		bossHealthText.setX(root.getScene().getWidth() - 250);
		bossHealthText.setY(43);

		// Initialize the health bar rectangle
		healthBar = new Rectangle(
				Config.Level.HEALTH_BAR_X_POSITION,
				Config.Level.HEALTH_BAR_Y_POSITION,
				Config.Level.HEALTH_BAR_WIDTH,
				Config.Level.HEALTH_BAR_HEIGHT
		);
		healthBar.setFill(Color.RED);

		// Initialize the health bar border
		healthBarBorder = new Rectangle(
				Config.Level.HEALTH_BAR_X_POSITION,
				Config.Level.HEALTH_BAR_Y_POSITION,
				Config.Level.HEALTH_BAR_WIDTH,
				Config.Level.HEALTH_BAR_HEIGHT
		);
		healthBarBorder.setFill(Color.TRANSPARENT);
		healthBarBorder.setStroke(Color.BLACK);
		healthBarBorder.setStrokeWidth(2);

		// Add elements to the root group
		root.getChildren().addAll(healthBarBorder, healthBar, bossHealthText);
	}

	/**
	 * Updates the boss's health bar and health text based on the current health.
	 *
	 * @param bossHealth the current health of the boss.
	 * @param maxHealth  the maximum health of the boss.
	 */
	public void updateBossHealth(int bossHealth, int maxHealth) {
		// Calculate the percentage of health remaining
		double healthPercentage = (double) bossHealth / maxHealth;

		// Update the health bar width based on health percentage
		healthBar.setWidth(Config.Level.HEALTH_BAR_WIDTH * healthPercentage);

		// Update the health text to show the current health
		bossHealthText.setText(String.valueOf(bossHealth));

		// Bring the health bar and text to the front for visibility
		healthBar.toFront();
		healthBarBorder.toFront();
		bossHealthText.toFront();
	}
	/**
	 * Gets the boss health text.
	 *
	 * @return the boss health text
	 */
	public Text getBossHealthText(){return bossHealthText;}
}
