package com.example.demo.Levels;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * Produces concrete level products for various level creation
 */
public class LevelFactory {
    /**
     *
     * @param levelClassName The name of the level
     * @param height Height of the window
     * @param width Width of the window
     * @return The level instance
     * @throws ClassNotFoundException if the class provided doesnt exist
     * @throws NoSuchMethodException if the method called doesnt exist
     */
    public static LevelParent createLevel(String levelClassName, double height, double width) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Class<?> myClass = Class.forName(levelClassName);
        Constructor<?> constructor = myClass.getConstructor(double.class, double.class);
        return (LevelParent) constructor.newInstance(height, width);
    }
}