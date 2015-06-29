package com.randspy.tictactoe.logic;

import java.util.Optional;

public abstract class Player {
    private PlayerId playerId = new PlayerId();

    public abstract Optional<PositionOnBoard> nextMove(Board board);

    public PlayerId getId(){
        return playerId;
    }
}
