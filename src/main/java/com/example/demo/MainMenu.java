package com.example.demo;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.KeyCode;

import java.util.Objects;

public class MainMenu {
    private static final String MENU_BACKGROUND_IMAGE = "/com/example/demo/images/background1.jpg";
    private static final double BUTTON_WIDTH = 200;
    private static final double BUTTON_HEIGHT = 50;

    private final Scene scene;
    private final Group root;
    private final double width;
    private final double height;
    private Runnable onStart;

    public MainMenu(double width, double height) {
        this.width = width;
        this.height = height;
        this.root = new Group();
        this.scene = new Scene(root, width, height);
        createMenu();
    }

    private void createMenu() {
        initializeBackground();
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

        startButton.setOnAction(e -> setStartGame());
        exitButton.setOnAction(e -> System.exit(0));

        scene.setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.SPACE || e.getCode() == KeyCode.ENTER) {
                setStartGame();
            }
            if (e.getCode() == KeyCode.ESCAPE) {
                System.exit(0);
            }
        });

        menuBox.getChildren().addAll(title, startButton, exitButton);
        menuContainer.getChildren().add(menuBox);
        root.getChildren().add(menuContainer);
    }

    private void initializeBackground() {
        try {
            ImageView background = new ImageView(new Image(Objects.requireNonNull(getClass().getResource(MENU_BACKGROUND_IMAGE)).toExternalForm()));
            background.setFitWidth(width);
            background.setFitHeight(height);
            root.getChildren().add(background);
        } catch (Exception e) {
            scene.setFill(Color.DARKBLUE);
        }
    }

    private Button createStyledButton(String text) {
        Button button = new Button(text);
        button.setPrefWidth(BUTTON_WIDTH);
        button.setPrefHeight(BUTTON_HEIGHT);
        button.setFont(Font.font("Helvetica Neue", FontWeight.BOLD, 18));
        button.setStyle("-fx-background-color: linear-gradient(to right, #4a90e2, #007AFF); " +
                "-fx-text-fill: white; " +
                "-fx-background-radius: 12; " +
                "-fx-padding: 10 20; " +
                "-fx-cursor: hand; " +
                "-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.3), 10, 0, 0, 2);");
        // hover over button, change colour
        button.setOnMouseEntered(e ->
                button.setStyle(button.getStyle() + "-fx-background-color: #357abd;"));
        button.setOnMouseExited(e ->
                button.setStyle(button.getStyle() + "-fx-background-color: #4a90e2;"));
        return button;
    }

    private void setStartGame() {
        if (onStart != null) {
            onStart.run();
        }
    }

    public Scene getScene() {
        return scene;
    }

    public void setOnStart(Runnable handler) {
        this.onStart = handler;
    }
}