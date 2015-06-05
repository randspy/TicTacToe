package com.randspy.tictactoe.logic;

public class HumanPlayer implements Player {
    private final HumanInput input;

    public HumanPlayer(HumanInput input) {
        this.input = input;
    }

    @Override
    public int nextMove(Board board) {
        return input.getInput();
    }
}
