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
        String printedConsole =
                "-------\n" +
                "| | | |\n" +
                "-------\n" +
                "| | | |\n" +
                "-------\n" +
                "| | | |\n" +
                "-------\n";

        Board board = new Board();
        PrintBoard printBoard = new PrintBoard();

        assertEquals(printedConsole,
                printBoard.print(board, new PlayerToDisplayedCharacterMapping()));
    }

    @Test
    public void printFilledBoard() {
        String printedConsole =
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
        mapping.map(xPlayer, "x");

        Player oPlayer = new HumanPlayer(null);
        mapping.map(oPlayer, "o");

        Board board = new Board();
        for (int idx = 0; idx < Board.getNumberOfBoardFields(); idx++) {

            boolean isEven = idx % 2 == 0;
            board.setPlayerAtPosition(isEven ? xPlayer : oPlayer, new PositionOnBoard(idx));
        }

        assertEquals(printedConsole, new PrintBoard().print(board, mapping));
    }
}