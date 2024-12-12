package com.example.demo.screens;

import com.example.demo.Managers.SoundManager;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.KeyCode;

public class MainMenu extends MenuScreen{
    private static final String MENU_BACKGROUND_IMAGE = "/com/example/demo/images/background1.jpg";
    private Runnable onStart;
    private final SoundManager soundManager;

    public MainMenu(double width, double height) {
        super(width, height, MENU_BACKGROUND_IMAGE);
        this.soundManager = SoundManager.getInstance();
        createScreen();
    }
    @Override
    protected void createScreen() {
//        initializeBackground();
        soundManager.playBackgroundMusic(soundManager.getMenuMusic());
        StackPane menuContainer = new StackPane();
        menuContainer.setPrefSize(width, height);

        VBox menuBox = new VBox(30);
        menuBox.setAlignment(Pos.CENTER);

        Text title = new Text("Sky Battle");
        title.setFont(Font.font("Monospaced", FontWeight.BOLD, 100));
        title.setFill(Color.WHITE);
        title.setEffect(new DropShadow(20, Color.BLACK));

        Button startButton = createStyledButton("Start");
        Button exitButton = createStyledButton("Exit");

        startButton.setOnAction(e -> {
            soundManager.stopBackgroundMusic();
            setStartGame();
        });
        exitButton.setOnAction(e -> System.exit(0));

        menuBox.getChildren().addAll(title, startButton, exitButton);
        menuContainer.getChildren().add(menuBox);
        root.getChildren().add(menuContainer);
    }

    private void setStartGame() {
        if (onStart != null) {
            onStart.run();
        }
    }

    public void setOnStart(Runnable handler) {
        this.onStart = handler;
    }
}