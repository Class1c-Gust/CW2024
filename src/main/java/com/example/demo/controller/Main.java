package com.example.demo.controller;

import javafx.application.Application;
import javafx.stage.Stage;
import com.example.demo.config.Config;
import java.lang.reflect.InvocationTargetException;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        stage.setResizable(false);
        stage.setHeight(Config.Screen.HEIGHT);
        stage.setWidth(Config.Screen.WIDTH);
        stage.setTitle(Config.Screen.TITLE);
        Controller myController = new Controller(stage);
        myController.launchGame();
    }

    public static void main(String[] args) {
        launch();
    }
}
