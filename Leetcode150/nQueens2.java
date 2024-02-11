//N Queens II:https://leetcode.com/problems/n-queens-ii/description/

/*
My solution which is influence from Striver's solution. The only diff is my changes the possile() implementation.
*/
class Cell{
    int x,y;
    public Cell(int x,int y){
        this.x = x;
        this.y = y;
    }
}
class Solution {
    int res = 0;
    private boolean possible(int x, int y,List<Cell> placedCells){
        for(int i=0;i<placedCells.size();i++){
            if(x == placedCells.get(i).x || 
            y == placedCells.get(i).y || 
            (x+y)==(placedCells.get(i).x+placedCells.get(i).y) ||
            (x-y)==(placedCells.get(i).x-placedCells.get(i).y))return false;
        }
        return true;
    }
    private void print(List<Cell> pc){
        for(int i=0;i<pc.size();i++){
            System.out.print("("+pc.get(i).x+","+pc.get(i).y+")");
        }
        System.out.println();
    }
    private void backtrack(int col, int n,List<Cell> placedCells){
        print(placedCells);
        if(col == n){
            res++;
            return;
        }
           
        for(int row=0;row<n;row++){
            if(possible(row,col,placedCells)){
                placedCells.add(new Cell(row,col));
                backtrack(col+1,n,placedCells);
                placedCells.remove(placedCells.size()-1);
            }
        }
    }
    public int totalNQueens(int n) {
        
        backtrack(0,n,new ArrayList<>());
        
        return res;
    }
}