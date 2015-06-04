package com.randspy.tictactoe.console;

import com.randspy.tictactoe.logic.Board;
import org.junit.Ignore;
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
        PrintBoard printBoard = new PrintBoard(board);
        assertEquals(printedConsole, printBoard.print());
    }

    @Ignore
    @Test
    public void printFilledBoard() {
        String printedConsole =
                "-------\n" +
                "|x|o|x|\n" +
                "-------\n" +
                "|o|x|o|\n" +
                "-------\n" +
                "|o|x|o|\n" +
                "-------\n";

        Board board = new Board();
        PrintBoard printBoard = new PrintBoard(board);
        assertEquals(printedConsole, printBoard.print());
    }


}