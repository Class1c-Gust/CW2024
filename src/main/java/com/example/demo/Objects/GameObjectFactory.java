package com.example.demo.Objects;

/**
 * New interface to create GameEntity products
 * Such as planes, projectiles
 */
public interface GameObjectFactory {
    GameObject createEntity(double xPos, double yPos);
    GameObject createEntity(double xPos, double yPos, int health);
}
