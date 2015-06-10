package com.randspy.tictactoe.logic;

public class HumanPlayer implements Player {
    private final HumanInput input;

    public HumanPlayer(HumanInput input) {
        this.input = input;
    }

    @Override
    public PositionOnBoard nextMove(Board board) {
        int boardStartingPosition = 1;
        int userInput = input.getInput() - boardStartingPosition;
        int row =  userInput / 3;
        int column = userInput % 3;
        return new PositionOnBoard(row, column);
    }
}
