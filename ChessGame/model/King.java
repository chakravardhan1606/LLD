public class King extends Piece {
    public King(boolean isWhite) {
        super(isWhite, PieceType.King, new KingMovementStrategy());
    }
}
