//House robber: https://leetcode.com/problems/house-robber/description/?envType=study-plan-v2&envId=top-interview-150

/*
DP problem.
This is the brute force logic which got a TLE
*/

class Solution {
    public int rob(int[] nums) {
        return robHelper(nums,new int[nums.length]);
    }
    private int robHelper(int[] nums, int[] vis){
        int max = 0;
        for(int i=0;i<nums.length;i++){
            if(vis[i]==1)continue;
            if(i-1>=0 && vis[i-1]==1)continue;
            if(i+1<nums.length && vis[i+1]==1)continue;

            vis[i] = 1;
            int profit = robHelper(nums,vis);
            max = Math.max(max,nums[i]+profit);
            vis[i] = 0;
        }
        return max;
    }
}

/*
Converting the same problem into a Brute force solution only but with a clear recursive equation 
Here, we have just replaced the idea of visited to a recursive equation.

The thought process that intrigued me to get to this equation is: 
At any point, the robber has two options: 1.To rob the given house 2.To not rob the given house.

And this thought process gave rise to the following recursive equation:

F(n,0) = Math.max(
			nums[i] + F(n-2,i+2),
				F(n-1,i+1))
				
Pretty awesome explanation: https://leetcode.com/problems/house-robber/solutions/156523/from-good-to-great-how-to-approach-most-of-dp-problems
*/

class Solution {
    public int rob(int[] nums) {
        return robHelper(nums,0);
    }
    private int robHelper(int[] nums,int pos){
        if(pos >= nums.length)return 0;

        return Math.max(robHelper(nums,pos+1), nums[pos] + robHelper(nums,pos+2));
    }
}

/*
Now all I need to do is insert memoization in the above solution

Beats 100%
*/

class Solution {
    int[] dp;
    public int rob(int[] nums) {
        dp = new int[nums.length];
        for(int i=0;i<nums.length;i++){
            dp[i] = -1;
        }
        return robHelper(nums,0);
    }
    private int robHelper(int[] nums,int pos){
        if(pos >= nums.length)return 0;
        if(dp[pos] != -1)return dp[pos];
        dp[pos] = Math.max(robHelper(nums,pos+1), nums[pos] + robHelper(nums,pos+2));
        return dp[pos];
    }
}