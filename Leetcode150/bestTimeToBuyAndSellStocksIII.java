//best time to buy and sell stocks III : 

/*
Thought of this solution but this will work, if our max transactions is not limited to two
*/

class Solution {
    List<Integer> p = new ArrayList<>();
    public int maxProfit(int[] prices) {
        return ph(prices,0,prices.length-1);
    }
    private int ph(int[] prices, int s, int e){
        if(s>=e)return 0;

        int maxProfit = 0;
        for(int i=s;i<e;i++){
            for(int j=i+1;j<=e;j++){
                if(prices[j] > prices[i]){
                    maxProfit = Math.max(maxProfit, prices[j] - prices[i] + ph(prices,j+1,e));
                    System.out.println(maxProfit+" "+i+" "+j);
                }
            }
        }
        return maxProfit;
    }
}

/*
Brute force. The idea is essentially divide the array into two halves and get the max profit from each half. We are creating every possible half,
so we know the max profit and at the end, check if we can do only one transaction and get the max profit.

hits TLE. O(n^3) solution
*/
class Solution {
    List<Integer> p = new ArrayList<>();
    public int maxProfit(int[] prices) {
        int maxProfit = 0;
        for(int i=1;i<prices.length-1;i++){
            maxProfit = Math.max(maxProfit, findMax(prices,0,i) + findMax(prices,i+1,prices.length-1));
        }
        return Math.max(maxProfit, findMax(prices,0,prices.length-1));
    }
    private int findMax(int[] prices, int s, int e){
        if(s>=e)return 0;
        int profit = 0;
        for(int i=s;i<e;i++){
            for(int j=i+1;j<=e;j++){
                profit = Math.max(profit, prices[j] - prices[i]);
            }
        }
        return profit;
    }
}

/*
Had also thought of this solution. But this isnt recursive.
This uses the idealogy of the rainwater trapping problem.
Beats 51%
*/
class Solution {
    public int maxProfit(int[] prices) {
        int[] left = new int[prices.length];
        int[] right = new int[prices.length];

        int min = prices[0];
        int max = prices[prices.length-1];

        left[0] = 0;
        right[prices.length-1]=0;
        int result = 0;

        for(int i=1;i<prices.length;i++){
            left[i] = Math.max(left[i-1], prices[i] - min);
            min = Math.min(min, prices[i]);
        }
        for(int i=prices.length-2;i>=0;i--){
            right[i] = Math.max(right[i+1], max - prices[i]);
            max = Math.max(max, prices[i]);
            result = Math.max(result, left[i]+right[i]);
        }
        return result;
    }
}
/*
A little optimisation on the above solution, made it beat 81%
*/
class Solution {
    public int maxProfit(int[] prices) {
        int[] left = new int[prices.length];
        int[] right = new int[prices.length];

        int min = prices[0];
        int max = prices[prices.length-1];

        left[0] = 0;
        right[prices.length-1]=0;
        int result = 0;

        for(int i=1;i<prices.length;i++){
            left[i] = Math.max(left[i-1], prices[i] - min);
            right[prices.length-i-1] = Math.max(right[prices.length-i-1+1], max - prices[prices.length-i-1]);
            min = Math.min(min, prices[i]);
            max = Math.max(max, prices[prices.length-i-1]);
        }
        for(int i=0;i<prices.length;i++){
            result = Math.max(result, left[i]+right[i]);
        }
        return result;
    }
}

/*
Looking at Striver's solution, this is the recursive code I took. Hits TLE.
Pretty intuitive
*/
class Solution {
    public int maxProfit(int[] prices) {
        return mp(true,prices,0,4);
    }
    private int mp(boolean buy, int[] prices, int ind, int cap){
        if(cap == 0|| ind == prices.length)return 0;

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
        return maxProfit;
    }
}

/*
Introduced DP to the above solution and now it beats 23% users
*/
class Solution {
    int[][] dp;
    public int maxProfit(int[] prices) {
        //4 because at max transactions is 2 (so 2*2 transactions, buy,sell,buy,sell)
        dp = new int[prices.length][4+1];
        for(int[] dp1 : dp)
            Arrays.fill(dp1, -1);

        return mp(true,prices,0,4);
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