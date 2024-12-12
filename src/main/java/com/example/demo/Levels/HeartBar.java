package com.example.demo.Levels;


import com.example.demo.HeartDisplay;
import com.example.demo.config.Config;
import javafx.scene.Group;

public class HeartBar {
	private final Group root;
	private final HeartDisplay heartDisplay;
	
	public HeartBar(Group root, int heartsToDisplay) {
		this.root = root;
		this.heartDisplay = new HeartDisplay(Config.Level.HEART_DISPLAY_X_POSITION,
				Config.Level.HEART_DISPLAY_Y_POSITION, heartsToDisplay);
	}
	
	public void showHeartDisplay() {
		root.getChildren().add(heartDisplay.getContainer());
	}

	/**
	 * Updates the current heart display by either adding or removing a heart
	 * @param currentHealth the current health of the user plane
	 */
	public void updateHearts(int currentHealth) {
		int currentNumberOfHearts = heartDisplay.getContainer().getChildren().size();
		int difference = currentNumberOfHearts - currentHealth;
		for (int i = 0; i < Math.abs(difference); i++) {
			if (difference > 0){
				heartDisplay.removeHeart();
			}
			else {
				heartDisplay.addHeart();
			}
		}
	}

}
