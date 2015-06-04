package com.randspy.tictactoe.console;

import com.randspy.tictactoe.logic.Board;

import java.util.Optional;

public class PrintBoard {
    private Board board;
    private PlayerToDisplayedCharacterMapping mapping;
    private int positionOnBoard = 0;

    public PrintBoard(Board board, PlayerToDisplayedCharacterMapping mapping) {
        this.board = board;
        this.mapping = mapping;
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
        final String rowSeparator = "|";
        String row = rowSeparator;

        for (int idx = positionOnBoard; idx < positionOnBoard + Board.getNumberOfColumns(); idx++) {
            Optional<String> playersCharacter =
                    mapping.getCharacter(board.getPlayerAtPosition(idx));

            final String emptyField = " ";
            final String gameFiled = playersCharacter.isPresent() ? playersCharacter.get() : emptyField;

            row += gameFiled + rowSeparator;
        }

        positionOnBoard += Board.getNumberOfColumns();
        return row + "\n";
    }

    private String printHorizontalSeparator() {
        return "-------\n";
    }
}
