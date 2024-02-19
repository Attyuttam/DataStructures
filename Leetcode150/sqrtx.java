//Sqrtx: https://leetcode.com/problems/sqrtx/description/?envType=study-plan-v2&envId=top-interview-150
/*
Beats 87.46% and that is the best because the others use Math.sqrt() which is incorrect
*/

class Solution {
    public int mySqrt(int x) {
        long s = 1;
        long e = x/2;

        long mid = 1;
        while(s<=e){
            mid = s + (e-s)/2;
            
            int val = Math.abs((int)(mid*mid) - x);
            if(val>=0 && val <1)return (int)mid;

            if(mid*mid > x){
                e = mid - 1;
            }else{
                s = mid + 1;
            }
        }
        return mid*mid>x ? (int)mid - 1 : (int)mid;
    }
}