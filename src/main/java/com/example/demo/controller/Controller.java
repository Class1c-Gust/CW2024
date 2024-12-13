package com.example.demo.controller;

import java.lang.reflect.InvocationTargetException;

import com.example.demo.config.Config;
import com.example.demo.screens.MainMenu;
import com.example.demo.screens.GameWinScreen;
import com.example.demo.screens.GameLoseScreen;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import com.example.demo.Levels.LevelParent;
import com.example.demo.Levels.LevelFactory;

/**
 * The primary controller of the application
 */
public class Controller {

	private final Stage stage;
	private MainMenu mainMenu;
	private GameWinScreen winScreen;
	private GameLoseScreen loseScreen;

    /**
	 * Initializes the controller with the specified stage.
	 *
	 * @param stage the primary stage of the application
	 */
	public Controller(Stage stage) {
		this.stage = stage;
	}

	/**
	 * Launches the game by displaying the main menu and showing the stage.
	 */
	public void launchGame() {
		showMainMenu();
		stage.show();
	}

	/**
	 * Displays the main menu scene, initializing it if necessary.
	 */
	private void showMainMenu() {
		if (mainMenu == null) {
			mainMenu = new MainMenu(stage.getWidth(), stage.getHeight());
			mainMenu.setOnStart(() -> {
				try {
					goToLevel(Config.Level.LEVEL_ONE_CLASS_NAME);
				} catch (Exception e) {
					showError(e);
				}
			});
		}
		stage.setScene(mainMenu.getScene());
	}

	/**
	 * Displays an error message in an alert dialog.
	 *
	 * @param e the exception to display
	 */
	private void showError(Exception e) {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Error");
		alert.setContentText(e.getMessage());
		alert.show();
	}

	/**
	 * Navigates to a specified level by class name and starts the game.
	 *
	 * @param className the fully qualified class name of the level
	 * @throws ClassNotFoundException    if the class cannot be found
	 * @throws NoSuchMethodException     if a required method is missing
	 * @throws SecurityException         if a security violation occurs
	 * @throws InstantiationException    if the level class cannot be instantiated
	 * @throws IllegalAccessException    if the level class or constructor is inaccessible
	 * @throws IllegalArgumentException  if an illegal argument is passed
	 * @throws InvocationTargetException if the constructor throws an exception
	 */
	private void goToLevel(String className) throws ClassNotFoundException, NoSuchMethodException, SecurityException,
			InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		LevelParent myLevel = LevelFactory.createLevel(className, stage.getHeight(), stage.getWidth());
		myLevel.levelChangeStatus(this::levelChangeAttempt);
		Scene scene = myLevel.initializeScene();
		stage.setScene(scene);
		myLevel.startGame();
	}

	/**
	 * Attempts to change the level, handling win, lose, or level transitions.
	 *
	 * @param levelname the name of the level or game status
	 */
	private void levelChangeAttempt(String levelname) {
		try {
			if (levelname.equals("WIN")) {
				showGameWin();
				return;
			} else if (levelname.equals("LOSE")) {
				showGameLose();
				return;
			}
			goToLevel(levelname);
		} catch (ClassNotFoundException | NoSuchMethodException | SecurityException | InstantiationException
				 | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setContentText(e.getClass().toString());
			alert.show();
		}
	}

	/**
	 * Displays the game win screen, initializing it if necessary.
	 */
	public void showGameWin() {
		if (winScreen == null) {
			winScreen = new GameWinScreen(stage.getWidth(), stage.getHeight());
			winScreen.setOnMainMenu(this::showMainMenu);
		}
		stage.setScene(winScreen.getScene());
	}

	/**
	 * Displays the game lose screen, initializing it if necessary.
	 */
	public void showGameLose() {
		if (loseScreen == null) {
			loseScreen = new GameLoseScreen(stage.getWidth(), stage.getHeight());
			loseScreen.setOnMainMenu(this::showMainMenu);
			loseScreen.setOnRetry(() -> levelChangeAttempt(Config.Level.LEVEL_ONE_CLASS_NAME));
		}
		stage.setScene(loseScreen.getScene());
	}
}