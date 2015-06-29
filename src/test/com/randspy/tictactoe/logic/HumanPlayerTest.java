package com.randspy.tictactoe.logic;

import com.randspy.tictactoe.spies.SpyInput;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class HumanPlayerTest {

    private SpyInput input;
    private HumanPlayer player;

    private void expectInputToBeSetAtPosition(String consoleInput, int row, int column) {
        input.userInputs.add(consoleInput);
        PositionOnBoard position = player.nextMove(new Board()).get();
        assertEquals(row, position.getRow());
        assertEquals(column, position.getColumn());
    }

    private void expectInvalidInput(String expected) {
        input.userInputs.add(expected);
        assertFalse(player.nextMove(new Board()).isPresent());
    }

    @Before
    public void setUp() throws Exception {
        input = new SpyInput();
        player = new HumanPlayer(input);
    }

    @Test
    public void whenNumberInRangeThenValidBoardPosition() {
        expectInputToBeSetAtPosition("1", 0, 0);
        expectInputToBeSetAtPosition("2", 0, 1);
        expectInputToBeSetAtPosition("3", 0, 2);
        expectInputToBeSetAtPosition("4", 1, 0);
        expectInputToBeSetAtPosition("5", 1, 1);
        expectInputToBeSetAtPosition("6", 1, 2);
        expectInputToBeSetAtPosition("7", 2, 0);
        expectInputToBeSetAtPosition("8", 2, 1);
        expectInputToBeSetAtPosition("9", 2, 2);
    }

    @Test
    public void whenNumberBelowRangeThenNotValidBoardPosition() {
        String expected = "0";
        expectInvalidInput(expected);
    }

    @Test
    public void whenNumberAboveRangeThenNotValidBoardPosition() {
        expectInvalidInput("10");
    }

    @Test
    public void whenNoInputInputThenNotValidBoardPosition() {
        expectInvalidInput("");
    }

    @Test
    public void whenInvalidInputThenNotValidBoardPosition() {
        expectInvalidInput("a");
    }
}