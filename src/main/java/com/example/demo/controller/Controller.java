package com.example.demo.controller;

import java.lang.reflect.InvocationTargetException;

import com.example.demo.MainMenu;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import com.example.demo.Levels.LevelParent;
import com.example.demo.Levels.LevelFactory;

public class Controller {

//	private static final String LEVEL_ONE_CLASS_NAME = "com.example.demo.Levels.LevelOne";
	private static final String LEVEL_ONE_CLASS_NAME = "LEVEL_1";
	private final Stage stage;
	private MainMenu mainMenu;

	public Controller(Stage stage) {
		this.stage = stage;
	}

	public void launchGame() {
		showMainMenu();
		stage.show();
	}

	private void showMainMenu() {
		if (mainMenu == null) {
			mainMenu = new MainMenu(stage.getWidth(), stage.getHeight());
			mainMenu.setOnStart(() -> {
				try {
					goToLevel(LEVEL_ONE_CLASS_NAME);
				} catch (Exception e) {
					showError(e);
				}
			});
		}
		stage.setScene(mainMenu.getScene());
	}

	private void showError(Exception e) {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Error");
		alert.setContentText(e.getMessage());
		alert.show();
	}

/**
Modified to use Levelfactory
 **/
	private void goToLevel(String className) throws ClassNotFoundException, NoSuchMethodException, SecurityException,
			InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
			LevelParent myLevel = LevelFactory.createLevel(className, stage.getHeight(), stage.getWidth());
			myLevel.levelChangeStatus(this::levelChangeAttempt);
			Scene scene = myLevel.initializeScene();
			stage.setScene(scene);
			myLevel.startGame();

	}
	private void levelChangeAttempt(String levelname) {
		try {
			goToLevel(levelname);
		} catch (ClassNotFoundException | NoSuchMethodException | SecurityException | InstantiationException
				 | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setContentText(e.getClass().toString());
			alert.show();
		}
	}

}