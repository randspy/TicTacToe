package com.randspy.tictactoe.logic;

public class PositionOnBoard {
    public PositionOnBoard(int value) {
        this.value = value;
    }

    public int get() {
        return value;
    }

    private int value = 0;
}
