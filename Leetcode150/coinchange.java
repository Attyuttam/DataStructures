//coin change : https://leetcode.com/problems/coin-change/?envType=study-plan-v2&envId=top-interview-150

/*
Simple recursive solution, hits TLE
*/
class Solution {
    public int coinChange(int[] coins, int amount) {
        int res = cc(coins,amount,0);
        return res == Integer.MAX_VALUE ? -1 : res;
    }
    private int cc(int[] coins, int amt, int ind){
        if(amt == 0)return 0;
        if(ind == coins.length)return Integer.MAX_VALUE;
        
        if(coins[ind]<=amt){
            int val1 = cc(coins,amt,ind+1);
            int val2 = cc(coins,amt-coins[ind],ind);

            if(val1 == Integer.MAX_VALUE && val2 == Integer.MAX_VALUE)
                return Integer.MAX_VALUE;   
            else if(val2 == Integer.MAX_VALUE)
                return val1;
            return Math.min(val1,val2+1);
        }
        return cc(coins,amt,ind+1);
    }
}
/*
Applied a 2D DP, but this failed
*/
class Solution {
    int[][] dp;
    public int coinChange(int[] coins, int amount) {
        dp = new int[coins.length][amount+1];
        for(int i=0;i<coins.length;i++){
            for(int j=0;j<=amount;j++){
                dp[i][j] = -1;
            }
        }
        int res = cc(coins,amount,0);
        return res == Integer.MAX_VALUE ? -1 : res;
    }
    private int cc(int[] coins, int amt, int ind){
        if(amt == 0)return 0;
        if(ind == coins.length)return Integer.MAX_VALUE;
        
        if(dp[ind][amt] != -1)return dp[ind][amt];

        if(coins[ind]<=amt){
            int val1 = cc(coins,amt,ind+1);
            int val2 = cc(coins,amt-coins[ind],ind);

            if(val1 == Integer.MAX_VALUE && val2 == Integer.MAX_VALUE){
                dp[ind][amt] = Integer.MAX_VALUE;
                return Integer.MAX_VALUE;   
            }
            else if(val2 == Integer.MAX_VALUE){
                dp[ind][amt] = Integer.MAX_VALUE;
                return val1;
            }
            dp[ind][amt] = Math.min(val1,val2+1);
            return dp[ind][amt];
        }
        dp[ind][amt] = cc(coins,amt,ind+1);
        return dp[ind][amt];
    }
}

/*
the idealogy shifted from keeping track of the index to only focusing on the amount, because in the same recursive stack,
we need to calculate the min amount and for that we need to check for all the coins.

This beats 49%
*/
class Solution {
    int[] dp;
    public int coinChange(int[] coins, int amount) {
        dp = new int[amount+1];
        if(amount == 0)return 0;
        for(int i=0;i<=amount;i++){
            dp[i] = Integer.MAX_VALUE;
        }
        dp[0] = 0;
        return cc(coins,amount);
    }
    private int cc(int[] coins, int amt){
        for(int i=0;i<=amt;i++){
            for(int j=0;j<coins.length;j++){
                if(i-coins[j]>=0 && dp[i-coins[j]]!=Integer.MAX_VALUE){
                    dp[i] = Math.min(dp[i], dp[i-coins[j]]+1);
                }
            }
        }
        return dp[amt] == Integer.MAX_VALUE ? -1 : dp[amt];
    }
}

/*
Just interchanging the loops so that the outer loop loops over the coins instead of the amount, in this way, we handle only the amounts
starting from the current coin value

Beats 82%
*/

class Solution {
    int[] dp;
    public int coinChange(int[] coins, int amount) {
        dp = new int[amount+1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;
        return cc(coins,amount);
    }
    private int cc(int[] coins, int amt){
        
        for(int j=0;j<coins.length;j++){
            for(int i=coins[j];i<=amt;i++){
                if(i-coins[j]>=0 && dp[i-coins[j]]!=Integer.MAX_VALUE){
                    dp[i] = Math.min(dp[i], dp[i-coins[j]]+1);
                }
            }
        }
        
        return dp[amt] == Integer.MAX_VALUE ? -1 : dp[amt];
    }
}