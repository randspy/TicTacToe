package com.randspy.tictactoe.spies;

import com.randspy.tictactoe.console.ConsoleInput;

import java.util.LinkedList;

public class SpyInput extends ConsoleInput {
    @Override
    public String getInput() {
        return userInputs.pop();
    }

    public LinkedList<String> userInputs = new LinkedList<>();
}