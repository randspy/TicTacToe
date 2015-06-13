package com.randspy.tictactoe.console;

import com.randspy.tictactoe.logic.HumanInput;

public class ConsoleInput implements HumanInput {

    @Override
    public int getInput() {
        try {
            return new Integer(System.console().readLine());
        } catch (java.lang.NumberFormatException e) {
            return new Integer(0);
        }
    }
}
