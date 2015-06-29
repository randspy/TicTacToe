package com.randspy.tictactoe.logic;

import com.randspy.tictactoe.console.ConsoleInput;

import java.util.Optional;

public class HumanPlayer extends Player {
    private final ConsoleInput input;
    private Board board;

    public HumanPlayer(ConsoleInput input) {
        this.input = input;
    }

    @Override
    public Optional<PositionOnBoard> nextMove(Board board) {

        this.board = board;

        int boardStartingPosition = 1;
        int userInput = toNumber(input.getInput()) - boardStartingPosition;
        int row = userInput / board.getDimension();
        int column = userInput % board.getDimension();

        return isValidMove(row, column) ?
                Optional.of(new PositionOnBoard(row, column)) :
                Optional.ofNullable(null);
    }

    public int toNumber(String input) {
        try {
            return new Integer(input);
        } catch (java.lang.NumberFormatException e) {
            return 0;
        }
    }

    private boolean isValidMove(int row, int column) {
        return isInRange(row) && isInRange(column);
    }

    private boolean isInRange(int position) {
        return position >= 0 && position < board.getDimension();
    }


}
