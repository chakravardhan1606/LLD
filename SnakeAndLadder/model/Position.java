package model;

import java.util.HashSet;
import java.util.Set;

public class Position {
    int row;
    int col;
    private Set<Color> colors;

    public Position(int row, int col) {
        this.row = row;
        this.col = col;
        colors = new HashSet<>();
    }

    public Set<Color> getColors() {
        return colors;
    }

    public void addColors(Color color) {
        colors.add(color);
    }

    public void removeColor(Color color) {
        colors.remove(color);
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getCol() {
        return col;
    }

    public void setCol(int col) {
        this.col = col;
    }
}
