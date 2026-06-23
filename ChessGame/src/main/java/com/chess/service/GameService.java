package com.chess.service;

import com.chess.exception.InvalidMoveException;
import com.chess.model.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Core game logic: orchestrates turns, validates moves, and tracks game state.
 */
public class GameService {
    private final Board board;
    private final Player player1; // WHITE
    private final Player player2; // BLACK
    private Player currentPlayer;
    private GameStatus status;
    private final List<Move> moveHistory;

    public GameService(Player player1, Player player2) {
        this.board = new Board();
        this.player1 = player1;
        this.player2 = player2;
        this.currentPlayer = player1; // White moves first
        this.status = GameStatus.ACTIVE;
        this.moveHistory = new ArrayList<>();
    }

    public void makeMove(int fromRow, int fromCol, int toRow, int toCol) {
        if (status != GameStatus.ACTIVE) {
            throw new InvalidMoveException("Game is already over.");
        }

        Cell from = board.getCell(fromRow, fromCol);
        Cell to = board.getCell(toRow, toCol);

        validateMove(from, to);

        Move move = new Move(currentPlayer, from, to);

        // Capture opponent's piece
        if (!to.isEmpty()) {
            Piece captured = to.getPiece();
            captured.setKilled(true);
            currentPlayer.addKilledPiece(captured);

            if (captured instanceof com.chess.model.piece.King) {
                status = (currentPlayer == player1) ? GameStatus.WHITE_WIN : GameStatus.BLACK_WIN;
            }
        }

        // Execute move
        to.setPiece(from.getPiece());
        from.setPiece(null);

        moveHistory.add(move);
        System.out.println("Move: " + move);

        if (status == GameStatus.ACTIVE) {
            switchTurn();
        }
    }

    private void validateMove(Cell from, Cell to) {
        if (from.isEmpty()) {
            throw new InvalidMoveException("No piece at " + from);
        }

        Piece piece = from.getPiece();
        if (piece.getColor() != currentPlayer.getColor()) {
            throw new InvalidMoveException("It is " + currentPlayer.getName() + "'s turn (" + currentPlayer.getColor() + ").");
        }

        List<Cell> validMoves = piece.getValidMoves(from, board);
        boolean isValid = validMoves.stream().anyMatch(c -> c.getRow() == to.getRow() && c.getCol() == to.getCol());

        if (!isValid) {
            throw new InvalidMoveException("Invalid move for " + piece.getSymbol().trim() + " from " + from + " to " + to);
        }
    }

    private void switchTurn() {
        currentPlayer = (currentPlayer == player1) ? player2 : player1;
    }

    public Board getBoard() { return board; }
    public Player getCurrentPlayer() { return currentPlayer; }
    public GameStatus getStatus() { return status; }
    public List<Move> getMoveHistory() { return moveHistory; }

    public void printBoard() {
        board.print();
        if (status == GameStatus.ACTIVE) {
            System.out.println("Current turn: " + currentPlayer);
        } else {
            System.out.println("Game over: " + status);
        }
    }
}
