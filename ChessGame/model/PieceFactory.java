public class PieceFactory {

    public static Piece createPiece(PieceType type, boolean isWhite) {
        switch (type) {
            case King:   return new King(isWhite);
            case Queen:  return new Queen(isWhite);
            case Rook:   return new Rook(isWhite);
            case Bishop: return new Bishop(isWhite);
            case Knight: return new Knight(isWhite);
            case Pawn:   return new Pawn(isWhite);
            default:     throw new IllegalArgumentException("Unknown piece type: " + type);
        }
    }
}
