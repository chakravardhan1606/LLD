public class KingMovementStrategy implements MovementStrategy {

    public boolean isValidMove(Cell from, Cell to) {
        int diffX = Math.abs(from.getX() - to.getX());
        int diffY = Math.abs(from.getY() - to.getY());
        return Math.max(diffX, diffY) == 1; 
    }
}
