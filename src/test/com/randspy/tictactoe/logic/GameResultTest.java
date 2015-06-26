package com.randspy.tictactoe.logic;

import org.junit.Before;
import org.junit.Test;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class GameResultTest {

    private Board board;
    private GameResult gameResult;

    private void setRowWithWinningPlayer(Player player, int rowNumber) {
        for (int idx = 0; idx < board.getDimension(); idx++) {
            board.setPlayerAtPosition(player.getId(), new PositionOnBoard(rowNumber, idx));
        }
    }

    private void setColumnWithWinningPlayer(Player player, int columnNumber) {
        for (int idx = 0; idx < board.getDimension(); idx++) {
            board.setPlayerAtPosition(player.getId(), new PositionOnBoard(idx, columnNumber));
        }
    }

    private void setDiagonalFromLeftToRightWithWinningPlayer(Player player) {
        for (int idx = 0; idx < board.getDimension(); idx++) {
            board.setPlayerAtPosition(player.getId(), new PositionOnBoard(idx, idx));
        }
    }

    private void setDiagonalFromRightToLeftWithWinningPlayer(Player player) {
        int idy = board.getDimension() - 1;
        for (int idx = 0; idx < board.getDimension(); idx++, idy--) {
                board.setPlayerAtPosition(player.getId(), new PositionOnBoard(idx, idy));
        }
    }

    @Before
    public void setUp() throws Exception {
        board = new Board();
        gameResult = new GameResult();
    }


    @Test
    public void noWinner() {
        assertFalse(gameResult.winnerIs(board).isPresent());
    }

    @Test
    public void whenFirstRowBelongsToOnePlayerHeIsTheWinner() {

        Player player = new HumanPlayer(null);

        setRowWithWinningPlayer(player, 0);

        Optional<PlayerId> winner = gameResult.winnerIs(board);
        assertTrue(winner.isPresent());
        assertEquals(player.getId(), winner.get());
    }

    @Test
    public void whenSecondRowBelongsToOnePlayerHeIsTheWinner() {

        Player player = new HumanPlayer(null);

        setRowWithWinningPlayer(player, 1);

        Optional<PlayerId> winner = gameResult.winnerIs(board);
        assertTrue(winner.isPresent());
        assertEquals(player.getId(), winner.get());
    }

    @Test
    public void whenColumnBelongsToOnePlayerHeIsTheWinner() {

        Player player = new HumanPlayer(null);

        setColumnWithWinningPlayer(player, 1);

        Optional<PlayerId> winner = gameResult.winnerIs(board);
        assertTrue(winner.isPresent());
        assertEquals(player.getId(), winner.get());
    }

    @Test
    public void whenDiagonalFromLeftToRightBelongsToOnePlayerHeIsTheWinner() {

        Player player = new HumanPlayer(null);

        setDiagonalFromLeftToRightWithWinningPlayer(player);

        Optional<PlayerId> winner = gameResult.winnerIs(board);
        assertTrue(winner.isPresent());
        assertEquals(player.getId(), winner.get());
    }

    @Test
    public void whenDiagonalFromRightToLeftBelongsToOnePlayerHeIsTheWinner() {

        Player player = new HumanPlayer(null);

        setDiagonalFromRightToLeftWithWinningPlayer(player);

        Optional<PlayerId> winner = gameResult.winnerIs(board);
        assertTrue(winner.isPresent());
        assertEquals(player.getId(), winner.get());
    }

    @Test
    public void whenManyPlayers() {

        Player winningPlayer = new HumanPlayer(null);
        Player losingPlayer = new HumanPlayer(null);

        setRowWithWinningPlayer(winningPlayer, 2);
        board.setPlayerAtPosition(losingPlayer.getId(), new PositionOnBoard(0, 0));

        Optional<PlayerId> winner = gameResult.winnerIs(board);
        assertTrue(winner.isPresent());
        assertEquals(winningPlayer.getId(), winner.get());
    }

}