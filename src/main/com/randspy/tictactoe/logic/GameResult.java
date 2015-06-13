package com.randspy.tictactoe.logic;

import java.util.Arrays;
import java.util.Optional;

public class GameResult {
    private Optional<Player> result;
    private Board board;

    public Optional<Player> winnerIs(Board board) {

        init(board);

        for(Player player : board.getPresentPlayers())
        {
            winnerInRow(player);
            winnerInColumn(player);
            winnerInDiagonalFromLeftToRight(player);
            winnerInDiagonalFromRightToLeft(player);
        }

        return result;
    }

    private void init(Board board) {
        result = Optional.ofNullable(null);
        this.board = board;
    }

    private void winnerInDiagonalFromRightToLeft(Player player) {
        if (doesSequenceBelongToOnePlayer(board.getPlayersAtDiagonalFromRightToLeft(), player)) {
            result = Optional.of(player);
        }
    }

    private void winnerInDiagonalFromLeftToRight(Player player) {
        if (doesSequenceBelongToOnePlayer(board.getPlayersAtDiagonalFromLeftToRight(), player)) {
            result = Optional.of(player);
        }
    }

    private void winnerInColumn(Player player) {
        for (int idx = 0; idx < board.getDimension(); idx++) {
            if (doesSequenceBelongToOnePlayer(board.getPlayersAtColumn(idx), player)) {
                result = Optional.of(player);
            }
        }
    }

    private void winnerInRow(Player player) {
        for (int idx = 0; idx < board.getDimension(); idx++) {
            if (doesSequenceBelongToOnePlayer(board.getPlayersAtRow(idx), player)) {
                result = Optional.of(player);
            }
        }
    }

    private boolean doesSequenceBelongToOnePlayer(Player[] playersAtRow, Player player) {
        return Arrays.stream(playersAtRow)
                .filter(field -> field != null && field.equals(player))
                .count() == board.getDimension();
    }

}
