package com.randspy.tictactoe.console;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class PrintUserInstructionsTest {

    @Test
    public void userInstructionsAreShown() {
        assertEquals(
                "Make move (from 1-9) : ",
                new PrintUserInstructions().print());
    }
}