package com.randspy.tictactoe.console;

import com.randspy.tictactoe.spies.SpyInput;
import com.randspy.tictactoe.spies.SpyOutput;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class GameTest {

    private SpyInput input;
    private SpyOutput output;
    private Game game;

    private void setUserInputs(String ... numbers) {
        for (String number : numbers) {
            input.userInputs.add(number);
        }
    }

    @Before
    public void setUp() {
        output = new SpyOutput();
        input = new SpyInput();
        game = new Game(input, output);
    }

    @Test
    public void whenTieInTheEnd() {

        setUserInputs("5", "3", "4", "2", "9");
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
        assertEquals(expectedResult, endResult);
        assertEquals(expectedResult, endResult);

    }

    @Test
    public void whenComputerWins() {

        setUserInputs("5", "4", "7", "8");


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
        assertEquals(expectedResult, endResult);

    }

    @Test
    public void whenUserTriesToSetAlreadyUsedFieldShouldGetMessageWithWaring() {

        setUserInputs("5", "1", "4", "7", "8");
        game.run();

        String endResult = "Already occupied field.\n";

        String expectedResult =
                output.printedOutput.get(output.printedOutput.size() - 6);

        assertEquals(expectedResult, endResult);

    }

    @Test
    public void whenUserTriesToSetNumberBelowRangeShouldGetMessageWithWaring() {

        setUserInputs("0", "5", "4", "7", "8");
        game.run();

        String endResult = "Illegal input.\n";

        String expectedResult =
                output.printedOutput.get(output.printedOutput.size() - 3);

        assertEquals(expectedResult, endResult);

    }
}