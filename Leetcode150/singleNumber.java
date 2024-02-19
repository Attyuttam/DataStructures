//single number: https://leetcode.com/problems/single-number/description/?envType=study-plan-v2&envId=top-interview-150
/*
Usage of XOR
*/
class Solution {
    public int singleNumber(int[] nums) {   
        int ans = nums[0];
        for(int i=1;i<nums.length;i++){
            ans^=nums[i];
        }
        return ans;
    }
}