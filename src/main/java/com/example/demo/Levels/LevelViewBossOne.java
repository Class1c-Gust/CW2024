package com.example.demo.Levels;

import com.example.demo.ShieldImage;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class LevelViewBossOne extends LevelView {

	private static final int SHIELD_X_POSITION = 1150;
	private static final int SHIELD_Y_POSITION = 500;
	private static final int HEALTH_BAR_WIDTH = 350;
	private static final int HEALTH_BAR_HEIGHT = 35;
	private static final int HEALTH_BAR_X_POSITION = 900;
	private static final int HEALTH_BAR_Y_POSITION = 20;
	private final Group root;
	private final ShieldImage shieldImage;
	private final Rectangle healthBar;
	private final Rectangle healthBarBorder;
	private final Text bossHealthText;
	
	public LevelViewBossOne(Group root, int heartsToDisplay) {
		super(root, heartsToDisplay);
		this.root = root;
		this.shieldImage = new ShieldImage(SHIELD_X_POSITION, SHIELD_Y_POSITION);
		bossHealthText = new Text();
		bossHealthText.setStroke(Color.BLACK);
		bossHealthText.setFont(new Font(20));
		bossHealthText.setFill(Color.WHITE);
		bossHealthText.setX(root.getScene().getWidth() - 250);
		bossHealthText.setY(43);
		healthBar = new Rectangle(HEALTH_BAR_X_POSITION, HEALTH_BAR_Y_POSITION, HEALTH_BAR_WIDTH, HEALTH_BAR_HEIGHT);
		healthBar.setFill(Color.RED);
		healthBarBorder = new Rectangle(HEALTH_BAR_X_POSITION, HEALTH_BAR_Y_POSITION, HEALTH_BAR_WIDTH, HEALTH_BAR_HEIGHT);
		healthBarBorder.setFill(Color.TRANSPARENT);
		healthBarBorder.setStroke(Color.BLACK);
		healthBarBorder.setStrokeWidth(2);
		root.getChildren().addAll(healthBarBorder, healthBar, bossHealthText);
	}
	
	private void addImagesToRoot() {
		root.getChildren().addAll(shieldImage);
	}
	
	public void showShield() {
		shieldImage.showShield();
		addImagesToRoot();
	}

	public void hideShield() {
		shieldImage.hideShield();
	}

	public void updateShield (double y){
		shieldImage.updateShield(y);
	}

	public void updateBossHealth(int bossHealth, int maxHealth) {
		double healthPercentage = (double) bossHealth / maxHealth;
		healthBar.setWidth(HEALTH_BAR_WIDTH * healthPercentage);
		bossHealthText.setText("" + bossHealth);
		healthBar.toFront();
		healthBarBorder.toFront();
		bossHealthText.toFront();
	}

}
