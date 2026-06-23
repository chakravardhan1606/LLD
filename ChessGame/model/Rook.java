public class Rook extends Piece {
    public Rook(boolean isWhite) {
        super(isWhite, PieceType.Rook, new RookMovementStrategy());
    }
}
