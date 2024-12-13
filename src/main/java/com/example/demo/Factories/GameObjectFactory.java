package com.example.demo.Factories;

import com.example.demo.Objects.GameObject;

/**
 * New interface to create GameEntity products
 * Such as planes, projectiles
 */
public interface GameObjectFactory {
    GameObject createEntity(double xPos, double yPos);
    GameObject createEntity(double xPos, double yPos, int health);
}
