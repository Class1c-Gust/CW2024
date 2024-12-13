package com.example.demo.Levels;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import javafx.scene.Group;

public class HeartBarTest {
    private HeartBar heartBar;

    @BeforeEach
    public void setUp() {
        heartBar = new HeartBar(new Group(), 5);
    }

    @Test
    public void testShowHeartDisplay() {
        heartBar.showHeartDisplay();
        assertEquals(5, heartBar.getContainer().getChildren().size());
    }

    @Test
    public void testUpdateHearts() {
        heartBar.updateHearts(3);
        assertEquals(3, heartBar.getContainer().getChildren().size());
    }
}
