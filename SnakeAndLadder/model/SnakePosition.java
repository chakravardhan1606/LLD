package model;

public class SnakePosition extends Position {
    private Position position;

    public SnakePosition(int row, int col, Position position) {
        super(row, col);
        this.position = position;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }
}
