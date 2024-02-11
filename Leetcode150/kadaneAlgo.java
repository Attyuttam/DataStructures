//maximum subarray :https://leetcode.com/problems/maximum-subarray/description/?envType=study-plan-v2&envId=top-interview-150

/*
Basic Kadane, beats 99%.

The idea is to simply keep track of the max ending value including the current element.
If its less than 0, then we rest the max value and update the max so far.
*/

class Solution {
    public int maxSubArray(int[] nums) {
        if(nums.length == 0)return 0;
        int maxSoFar = 0;
        int maxEndingHere = 0;
        int maxVal = nums[0];
        for(int i=0;i<nums.length;i++){
            maxVal = Math.max(maxVal,nums[i]);

            maxEndingHere+=nums[i];
            if(maxEndingHere < 0){
                maxEndingHere = 0;
            }
            maxSoFar = Math.max(maxEndingHere, maxSoFar);
        }
        return maxSoFar==0?maxVal:maxSoFar;
    }
}