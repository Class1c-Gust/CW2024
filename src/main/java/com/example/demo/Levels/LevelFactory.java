package com.example.demo.Levels;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * Produces concrete level products for various level creation
 */
public class LevelFactory {
            public static LevelParent createLevel(String levelClassName, double height, double width)
                    throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException,
                            InstantiationException, IllegalAccessException {
                            if (levelClassName.startsWith("LEVEL_")) {
                            int levelNumber = Integer.parseInt(levelClassName.split("_")[1]);
                            if (levelNumber % 3 == 0) { // For every 3rd level
                                return new BossLevelOne(height, width, levelNumber);
                            }
                            return new DynamicLevel(height, width, levelNumber);
                        }

                    Class<?> myClass = Class.forName(levelClassName);
            Constructor<?> constructor = myClass.getConstructor(double.class, double.class);
            return (LevelParent) constructor.newInstance(height, width);
        }
    }