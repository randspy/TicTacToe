package com.randspy.tictactoe.console;

public class ConsoleInput{

    public int getInput() {
        try {
            return new Integer(System.console().readLine());
        } catch (java.lang.NumberFormatException e) {
            return 0;
        }
    }
}
