//reverse bits: https://leetcode.com/problems/reverse-bits/description/?envType=study-plan-v2&envId=top-interview-150

/*
This is not an easy problem. You really need to understand how to use bit mnaipulation to reverse a number
*/
public class Solution {
    // you need treat n as an unsigned value
    public int reverseBits(int n) {
        int ans = 0;

        for(int i=0;i<32;i++){
            ans<<=1;
            ans |= (n&1);
            n>>=1;
        }
        return ans;
    }
}