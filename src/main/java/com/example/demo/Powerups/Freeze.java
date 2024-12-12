package com.example.demo.Powerups;

import com.example.demo.Planes.EnemyPlane;
import com.example.demo.Planes.FighterPlane;
import com.example.demo.Planes.UserPlane;
import com.example.demo.config.Config;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Group;
import javafx.scene.effect.Glow;
import javafx.util.Duration;

/**
 * Freeze powerup that when shot/collided with freezes planes within proximity
 */
public class Freeze extends Powerup {

    public Freeze(double initialYPos) {
        super(Config.Powerup.FREEZE_IMAGE_NAME, Config.Powerup.IMAGE_HEIGHT, initialYPos, true);
    }
    @Override
    public void updatePosition() {
        moveHorizontally(Config.Powerup.HORIZONTAL_VELOCITY);
    }

    //    public void updatePosition(int y) {
    //        this.setTranslateY(y);
    //    }
    //
    //    @Override
    //    public void updateActor() {
    //        updatePosition();
    ////        this.laser.updateActor();
    //
    //    }

//    @Override
//    public void activatePower(Group root, UserPlane user){
//        root.getChildren().add(this.laser);
//
//
//    }

    /**
     * freezes all planes within a 300 pixel proximity to the powerup for 10 seconds after destroyed
     * Causes effected planes to emit a glow effect
     * @param root The main scene
     * @param user The user plane
     */
    @Override
    public void activatePower(Group root, UserPlane user){
        this.destroy();
        Glow glow = new Glow();
        glow.setLevel(0.9);
        root.getChildren().forEach(node -> {
            if (node instanceof FighterPlane){
                FighterPlane plane = (FighterPlane) node;
                double distance = Math.sqrt(
                        Math.pow(plane.getTranslateX() - this.getTranslateX(), 2) + Math.pow(plane.getTranslateY() - this.getTranslateY(), 2)
                );

                if (distance <= 300){
                    plane.setDisabledStatus(true);
                    plane.setEffect(glow);
                    Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(10), e -> {
                        plane.setDisabledStatus(false);
                        plane.setEffect(null);
                    }));
                    timeline.setCycleCount(1);
                    timeline.play();
                }
            }
        });
    }

}

