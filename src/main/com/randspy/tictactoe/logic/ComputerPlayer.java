package com.randspy.tictactoe.logic;

import java.util.Set;
import java.util.UUID;

public class ComputerPlayer extends Player {
    private PlayerId opponent;
    private GameResult gameResult = new GameResult();
    private Board board;

    private class MinMaxResult{
        int score = 0;
        int row = -1;
        int column = -1;
    }

    @Override
    public PositionOnBoard nextMove(Board board) {

        init(board);

        int initialDepth = 9;
        MinMaxResult result = minMax(getId(), initialDepth);
        return new PositionOnBoard(result.row, result.column);
    }

    private void init(Board board) {
        this.board = board;
        opponent = getOpponent();
    }

    private PlayerId getOpponent() {
        Set<PlayerId> playersIds = board.getPresentPlayers();
        PlayerId opponent = new PlayerId(UUID.randomUUID());

        for (PlayerId playerId : playersIds) {
            if (playerId != getId()) {
                opponent = playerId;
            }
        }

        return opponent;
    }

    private MinMaxResult minMax(PlayerId playerId, int depth) {

        MinMaxResult result = new MinMaxResult();
        if (noMoreMovesPossible(depth)) {
            result.score = score(depth);
            return result;
        }

        result.score = getStartingBestScore(playerId);

        for (PositionOnBoard possibleMove : board.getEmptyFields()) {
            setMove(playerId, possibleMove);
            if (playerId == getId()) {
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
        } else if (gameResult.winnerIs(board).get() == getId()) {
            return depth + 1;
        } else {
            return -depth - 1;
        }
    }

    private int getStartingBestScore(PlayerId playerId) {
        return (playerId == getId()) ? Integer.MIN_VALUE : Integer.MAX_VALUE;
    }

    private void setMove(PlayerId playerId, PositionOnBoard possibleMove) {
        board.setPlayerAtPosition(playerId, possibleMove);
    }

    private void rollBackMove(PositionOnBoard possibleMove) {
        board.setPlayerAtPosition(null, possibleMove);
    }

    private MinMaxResult max(int depth, MinMaxResult result, PositionOnBoard possibleMove) {
        int score = minMax(opponent, depth - 1).score;
        if (score > result.score) {
            result.score = score;
            result.row = possibleMove.getRow();
            result.column = possibleMove.getColumn();

        }
        return  result;
    }

    private MinMaxResult min(int depth, MinMaxResult result, PositionOnBoard possibleMove) {
        int score = minMax(getId(), depth - 1).score;
        if (score < result.score) {
            result.score = score;
            result.row = possibleMove.getRow();
            result.column = possibleMove.getColumn();
        }
        return  result;
    }
}
