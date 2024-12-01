package com.example.demo.Planes;

import com.example.demo.GameObject;
import com.example.demo.GameObjectFactory;

/**
 * Implements GameEntityFactory interface
 * Creates plane products
 */
public abstract class PlaneFactory implements GameObjectFactory {
    protected String imageName;
    protected int imageHeight;
    protected int defaultHealth;

    public PlaneFactory(String imageName, int imageHeight, int defaultHealth) {
        this.imageName = imageName;
        this.imageHeight = imageHeight;
        this.defaultHealth = defaultHealth;
    }

    /**
     * Subclass will override this to create its own variations of entities
     * @param xPos initial X position of the plane
     * @param yPos initial Y position of the plane
     * @return the instance of the entity
     */
    @Override
    public GameObject createEntity(double xPos, double yPos) {
        return createEntity(xPos, yPos, defaultHealth);
    }
}
