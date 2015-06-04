package com.randspy.tictactoe.logic;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class GameTest {

    private Game game;

    @Before
    public void setUp() throws Exception {
        game = new Game();
    }

    @Test
    public void whenNoMoveGetEmptyBoard() {
        assertEquals(new Board(), game.getBoard());
    }

    @Test
    public void whenOneTheFieldsAreOccupiedGameIsFinished() {

        int leftUpCorner = 0;
        Player player = new HumanPlayer();

        Board expectedBoard = new Board();
        expectedBoard.setPlayerAtPosition(player, leftUpCorner);

        game.play(player, leftUpCorner);
        assertEquals(expectedBoard, game.getBoard());
        assertFalse(game.isFinished());
    }

    @Test
    public void whenAllTheFieldsAreOccupiedGameIsFinished() {

        Player player = new HumanPlayer();

        Board expectedBoard = new Board();

        for (int idx = 0; idx < Board.getNumberOfBoardFields(); idx++) {
            expectedBoard.setPlayerAtPosition(player, idx);
            game.play(player, idx);
        }

        assertEquals(expectedBoard, game.getBoard());
        assertTrue(game.isFinished());
    }
}