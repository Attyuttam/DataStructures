//Longest palindromic substring: https://leetcode.com/problems/longest-palindromic-substring/?envType=study-plan-v2&envId=top-interview-150

/*
Brute force. Beats 5% !
*/

class Solution {
    private boolean isPalin(String s){
        int i = 0;
        int j = s.length()-1;

        while(i<j){
            if(s.charAt(i) != s.charAt(j))return false;
            i++;
            j--;
        }
        return true;
    }
    public String longestPalindrome(String s) {
        int n = s.length();
        int maxLen = Integer.MIN_VALUE;
        String maxSub = "";
        for(int i=0;i<n;i++){
            for(int j=i+1;j<=n;j++){
                String sub = s.substring(i,j);
                if(isPalin(sub) && maxLen < sub.length()){
                    maxLen = sub.length();
                    maxSub = sub;
                }
            }
        }
        return maxSub;
    }
}

/*
Recursive Brute Force. TLE
*/

class Solution {
    private boolean isPalin(String s){
        int i =0;
        int j = s.length() -1 ;

        while(i<j){
            if(s.charAt(i) != s.charAt(j))return false;
            i++;
            j--;
        }
        return true;
    }
    public String longestPalindrome(String s) {
        if(s.length() == 1 || s.length() == 0)return s;
        if(s.charAt(0) == s.charAt(s.length() - 1)){
            if(isPalin(s)){
                return s;
            }
        }
        String subLeft = longestPalindrome(s.substring(0,s.length()-1));
        String subRight = longestPalindrome(s.substring(1));

        if(subLeft.length() > subRight.length()){
            return subLeft;
        }
        return subRight;
    }
}

/*
Beautiful solution with respect to expanding around the center. Practice it more
*/

class Solution {
    public String longestPalindrome(String s) {
        if(s.length() <= 1)return s;
        int n = s.length();
        String maxStr = s.substring(0,1);
        for(int i=0;i<n-1;i++){
			//if the string is odd that we are forming, we will expand around the single character at i
            String odd = expand(s,i,i);
			
			//if the string is even that we are forming, we take the characters side by side and form a string and gradually expand around these two characters
            String even = expand(s,i,i+1);

            if(odd.length() > maxStr.length()){
                maxStr = odd;
            }
            if(even.length() > maxStr.length()){
                maxStr = even;
            }  
        }
        return maxStr;
    }
    private String expand(String s, int left, int right){
        while(left>=0 && right<s.length() && s.charAt(left) == s.charAt(right)){
            left--;
            right++;
        }
        return s.substring(left+1,right);
    }
}

/*
My DP based solution which beats 37%.
The idea is to take all substrings first of length 1, then 2 then 3 and so on until s.length()
*/

class Solution {
    public String longestPalindrome(String s) {
        int n = s.length();
        if(n<=1)return s;
        boolean dp[][] = new boolean[n][n];

        int endi = n-1;
        int startj = 0;
        int i = 0;
        int j = 0;
        int maxLen = Integer.MIN_VALUE;
        String maxSub = "";
        while(endi >= 0 && startj <n){
            i = 0;
            j = startj;

            while(i<=endi && j<n){
                
                if(s.charAt(i) == s.charAt(j) && (j-i<=2 || dp[i+1][j-1])){
                    dp[i][j] = true;
                    
                    if(maxLen < j-i+1){
                        maxLen = j-i+1;
                        maxSub = s.substring(i,j+1);
                        
                    }    
                }
                
                i++;
                j++;
            }
            endi--;
            startj++;
        }
        return maxSub;
    }
}

/*
Optimsed the above solution to beat 49%
*/

class Solution {
    public String longestPalindrome(String s) {
        int n = s.length();
        if(n<=1)return s;

        char[] sa = s.toCharArray();

        boolean dp[][] = new boolean[n][n];

        int endi = n-1;
        int startj = 0;
        int i = 0;
        int j = 0;
        int maxLen = Integer.MIN_VALUE;
        int maxStart = 0;
        int maxEnd = 0;
        while(endi >= 0 && startj <n){
            i = 0;
            j = startj;

            while(i<=endi && j<n){
                
                if(sa[i] == sa[j] && (j-i<=2 || dp[i+1][j-1])){
                    dp[i][j] = true;
                    
                    if(maxLen < j-i+1){
                        maxLen = j-i+1;
                        maxStart = i; 
                        maxEnd = j;
                    }    
                }
                
                i++;
                j++;
            }
            endi--;
            startj++;
        }
        return s.substring(maxStart, maxEnd+1);
    }
}