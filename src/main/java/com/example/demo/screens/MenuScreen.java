package com.example.demo.screens;

import com.example.demo.Managers.SoundManager;
import com.example.demo.config.Config;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.Group;
import javafx.scene.paint.Color;

import java.util.Objects;

/**
 * Represents a base class for menu screens in the game.
 * This class provides common functionality for managing screen layout,
 * background setup, button styles, and disabling default button key behaviors.
 */
public abstract class MenuScreen {
    protected final Scene scene;
    protected final Group root;
    protected final double width;
    protected final double height;
    protected SoundManager soundManager;

    /**
     * Constructs a MenuScreen with the specified width, height, and background image.
     *
     * @param width           The width of the screen.
     * @param height          The height of the screen.
     * @param backgroundImage The path to the background image resource.
     */
    public MenuScreen(double width, double height, String backgroundImage) {
        this.width = width;
        this.height = height;
        this.root = new Group();
        this.scene = new Scene(root, width, height);
        this.soundManager = SoundManager.getInstance();
        initializeBackground(backgroundImage);
    }

    /**
     * Initializes the background of the menu screen with the specified image.
     * If the image is not found, the screen defaults to a dark blue color.
     *
     * @param backgroundPath The path to the background image resource.
     */
    protected void initializeBackground(String backgroundPath) {
        try {
            // Load and set the background image
            ImageView background = new ImageView(new Image(
                    Objects.requireNonNull(getClass().getResource(backgroundPath)).toExternalForm()));
            background.setFitWidth(width); // Fit the background to the screen width
            background.setFitHeight(height); // Fit the background to the screen height
            root.getChildren().add(background); // Add the background to the root
        } catch (Exception e) {
            // Fallback to a solid color if the image fails to load
            scene.setFill(Color.DARKBLUE);
        }
    }

    /**
     * Disables default key behavior (e.g., SPACE and ENTER) for the specified button.
     * Prevents accidental activation of button actions through key presses.
     *
     * @param button The button to modify.
     */
    protected void disableButtonDefaultKeyBehavior(Button button) {
        button.addEventFilter(KeyEvent.KEY_PRESSED, event -> {
            if (event.getCode() == KeyCode.SPACE || event.getCode() == KeyCode.ENTER) {
                event.consume(); // Suppress the default key event
            }
        });
    }

    /**
     * Creates a styled button with predefined styles and effects.
     * Includes hover effects and a gradient background.
     *
     * @param text The text to display on the button.
     * @return A styled Button instance.
     */
    protected Button createStyledButton(String text) {
        Button button = new Button(text);
        button.setPrefWidth(Config.Screen.BUTTON_WIDTH); // Set button width
        button.setPrefHeight(Config.Screen.BUTTON_HEIGHT); // Set button height
        button.setFont(Font.font("Helvetica Neue", FontWeight.BOLD, 18)); // Set font style and size
        button.setStyle("-fx-background-color: linear-gradient(to right, #4a90e2, #007AFF); " +
                "-fx-text-fill: white; " +
                "-fx-background-radius: 12; " +
                "-fx-padding: 10 20; " +
                "-fx-cursor: hand; " +
                "-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.3), 10, 0, 0, 2);");

        // Add hover effect for the button
        button.setOnMouseEntered(e ->
                button.setStyle(button.getStyle() + "-fx-background-color: #357abd;"));
        button.setOnMouseExited(e ->
                button.setStyle(button.getStyle() + "-fx-background-color: #4a90e2;"));
        return button;
    }

    /**
     * Retrieves the Scene associated with this MenuScreen.
     *
     * @return The Scene object representing this screen.
     */
    public Scene getScene() {
        return scene;
    }

    /**
     * Abstract method to be implemented by subclasses to create and set up the menu screen.
     */
    protected abstract void createScreen();
}
