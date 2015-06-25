package com.randspy.tictactoe.console;

import com.randspy.tictactoe.logic.Board;
import com.randspy.tictactoe.spies.SpyOutput;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ConsoleDisplayTest {

    private ConsoleDisplay display;
    private SpyOutput output;


    private void expectOutput(String expected) {
        assertEquals(expected, output.printedOutput.getLast());
    }

    @Before
    public void setUp() throws Exception {
        output = new SpyOutput();
        display = new ConsoleDisplay(output);

    }

    @Test
    public void testPrintFieldIsOccupied() {
        display.printFieldIsOccupied();
        expectOutput("Already occupied field.\n");
    }

    @Test
    public void testPrintComputerWon(){
        display.printComputerWon();
        expectOutput("Computer won.\n");
    }

    @Test
    public void testPrintTie() {
        display.printTie();
        expectOutput("There was a tie.\n");
    }

    @Test
    public void testPrintInstructions() {
        display.printInstructions();
        expectOutput("Make move (from 1-9) : ");
    }

    @Test
    public void testPrintBoard() {
        display.printBoard(new Board(), new PlayerToDisplayedCharacterMapping());
        expectOutput("-------\n" +
                     "| | | |\n" +
                     "-------\n" +
                     "| | | |\n" +
                     "-------\n" +
                     "| | | |\n" +
                     "-------\n");
    }

    @Test
    public void testPrintInvalidMove() {
        display.printInvalidMove();
        expectOutput("Illegal input.\n");
    }
}