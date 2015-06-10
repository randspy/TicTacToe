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

    @Before
    public void setUp() {
        output = new SpyOutput();
        input = new SpyInput();
    }

    @Test
    public void onlyHumanGame_toBeChangeInFuture() {

        for (int idx = 1; idx <= 9; idx++) {
            input.userInputs.add(idx);
        }
        Game game = new Game(input, output);
        game.run();

        String endResult =
                "-------\n" +
                "|x|o|x|\n" +
                "-------\n" +
                "|o|x|o|\n" +
                "-------\n" +
                "|x| | |\n" +
                "-------\n";
        String expectedResult = output.printedOutput.getFirst();
        assertEquals(endResult, expectedResult);
    }
}