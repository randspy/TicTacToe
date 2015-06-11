package com.randspy.tictactoe.logic;

import java.util.Arrays;
import java.util.Optional;

public class GameResult {
    private Optional<Player> result;

    public Optional<Player> winnerIs(Board board) {

        init();

        for(Player player : PlayersOnBoard.get(board))
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
        for (int idx = 0; idx < Board.getDimension(); idx++) {
            if (doesSequenceBelongToOnePlayer(board.getPlayersAtColumn(idx), player)) {
                result = Optional.of(player);
            }
        }
    }

    private void winnerInRow(Board board, Player player) {
        for (int idx = 0; idx < Board.getDimension(); idx++) {
            if (doesSequenceBelongToOnePlayer(board.getPlayersAtRow(idx), player)) {
                result = Optional.of(player);
            }
        }
    }

    private boolean doesSequenceBelongToOnePlayer(Player[] playersAtRow, Player player) {
        return Arrays.stream(playersAtRow)
                .filter(field -> field != null && field.equals(player))
                .count() == Board.getDimension();
    }

}
