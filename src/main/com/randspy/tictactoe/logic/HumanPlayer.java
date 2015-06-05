package com.randspy.tictactoe.logic;

public class HumanPlayer implements Player {
    private final HumanInput input;

    public HumanPlayer(HumanInput input) {
        this.input = input;
    }

    @Override
    public PositionOnBoard nextMove(Board board) {
        return new PositionOnBoard(input.getInput());
    }
}
