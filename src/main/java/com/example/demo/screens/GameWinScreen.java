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

public class GameWinScreen extends MenuScreen {
    private static final String WIN_BACKGROUND = "/com/example/demo/images/background14.jpg";
    private Runnable onMainMenu;
    
    public GameWinScreen(double width, double height) {
        super(width, height, WIN_BACKGROUND);
        createScreen();
    }
    
    @Override
    protected void createScreen() {
        StackPane menuContainer = new StackPane();
        menuContainer.setPrefSize(width, height);
        
        VBox menuBox = new VBox(30);
        menuBox.setAlignment(Pos.CENTER);
        
        Text title = new Text("Victory!");
        title.setFont(Font.font("Monospaced", FontWeight.BOLD, 100));
        title.setFill(Color.GOLD);
        title.setEffect(new DropShadow(20, Color.BLACK));
        
        Button mainMenuButton = createStyledButton("Main Menu");
        Button exitButton = createStyledButton("Exit");
        
        mainMenuButton.setOnAction(e -> {
            if (onMainMenu != null) onMainMenu.run();
        });
        exitButton.setOnAction(e -> System.exit(0));
        
        menuBox.getChildren().addAll(title, mainMenuButton, exitButton);
        menuContainer.getChildren().add(menuBox);
        root.getChildren().add(menuContainer);
    }
    
    public void setOnMainMenu(Runnable handler) {
        this.onMainMenu = handler;
    }
}
