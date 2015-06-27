package com.randspy.tictactoe.console;

import com.randspy.tictactoe.logic.PlayerId;
import org.junit.Before;
import org.junit.Test;

import java.util.UUID;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class PlayerToDisplayedCharacterMappingTest {

    private PlayerId playerId;

    @Before
    public void setUp() throws Exception {
        mapping = new PlayerToDisplayedCharacterMapping();
        playerId = new PlayerId(UUID.randomUUID());
    }

    private PlayerToDisplayedCharacterMapping mapping;

    @Test
    public void whenNoMappingExistsReturnNothing() {
        assertFalse(mapping.getCharacter(playerId).isPresent());
    }

    @Test
    public void whenMappingExistsReturnCharacter() {
        mapping.map(playerId, "x");
        assertEquals("x", mapping.getCharacter(playerId).get());
    }
}