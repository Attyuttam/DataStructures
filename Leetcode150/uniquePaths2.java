//Unique paths 2: https://leetcode.com/problems/unique-paths-ii/description/?envType=study-plan-v2&envId=top-interview-150

/*
Used the same approach as Minimum path sum and beat 100% users
*/

class Solution {
    int dp[][];
    public int uniquePathsWithObstacles(int[][] g) {
        int m = g.length;
        int n = g[0].length;
        dp = new int[m][n];
        int val = solve(m-1,n-1,g);
        return val == Integer.MAX_VALUE ? 0 : val;
    }
    private int solve(int r,int c, int[][] g){
        if(r==0 && c==0){
            if(g[r][c] == 0)
                return 1;
            return 0;
        }
        if(r <0 || c<0){
            return Integer.MAX_VALUE;
        }
        if(g[r][c] == 1){
            return Integer.MAX_VALUE;
        }
        if(dp[r][c] != 0)return dp[r][c];

        int up = solve(r-1,c,g);
        int left = solve(r,c-1,g);

        int res = 0;

        if(up != Integer.MAX_VALUE && left != Integer.MAX_VALUE){
            res = up + left;
        }else{
            res = up == Integer.MAX_VALUE ? left : up;
        }
        dp[r][c] = res;
        return res;
    }
}