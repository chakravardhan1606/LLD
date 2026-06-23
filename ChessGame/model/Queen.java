public class Queen extends Piece {
    public Queen(boolean isWhite) {
        super(isWhite, PieceType.Queen, new QueenMovementStrategy());
    }
}
