package com.randspy.tictactoe.logic;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ComputerPlayerTest {

    private ComputerPlayer computer;
    private PlayerId opponent;
    private Board board;

    private void expectMove(PositionOnBoard opponentsMove, int expectedRow, int expectedColumn) {
        board.setPlayerAtPosition(opponent, opponentsMove);
        PositionOnBoard positionOnBoard = computer.nextMove(board);
        board.setPlayerAtPosition(computer.getId(), positionOnBoard);
        assertEquals(expectedRow, positionOnBoard.getRow());
        assertEquals(expectedColumn, positionOnBoard.getColumn());
    }

    @Before
    public void setUp() throws Exception {
        computer = new ComputerPlayer();
        opponent = new PlayerId();
        board = new Board();
    }

    @Test
    public void computerWinningGame() {
        expectMove(new PositionOnBoard(1, 1), 0, 0);
        expectMove(new PositionOnBoard(2, 2), 0, 2);
        expectMove(new PositionOnBoard(2, 1), 0, 1);
    }


    @Test
    public void tieGame() {
        expectMove(new PositionOnBoard(1, 1), 0, 0);
        expectMove(new PositionOnBoard(0, 2), 2, 0);
        expectMove(new PositionOnBoard(1, 0), 1, 2);
        expectMove(new PositionOnBoard(2, 1), 0, 1);
    }

}