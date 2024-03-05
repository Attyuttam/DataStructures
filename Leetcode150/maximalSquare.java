//maximal square:https://leetcode.com/problems/maximal-square/description/?envType=study-plan-v2&envId=top-interview-150

/*
This solution is pretty fast but this is not recursive hence is not that interesting. Beats 88%
*/
class Solution {
    public int maximalSquare(char[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;

        int[][] dp = new int[m][n];

        int res = 0;

        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                if(i==0||j==0){
                    if(matrix[i][j] == '1'){
                        dp[i][j] = 1;
                        res = Math.max(res, 1);
                    }
                }else{
                    if(matrix[i][j] == '1'){
                        dp[i][j] = Math.min(dp[i-1][j-1], Math.min(dp[i-1][j],dp[i][j-1]))+1;
                        res = Math.max(res, dp[i][j]);
                    }
                }
            }
        }
        return res*res;
    }
}

/*
Simple recursive. Hits TLE
*/

class Solution {
    public int maximalSquare(char[][] matrix) {
        int val = 0;
            for(int i=0;i<matrix.length;i++){
                for(int j=0;j<matrix[i].length;j++){
                    if(matrix[i][j] == '1'){
                        val = Math.max(val, ms(matrix,i,j));
                    }
                }
            }
        
        return val * val;
    }
    private int ms(char[][] matrix, int m,int n){
        if(m<0 || n<0)return 0;

        if(matrix[m][n] == '0')return 0;

        return Math.min(ms(matrix,m-1,n-1),
            Math.min(ms(matrix,m-1,n),ms(matrix,m,n-1)))+1;
    
    }
}

/*
Adding DP to the above solution. Beats 33.72% users
*/

class Solution {
    int[][] dp;
    public int maximalSquare(char[][] matrix) {
        int val = 0;
        dp = new int[matrix.length][matrix[0].length];
        for(int[] dp1 : dp)
            Arrays.fill(dp1,-1);
        for(int i=0;i<matrix.length;i++){
            for(int j=0;j<matrix[i].length;j++){
                if(matrix[i][j] == '1'){
                    val = Math.max(val, ms(matrix,i,j));
                }
            }
        }
        return val * val;
    }
    private int ms(char[][] matrix, int m,int n){
        if(m<0 || n<0)return 0;
        if(dp[m][n] != -1)return dp[m][n];

        if(matrix[m][n] == '0')return 0;

        dp[m][n] = Math.min(ms(matrix,m-1,n-1),
            Math.min(ms(matrix,m-1,n),ms(matrix,m,n-1)))+1;
        return dp[m][n];
    }
}