package com.randspy.tictactoe.logic;

import java.util.List;

public class AIPlayer implements Player {
    private Player humanPlayer;

    public AIPlayer(Player humanPlayer) {
        this.humanPlayer = humanPlayer;
    }

    @Override
    public PositionOnBoard nextMove(Board board) {

        int[] result = minMax(board, this, 10);
        return new PositionOnBoard(result[1], result[2]);
    }

    private int[] minMax(Board board, Player player, int depth) {

        GameResult gameResult = new GameResult();
        if (board.isFull() || gameResult.winnerIs(board).isPresent() || depth == 0) {
            return new int[] {score(board, depth), 2, 2};
        }

        List<PositionOnBoard> availableMoves = board.getEmptyFields();
        int bestScore = (player == this) ? Integer.MIN_VALUE : Integer.MAX_VALUE;
        int currentScore = 0;

        int bestRow = -1;
        int bestCol = -1;

        for (PositionOnBoard position : availableMoves) {
            board.setPlayerAtPosition(player, position);
            if (player == this) {
                currentScore = minMax(board, humanPlayer, depth - 1)[0];
                if (currentScore > bestScore) {

                    bestScore = currentScore;
                    bestRow = position.getRow();
                    bestCol = position.getColumn();

                }
            }
            else
            {
                currentScore = minMax(board, this, depth - 1)[0];
                if (currentScore < bestScore) {
                    bestScore = currentScore;
                    bestRow = position.getRow();
                    bestCol = position.getColumn();
                }
            }
            board.setPlayerAtPosition(null, position);
        }

        return new int[] {bestScore, bestRow, bestCol};
    }

    private int score(Board board, int depth) {
        GameResult gameResult = new GameResult();
        if (!gameResult.winnerIs(board).isPresent()) {
            return 0;
        } else if (gameResult.winnerIs(board).get() == this) {
            return 10 * depth;
        } else {
            return -10 * depth;
        }
    }
}
