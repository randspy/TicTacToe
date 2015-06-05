package com.randspy.tictactoe.logic;

import org.junit.Before;
import org.junit.Test;

import java.util.LinkedList;

import static org.junit.Assert.*;

public class GameTest {

    private class StubPlayer implements Player {
        @Override
        public PositionOnBoard nextMove(Board board) {
            return moves.pop();
        }

        public LinkedList<PositionOnBoard> moves = new LinkedList<>();
    }

    private Game game;
    private StubPlayer stubPlayer;


    @Before
    public void setUp() throws Exception {
        game = new Game();
        stubPlayer = new StubPlayer();
    }

    @Test
    public void whenNoMoveGetEmptyBoard() {
        assertEquals(new Board(), game.getBoard());
    }

    @Test
    public void whenOneTheFieldsAreOccupiedGameIsFinished() {

        int leftUpCorner = 0;

        StubPlayer player = new StubPlayer();
        player.moves.push(new PositionOnBoard(leftUpCorner));

        Board expectedBoard = new Board();
        expectedBoard.setPlayerAtPosition(player, new PositionOnBoard(leftUpCorner));

        game.play(player);
        assertEquals(expectedBoard, game.getBoard());
        assertFalse(game.isFinished());
    }

    @Test
    public void whenAllTheFieldsAreOccupiedGameIsFinished() {

        StubPlayer player = new StubPlayer();

        Board expectedBoard = new Board();

        for (int idx = 0; idx < Board.getNumberOfBoardFields(); idx++) {
            player.moves.push(new PositionOnBoard(idx));
            expectedBoard.setPlayerAtPosition(player, new PositionOnBoard(idx));
            game.play(player);
        }

        assertEquals(expectedBoard, game.getBoard());
        assertTrue(game.isFinished());
    }
}