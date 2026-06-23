public class Move {
    private Cell startCell;
    private Cell endCell;

    public Move(Cell startCell, Cell endCell) {
        this.startCell = startCell;
        this.endCell   = endCell;
    }

    public Cell getStartCell() { return startCell; }
    public Cell getEndCell()   { return endCell; }

    public boolean isValid() {
        if (startCell == null || endCell == null) return false;
        if (startCell.getPiece() == null) return false;
        if (endCell.getPiece() != null &&
            endCell.getPiece().isWhite() == startCell.getPiece().isWhite()) return false;
        return true;
    }
}
