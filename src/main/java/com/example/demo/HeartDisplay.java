package com.example.demo;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

import java.util.Objects;

/**
 * Class responsible for displaying a series of heart icons representing health in the game.
 * This class uses an {@link HBox} to arrange the heart images horizontally and provides methods
 * to add or remove hearts as the player's health changes.
 */
public class HeartDisplay {

	// Constants for heart image and size
	private static final String HEART_IMAGE_NAME = "/com/example/demo/images/heart.png";
	private static final int HEART_HEIGHT = 50;
	private static final int INDEX_OF_FIRST_ITEM = 0;

	private HBox container;
	private final double containerXPosition;
	private final double containerYPosition;
	private final int numberOfHeartsToDisplay;

	/**
	 * Constructor to initialize the heart display with the specified position and number of hearts.
	 *
	 * @param xPosition The X position where the heart container should be placed.
	 * @param yPosition The Y position where the heart container should be placed.
	 * @param heartsToDisplay The number of hearts to initially display.
	 */
	public HeartDisplay(double xPosition, double yPosition, int heartsToDisplay) {
		this.containerXPosition = xPosition;
		this.containerYPosition = yPosition;
		this.numberOfHeartsToDisplay = heartsToDisplay;
		initializeContainer(); // Initialize the container for the hearts
		initializeHearts(); // Initialize the hearts to be displayed
	}

	/**
	 * Initializes the container that holds the heart images.
	 * The container is positioned based on the provided X and Y positions.
	 */
	private void initializeContainer() {
		container = new HBox(); // Create a horizontal box to hold the hearts
		container.setLayoutX(containerXPosition);
		container.setLayoutY(containerYPosition);
	}

	/**
	 * Initializes the heart images by adding the specified number of hearts to the container.
	 */
	private void initializeHearts() {
		for (int i = 0; i < numberOfHeartsToDisplay; i++) {
			createHeart(); // Create and add a heart for each one to be displayed
		}
	}

	/**
	 * Creates a new heart image and adds it to the container.
	 * This method is separated to prevent duplicate code when adding hearts.
	 */
	private void createHeart() {
		ImageView heart = new ImageView(new Image(Objects.requireNonNull(getClass().getResource(HEART_IMAGE_NAME)).toExternalForm()));
		heart.setFitHeight(HEART_HEIGHT);
		heart.setPreserveRatio(true);
		container.getChildren().add(heart);
	}

	/**
	 * Removes the first heart from the container.
	 * This can be used when the player loses health and the number of hearts should decrease.
	 */
	public void removeHeart() {
		if (!container.getChildren().isEmpty()) {
			container.getChildren().remove(INDEX_OF_FIRST_ITEM); // Remove the first heart from the container
		}
	}

	/**
	 * Public method to add a new heart to the display.
	 * This calls the {@link #createHeart()} method to create and add a heart.
	 */
	public void addHeart() {
		createHeart(); // Add a new heart to the container
	}

	/**
	 * Gets the container (HBox) that holds all the heart images.
	 *
	 * @return The HBox container holding the heart images.
	 */
	public HBox getContainer() {
		return container;
	}
}
