package com.example.demo;

import javafx.geometry.BoundingBox;
import javafx.scene.image.*;
import javafx.geometry.Bounds;
import javafx.scene.transform.*;

public abstract class ActiveActor extends ImageView {
	
	private static final String IMAGE_LOCATION = "/com/example/demo/images/";

	public ActiveActor(String imageName, int imageHeight, double initialXPos, double initialYPos) {
		//this.setImage(new Image(IMAGE_LOCATION + imageName));
		this.setImage(new Image(getClass().getResource(IMAGE_LOCATION + imageName).toExternalForm()));
		this.setLayoutX(initialXPos);
		this.setLayoutY(initialYPos);
		this.setFitHeight(imageHeight);
		this.setPreserveRatio(true);
	}

	public abstract void updatePosition();

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

}
