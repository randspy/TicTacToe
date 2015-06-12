package com.randspy.tictactoe.console;

import com.randspy.tictactoe.logic.HumanInput;
import com.randspy.tictactoe.logic.HumanOutput;
import org.junit.Before;
import org.junit.Test;

import java.util.LinkedList;

import static org.junit.Assert.assertEquals;

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
    public void whenTieInTheEnd() {

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
                        "-------\n" +
                        "There was a tie.\n";

        String expectedResult = output.printedOutput.get(1) + output.printedOutput.get(0);
        assertEquals(endResult, expectedResult);

    }

    @Test
    public void whenComputerWins() {

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
                        "-------\n" +
                        "Computer won.\n";

        String expectedResult = output.printedOutput.get(1) + output.printedOutput.get(0);
        assertEquals(endResult, expectedResult);

    }

    @Test
    public void whenUserTriesToSetAlreadyUsedFieldShouldGetMessageWithWaring() {

        input.userInputs.add(5);
        input.userInputs.add(1);
        input.userInputs.add(4);
        input.userInputs.add(7);
        input.userInputs.add(8);
        game.run();

        String endResult = "Already occupied field.\n";

        String expectedResult =
                output.printedOutput.get(output.printedOutput.size() - 6);

        assertEquals(endResult, expectedResult);

    }
}