package com.chess.model;

/**
 * Represents one move in the game.
 */
public class Move {
    private final Player player;
    private final Cell from;
    private final Cell to;
    private final Piece pieceMoved;
    private Piece pieceKilled;

    public Move(Player player, Cell from, Cell to) {
        this.player = player;
        this.from = from;
        this.to = to;
        this.pieceMoved = from.getPiece();
        this.pieceKilled = to.getPiece();
    }

    public Player getPlayer() { return player; }
    public Cell getFrom() { return from; }
    public Cell getTo() { return to; }
    public Piece getPieceMoved() { return pieceMoved; }
    public Piece getPieceKilled() { return pieceKilled; }

    @Override
    public String toString() {
        return player.getName() + ": " + pieceMoved.getSymbol().trim()
            + " " + from + " -> " + to
            + (pieceKilled != null ? " (captures " + pieceKilled.getSymbol().trim() + ")" : "");
    }
}
