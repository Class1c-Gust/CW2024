package com.example.demo.screens;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.paint.Color;
import javafx.scene.effect.DropShadow;

public class GameLoseScreen extends MenuScreen {
    private static final String LOSE_BACKGROUND = "/com/example/demo/images/background13.jpg";
    private Runnable onMainMenu;
    private Runnable onRetry;
    
    public GameLoseScreen(double width, double height) {
        super(width, height, LOSE_BACKGROUND);
        createScreen();
    }
    
    @Override
    protected void createScreen() {
        StackPane menuContainer = new StackPane();
        menuContainer.setPrefSize(width, height);
        
        VBox menuBox = new VBox(30);
        menuBox.setAlignment(Pos.CENTER);
        
        Text title = new Text("Game Over");
        title.setFont(Font.font("Monospaced", FontWeight.BOLD, 100));
        title.setFill(Color.RED);
        title.setEffect(new DropShadow(20, Color.BLACK));
        
        Button retryButton = createStyledButton("Retry");
        Button mainMenuButton = createStyledButton("Main Menu");
        
        retryButton.setOnAction(e -> {
            if (onRetry != null) onRetry.run();
        });
        mainMenuButton.setOnAction(e -> {
            if (onMainMenu != null) onMainMenu.run();
        });
        
        menuBox.getChildren().addAll(title, retryButton, mainMenuButton);
        menuContainer.getChildren().add(menuBox);
        root.getChildren().add(menuContainer);
    }
    
    public void setOnMainMenu(Runnable handler) {
        this.onMainMenu = handler;
    }
    
    public void setOnRetry(Runnable handler) {
        this.onRetry = handler;
    }
}
