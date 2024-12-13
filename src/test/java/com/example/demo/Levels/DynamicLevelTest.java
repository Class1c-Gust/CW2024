package com.example.demo.Levels;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class DynamicLevelTest {
    private DynamicLevel dynamicLevel;

    @BeforeEach
    public void setUp() {
        dynamicLevel = new DynamicLevel(750, 1300, 1);
    }

    @Test
    public void testInitializeFriendlyUnits() {
        dynamicLevel.initializeFriendlyUnits();
        assertNotNull(dynamicLevel.getUser());
    }

    @Test
    public void testCheckIfGameOver_UserDestroyed() {
        dynamicLevel.getUser().destroy(); // Simulate user destruction
        dynamicLevel.checkIfGameOver();
        assertTrue(dynamicLevel.userIsDestroyed());
    }

    @Test
    public void testCheckIfGameOver_KillsToAdvance() {
        dynamicLevel.getUser().incrementKillCount(); // Simulate kills
        dynamicLevel.checkIfGameOver();
        assertEquals(1, dynamicLevel.getUser().getNumberOfKills());
    }

    @Test
    public void testSpawnEnemyUnits() {
        dynamicLevel.spawnEnemyUnits();
        assertTrue(dynamicLevel.getCurrentNumberOfEnemies() <= dynamicLevel.getConfig().getTotalEnemies());
    }

    @Test
    public void testInstantiateLevelView() {
        HeartBar heartBar = dynamicLevel.instantiateLevelView();
        assertNotNull(heartBar);
    }
}
