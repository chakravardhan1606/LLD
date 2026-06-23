public class Piece {
    boolean isWhite;
    PieceType type;
    boolean isAlive = true;
    MovementStrategy moveStrategy;

    public Piece(boolean isWhite, PieceType type, MovementStrategy moveStrategy) {
        this.isWhite = isWhite;
        this.type = type;
        this.moveStrategy = moveStrategy;
    }

    public boolean isWhite() {
        return isWhite;
    }

    public PieceType getType() {
        return type;
    }

    public boolean isAlive() {
        return isAlive;
    }

    public void setAlive(boolean isAlive) {
        this.isAlive = isAlive;
    }

    public boolean isValidMove(Cell from, Cell to) {
        return moveStrategy.isValidMove(from, to);
    }
}
