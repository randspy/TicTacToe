package com.randspy.tictactoe.console;

import com.randspy.tictactoe.logic.HumanOutput;


public class ConsoleOutput implements HumanOutput{
    @Override
    public void setOutput(String output) {
        System.out.print(output);
    }
}
