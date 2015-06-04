package com.randspy.tictactoe.logic;

public class Game {
    private Board board = new Board();

    public void play(Player player, int positionOnBoard) {
        board.setPlayerAtPosition(player, positionOnBoard);
    }

    public Board getBoard() {
        return board;
    }

    public boolean isFinished() {
        return board.isFull();
    }
}
