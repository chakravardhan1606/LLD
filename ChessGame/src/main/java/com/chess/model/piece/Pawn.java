package com.chess.model.piece;

import com.chess.model.Board;
import com.chess.model.Cell;
import com.chess.model.Color;
import com.chess.model.Piece;

import java.util.ArrayList;
import java.util.List;

public class Pawn extends Piece {

    public Pawn(Color color) {
        super(color);
    }

    @Override
    public List<Cell> getValidMoves(Cell from, Board board) {
        List<Cell> moves = new ArrayList<>();
        int row = from.getRow();
        int col = from.getCol();

        // White moves up (decreasing row), Black moves down (increasing row)
        int direction = (color == Color.WHITE) ? -1 : 1;
        int startRow   = (color == Color.WHITE) ? 6 : 1;

        // One step forward
        int nextRow = row + direction;
        if (board.isValidPosition(nextRow, col) && board.getCell(nextRow, col).isEmpty()) {
            moves.add(board.getCell(nextRow, col));

            // Two steps forward from starting position
            int twoStep = row + 2 * direction;
            if (row == startRow && board.isValidPosition(twoStep, col) && board.getCell(twoStep, col).isEmpty()) {
                moves.add(board.getCell(twoStep, col));
            }
        }

        // Diagonal captures
        for (int dc : new int[]{-1, 1}) {
            int captureCol = col + dc;
            if (board.isValidPosition(nextRow, captureCol)) {
                Cell target = board.getCell(nextRow, captureCol);
                if (!target.isEmpty() && target.getPiece().getColor() != color) {
                    moves.add(target);
                }
            }
        }

        return moves;
    }

    @Override
    public String getSymbol() {
        return color == Color.WHITE ? "P " : "p ";
    }
}
