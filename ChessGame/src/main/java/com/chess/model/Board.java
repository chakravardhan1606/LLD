package com.chess.model;

/**
 * Represents the 8x8 chess board.
 */
public class Board {
    private final Cell[][] cells;

    public Board() {
        cells = new Cell[8][8];
        for (int r = 0; r < 8; r++) {
            for (int c = 0; c < 8; c++) {
                cells[r][c] = new Cell(r, c);
            }
        }
        initializePieces();
    }

    private void initializePieces() {
        // Black pieces (row 0 = rank 8)
        placeMajorPieces(0, Color.BLACK);
        placePawns(1, Color.BLACK);

        // White pieces (row 7 = rank 1)
        placePawns(6, Color.WHITE);
        placeMajorPieces(7, Color.WHITE);
    }

    private void placeMajorPieces(int row, Color color) {
        cells[row][0].setPiece(new piece.Rook(color));
        cells[row][1].setPiece(new piece.Knight(color));
        cells[row][2].setPiece(new piece.Bishop(color));
        cells[row][3].setPiece(new piece.Queen(color));
        cells[row][4].setPiece(new piece.King(color));
        cells[row][5].setPiece(new piece.Bishop(color));
        cells[row][6].setPiece(new piece.Knight(color));
        cells[row][7].setPiece(new piece.Rook(color));
    }

    private void placePawns(int row, Color color) {
        for (int c = 0; c < 8; c++) {
            cells[row][c].setPiece(new piece.Pawn(color));
        }
    }

    public Cell getCell(int row, int col) {
        if (!isValidPosition(row, col)) {
            throw new IllegalArgumentException("Invalid position: (" + row + "," + col + ")");
        }
        return cells[row][col];
    }

    public boolean isValidPosition(int row, int col) {
        return row >= 0 && row < 8 && col >= 0 && col < 8;
    }

    public void print() {
        System.out.println("  a  b  c  d  e  f  g  h");
        for (int r = 0; r < 8; r++) {
            System.out.print((8 - r) + " ");
            for (int c = 0; c < 8; c++) {
                Cell cell = cells[r][c];
                if (cell.isEmpty()) {
                    System.out.print(".  ");
                } else {
                    System.out.print(cell.getPiece().getSymbol() + " ");
                }
            }
            System.out.println(8 - r);
        }
        System.out.println("  a  b  c  d  e  f  g  h");
    }
}
