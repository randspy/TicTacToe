package com.randspy.tictactoe.spies;

import com.randspy.tictactoe.console.ConsoleInput;

import java.util.LinkedList;

public class SpyInput extends ConsoleInput {
    @Override
    public int getInput() {
        return userInputs.pop();
    }

    public LinkedList<Integer> userInputs = new LinkedList<>();
}