public class QueenMovementStrategy implements MovementStrategy {

    public boolean isValidMove(Cell from, Cell to) {
        int diffX = Math.abs(from.getX() - to.getX());
        int diffY = Math.abs(from.getY() - to.getY());
        boolean diagonal   = (diffX == diffY) && diffX != 0;
        boolean straightLine = (diffX == 0 || diffY == 0) && !(diffX == 0 && diffY == 0);
        return diagonal || straightLine;
    }
}
