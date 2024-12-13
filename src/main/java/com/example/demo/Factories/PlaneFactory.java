package com.example.demo.Factories;

import com.example.demo.Objects.GameObject;

/**
 * Implements GameObjectFactory interface to creates concrete plane products
 */
public abstract class PlaneFactory implements GameObjectFactory {
    protected String imageName;
    protected int imageHeight;
    protected int defaultHealth;

    /**
     * Constructor of PlaneFactory
     * @param imageName image of the plane
     * @param imageHeight image height
     * @param defaultHealth health of the plane
     */
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
