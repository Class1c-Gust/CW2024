package com.example.demo.screens;

import com.example.demo.Managers.SoundManager;
import com.example.demo.config.Config;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.paint.Color;
import javafx.scene.effect.DropShadow;

/**
 * Represents the "Game Over" screen displayed when the player loses the game.
 * This screen provides options to retry the game or return to the main menu.
 */
public class GameLoseScreen extends MenuScreen {
    private Runnable onMainMenu;
    private Runnable onRetry;

    /**
     * Constructs a GameLoseScreen with the specified width and height.
     *
     * @param width  The width of the screen.
     * @param height The height of the screen.
     */
    public GameLoseScreen(double width, double height) {
        super(width, height, Config.Game.LOSE_BACKGROUND);
        createScreen();
    }

    /**
     * Initializes and constructs the layout and components of the GameLoseScreen.
     */
    @Override
    protected void createScreen() {
        StackPane menuContainer = new StackPane();
        menuContainer.setPrefSize(width, height);

        VBox menuBox = new VBox(30);
        menuBox.setAlignment(Pos.CENTER);

        // Add a title to the screen
        Text title = new Text("Game Over");
        title.setFont(Font.font("Monospaced", FontWeight.BOLD, 100));
        title.setFill(Color.RED);
        title.setEffect(new DropShadow(20, Color.BLACK));

        // Create retry and main menu buttons
        Button retryButton = createStyledButton("Retry");
        Button mainMenuButton = createStyledButton("Main Menu");

        // Disable space and enter triggering button action
        disableButtonDefaultKeyBehavior(mainMenuButton);
        disableButtonDefaultKeyBehavior(retryButton);

        // Set button actions
        retryButton.setOnAction(e -> {
            if (onRetry != null) onRetry.run();
        });
        mainMenuButton.setOnAction(e -> {
            soundManager.playBackgroundMusic(soundManager.getMenuMusic());
            if (onMainMenu != null) onMainMenu.run();
        });

        menuBox.getChildren().addAll(title, retryButton, mainMenuButton);
        menuContainer.getChildren().add(menuBox);
        root.getChildren().add(menuContainer);
    }

    /**
     * Sets the action to be performed when the "Main Menu" button is clicked.
     *
     * @param handler A Runnable representing the action.
     */
    public void setOnMainMenu(Runnable handler) {
        this.onMainMenu = handler;
    }

    /**
     * Sets the action to be performed when the "Retry" button is clicked.
     *
     * @param handler A Runnable representing the action.
     */
    public void setOnRetry(Runnable handler) {
        this.onRetry = handler;
    }
}
