package com.chess;

import com.chess.model.Color;
import com.chess.model.Player;
import com.chess.service.GameService;

/**
 * Entry point to demo the Chess LLD.
 */
public class ChessGame {
    public static void main(String[] args) {
        Player white = new Player("Alice", Color.WHITE);
        Player black = new Player("Bob",   Color.BLACK);

        GameService game = new GameService(white, black);

        System.out.println("=== Chess Game Start ===");
        game.printBoard();

        // Sample opening: e2->e4 (white pawn), e7->e5 (black pawn)
        game.makeMove(6, 4, 4, 4); // e2 -> e4
        game.makeMove(1, 4, 3, 4); // e7 -> e5

        // Knight: g1->f3, b8->c6
        game.makeMove(7, 6, 5, 5); // Ng1 -> f3
        game.makeMove(0, 1, 2, 2); // Nb8 -> c6

        System.out.println("\n=== Board after 4 moves ===");
        game.printBoard();
    }
}
