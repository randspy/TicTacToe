package com.randspy.tictactoe.logic;

import java.util.*;

public class Board {
    private static final int DIMENSION = 3;

    private Player[][] boardElements = new Player[DIMENSION][DIMENSION];

    public static int getNumberOfBoardFields() {
        return DIMENSION * DIMENSION;
    }

    public static int getDimension() {
        return DIMENSION;
    }

    public void setPlayerAtPosition(Player player, PositionOnBoard position) {
        boardElements[position.getRow()][position.getColumn()] = player;
    }

    public boolean isFull() {

        for(Player[] row : boardElements) {
            for (Player elem : row) {
                if (elem == null) {
                    return false;
                }
            }
        }
        return true;
    }

    public Player getPlayerAtPosition(PositionOnBoard position) {
        return boardElements[position.getRow()][position.getColumn()];
    }

    public Player[] getPlayersAtRow(int idx) {
        return boardElements[idx];
    }

    public Player[] getPlayersAtColumn(int idx) {

        return Arrays.stream(boardElements).map(row -> row[idx]).toArray(Player[]::new);
    }

    public Player[] getPlayersAtDiagonalFromLeftToRight() {
        Player [] diagonal = new Player[DIMENSION];

        for (int idx = 0; idx < DIMENSION; idx++) {
            diagonal[idx] = boardElements[idx][idx];
        }
        return diagonal;
    }

    public Player[] getPlayersAtDiagonalFromRightToLeft() {
        Player [] diagonal = new Player[DIMENSION];

        int idy = DIMENSION - 1;
        for (int idx = 0; idx < DIMENSION; idx++, idy--) {
            diagonal[idx] = boardElements[idx][idy];
        }
        return diagonal;
    }

    public List<PositionOnBoard> getEmptyFields() {

        List<PositionOnBoard> positions = new LinkedList<>();

        for (int idx = 0; idx < boardElements.length; idx++) {
            for (int idy = 0; idy < boardElements[idx].length; idy++) {
                if (boardElements[idx][idy] == null) {
                    positions.add(new PositionOnBoard(idx, idy));
                }
            }
        }
        return positions;
    }

    public boolean isPositionOccupied(PositionOnBoard position) {
        return boardElements[position.getRow()][position.getColumn()] != null;
    }

    public Set<Player> getPresentPlayers() {

        Set<Player> players = new HashSet<>();

        for(Player[] row : boardElements) {
            for (Player elem : row) {
                players.add(elem);
            }
        }

        return players;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Board board = (Board) o;

        if (boardElements.length != board.boardElements.length) return false;

        for (int idx = 0; idx < boardElements.length; idx++) {
            if (!Arrays.equals(boardElements[idx], board.boardElements[idx])) return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        return boardElements != null ? Arrays.hashCode(boardElements) : 0;
    }
}
