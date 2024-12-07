package com.example.demo.Planes;

import com.example.demo.GameObject;

public abstract class FighterPlane extends GameObject {

	private int health;
	private boolean isDisabled;

	public FighterPlane(String imageName, int imageHeight, double initialXPos, double initialYPos, int health) {
		super(imageName, imageHeight, initialXPos, initialYPos);
		this.health = health;
		this.isDisabled = false;
	}

	public abstract GameObject fireProjectile();
	
	@Override
	public void takeDamage() {
		health--;
		if (healthAtZero()) {
			this.destroy();
		}
	}

	protected double getProjectileXPosition(double xPositionOffset) {
		return getLayoutX() + getTranslateX() + xPositionOffset;
	}

	protected double getProjectileYPosition(double yPositionOffset) {
		return getLayoutY() + getTranslateY() + yPositionOffset;
	}

	private boolean healthAtZero() {
		return health == 0;
	}

	/**
	 * Getters and setters for the health of the fighter plane
	 */
	public int getHealth() {
		return health;
	}
	public void addHealth(){this.health+=1;}

	/**
	 * Getters and Setters for isDisabled variable
	 * @param disabledStatus true or false whether plane is disabled or not
	 */
	public void setDisabledStatus(boolean disabledStatus){
		this.isDisabled = disabledStatus;
	}

	public boolean getDisabledStatus(){
		return this.isDisabled;
	}
		
}
