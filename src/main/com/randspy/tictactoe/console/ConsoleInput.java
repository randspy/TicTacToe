package com.randspy.tictactoe.console;

import com.randspy.tictactoe.logic.HumanInput;

public class ConsoleInput implements HumanInput {
    private Integer offset = 1;

    @Override
    public int getInput() {
        return new Integer(System.console().readLine()) - offset;
    }
}
