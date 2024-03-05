//Minimum path sum: https://leetcode.com/problems/minimum-path-sum/description/?envType=study-plan-v2&envId=top-interview-150

/*
My solution, simple DP, beats 68%
*/
class Solution {
    public int minPathSum(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        for(int i=1;i<n;i++){
            grid[0][i] = grid[0][i] + grid[0][i-1];
        }
        for(int i=1;i<m;i++){
            grid[i][0] = grid[i][0] + grid[i-1][0];
        }
        for(int i=1;i<m;i++){
            for(int j=1;j<n;j++){
                grid[i][j] = Math.min(grid[i-1][j],grid[i][j-1]) + grid[i][j];
            }
        }
        return grid[m-1][n-1];
    }
}

/*
The fastest solution. Beats 100%
*/

class Solution {
    int dp[][];
    public int minPathSum(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        dp = new int[m][n];

        return solve(m-1,n-1,grid);
    }
    private int solve(int r, int c, int[][] grid){
        if(r == 0 && c == 0){
            return grid[0][0];
        }
        if(r < 0 || c<0){
            return Integer.MAX_VALUE;
        }
        if(dp[r][c]!=0)return dp[r][c];
        int up = solve(r-1,c,grid);
        int left = solve(r,c-1,grid);

        dp[r][c] = grid[r][c] + Math.min(up,left);

        return dp[r][c];
    }
}