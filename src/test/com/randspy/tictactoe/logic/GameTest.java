package com.randspy.tictactoe.logic;

import org.junit.Before;
import org.junit.Test;

import java.util.LinkedList;

import static org.junit.Assert.*;

public class GameTest {

    class SpyInput implements HumanInput {
        @Override
        public int getInput() {
            return userInputs.pop();
        }

        public LinkedList<Integer> userInputs = new LinkedList<>();
    }

    class SpyOutput implements HumanOutput {
        @Override
        public void setOutput(String output) {
            printedOutput.push(output);
        }

        public LinkedList<String> printedOutput = new LinkedList<>();

    }

    private SpyInput input;
    private SpyOutput output;
    private Game game;

    @Before
    public void setUp() {
        output = new SpyOutput();
        input = new SpyInput();
        game = new Game(input, output);
    }

    @Test
    public void tie() {

        input.userInputs.add(5);
        input.userInputs.add(3);
        input.userInputs.add(4);
        input.userInputs.add(2);
        input.userInputs.add(9);

        game.run();

        String endResult =
                "-------\n" +
                "|o|x|x|\n" +
                "-------\n" +
                "|x|x|o|\n" +
                "-------\n" +
                "|o|o|x|\n" +
                "-------\n";

        String expectedResult = output.printedOutput.getFirst();
        assertEquals(endResult, expectedResult);

    }

    @Test
    public void computerWins() {

        input.userInputs.add(5);
        input.userInputs.add(4);
        input.userInputs.add(7);
        input.userInputs.add(8);

        game.run();

        String endResult =
                "-------\n" +
                "|o|o|o|\n" +
                "-------\n" +
                "|x|x|o|\n" +
                "-------\n" +
                "|x|x| |\n" +
                "-------\n";

        String expectedResult = output.printedOutput.getFirst();
        assertEquals(endResult, expectedResult);

    }
}