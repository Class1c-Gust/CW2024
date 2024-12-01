package com.example.demo;

/**
 * New interface to create GameEntity products
 * Such as planes, projectiles etc
 */
public interface GameObjectFactory {
    GameObject createEntity(double xPos, double yPos);
    GameObject createEntity(double xPos, double yPos, int health);
}
