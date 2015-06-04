package com.randspy.tictactoe.console;

import com.randspy.tictactoe.logic.Board;

public class PrintBoard {
    private Board board;

    public PrintBoard(Board board) {
        this.board = board;
    }

    public String print() {

        return printHorizontalSeparator() +
                printRow() +
                printHorizontalSeparator() +
                printRow() +
                printHorizontalSeparator() +
                printRow() +
                printHorizontalSeparator();
    }

    private String printRow() {
        return "| | | |\n";
    }

    private String printHorizontalSeparator() {
        return "-------\n";
    }
}
