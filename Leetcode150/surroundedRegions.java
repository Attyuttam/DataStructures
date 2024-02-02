//surrounded regions:https://leetcode.com/problems/surrounded-regions/description/?envType=study-plan-v2&envId=top-interview-150


/*
This solution could only beat 37%
*/
class Solution {
    private void mark(char[][] board, int x, int y){
        if(x<0 || y<0 || x >= board.length || y >= board[0].length || board[x][y] == 'X' || board[x][y] == 'P')return;

        board[x][y] = 'P';

        mark(board,x+1,y);
        mark(board,x-1,y);
        mark(board,x,y+1);
        mark(board,x,y-1);
    }
    private void convert(char[][] board, int x, int y){
        if(x<0 || y<0 || x >= board.length || y >= board[0].length || board[x][y] == 'X' || board[x][y] == 'P')return;

        board[x][y] = 'X';
        
        convert(board,x+1,y);
        convert(board,x-1,y);
        convert(board,x,y+1);
        convert(board,x,y-1);
    }
    public void solve(char[][] board) {
        for(int i=0;i<board.length;i++){
            if(board[i][0] == 'O'){
                mark(board,i,0);
            }
            if(board[i][board[0].length-1] == 'O'){
                mark(board,i,board[0].length-1);
            }
        }
        for(int i=0;i<board[0].length;i++){
            if(board[0][i] == 'O'){
                mark(board,0,i);
            }
            if(board[board.length-1][i] == 'O'){
                mark(board,board.length-1,i);
            }
        }
        for(int i=0;i<board.length;i++){
            for(int j=0;j<board[0].length;j++){
                if(board[i][j] == 'O'){
                    convert(board,i,j);
                }
            }
        }
        for(int i=0;i<board.length;i++){
            for(int j=0;j<board[0].length;j++){
                if(board[i][j] == 'P'){
                    board[i][j] = 'O';
                }
            }
        }
    }
}

/*
Beautiful solution ,that beats 86%
*/

class Solution {
    boolean[][] vis;
    private void mark(char[][] board, int x, int y){
        if(x<0 || y<0 || x >= board.length || y >= board[0].length || board[x][y] == 'X' ||  vis[x][y])return;

        vis[x][y] = true;

        mark(board,x+1,y);
        mark(board,x-1,y);
        mark(board,x,y+1);
        mark(board,x,y-1);
    }
  
    public void solve(char[][] board) {
        vis = new boolean[board.length][board[0].length];

        for(int i=0;i<board.length;i++){
            if(board[i][0] == 'O' && !vis[i][0]){
                mark(board,i,0);
            }
            if(board[i][board[0].length-1] == 'O' && !vis[i][board[0].length-1]){
                mark(board,i,board[0].length-1);
            }
        }
        for(int i=0;i<board[0].length;i++){
            if(board[0][i] == 'O' && !vis[0][i]){
                mark(board,0,i);
            }
            if(board[board.length-1][i] == 'O' && !vis[board.length-1][i]){
                mark(board,board.length-1,i);
            }
        }
        for(int i=1;i<board.length-1;i++){
            for(int j=1;j<board[0].length-1;j++){
                if(board[i][j] == 'O' && !vis[i][j]){
                    board[i][j] = 'X';
                }
            }
        }
    }
}