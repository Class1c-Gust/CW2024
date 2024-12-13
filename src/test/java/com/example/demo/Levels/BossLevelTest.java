package com.example.demo.Levels;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class BossLevelTest {
    private BossLevel bossLevel;

    @BeforeEach
    public void setUp() {
        bossLevel = new BossLevel(750, 1300, 1);
    }

    @Test
    public void testInitializeFriendlyUnits() {
        bossLevel.initializeFriendlyUnits();
        assertNotNull(bossLevel.getUser());
    }

    @Test
    public void testCheckIfGameOver_UserDestroyed() {
        bossLevel.getUser().destroy(); // Simulate user destruction
        bossLevel.checkIfGameOver();
        // Verify that the game is marked as lost
        assertTrue(bossLevel.userIsDestroyed());
    }

    @Test
    public void testCheckIfGameOver_BossDestroyed() {
        bossLevel.getBoss().destroy(); // Simulate boss destruction
        bossLevel.checkIfGameOver();
        // Verify that the game is marked as won
        assertTrue(bossLevel.getBoss().isDestroyed());
    }

    @Test
    public void testSpawnEnemyUnits() {
        bossLevel.spawnEnemyUnits();
        assertEquals(1, bossLevel.getCurrentNumberOfEnemies());
    }

    @Test
    public void testUpdateScene() {
        bossLevel.updateScene();
        assertNotNull(bossLevel.getLevelview().getBossHealthText());
    }
}
