package com.randspy.tictactoe.console;

import com.randspy.tictactoe.logic.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class PlayerToDisplayedCharacterMapping {
    Map<Player, String> mapping = new HashMap<>();

    public void map(Player player, String character) {
        mapping.put(player, character);
    }

    public Optional<String> getCharacter(Player player) {
        String character = mapping.get(player);
        return character == null ? Optional.ofNullable(null) : Optional.ofNullable(character);
    }
}
