package com.randspy.tictactoe.logic;

public class GameToBeRemoved {
    private Board board = new Board();
    private GameResult result = new GameResult();

    public void play(Player player) {
        board.setPlayerAtPosition(player, player.nextMove(board));
    }

    public Board getBoard() {
        return board;
    }

    public boolean isFinished() {
        return board.isFull() || result.winnerIs(board).isPresent();
    }
}
