import java.util.ArrayList;
import java.util.Scanner;

public class ChessGame {

    private Board board;
    private Player player1;   // WHITE
    private Player player2;   // BLACK
    private boolean isWhiteTurn;
    private Status status;
    private ArrayList<Move> gameLog;

    public ChessGame(Player player1, Player player2) {
        this.player1     = player1;
        this.player2     = player2;
        this.board       = new Board(8);
        this.isWhiteTurn = true;
        this.status      = Status.ACTIVE;
        this.gameLog     = new ArrayList<>();
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);

        while (status == Status.ACTIVE) {
            Player current = isWhiteTurn ? player1 : player2;
            System.out.println("\n" + current.getName() + "'s turn (" + (current.isWhite() ? "White" : "Black") + ")");

            System.out.print("From (row col): ");
            int fromRow = scanner.nextInt();
            int fromCol = scanner.nextInt();

            System.out.print("To   (row col): ");
            int toRow = scanner.nextInt();
            int toCol = scanner.nextInt();

            Cell from = board.getCell(fromRow, fromCol);
            Cell to   = board.getCell(toRow,   toCol);

            // Check it's current player's piece
            if (from.getPiece() == null || from.getPiece().isWhite() != current.isWhite()) {
                System.out.println("That's not your piece!");
                continue;
            }

            // Board handles all validation and moves the piece
            if (board.makeMove(from, to)) {
                gameLog.add(new Move(from, to));  // log only
                status = board.getStatus();
                isWhiteTurn = !isWhiteTurn;
            }
        }

        System.out.println("Game Over Result: " + status);
        scanner.close();
    }

    public static void main(String[] args) {
        Player white = new Player("Alice", true);
        Player black = new Player("Bob",   false);
        new ChessGame(white, black).start();
    }
}
