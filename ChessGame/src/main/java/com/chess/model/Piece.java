package com.chess.model;

import java.util.List;

/**
 * Abstract base class for all chess pieces.
 */
public abstract class Piece {
    protected final Color color;
    protected boolean killed;

    protected Piece(Color color) {
        this.color = color;
        this.killed = false;
    }

    public Color getColor() { return color; }
    public boolean isKilled() { return killed; }
    public void setKilled(boolean killed) { this.killed = killed; }

    /**
     * Returns all valid moves this piece can make from the given cell on the given board.
     */
    public abstract List<Cell> getValidMoves(Cell from, Board board);

    /**
     * Returns the display symbol for this piece.
     */
    public abstract String getSymbol();
}
