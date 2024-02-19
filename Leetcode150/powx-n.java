//powx-n: https://leetcode.com/problems/powx-n/description/?envType=study-plan-v2&envId=top-interview-150

/*
Beats 100%, simple divide and conquer
*/

class Solution {
    public double myPow(double x, int n){
        return myPow2(x,(long)n);
    }
    public double myPow2(double x, long n) {
        if(n<0)return myPow2((1/x) , -1*n);
        if(x==1 || n==0)return 1;
        if(n == 1 || x==0)return x;

        if(n%2 == 0){
            double val = myPow2(x,n/2);
            return val*val;
        }
        return x * myPow2(x,n-1);
    }
}