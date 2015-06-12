package com.randspy.tictactoe.console;

import com.randspy.tictactoe.logic.*;

import java.util.Optional;

public class Game {
    private HumanInput input;
    private DisplayInConsole console;

    private Board board;
    private GameResult result;
    private Player humanPlayer;
    private Player computerPlayer;
    private PlayerToDisplayedCharacterMapping mapping;

    public Game(HumanInput input, HumanOutput output) {
        this.input = input;
        this.console = new DisplayInConsole(output);
    }

    public void run() {

        init();

        console.printBoard(board, mapping);

        while (isNotFinished()) {

            console.printInstruction();
            PositionOnBoard humanPlayerMove = humanPlayer.nextMove(board);

            if (isInvalidMove(humanPlayerMove)) {
                console.printThatFieldIsOccupied();
                continue;
            }

            humanMakesMove(humanPlayerMove);

            console.printBoard(board, mapping);

            if (isNotFinished()) {
                computerMakesMove();
                console.printBoard(board, mapping);
            }
        }

        gameFinalResult(computerPlayer);

    }

    private boolean isInvalidMove(PositionOnBoard humanPlayerMove) {
        return board.isPositionOccupied(humanPlayerMove);
    }

    private void computerMakesMove() {
        board.setPlayerAtPosition(computerPlayer, computerPlayer.nextMove(board));
    }

    private void humanMakesMove(PositionOnBoard humanPlayerMove) {
        board.setPlayerAtPosition(humanPlayer, humanPlayerMove);
    }

    private void init() {
        board = new Board();
        result = new GameResult();
        humanPlayer = new HumanPlayer(input);
        computerPlayer = new AIPlayer();

        mapping = new PlayerToDisplayedCharacterMapping();
        mapping.map(humanPlayer, "x");
        mapping.map(computerPlayer, "o");

    }

    private void gameFinalResult(Player computerPlayer) {
        Optional<Player> winner = result.winnerIs(board);
        if (!winner.isPresent()) {
            console.printTie();
        }
        else if(winner.get().equals(computerPlayer)){
            console.printComputerWon();
        }
    }

    public boolean isNotFinished() {
        return !(board.isFull() || result.winnerIs(board).isPresent());
    }

}
