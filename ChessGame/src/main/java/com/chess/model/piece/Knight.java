package com.chess.model.piece;

import com.chess.model.Board;
import com.chess.model.Cell;
import com.chess.model.Color;
import com.chess.model.Piece;

import java.util.ArrayList;
import java.util.List;

public class Knight extends Piece {

    public Knight(Color color) {
        super(color);
    }

    @Override
    public List<Cell> getValidMoves(Cell from, Board board) {
        List<Cell> moves = new ArrayList<>();
        int[][] offsets = {
            {-2, -1}, {-2, 1}, {-1, -2}, {-1, 2},
            { 1, -2}, { 1, 2}, { 2, -1}, { 2, 1}
        };

        for (int[] offset : offsets) {
            int r = from.getRow() + offset[0];
            int c = from.getCol() + offset[1];
            if (board.isValidPosition(r, c)) {
                Cell cell = board.getCell(r, c);
                if (cell.isEmpty() || cell.getPiece().getColor() != color) {
                    moves.add(cell);
                }
            }
        }
        return moves;
    }

    @Override
    public String getSymbol() {
        return color == Color.WHITE ? "N " : "n ";
    }
}
