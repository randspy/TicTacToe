package com.randspy.tictactoe.spies;

import com.randspy.tictactoe.console.ConsoleOutput;

import java.util.LinkedList;

public class SpyOutput extends ConsoleOutput {
    @Override
    public void setOutput(String output) {
        printedOutput.push(output);
    }

    public LinkedList<String> printedOutput = new LinkedList<>();

}
