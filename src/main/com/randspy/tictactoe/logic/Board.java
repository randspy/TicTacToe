package com.randspy.tictactoe.logic;

import java.util.Arrays;

public class Board {
    private static final int NUMBER_OF_BOARD_FIELDS = 9;

    private Player[] boardElements = new Player[NUMBER_OF_BOARD_FIELDS];

    public static int getNumberOfBoardFields() {
        return NUMBER_OF_BOARD_FIELDS;
    }

    public void setPlayerAtPosition(Player player, int positionOnBoard) {
        boardElements[positionOnBoard] = player;
    }

    public boolean isFull() {
        return Arrays.stream(boardElements)
                .filter(field -> field != null)
                .count() == NUMBER_OF_BOARD_FIELDS;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Board board = (Board) o;

        if (!Arrays.equals(boardElements, board.boardElements)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return boardElements != null ? Arrays.hashCode(boardElements) : 0;
    }
}
