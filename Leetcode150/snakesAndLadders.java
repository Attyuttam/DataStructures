//Snakes and ladders

class Cell{
    int x;
    int y;
    int hashCode;


    public Cell(int x, int y){
        this.x = x;
        this.y = y;
        this.hashCode = Objects.hash(x, y);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Cell that = (Cell) o;
        return x == that.x && y == that.y;
    }

    @Override
    public int hashCode() {
        return this.hashCode;
    }
}
class Solution {
    HashMap<Integer, Cell> numToCell;
    HashMap<Cell, Integer> cellToNum;
    int minMoves = Integer.MAX_VALUE;

    private void initializeMap(int n){
        numToCell = new HashMap<>();
        cellToNum  = new HashMap<>();
        int v = 1;
        for(int i=n-1;i>=0;i--){
            for(int j=0;j<n;j++){
                Cell c = new Cell(i,j);
                numToCell.put(v,c);
                cellToNum.put(c,v);
                v++;
            }
        }
    }
    private void bfs(int x,int y,int moves,int currCell, int[][] board){
        if(x<0 || y<0 || x>=board.length || y>=board.length)return;
        moves++;
        if(x == 0 &&  y == 0){
            minMoves = Math.min(minMoves, moves);
            return;
        }else if(board[x][y] == -1){
            for(int i=currCell+1;i<=Math.min(currCell+6,Math.pow(board.length,2));i++){
                Cell newCell = numToCell.get(i);
                bfs(newCell.x, newCell.y, moves,i,board);
            }
        }else{
            Cell newCell = numToCell.get(board[x][y]);
            int newCellX = newCell.x;
            int newCellY = newCell.y;

            bfs(newCellX, newCellY,moves,board[x][y],board);
        }
    }
    public int snakesAndLadders(int[][] board) {
        initializeMap(board.length);
        bfs(board);
        return minMoves;
    }
}