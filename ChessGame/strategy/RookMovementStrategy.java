public class RookMovementStrategy implements MovementStrategy {

    public boolean isValidMove(Cell from, Cell to) {
        int diffX = Math.abs(from.getX() - to.getX());
        int diffY = Math.abs(from.getY() - to.getY());
        return (diffX == 0 || diffY == 0) && !(diffX == 0 && diffY == 0);
    }
}
