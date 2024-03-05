//best time to buy and sell stock IV : 

/*
Same as III, beats 45.44% users
*/
class Solution {
    int[][] dp;
    public int maxProfit(int k, int[] prices) {
        //4 because at max transactions is 2 (so 2*2 transactions, buy,sell,buy,sell)
        dp = new int[prices.length][(2*k)+1];
        for(int[] dp1 : dp)
            Arrays.fill(dp1, -1);

        return mp(true,prices,0,2*k);
    }
    private int mp(boolean buy, int[] prices, int ind, int cap){
        if(cap == 0|| ind == prices.length)return 0;
        if(dp[ind][cap] != -1)return dp[ind][cap];
        int maxProfit = 0;
        if(buy){
            maxProfit = Math.max(
                -prices[ind] + mp(false, prices, ind+1, cap - 1),
                mp(true, prices, ind+1, cap)
            );
        }else{
            maxProfit = Math.max(
                prices[ind] + mp(true, prices, ind+1, cap - 1),
                mp(false, prices, ind+1, cap)
            );
        }
        dp[ind][cap] = maxProfit;
        return maxProfit;
    }
}
