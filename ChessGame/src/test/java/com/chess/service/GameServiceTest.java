package com.chess.service;

import com.chess.exception.InvalidMoveException;
import com.chess.model.Color;
import com.chess.model.GameStatus;
import com.chess.model.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GameServiceTest {

    private GameService game;

    @BeforeEach
    void setUp() {
        Player white = new Player("White", Color.WHITE);
        Player black = new Player("Black", Color.BLACK);
        game = new GameService(white, black);
    }

    @Test
    void testInitialStatus() {
        assertEquals(GameStatus.ACTIVE, game.getStatus());
    }

    @Test
    void testWhiteMovesFirst() {
        assertEquals(Color.WHITE, game.getCurrentPlayer().getColor());
    }

    @Test
    void testValidPawnMove() {
        // e2 -> e4 (white pawn two-step)
        assertDoesNotThrow(() -> game.makeMove(6, 4, 4, 4));
        assertEquals(Color.BLACK, game.getCurrentPlayer().getColor());
    }

    @Test
    void testInvalidMoveThrows() {
        // White pawn cannot move three squares
        assertThrows(InvalidMoveException.class, () -> game.makeMove(6, 4, 3, 4));
    }

    @Test
    void testMoveWrongColorThrows() {
        // White's turn — trying to move a black pawn
        assertThrows(InvalidMoveException.class, () -> game.makeMove(1, 4, 3, 4));
    }

    @Test
    void testKnightJumpsOverPieces() {
        // White knight g1->f3
        assertDoesNotThrow(() -> game.makeMove(7, 6, 5, 5));
    }
}
