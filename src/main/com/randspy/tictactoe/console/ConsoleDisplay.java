package com.randspy.tictactoe.console;

import com.randspy.tictactoe.logic.Board;
import com.randspy.tictactoe.logic.HumanOutput;

public class ConsoleDisplay {

    private HumanOutput output;

    public ConsoleDisplay(HumanOutput output) {
        this.output = output;
    }

    public void printFieldIsOccupied() {
        output.setOutput("Already occupied field.\n");
    }

    public void printComputerWon() {
        output.setOutput("Computer won.\n");
    }

    public void printTie() {
        output.setOutput("There was a tie.\n");
    }

    public void printInstructions() {
        output.setOutput("Make move (from 1-9) : ");
    }

    public void printBoard(Board board, PlayerToDisplayedCharacterMapping mapping) {
        output.setOutput(new PrintBoard().print(board, mapping));
    }

    public void printInvalidMove() {
        output.setOutput("Illegal input.\n");
    }
}
