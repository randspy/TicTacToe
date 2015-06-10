package com.randspy.tictactoe.console;

import com.randspy.tictactoe.logic.HumanInput;

public class ConsoleInput implements HumanInput {

    @Override
    public int getInput() {
        return new Integer(System.console().readLine());
    }
}
