public class Bishop extends Piece {
    public Bishop(boolean isWhite) {
        super(isWhite, PieceType.Bishop, new BishopMovementStrategy());
    }
}
