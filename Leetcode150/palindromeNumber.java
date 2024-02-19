//palindrome number:https://leetcode.com/problems/palindrome-number/description/?envType=study-plan-v2&envId=top-interview-150

class Solution {
    public boolean isPalindrome(int x) {
        if(x<0)return false;
        int temp = x;
        int res = 0;
        while(x!=0){
            res = res*10 + x%10;
            x/=10;
        }
        return temp == res;
    }
}