package com.example.demo.Objects;

import com.example.demo.config.Config;
import javafx.geometry.BoundingBox;
import javafx.geometry.Bounds;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.Objects;

/**
 * Abstract class representing a game object that is rendered as an image on the screen.
 * This class provides basic functionality for handling movement, destruction, and bounding box retrieval.
 * It extends {@link ImageView} to display the game object as an image.
 */
public abstract class GameObject extends ImageView {
    private boolean isDestroyed;

    /**
     * Constructor to initialize a game object with a given image, position, and size.
     *
     * @param imageName     The name of the image to display for this object.
     * @param imageHeight   The height of the image.
     * @param initialXPos   The initial X position of the object.
     * @param initialYPos   The initial Y position of the object.
     */
    public GameObject(String imageName, int imageHeight, double initialXPos, double initialYPos) {
        // Load the image from resources and set the initial position and size of the object.
        this.setImage(new Image(Objects.requireNonNull(getClass().getResource(Config.Game.IMAGE_LOCATION + imageName)).toExternalForm()));
        this.setLayoutX(initialXPos);
        this.setLayoutY(initialYPos);
        this.setFitHeight(imageHeight);
        this.setPreserveRatio(true);
        isDestroyed = false; // The object is not destroyed by default.
    }

    /**
     * Moves the object horizontally by a specified distance.
     *
     * @param horizontalMove The distance to move the object horizontally.
     */
    protected void moveHorizontally(double horizontalMove) {
        this.setTranslateX(getTranslateX() + horizontalMove);
    }

    /**
     * Moves the object vertically by a specified distance.
     *
     * @param verticalMove The distance to move the object vertically.
     */
    protected void moveVertically(double verticalMove) {
        this.setTranslateY(getTranslateY() + verticalMove);
    }

    /**
     * Retrieves the exact bounds of the game object with a reduction applied to its height.
     * This method adjusts the bounds to make the object slightly smaller (reduced by 20% in height).
     *
     * @return The bounding box of the game object with adjusted dimensions.
     */
    public Bounds getExactBounds() {
        Bounds bounds = this.getBoundsInParent();
        double reduction = bounds.getHeight() * 0.8; // Reduce the height by 20%.
        double minx = bounds.getMinX();
        double miny = bounds.getMinY() + reduction / 2; // Adjust the Y position based on the reduction.
        double width = bounds.getWidth();
        double height = bounds.getHeight() - reduction; // Adjust the height.
        return new BoundingBox(minx, miny, width, height);
    }

    /**
     * Updates the position of the game object. This method should be implemented by subclasses
     * to define how the object moves or behaves in the game.
     */
    public abstract void updatePosition();

    /**
     * Updates the behavior or state of the game object. This method should be implemented by subclasses
     * to define how the object interacts with other game entities.
     */
    public abstract void updateActor();

    /**
     * Handles damage taken by the game object. This method should be implemented by subclasses
     * to define how the object responds to taking damage (e.g., reducing health, triggering destruction).
     */
    public abstract void takeDamage();

    /**
     * Destroys the game object, setting it as destroyed and preventing it from interacting with the game world.
     * This method can be called when the object is no longer needed or has been defeated.
     */
    public void destroy() {
        setDestroyed(); // Mark the object as destroyed.
    }

    /**
     * Sets the game object as destroyed.
     * This is typically called internally when the object is no longer in play.
     */
    protected void setDestroyed() {
        this.isDestroyed = true;
    }

    /**
     * Checks if the game object has been destroyed.
     *
     * @return true if the object is destroyed, false otherwise.
     */
    public boolean isDestroyed() {
        return isDestroyed;
    }
}
