//Bitwise and of numbers : https://leetcode.com/problems/bitwise-and-of-numbers-range/solutions/593317/simple-3-line-java-solution-faster-than-100

/*
The most interesting approach of solving this problem
Explanation: https://leetcode.com/problems/bitwise-and-of-numbers-range/solutions/593317/simple-3-line-java-solution-faster-than-100

*/
class Solution {
    public int rangeBitwiseAnd(int left, int right) {
        while(right>left){
            right &= (right-1);
        }
        return left&right;
    }
}