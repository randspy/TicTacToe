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
    private boolean isHumanPlayerTurn;

    public Game(HumanInput input, HumanOutput output) {
        this.input = input;
        this.console = new ConsoleDisplay(output);
    }

    public void run() {

        init();

        console.printBoard(board, mapping);

        while (isNotFinished()) {
            if (isHumanPlayerTurn) {
                humanTurn();
            }
            else
            {
                computerTurn();
            }
        }

        gameFinalResult();

    }

    private void init() {
        board = new Board();
        result = new GameResult();
        computerPlayer = new ComputerPlayer();
        humanPlayer = new HumanPlayer(input);

        mapping = new PlayerToDisplayedCharacterMapping();
        mapping.map(humanPlayer, "x");
        mapping.map(computerPlayer, "o");

        switchToHumanPlayer();
    }

    private void humanTurn() {

        console.printInstructions();

        PositionOnBoard humanPlayerMove = humanPlayer.nextMove(board);

        if (isInvalidMove(humanPlayerMove)) {
            console.printInvalidMove();
        }
        else if (isPositionAlreadyOccupied(humanPlayerMove)) {
            console.printFieldIsOccupied();
        }
        else{
            humanMakesMove(humanPlayerMove);
            switchToComputerPlayer();
            console.printBoard(board, mapping);
        }
    }

    private void computerTurn() {
        computerMakesMove();
        switchToHumanPlayer();
        console.printBoard(board, mapping);
    }

    private void switchToComputerPlayer() {
        isHumanPlayerTurn = false;
    }

    private void switchToHumanPlayer() {
        isHumanPlayerTurn = true;
    }

    public boolean isNotFinished() {
        return !(board.isFull() || result.winnerIs(board).isPresent());
    }

    private boolean isInvalidMove(PositionOnBoard humanPlayerMove) {

        boolean isBellowRange = humanPlayerMove.getRow() < 0 ||
                                humanPlayerMove.getColumn() < 0;

        boolean isAboveRange =  humanPlayerMove.getRow() >= board.getDimension() ||
                                humanPlayerMove.getColumn() >= board.getDimension();

        return isBellowRange || isAboveRange;
    }

    private boolean isPositionAlreadyOccupied(PositionOnBoard humanPlayerMove) {
        return board.isPositionOccupied(humanPlayerMove);
    }

    private void humanMakesMove(PositionOnBoard humanPlayerMove) {
        board.setPlayerAtPosition(humanPlayer, humanPlayerMove);
    }

    private void computerMakesMove() {
        board.setPlayerAtPosition(computerPlayer, computerPlayer.nextMove(board));
    }

    private void gameFinalResult() {
        Optional<Player> winner = result.winnerIs(board);
        if (!winner.isPresent()) {
            console.printTie();
        }
        else if(winner.get().equals(computerPlayer)){
            console.printComputerWon();
        }
    }

}
