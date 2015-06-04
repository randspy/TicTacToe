package com.randspy.tictactoe;

import com.randspy.tictactoe.console.PlayerToDisplayedCharacterMapping;
import com.randspy.tictactoe.console.PrintBoard;
import com.randspy.tictactoe.logic.Game;
import com.randspy.tictactoe.logic.HumanPlayer;
import com.randspy.tictactoe.logic.Player;

public class Main {

    public static void main(String[] args) {

        Game game = new Game();
        Player humanPlayer = new HumanPlayer();
        PlayerToDisplayedCharacterMapping mapping =
                new PlayerToDisplayedCharacterMapping();

        mapping.map(humanPlayer, "x");

        while (!game.isFinished()) {
            game.play(humanPlayer, new Integer(System.console().readLine()));
            System.out.print(new PrintBoard(game.getBoard(), mapping).print());
        }
    }
}
