package com.randspy.tictactoe.logic;

import java.util.UUID;

public abstract class Player {
    private PlayerId playerId = new PlayerId(UUID.randomUUID());

    public abstract PositionOnBoard nextMove(Board board);

    public PlayerId getId(){
        return playerId;
    }
}
