package com.randspy.tictactoe.logic;

import com.randspy.tictactoe.console.ConsoleInput;

public class HumanPlayer implements Player {
    private final ConsoleInput input;

    public HumanPlayer(ConsoleInput input) {
        this.input = input;
    }

    @Override
    public PositionOnBoard nextMove(Board board) {
        int boardStartingPosition = 1;
        int userInput = input.getInput() - boardStartingPosition;
        int row =  userInput / board.getDimension();
        int column = userInput % board.getDimension();
        return new PositionOnBoard(row, column);
    }
}
