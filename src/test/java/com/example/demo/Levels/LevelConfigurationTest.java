package com.example.demo.Levels;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class LevelConfigurationTest {

    @Test
    public void testLevelConfiguration() {
        LevelConfiguration config = new LevelConfiguration(1);
        assertEquals(2, config.getKillsToAdvance());
        assertEquals(2, config.getTotalEnemies());
        assertTrue(config.getEnemySpawnProbability() > 0);
    }
}
