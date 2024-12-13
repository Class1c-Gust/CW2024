package com.example.demo.controller;

import javafx.application.Application;
import javafx.stage.Stage;
import com.example.demo.config.Config;
import java.lang.reflect.InvocationTargetException;

/**
 * The main entry point for the application. This class extends {@link Application}
 * and sets up the primary stage of the game.
 *
 * <p>The {@code Main} class is responsible for configuring and launching the
 * game window by setting the stage properties such as size, title, and
 * non-resizability. It initializes a {@link Controller} instance and starts
 * the game through the {@code launchGame} method.</p>
 *
 * <p>This class serves as the starting point for the JavaFX application and
 * invokes the {@link Controller} to manage the game logic.</p>
 */
public class Main extends Application {

    /**
     * The main entry point for JavaFX application. It sets the properties of
     * the primary stage and launches the game via the {@link Controller}.
     *
     * @param stage the primary stage for this JavaFX application
     * @throws ClassNotFoundException if a class required for the game cannot be found
     * @throws InvocationTargetException if there is an error invoking methods via reflection
     * @throws NoSuchMethodException if a required method cannot be found
     * @throws InstantiationException if an error occurs during object instantiation
     * @throws IllegalAccessException if access to a required class or method is illegal
     */
    @Override
    public void start(Stage stage) throws ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        stage.setResizable(false);
        stage.setHeight(Config.Screen.HEIGHT);
        stage.setWidth(Config.Screen.WIDTH);
        stage.setTitle(Config.Screen.TITLE);
        Controller myController = new Controller(stage);
        myController.launchGame();
    }

    /**
     * The main method to launch the JavaFX application.
     *
     * @param args command-line arguments
     */
    public static void main(String[] args) {
        launch();
    }
}

