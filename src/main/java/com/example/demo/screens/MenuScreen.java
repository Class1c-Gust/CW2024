package com.example.demo.screens;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.Group;
import javafx.scene.paint.Color;

import java.util.Objects;

public abstract class MenuScreen {
    protected static final double BUTTON_WIDTH = 200;
    protected static final double BUTTON_HEIGHT = 50;
    
    protected final Scene scene;
    protected final Group root;
    protected final double width;
    protected final double height;
    
    public MenuScreen(double width, double height, String backgroundImage) {
        this.width = width;
        this.height = height;
        this.root = new Group();
        this.scene = new Scene(root, width, height);
        initializeBackground(backgroundImage);
    }
    
    protected void initializeBackground(String backgroundPath) {
        try {
            ImageView background = new ImageView(new Image(
                Objects.requireNonNull(getClass().getResource(backgroundPath)).toExternalForm()));
            background.setFitWidth(width);
            background.setFitHeight(height);
            root.getChildren().add(background);
        } catch (Exception e) {
            scene.setFill(Color.DARKBLUE);
        }
    }
    
    protected Button createStyledButton(String text) {
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
        
        button.setOnMouseEntered(e -> 
            button.setStyle(button.getStyle() + "-fx-background-color: #357abd;"));
        button.setOnMouseExited(e -> 
            button.setStyle(button.getStyle() + "-fx-background-color: #4a90e2;"));
        return button;
    }
    
    public Scene getScene() {
        return scene;
    }
    
    protected abstract void createScreen();
}