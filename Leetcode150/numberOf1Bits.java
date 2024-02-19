//number of 1 bits: https://leetcode.com/problems/number-of-1-bits/description/?envType=study-plan-v2&envId=top-interview-150
/*
Once you have done reverse bits, this is a piece of cake
*/
public class Solution {
    // you need to treat n as an unsigned value
    public int hammingWeight(int n) {
        int c = 0;
        for(int i=0;i<32;i++){
            if((n&1)==1)c++;
            n>>=1;
        }
        return c;
    }
}