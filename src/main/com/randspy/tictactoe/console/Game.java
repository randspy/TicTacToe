package com.randspy.tictactoe.console;

import com.randspy.tictactoe.logic.*;

import java.util.Optional;

public class Game {
    private HumanInput input;
    private ConsoleDisplay console;

    private Board board;
    private GameResult result;
    private Player humanPlayer;
    private Player computerPlayer;
    private PlayerToDisplayedCharacterMapping mapping;

    public Game(HumanInput input, HumanOutput output) {
        this.input = input;
        this.console = new ConsoleDisplay(output);
    }

    public void run() {

        init();

        console.printBoard(board, mapping);

        while (isNotFinished()) {

            console.printInstructions();
            PositionOnBoard humanPlayerMove = humanPlayer.nextMove(board);

            if (isInvalidMove(humanPlayerMove)) {
                console.printInvalidMove();
                continue;
            }

            if (isAlreadyOccupiedPosition(humanPlayerMove)) {
                console.printFieldIsOccupied();
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

    private void init() {
        board = new Board();
        result = new GameResult();
        humanPlayer = new HumanPlayer(input);
        computerPlayer = new AIPlayer();

        mapping = new PlayerToDisplayedCharacterMapping();
        mapping.map(humanPlayer, "x");
        mapping.map(computerPlayer, "o");

    }

    public boolean isNotFinished() {
        return !(board.isFull() || result.winnerIs(board).isPresent());
    }

    private boolean isInvalidMove(PositionOnBoard humanPlayerMove) {

        boolean isBellowRange = humanPlayerMove.getRow() < 0 ||
                                humanPlayerMove.getColumn() < 0;

        boolean isAboveRange =  humanPlayerMove.getRow() >= Board.getDimension() ||
                                humanPlayerMove.getColumn() >= Board.getDimension();

        return isBellowRange || isAboveRange;
    }

    private boolean isAlreadyOccupiedPosition(PositionOnBoard humanPlayerMove) {
        return board.isPositionOccupied(humanPlayerMove);
    }

    private void humanMakesMove(PositionOnBoard humanPlayerMove) {
        board.setPlayerAtPosition(humanPlayer, humanPlayerMove);
    }

    private void computerMakesMove() {
        board.setPlayerAtPosition(computerPlayer, computerPlayer.nextMove(board));
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

}
