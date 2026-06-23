package com.chess.model.piece;

import com.chess.model.Board;
import com.chess.model.Cell;
import com.chess.model.Color;
import com.chess.model.Piece;

import java.util.ArrayList;
import java.util.List;

public class Queen extends Piece {

    public Queen(Color color) {
        super(color);
    }

    @Override
    public List<Cell> getValidMoves(Cell from, Board board) {
        List<Cell> moves = new ArrayList<>();
        // Queen = Rook + Bishop directions
        int[][] directions = {
            {1, 0}, {-1, 0}, {0, 1}, {0, -1},
            {1, 1}, {1, -1}, {-1, 1}, {-1, -1}
        };

        for (int[] dir : directions) {
            int r = from.getRow() + dir[0];
            int c = from.getCol() + dir[1];
            while (board.isValidPosition(r, c)) {
                Cell cell = board.getCell(r, c);
                if (cell.isEmpty()) {
                    moves.add(cell);
                } else {
                    if (cell.getPiece().getColor() != color) moves.add(cell);
                    break;
                }
                r += dir[0];
                c += dir[1];
            }
        }
        return moves;
    }

    @Override
    public String getSymbol() {
        return color == Color.WHITE ? "Q " : "q ";
    }
}
