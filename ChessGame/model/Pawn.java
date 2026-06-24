public class Pawn extends Piece {
    public Pawn(boolean isWhite) {
        super(isWhite, PieceType.Pawn, new PawnMovementStrategy(isWhite));
    }
}
