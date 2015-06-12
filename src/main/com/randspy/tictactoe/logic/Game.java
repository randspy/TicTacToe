package com.randspy.tictactoe.logic;

import com.randspy.tictactoe.console.PlayerToDisplayedCharacterMapping;
import com.randspy.tictactoe.console.PrintBoard;

import java.util.Optional;

public class Game {
    private HumanInput input;
    private HumanOutput output;

    private Board board;
    private GameResult result;
    private Player humanPlayer;
    private Player computerPlayer;
    private PlayerToDisplayedCharacterMapping mapping;

    public Game(HumanInput input, HumanOutput output) {
        this.input = input;
        this.output = output;
    }

    public void run() {

        init();

        printBoard();

        while (isNotFinished()) {

            printInstruction();
            PositionOnBoard humanPlayerMove = humanPlayer.nextMove(board);

            if (isInvalidMove(humanPlayerMove)) {
                printThatFieldIsOccupied();
                continue;
            }

            humanMakesMove(humanPlayerMove);

            printBoard();

            if (isNotFinished()) {
                computerMakesMove();
                printBoard();
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

    private void printBoard() {
        output.setOutput(new PrintBoard().print(board, mapping));
    }

    private void init() {
        board = new Board();
        result = new GameResult();
        humanPlayer = new HumanPlayer(input);
        computerPlayer = new AIPlayer(humanPlayer);

        mapping = new PlayerToDisplayedCharacterMapping();
        mapping.map(humanPlayer, "x");
        mapping.map(computerPlayer, "o");

    }

    private void gameFinalResult(Player computerPlayer) {
        Optional<Player> winner = result.winnerIs(board);
        if (!winner.isPresent()) {
            printTie();
        }
        else if(winner.get().equals(computerPlayer)){
            printComputerWon();
        }
    }

    private void printThatFieldIsOccupied() {
        output.setOutput("Already occupied field.\n");
    }

    private void printComputerWon() {
        output.setOutput("Computer won.\n");
    }

    private void printTie() {
        output.setOutput("There was a tie.\n");
    }

    private void printInstruction() {
        output.setOutput("Make move (from 1-9) : ");
    }

    public boolean isNotFinished() {
        return !(board.isFull() || result.winnerIs(board).isPresent());
    }

}
