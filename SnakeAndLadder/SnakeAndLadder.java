import manager.Board;
import model.Player;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class SnakeAndLadder {
    private Board board;
    private Queue<Player> players;

    public SnakeAndLadder(List<Player> playerList, int size) {
        board = new Board(size);
        players = new LinkedList<>();
        for (Player player : playerList) {
            players.offer(player);
        }
        initializeBoard();
    }

    private void initializeBoard() {
        // Snakes
        board.addSnake(99, 54);
        board.addSnake(70, 55);
        board.addSnake(52, 42);
        board.addSnake(25, 2);

        // Ladders
        board.addLadder(6, 25);
        board.addLadder(11, 40);
        board.addLadder(46, 90);
        board.addLadder(60, 85);
    }

    public void play() {
        while (players.size() > 1) {
            Player player = players.poll();
            int dice = player.roll();
            System.out.println("--------------------------------");
            System.out.println(player.getPlayerName() + " rolled : " + dice);

            int before = board.getPlayerPosition(player.getColor());
            boolean won = board.move(player.getColor(), dice);
            int after = board.getPlayerPosition(player.getColor());

            System.out.println(
                    player.getPlayerName() +
                            " moved from " +
                            before +
                            " to " +
                            after
            );

            if (won) {
                System.out.println(player.getPlayerName() + " wins!");
                break;
            }
            players.offer(player);
        }
    }
}
