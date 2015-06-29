package com.randspy.tictactoe.console;

import com.randspy.tictactoe.logic.*;

import java.util.Optional;

public class Game {
    private ConsoleInput input;
    private ConsoleDisplay console;

    private Board board;
    private GameResult result;
    private Player humanPlayer;
    private Player computerPlayer;
    private PlayerToDisplayedCharacterMapping mapping;
    private boolean isHumanPlayerTurn;

    public Game(ConsoleInput input, ConsoleOutput output) {
        this.input = input;
        this.console = new ConsoleDisplay(output);
    }

    public void run() {

        init();

        printBoard();

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
        mapping.map(humanPlayer.getId(), "x");
        mapping.map(computerPlayer.getId(), "o");

        switchToHumanPlayer();
    }

    private void printBoard() {

        console.printBoard(board, mapping);
    }

    private void humanTurn() {

        console.printInstructions();

        Optional<PositionOnBoard> humanPlayerMove = humanPlayer.nextMove(board);

        if (!humanPlayerMove.isPresent()) {
            console.printInvalidMove();
        }
        else if (isPositionAlreadyOccupied(humanPlayerMove.get())) {
            console.printFieldIsOccupied();
        }
        else{
            humanMakesMove(humanPlayerMove.get());
            switchToComputerPlayer();
            printBoard();
        }
    }

    private void computerTurn() {
        computerMakesMove();
        switchToHumanPlayer();
        printBoard();
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

    private boolean isPositionAlreadyOccupied(PositionOnBoard humanPlayerMove) {
        return board.isPositionOccupied(humanPlayerMove);
    }

    private void humanMakesMove(PositionOnBoard humanPlayerMove) {
        board.setPlayerAtPosition(humanPlayer.getId(), humanPlayerMove);
    }

    private void computerMakesMove() {
        board.setPlayerAtPosition(computerPlayer.getId(), computerPlayer.nextMove(board).get());
    }

    private void gameFinalResult() {
        Optional<PlayerId> winner = result.winnerIs(board);
        if (!winner.isPresent()) {
            console.printTie();
        }
        else if(winner.get().equals(computerPlayer.getId())){
            console.printComputerWon();
        }
    }

}
