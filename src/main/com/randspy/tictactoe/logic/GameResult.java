package com.randspy.tictactoe.logic;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class GameResult {
    private Optional<Player> result;

    public Optional<Player> winnerIs(Board board) {

        init();

        for(Player player : getPlayersPresentOnBoard(board))
        {
            winnerInRow(board, player);
            winnerInColumn(board, player);
            winnerInDiagonalFromLeftToRight(board, player);
            winnerInDiagonalFromRightToLeft(board, player);
        }

        return result;
    }

    private void init() {
        result = Optional.ofNullable(null);
    }

    private Set<Player> getPlayersPresentOnBoard(Board board) {
        Set<Player> players = new HashSet<>();

        for (int idx = 0; idx < Board.getNumberOfRows(); idx++) {
            for (int idy = 0; idy < Board.getNumberOfColumns(); idy++) {
                Player player = board.getPlayerAtPosition(new PositionOnBoard(idx, idy));
                if (player != null) {
                    players.add(player);
                }
            }
        }
        return players;
    }

    private void winnerInDiagonalFromRightToLeft(Board board, Player player) {
        if (doesSequenceBelongToOnePlayer(board.getPlayersAtDiagonalFromRightToLeft(), player)) {
            result = Optional.of(player);
        }
    }

    private void winnerInDiagonalFromLeftToRight(Board board, Player player) {
        if (doesSequenceBelongToOnePlayer(board.getPlayersAtDiagonalFromLeftToRight(), player)) {
            result = Optional.of(player);
        }
    }

    private void winnerInColumn(Board board, Player player) {
        for (int idx = 0; idx < Board.getNumberOfRows(); idx++) {
            if (doesSequenceBelongToOnePlayer(board.getPlayersAtColumn(idx), player)) {
                result = Optional.of(player);
            }
        }
    }

    private void winnerInRow(Board board, Player player) {
        for (int idx = 0; idx < Board.getNumberOfColumns(); idx++) {
            if (doesSequenceBelongToOnePlayer(board.getPlayersAtRow(idx), player)) {
                result = Optional.of(player);
            }
        }
    }

    private boolean doesSequenceBelongToOnePlayer(Player[] playersAtRow, Player player) {
        return Arrays.stream(playersAtRow)
                .filter(field -> field != null && field.equals(player))
                .count() == Board.getNumberOfColumns();
    }

}
