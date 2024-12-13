package com.example.demo.Levels;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import javafx.scene.Group;

public class LevelViewBossTest {
    private LevelViewBoss levelViewBoss;

    @BeforeEach
    public void setUp() {
        levelViewBoss = new LevelViewBoss(new Group(), 5);
    }

    @Test
    public void testUpdateBossHealth() {
        levelViewBoss.updateBossHealth(3, 5);
        assertEquals("3", levelViewBoss.getBossHealthText().getText());
    }
}
