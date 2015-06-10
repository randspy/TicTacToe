package com.randspy.tictactoe;

import com.randspy.tictactoe.console.ConsoleInput;
import com.randspy.tictactoe.console.ConsoleOutput;
import com.randspy.tictactoe.logic.Game;

public class Main {

    public static void main(String[] args) {

        Game game = new Game(new ConsoleInput(), new ConsoleOutput());
        game.run();
    }
}
