//climb stairs: https://leetcode.com/problems/climbing-stairs/description/?envType=study-plan-v2&envId=top-interview-150

/*
Pretty simple.

Revisiting DP is good.

The idea is, to get the total number of ways to climb n stairs given, one can take only 1 or 2 steps at a time.

This idea can be refresented as :

F(n) = F(n-1) + F(n-2) where F(i) = i where i belongs to [0,2] and n>2

*/

class Solution {
    public int climbStairs(int n) {
        if(n<=2)return n;
        int[] dp = new int[n+1];
        dp[0] = 0;
        dp[1] = 1;
        dp[2] = 2;

        if(n<=2){
            return dp[n];
        }
        
        for(int i=3;i<=n;i++){
            dp[i] = dp[i-1] + dp[i-2];
        }

        return dp[n];
    }
}