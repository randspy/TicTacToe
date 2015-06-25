package com.randspy.tictactoe.logic;

import com.randspy.tictactoe.spies.SpyInput;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class HumanPlayerTest {

    private SpyInput input;
    private HumanPlayer player;

    private void expectInputToBeSetAtPosition(String consoleInput, int row, int column) {
        input.userInputs.add(consoleInput);
        PositionOnBoard position = player.nextMove(new Board());
        assertEquals(row, position.getRow());
        assertEquals(column, position.getColumn());
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
     expectInputToBeSetAtPosition("0", 0, -1);
    }

    @Test
    public void whenNumberAboveRangeThenNotValidBoardPosition() {
     expectInputToBeSetAtPosition("10", 3, 0);
    }

    @Test
    public void whenNoInputInputThenNotValidBoardPosition() {
        expectInputToBeSetAtPosition("", 0, -1);
    }

    @Test
    public void whenInvalidInputThenNotValidBoardPosition() {
        expectInputToBeSetAtPosition("a", 0, -1);
    }
}