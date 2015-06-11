package com.randspy.tictactoe.logic;

import java.util.HashSet;
import java.util.Set;

public class PlayersOnBoard {
    static public Set<Player> get(Board board) {
        Set<Player> players = new HashSet<>();

        for (int idx = 0; idx < Board.getDimension(); idx++) {
            for (int idy = 0; idy < Board.getDimension(); idy++) {
                Player player = board.getPlayerAtPosition(new PositionOnBoard(idx, idy));
                if (player != null) {
                    players.add(player);
                }
            }
        }
        return players;
    }

}
