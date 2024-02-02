//Number of islands: https://leetcode.com/problems/number-of-islands/description/?envType=study-plan-v2&envId=top-interview-150


class Solution {
    int num = 0;
    private void convert(char[][] grid,int a,int b){
        if (a < 0 || b < 0 || a >= grid.length || b >= grid[0].length || grid[a][b] == '0') return;
        grid[a][b] = '0';
        
        convert(grid,a-1,b);
        convert(grid,a+1,b);
        convert(grid,a,b-1);
        convert(grid,a,b+1);  
    }

    public int numIslands(char[][] grid) {
        for(int i=0;i<grid.length;i++){
            for(int j=0;j<grid[i].length;j++){
                if(grid[i][j] == '1'){
                    num++;
                    convert(grid,i,j);
                }
            }
        }
        return num;
    }
}