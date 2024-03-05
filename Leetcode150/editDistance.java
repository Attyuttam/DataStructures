//edit distance: https://leetcode.com/problems/edit-distance/description/?envType=study-plan-v2&envId=top-interview-150

/*
Recursive technique. Hits TLE. The base case is very interesting and i was stuck in that. Keep that in mind
*/
class Solution {
    char[] w1,w2;
	int[][] dp;
    public int minDistance(String word1, String word2) {
		dp = new int[word1.length()+1][word2.length()+1];
		for(int[] dp1 : dp){
			Arrays.fill(dp1, -1);
		}
        w1 = word1.toCharArray();
        w2 = word2.toCharArray();
        return md(0,0);
    }
    private int md(int p1 ,int p2){
		if(dp[p1][p2] !=-1) return dp[p1][p2];
        if(p1 >= w1.length){
            return w2.length - p2;
        } 
        if(p2 >= w2.length){
            return w1.length - p1;
        }

        if(w1[p1] == w2[p2]){
            return md(p1+1,p2+1);
        }
        //insert, delete, replace
        return 1 + Math.min(md(p1,p2+1) , Math.min(md(p1+1,p2), md(p1+1,p2+1)));
    }
}

/*
Introduced DP
*/

class Solution {
    char[] w1,w2;
    int[][] dp;
    public int minDistance(String word1, String word2) {
		dp = new int[word1.length()+1][word2.length()+1];
		for(int[] dp1 : dp){
			Arrays.fill(dp1, -1);
		}
        w1 = word1.toCharArray();
        w2 = word2.toCharArray();
        return md(0,0);
    }
    private int md(int p1 ,int p2){
        if(dp[p1][p2] !=-1) return dp[p1][p2];
        if(p1 >= w1.length){
            dp[p1][p2] = w2.length - p2;
            return w2.length - p2;
        } 
        if(p2 >= w2.length){
            dp[p1][p2] = w1.length - p1;
            return w1.length - p1;
        }

        if(w1[p1] == w2[p2]){
            dp[p1][p2] = md(p1+1,p2+1);
            return dp[p1][p2];
        }
        //insert, delete, replace
        int insert = md(p1,p2+1);
        int delete = md(p1+1,p2);
        int replace = md(p1+1,p2+1);

        dp[p1][p2] = 1 + Math.min(insert , Math.min(delete, replace));
        return dp[p1][p2];
    }
}