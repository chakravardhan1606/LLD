package manager;

import model.Color;

import java.util.HashMap;
import java.util.Map;

public class Board {
    private final int size;
    private final int totalCells;
    private final Map<Integer, Integer> snakes;
    private final Map<Integer, Integer> ladders;
    private final Map<Color, Integer> playerPositions;

    public Board(int size) {
        this.size = size;
        this.totalCells = size * size;
        snakes = new HashMap<>();
        ladders = new HashMap<>();
        playerPositions = new HashMap<>();
        initializePlayers();
    }

    private void initializePlayers() {
        playerPositions.put(Color.Red, 1);
        playerPositions.put(Color.Blue, 1);
        playerPositions.put(Color.Yellow, 1);
        playerPositions.put(Color.White, 1);
    }

    public void addSnake(int head, int tail) {
        snakes.put(head, tail);
    }

    public void addLadder(int start, int end) {
        ladders.put(start, end);
    }

    public int getPlayerPosition(Color color) {
        return playerPositions.get(color);
    }

    public boolean move(Color color, int diceValue) {
        int current = playerPositions.get(color);
        // Need exact dice to win
        if (current + diceValue > totalCells) {
            return false;
        }
        int next = current + diceValue;
        // Snake
        if (snakes.containsKey(next)) {
            next = snakes.get(next);
        }
        // Ladder
        else if (ladders.containsKey(next)) {
            next = ladders.get(next);
        }
        playerPositions.put(color, next);
        return next == totalCells;
    }
}
