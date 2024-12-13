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
 * Represents the "Victory" screen displayed when the player wins the game.
 * This screen provides options to return to the main menu or exit the game.
 */
public class GameWinScreen extends MenuScreen {
    private Runnable onMainMenu;

    /**
     * Constructs a GameWinScreen with the specified width and height.
     *
     * @param width  The width of the screen.
     * @param height The height of the screen.
     */
    public GameWinScreen(double width, double height) {
        super(width, height, Config.Game.WIN_BACKGROUND);
        createScreen();
    }

    /**
     * Initializes and constructs the layout and components of the GameWinScreen.
     */
    @Override
    protected void createScreen() {
        StackPane menuContainer = new StackPane();
        menuContainer.setPrefSize(width, height);

        VBox menuBox = new VBox(30);
        menuBox.setAlignment(Pos.CENTER);

        // Add a title to the screen
        Text title = new Text("Victory!");
        title.setFont(Font.font("Monospaced", FontWeight.BOLD, 100));
        title.setFill(Color.GOLD);
        title.setEffect(new DropShadow(20, Color.BLACK));

        // Create main menu and exit buttons
        Button mainMenuButton = createStyledButton("Back to Main Menu");
        Button exitButton = createStyledButton("Exit");

        // Disable space and enter triggering button action
        disableButtonDefaultKeyBehavior(mainMenuButton);
        disableButtonDefaultKeyBehavior(exitButton);

        // Set button actions
        mainMenuButton.setOnAction(e -> {
            if (onMainMenu != null) {
                soundManager.playBackgroundMusic(soundManager.getMenuMusic());
                onMainMenu.run();
            }
        });
        exitButton.setOnAction(e -> System.exit(0));

        menuBox.getChildren().addAll(title, mainMenuButton, exitButton);
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
}
