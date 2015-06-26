package com.randspy.tictactoe.console;

import com.randspy.tictactoe.logic.Board;
import com.randspy.tictactoe.logic.HumanPlayer;
import com.randspy.tictactoe.logic.Player;
import com.randspy.tictactoe.logic.PositionOnBoard;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class PrintBoardTest {

    @Test
    public void printCleanBoard() {
        String expectedConsoleOutput =
                "-------\n" +
                "| | | |\n" +
                "-------\n" +
                "| | | |\n" +
                "-------\n" +
                "| | | |\n" +
                "-------\n";

        Board board = new Board();
        PrintBoard printBoard = new PrintBoard();

        assertEquals(expectedConsoleOutput,
                printBoard.print(board, new PlayerToDisplayedCharacterMapping()));
    }

    @Test
    public void printFilledBoard() {
        String expectedConsoleOutput =
                "-------\n" +
                "|x|o|x|\n" +
                "-------\n" +
                "|o|x|o|\n" +
                "-------\n" +
                "|x|o|x|\n" +
                "-------\n";

        PlayerToDisplayedCharacterMapping mapping =
                new PlayerToDisplayedCharacterMapping();

        Player xPlayer = new HumanPlayer(null);
        mapping.map(xPlayer.getId(), "x");

        Player oPlayer = new HumanPlayer(null);
        mapping.map(oPlayer.getId(), "o");

        Board board = new Board();
        int numberOfFields = board.getDimension() * board.getDimension();
        for (int idx = 0; idx < numberOfFields; idx++) {

            boolean isEven = idx % 2 == 0;
            int row = idx / board.getDimension();
            int column = idx % board.getDimension();
            board.setPlayerAtPosition(isEven ? xPlayer.getId() : oPlayer.getId(), new PositionOnBoard(row, column));
        }

        assertEquals(expectedConsoleOutput, new PrintBoard().print(board, mapping));
    }
}