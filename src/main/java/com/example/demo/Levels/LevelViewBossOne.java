package com.example.demo.Levels;

import com.example.demo.config.Config;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class LevelViewBossOne extends HeartBar {
	private final Group root;
	private final Rectangle healthBar;
	private final Rectangle healthBarBorder;
	private final Text bossHealthText;
	
	public LevelViewBossOne(Group root, int heartsToDisplay) {
		super(root, heartsToDisplay);
		this.root = root;
		bossHealthText = new Text();
		bossHealthText.setStroke(Color.BLACK);
		bossHealthText.setFont(new Font(20));
		bossHealthText.setFill(Color.WHITE);
		bossHealthText.setX(root.getScene().getWidth() - 250);
		bossHealthText.setY(43);
		healthBar = new Rectangle(Config.Level.HEALTH_BAR_X_POSITION, Config.Level.HEALTH_BAR_Y_POSITION, Config.Level.HEALTH_BAR_WIDTH, Config.Level.HEALTH_BAR_HEIGHT);
		healthBar.setFill(Color.RED);
		healthBarBorder = new Rectangle(Config.Level.HEALTH_BAR_X_POSITION, Config.Level.HEALTH_BAR_Y_POSITION, Config.Level.HEALTH_BAR_WIDTH, Config.Level.HEALTH_BAR_HEIGHT);
		healthBarBorder.setFill(Color.TRANSPARENT);
		healthBarBorder.setStroke(Color.BLACK);
		healthBarBorder.setStrokeWidth(2);
		root.getChildren().addAll(healthBarBorder, healthBar, bossHealthText);
	}

	public void updateBossHealth(int bossHealth, int maxHealth) {
		double healthPercentage = (double) bossHealth / maxHealth;
		healthBar.setWidth(Config.Level.HEALTH_BAR_WIDTH * healthPercentage);
		bossHealthText.setText("" + bossHealth);
		healthBar.toFront();
		healthBarBorder.toFront();
		bossHealthText.toFront();
	}

}
