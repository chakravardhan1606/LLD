public class Knight extends Piece {
    public Knight(boolean isWhite) {
        super(isWhite, PieceType.Knight, new KnightMovementStrategy());
    }
}
