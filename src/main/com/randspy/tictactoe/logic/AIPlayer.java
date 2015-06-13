package com.randspy.tictactoe.logic;

import java.util.Set;

public class AIPlayer implements Player {
    private Player opponent;
    private GameResult gameResult = new GameResult();
    private Board board;

    private class MinMaxResult{
        int score = 0;
        int row = -1;
        int column = -1;
    }

    @Override
    public PositionOnBoard nextMove(Board board) {

        this.board = board;
        opponent = getOpponent();
        MinMaxResult result = minMax(this, 9);
        return new PositionOnBoard(result.row, result.column);
    }

    private Player getOpponent() {
        Set<Player> players = board.getPresentPlayers();
        Player opponent = null;

        for (Player player : players) {
            if (player != this) {
                opponent = player;
            }
        }

        return opponent == null ? new AIPlayer() : opponent;
    }

    private MinMaxResult minMax(Player player, int depth) {

        MinMaxResult result = new MinMaxResult();
        if (noMoreMovesPossible(depth)) {
            result.score = score(depth);
            return result;
        }

        result.score = getStartingBestScore(player);

        for (PositionOnBoard possibleMove : board.getEmptyFields()) {
            setMove(player, possibleMove);
            if (player == this) {
                result = max(depth, result, possibleMove);
            }
            else
            {
                result = min(depth, result, possibleMove);
            }
            rollBackMove(possibleMove);
        }

        return result;
    }

    private boolean noMoreMovesPossible(int depth) {
        return board.isFull() || gameResult.winnerIs(board).isPresent() || depth == 0;
    }

    private int score(int depth) {
        if (!gameResult.winnerIs(board).isPresent()) {
            return 0;
        } else if (gameResult.winnerIs(board).get() == this) {
            return depth + 1;
        } else {
            return -depth - 1;
        }
    }

    private int getStartingBestScore(Player player) {
        return (player == this) ? Integer.MIN_VALUE : Integer.MAX_VALUE;
    }

    private void setMove(Player player, PositionOnBoard possibleMove) {
        board.setPlayerAtPosition(player, possibleMove);
    }

    private void rollBackMove(PositionOnBoard possibleMove) {
        board.setPlayerAtPosition(null, possibleMove);
    }

    private MinMaxResult max(int depth, MinMaxResult result, PositionOnBoard possibleMove) {
        int currentScore = minMax(opponent, depth - 1).score;
        if (currentScore > result.score) {
            result.score = currentScore;
            result.row = possibleMove.getRow();
            result.column = possibleMove.getColumn();

        }
        return  result;
    }

    private MinMaxResult min(int depth, MinMaxResult result, PositionOnBoard possibleMove) {
        int currentScore = minMax(this, depth - 1).score;
        if (currentScore < result.score) {
            result.score = currentScore;
            result.row = possibleMove.getRow();
            result.column = possibleMove.getColumn();
        }
        return  result;
    }
}
