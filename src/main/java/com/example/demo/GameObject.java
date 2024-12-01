package com.example.demo;

import javafx.geometry.BoundingBox;
import javafx.geometry.Bounds;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.Objects;

public abstract class GameObject extends ImageView {
    private boolean isDestroyed;
    private static final String IMAGE_LOCATION = "/com/example/demo/images/";

    public GameObject(String imageName, int imageHeight, double initialXPos, double initialYPos) {
        //this.setImage(new Image(IMAGE_LOCATION + imageName));
        this.setImage(new Image(Objects.requireNonNull(getClass().getResource(IMAGE_LOCATION + imageName)).toExternalForm()));
        this.setLayoutX(initialXPos);
        this.setLayoutY(initialYPos);
        this.setFitHeight(imageHeight);
        this.setPreserveRatio(true);
        isDestroyed = false;
    }

    protected void moveHorizontally(double horizontalMove) {
        this.setTranslateX(getTranslateX() + horizontalMove);
    }

    protected void moveVertically(double verticalMove) {
        this.setTranslateY(getTranslateY() + verticalMove);
    }
    public Bounds getExactBounds(){
        Bounds bounds = this.getBoundsInParent();
        double reduction = bounds.getHeight() * 0.8;
        double minx = bounds.getMinX();
        double miny = bounds.getMinY() + reduction/2;
        double width = bounds.getWidth();
        double height = bounds.getHeight() - reduction;
        return new BoundingBox(minx, miny, width, height);
    }

    public abstract void updatePosition();

    public abstract void updateActor();

    public abstract void takeDamage();

    public void destroy() {
        setDestroyed();
    }

    protected void setDestroyed() {
        this.isDestroyed = true;
    }

    public boolean isDestroyed() {
        return isDestroyed;
    }
}
