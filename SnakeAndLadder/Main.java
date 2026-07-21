import model.Color;
import model.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Main {
    public static void main(String[] args) {
        List<Player> players = new ArrayList<>();
        players.add(
                new Player(
                        UUID.randomUUID().toString(),
                        "Alice",
                        Color.Red));
        players.add(
                new Player(
                        UUID.randomUUID().toString(),
                        "Bob",
                        Color.Blue));
        players.add(
                new Player(
                        UUID.randomUUID().toString(),
                        "Charlie",
                        Color.White));

        SnakeAndLadder game = new SnakeAndLadder(players, 10);
        game.play();
    }
}

/*
1- Multi Player game. Played on a grid of size 100. Each cell will have a number.
2- Some of the cell will have ladder which will jump to higher number.
3- Some cell will have a snake which will take you down to tail number.
4- Player will in turn roll a die(1-6). Based on the value user will advance.

User Action -> Dice Roll
*/
