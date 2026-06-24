public class Board {
   
    private Cell[][] cells;
    private int size;

    public Board(int size){

        this.size = size;
        cells = new Cell[size][size];
       initializeCells();
    }

    private void initializeCells(){
        
        for(int i=0;i<size;i++){
            for(int j=0;j<size;j++){
                cells[i][j] = new Cell(i,j);
            }
        }

        for( int i =0; i<size; i++){
            for(int j=0; j<size; j++){
                if(i==1){
                    cells[i][j].setPiece(PieceFactory.createPiece(PieceType.Pawn, false));
                }else if(i==6){
                    cells[i][j].setPiece(PieceFactory.createPiece(PieceType.Pawn, true));
                }else if(i==0 || i==7){
                    boolean isWhite = (i==7);
                    if(j==0 || j==7){
                        cells[i][j].setPiece(PieceFactory.createPiece(PieceType.Rook, isWhite));
                    }else if(j==1 || j==6){
                        cells[i][j].setPiece(PieceFactory.createPiece(PieceType.Knight, isWhite));
                    }else if(j==2 || j==5){
                        cells[i][j].setPiece(PieceFactory.createPiece(PieceType.Bishop, isWhite));
                    }else if(j==3){
                        cells[i][j].setPiece(PieceFactory.createPiece(PieceType.Queen, isWhite));
                    }else{
                        cells[i][j].setPiece(PieceFactory.createPiece(PieceType.King, isWhite));
                    }
                }
            }
        }
    }

    private boolean isValidCell(int x , int y){
        return x>=0 && x<size && y>=0 && y<size;
    }

    public boolean makeMove(Cell from, Cell to){
        if(from.getPiece() == null){
                        System.out.println("Invalid Move");

            return false;
        }

        if(!isValidCell(to.getX(), to.getY())){
                        System.out.println("Invalid Move");

            return false;
        }

        if(!from.getPiece().isValidMove(from, to)){
            System.out.println("Invalid Move");
            return false;
        }

        to.setPiece(from.getPiece());
        from.setPiece(null);
        return true;
    }

    public Cell getCell(int x, int y) {
        return cells[x][y];
    }

    public Status getStatus() {
        boolean whiteKingAlive = false;
        boolean blackKingAlive = false;

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                Piece p = cells[i][j].getPiece();
                if (p != null && p.getType() == PieceType.King) {
                    if (p.isWhite()) whiteKingAlive = true;
                    else             blackKingAlive = true;
                }
            }
        }

        if (!whiteKingAlive) return Status.BLACK_WIN;
        if (!blackKingAlive) return Status.WHITE_WIN;
        return Status.ACTIVE;
    }

}
