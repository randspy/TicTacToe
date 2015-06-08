package com.randspy.tictactoe.console;

import com.randspy.tictactoe.logic.Board;
import com.randspy.tictactoe.logic.PositionOnBoard;

import java.util.Optional;

public class PrintBoard {
    private Board board;
    private PlayerToDisplayedCharacterMapping mapping;
    private int row;

    public String print(Board board, PlayerToDisplayedCharacterMapping mapping) {

        init(board, mapping);
        return printHorizontalSeparator() +
                printRow() +
                printHorizontalSeparator() +
                printRow() +
                printHorizontalSeparator() +
                printRow() +
                printHorizontalSeparator();
    }

    private void init(Board board, PlayerToDisplayedCharacterMapping mapping) {
        this.board = board;
        this.mapping = mapping;
        this.row = 0;
    }

    private String printHorizontalSeparator() {
        return "-------\n";
    }

    private String printRow() {
        final String rowSeparator = "|";
        String printedRow = rowSeparator;

        for (int idx = 0; idx < Board.getDimension(); idx++) {
            Optional<String> playersCharacter =
                    mapping.getCharacter(board.getPlayerAtPosition(new PositionOnBoard(row, idx)));

            final String emptyField = " ";
            final String gameFiled = playersCharacter.isPresent() ? playersCharacter.get() : emptyField;

            printedRow += gameFiled + rowSeparator;
        }

        ++row;
        return printedRow + "\n";
    }
}
