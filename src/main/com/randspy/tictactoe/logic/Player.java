package com.randspy.tictactoe.logic;

public abstract class Player {
    private PlayerId playerId = new PlayerId();

    public abstract PositionOnBoard nextMove(Board board);

    public PlayerId getId(){
        return playerId;
    }
}
