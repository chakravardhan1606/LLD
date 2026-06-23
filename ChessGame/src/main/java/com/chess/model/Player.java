package com.chess.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a chess player.
 */
public class Player {
    private final String name;
    private final Color color;
    private final List<Piece> killedPieces;

    public Player(String name, Color color) {
        this.name = name;
        this.color = color;
        this.killedPieces = new ArrayList<>();
    }

    public String getName() { return name; }
    public Color getColor() { return color; }
    public List<Piece> getKilledPieces() { return killedPieces; }

    public void addKilledPiece(Piece piece) {
        killedPieces.add(piece);
    }

    @Override
    public String toString() {
        return name + " (" + color + ")";
    }
}
