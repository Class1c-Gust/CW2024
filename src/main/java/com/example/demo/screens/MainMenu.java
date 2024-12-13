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
 * Represents the main menu screen of the game.
 * This screen provides options to start the game or exit.
 */
public class MainMenu extends MenuScreen {
    private Runnable onStart;

    /**
     * Constructs a MainMenu with the specified width and height.
     *
     * @param width  The width of the screen.
     * @param height The height of the screen.
     */
    public MainMenu(double width, double height) {
        super(width, height, Config.Game.MENU_BACKGROUND_IMAGE);
        createScreen();
    }

    /**
     * Initializes and constructs the layout and components of the MainMenu.
     */
    @Override
    protected void createScreen() {
        soundManager.playBackgroundMusic(soundManager.getMenuMusic());

        StackPane menuContainer = new StackPane();
        menuContainer.setPrefSize(width, height);

        VBox menuBox = new VBox(30);
        menuBox.setAlignment(Pos.CENTER);

        // Add a title to the screen
        Text title = new Text("Sky Battle");
        title.setFont(Font.font("Monospaced", FontWeight.BOLD, 100));
        title.setFill(Color.WHITE);
        title.setEffect(new DropShadow(20, Color.BLACK));

        // Create start and exit buttons
        Button startButton = createStyledButton("Start");
        Button exitButton = createStyledButton("Exit");

        // Disable space and enter triggering button action
        disableButtonDefaultKeyBehavior(startButton);
        disableButtonDefaultKeyBehavior(exitButton);

        // Set button actions
        startButton.setOnAction(e -> {
            soundManager.stopBackgroundMusic();
            setStartGame();
        });

        exitButton.setOnAction(e -> System.exit(0));

        menuBox.getChildren().addAll(title, startButton, exitButton);
        menuContainer.getChildren().add(menuBox);
        root.getChildren().add(menuContainer);
    }

    /**
     * Invokes the start game handler if defined.
     */
    private void setStartGame() {
        if (onStart != null) {
            onStart.run();
        }
    }

    /**
     * Sets the action to be performed when the "Start" button is clicked.
     *
     * @param handler A Runnable representing the action.
     */
    public void setOnStart(Runnable handler) {
        this.onStart = handler;
    }
}
