public class PawnMovementStrategy implements MovementStrategy {

    private boolean isWhite;

    public PawnMovementStrategy(boolean isWhite) {
        this.isWhite = isWhite;
    }

    public boolean isValidMove(Cell from, Cell to) {
        int dx = to.getX() - from.getX();
        int dy = Math.abs(to.getY() - from.getY());
        if (isWhite) return dx == -1 && dy == 0;
        else         return dx ==  1 && dy == 0;
    }
}
