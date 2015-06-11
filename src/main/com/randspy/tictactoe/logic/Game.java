package com.randspy.tictactoe.logic;

import com.randspy.tictactoe.console.PlayerToDisplayedCharacterMapping;
import com.randspy.tictactoe.console.PrintBoard;

public class Game {
    private final HumanInput input;
    private final HumanOutput output;

    public Game(HumanInput input, HumanOutput output) {
        this.input = input;
        this.output = output;
    }

    public void run() {

        GameToBeRemoved gameToBeRemoved = new GameToBeRemoved();
        Player humanPlayer = new HumanPlayer(input);
        Player computerPlayer = new AIPlayer(humanPlayer);
        PlayerToDisplayedCharacterMapping mapping =
                new PlayerToDisplayedCharacterMapping();

        mapping.map(humanPlayer, "x");
        mapping.map(computerPlayer, "o");

        while (!gameToBeRemoved.isFinished()) {
            gameToBeRemoved.play(humanPlayer);
            output.setOutput(new PrintBoard().print(gameToBeRemoved.getBoard(), mapping));
            if (!gameToBeRemoved.isFinished()) {
                gameToBeRemoved.play(computerPlayer);

                output.setOutput(new PrintBoard().print(gameToBeRemoved.getBoard(), mapping));
            }
        }

    }
}
