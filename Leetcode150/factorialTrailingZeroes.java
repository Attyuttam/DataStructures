//Trailing zeroes in  factorial : https://leetcode.com/problems/factorial-trailing-zeroes/description/?envType=study-plan-v2&envId=top-interview-150

/*
It is basically finding out the number of 5's in the x!. The number of fives is equal to the number of trailing zeroes.

I still don't clearly understand the proof. See my notes for more details
*/
class Solution {
    public int trailingZeroes(int n) {
        int res = 0;
        while(n>=5){
            res+=(n/5);
            n/=5;
        }
        return res;
    }
}